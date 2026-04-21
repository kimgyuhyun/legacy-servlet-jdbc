<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 등록</title>
</head>
<body>
	<h1> 사용자 등록</h1>
	
	<form method="post" action="${pageContext.request.contextPath}/user/create">
		<div>
			<label for="name">이름</label>
			<input type="text" id="name" name="name" required>
		</div>
		<div>
			<label for="age">나이</label>
			<input type="number" id="age" name="age" min="0">
		</div>
		<div>
			<label for="birthDate">생년월일</label>
			<input type="date" id="birthDate" name="birthDate" required>
		</div>
		<div>
			<label for="address">주소</label>
			<input type="text" id="address" name="address">
		</div>
		<button type="submit">등록</button>
	</form>
</body>
</html>