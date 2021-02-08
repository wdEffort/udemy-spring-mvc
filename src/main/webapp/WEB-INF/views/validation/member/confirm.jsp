<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입 완료</title>
</head>
<body>
<h2>회원가입 완료</h2>
<hr/>
<table border="1" cellpadding="2" cellspacing="0" width="500">
    <tr>
        <td>아이디</td>
        <td>${member.id}</td>
    </tr>
    <tr>
        <td>이름</td>
        <td>${member.name}</td>
    </tr>
    <tr>
        <td>나이</td>
        <td>${member.age}</td>
    </tr>
</table>
</body>
</html>
