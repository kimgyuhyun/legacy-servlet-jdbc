<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>User + Detail 등록</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .row { margin-bottom: 8px; }
        button { margin-top: 8px; }
    </style>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
        window.CREATE_USER_DETAIL_ASYNC_URL = '${asyncWithActionUrl}';
    </script>
</head>
<body>

<h1>user + detail 등록</h1>

<form id="userWithDetailForm">
    <div class="row">
        <label for="name">이름</label>
        <input type="text" id="name" name="name" required>
    </div>
    <div class="row">
        <label for="age">나이</label>
        <input type="number" id="age" name="age" required>
    </div>
     <div class="row">
         <label for="birthDate">생년월일</label>
         <input type="date" id="birthDate" name="birthDate" required>
     </div>
     <div class="row">
         <label for="address">주소</label>
         <input type="text" id="address" name="address" required>
     </div>
     <div class="row">
         <label for="phone">전화</label>
         <input type="text" id="phone" name="phone" required>
     </div>
     <div class="row">
         <label for="job">직업</label>
         <input type="text" id="job" name="job" required>
     </div>

     <div class="row">
        <button type="submit"
            formaction="${syncWithActionUrl}"
            formmethod="post">
            동기 Form 제출
        </button>
        <button type="button" onclick="createUserDetailAsync()">유저 + 디테일 생성</button>
     </div>
</form>

<div id="result"></div>

<script src="${pageContext.request.contextPath}/js/api.js"></script>
</body>
</html>