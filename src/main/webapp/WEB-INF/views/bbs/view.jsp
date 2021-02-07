<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 보기</title>
</head>
<body>
<h2>게시글 보기</h2>

<hr/>

<form method="post" action="/bbs/view">
    <table border="1" cellpadding="2" cellspacing="0">
        <tr>
            <td>제목:</td>
            <td>${posts.subject}</td>
        </tr>
        <tr>
            <td>작성자:</td>
            <td>${posts.writer}</td>
        </tr>
        <tr>
            <td>내용:</td>
            <td>${posts.contents}</td>
        </tr>
    </table>
</form>
</body>
</html>
