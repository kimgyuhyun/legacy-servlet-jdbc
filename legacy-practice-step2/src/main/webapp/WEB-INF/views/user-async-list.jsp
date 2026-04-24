<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Async User List</title>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

    <script>
        window.USER_AJAX_LIST_URL = '${ajaxListUrl}';
        window.USER_AXIOS_LIST_URL = '${axiosListUrl}';
        window.USER_FETCH_LIST_URL = '${fetchListUrl}';

        window.USER_AJAX_DETAIL = '${ajaxDetailUrl}';
        window.USER_AXIOS_DETAIL = '${axiosDetailUrl}';
        window.USER_FETCH_DETAIL = '${fetchDetailUrl}';
    </script>
</head>
<body>
<h1> 비동기 User 목록</h1>

<div style="margin-bottom: 12px;">
    <button type="button" onclick="loadListByAjax()">Ajax 목록 조회</button>
    <button type="button" onclick="loadListByAxios()">Axios 목록 조회</button>
    <button type="button" onclick="loadListByFetch()">Fetch 목록 조회</button>
</div>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>ID</th>
        <th>이름</th>
        <th>나이</th>
        <th>생년월일</th>
        <th>주소</th>
        <th>Ajax 상세보기</th>
        <th>Axios 상세보기</th>
        <th>Fetch 상세보기</th>
    </tr>
    </thead>
    <tbody id="userTableBody">
    <tr>
        <td colspan="8">아직 조회되지 않았습니다.</td>
    </tr>
    </tbody>
</table>

<div id="result" style="margin-top: 12px;"></div>

<script src="${pageContext.request.contextPath}/js/api.js"></script>
</body>
</html>