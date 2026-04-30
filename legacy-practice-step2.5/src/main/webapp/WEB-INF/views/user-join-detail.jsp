<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.legacy.practice.step2.dto.UserDetailDto" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>User Detail</title>
</head>
<body>
<h1>사용자 join 상세</h1>
<%
    UserDetailDto detail = (UserDetailDto) request.getAttribute("userDetail");
    if (detail == null) {
%>
    <p>사용자 정보를 찾을 수 없습니다.</p>
<%
    } else {
%>
    <table border="1" cellpadding="8" cellspacing="0">
       <thead>
       <tr>
        <th>ID</th>
        <th>userId</th>
        <th>전화번호</th>
        <th>직업</th>
        <th>생성일</th>
        <th>수정일</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td><%= detail.getId() %></td>
        <td><%= detail.getUserId() %></td>
        <td><%= detail.getPhone() %></td>
        <td><%= detail.getJob() %></td>
        <td><%= detail.getCreateAt() %></td>
        <td><%= detail.getUpdateAt() %></td>
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