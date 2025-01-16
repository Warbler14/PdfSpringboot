<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/jstl.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>

	<h1>Pages</h1>
	
	<div>
		<a href="<c:url value='/storage/file'/>">storage file</a>	
	</div>
	<div>
		<a href="<c:url value='/wordBoard/view'/>">word board</a>	
	</div>
	<div>
		<a href="<c:url value='/workbook/manage'/>">workbook board</a>	
	</div>
	
</body>
</html>