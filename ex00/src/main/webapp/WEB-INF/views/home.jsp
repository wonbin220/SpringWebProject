<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<script>
$.ajax({
	  method: "GET",
	  url: "doJSON",
	  dataType: "JSON",
	  success: function(result){
		  alert(result.name);
		  alert(result["name"]);
		  alert(result['name']);
	  }
	});
</script>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
