<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>자동차 정보 작성</title>
</head>
<body>
<h2>자동차 정보</h2>

<hr/>

<form method="post" action="${pageContext.request.contextPath}/car/save">
    <table border="1" cellpadding="2" cellspacing="0">
        <tr>
            <td><label for="carName">자동차 이름:</label></td>
            <td>
                <select name="carName" id="carName">
                    <option value="">선택</option>
                    <c:forEach var="item" items="${hitCar}">
                        <option value="${item}">${item}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="userName">소유자:</label></td>
            <td><input type="text" name="userName" id="userName" placeholder="${car.userName}"/></td>
        </tr>
        <tr>
            <td><label for="userHp">소유자 연락처:</label></td>
            <td><input type="text" name="userHp" id="userHp" placeholder="${car.userHp}"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="작성하기"/></td>
        </tr>
    </table>
</form>
</body>
</html>

