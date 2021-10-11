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
	  /* width: 50%; */
	  /* border: 1px solid green; */
	  padding: 10px;
	}
	#header {
		background-color:lightgrey;
		height:100px;
	}
	#nav0 {
		background-color:#b9e3b4;
		height:100px;
		padding-top:10px;
	}
	#nav {
		background-color:#9ce7e7;
		margin-left:100px;
		/* width: 600px; */
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
	
	<div id="nav0">
		<h2>inputs</h2>
		<div style="margin-left: 20px;">
			<input type="text" id="inputWord" />
			<button type="button" id="btn-input-word" >input</button>
		</div>
			
	</div>
	
	<div id="nav">
		<div>
			<div>
				<span>header </span><input type="text" id="search-header" value="${word.header}" maxlength="1"/>
				<span>word   </span><input type="text" id="search-word" value="${word.word}"/>
				<button type="button" id="btn-search" >search</button>
			</div>
		</div>

		<div id="word_list_container">
			<p>wordList</p>
			<%-- <jsp:include page="section.jsp"/> --%>
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
var currentPageNumber = 1;

var wordList = function(pageNumber) {
	$("#word_list_container").html('');
	
	if(pageNumber == undefined) {
		pageNumber = currentPageNumber;
	} else {
		currentPageNumber = pageNumber;
	}
	
	var urls = "./wordList?currentPage=" + pageNumber;
	var header = $("#search-header").val();
	var word = $("#search-word").val();
	
	if(header != '') {
		urls = urls + "&header=" + header.charAt(0) ;
	}
	if(word != '') {
		urls = urls + "&word=" + word;
	}
	
	$.ajax({
		url: urls,
		method: "GET",
		success : function(result) {
			$("#word_list_container").html(result);
		},
		error : function(xhr, status, error) {
			console.log("에러발생 :  " + error);
			
		}
	});
}

$("#btn-search").click(function() {
	wordList(1);
});

$("#btn-input-word").click(function() {
	var inputWord = $("#inputWord").val();
	
	$.ajax({
		url: "./inputWord?word=" + inputWord,
		method: "GET",
		success : function(result) {
			console.log(result);
			const resultCount = result.data.resultCount;
			alert("저장 " + resultCount + " 건");
		},
		error : function(xhr, status, error) {
			console.log("에러발생 :  " + error);
			
		}
	});
	
});

wordList(1);
	
</script>
	
</body>
</html>