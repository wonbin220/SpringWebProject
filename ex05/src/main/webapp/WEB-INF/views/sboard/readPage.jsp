<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<!-- Main content -->
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
<!-- <div class="box-footer"> -->
<!-- 	<button type="submit" class="btn btn-warning modifyBtn">Modify</button>	 -->
<!-- 	<button type="submit" class="btn btn-danger removeBtn">REMOVE</button>	 -->
<!-- 	<button type="submit" class="btn btn-primary goListBtn">LIST ALL</button> -->
<!-- </div> -->
<div  class="box-footer">
<ul class="mailbox-attachments clearfix uploadedList">
</ul>
<c:if test="${login.uid == boardVO.writer }">
	<button type="submit" class="btn btn-warning modifyBtn">Modify</button>
	<button type="submit" class="btn btn-danger removeBtn">REMOVE</button>
</c:if>
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
				<c:if test="${not empty login }">
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
				</c:if>
				
				<c:if test="${empty login }">
				 <div class="box-body">
				 	<div><a href="javasrcipt:goLogin();">Login Please</a></div>
				 </div>
				</c:if>
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
		{{#eqReplyer replyer}} 
		 <a class="btn btn-primary btx-xs" data-toggle="modal" data-target="#modifyModal">Modify</a>
		{{/eqReplyer}}
		</div>
	<div>
</li>
{{/each}}
</script>

<script>


Handlebars.registerHelper("eqReplyer", function(replyer, block) {//로그인 한 사용자의 경우 값을 비교
	var accum = '';
	if (replyer == '${login.uid}') {
		accum += block.fn();
	}
	return accum;
});

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
	formObj.attr("action", "/sboard/removePage");
	formObj.submit();
	});
	
	$(".goListBtn").on("click", function(){
		formObj.attr("method", "get");
		formObj.attr("action", "/sboard/list");
		formObj.submit();
	});
});
</script>


<%@ include file="../include/footer.jsp" %>