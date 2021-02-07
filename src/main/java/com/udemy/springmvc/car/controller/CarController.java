package com.udemy.springmvc.car.controller;

import com.udemy.springmvc.car.vo.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String carForm() {
        return "car/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView carSave(Car car) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "성공");
        mv.addObject("car", car);
        mv.setViewName("car/save");

        return mv;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public Model carView() {
        Model model = new ExtendedModelMap();
        model.addAttribute("message", "자동차 정보");
        return model;
    }
}
