<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.fileDrop{
width:100%;
height:200px;
border:1pxdottedblue;
}
</style>
<script>
$(function(){
	$(".uploadedList").on("click","span",function(event){
	var that=$(this);//클릭한span태그
		$.ajax({
			url:"/spring04/upload/delete_file",
			type:"post",
			data:{file_name:$(this).attr("data-src")},
			dataType:"text",
			success:function(result){
			if(result=="deleted"){
			that.parent("div").remove();
		}
	}
});
});
$(".fileDrop").on("dragenter dragover", function(event){
	event.preventDefault();//기본효과를막음
});
$(".fileDrop").on("drop",function(event){
	event.preventDefault();
	var files=event.originalEvent.dataTransfer.files;
	var file=files[0];
	var form_data=newFormData();
	form_data.append("file",file);
	$.ajax({
	type:"post",
	url:"/spring04/upload/ajax_upload",
	data:form_data,
	dataType:"text",
	processData:false,
	contentType:false,
	success:function(data,status,req){
	varstr="<div>";
	str+="<a href='/spring04/upload/display_file?file_name="+data+"'>"
	+getOriginalName(data)+"</a>";
	str+="<spandata-src="+data+">[삭제]</span></di
	v>";
$(".uploadedList").append(str);
}
});
});
});
function getOriginalName(fileName){
var idx=fileName.indexOf("_")+1;
return fileName.substr(idx);
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp"%>
<h2>Ajax File Upload</h2>
<div class="fileDrop"></div>
<div class="uploadedList"></div>
</body>
</html>