<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.reply_msg.model.*"%>

<%
	PostVO postVO = (PostVO) request.getAttribute("postVO");
%>

<jsp:useBean id="postSvc" scope="page"
	class="com.post.model.PostService" />
<jsp:useBean id="memSvc" scope="page"
	class="com.member.model.MemberService" />
<jsp:useBean id="cusmealSvc" scope="page"
	class="com.custommeals.model.CustommealsService" />


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>顯示單一貼文</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!-- linearicons CSS -->
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
<!--your  CSS ============================================= -->
<!-- star 評分 套件-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front_end/post/css/starability-all.min.css" />

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
</style>
</head>
<jsp:include page="/front_end/header.jsp" flush="true" />
<body
	background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "
	width="100%" height="">
	<!--your html start==================================================================================-->

	<div class="container container-margin">
		<div class="row">
	
			<div class="col-sm-12 blog-main p-2 box">
				<!-- 單一餐點貼文 -->
				<div class="blog-post">
					<h3 class="blog-post-title">
						<b>${cusmealSvc.getOneCustommeals(postVO.custom_No).custom_Name}</b>
					</h3>
					<p class="font-italic ml-auto post-time">
						<span class="lnr lnr-bookmark"></span>
						<fmt:formatDate value="${postVO.post_Time}"
							pattern="yyyy年MM月dd日 HH:mm:ss發表" />
						<br> by ${memSvc.getOne_Member(postVO.mem_No).mem_Name}
					</p>
					<hr>
					<img id="img"
						src="<%=request.getContextPath()%>/post/postshowimage.do?post_No=${postVO.post_No} "
						class="img-fluid single-gallery-image img-responsive"
						style="display: block; margin: auto;" alt="Responsive image">

					<hr>
					<p>餐點推薦度</p>
					<p class="starability-result" data-rating="${postVO.post_Eva}"></p>
					<p>${postVO.post_Cont}</p>

					<br>
					<div class="btn-group justify-content-right">
						<button type="button" class="btn ">
							<span class="lnr lnr-thumbs-up"></span>
						</button>
						<button type="button" class="btn ">
							<span class="lnr lnr-location"></span>
						</button>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/post/postServlet.do"
							style="margin-bottom: 0px;">
							<button type="submit" value="修改" class="btn ">
								<span class="lnr lnr-pencil"></span>
							</button>
							<input type="hidden" name="post_No" value="${postVO.post_No}">
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>

						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/post/postServlet.do"
							style="margin-bottom: 0px;">
							<button type="submit" class="btn " value="刪除">
								<span class="lnr lnr-cross"></span>
							</button>
							<input type="hidden" name="post_No" value="${postVO.post_No}">
							<input type="hidden" name="action" value="delete">
						</FORM>
					</div>
					<p class="lnr lnr-eye " style="text-align: right;"></p>
				</div>
					<!-- /*單一餐點貼文 -->

				<!-- 新增留言區塊 -->
				<form method="post"
							action="<%=request.getContextPath()%>/reply/replyServlet.do"
							name="insertform" enctype="multipart/form-data">
				<div class="card my-4">
					<p class="card-header">留言</p>
					<div class="card-body">
						<form>
							<div class="form-group">
								<textarea class="form-control" name="rply_Cont" rows="3"
									required></textarea>
							</div>
							<input type="hidden" id="post_No" name="post_No"
								value="${replyVO.post_No}" /> <input type="hidden" id="mem_No"
								name="mem_No" value="${replyVO.mem_No}" /> <input type="hidden"
								name="action" value="insert">
							<button type="submit" class="btn btn-primary btn-sm ">送出</button>
						</form>
					</div>
				</div>
				</form>
	<!-- /*新增留言區塊 -->


					   <%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
	
	<!-- 列出所有留言 -->
			<c:set var="listReplybypost_No" value="${postVO.post_No}"/>		
		<%
 				ReplyService rplySvc = new ReplyService(); 
 				String listReplybypost_No = postVO.getPost_No();
 				System.out.println("listReplybypost_No"+listReplybypost_No);
 				List<ReplyVO>  rplyList = rplySvc.getByPostNo(listReplybypost_No) ;
 				pageContext.setAttribute("rplyList",rplyList); 
			%>			
		<div class="content">
						<span class="right floated"><%=rplyList.size()%>個留言</span> 
					</div>
		
		<!-- Comment  comments -->
           <c:forEach var="replyVO" items="${rplyList}">
          <div class="media mb-4 test">
            <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
            <div class="my-0 ">
						<h5 class="my-0  ">${memSvc.getOne_Member(rplyVO.mem_No).mem_Name}</h5>
						${replyVO.rply_Cont}只是一條馬路的距離 可憐瑪麗走不過去 眺望著查理 縮在誰懷裡吃魚
						<div class="mt-0 ">
						<p class="mt-0">檢舉</p><p class="font-italic mx-auto post-time">
				<fmt:formatDate value="${replyVO.rply_Time}"
					pattern="yyyy年MM月dd日 HH:mm:ss" />
			</p>
              </div>
            </div>
          </div>
          </c:forEach>
		
		
		
						</div>
					</div>
				</div>
	

	<jsp:include page="/front_end/footer.jsp" flush="true" />

</body>

</html>
