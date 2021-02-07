<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>자동차 정보</title>
</head>
<body>
<h2>${message}</h2>

<hr/>

<table border="1" cellpadding="2" cellspacing="0">
    <tr>
        <td>자동차 이름:</td>
        <td>
            <c:forEach var="item" items="${hitCar}">
                ${item},
            </c:forEach>
        </td>
    </tr>
</table>
</body>
</html>
