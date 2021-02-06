package com.udemy.springmvc.sample.request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathVariableSampleController {

    /**
     * "@PathVariable" 어노테이션을 통해 요청 URL Path 값에 따라 보여주는 View를 다르게 설정
     *
     * @param var
     * @return
     */
    @RequestMapping("/page/{var}")
    public String page(@PathVariable("var") String var) {
        String viewName = "";

        if (var.equals("one")) {
            viewName = "sample/page/page1";
        } else if (var.equals("two")) {
            viewName = "sample/page/page2";
        }

        return viewName;
    }
}
