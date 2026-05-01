<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>User Delete</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
        window.USER_ID_LIST_DELETE = '${idListDeleteUrl}';
    </script>
</head>
<body>
<h1>삭제 페이지</h1>

<div>
    <label for="idList">ID 목록</label>
    <input type="text" id="idList" placeholder="예: 1, 2, 3" style="width:220px">
    <button type="button" onclick="userIdListDelete()">ID 여러 개로 삭제</button>

</div>



<pre id="result" style="margin-top:12px;"></pre>

<script src="${pageContext.request.contextPath}/js/api.js"></script>

</body>
</html>