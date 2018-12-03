<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
iframe {
	width:0px;
	height:0px;
	border: 0px;
}

</style>
</head>
<body>

	<form id='form1' action="uploadForm" method="post"
		enctype="multipart/form-data" target="zeroFrame">
		<input type='file' name='file'> <input type='submit'>
	</form>

	<iframe name="zeroFrame"></iframe><!-- 		width와 height를 모두 '0'으로 지정하면 화면상에선 전혀 안보이기 때문에 
						이를 이욯하면 Ajax를 사용하지 않고도 화면이 전환되지 않는 효과를 볼수있다는 장점이 있다. -->

	<script>
		function addFilePath(msg) {
			alert(msg);
			document.getElementById("form1").reset();
		}//uploadResult.jsp에서 호출하는 부모창(parent)의 함수
	</script>

<!-- 		<form id='form1' action="uploadForm" method="post" 
			enctype="multipart/form-data"> 
			<input type='file' name='file'> <input type='submit'> 
			</form>
 -->

</body>
</html>