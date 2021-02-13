package com.udemy.springmvc.validation.member.validate;

import com.udemy.springmvc.validation.member.vo.Member;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MemberValidator implements Validator {

    /**
     * 커맨드 객체에 대한 값 검증을 지원하는지의 여부를 반환
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    /**
     * 커맨드 객체에 대한 유효성 검증을 진행하는 메소드
     *
     * @param target 커맨드 객체
     * @param errors 에러 정보를 저장하는 객체
     */
    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("===== validate() start =====");

        Member member = (Member) target;

        /*
        if (member.getId() == null || member.getId().trim().isEmpty()) {
            System.out.println("회원 아이디를 입력하세요.");
            errors.rejectValue("id", "아이디 입력시 오류 발생");
        }

        if (member.getName() == null || member.getName().trim().isEmpty()) {
            System.out.println("회원 이름을 입력하세요.");
            errors.rejectValue("name", "이름 입력시 오류 발생");
        }
        */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "아이디 입력시 오류 발생");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "이름 입력시 오류 발생");

        if (member.getAge() == 0) {
            System.out.println("나이를 입력해주세요.");
            errors.rejectValue("age", "나이 입력시 오류 발생");
        }

        System.out.println("===== validate() end =====");
    }
}
