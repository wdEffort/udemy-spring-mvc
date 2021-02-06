package com.udemy.springmvc.sample.request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
@RequestMapping("/sample")
public class RequestSampleController {

    @RequestMapping(value = "/request/form", method = RequestMethod.GET)
    public String sampleRequestForm() {
        return "sample/form";
    }

    /**
     * @param httpServletRequest 요청 데이터를 담고 있는 객체
     * @param model
     * @return
     */
    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public String sampleRequestProcessing(HttpServletRequest httpServletRequest, Model model) {
        String id = httpServletRequest.getParameter("id");
        String name = httpServletRequest.getParameter("name");

        model.addAttribute("id", id);
        model.addAttribute("name", name);

        return "sample/result";
    }

    /**
     * URL PathVariable 같은 경우 ISO-8859-1 인코딩 형식에서 스프링을 통해  UTF-8로 변환된다.
     * 이런 경우 View에서 한글이 깨지는 현상이 발생함으로 디코딩 과정이 필요하다.
     *
     * @param pathVariable
     * @param model
     * @return
     */
    @RequestMapping(value = "/path/{pathVariable}", method = RequestMethod.GET)
    public String samplePathVariable(@PathVariable String pathVariable, Model model) throws UnsupportedEncodingException {
        URLDecoder.decode(URLDecoder.decode(pathVariable, "8859_1"), "UTF-8");

        model.addAttribute("pathVariable", pathVariable);

        return "sample/path";
    }

}
