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
<title>竹風堂後台管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/res/img/icon.png" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<!-- linearicons CSS -->
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
<!-- Datetimepicker -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/activity/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/back_end/activity/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/activity/datetimepicker/jquery.datetimepicker.full.js"></script>
<script type="text/javascript">
	var $JUI = $.noConflict(true);
</script>
<script
	src="<%=request.getContextPath()%>/back_end/ckeditor/ckeditor.js"></script>
<style>
#preview_box2 img {
	width: 450px;
}

#preview_box1 img {
	width: 450px;
}
</style>
</head>
<body>
	<jsp:include page="/back_end/HeadquarterHeader.jsp" flush="true" />
	<div class="py-5 text-white">
		<div class="container">
			<div class="row">
				<!-- 活動Bar -->
				<div class="col-md-12">
					<ul class="nav nav-tabs">
						<li class="nav-item"><a
							href="<%=request.getContextPath()%>/back_end/activity/addCoupon.jsp"
							class=" nav-link">優惠卷設定</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/back_end/activity/listAllActivity.jsp">廣告設定</a></li>
					</ul>
				</div>
			</div>
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
				<div class="col-md-12 p-4">
					<form method="post"
						action="<%=request.getContextPath()%>/activity/activityServlet.do"
						name="insertform" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-8">
								<div class="form-group">
									<label class="text-dark">廣告標題</label> <input type="text"
										class="form-control" placeholder="輸入廣告標題" id="act_Name" name="act_Name"
										required>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label for="sel1" class="text-dark">廣告分類</label> <select
												class="form-control" id="sel1" name="act_Cat"">
												<option value="AC1">新品上市
												<option value="AC2">優惠折扣
												<option value="AC3">分店限定
											</select> <label for="sel2" class="text-dark">欲宣傳的優惠卷</label> <select
												multiple="1" class="form-control" id="sel2" name="coucat_No">
												<c:forEach var="coucatVO" items="${couSvc.all}">
													<option value="${coucatVO.coucat_No}">${coucatVO.coucat_Name}
												</c:forEach>
											</select>
										</div>
										<div class="col-md-6"></div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="text-dark"><b>預計上架日</b></label> <input
													name="act_PreAddTime" id="start_dateTime" type="text"
													class="form-control">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="text-dark"><b>預計下架日</b></label> <input
													name="act_PreOffTime" class="form-control"
													id="end_dateTime" type="text">
											</div>
										</div>
										<div class="col-md-4"></div>
										<div class="col-md-12" id=""></div>

									</div>
								</div>

								<%
									ActivityVO actVO = (ActivityVO) request.getAttribute("actVO");
								%>



								<div class="row">
									<div class="col-12">
										<!-- 編輯區塊 -->
										<textarea name="act_Content">
					            	<%=(actVO == null) ? "青蔥豚骨拉麵現折100元優惠!" : actVO.getAct_Content()%>
					            </textarea>
										<!-- */編輯區塊 -->
									</div>
									<div class="col-md-6"></div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="row">
									<div class="form-group">
										<label class="text-dark">大圖輪播</label> <input type="file"
											id="img_input1" name="act_Carousel" class="form-control"
											accept="image/*">
										<div class="card" id="preview_box1"></div>
										<label class="text-dark">宣傳小圖</label> <input type="file"
											id="img_input2" name="act_Pic" class="form-control"
											accept="image/*">
										<div class="card" id="preview_box2"></div>
									</div>
								</div>
							</div>
						</div>
						
						
						<input type="hidden" name="action" value="insert"> <input
							type="hidden" name="emp_No" value="E000000002">
						<button type="submit" class="btn btn-secondary">新增</button>
						<a
							href="<%=request.getContextPath()%>/back_end/activity/listAllActivity.jsp"
							class="btn btn-dark ">放棄編輯</a>
						<button type="button" class=" btn btn-default mt-2" id="demo"><img  src="<%= request.getContextPath() %>/front_end/img/cutie.png" style="width:100px;height:50%;"></button>
							
					</form>
					
						<script>
							
							$("#demo").click(function(){
								console.log("demo clicked")
								$("#act_Name").val("【全台門市適用】竹風堂優惠點青蔥豚骨拉麵折100元");
								
							})
							
							</script>
				</div>
			</div>
		</div>
	</div>
	<script>
		CKEDITOR.replace('act_Content', {
			removePlugins : 'image',
			removePlugins : 'resize',
			height : 300,
			removeDialogTabs : 'image:advanced;link:advanced',
		});
		//            extraPlugins: 'autogrow',
		//            autoGrow_minHeight: 500,
	</script>
	<script>
		$JUI.datetimepicker.setLocale('zh'); // kr ko ja en
		$(function() {
			$JUI('#start_dateTime').datetimepicker(
					{
						format : 'Y-m-d H:i',
						onShow : function() {
							this.setOptions({
								maxDate : $('#end_dateTime').val() ? $(
										'#end_dateTime').val() : false
							})
						},
						timepicker : true,
						step : 1
					});

			$JUI('#end_dateTime').datetimepicker(
					{
						format : 'Y-m-d H:i',
						onShow : function() {
							this.setOptions({
								minDate : $('#start_dateTime').val() ? $(
										'#start_dateTime').val() : false
							})
						},
						timepicker : true,
						step : 1
					});
		});
	</script>
	<script>
		$("#img_input1")
				.on(
						"change",
						function(e) {
							var file = e.target.files[0];
							if (!file.type.match('image.*')) {
								return false;
							}
							var reader = new FileReader();
							reader.readAsDataURL(file);
							reader.onload = function(arg) {
								var img = '<img class="preview" src="' + arg.target.result + '" alt="preview"/>';
								$("#preview_box1").empty().append(img);
							}
						});

		$("#img_input2")
				.on(
						"change",
						function(e) {
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