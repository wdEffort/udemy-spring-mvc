<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>회원가입 완료</title>
</head>
<body>
<h2>회원가입 완료</h2>
<hr/>
<strong>회원정보</strong><br/>
아이디 : ${member.id}<br/>
이름 : ${member.name}<br/>
이메일 : ${member.email}<br/>
주소1 : ${member.address.address1}<br/>
주소2 : ${member.address.address2}<br/>
직업 : ${member.job}<br/>
취미 : ${member.hobbies}<br/>
성별 : ${member.gender}<br/>
기타 : ${member.etc}<br/>
약관동의 : ${member.contractAgreement}
</body>
</html>
