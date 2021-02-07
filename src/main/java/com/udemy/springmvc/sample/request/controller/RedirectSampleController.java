package com.udemy.springmvc.sample.request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RedirectSampleController {

    @RequestMapping(value = "/sample/login", method = RequestMethod.GET)
    public String login() {
        return "sample/login";
    }

    /**
     * "redirect:" 키워드를 이용하여 페이지 전환
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/sample/login", method = RequestMethod.POST)
    public String loginProcessing(HttpServletRequest request) {
        String id = request.getParameter("id");

        if (id.equals("test")) {
            return "redirect:/sample/login/success";
        }

        return "redirect:/sample/login?status=fail";
    }

    @RequestMapping(value = "/sample/login/success", method = RequestMethod.GET)
    public String loginSuccess() {
        return "sample/loginSuccess";
    }
}
