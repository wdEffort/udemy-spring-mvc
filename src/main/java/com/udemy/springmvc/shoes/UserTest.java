package com.udemy.springmvc.shoes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserTest {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/resources/shoes.xml");
        UserInfo user = (UserInfo) context.getBean("user");
        System.out.println(user.getShoesBrand());
    }
}
