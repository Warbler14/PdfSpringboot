<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board</title>
</head>
<style>
	.center {
	  margin: auto;
	  width: 50%;
	  /* border: 1px solid green; */
	  padding: 10px;
	}
</style>
<body>

	<h1>board</h1>
	
	
	<div style="display: block; width: 800px;">
		<div class="center">
			<table border="1">
			    <tr>
			        <td>file</td>
			        <td>word</td>
			    </tr>
			    <c:forEach var="word" items="${wordList}">
			    <tr>
			        <td><c:out value="${word.fileId}"/></td>
			         <td><c:out value="${word.word}"/></td>
			    </tr>  
			    </c:forEach>
			</table>
		</div>
		
		<div style="display: block; text-align: center;">
			<c:if test="${paging.startPage != 1 }">
				<a href="/word/boardList?currentPage=${paging.startPage - 1 }">&lt;</a>
			</c:if>
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="page">
				<c:choose>
					<c:when test="${page == paging.currentPage }">
						<b>${page }</b>
					</c:when>
					<c:when test="${page != paging.currentPage }">
						<a href="/word/boardList?currentPage=${page }">${page }</a>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.endPage != paging.lastPage}">
				<a href="/word/boardList?currentPage=${paging.endPage+1 }">&gt;</a>
			</c:if>
		</div>
	</div>

<script type="text/javascript" src="/js/jquery/jquery-3.5.1.min.js"></script>
<script type="text/javascript">


document.addEventListener("DOMContentLoaded", function(){

});
	
</script>
	
</body>
</html>