<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>
 <style>
 .fileDrop{
 width:80%;
 height:100px;
 border : 1px dotted gray;
 background-color : lightslategrey;
 margin: auto;
 }
 </style>
 
 <form role="form" method="post">
 	<div class="box-body">
 		<div class="form-group">
 			<label for="exampleInputEamil1">Title</label>
 			<input type="text" name="title" class="form-control" placeholder="Enter Title">
 		</div>
 		<div class="form-group">
 			<label for="exampleInputPassword1">Content</label>
 			<textarea class="form-control" name="content" rows="3" placeholder="Enter...."></textarea>
 		</div>
 		<div class="form-group">
 			<label for="exampleInputEamil1">Writer</label>
 			<input type="text" name="writer" class="form-control" placeholder="Enter Writer">
 		</div>	
 		<div class="form-group">
 			<label for="exampleInputEamil1">File DROP Here</label>
 			<div class="fileDrop"></div>
 		</div>	
 	</div>
 	<!-- /.box-body -->
 	
 	<div class="box-footer">
 		<div>
 			<hr>
 		</div>
 	
 		<ul class="mailbox-attachments clearfix uploadedList">
 		</ul>
 	
 		<button type="submit" class="btn btn-primary">Submit</button>
 	</div>
 </form>
 

 <script type="text/javascript" src="/resources/js/upload.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.12/handlebars.js"></script>
 <script id="template" type="text/x-handlebars-template">
	<li>														
		<span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
		<div class="mailbox-attachments-info">
			<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
			<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></li></a>
		</div>
	</li>
</script>
	<!-- {{imgsrc}}=이미지 파일인경우 썸네일 파일의 경로, 일반 파일인경우 단순히 파일 모양의 이미지(file.png)를 보여주는 경로 -->
	
<script>

var template = Handlebars.compile($("#template").html());


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
				var fileInfo = getFileInfo(data); // 템플릿에 필요한객체 생성
				
				var html = template(fileInfo);
				
				console.log(data);
				console.log(fileInfo);
				
				
				
			$(".uploadedList").append(str);
			}
	}); //processData,contentType 둘다 기본값은 'application/x-www-form-urlencoded' 타입으로 전소
});
	
$("#registerForm").submit(function(event){
	event.preventDefault(); // submit 기본동작을 막고, 현재 까지 업로드된 파일을 form태그 내부에 <intype='hidden'>으로 추가
	//각 파일은 'files[0]'과 같은 이름으로 추가되는데, 이 배열 표시를 이용해서 컨트롤러에서 BoardVO의 files 파라미터를 수집
	var that = $(this);
	
	var str = "";
	
	$(".uploadedList .delbtn").each(function(index){
		str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href")+"'>";
	});
	
	that.append(str);
	
	that.get(0).submit(); //jQuery의 get(0)은 순수한 DOM객체를 얻기위해서 사용. 마지막 .submit을 주석처리해보고 여러개의 파일이 존재할때 DOM 확인해볼것
});
</script>
 <%@ include file="../include/footer.jsp" %>