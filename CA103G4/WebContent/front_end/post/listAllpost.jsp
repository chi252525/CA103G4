<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.custommeals.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="cusmealSvc1" scope="page"
	class="com.custommeals.model.CustommealsService" />
<jsp:useBean id="memSvc" scope="page"
	class="com.member.model.MemberService" />
<jsp:useBean id="postSvc2" scope="page"
	class="com.post.model.PostService" />
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
<!-- Chart js -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>

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
</style>
</head>
<body
	background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "
	width="100%">
	<!--your html start==================================================================================-->
	<jsp:include page="/front_end/header.jsp" flush="true" />
	<!--background image-->
	<img
		src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg"
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

		<div class="row">
			<!-- 查詢BAR開始 -->

			<div class=" d-flex mx-2">
				<div class="card"
					style="background-color: rgba(255, 255, 255, 0.45)">
					<div class="card-header">查詢貼文</div>
					<div class="card-body py-1 px-2">

						<div class="d-flex justify-content-end">
							<div class="p-2 "></div>


						</div>
						<div class="d-flex  ">
							<div class="p-1">
								<form METHOD="post" class="form-inline"
									ACTION="<%=request.getContextPath()%>/post/postServlet.do">
									<input type="month" name="bdaymonth" value="date"
										class="form-control " required="required"> <input
										type="hidden" name="action" value="getYear_and_Month_Post">
									<button type="submit" class="btn btn-default">送出</button>
								</form>
							</div>
							<div class="p-1">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/post/postServlet.do"
									class="form-inline">
									<div class="form-group">
										<select size="1" class="form-control" name="post_No">
											<c:forEach var="postVO" items="${postSvc2.all}">
												<option value="${postVO.post_No}">${cusmealSvc1.getOneCustommeals(postVO.custom_No).custom_Name}
											</c:forEach>
										</select>
									</div>
									<div class="input-group-append">
										<button type="submit" class="btn btn-default">送出</button>
										<input type="hidden" name="action" value="getOne_For_Display">
									</div>
								</FORM>
							</div>
						</div>
						<div class="d-flex  justify-content-start">
							<div class="p-1">
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/post/postServlet.do"
									class="form-inline ">
									<div class="col-xs-2 input-group">
										<input class="form-control" name="keyword" type="search"
											placeholder="搜尋關鍵字">
									</div>
									<div class="input-group-append">
										<button class="btn btn-default" type="submit" name="action"
											value="keyword">搜尋</button>
									</div>

								</form>

							</div>
						</div>
						<div class="float-right ">
							<a class="btn btn-info btn-sm"
								href="<%=request.getContextPath()%>/front_end/post/addPost.jsp">我要分享</a>
							
							
							<a class="btn btn-info btn-sm"
								href="<%=request.getContextPath()%>/post/postServlet.do?action=orderbyViews">依點閱人氣</a>


						</div>
					</div>
				</div>



			</div>


			<div class="d-flex p-2">
				<canvas id="myChart"
					style="background-color: rgba(255, 255, 255, 0.45); width: 420px; height: 250px"></canvas>
			</div>
		</div>


		<!-- */查詢BAR -->

		<div class="row">
			<div class="col-md-12 mt-1">
				<!-- 共幾篇貼文 -->
				<c:if test="${not empty list}">
				共 <span>${list.size()}</span> 篇
				</c:if>

				<%@ include file="pages/page1.file"%>
				<!-- /*共幾篇貼文 -->
			</div>
			<c:forEach var="postVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<div class="col-md-4 col-4 px-2 py-4 ">
					<div class="card">
						<img class="card-img img-fluid"
							src="<%=request.getContextPath()%>/post/postshowimage.do?post_No=${postVO.post_No}"
							alt="Card image" style="width:400px;height:300px;">
						<div
							class="card-img-overlay d-flex justify-content-center align-items-center">
							<h2 class="display-5" class="text-primary ">
								<b>${cusmealSvc.getOneCustommeals(postVO.custom_No).custom_Name}</b>
							</h2>
						</div>
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
							<button type="submit" class="btn btn-info btn-sm btn-block my-2">看更多
								&raquo;</button>
						</FORM>

					</div>
				</div>
			</c:forEach>
			<div class="col-12">
				<%@ include file="pages/page2.file"%></div>
		</div>

		<div class="col-12">
			<%
	PostService postSvc1 = new PostService();
	Map<Integer, Integer> map = postSvc.getCountByEva();
	pageContext.setAttribute("map", map);
%>


			<script>
var ctx = document.getElementById('myChart');
var myChart = new Chart(ctx, {
  type: 'bar',
  data: {
		
    labels: [ <c:forEach var="message" items="${map}">'${message.key}星',</c:forEach>  ],   
		 datasets: [{
      backgroundColor: [
        'rgba(255, 99, 132, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
         'rgba(255, 206, 86, 1)',
          'rgba(255, 206, 86, 1)'
      ],
      borderColor: [
        'rgba(255,255,255,1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
          'rgba(75, 192, 192, 1)',
          'rgba(75, 192, 192, 1)'
      ],
      borderWidth: 1,
      label: '竹風堂餐點評比',
      data: [ <c:forEach var="message" items="${map}"> '${message.value}',</c:forEach>]
   
 
    }]
  }
});

</script>



		</div>




	</div>
	<jsp:include page="/front_end/footer.jsp" flush="true" />

</body>

</html>