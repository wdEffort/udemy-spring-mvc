<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<h2>회원가입</h2>
<hr/>
<form method="post" action="${pageContext.request.contextPath}/members">
    <table border="1" cellpadding="2" cellspacing="0" width="500">
        <tr>
            <td>아이디</td>
            <td><input type="text" name="id" id="id"/></td>
        </tr>
        <tr>
            <td>이름</td>
            <td><input type="text" name="name" id="name"/></td>
        </tr>
        <tr>
            <td>나이</td>
            <td><input type="text" name="age" id="age"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="가입하기"/></td>
        </tr>
    </table>
</form>
</body>
</html>
