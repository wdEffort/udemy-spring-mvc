<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 작성</title>
</head>
<body>
<h2>게시글 작성</h2>

<hr/>

<form method="post" action="${pageContext.request.contextPath}/bbs/view">
    <table border="1" cellpadding="2" cellspacing="0">
        <tr>
            <td><label for="subject">제목:</label></td>
            <td><input type="text" name="subject" id="subject"/></td>
        </tr>
        <tr>
            <td><label for="writer">작성자:</label></td>
            <td><input type="text" name="writer" id="writer"/></td>
        </tr>
        <tr>
            <td><label for="contents">내용:</label></td>
            <td><textarea name="contents" id="contents" rows="5" cols="60"></textarea></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="작성하기"/></td>
        </tr>
    </table>
</form>
</body>
</html>