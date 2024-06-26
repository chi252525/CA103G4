<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Mobile Specific Meta -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/res/img/icon.png" />
<!-- Site Title -->
<title>竹風堂</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
<!--JS BS4-->

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
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
<style>

/*banner*/
.banner-area {
	background: url(img/index/ramen-animate.gif) center center/cover;
	background-size: cover
}

@media ( max-width : 860px) {
	.banner-area {
		height: 860px !important
	}
}
/*animation text*/
.ml13 {
	font-size: 1.5em;
	text-transform: uppercase;
	font-weight: 600;
	
}

.ml13 .letter {
	display: inline-block;
	line-height: 0.5em;
	color:#fff;
}
.p-self{
padding:11rem!important;

}


   
</style>
</head>
<body style="overflow:hidden;">
	<jsp:include page="/front_end/header.jsp" flush="true" />
	<!-- #header -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.0.2/anime.min.js"></script>
	<!-- 標題區 -->
	<div class="text-center p-self"	style="background-image: url('img/index/ramen-animate.gif'); 
											background-size:cover; min-height:1080px; background-repeat: no-repeat; 
											background-position:center; background-attachment:fixed;">
		<div class="container  h-100">
			<div class="row mx-2 my-5 py-5">
				<p class="px-2">WIDE OPTIONS OF CHOICE</p>
				<div class="col-md-12 text-md-left text-center">
					<img src="img/index/Text_horizontal_sm.png" style="width: 300px"
						alt="">
					<p class="lead ml13">竹風堂拉麵 頂級食材 客製化搭配</p>
					<div class="row  test">
						<div class="col-md-5 col-6 test">
							<a class="btn btn-danger btn-lg" href="<%=request.getContextPath()%>/front_end/menu/listAllMenu4.jsp">立即訂餐</a>
						</div>
						<div class="col-md-5 col-6"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End banner Area -->
	
	
	
	
	
	
	
	
	<script>
            // Wrap every letter in a span
$('.ml13').each(function(){
  $(this).html($(this).text().replace(/([^\x00-\x80]|\w)/g, "<span class='letter'>$&</span>"));
});

anime.timeline({loop: true})
  .add({
    targets: '.ml13 .letter',
    opacity: [0,1],
    easing: "easeInOutQuad",
    duration: 2250,
    delay: function(el, i) {
      return 150 * (i+1)
    }
  }).add({
    targets: '.ml13',
    opacity: 0,
    duration: 1000,
    easing: "easeOutExpo",
    delay: 1000
  });</script>





</body>
</html>
