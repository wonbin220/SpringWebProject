<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

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
 			<input type="text" name="writer" class="form-control" value="${login.uid }" readonly>
 		</div>						<!-- JSP에서 사용하는 EL의 경우 자동으로 HttpSession에 있는 'login'을 찾아서 사용하므로  "${login.uid }와 같은 형태로 사용 할 수있다-->
 	</div>
 	<!-- /.box-body -->
 	
 	<div class="box-footer">
 		<button type="submit" class="btn btn-primary">Submit</button>
 	</div>
 </form>
 <%@ include file="../include/footer.jsp" %>