<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.member.model.*"%>
<% MemberVO memVO = (MemberVO) session.getAttribute("memVO"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>竹風堂</title>
<meta charset="UTF-8">
<!-- Mobile Specific Meta -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!-- linearicons CSS -->
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
<style type="text/css">
/*header*/
.dropdown-menu li:hover .sub-menu {
	visibility: visible;
}

.dropdown:hover .dropdown-menu {
	display: block;
}

.nav-link {
	font-size: 20px;
}

.lnr-user {
	color: #FFF;
	font-size: 20px;
}

.lnr-alarm {
	color: #FFF;
	font-size: 23px;
	margin-right: 10px;
}

.lnr-cart {
	color: #FFF;
	font-size: 23px;
	margin-right: 10px;
}

/* .imgicon { */
/* 	margin-top: 10px; */
/* } */


.bg-black {
	background-color: #000000;
}

.nav-link {
	font-size: 20px;
}

html {
	height: 100%;
	font-family: 'PT Sans', Microsoft JhengHei, sans-serif;
}

body {
	font-family: Montserrat, Arial, "微軟正黑體", "Microsoft JhengHei" !important;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}

</style>
<!--Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js "></script>
<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" -->
<!-- 	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" -->
<!-- 	crossorigin="anonymous"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
</head>
<body>
	<section>
		<header id="header-whole">
			<div class="header-top" id="header">
				<nav
					class="navbar-self navbar navbar-expand-lg navbar-dark bg-black fixed-top">
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarNavDropdown">
						<ul class="navbar-nav nav-fill mx-auto">
							<li>
								<a class="index_page" href="<%=request.getContextPath()%>/front_end/index.jsp">
									<img src="/CA103G4/front_end/img/LOGO-04.png" style="width:50px;"></a>
							</li>
							
							<li class="nav-item ">
								<a class="nav-link" href="<%=request.getContextPath()%>/front_end/branchInfo/branch_mang.jsp">分店資訊
									<span class="sr-only">(current)</span></a>
							</li>
<!-- 							<li class="nav-item"><a class="nav-link" href="#">餐點介紹</a></li> -->
							<li class="nav-item"><a class="nav-link" href="#"></a></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> 餐點 </a>
								<div class="dropdown-menu"
									aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/menu/listAllMenu4.jsp">經典餐點</a> 
									<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/custommeals/addCustommeals2.jsp">客製化點餐</a>
								</div></li>
							<li class="nav-item"><a class="nav-link" href="#">線上預約訂位</a>
							</li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/front_end/post/listAllpost.jsp">餐點分享</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/front_end/activity/listAllActivity.jsp"">活動快訊</a></li>
							<li class="nav-item"><a class="nav-link" href="#">線上客服</a></li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li class="nav-link imgicon"><a href="<%=request.getContextPath()%>/front_end/shoppingCart/Cart.jsp"><span
									class="lnr lnr-cart"></span></a></li>
							<li class="nav-link imgicon"><a href="gallery.html"></a><span
								class="lnr lnr-alarm"></span></li>
							<li class="nav-item dropdown imgicon">
							
							<!-- 無登入狀態 點選人頭示意圖將導入Login.jsp -->

								<a
								class="nav-link dropdown-toggle" href="${(memVO.mem_Name == null )? '/CA103G4/front_end/member/login.jsp' : '#'}"
								id="navbarDropdownMenuLink" role="button" ${(memVO.mem_Name == null )? '' : 'data-toggle=\"dropdown\"'}
								aria-haspopup="true" aria-expanded="false" style="padding-top:${(memVO.mem_Name == null )? '': '3px'} "> 
									${(memVO.mem_Name == null )? 'Log in':memVO.mem_Name}
								
							<!--有登入狀態圖示改為會員照片小圖 -->
								<span class="lnr lnr-user" style="display: ${(memVO.mem_Name == null )? '': 'none'} "></span>
								<span class="lnr nav-link" style="display:inline;">
									<img class="nav-item " src="<%=request.getContextPath()%>/front_end/member/member.do?mem_No=${(memVO.mem_No == null)?' ':memVO.mem_No}" style="display:${(memVO.mem_Name == null )? 'none': ''};height:35px;width:35px;border-radius:50%;">
								</span>
								</a>
								
							<!-- 有登入狀態才會有這裏的會員相關資料 -->
								<div class="dropdown-menu"
									aria-labelledby="navbarDropdownMenuLink" style="visibility: ${(memVO.mem_Name == null )? 'hidden': ''} ">
							<!-- 以下可以自己新增相關會員附屬功能 -->
									<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/member/memberinfo.jsp">會員基本資料</a>
									<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/storedrecord/addNewtransaction.jsp">儲值點數</a>
									<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/storedrecord/transaction_query.jsp">儲值紀錄</a> 
									<a class="dropdown-item" href="<%=request.getContextPath()%>/protected_front/orderform/listOneOrderformByMemNo3.jsp">訂單查詢</a> 
									<a class="dropdown-item" href="<%=request.getContextPath()%>/protected_front/post/listPostByMember.jsp">我的貼文</a>
									<a class="dropdown-item" href="<%=request.getContextPath()%>/front_end/member/logout.do" >登出</a>
								</div>
							</li>
					
						</ul>
					</div>
				</nav>

			</div>
		</header>
		
	
	</section>
</body>
</html>