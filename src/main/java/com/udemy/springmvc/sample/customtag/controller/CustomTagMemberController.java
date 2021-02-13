package com.udemy.springmvc.sample.customtag.controller;

import com.udemy.springmvc.sample.customtag.validator.MemberValidator;
import com.udemy.springmvc.sample.customtag.vo.Code;
import com.udemy.springmvc.sample.customtag.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customtag/member/join")
public class CustomTagMemberController {

    private final String PAGE = "sample/customtag/join";

    /**
     * 새로운 회원 객체를 전달하는 메소드
     *
     * @return
     * @throws Exception
     */
    @ModelAttribute
    protected Object formBack() throws Exception {
        return new Member();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String form(Model model) {
        refData(model);

        return PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute Member member, BindingResult result, Model model) {
        new MemberValidator().validate(member, result);

        // 유효성 검증 결과 에러 정보가 있는 경우
        if (result.hasErrors()) {
            refData(model);
            return PAGE;
        }

        return "sample/customtag/confirm";
    }

    /**
     * 회원가입 페이지에 전달할 Model 객체를 저장하는 메소드
     *
     * @param model
     */
    private void refData(Model model) {
        List<Code> jobCodes = new ArrayList<Code>();
        jobCodes.add(new Code("001", "기획자"));
        jobCodes.add(new Code("002", "프로그래머"));
        jobCodes.add(new Code("003", "디자이너"));
        jobCodes.add(new Code("004", "데이터베이스 관리자"));
        jobCodes.add(new Code("005", "서버 엔지니어"));

        String[] hobbies = {"독서", "여행", "스포츠", "동산", "영화감상"};
        String[] mw = {"남성", "여성"};

        model.addAttribute("jobCodes", jobCodes);
        model.addAttribute("hobbies", hobbies);
        model.addAttribute("mw", mw);
    }
}
