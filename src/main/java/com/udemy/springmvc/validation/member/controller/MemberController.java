package com.udemy.springmvc.validation.member.controller;

import com.udemy.springmvc.validation.member.validate.MemberValidator;
import com.udemy.springmvc.validation.member.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class MemberController {

    @RequestMapping(value = "/members/form", method = RequestMethod.GET)
    public String memberForm() {
        return "validation/member/form";
    }

    /**
     * 1. validate() 메소드를 이용하는 방법
     * - 해당 메소드에서는 Validator 구현 객체를 사용하여 커맨드 객체에 대한 검증을 진행한다.
     *
     * @param member
     * @param bindingResult
     * @return
     */
//    @RequestMapping(value = "/members", method = RequestMethod.POST)
//    public String memberRegist(@ModelAttribute("Member") Member member, BindingResult bindingResult) {
//        // Validator 객체 생성
//        MemberValidator memberValidator = new MemberValidator();
//        memberValidator.validate(member, bindingResult);
//
//        // 검증 에러가 있는지 확인
//        if (bindingResult.hasErrors()) {
//            return "redirect:/members/form";
//        }
//
//        return "validation/member/confirm";
//    }

    /**
     * 2. @Valid와 @InitBind 어노테이션을 이용하는 방법
     *
     * @param member
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/members", method = RequestMethod.POST)
    public String memberRegist(@Valid @ModelAttribute("Member") Member member, BindingResult bindingResult) {
        // 검증 에러가 있는지 확인
        if (bindingResult.hasErrors()) {
            return "redirect:/members/form";
        }

        return "validation/member/confirm";
    }

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new MemberValidator());
    }
}
