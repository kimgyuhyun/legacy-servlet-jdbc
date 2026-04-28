<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.legacy.practice.step2.dto.UserDto" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script>
        window.USER_LIST_API_URL = '/user/list';
        window.USER_AJAX_DETAIL = '${ajaxDetailUrl}';
        window.USER_AXIOS_DETAIL = '${axiosDetailUrl}';
        window.USER_FETCH_DETAIL = '${fetchDetailUrl}';

        window.USER_AJAX_FORM_DELETE = '${ajaxFormDeleteUrl}';
        window.USER_JSON_DELETE = '${jsonDeleteUrl}';
        window.USER_PATH_DELETE = '${pathDeleteUrl}';
    </script>
</head>
<body>
<h1>User 목록</h1>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>ID</th>
        <th>이름</th>
        <th>상세보기</th>
        <th>Ajax 상세보기</th>
        <th>Axios 상세보기</th>
        <th>Fetch 상세보기</th>
        <th>수정</th>
        <th>순수 폼 삭제</th>
        <th>AjaxForm 삭제</th>
        <th>AjaxJson 삭제</th>
        <th>AxiosPath 삭제</th>
        <th>FetchPath 삭제</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<UserDto> userList = (List<UserDto>) request.getAttribute("userList");
        if (userList == null || userList.isEmpty()) {
    %>
    <tr>
        <td colspan="12">조회된 사용자가 없습니다.</td>
    </tr>
    <%
        } else {
            for (UserDto user : userList) {
    %>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getName() %></td>
        <td>
            <a href="${pageContext.request.contextPath}/user/syncDetail/<%= user.getId() %>">동기 상세보기</a>
        </td>
        <td>
            <button type="button" onclick="loadDetailByAjax(<%= user.getId() %>)">Ajax 상세보기</button>
        </td>
        <td>
            <button type="button" onclick="loadDetailByAxios(<%= user.getId() %>)">Axios 상세보기</button>
        </td>
        <td>
            <button type="button" onclick="loadDetailByFetch(<%= user.getId() %>)">Fetch 상세보기</button>
        </td>
        <td>
           <a href="${pageContext.request.contextPath}/user/updatePage/<%= user.getId() %>">수정</a>
        </td>
        <td>
            <form method="post" action="${pageContext.request.contextPath}/user/delete/sync" style="display:inline;">
                <input type="hidden" name="id" value="<%= user.getId() %>">
                <button type="submit">순수 폼 삭제</button>
            </form>
        </td>
        <td>
            <button type="button" onclick="deleteAjaxForm(<%= user.getId() %>)">AjaxForm 삭제</button>
        </td>
        <td>
            <button type="button" onclick="deleteAjaxJson(<%= user.getId() %>)">AjaxJson 삭제</button>
        </td>
        <td>
            <button type="button" onclick="deleteAxiosPath(<%= user.getId() %>)">AxiosPath 삭제</button>
        </td>
        <td>
            <button type="button" onclick="deleteFetchPath(<%= user.getId() %>)">FetchPath 삭제</button>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

<div id="result"></div>

<script src="${pageContext.request.contextPath}/js/api.js"></script>
</body>
</html>
