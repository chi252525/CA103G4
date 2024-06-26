<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>


<%
	MenuService menuSvc = new MenuService();
	List<MenuVO> list = menuSvc.getAll_front();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="menuDAO" scope="page" class="com.menu.model.MenuDAO" />



<html>
<head>
<title>經典餐點</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/res/img/icon.png" />






<style>
table#table-1 {
	background-color: rgba(255, 255, 255, 0.45);
	/*     border: 2px solid black; */
	border-radius: 15px;
	text-align: center;
}
table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}
h4 {
	color: white;
	display: inline;
}
</style>

<style>
table {
	max-width: 1280px;
	background-color: rgba(255, 255, 255, 0.45);
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 320px;
	font-family: 'Noto Sans TC', sans-serif;
	font-weight: 600;
	font-size: 20;
}
table, th, td {
	/*     border: 2px solid rgba(255, 255, 255, 0.8); */
	border-radius: 15px;
	text-align: left;
	font-family: 'Noto Sans TC', sans-serif;
	font-weight: 600;
}
th, td {
	padding: 5px;
	text-align: left;
	font-family: 'Noto Sans TC', sans-serif;
	font-weight: 600;
}
@import url(//fonts.googleapis.com/earlyaccess/notosanstc.css);
body {
	background-image: url("images/woodbackground3.png");
}
</style>







<style>
.black_overlay {
	display: none;
	/* 			position: absolute; */
	top: 0%;
	left: 0%;
	width: 100%;
	height: 1920px;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
	/*  			background-size:cover;    */
	/* 			min-height:1080px;  */
	/*  			background-repeat: no-repeat; */
	/*  			background-position:center;   */
	/*  			background-attachment:fixed;  */
	position: relative;
	margin-top: -200px;
}
.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 25%;
	width: 50%;
	height: 60%;
	padding: 16px;
	/* 			border: 16px solid orange; */
	border-radius: 20px;
	background-color: rgba(255, 255, 255, 0.7);
	z-index: 1002;
	/*  			overflow: auto; */
}
</style>









<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front_end/menu/css/theme.css" />
<!-- sweet alert2 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js"
	type="text/javascript"></script>





</head>
<body bgcolor='white'>




	<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>
	<img src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg" width="100%" height="" alt="banner">






	<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
	<!-- <table id="table-1"> -->
	<!-- 	<tr><td> -->
	<!-- 		 <h3>listAllMenu.jsp</h3> -->
	<!-- 		 <h4><a href="select_page.jsp">回首頁</a></h4> -->
	<!-- 	</td></tr> -->
	<!-- </table> -->

	<%-- 錯誤列表 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>






	<div class="h-100 py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12 mt-3">
					<%@ include file="page1.file"%><br>

				</div>
				<c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				
<%-- 				<c:if test="${menuVO.menu_Status == '1'}"> --%>

					<div class="col-md-4">

						<div class="card" style="background-color: rgba(255, 255, 255, 0.45); margin-bottom: 20px;">

								<img class="card-img-top"
								src="<%=request.getContextPath()%>/menu/menushowimage.do?menu_No=${menuVO.getMenu_No()}"
								alt="Card image cap" style="margin-top: 20px;">

							<div class="card-body">
								<h5 class="card-title">${menuSvc.getOneMenu(menuVO.menu_No).menu_Id} ${menuVO.menu_Id} <br> </h5>
								<h5 class="card-title">$${menuSvc.getOneMenu(menuVO.menu_No).menu_Price} ${menuVO.menu_Price}</h5>
								<p class="card-text" style="height: 72px;">${menuSvc.getOneMenu(menuVO.menu_No).menu_Intro}	${menuVO.menu_Intro}</p>

								<form id="menuform" name="shoppingForm" class="shoppingForm" action="<%=request.getContextPath()%>/protected_front/shoppingCart/ShoppingServlet.do" method="POST">
									<div class="row">
										<div style="width:100px; margin-left:20px; margin-top:5px;">
											數量 <input type="number" name="quantity" size="3" value=1 style="width: 40px">
										</div>
										<div style="width:120px;">
											<input type="button" value="加入購物車" class="btn btn-primary submit" style="background-color: #dc3545; border-color: #dc3545; margin-left: 0px;">
										</div>
										 
											<input type="hidden" name="menuno" value="${menuVO.menu_No}">
	                						<input type="hidden" name="menuid" value="${menuVO.menu_Id}">
	                						<input type="hidden" name="type" value="${menuVO.menu_Type}">
	                						<input type="hidden" name="price" value="${menuVO.menu_Price}">
	                						<input type="hidden" name="status" value="${menuVO.menu_Status}">
	               							<input type="hidden" name="action" value="ADD">
	               							<input type="hidden" name="requestURL" value="/front_end/menu/listAllMenu4.jsp">
               						</div>
               					</form>
							</div>
						</div>

					</div>
<%-- 				</c:if> --%>
				</c:forEach>

<!-- 				<div class="col-md-12 mt-3"> -->
<%-- 					<%@ include file="page2.file"%> --%>
<!-- 				</div> -->
				
				<div class="container">
					<div class="row">
						<div style="margin-left:480px;"></div>
						<div><%@ include file="page2.file" %></div>
					</div>
				</div>
			</div>
		</div>
	</div>




	<script>
		$(function() {
			$("#dialog").dialog({
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
					}
				}
			});
		});
		
		//Java完美操縱javaScript , 加入餐點進購物車
	<%for (int i = 0; i < 12; i++) {%>
		$(function() {
			$(".submit").eq(
	<%=i%>
		).click(function() {
				swal({
					title : "加入購物車",
					html : "成功",
					type : "success"
				}).then(function() {
					$(".shoppingForm").eq(
	<%=i%>
		).submit();
				});
			});
		});
	<%}%>
		
	</script>


	<script>
		
	</script>



	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>








</body>
</html>