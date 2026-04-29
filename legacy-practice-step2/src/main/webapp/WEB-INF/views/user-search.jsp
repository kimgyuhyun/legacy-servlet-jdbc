<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>User Search</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
        window.USER_NAME_ADDRESS_SEARCH_URL = '${searchNameAddressUrl}';
    </script>
</head>
<body>
<h1>사용자 검색</h1>

<div>
    <label for="name">이름</label>
    <input type="text" id="name" placeholder="이름">

    <label for="address">주소</label>
    <input type="text" id="address" placeholder="주소">

    <button type="button" onclick="AxiosSearchUserByNameAndAddress()">검색</button>
</div>

<table border="1" cellpadding="8" cellspacing="0" style="margin-top:12px">
    <thead>
    <tr>
        <th>ID</th>
        <th>이름</th>
        <th>나이</th>
        <th>생년월일</th>
        <th>주소</th>
    </tr>
    </thead>
    <tbody id="searchResultBody">
    <tr>
        <td colspan="5">검색 결과가 없습니다.</td>
    </tr>
    </tbody>
</table>

<pre id="result" style="margin-top:12px;"></pre>

<script src="${pageContext.request.contextPath}/js/api.js"></script>

</body>
</html>