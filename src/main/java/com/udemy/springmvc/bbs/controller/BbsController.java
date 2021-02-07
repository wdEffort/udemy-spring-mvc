package com.udemy.springmvc.bbs.controller;

import com.udemy.springmvc.bbs.vo.Bbs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/bbs")
public class BbsController {

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String bbsForm() {
        return "bbs/form";
    }

    /**
     * "@ModelAttribute" 어노테이션을 이용하여 View에서 사용할 Custom 객체에 대한 별칭을 붙일 수도 있다.
     * 만약, 아래와 같이 "posts"로 별칭을 정하면 View에서 ${posts.xxx}와 같이 사용 가능하다.
     *
     * @param bbs
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public String bbsView(@ModelAttribute("posts") Bbs bbs) {
        return "bbs/view";
    }

}
