package com.udemy.springmvc.user.controller;

import com.udemy.springmvc.user.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @RequestMapping(value = "/users/create", method = RequestMethod.GET)
    public String create() {
        return "user/create";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String insert(Model model) {
        model.addAttribute("message", "회원가입이 완료되었습니다.");

        return "user/result";
    }

    @RequestMapping(value = "/users/info", method = RequestMethod.GET)
    public ModelAndView info() {
        ModelAndView mv = new ModelAndView(); // Model 데이터와 View를 설정할 수 있는 객체 생성
        mv.addObject("userId", "test");
        mv.setViewName("user/info");

        return mv;
    }

    /**
     * "@RequestParam" 어노테이션을 사용하는 경우 요청 데이터가 넘어오지 않으면 에러가 발생한다.
     * 만약, 요청 데이터가 없어도 에러를 발생시키지 않게 하려면 추가 속성들을 이용해야 한다.
     *
     * @param id
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(value = "/users/find", method = RequestMethod.GET)
    public String findUser(@RequestParam("id") String id,
                           @RequestParam("name") String name,
                           Model model) {
        model.addAttribute("id", id);
        model.addAttribute("name", name);

        return "user/find";
    }

    /**
     * Custom 객체를 Model에 담아서 View에 전달해 줄 수도 있다.
     *
     * @param id
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(value = "/users/detail", method = RequestMethod.GET)
    public String detailUserInfo(@RequestParam("id") String id,
                                 @RequestParam("name") String name,
                                 @RequestParam("email") String email,
                                 Model model) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);

        model.addAttribute("user", user);

        return "user/detail";
    }

    /**
     * Custom 객체를 메소드 인자로 사용하게되면 스프링에서 자동으로 요청 데이터를 바인딩 해주고
     * 자동으로 Model에 담아서 View로 전달한다.
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/users/detail2", method = RequestMethod.GET)
    public String detailUserInfo(User user) {
        return "user/detail";
    }
}
