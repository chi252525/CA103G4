<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>


<%
	MenuService menuSvc = new MenuService();
	List<MenuVO> list = menuSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="menuDAO" scope="page" class="com.menu.model.MenuDAO" />



<html>
<head>
<title>listAllMenu2.jsp</title>






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

<style>
/* * { */
/*   font size: 62.5%; */
/*   box-sizing: border-box; */
/*   outline: none; */
/* } */
/* input { */
/*   display:block; */
/*   padding: 10px; */
/*   position: relative; */
/*   border: 1px solid black; */
/*   width: 80px; */
/* } */
/* input:focus { */
/*   outline-color: springgreen; */
/* } */
/* input[type=number]::-webkit-inner-spin-button {  */
/*   position: absolute; */
/*   top: 0; */
/*   bottom: 0; */
/*   right: 0; */
/*   padding: 10px; */
/*   opacity: 1; */
/*   cursor: pointer; */
/* } */

/* input[type=number]::-webkit-inner-spin-button:hover {  */

/* } */
/* input[type=number]::-webkit-inner-spin-button:before { */
/*   font-family: "FontAwesome"; */
/*   font-size: 1.4rem; */
/*   content: '\f106'; */
/*   position: absolute; */
/*   top: 0; */
/*   bottom: 50%; */
/*   right: 0; */
/*   left: 0; */
/*   border-left: 1px solid black; */
/*   border-bottom: 1px solid black; */
/*   display: flex; */
/*   justify-content: center; */
/*   align-items: center; */
/*   background: white; */
/*   cursor: pointer; */
/* } */
/* input[type=number]::-webkit-inner-spin-button:after { */
/*   font-family: "FontAwesome"; */
/*   font-size: 1.4rem; */
/*   content: '\f107'; */
/*   position: absolute; */
/*   top: 50%; */
/*   bottom: 0; */
/*   right: 0; */
/*   left: 0; */
/*   border-left: 1px solid black; */
/*   display: flex; */
/*   justify-content: center; */
/*   align-items: center; */
/*   background: white; */
/*   cursor: pointer; */
/* } */
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
	<img
		src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg"
		width="100%" height="" alt="banner">




	<!-- 		<p>燈箱點這裡  -->
	<!-- 		    <a href = "javascript:void(0)"  -->
	<%-- 		    onclick = "document.getElementById('light').style.display='block'; --%>
	<%-- <!-- 					   document.getElementById('fade').style.display='block'">點這裡</a> --> --%>
	<!-- 		</p> -->

	<!-- 		<div id="light" class="white_content">  -->
	<%-- 			<jsp:include page="listOneMenu2.jsp" /> --%>
	<!-- 		    <a href = "javascript:void(0)"  -->
	<%-- 		    onclick = "document.getElementById('light').style.display='none'; --%>
	<%-- <!-- 		    		   document.getElementById('fade').style.display='none'">關閉</a> --> --%>
	<!--         </div> -->

	<!-- 		<div id="fade" class="black_overlay"> -->
	<!-- 		</div> -->






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

	<!-- <input type="button" value="訊息顯示" onclick="alert('test!!')"> -->
	<div class="well">
		<label class="input-stepper"> <span id="label-text">Things</span>
			<div>
				<a href="#0" role="button" id="step-decrement"
					class="btn btn-default" aria-label="remove 1"
					aria-labelledby="step-decrement label-text"> <i
					aria-hidden="true" class="icon icon-minus"></i>
				</a> <input disabled type="text" id="spinner" name="quantity"
					class="text-strong form-control input-number text-center" value="1"
					aria-live="polite" tabindex="-1" aria-labelledby="label-text">
				<a href="#0" role="button" id="step-increment"
					class="btn btn-default" aria-label="add 1"
					aria-labelledby="step-increment label-text"> <i
					aria-hidden="true" class="icon icon-plus"></i>
				</a>
			</div>
		</label>
	</div>
	</div>




	<div class="h-100 py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12 mt-3">
					<%@ include file="page1.file"%><br>

				</div>
				<c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<br>

					<div class="col-md-3">

						<div class="card"
							style="background-color: rgba(255, 255, 255, 0.45); margin-bottom: 20px;">
							<a
								href="menu.do?action=getOne_For_Display_Member&menu_No=${menuVO.menu_No}">
								<img class="card-img-top"
								src="<%=request.getContextPath()%>/menu/menushowimage.do?menu_No=${menuVO.getMenu_No()}"
								alt="Card image cap" style="margin-top: 20px;">
							</a>
							<div class="card-body">
								<h5 class="card-title">${menuSvc.getOneMenu(menuVO.menu_No).menu_Id}
									${menuVO.menu_Id} <br>
								</h5>
								<h5 class="card-title">$${menuSvc.getOneMenu(menuVO.menu_No).menu_Price}
									${menuVO.menu_Price}</h5>
								<p class="card-text" style="height: 72px;">${menuSvc.getOneMenu(menuVO.menu_No).menu_Intro}
									${menuVO.menu_Intro}</p>
								<!-- 				            	  數量 <input type="tel" name="quantity" value=1 style="width: 60px"> -->

								<form id="menuform" name="shoppingForm" class="shoppingForm" action="<%=request.getContextPath()%>/front_end/shoppingCart/ShoppingServlet.do" method="POST">
									    數量 <input type="number" name="quantity" value=1
										style="size: 3rem;">
										 <input type="button" value="訂餐" class="btn btn-primary submit"
											style="background-color: #dc3545; border-color: #dc3545; margin-left: 60px;">
										 <input type="hidden" name="menuno" value="${menuVO.menu_No}">
                						 <input type="hidden" name="menuid" value="${menuVO.menu_Id}">
                						 <input type="hidden" name="type" value="${menuVO.menu_Type}">
                						 <input type="hidden" name="price" value="${menuVO.menu_Price}">
                						 <input type="hidden" name="status" value="${menuVO.menu_Status}">
               							 <input type="hidden" name="action" value="ADD">
               					</form>
							</div>
						</div>

					</div>
				</c:forEach>

				<div class="col-md-12 mt-3">
					<%@ include file="page2.file"%>
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