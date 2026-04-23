<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.legacy.practice.step2.dto.UserDto" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
        window.USER_LIST_API_URL = '/user/list';
    </script>
</head>
<body>
<h1>User 목록</h1>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>ID</th>
        <th>이름</th>
        <th>상세보기</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<UserDto> userList = (List<UserDto>) request.getAttribute("userList");
        if (userList == null || userList.isEmpty()) {
    %>
    <tr>
        <td colspan="3">조회된 사용자가 없습니다.</td>
    </tr>
    <%
        } else {
            for (UserDto user : userList) {
    %>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getName() %></td>
        <td>
            <a href="${pageContext.request.contextPath}/user/syncDetail/<%= user.getId() %>">동기 상세보기</a>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

<div id="result"></div>

<script src="${pageContext.request.contextPath}/js/api.js"></script>
</body>
</html>
