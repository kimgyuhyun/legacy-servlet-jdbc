<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.legacy.practice.step2.dto.UserDto" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>User Detail</title>
</head>
<body>
<h1>사용자 상세</h1>
<%
    UserDto user = (UserDto) request.getAttribute("user");
    if (user == null) {
%>
    <p>사용자 정보를 찾을 수 없습니다.</p>
<%
    } else {
%>
    <table border="1" cellpadding="8" cellspacing="0">
       <thead>
       <tr>
        <th>ID</th>
        <th>이름</th>
        <th>나이</th>
        <th>생년월일</th>
        <th>주소</th>
        <th>생성일</th>
        <th>수정일</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getName() %></td>
        <td><%= user.getAge() %></td>
        <td><%= user.getBirthDate() %></td>
        <td><%= user.getAddress() %></td>
        <td><%= user.getCreateAt() %></td>
        <td><%= user.getUpdateAt() %></td>
      </tr>
      </tbody>
    </table>
<%
    }
%>

<p>
    <a href="${pageContext.request.contextPath}/user/list">목록으로</a>

    <a href="${pageContext.request.contextPath}/user/page">등록 페이지로</a>
</p>
</body>
</html>