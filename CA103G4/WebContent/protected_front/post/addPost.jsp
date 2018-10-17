<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	PostVO postVO = (PostVO) request.getAttribute("postVO");
// 	System.out.println("addpost的request.getAttribute"+postVO);
	MemberVO memVO = (MemberVO) request.getAttribute("memVO");
	if (memVO == null) {
		memVO = (MemberVO) session.getAttribute("memVO");
	}
%>

<jsp:useBean id="cusmealSvc1" scope="page"
	class="com.custommeals.model.CustommealsService" />
<jsp:useBean id="memSvc" scope="page"
	class="com.member.model.MemberService" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<!-- Site Title -->
<title>addPost</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/res/img/icon.png" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!-- linearicons CSS -->
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">

<!-- star 評分 套件-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front_end/post/css/starability-all.min.css" />
<!--Bootstrap JS -->
<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js" -->
<!-- 	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" -->
<!-- 	crossorigin="anonymous"></script> -->
<!--JS BS4-->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<script
	src="<%=request.getContextPath()%>/front_end/blog_ckeditor/ckeditor.js"></script>

<style>
html {
	height: 100%;
	font-family: 'PT Sans', Microsoft JhengHei, sans-serif;
	font-size: 18px;
}

body {
	font-family: Montserrat, Arial, "微軟正黑體", "Microsoft JhengHei" !important;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}

#myDIV {
	height: 150px;
	overflow: auto;
	background-color:rgba(255,255,255,0.8);
}

#img_input2 {
	display: none;
}

#img_label2 {
	background-color: #f2d547;
	border-radius: 5px;
	display: inline-block;
	padding: 10px;
}

#preview_box2 img {
	width: 200px;
}

.postitem {
	background-color:rgba(255,255,255,0.8);
	border-radius: 5px;
	padding-top: 20px;
	padding-bottom: 20px;
}
.card-header-ning,.card-ning{
background-color:rgba(255,255,255,0.8);

}
.list-ning{
background-color:rgba(255,255,255,0.8);
}
.text-w{
color:#fff;}
</style>
</head>
<body
	background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "
	width="100%">
	<!--your html start==================================================================================-->
	<jsp:include page="/front_end/header.jsp" flush="true" />
	<img
		src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg"
		width="100%" height="" alt="">
	<div class="container my-5">
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<!-- */會員訂過的餐點 -->
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1 class="text-w">分享餐點</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 my-4">
					<div class="card card-ning">

						<%@ page import="com.custommeals.model.*"%>

						<%
	CustommealsService cusmealSvc = new CustommealsService();
	List<CustommealsVO> list = cusmealSvc.getMealByMemBuyed(memVO.getMem_No());
	pageContext.setAttribute("list", list);
%>
						<div class="card-header text-primary p-3 card-header-ning">我訂過的餐點</div>
					</div>
					<div class="card-body px-0 py-0" id="myDIV" onscroll="myFunction()">
						<ul class="list-group">
							<!-- 訂過的List -->
							<c:forEach var="custommealsVO" items="${list}">
								<li
									class="list-group-item  d-flex justify-content-between align-items-center " style="background-color:rgba(255,255,255,0.45);">
									${custommealsVO.custom_Name}
									<button type="submit"
										class="btn sharebtn btn-outline-info btn-xs ${custommealsVO.custom_No}">分享</button>
									<script>
				$(document).ready(function() {
					$(".${custommealsVO.custom_No}").click(function() {
						$("#custom_No").val("${custommealsVO.custom_No}");
						$("#custom_Name").val("${custommealsVO.custom_Name}");

					});
				});
			</script>
								</li>
							</c:forEach>

						</ul>
					</div>
				</div>
			</div>

			<script>

				function myFunction() {
					var elmnt = document.getElementById("myDIV");
					var y = elmnt.scrollTop;
				}
			</script>
			<!-- */會員訂過的餐點 -->



			<div class="row">
				<div class="col-md-12" >
					<div class="card mb-3 " style="background-color:rgba(255,255,255,0.8);">
						<div class="card-header list-ning text-primary">分享你最獨特的組合!</div>
						<div class="card-body m-2" style="background-color:rgba(255,255,255,0);">
							<form method="post"
								action="<%=request.getContextPath()%>/post/postServlet.do"
								name="insertform" enctype="multipart/form-data">


								<div class="form-group">
									<label>我的組合餐名</label> <input type="text" id="custom_Name"
										name="custom_Name" value="" class="form-control" required />
								</div>
								<input type="hidden" id="custom_No" name="custom_No" value="" />


								<div class="form-group">
									<label for="img_input2" id="img_label2">選個封面照片! <i
										class="fa fa-plus fa-lg"></i></label> <input type="file"
										id="img_input2" name="post_Photo" class="form-control"
										accept="image/*" />
									<div id="preview_box2"></div>
								</div>

								<div class="form-group">
									<p>餐點推薦度</p>
									<fieldset class="starability-slot">
										<input type="radio" id="no-rate" class="input-no-rate"
											name="post_Eva" value="0" checked aria-label="No rating." />
										<input type="radio" id="rate1" name="post_Eva" value="1" /> <label
											for="rate1">1 star.</label> <input type="radio" id="rate2"
											name="post_Eva" value="2" /> <label for="rate2">2
											stars.</label> <input type="radio" id="rate3" name="post_Eva"
											value="3" /> <label for="rate3">3 stars.</label> <input
											type="radio" id="rate4" name="post_Eva" value="4" /> <label
											for="rate4">4 stars.</label> <input type="radio" id="rate5"
											name="post_Eva" value="5" /> <label for="rate5">5
											stars.</label> <span class="starability-focus-ring"></span>
									</fieldset>
								</div>
								<!-- 編輯區塊 -->
								<textarea name="post_Cont" id="textareaning">
					            	<%=(postVO == null) ? "我推薦此餐點!湯頭就是很好喝的豚骨湯~~且叉燒肉很嫩又很少肥肉超喜歡~~麵很有嚼勁，吃起來很過癮~~ " : postVO.getPost_Cont()%>
					            </textarea>
								<!-- */編輯區塊 -->
								<br> <input type="hidden" id="mem_No" name="mem_No"
									value="${memVO.mem_No}" /> <input type="hidden" name="action"
									value="insert">
								<button type="submit" class="btn btn-success">確認分享</button>
								
								<a
									href="<%=request.getContextPath()%>/front_end/post/listAllpost.jsp"
									class="btn btn-dark ">放棄編輯</a>
							</form>
							
						</div>
					</div>
				</div>
				
	
			</div>
		</div>


		<script>
				CKEDITOR.replace('post_Cont', {
					removePlugins : 'image',
					removePlugins : 'resize',
					height : 300,
					removeDialogTabs : 'image:advanced;link:advanced',
				});
				//            extraPlugins: 'autogrow',
				//            autoGrow_minHeight: 500,
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

	</div>



</body>

</html>



<jsp:include page="/front_end/footer.jsp" flush="true" />