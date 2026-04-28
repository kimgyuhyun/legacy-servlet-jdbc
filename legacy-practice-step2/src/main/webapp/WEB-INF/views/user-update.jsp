<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.legacy.practice.step2.dto.UserDto" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>User 수정</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

    <script>
        window.USER_UPDATE_AJAX_FORM_URL = '${ajaxUpdateUrl}';
        window.USER_UPDATE_JSON_URL = '${jsonUpdateUrl}';
        window.USER_UPDATE_PUT_JSON_URL = '${jsonUpdatePutUrl}';
        window.USER_UPDATE_PATCH_JSON_URL = '${jsonUpdatePatchUrl}';
        window.USER_UPDATE_PATCH_JSON_MAP_URL = '${jsonMapUpdatePatchUrl}'
    </script>
</head>
<body>

<%
    UserDto user = (UserDto) request.getAttribute("user");
%>

<h1> 수정 페이지</h1>

<form id="updateForm" method="post" action="${pageContext.request.contextPath}/user/update/sync">

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
        <button type="button" onclick="updateByAjaxForm()">Ajax form 수정</button>
        <button type="button" onclick="updateByAjaxJson()">Ajax json 수정</button>
        <button type="button" onclick="updateByAxiosJson()">Axios json 수정</button>
        <button type="button" onclick="updateByFetchJson()">Fetch json 수정</button>
        <button type="button" onclick="updatePutByAxiosJson()">Axios put 수정</button>
        <button type="button" onclick="updatePatchByAxiosJson()">Axios Patch 수정</button>
        <button type="button" onclick="updatePatchByAxiosJsonMap()">Axios Patch Map 수정</button>
    </div>

</form>

<div id="result"></div>

<script src="${pageContext.request.contextPath}/js/api.js"></script>
</body>
</html>