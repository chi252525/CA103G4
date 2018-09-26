<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>


<% PostVO postVO = (PostVO) request.getAttribute("postVO");
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
<title>update_post_input</title>
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
<!--your  CSS ============================================= -->
<!--Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>

<style>
/* 請複製這一段到你自己的CSS*/

html {
	height: 100%;
	font-family: Microsoft JhengHei;
}

/*font */
body {
	color: white;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
	font-family: Microsoft JhengHei;
	font-weight: 400;
}

.circle-pic {
	margin-top: 10px;
	margin-left: 10px;
}

.post-time {
	margin-top: 1px;
	margin-right: 10px;
}

.container-margin {
	margin-top: 20px;
	margin-bottom: 20px;
	opacity:0.9;
}
/* image*/
.single-gallery-image {
	margin-top: 10px;
	background-repeat: no-repeat !important;
	background-position: center center !important;
	background-size: cover !important;
	height: 500px;
	-webkit-transition: all 0.3s ease 0s;
	/*        -moz-transition: all 0.3s ease 0s;*/
	/*        -o-transition: all 0.3s ease 0s;*/
	/*        transition: all 0.3s ease 0s*/
}

.row-pagewhole {
	margin-top: 30px;
}

.btn-pos {
	position: relative;
	left: 180px;
	top: 0px;
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
.postitem{
background-color:white;
border-radius: 5px;
padding-top:20px;
padding-bottom:20px;
}
</style>
</head>
<jsp:include page="/front_end/header.jsp" flush="true" />
<body
	background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "
	width="100%" height="">
	<!--your html start==================================================================================-->

	<div class="container container-margin">
		<div class="row">
			<div class="col-sm-12">
				<div class="row d-flex flex-wrap justify-content-center postitem">
					<div class="col-md-12 col-sm-12 ">
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
							action="<%=request.getContextPath()%>/post/postServlet.do"
							name="updateform" enctype="multipart/form-data">
							<h3>分享你最獨特的組合!</h3>
					
							
							<div class="form-group">
								<label>我的組合餐名</label> 
								<input type="text" id="custom_Name"
									name="custom_Name" value="${cusmealSvc1.getOneCustommeals(postVO.custom_No).custom_Name}" class="form-control"
									required />
									<input type="text" id="member_Buyed"
									name="member_Buyed" value="${cusmealSvc1.getOneCustommeals(postVO.custom_No).mem_No}" class="form-control"
									required />
								
								<input type="hidden" id="custom_No"
									name="custom_No" value="<%=postVO.getCustom_No()%>" class="form-control"
									required />
							</div>
							<div class="form-group">
									<input type="file"
									 value="<%=postVO.getPost_Photo()%>" id="getPicture" name="post_Photo" 
									accept="image/*" onchange="onLoadBinaryFile()" />
								
								
								<img id="theImage" src="<%=request.getContextPath()%>/post/postshowimage.do?post_No=${postVO.post_No} "
                      style="height:111px;width:120px;"/>
							</div>
							
							<div class="form-group">
								<p>餐點推薦度</p>
								<fieldset class="starability-slot">
									<input type="radio" id="no-rate" class="input-no-rate"
										name="post_Eva" value="0" checked aria-label="No rating." />
									<input type="radio" id="rate1" name="post_Eva" value="<%=postVO.getPost_Eva()%>" /> <label
										for="rate1">1 star.</label> <input type="radio" id="rate2"
										name="post_Eva" value="<%=postVO.getPost_Eva()%>" /> <label for="rate2">2
										stars.</label> <input type="radio" id="rate3" name="post_Eva"
										value="<%=postVO.getPost_Eva()%>" /> <label for="rate3">3 stars.</label> <input
										type="radio" id="rate4" name="post_Eva" value="<%=postVO.getPost_Eva()%>" /> <label
										for="rate4">4 stars.</label> <input type="radio" id="rate5"
										name="post_Eva" value="<%=postVO.getPost_Eva()%>" /> <label for="rate5">5
										stars.</label> <span class="starability-focus-ring"></span>
								</fieldset>
							</div>
							
							
							<div class="form-group">
								<label>評分 1-5(轉星星)</label> <input type="text" id="post_Eva"
								value="<%=postVO.getPost_Eva()%>" name="post_Eva" class="form-control" required />
							</div>
							
							
							
							<div class="form-group">
								<label>想說什麼?</label>
								<textarea class="form-control" id="textarea" name="post_Cont" rows="6" 
									 required></textarea>
							</div>
							<script>
							$(function(){
								$("#textarea").val('<%=postVO.getPost_Cont()%>');
							})
							
							</script>
							<input type="hidden" id="post_No" name="post_No" value="${postVO.post_No}" />
							<input type="hidden" id="mem_No" name="mem_No" value="${postVO.mem_No}" />
							<input type="hidden" name="action" value="update">
							<button type="submit" class="btn btn-success">送出修改</button>
						<a href="<%=request.getContextPath() %>/front_end/post/listAllpost.jsp" class="btn btn-dark ">放棄編輯</a>
						</form>
					</div>
				</div>

			</div>
			<!-- /.blog-post -->
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
			</script>
		
		</div>
	</div>


	<jsp:include page="/front_end/footer.jsp" flush="true" />

</body>

</html>
