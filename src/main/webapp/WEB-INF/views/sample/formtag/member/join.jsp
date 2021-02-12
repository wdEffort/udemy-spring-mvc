<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>스프링 커스텀 태그 | 회원가입</title>
</head>
<body>
<h2>회원가입 정보 입력</h2>
<hr/>
<form:form action="${pageContext.request.contextPath}/custom/members" method="post">
    <table border="1">
        <tr>
            <td><form:label path="id">아이디</form:label></td>
            <td><form:input path="id"/></td>
        </tr>
        <tr>
            <td><form:label path="name">이름</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="가입하기"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
