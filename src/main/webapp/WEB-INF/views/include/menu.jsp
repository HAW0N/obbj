<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>

</head>
<body>
<div class="tag">
<a href="/project/shop/product/list.do">ALL</a>
<c:if test="${sessionScope.admin_userid!=null}">
<a href="/project/shop/product/write.do">상품등록</a>
<a href="/project/member/list.do">회원관리</a>
</c:if>
<a href="/project/board/list.do">Q & A</a>
</div>
<div class="log">
<c:choose>
	<c:when test="${sessionScope.userid == null }">
		<a href="/project/member/login.do">로그인</a>
		<a href="/project/admin/login.do">관리자로그인</a>
	</c:when>
	<c:otherwise>
	${sessionScope.name}님이로그인중입니다.
		<a href="/project/member/logout.do">로그아웃</a>
		<a href="/project/shop/cart/list.do">장바구니</a>
		<a href="/project/admin/logout.do">관리자 로그아웃</a>
	</c:otherwise>
</c:choose>
</div>


</body>
</html>