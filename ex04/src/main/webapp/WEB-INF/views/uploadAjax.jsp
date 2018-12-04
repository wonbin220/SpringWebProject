<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.fileDrop{
	width: 100%;
	height: 200px;
	border: 1px dotted blue; 
}

small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>

</head>
<body>

	<h3>Ajax File Upload</h3>
	<div class="fileDrop"></div>
	
	<div class="uploadedList"></div>
	
	<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>	 

		$(".fileDrop").on("dragenter dragover", function(event){
			event.preventDefault();
		});
		$(".fileDrop").on("drop", function(event){
			event.preventDefault();
			
			var files = event.originalEvent.dataTransfer.files;	
						//event.originalEvent는 jQuery를 이용하는 경우 event가 순수한 DOM 이벤트가 아니기때문에
						//event.originalEvent를 이용해 순수한 원래의 DOM 이벤트를 가져옴
						//event.dataTransfer : 이벤트와 같이 전달된 데이터를 의미, 그안에 포함된 파일 데이터를 찾아내기 위해 dataTransfer.files를 이용
			var file = files[0];
			
			//console.log(file);//Drop이 이루어진 파일에 대한 정보를 읽어냄
			var formData = new FormData();
			
			formData.append("file", file);
			
			$.ajax({
				url : '/uploadAjax',
				data : formData,
				dataType : 'text',
				processData : false, //데이터를 일반적인 query string으로 변환할 것인지 결정, 기본값은 true
				contentType : false,// 파일의 경우 multipart/form-data 방식으로 전송하기위해서 false로 지정
				type : 'POST',
				success : function(data) {
					
					//alert(data);
					var str ="";
					
					console.log(data);
					console.log(checkImageType(data));
					
					if(checkImageType(data)){
						str ="<div><a href=displayFile?fileName="+getImageLink(data)+">"
						+"<img src='displayFile?fileName="+data+"'/>"
							+"</a><small data-src="+data+">X</small></div>";
					}else{
						str ="<div><a href='displayFile?fileName="+data+"'>"
						+getOriginalName(data)+"</a>"
						+"<small data-src="+data+">X</small></div></div>";	
					}
					$(".uploadedList").append(str);
				}
			}); //processData,contentType 둘다 기본값은 'application/x-www-form-urlencoded' 타입으로 전소
		});
		
		$(".uploadedList").on("click", "small", function(event){
			
			var that = $(this);
			
			$.ajax({
				url:"deleteFile",
				type:"post",
				data: {fileName:$(this).attr("data-src")},
				dataType:"text",
				success:function(result){
					if(result == 'deleted'){
						//alert("deleted");
						that.parent("div").remove(); //이미지나 파일옆의 'x'를 클릭하면 서버에서 'deleted'라는 메시지가 전송됨. 
													//-> 결과로 현재 첨부파일에 해당되는  <div>를 삭제.
					}
				}
			});
		});
/* 		
$(".fileDrop").on("drop", function(event) {
	event.preventDefault();
	
	var files = event.originalEvent.dataTransfer.files;
	
	var file = files[0];

	//console.log(file);
	var formData = new FormData();
	
	formData.append("file", file);

	
	$.ajax({
		  url: '/uploadAjax',
		  data: formData,
		  dataType:'text',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){
			  
			  var str ="";
			  
			  console.log(data);
			  console.log(checkImageType(data));
			  
			  if(checkImageType(data)){
				  str ="<div><a href='displayFile?fileName="+getImageLink(data)+"'>"
						  +"<img src='displayFile?fileName="+data+"'/></a>"
						  +data +"</div>";
			  }else{
				  str = "<div><a href='displayFile?fileName="+data+"'>" 
						  + getOriginalName(data)+"</a></div>";
			  }
			  
			  $(".uploadedList").append(str);
		  }
		});			
});	 */


		
	function checkImageType(fileName){
			// 정규표현식을 이용해서 파일의 확장자가 존재하는지를 검사
		var pattern = /jpg$|gif$|png$|jpeg$/i;
		
		return fileName.match(pattern);
	} // i: 대,소문자의 구분 없음.
		
	function getOriginalName(fileName){ //파일 이름 줄여주는 기능. 첨부파일의 이름이 UUID와  첨부파일의 이름이 결합될때 
									//'_'가 사용되는 것을 이용해서 원래의 파일 이름만을 추출
			if(checkImageType(fileName)){
				return;
			}
			
			var idx = fileName.indexOf("_") + 1;
			return fileName.substr(idx);
			
		}
	
	function getImageLink(fileName){
		
		if(!checkImageType(fileName)){
			return;
		}
		
		var front = fileName.substr(0,12); //'/년/월/일' 경로를 추출
		var end = fileName.substr(14);//파일 이름 앞의 's_'를 제거하는 용도.
		
		return front + end;
	}
	
	
	</script>
</body>
</html>