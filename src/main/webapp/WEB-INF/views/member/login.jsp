<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
$("#btnLogin").click(function(){
var userid=$("#userid").val();
var passwd=$("#passwd").val();
if(userid==""){
alert("아이디를입력하세요.");
$("#userid").focus();
return;
}
if(passwd==""){
alert("비밀번호를입력하세요.");
$("#passwd").focus();
return;
}
document.form1.action="/project/member/login_check.do";
document.form1.submit();
});
});
</script>
</head>
<body>
<div class="wrap">
<h1>
<a href="/project/">OBBJ</a> 
</h1>
<div class="header">
<%@include file="../include/menu.jsp"%>
</div>
<h2><span>로그인</span></h2>
<form name="form1" method="post">
<table width="450px">
<tr>
<td class="input-box"><input id="userid" name="userid" class="log_input" placeholder="아이디"></td>
</tr>
<tr>
<td class="input-box"><input type="password" id="passwd" name="passwd" class="log_input" placeholder="비밀번호">
</td>
</tr>
<tr style="margin-top:15px;">
<td colspan="2" align="center">
<button type="button" id="btnLogin">로그인</button>
<c:if test="${message=='error'}">
<div style="color:red; margin-top:10px;">
아이디 또는 비밀번호가 일치하지 않습니다.
</div>
</c:if>
<c:if test="${message=='logout'}"> 
<div style="color:red;">
로그아웃 되었습니다.
</div>
</c:if>
<c:if test="${param.message=='nologin'}">
<div style="color:blue; margin-top:10px;">
로그인 하신 후 사용하세요.
</div>
</c:if>
</td>
</tr>
</table>
</form>
</div>
</body>
</html>