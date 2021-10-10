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
	#header {
		background-color:lightgrey;
		height:100px;
	}
	#nav {
		background-color:#9ce7e7;
		width: 600px;
		float:left;
	}
	#section {
		float:left;
		padding:10px;
	}
	#footer {
		background-color:#FFCC00;
		height:100px;
		clear:both;
	}
	#header, #nav, #section, #footer { text-align:center }
	#header, #footer { line-height:100px; }
</style>
<body>
	<div id="header">
		<h2>Word Board</h2>
	</div>
	
	<div id="nav">
		
		<div class="center">
			<table border="1">
			    <tr>
			    	<td>header</td>
			        <td>word</td>
			        <td>lank</td>
			        <td>difficulty</td>
			    </tr>
			    <c:forEach var="word" items="${wordList}">
			    <tr>
			    	<td><c:out value="${word.header}"/></td>
			        <td class="keyWord"><span><c:out value="${word.word}"/></span></td>
			        <td><c:out value="${word.lank}"/></td>
			        <td><c:out value="${word.difficulty}"/></td>
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
		
		<!-- end NAV -->
	</div>
	
	<div id="section">
		<div id="section_container">
			<p>section</p>
			<%-- <jsp:include page="section.jsp"/> --%>
		</div>
		
	</div>
	
	<div id="footer">
		<p>footer</p>
	</div>

<script type="text/javascript" src="/static/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript">


document.addEventListener("DOMContentLoaded", function(){

});

var loadSection = function(text) {
	$("#section_container").html('');
	
	$.ajax({
		url: "./section?word=" + text,
		method: "GET",
		success : function(result) {
			$("#section_container").html(result);
		},
		error : function(xhr, status, error) {
			console.log("에러발생 :  " + error);
			
		}
	});
}

$(".keyWord").click(function() {
	var value = $(this).text();
	
	loadSection(value);
	
});
	
</script>
	
</body>
</html>