<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로그인</title>
    <c:if test="${param.status eq 'fail'}">
        <script>
            (function () {
                alert('로그인 실패');
            })();
        </script>
    </c:if>
</head>
<body>
<h2>로그인</h2>
<hr/>
<form method="post" action="/sample/login">
    <table border="1" cellpadding="2" cellspacing="0">
        <tr>
            <td>아이디:</td>
            <td><input type="text" name="id" id="id"/></td>
        </tr>
        <tr>
            <td>비밀번호:</td>
            <td><input type="password" name="pwd" id="pwd"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="로그인"/></td>
        </tr>
    </table>
</form>
</body>
</html>
