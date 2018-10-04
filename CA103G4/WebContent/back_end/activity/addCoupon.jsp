<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<jsp:useBean id="couSvc" scope="page"
	class="com.coucat.model.CoucatService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Site Title -->
<title>Backend_addAct</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"crossorigin="anonymous">

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<!-- linearicons CSS -->
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
<!-- JqueryUI -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
var $JUI = $.noConflict(true);
</script>
<style>
#preview_box2 img {
	width: 450px;
}


</style>
</head>
<body>

<div class="py-5 text-white">
	<div class="container">
		<div class="row">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<div class="col-md-12">
				<ul class="nav nav-tabs">
					<li class="nav-item"><a
						href="<%=request.getContextPath()%>/back_end/activity/addCoupon.jsp"
				class=" nav-link">優惠卷設定</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/back_end/activity/listAllActivity.jsp">廣告設定</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 p-4">
				<p class="text-dark">竹風堂Coupon</p>
					<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<form method="post"
						action="<%=request.getContextPath()%>/coucat/coucatServlet.do"
						 enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-8">
							<div class="form-group">
								<label class="text-dark">優惠卷名稱</label>
								 <input type="text"	class="form-control" placeholder="輸入優惠卷名稱:" name="coucat_Name">
							</div>
									<div class="col-md-6">
									<label class="text-dark">優惠卷種類</label> 
										<select class="form-control" name="coucat_Cata">
											<option value="CC1">點數優惠</option>
											<option value="CC2">現金折扣</option>
											<option value="CC3">分店限定</option>
										</select>
									</div>
									<div class="col-md-6"></div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="text-dark">折抵現金:</label> 
											<input type="text"	class="form-control" placeholder="NT." name="coucat_Value">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="text-dark">生效日:</label> <input type="text"
												id="datepicker1" name="coucat_Valid" class="form-control">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="text-dark">失效日:</label> <input type="text"
												id="datepicker2" name="coucat_Invalid" class="form-control">
										</div>
									</div>
								<div class="col-md-3">
									<div class="form-group">
										<label class="text-dark">發放數量</label> <input type="text"
											class="form-control" placeholder="數量" name="coucat_Amo">
									</div>
								</div>
								<div class="col-md-3">
								<input type="hidden" name="action" value="insert">
							<button type="submit" class="btn btn-secondary">新增</button></div>
								<div class="col-md-3"></div>
								<div class="col-md-3"></div>
								<div class="col-md-3">
									
								</div>
							
						</div>
						<div class="col-md-4">
						
								<div class="form-group">
									<label for="img_input2" id="img_label2">選擇檔案 </label> <input
										type="file" id="img_input2" name="coucat_Pic"
										class="form-control" accept="image/*">
									<div class="card">
										<div class="card" id="preview_box2"></div>
									</div>
								</div>
							
						</div>
							
					</div>
				</form>
			</div>
		</div>
	</div>
</div>



<script>
$( function() {
  $JUI( "#datepicker1" ).datepicker({
	  
	  showAnim: "slideDown",
	  minDate: "-1d",
	  dateFormat : "yy-mm-dd"
	  
  });
  $JUI( "#datepicker2" ).datepicker({
	  
	  showAnim: "slideDown",
	  minDate: "-1d",
	  dateFormat : "yy-mm-dd"
	  
	  
  });
} );
</script>
<script>

	$("#img_input2")
			.on("change",function(e) {
						var file = e.target.files[0];
						if (!file.type.match('image.*')) {
							return false;
						}
						var reader = new FileReader();
						reader.readAsDataURL(file);
						reader.onload = function(arg) {
							var img = '<img class="preview" src="' + arg.target.result + '" alt="preview"/>';
							$("#preview_box2").empty().append(img);
						}
					});
</script>



</body>
</html>