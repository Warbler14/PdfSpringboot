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
		        <td>extract words</td>
		    </tr>
		    <c:forEach var="fileName" items="${fileList}">
		    <tr>
		        <td><a href="/storage/down/${fileName}">${fileName}</a></td>
		        <td><a href="/storage/delete/${fileName}">${fileName}</a></td>
		        <td><button onclick="loadData('${fileName}')">Click me</button></td>
		    </tr>  
		    </c:forEach>
		</table>
	</div>

<script type="text/javascript" src="/js/jquery/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

	function loadData(fileName) {
	  $.ajax({
			url: "/storage/data?fileName=" + fileName
		  	, success: function(result){
	        	console.log(result.data);
		  	}
	  });
	}
	

/*
	function loadData(fileName) {
		var url = "/storage/data?version=1&fileName=" + fileName;
		
		// Creating the XMLHttpRequest object
	    var request = new XMLHttpRequest();
	
	    // Instantiating the request object
	    request.open("GET", url);
	
	    // Defining event listener for readystatechange event
	    request.onreadystatechange = function() {
	        // Check if the request is compete and was successful
	        if(this.readyState === 4 && this.status === 200) {
	        	var responseText = this.responseText;
	        	const obj = JSON.parse(responseText);
	        	
	        	console.log(obj.data);
	        	
	        }
	    };
	
	    // Sending the request to the server
	    request.send();
	}

*/

document.addEventListener("DOMContentLoaded", function(){

});
	
</script>
	
</body>
</html>