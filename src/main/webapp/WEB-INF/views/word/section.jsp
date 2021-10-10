<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
	.titleBox {
	 	background-color:lightgrey;
		/* width:20%; */
		width:200px;
	  	padding: 5px;
	  	float:left;
	}
	.valueBox {
		/* width:80%; */
		width:600px;
	  	padding: 5px;
	  	float:left;
	  	border-style:solid
	}
	.end {
		clear:both;
	}
	.textLeft {
		text-align:left
	}
</style>
<div>
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
			<textarea name="description" cols="70" rows="4">${wordDetail.description}</textarea>
		</div>
		
		<div class="end"></div>
	
		<button type="button" id="btn-save" >save</button>
		<button type="button" id="btn-delete" >delete</button>
	</div>
</form:form>	
	
<script type="text/javascript" src="/static/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function(){
});

var ajaxSubmit = function(caller) {
	var formData = $("#sectionForm").serialize();
	var action = $("#sectionForm").attr("action");
	console.log(action);
	$.ajax({
        cache : false,
        url : action, // "${pageContext.request.contextPath}/testForm1",
        type : 'POST', 
        data : formData, 
        success : function(result) {
            console.log(result);
            if(result.status) {
            	if(caller != undefined) {
	            	caller();            		
            	}
            }
        }, // success 

        error : function(xhr, status) {
            alert(xhr + " : " + status);
        }
    });
}

var locationReload = function() {
	location.reload();
}

$("#btn-save").click(function(e) {
	e.preventDefault();
	if(!confirm('정말로 저장하시겠습니까?')) return;
	$("#sectionForm #formMethod").val("PUT");
	//$('#sectionForm')[0].submit();
	var keyWord = $("#keyWord").val();
	ajaxSubmit(locationReload);
});

$("#btn-delete").click(function(e) {
	e.preventDefault();
	if(!confirm('정말로 삭제하시겠습니까?')) return;
	$("#sectionForm #formMethod").val("DELETE");
	//$('#sectionForm')[0].submit();
	ajaxSubmit(locationReload);
	
});

	
</script>
</div>