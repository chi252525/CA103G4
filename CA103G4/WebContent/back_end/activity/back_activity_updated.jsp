<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<jsp:useBean id="couSvc" scope="page"	class="com.coucat.model.CoucatService" />


<% ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Site Title -->
<title>Backend_updated</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"crossorigin="anonymous">
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"crossorigin="anonymous"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"crossorigin="anonymous"></script>
<!-- linearicons CSS -->
<link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
<!-- Datetimepicker -->
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/back_end/activity/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath() %>/back_end/activity/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath() %>/back_end/activity/datetimepicker/jquery.datetimepicker.full.js"></script>
<script type="text/javascript">
var $JUI1 = $.noConflict(true);
</script>
<style>


#preview_box2 img {
	width: 450px;
}
#img_input2 {
	display: none;
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
								<label class="text-dark">廣告標題</label> 
									<input type="text" class="form-control" placeholder="輸入廣告標題"
										name="act_Name" value="<%=activityVO.getAct_Name()%>"required> 
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label for="sel1" class="text-dark">廣告分類</label> 
											<select	class="form-control" id="sel1" name="act_Cat" >
												<option value="${(activityVO.act_Cat==AC1)?'selected':'' }">新品上市
												<option value="${(activityVO.act_Cat==AC2)?'selected':'' }">優惠折扣
												<option value="${(activityVO.act_Cat==AC3)?'selected':'' }">分店限定
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
												<label class="text-dark"><b>預計上架日</b></label> 
												<input name="act_PreAddTime" id="start_dateTime"  value="<%=activityVO.getAct_PreAddTime()%>"  type="text" class="form-control" >
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="text-dark"><b>預計下架日</b></label> 
												<input name="act_End"  class="form-control" value="<%=activityVO.getAct_End()%>" id="end_dateTime"  type="text" >
											</div>
										</div>
										<div class="col-md-4"></div>
										<div class="col-md-12" id=""></div>

									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label for="comment" class="text-dark">廣告內容</label>
											<textarea class="form-control" rows="9" id="comment"
												name="act_Content" ><%=activityVO.getAct_Content()%></textarea>
										</div>
									</div>
									<div class="col-md-6"></div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="row">
									<div class="form-group">
										<label class="text-dark">大圖輪播</label> <input type="file"
									 value="<%=activityVO.getAct_Carousel()%>" id="getPicture" name="act_Carousel" 
									accept="image/*" onchange="onLoadBinaryFile()" />
								
								
								<img id="theImage" src="<%=request.getContextPath()%>/activity/activityshowimage.do?act_No=${activityVO.act_No}" style="width:350px;">
										
										
										
										<label class="text-dark">宣傳小圖</label>		
										<input type="file"
									 value="<%=activityVO.getAct_Pic()%>" id="getPicture1" name="act_Pic" 
									accept="image/*" onchange="onLoadBinaryFile2()" />
								
								
								<img id="theImage2" src="<%=request.getContextPath()%>/activity/activityshowsmallpic.do?act_No=${activityVO.act_No}" style="width:350px;">
										
									</div>
								</div>
							</div>
						</div>
						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="emp_No" value="E000000002">
						<button type="submit" class="btn btn-secondary">確定修改</button>
						<a
							href="<%=request.getContextPath()%>/back_end/activity/listAllActivity.jsp"
							class="btn btn-dark ">放棄編輯</a>
					</form>
				</div>
			</div>
		</div>
	</div>

<script>

$JUI1.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $JUI1('#start_dateTime').datetimepicker({
	  format:'Y-m-d H:i',
	  onShow:function(){
	   this.setOptions({
// 	    maxDate:$('#end_dateTime').val()?$('#end_dateTime').val():false
	   })
	  },
	  timepicker:true,
	  step: 30
	 });
	 
	 $JUI1('#end_dateTime').datetimepicker({
	  format:'Y-m-d H:i',
	  onShow:function(){
	   this.setOptions({
// 	    minDate:$('#start_dateTime').val()?$('#start_dateTime').val():false
	   })
	  },
	  timepicker:true,
	  step: 30
	 });
});


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
function onLoadBinaryFile() {
    var theFile = document.getElementById("getPicture");

    if (theFile.files.length != 0 && theFile.files[0].type.match(/image.*/)) {
        var reader = new FileReader();
        reader.onload = function (e) {
            var theImg = document.getElementById("theImage");
            theImg.src = e.target.result;
        };
        reader.onerror = function (e) {
            alert("error");
        };

     
        reader.readAsDataURL(theFile.files[0]);
    }
    else {
        alert("error");
    }
}
function onLoadBinaryFile2() {
    var theFile = document.getElementById("getPicture1");

    if (theFile.files.length != 0 && theFile.files[0].type.match(/image.*/)) {
        var reader = new FileReader();
        reader.onload = function (e) {
            var theImg = document.getElementById("theImage2");
            theImg.src = e.target.result;
        };
        reader.onerror = function (e) {
            alert("error");
        };

     
        reader.readAsDataURL(theFile.files[0]);
    }
    else {
        alert("error");
    }
}		
				
				
				
			</script>


	<jsp:include page="/back_end/HeadquarterFooter.jsp" flush="true" />
</body>
</html>