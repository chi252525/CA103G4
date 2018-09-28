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

	IngredientsService ingtSvc = (IngredientsService) new IngredientsService();
	List<IngredientsVO> tags = ingtSvc.findIngtByCustomNo(postVO.getCustom_No());
	pageContext.setAttribute("tags", tags);
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

<meta property="og:url"
	content="<%=request.getContextPath()%>/post/listOnepost.jsp" />
<meta property="og:type" content="website" />
<meta property="og:title" content="Your Website Title" />
<meta property="og:description" content="Your description" />
<meta property="og:image"
	content="<%=request.getContextPath()%>/post/postshowimage.do?post_No=${postVO.post_No}" />




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
	<img
		src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg"
		width="100%" height="" alt="">
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
					<p></p>
					<hr>
					<img id="img"
						src="<%=request.getContextPath()%>/post/postshowimage.do?post_No=${postVO.post_No}"
						class="img-fluid single-gallery-image img-responsive"
						style="display: block; margin: auto;" alt="Responsive image">
					<hr>
					<p>餐點推薦度</p>
					<p class="starability-result" data-rating="${postVO.post_Eva}"></p>
					<c:forEach var="ingredientsVO" items="${tags}">
						<span class="label label-default">${ingredientsVO.ingdt_Name}</span>
					</c:forEach>
					<p>${postVO.post_Cont}</p>
					<p class="lnr lnr-eye " style="text-align: right;">${postVO.post_Views}</p>
				</div>
				<!-- /*單一餐點貼文 -->

				<!-- Load Facebook SDK for JavaScript -->
				<div id="fb-root"></div>
				<script>
					(function(d, s, id) {
						var js, fjs = d.getElementsByTagName(s)[0];
						if (d.getElementById(id))
							return;
						js = d.createElement(s);
						js.id = id;
						js.src = "https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v3.0";
						fjs.parentNode.insertBefore(js, fjs);
					}(document, 'script', 'facebook-jssdk'));
				</script>

				<!-- Your share button code -->
				<div class="fb-share-button"
					data-href="<%=request.getContextPath()%>/post/listOnepost.jsp"
					data-layout="button_count"></div>

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
									value="${postVO.post_No}" /> <input type="hidden" id="mem_No"
									name="mem_No" value="M000001" /> <input type="hidden"
									name="action" value="insert">
								<button type="submit"
									class="btn btn-primary btn-sm onclick=addMsg()">送出</button>
							</form>
						</div>
					</div>
				</form>
				<!-- /*新增留言區塊 -->
				<script type="text/javascript">
					
				</script>

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
				<c:set var="listReplybypost_No" value="${postVO.post_No}" />
				<%
					ReplyService rplySvc = new ReplyService();
					String listReplybypost_No = postVO.getPost_No();
					System.out.println("listReplybypost_No" + listReplybypost_No);
					List<ReplyVO> rplyList = rplySvc.getByPostNo(listReplybypost_No);
					pageContext.setAttribute("rplyList", rplyList);
				%>
				<div class="content">
					<span class="right floated">${rplyList.size()}個留言</span>
				</div>


				<!-- Comment  comments -->
				<c:forEach var="replyVO" items="${rplyList}">
					<div class="media mb-4 test">
						<img class="d-flex mr-3 rounded-circle"
							src="<%=request.getContextPath()%>/front_end/member/member.do?mem_No=${replyVO.mem_No}" style="display:${(memVO.mem_Name == null )? 'none': ''};height:35px;width:35px;border-radius:50%;">
						<div class="my-0 ">
							<h5 class="my-0 ">${memSvc.getOne_Member(replyVO.mem_No).mem_Name}</h5>
							<p id="rply_Cont">${replyVO.rply_Cont}</p>
							<div class="mt-0 mb-2 mr-2">
								<fmt:formatDate value="${replyVO.rply_Time}"
									pattern="yyyy年MM月dd日 HH:mm:ss" />
								<button type="button"
									class="btn btn-sm btn-outline-danger mx-3 mt-0"
									data-toggle="modal" data-target="#reportModal">檢舉</button>
							</div>
						</div>
					</div>
				</c:forEach>


			</div>
		</div>
	</div>


<!-- 輸入檢舉原因的Modal -->
<div class="modal fade" id="reportModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLongTitle">請告訴我們原因</h5>
			</div>
			<form>
			<div class="modal-body">
				<div class="custom-control custom-radio">
					<input type="radio" id="customRadio1" name="rply_Rsm"
						class="custom-control-input"> <label
						class="custom-control-label" for="customRadio1">留言內容不實</label>
				</div>
				<div class="custom-control custom-radio">
					<input type="radio" id="customRadio2" name="rply_Rsm"
						class="custom-control-input"> <label
						class="custom-control-label" for="customRadio2">廣告內容</label>
				</div>
				<div class="custom-control custom-radio">
					<input type="radio" id="customRadio3" name="rply_Rsm"
						class="custom-control-input"> <label
						class="custom-control-label" for="customRadio3">惡意留言</label>
				</div>
				<div class="custom-control custom-radio">
					<input type="radio" id="customRadio4" name="rply_Rsm"
						class="custom-control-input"> <label
						class="custom-control-label" for="customRadio4">就是想檢舉</label>
				</div>
				</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-dismiss="modal">不檢舉了</button>
				<input type="hidden" name="mem_No" value="M000001"/>
				<input type="hidden" name="rply_No" value="${replyVO.rply_No}"/>
				<button type="button" class="btn btn-primary" name="action" >送出</button>
			</div>
				</form>
			</div>

		</div>
	</div>



</body>

</html>
