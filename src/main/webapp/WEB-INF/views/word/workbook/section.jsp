<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
	.titleBox {
	 	background-color:lightgrey;
		width:200px;
	  	padding: 5px;
	  	float:left;
	}
	.valueBox {
		width:600px;
	  	padding: 5px;
	  	float:left;
	  	/* border-style:solid */
	}
	.end {
		clear:both;
	}
	.textLeft {
		text-align:left
	}
</style>
<div>
<c:choose>
	<c:when test="${wordDetail.word != null }">
		<form:form id="sectionForm" action="./update">
			<input type="hidden" name="formMethod" id="formMethod" value="PUT"/>
			
			<div>
				<div class="titleBox">
					<p>단어</p>
				</div>
				<div class="valueBox">
					<p id="keyWord"><c:out value="${wordDetail.word}"/></p>
					<input type="hidden" name="word" value="${wordDetail.word}"/>
				</div>
				<div class="end1"></div>
			</div>
			<div>
				<div class="titleBox">
					<p>상태</p>
				</div>
				<div class="valueBox textLeft">
					<p><span>순위  </span><input type="text" name="lank" value="${wordDetail.lank}"/></p>
					<p><span>난이도 </span><input type="text" name="difficulty" value="${wordDetail.difficulty}"/></p>
					
				</div>
			</div>
			
			<div>
				<div class="titleBox">
					<p>상세</p>
				</div>
				<div class="valueBox textLeft">
					<textarea name="description" cols="70" rows="6">${wordDetail.description}</textarea>
				</div>
				
				<div class="end"></div>
			
			</div>
		</form:form>
	</c:when>
	<c:otherwise>
		<div>
			<span>word not found</span>
		</div>
	</c:otherwise>
</c:choose>
	
<script type="text/javascript" src="/static/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function(){
	
});



	
</script>
</div>