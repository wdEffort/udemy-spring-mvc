package com.udemy.springmvc.sample.abstracts;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AbstractController 추상 클래스는 컨트롤러 클래스를 구성하는 최상위 클래스이다.
 */
//@Component
@Controller
public class AbstractController extends org.springframework.web.servlet.mvc.AbstractController {

    @Resource(name = "service01") // 의존하고자 하는 Bean 이름을 설정한다.
    private AbstractService service;

    /**
     * @param req
     * @param res
     * @return
     * @throws Exception
     */
    @Override
    @RequestMapping("/comp")
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("sample/abstracts/view");
        mv.addObject("message", "Component Annotation Test");

        System.out.println("================");
        System.out.println(service.getClassName());
        System.out.println("================");

        return mv;
    }
}
