<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>word test</title>
</head>
<body>

	<h1>word test</h1>
	
	<div>
		<table border="1">
		    <tr>
		        <td>파일 이름</td>
		        <td>단어</td>
		        <td>참조수</td>
		        <td>등록</td>
		        <td>수정일</td>
		    </tr>
		    <c:forEach var="word" items="${wordList}">
		    <tr>
		        <td>${word.fileId}</td>
		        <td>${word.word}</td>
		        <td>${word.referanceCount}</td>
		        <td>${word.registDatetime}</td>
		        <td>${word.modifyDatetime}</td>
		    </tr>  
		    </c:forEach>
		</table>
	</div>
	
</body>
</html>