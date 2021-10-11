<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	.pageMarker {
	  width: 5px;
	  /* border: 1px solid green; */
	  padding: 10px;
	}
	table tr td { width:80px; text-align:center; }
	.width200 { width: 200px;}
</style>

<div class="center">
	<table border="1">
	    <tr>
	    	<td>header</td>
	        <td class="width200">word</td>
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
		<a class="pageMarker" onclick="wordList(${paging.startPage - 1 })">&lt;</a>
	</c:if>
	<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="page">
		<c:choose>
			<c:when test="${page == paging.currentPage }">
				<b  class="pageMarker" >${page }</b>
			</c:when>
			<c:when test="${page != paging.currentPage }">
				<a  class="pageMarker" onclick="wordList(${page })">${page }</a>
			</c:when>
		</c:choose>
	</c:forEach>
	<c:if test="${paging.endPage != paging.lastPage}">
		<a  class="pageMarker" onclick="wordList(${paging.endPage+1 })">&gt;</a>
	</c:if>
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
