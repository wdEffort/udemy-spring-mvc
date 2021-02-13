package com.udemy.springmvc.sample.customtag.validator;

import com.udemy.springmvc.sample.customtag.vo.Member;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Member 객체에 대한 유효성 검증 구현 객체
 */
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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required", "아이디를 입력해 주세요.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "이름을 입력해 주세요.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required", "이메일을 입력해 주세요.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.address1", "required", "주소1를 입력해 주세요.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.address2", "required", "주소2를 입력해 주세요.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "job", "required", "직업을 선택해 주세요.");

        Member member = (Member) target;

        if (member.getHobbies() == null || member.getHobbies().length == 0) {
            errors.rejectValue("hobbies", "select", "취미를 선택해 주세요.");
        }

        if (member.getGender() == null || member.getGender().trim().isEmpty()) {
            errors.rejectValue("gender", "select", "성별을 선택해 주세요.");
        }
    }
}
