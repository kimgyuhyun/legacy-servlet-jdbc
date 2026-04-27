<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.legacy.practice.step2.dto.UserDto" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>User 수정</title>
</head>
<body>

<%
    UserDto user = (UserDto) request.getAttribute("user");
%>

<h1> 수정 페이지</h1>

<form method="post" action="${pageContext.request.contextPath}/user/update/sync">

    <div>
        <label for="id">ID</label>
        <input type="text" id="id" name="id" value="<%= user.getId() %>" required />
    </div>

    <div>
        <label for="name">이름</label>
        <input type="text" id="name" name="name" value="<%= user.getName() %>" required />
    </div>

    <div>
        <label for="age">나이</label>
        <input type="number" id="age" name="age" value="<%= user.getAge() %>" required />
    </div>

    <div>
        <label for="birthDate">생년월일</label>
        <input type="date" id="birthDate" name="birthDate" value="<%= user.getBirthDate() == null ? "" : user.getBirthDate().toString() %>" required />
    </div>

    <div>
        <label for="address">주소</label>
        <input type="text" id="address" name="address" value="<%= user.getAddress() %>" required />
    </div>

    <div style="margin-top: 12px;">
        <button type="submit">수정</button>
        <a href="${pageContext.request.contextPath}/user/list">목록으로</a>
    </div>

</form>
</body>
</html>