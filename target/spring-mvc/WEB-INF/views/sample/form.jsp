<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>샘플 입력 Form 화면</title>
</head>
<body>
<div class="container">
    <form name="form" id="form" method="post" action="/sample/request">
        <p>
            <label for="id">아이디:</label>
            <input type="text" name="id" id="id"/>
        </p>
        <p>
            <label for="name">이름:</label>
            <input type="text" name="name" id="name"/>
        </p>
        <p>
            <input type="submit" value="전송"/>
        </p>
    </form>
</div>
</body>
</html>
