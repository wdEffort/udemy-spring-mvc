package com.udemy.springmvc.shoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserInfo {

    @Autowired
    @Qualifier("adidasShoes") // 동일한 타입의 Bean이 여러 개 있을 때 그 중 어떤 Bean을 주입할 것인지 설정하는 어노테이션
    private Shoes shoes;

//    public Shoes getShoes() {
//        return shoes;
//    }
//
//    public void setShoes(Shoes shoes) {
//        this.shoes = shoes;
//    }

    public String getShoesBrand() {
        return "선택한 신발 브랜드: " + shoes.getBrand();
    }
}
