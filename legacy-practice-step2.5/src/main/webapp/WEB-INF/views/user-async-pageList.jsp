<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>User 비동기 페이지네이션</title>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
    window.USER_LOAD_ASYNC_PAGE_LIST_URL = '${axiosListPagedUrl}';
    </script>
</head>
<body>
<h1>user 목록 — Axios 페이징</h1>

<div style="margin-bottom: 12px;">
    <label>
        page
        <input type="number" id="userPageInput" value="1" min="1" style="width:5em;">
   </label>
   <label>
        size
        <input type="number" id="userSizeInput" value="10" min="1" style="width:5em;">
   </label>
   <button type="button" onclick="userPagedBtnLoad()">목록 조회</button>
   <button type="button" onclick="userPagedBtnPrev()">이전</button>
   <button type="button" onclick="userPagedBtnNext()">다음</button>
</div>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>ID</th>
        <th>이름</th>
        <th>나이</th>
        <th>생년월일</th>
        <th>주소</th>
    </tr>
    </thead>
    <tbody id="userPagedTbody">
        <tr>
            <td colspan="5">목록 조회를 눌러 주세요.</td>
        </tr>
    </tbody>
</table>

<p id="userPagedClientMsg" style="margin-top:12px;"></p>

<script src="${pageContext.request.contextPath}/js/api.js"></script>
</body>
</html>