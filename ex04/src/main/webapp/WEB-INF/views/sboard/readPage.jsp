<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>


<!-- Main content -->

<style type="text/css">
.popup {
	position: absolute;
}
.back { 
	background-color: gray; 
	opacity:0.5; 
	width: 100%; 
	height: 300%; 
	overflow:hidden;  
	z-index:1101;
}
.front { 
   z-index:1110; 
   opacity:1; 
   boarder:1px; 
   margin: auto; 
}
.show{
   position:relative;
   max-width: 1200px; 
   max-height: 800px; 
   overflow: auto;       
} 

</style>

<div class="popup back" style="display:none;" ></div>
	<div id="popup_front" class="popup front" style="display-none;">
	<img id="popup_img">
</div>

<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<!-- /.box-header -->
				

<form role="form" action="modifyPage" method="post">
	<input type="hidden" name="bno" value="${boardVO.bno}">
	<input type="hidden" name="page" value="${cri.page}">
	<input type="hidden" name="perPageNum" value="${cri.perPageNum}">	
	<input type="hidden" name="searchType" value="${cri.searchType}">	
	<input type="hidden" name="keyword" value="${cri.keyword}">	
</form>

<div class="box-body">
	<div class="form-group">
		<label for="exampleInputEmail1">Title</label>
		<input type="text" name="title" class="form-control" value="${boardVO.title }" readonly="readonly"> 
	</div>
	<div class="form-group">
		<label for="exampleInputPassword1">Content</label>
		<textarea class="form-control" name="content" rows="3" readonly="readonly">${boardVO.content }</textarea>  
	</div>
	<div class="form-group">
		<label for="exampleInputEmail1">Writer</label>
		<input type="text" name="writer" class="form-control" value="${boardVO.writer }" readonly="readonly"> 
	</div>
</div>
<!-- /.box-body -->
<div class="box-footer">
	<button type="submit" class="btn btn-warning modifyBtn">Modify</button>	
	<button type="submit" class="btn btn-danger removeBtn">REMOVE</button>	
	<button type="submit" class="btn btn-primary goListBtn">LIST ALL</button>
</div>



			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
	
	<div class="row">
		<div class="col-md-12">
			
			<div class="box box-success">
				<div class="box-header">
					<h3 class="box-title">ADD NEW REPLY</h3>
				</div>
				<div class="box-body">
					<label for = "newRelpyWriter">Writer</label>
						<input class="form-control" type="text" placeholder="USER ID" id="newReplyWriter">
					<label for = "newReplyText">ReplyText</label>
						<input class="form-control" type="text" placeholder="USER ID" id="newReplyText">
				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<button type="submit" class="btn btn-primary" id="replyAddBtn">ADD REPLY</button>
				</div>
			</div>
		
	
		<!-- The time line -->
		<ul class="timeline">
		  <!-- timeline time label -->
		<li class="time-label" id="repliesDiv">
		  <span class="bg-green">
		    Replies List 
		    </span>
		  </li>
		</ul>	
	
		<div class="text-center">
			<ul id='pagination' class="pagination pagination-sm no-margin"></ul>
		</div>
	
		</div>
		<!-- /.col -->
</div>
<!-- /.row -->
</section>
<!-- /.content -->
<script id="templateAttach" type="text/x-handlebars-template">
<li data-src='{{fullName}}'>
	<span class="mailbox-attachment-icon has-img">
		<img src="{{imgsrc}}" alt="Attachment">
	</span>
	<div class="mailbox-attachment-info">
		<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	</span><!--이게 왜들어가는지?-->
	</div>
</li>
</script>

<script id="template" type="text/x-handlebars-template">
{{#each .}}
<li class="replyLi data-rno={{rno}}>
<i class="fa fa-comments bg-blue"></i>
	<div class="timeline-item">
		<span class="time">
			<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
		</span>
	<h3 class="timeline-header"><strong>{{rno}}</strong> -{{replyer}}</h3>
	<div class="timeline-body">{{replytext}} </div>
		<div class="timeline-footer">
		 <a class="btn btn-primary btx-xs" data-toggle="modal" data-target="#modifyModal">Modify</a>
		</div>
	<div>
</li>
{{/each}}
</script>

<script>



Handlebars.registerHelper("prettifyDate", function(timevalue){
	var dateObj = new Date(timeValue);
	var year = dataObj.getFullYear();
	var month = dataObj.getMonth() + 1;
	var date = dateObj.getDate();
	return year+"/"+month+"/"+date;
});

var printData = function (replyArr, target, templateObject){
	
	var template = Handlebars.complie(templateObject.html());
	
	var html = template(replyArr);
	$(".replyLi").remove();
	target.after(html);
	}
	
var bno = ${boardVO.bno}; // jsp에 처리되는 문자열로 해당 게시물의 번호
var replypage = 1; // 수정, 삭제 작업 이후 사용자가 보던 댓글의 페이지 번호를 가지고 다시 목록을 출력하기 위해 유지되는 데이터


function getPage(pageInfo){ // getPage() 특정한 게시물에 대한 페이징 처리를 위해 호출되는 함수
							// jQuery를 이용해 JSON타입의 데이터를 처리
	$.getJSON(pageInfo,function(data){
		printData(data.list, $("#repliesDiv") ,$('#template'));
		printPaging(data.pageMaker, $(".pagination"));
		
		$("#modifyModal").modal('hide');
		
	});
}

var printPaging = function(pageMaker, target){
	var str="";
	
	if(pageMaker.prev){
		str += "<li><a href='"+(pageMaker.startPage-1)"'> << </a></li>";
		
	}
	
	for (var i=pageMaker.startPage, len = pageMaker.endPage; i <= len; i++){
		var startClass = pageMaker.cri.page == i?'class=active':'';
		str += "<li "+strClass+"><a href='"+i"'>"+i+"</a></li>";
	}
	
	if(pageMaker.next){
		str += "<li><a href='"+(pageMaker.endPage+1)"'> >> </a></li>";

	}
	target.html(str);
};

$("#repliesDiv").click(function () { 
	if($(".timeline li").size() > 1){ //.size() 목록을 가져오는 버튼이 보여지는 <li>만 있는 경우 1페이지의 댓글목록을 가져오기위해 처리한 코드
		return;
	}
	getPgae("/replies/" + bno + "/1");
});

$(".pagination").click("li a", function(event){
	
	event.preventDefault();
	
	replyPage = $(this).attr("href");
	
	getPage("/replies/" + bno + "/" + replyPage);
	
}); // 댓글 페이징 의 이벤트 처리, ul class="pagination"에서 이루어짐. 

</script>

<script>


<!-- fomObj => 위에 선언된 form태그 -->
$(document).ready(function(){
	
	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
	$(".modifyBtn").on("click", function(){
	formObj.attr("action", "/sboard/modifyPage");
	formObj.attr("method", "get");
	formObj.submit();
	});
	
	$(".removeBtn").on("click", function(){
	
		var replyCnt = $("#replycntSmall").html().replace(/[^0-9]/g,"");
		
		if(replyCnt > 0){
			alert("댓글이 달린 게시물을 삭제할 수 없습니다.");
			return;
		}
		
		var arr = [];
		$(".uploadedList").each(function(index){
			arr.push($(this).attr("data-src"));
		});
		
		if(arr.length > 0 ){
			$.post("/deleteAllFiles",{files:arr}, function(){
				
			});
		}
		
		formObj.attr("action", "/sboard/removePage");
		formObj.submit();
		});
	
	/* $(".removeBtn").on("click", function(){
	formObj.attr("action", "/sboard/removePage");
	formObj.submit();
	}); */
	
	$(".goListBtn").on("click", function(){
		formObj.attr("method", "get");
		formObj.attr("action", "/sboard/list");
		formObj.submit();
	});
});


var bno =  ${boardVO.bno};
var template = Handlebars.compile($("#templateAttach").html());

$.getJSON("/sboard/getAttach/"+bno,function(list){ //조회화면에서 첨부파일 처리, 조회화면에선 삭제버튼이 없다.
	$(list).each(function(){
		
		var fileInfo = getFileInfo(this);
		
		var html = template(fileInfo);
		
		$(".uploadedList").append(html);
	});
});

$(".uploadedList").on("click", ".mailbox-attachment-info a", function(event) {
	
	var fileLink = $(this).attr("href");
	
	if(checkImageType(fileLink)){
		
		event.preventDefault();//첨부파일의 제목을 클릭한경우 해당 파일이 이미지인지 체크하면, 이동을 못하도록 처리
		
		var imgTag = $("#popup_img");// 현재 클릭한 이미지의 경로를 id 속성값이 'popup_img'인 요소에 추가
		
		imgTag.attr("src", fileLink);
		
		console.log(imgTag.attr("src"));
		
		$(".popup").show('slow');
		imgTag.addClass("show");
		
	}
});

$("#popup_img").on("click", function(){  // 원본이지미가 보여진 후 다시 한번 클릭하면 이미지가 사라지는 효과
	
	$(".popup").hide('slow');
});

</script>


<%@ include file="../include/footer.jsp" %>