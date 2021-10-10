<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<body>

	<div id="nav02">
		<input type="hidden" id="workDay" value="${workDay}"/>
		<div>
			<input type="button" id="getWords" value="getWords"/>
		</div>
		
		<div class="center">
			<table border="1">
			    <tr>
			        <td>word</td>
			        <td>status</td>
			    </tr>
			    <c:forEach var="workpage" items="${workpageList}">
			    <tr>
			        <td class="keyWord"><span><c:out value="${workpage.word}"/></span></td>
			        <td><c:out value="${workpage.status}"/></td>
			    </tr>  
			    </c:forEach>
			</table>
		</div>
		<%-- 
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
		 --%>
		<!-- end NAV -->
	</div>
	
<script type="text/javascript" src="/static/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript">


document.addEventListener("DOMContentLoaded", function(){

});

$("#getWords").click(function(e) {
	//e.preventDefault();
	//if(!confirm('정말로 저장하시겠습니까?')) return;
	var workDay = $("#workDay").val();
	var urls = "./getWords?workDay=" + workDay
	
	$.ajax({
		url: urls,
		method: "GET",
		success : function(result) {
			console.log(result);
		},
		error : function(xhr, status, error) {
			console.log("에러발생 :  " + error);
			
		}
	});
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
	
