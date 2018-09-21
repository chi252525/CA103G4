<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
/*navbar*/
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
	margin-right: 15px;
}

.imgicon {
	margin-top: 5px;
}

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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
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
							<li class="nav-item active"><a class="nav-link" href="#">分店資訊
									<span class="sr-only">(current)</span>
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#">餐點介紹</a></li>
							<li class="nav-item"><a class="nav-link" href="#"></a></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> 客製化拉麵 </a>
								<div class="dropdown-menu"
									aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="#">經典餐點</a> <a
										class="dropdown-item" href="#">客製化餐點</a>
								</div></li>
							<li class="nav-item"><a class="nav-link" href="#">線上預約訂位</a>
							</li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/front_end/post/listAllpost.jsp">餐點分享</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="#">活動快訊</a></li>
							<li class="nav-item"><a class="nav-link" href="#">線上客服</a></li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li class="nav-link imgicon"><a href="..."><span
									class="lnr lnr-cart"></span></a></li>
							<li class="nav-link imgicon"><a href="gallery.html"></a><span
								class="lnr lnr-alarm"></span></li>
							<li class="nav-item dropdown imgicon"><a
								class="nav-link dropdown-toggle" href="#"
								id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"><span
									class="lnr lnr-user"></span> </a>
								<div class="dropdown-menu"
									aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="#">儲值管理</a> <a
										class="dropdown-item" href="#">訂單查詢</a> <a
										class="dropdown-item" href="#">會員中心</a>
								</div></li>

						</ul>
					</div>
				</nav>

			</div>
		</header>

		<!--background image-->
		<img
			src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg"
			width="100%" height="" alt="">
	</section>
</body>
</html>