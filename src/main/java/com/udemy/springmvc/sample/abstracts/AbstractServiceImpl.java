package com.udemy.springmvc.sample.abstracts;

import org.springframework.stereotype.Component;

@Component("service01")
public class AbstractServiceImpl implements AbstractService {

    private String name = "service";

    public String getClassName() {
        return "AbstractService : " + this.name;
    }
}
