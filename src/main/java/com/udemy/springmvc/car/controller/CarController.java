package com.udemy.springmvc.car.controller;

import com.udemy.springmvc.car.vo.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/car")
public class CarController {

    /**
     * "@ModelAttribute" 어노테이션이 메소드에 붙어 있으면
     * 해당 컨트롤러에서 참조하는 모든 View에 메소드의 결과 값이 모델로 전달된다.
     *
     * @return
     */
    @ModelAttribute("hitCar")
    public String[] refHitCar() {
        return new String[]{"현대", "기아", "대우", "쌍용" };
    }

    /**
     * HTTP 요청 Method 종류에 따라 Car 객체를 생성해서
     * View에 전달하는 메소드를 실행한다.
     * 이처럼 "@ModelAttribute" 어노테이션을 사용하면 커맨드 객체를 초기화하는데 사용할 수도 있다.
     *
     * @param request
     * @return
     */
    @ModelAttribute("car")
    public Car init(HttpServletRequest request) {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            System.out.println("@ModelAttribute GET ...");

            Car car = new Car();
            car.setUserName("소유자를 입력해 주세요.");
            car.setUserHp("소유자 연락처를 입력해 주세요.");

            return car;
        } else {
            System.out.println("@ModelAttribute POST ...");

            return new Car();
        }
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String carForm() {
        return "car/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView carSave(@ModelAttribute("car") Car car) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "성공");
        mv.addObject("car", car);
        mv.setViewName("car/save");

        return mv;
    }

//    @RequestMapping(value = "/view", method = RequestMethod.GET)
//    public Model carView() {
//        Model model = new ExtendedModelMap();
//        model.addAttribute("message", "자동차 정보");
//        return model;
//    }
}
