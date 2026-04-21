<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="step1.user.dto.UserDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 목록</title>
</head>
<body>
	<h1> 사용자 목록</h1>
	
	<p>
		<a href="${pageContext.request.contextPath}/user/form">등록 폼으로 이동</a>
	</p>
	
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
			<%
				List<UserDto> userList = (List<UserDto>) request.getAttribute("userList");
				if (userList == null || userList.isEmpty()) {
			%>
				<tr>
					<td colspan="7">조회된 사용자가 없습니다.</td>
				</tr>
			<%
				} else {
					for (UserDto user : userList) {
			%>
				<tr>
					<td><%= user.getId() %></td>
					<td><%= user.getName() %></td>
					<td><%= user.getAge() %></td>
					<td><%= user.getBirthDate() %></td>
					<td><%= user.getAddress() %></td>
					<td><%= user.getCreateAt() %></td>
					<td><%= user.getUpdateAt() %></td>
				</tr>
			<%
					}
				}
			%>
		</tbody>
	</table>
</body>
</html>