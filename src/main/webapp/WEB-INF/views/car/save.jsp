<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>자동차 정보 입력결과</title>
</head>
<body>
<h2>${message}</h2>

<hr/>

<table border="1" cellpadding="2" cellspacing="0">
    <tr>
        <td>자동차 이름:</td>
        <td>${car.carName}</td>
    </tr>
    <tr>
        <td>소유자:</td>
        <td>${car.userName}</td>
    </tr>
    <tr>
        <td>소유자 연락처:</td>
        <td>${car.userHp}</td>
    </tr>
</table>
</body>
</html>
