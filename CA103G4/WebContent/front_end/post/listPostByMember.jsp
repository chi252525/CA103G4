<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.custommeals.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="cusmealSvc1" scope="page"
	class="com.custommeals.model.CustommealsService" />
<jsp:useBean id="memSvc" scope="page"
	class="com.member.model.MemberService" />

<%
	PostService postSvc = new PostService();
	MemberVO memVO = (MemberVO) session.getAttribute("memVO");
	List<PostVO> list = postSvc.getMem_Post(memVO.getMem_No());
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<!-- Site Title -->
<title>所有貼文</title>
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

<!--for star rating============================================= -->
<link
	href="<%=request.getContextPath()%>/front_end/post/css/starability.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.js"></script>
<!--Bootstrap JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>

<style>
html {
	height: 100%;
	font-family: 'PT Sans', Microsoft JhengHei, sans-serif;
	font-size: 20px;
}

body {
	font-family: Montserrat, Arial, "微軟正黑體", "Microsoft JhengHei" !important;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}

.box {
	background-color: rgba(252, 252, 252, 0.8);
	border: solid 0.5px rgba(119, 97, 75, 0.2);
	border-radius: 8px;
	opacity: 0.8;
}

.navbar-self {
	margin-bottom: 0px;
}

.test {
	border: solid 1px;
}
</style>
</head>
<body
	background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "
	width="100%">
	<!--your html start==================================================================================-->
	<jsp:include page="/front_end/header.jsp" flush="true" />
	<!--background image-->
	<img
		src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg"
		width="100%" height="" alt="">

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div class="container my-5">
		<div class="container">
			<div class="row">

				<div class="col-md-6">

					<div class="display-4">${memVO.mem_Name}的貼文</div>
				</div>




			</div>
			<div class="row">
				<div class="col-md-12 mt-3">
					<!-- 共幾篇貼文 -->
					<c:if test="${not empty list}">
				查詢的結果共 <span>${list.size()}</span> 篇
				</c:if><%@ include file="pages/page1.file"%>
					<!-- /*共幾篇貼文 -->
				</div>
				<c:forEach var="postVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<div class="col-md-4 px-2 py-4 ">

						<div class="card"
							style="background-color: rgba(255, 255, 255, 0.45)">
							<img class=" img-responsive"
								src="<%=request.getContextPath()%>/post/postshowimage.do?post_No=${postVO.post_No}"
								style="height: 280px; width: 348px" alt="Card image">
						</div>

						<div class="card px-2"
							style="background-color: rgba(255, 255, 255, 0.45)">
							<h5 class="card-title text-dark my-2 px-2">${cusmealSvc1.getOneCustommeals(postVO.custom_No).custom_Name}</h5>
							<p class="starability-result" data-rating="${postVO.post_Eva}"></p>
							<p style="text-align: right;" class="my-0">
								by ${memSvc.getOne_Member(postVO.mem_No).mem_Name} <span
									class="lnr lnr-eye " style="text-align: right;">${postVO.post_Views}</span>
							</p>


							<!-- 查看單一貼文action -->
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/post/postServlet.do"
								style="margin-bottom: 0px;">
								<input type="hidden" name="post_No" value="${postVO.post_No}" />
								<input type="hidden" name="mem_No" value="${postVO.mem_No}" />
								<!-- 查單一貼文 -->
								<input type="hidden" name="action" value="getOne_For_Display" />
								<input type="hidden" name="action" value="addviews">
								<button type="submit" class="btn btn-info btn-sm btn-block ">看更多
									&raquo;</button>
							</FORM>
							<!-- 會員可以修改及刪除的按紐 -->
							<a
								href="<%=request.getContextPath()%>/post/postServlet.do?action=getOne_For_Update&post_No=${postVO.post_No}"
								class="btn lnr lnr-pencil btn-warning">修改</a>
							<FORM METHOD="post" id="deleteform"
								ACTION="<%=request.getContextPath()%>/post/postServlet.do"
								style="margin-bottom: 0px;">
								<input type="hidden" name="post_No" value="${postVO.post_No}">
								<input type="hidden" name="requestURL"
									value="<%=request.getServletPath()%>">
								<!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="whichPage" value="<%=whichPage%>">
								<!--送出當前是第幾頁給Controller-->
								<input type="hidden" name="action" value="delete">
								<button type="button" value="delete"
									class="btn  btn-sm btn-danger btn-block mb-1"
									onclick="deleteConfirm()">
									<i class="far fa-trash-alt"></i> 刪除
								</button>
							</FORM>




						</div>
					</div>


				</c:forEach>

			</div>
			<div class="col-12">
					<%@ include file="pages/page2.file"%></div>
		</div>
	</div>
	<script type="text/javascript">
		function deleteConfirm() {
			if (window.confirm('確定刪除嗎?')) {
				$('#deleteform').submit();
			}
		}
	</script>

	<jsp:include page="/front_end/footer.jsp" flush="true" />

</body>

</html>