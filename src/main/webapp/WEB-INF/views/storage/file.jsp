<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File</title>
</head>
<body>

	<h1>File</h1>
	<form action="/storage/put" method="post" enctype="multipart/form-data">
		<input name="file" type="file"/>
        <input type="submit" role="button" value="upload">
	</form>
	
	<div>
		<table border="1">
		    <tr>
		        <td>download file</td>
		        <td>delete file</td>
		    </tr>
		    <c:forEach var="fileName" items="${fileList}">
		    <tr>
		        <td><a href="/storage/down/${fileName}">${fileName}</a></td>
		        <td><a href="/storage/delete/${fileName}">${fileName}</a></td>
		    </tr>  
		    </c:forEach>
		</table>
	</div>

<script type="text/javascript">


document.addEventListener("DOMContentLoaded", function(){

});
	
</script>
	
</body>
</html>