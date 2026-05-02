<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>User 목록 (페이지)</title>
</head>
<body>
<h1>user 목록 페이지네이션</h1>

<p>
    페이지: ${currentPage} · 크기: ${pageSize}
    <c:if test="${not empty totalCount}">
        · 전체: ${totalCount}건
        <c:if test="${not empty totalPages}">
            · 총 ${totalPages}페이지
        </c:if>
   </c:if>
</p>

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
    <tbody>
    <c:choose>
        <c:when test="${empty userList}">
            <tr><td colspan="5">조회된 사용자가 없습니다.</td></tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.birthDate}</td>
                    <td>${user.address}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
        </c:choose>
    </tbody>
</table>

<div style="margin-top:16px;">
    <c:url var="prevUrl" value="${pagedListUrl}">
        <c:param name="page" value="${currentPage -1}"/>
        <c:param name="size" value="${pageSize}"/>
    </c:url>
    <c:url var="nextUrl" value="${pagedListUrl}">
        <c:param name="page" value="${currentPage +1}"/>
        <c:param name="size" value="${pageSize}"/>
    </c:url>

    <c:if test="${currentPage >1}">
        <a href="${prevUrl}">이전</a>
    </c:if>
    <c:if test="${currentPage <= 1}">이전</c:if>

    &nbsp;|&nbsp;

    <c:choose>
        <c:when test="${not empty totalPages and currentPage < totalPages}">
            <a href="${nextUrl}">다음</a>
        </c:when>
        <c:when test="${empty totalPages}">
            <a href="${nextUrl}">다음</a>
        </c:when>
        <c:otherwise>다음</c:otherwise>
    </c:choose>
</div>

</body>
</html>