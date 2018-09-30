<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Mobile Specific Meta -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Site Title -->
<title>Homepage</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
<!--HomepageCSS============================================= -->
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/animate.min.css">
<link rel="stylesheet" href="css/bootstrap.css">


<!--JS BS4-->

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
<style>
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

.imgicon {
	margin-top: 10px;
}

#header.header-scrolled {
	transition: all 0.5s;
	background: black;
	border: none
}
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
}
.p-self{
padding:11rem!important;

}
.coupon{
position: fixed;
  top:500px;
    width: 30%;
    margin: 0 auto;
    max-width: 200px;
    background-color:rgba(0,0,0,0.4)
}


   
</style>
</head>
<body>
	<jsp:include page="/front_end/header.jsp" flush="true" />
	<!-- #header -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.0.2/anime.min.js"></script>
	<!-- 標題區 -->
	<div class="text-center p-self"
		style="background-image: url('img/index/ramen-animate.gif');">
		<div class="container  h-100">
			<div class="row mx-2 my-5 py-5">
				<p class="px-2">WIDE OPTIONS OF CHOICE</p>
				<div class="col-md-12 text-md-left text-center">
					<img src="img/index/Text_horizontal_sm.png" style="width: 300px"
						alt="">
					<p class="lead ml13">竹風堂拉麵 頂級食材 客製化搭配</p>
					<div class="row  test">
						<div class="col-md-5 col-6 test">
							<a class="btn btn-danger btn-lg" href="#">View Our Menu</a>
						</div>
						<div class="col-md-5 col-6"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End banner Area -->
	
	<!-- 廁旁優惠卷 -->
	<div class="coupon filter-dark" >
          <div id="carousel" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
            <!-- 第一個 -->
              <div class="carousel-item active">
              
                <img class="d-block img-fluid px-2 py-2" src="<%=request.getContextPath()%>/res/img/coupon01.jpg" style="height:150px;width:200px";>
                <div class="carousel-caption">
                  <h5 style="color:black"> <span class="promo">Test</span></h5>
                </div>
                <p class="mb-0" style="color:white" Align="Center">test</p>
              </div>
              
              <!-- 第二個 -->
             <div class="carousel-item ">
              
                <img class="d-block img-fluid px-2 py-2" src="<%=request.getContextPath()%>/res/img/coupon01.jpg" style="height:150px;width:200px";>
                <div class="carousel-caption">
                  <h5 style="color:black"> <span class="promo">BOH232</span></h5>
                </div>
                <p class="lead mb-0" style="color:white" Align="Center">優惠卷</p>
              </div>
              
            </div>
            <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
              <span class="carousel-control-next-icon"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
          <div class="row">
            <div class="col-md-12"></div>
          </div>
        </div>
	
	
	
	
	
	
	
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


	<script src="js/vendor/jquery-2.2.4.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/easing.min.js"></script>
	<script src="js/hoverIntent.js"></script>
	<script src="js/superfish.min.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/isotope.pkgd.min.js"></script>
	<script src="js/main.js"></script>




</body>
</html>
