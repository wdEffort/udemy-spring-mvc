package com.udemy.springmvc.sample.formtag.controller;

import com.udemy.springmvc.sample.formtag.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormTagSampleController {

    @RequestMapping(value = "/custom/members/join", method = RequestMethod.GET)
    public ModelAndView getMemberJoinPage() {
        // new ModelAndView("View 경로", "커맨드 객체 별칭", "커맨드 객체");
        return new ModelAndView("sample/formtag/member/join", "command", new Member());
    }

    @RequestMapping(value = "/custom/members", method = RequestMethod.POST)
    public String addMember(@ModelAttribute Member member, BindingResult result) {
        System.out.println("아이디 : " + member.getId());
        System.out.println("이름 : " + member.getName());

        return "redirect:/custom/members/join";
    }

}
