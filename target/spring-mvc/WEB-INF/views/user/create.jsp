<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<div class="container">
    <form name="createForm" id="createForm" method="post" action="/users">
        <p>
            <label for="userId">아이디:</label>
            <input type="text" name="userId" id="userId" size="30" maxlength="10"/>
        </p>
        <p>
            <label for="password">비밀번호:</label>
            <input type="password" name="password" id="password" size="30" maxlength="16"/>
        </p>
        <p>
            <input type="submit" value="가입하기"/>
        </p>
    </form>
</div>
</body>
</html>
