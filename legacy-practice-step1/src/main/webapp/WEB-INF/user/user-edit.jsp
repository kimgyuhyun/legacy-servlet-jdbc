<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="step1.user.dto.UserDto" %>
<%
	UserDto user = (UserDto) request.getAttribute("user");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사용자 수정</h1>
	
	<p>
		<a href="${pageContext.request.contextPath}/user/list">목록으로 이동</a>
	</p>
	
	<%
		if (user == null) {
	%>
	
		<p> 수정할 사용자 정보를 찾을 수 없습니다.</p>
	<%
		} else {
	%>
		<form method="post" action="${pageContext.request.contextPath}/user/update">
			<input type="hidden" name="id" value="<%= user.getId() %>"/>
			
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name" value="<%= user.getName() %>" required />
			</div>
			
			<div>
				<label for="age">나이</label>
				<input type="number" id="age" name="age" min="0" value="<%= user.getAge() %>" required />
			</div>
			
			<div>
				<label for="birthDate">생년월일</label>
				<input type="date" id="birthDate" name="birthDate" value="<%= user.getBirthDate() %>" required />
			</div>
			
			<div>
				<label for="address">주소</label>
				<input type="text" id="address" name="address" value="<%= user.getAddress() %>" />
			</div>
			
			<button type="submit">수정 완료</button>
		</form>
	<%
		}
	%>
</body>
</html>