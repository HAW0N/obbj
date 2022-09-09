<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>OBBJ</title>
	<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/> 
</head>
<body>

<div class="wrap">
	<div class="header">
	<h1>
		<a href="/project/">OBBJ</a> 
	</h1>
		<div class="menu">
			
			<%@ include file="include/menu.jsp" %>
			
		</div>
	</div>
	<div class="body"></div>
	<div class="footer"></div>
</div>
</body>
</html>
