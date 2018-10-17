<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.reply_msg.model.*"%>
<%@ page import="com.ingredients.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	PostVO postVO = (PostVO) session.getAttribute("postVO");
	MemberVO memVO = (MemberVO) request.getAttribute("memVO");
	if (memVO == null) {
		memVO = (MemberVO) session.getAttribute("memVO");
	}


%>


<jsp:useBean id="postSvc" scope="page"	class="com.post.model.PostService" />





<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">

<title>竹風堂活動瀏覽</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/res/img/icon.png" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!-- linearicons CSS -->
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
<!--your  CSS ============================================= -->

<style>
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
	font-family: Montserrat, Arial, "微軟正黑體", Microsoft JhengHei " !important;
}

.post-time {
	margin-top: 1px;
	margin-right: 10px;
}

.container-margin {
	margin-top: 60px;
	margin-bottom: 60px;
	opacity: 0.9;
}
/* image*/
.single-gallery-image {
	margin-top: 10px;
	background-repeat: no-repeat !important;
	background-position: center center !important;
	background-size: cover !important;
	height: 500px;
	-webkit-transition: all 0.3s ease 0s;
}

.single-gallery-image:hover {
	opacity: 0.8;
}

.blog-main {
	background-color: white;
	border-radius: 5px;
	;
}
.box{
background-color:rgba(253,253,253,0.8);
}

.hidebtn{
  visibility: hidden;
}

</style>
</head>
<jsp:include page="/front_end/header.jsp" flush="true" />
<body
	background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "
	width="100%" height="">
	<!--your html start==================================================================================-->
	<img
		src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg"
		width="100%" height="" alt="">
	<div class="container container-margin">
		<div class="row">
			<div class="col-sm-12 blog-main p-4 box mt-3">
				<div class="blog-post">
					<h5 class="blog-post-title">${activityVO.act_Name}
					</h5>
					<hr>
					<p class="font-italic ml-auto post-time">
						活動日期:
						<fmt:formatDate value="${activityVO.act_Start}"
							pattern="yyyy年MM月dd日 " />到<fmt:formatDate value="${activityVO.act_End}"
							pattern="yyyy年MM月dd日 " />
					</p>
					<p></p>
					<hr>
					<img id="img"
						src="<%=request.getContextPath()%>/activity/activityshowsmallpic.do?act_No=${activityVO.act_No}"
						class="img-fluid single-gallery-image img-responsive"
						style="display: block; margin: auto;" alt="Responsive image">
					<hr>
					<p>${activityVO.act_Content}</p>
					<p class="lnr lnr-eye " style="text-align: right;">${activityVO.act_Views}</p>
				</div>
					
				
		

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			

			</div>
		</div>
	</div>

</body>

</html>
