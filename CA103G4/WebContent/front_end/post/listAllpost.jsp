<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>
<%@ page import="com.custommeals.model.*"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="cusmealSvc" scope="page"
	class="com.custommeals.model.CustommealsService" />
	
	
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
<title>Post</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!-- linearicons CSS -->
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
<!-- star 評分 套件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front_end/post/css/starability-all.min.css"/>

<!--for star rating============================================= -->
<link
	href="<%=request.getContextPath()%>/front_end/post/css/starability.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/front_end/post/js/star-rating.js"
	type="text/javascript"></script>
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
	font-family: 'PT Sans',Microsoft JhengHei, sans-serif;
	font-size :20px;
}

body {
    font-family: Montserrat,Arial,"微軟正黑體","Microsoft JhengHei" !important;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
	
}

/*overflow-hidden*/
div.overflow-hidden {
	white-space: nowrap;
	width: 300px;
	overflow: hidden;
	text-overflow: ellipsis;
	margin-bottom: 20px;
}

.viewdetailbtn {
	margin-bottom: 20px;
}

.container-margin {
	margin-top: 20px;
	margin-bottom: 20px;
	
}

.box {
	background-color: rgba(252, 252, 252,0.8);
	border: solid 0.5px rgba(119, 97, 75,0.2);
	border-radius: 8px;
	opacity:0.8;
}

.post-time {
	text-align: right;
}

.btn-pos{
margin-left:200px;
}
.navbar-self{
margin-bottom:0px;

}
.test{
border:solid 1px;}
</style>
</head>
<body background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "
	width="100%" >
<!--your html start==================================================================================-->
<jsp:include page="/front_end/header.jsp" flush="true" />
	
	
	<div class="container container-margin" >
	 <div class="row justify-content-between ">
	 
    <div class="col-6 list-unstyled mb-0 ">
     <%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<div class="modal fade" id="errorModal_Ning">
			    <div class="modal-dialog modal-sm" role="dialog">
			      <div class="modal-content">
			        <div class="modal-header">
			          <i class="fas fa-exclamation-triangle"></i>
			          <span class="modal-title"><h4>&nbsp;注意：</h4></span>
			        </div>
			        <div class="modal-body">
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red" type="square">${message}</li>
						</c:forEach>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
			        </div>
			      </div>
			    </div>
			 </div>
		</c:if>
		<%-- 錯誤表列 --%>
	
	
		
     <ul class="list-unstyled mb-0">
     <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/postServlet.do" >
      <input type="month" name="bdaymonth" value="date" >
       <input type="hidden" name="action" value="getYear_and_Month_Post">
       <input type="submit" class="btn btn-danger" value="送出">
     </FORM>
     </li>
     </ul>
    </div>
    <div class="col-6">
   
   <a href="<%=request.getContextPath() %>/front_end/post/addPost.jsp"  class="btn btn-info ml-auto" style="float: right;">我要分享</a>
    </div>
  </div>
</div>

	<div class="container container-margin">
		<%@ include file="pages/page1.file"%>
		<!-- 共幾篇貼文 -->
		 <c:if test="${not empty list}">
				共 <span>${list.size()}</span> 篇
				</c:if>
			<!-- /*共幾篇貼文 -->
			
		<div class="row">
					<c:forEach var="postVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<!-- card start -->
				<div class="col-md-4 col-sm-12 ">
					<div class="item box m-1 p-2">
						<p class="font-italic mx-auto post-time">
							<fmt:formatDate value="${postVO.post_Time}"
								pattern="MM月dd日 HH:mm" />
						</p>
						<img class="img-fluid"
							src="<%=request.getContextPath()%>/post/postshowimage.do?post_No=${postVO.post_No}"
							width="350" height="50%">
						<h4 class="mt-2">${cusmealSvc.getOneCustommeals(postVO.custom_No).custom_Name}</h4>
								 <p class="starability-result" data-rating="${postVO.post_Eva}"></p>
						<div class="overflow-hidden">${postVO.post_Cont}</div>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/post/postServlet.do"
							style="margin-bottom: 0px;">
							<input type="hidden" name="post_No" value="${postVO.post_No}">
							<input type="hidden" name="mem_No" value="${postVO.mem_No}" />
							<!-- 查單一貼文 -->
							<input type="hidden" name="action" value="getOne_For_Display">
							<!-- 同時查單一貼文的所有留言 -->
							<input type="hidden" name="action" value="listReplybyPostNo">
							<button type="submit" value="viewdetail"
								class="btn btn-danger btn-sm viewdetailbtn">看更多
								&raquo;</button>
						</FORM>
					</div>
				</div>
			</c:forEach>
			<!-- /*card  -->
		</div>	<%@ include file="pages/page2.file"%>
	</div>

	<jsp:include page="/front_end/footer.jsp" flush="true" />

</body>

</html>