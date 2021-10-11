<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>workbook</title>
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
	#nav1 {
		background-color:#96bce3;
		width: 280px;
		float:left;
	}
	#nav2 {
		background-color:#9ce7e7;
		width: 280px;
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
	table tr td { width:100px; text-align:center; }
</style>
<body>
	<div id="header">
		<h2>Workbook</h2>
	</div>
	
	<div id="nav1">
		<div>
			<input type="button" id="addWorkbook" value="addWorkbook"/>
		</div>
		
		<div class="center">
			<table border="1">
			    <tr>
			    	<td>workDay</td>
			        <td>userId</td>
			    </tr>
			    <c:forEach var="workbook" items="${workbookList}">
			    <tr>
			    	<td class="keyWord"><c:out value="${workbook.workDay}"/></td>
			        <td><c:out value="${workbook.userId}"/></td>
			    </tr>  
			    </c:forEach>
			</table>
		</div>
		
		<div style="display: block; text-align: center;">
			<c:if test="${paging.startPage != 1 }">
				<a href="./manage?currentPage=${paging.startPage - 1 }">&lt;</a>
			</c:if>
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="page">
				<c:choose>
					<c:when test="${page == paging.currentPage }">
						<b>${page }</b>
					</c:when>
					<c:when test="${page != paging.currentPage }">
						<a href="./manage?currentPage=${page }">${page }</a>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.endPage != paging.lastPage}">
				<a href="./manage?currentPage=${paging.endPage+1 }">&gt;</a>
			</c:if>
		</div>
		
		<!-- end NAV -->
	</div>
	
	<div id="nav2">
		<div id="workpage_container">
			<p>workpage</p>
		</div>
	</div>
	
	<div id="section">
		<div id="section_container">
			<p>section</p>
		</div>
		
	</div>
	
	<div id="footer">
		<p>footer</p>
	</div>

<script type="text/javascript" src="/static/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript">


document.addEventListener("DOMContentLoaded", function(){

});

var loadWorkpage = function(workDay) {
	
	$.ajax({
		url: "./workpage?workDay=" + workDay,
		method: "GET",
		success : function(result) {
			$("#workpage_container").html('');
			$("#section_container").html('');
			
			$("#workpage_container").html(result);
		},
		error : function(xhr, status, error) {
			console.log("에러발생 :  " + error);
			
		}
	});
}

$(".keyWord").click(function() {
	var value = $(this).text();
	
	loadWorkpage(value);
	
});


$("#addWorkbook").click(function() {
	$.ajax({
		url: "./addWorkbook",
		method: "GET",
		success : function(result) {
			console.log(result);
			
			location.reload();
		},
		error : function(xhr, status, error) {
			console.log("에러발생 :  " + error);
			
		}
	});
	
});

	
</script>
	
</body>
</html>