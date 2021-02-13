<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>회원가입</title>
</head>
<body>
<div align="center">
    <h2>회원가입 정보 입력</h2>
    <hr/>
    <form:form modelAttribute="member">
        <table border="1" cellpadding="2" cellspacing="0">
            <tr>
                <td><form:label path="id">아이디</form:label></td>
                <td>
                    <form:input path="id"/>
                    <form:errors path="id" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="name">이름</form:label></td>
                <td>
                    <form:input path="name"/>
                    <form:errors path="name" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="email">이메일</form:label></td>
                <td>
                    <form:input path="email"/>
                    <form:errors path="email" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="address.address1">주소1</form:label></td>
                <td>
                    <form:input path="address.address1"/>
                    <form:errors path="address.address1" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="address.address2">주소2</form:label></td>
                <td>
                    <form:input path="address.address2"/>
                    <form:errors path="address.address2" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="job">직업</form:label></td>
                <td>
                    <form:select path="job">
                        <form:option value="">선택</form:option>
                        <form:options items="${jobCodes}" itemLabel="label" itemValue="code"/>
                    </form:select>
                    <form:errors path="job" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="hobbies">취미</form:label></td>
                <td>
                    <form:checkboxes path="hobbies" items="${hobbies}"/>
                    <form:errors path="hobbies" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="gender">성별</form:label></td>
                <td>
                    <form:radiobuttons path="gender" items="${mw}"/>
                    <form:errors path="gender" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="etc">기타</form:label></td>
                <td>
                    <form:textarea path="etc" cols="20" rows="5"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <form:checkbox path="contractAgreement" label="약관에 동의합니다."/>
                </td>
            </tr>
        </table>
        <input type="submit" value="가입하기"/>
    </form:form>
</div>
</body>
</html>
