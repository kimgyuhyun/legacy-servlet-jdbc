<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>User Create</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <style>
            body { font-family: Arial, sans-serif; }
            .row { margin-bottom: 8px; }
            button { margin-right: 8px; }
            #result { margin-top: 16px; padding: 8px; background: #f4f4f4; }
    </style>
</head>
<body>
<h1>User 등록</h1>
<form id ="userForm">
    <div class="row">
        <label for="name">이름</label>
        <input type="text" id="name" name="name" required>
    </div>
    <div class="row">
        <label for="age">나이</label>
        <input type="number" id="age" name="age" min="0" required>
    </div>
    <div class="row">
            <label for="date">생년월일</label>
            <input type="date" id="birthDate" name="birthDate" required>
    </div>
    <div class="row">
            <label for="address">주소</label>
            <input type="text" id="address" name="address" required>
    </div>

    <div class="row">
        <!--1) 동기 Form 제출 -->
        <button type="submit"
            formaction="${syncActionUrl}"
            formmethod="post">
          동기 Form 제출
        </button>

        <!--2) 비동기 Form 제출 -->
        <button type="button" onclick="submitFormAjax()">
          비동기 Form 제출
        </button>

        <!--3) 비동기 JSON 제출 -->
        <button type="button" onclick="submitJsonAjax()">
          비동기 JSON 제출
        </button

</body>
</html>