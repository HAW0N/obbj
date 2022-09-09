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
<div class="wrap">
<h1>
<a href="/project/">OBBJ</a> 
</h1>
<div class="header">
<%@ include file="../include/menu.jsp" %>
</div>
<c:if test="${message=='success'}">
<h2>
${sessionScope.admin_name} ( ${sessionScope.admin_userid})님
환영합니다.
</h2>
</c:if>
</div>
</body>
</html>