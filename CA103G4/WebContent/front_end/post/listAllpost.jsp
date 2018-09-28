<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.custommeals.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="cusmealSvc1" scope="page" class="com.custommeals.model.CustommealsService" />
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<% 
	PostService postSvc = new PostService();
	List<PostVO> list = postSvc.getAll();
	
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
		<img src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg"
			width="100%" height="" alt="">

	<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


	<div class="container my-5">
		<div class="container">
			<div class="row">
				<!-- 查詢BAR -->
				<div class="col-md-6">
					<div class="card">
						<div class="card-header">查詢貼文</div>
						<div class="card-body">
							<form METHOD="post"
								ACTION="<%=request.getContextPath()%>/post/postServlet.do">
								<div class="input-group my-1">
									<input type="month" name="bdaymonth" value="date"
										class="form-control mr-3 my-1 w-25 " size="10"
										id="inlineFormInputGroup" required="required">
								</div>
								<input type="hidden" name="action"
									value="getYear_and_Month_Post">
								<button type="submit" class="btn btn-danger">送出</button>
							</form>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/post/postServlet.do">
								<jsp:useBean id="postSvc2" scope="page"
									class="com.post.model.PostService" />
								<b><font color=orange>選擇貼文:</font></b> <select size="1"
									name="post_No">
									<c:forEach var="postVO" items="${postSvc2.all}">
										<option value="${postVO.post_No}">${cusmealSvc1.getOneCustommeals(postVO.custom_No).custom_Name}
									</c:forEach>
								</select> <button type="submit" class="btn btn-danger">送出</button><input type="hidden"
									name="action" value="getOne_For_Display">
							</FORM>
						</div>
					</div>
				</div>
				<!-- */查詢BAR -->
				
				<!-- 查詢BAR -->
				<div class="col-md-6">
					<div class="card">
						<div class="card-header mb-5">關鍵字搜尋</div>
						
							<form METHOD="post"
								ACTION="<%=request.getContextPath()%>/post/postServlet.do" class="card card-sm">
                                <div class="card-body row no-gutters align-items-center mb-4">
                                    <!--end of col-->
                                    <div class="col">
                                        <input class="form-control form-control-lg form-control-borderless" name="keyword" type="search" placeholder="搜尋關鍵字">
                                    </div>
                                    <!--end of col-->
                                    <div class="col-auto">
                                        <button class="btn btn-lg btn-success" type="submit" 
                                        name="action" value="keyword">搜尋</button>
                                    </div>
                                    <!--end of col-->
                                </div>
                            </form>
							
						
					</div>
				</div>
				<!-- */查詢BAR -->
				
				<!-- 分享按鈕 -->
				<div class="col-md-6">
					<a class="btn btn-info btn-sm px-3 btn-block mt-2"
						href="<%=request.getContextPath()%>/front_end/post/addPost.jsp">我要分享</a>
				</div>
				<!-- /*分享按鈕 -->
				<div class="col-md-6">
					<a class="btn btn-info btn-sm px-3 btn-block mt-2"
						href="<%=request.getContextPath()%>/front_end/post/listPostByMember.jsp">我的貼文</a>
				</div>

				<div class="btn-group float-right my-3">
					<button class="btn btn-secondary btn-sm dropdown-toggle"
						type="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">排序</button>
					<div class="dropdown-menu"><a
							href="<%=request.getContextPath()%>/post/postServlet.do?action=orderbyViews"
							>依點閱人氣</a> </div>
				</div>

			</div>
			
			<div class="row">
				<div class="col-md-12 mt-3">
					<!-- 共幾篇貼文 -->
					<c:if test="${not empty list}">
				共 <span>${list.size()}</span> 篇
				</c:if><%@ include file="pages/page1.file"%>
					<!-- /*共幾篇貼文 -->
				</div>
				<c:forEach var="postVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<div class="col-md-4 px-2 py-4">
						<div class="card">
							<img class="card-img img-fluid"
								src="<%=request.getContextPath()%>/post/postshowimage.do?post_No=${postVO.post_No}"
								alt="Card image">
							<div
								class="card-img-overlay d-flex justify-content-center align-items-center">
								<h2 class="display-5" class="text-primary ">
									<b>${cusmealSvc1.getOneCustommeals(postVO.custom_No).custom_Name}</b>
								</h2>
							</div>
						</div>
						
						<div class="card px-2">
							<h5 class="card-title text-dark my-2 px-2"></h5>
							推薦度<p class="starability-result" data-rating="${postVO.post_Eva}"></p>
							
							<p class="card-text text-dark px-2 font-italic ">
								<fmt:formatDate value="${postVO.post_Time}"
									pattern="MM月dd日 HH:mm" />
							</p>
							<p style="text-align: right;">
							by ${memSvc.getOne_Member(postVO.mem_No).mem_Name}</p>
							<p class="lnr lnr-eye " style="text-align: right;">${postVO.post_Views}</p>
							<!-- 查看單一貼文action -->
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/post/postServlet.do"
								style="margin-bottom: 0px;">
								<input type="hidden" name="post_No" value="${postVO.post_No}"/>
								<input type="hidden" name="mem_No" value="${postVO.mem_No}" />
								<!-- 查單一貼文 -->
								<input type="hidden" name="action" value="getOne_For_Display"/>
								<!-- 同時查單一貼文的所有留言 -->
								<input type="hidden" name="action" value="listReplybyPostNo"/>
								<input type="hidden" name="action" value="addviews">
								<button type="submit" 
									class="btn btn-info btn-sm btn-block mb-2">看更多 &raquo;</button>
							</FORM>

						</div>
					</div>
				</c:forEach>
			</div>
			<%@ include file="pages/page2.file"%>
		</div>
	</div>


	<jsp:include page="/front_end/footer.jsp" flush="true" />

</body>

</html>