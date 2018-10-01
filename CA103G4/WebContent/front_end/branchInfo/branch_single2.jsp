<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.branch.model.*"%>

<%
	//BranchVO brVO = (BranchVO) request.getAttribute("brVO");
	//List<BranchVO> list = (List<BranchVO>) request.getAttribute("list");
	//pageContext.setAttribute("list", list);
%>








<!DOCTYPE html>
<head>
<title>店鋪資訊 - 一風堂拉麵</title>
<meta name="description"
	content="一風堂拉麵來自日本福岡，為河原成美打造的拉麵冠軍品牌，保有博多拉麵濃厚豚骨湯底加細麵的特色，本著日式拉麵先驅之姿在紐約、新加坡、首爾、香港開設分店，以白丸元味、赤丸新味、一風堂辣肉味噌等人氣口味在世界各地方發揚日本拉麵文化。">
<meta name="keywords"
	content="日本拉麵店,福岡拉麵,濃厚拉麵,豚骨拉麵,日本拉麵文化,白丸元味,赤丸新味,辣肉味噌,細麵,豚骨,日式,拉麵,濃,細麵,福岡,博多,河原成美,拉麵冠軍">
<meta http-equiv="Content-Language" content="zh-tw">
<meta name="distribution" content="Taiwan" />
<meta name="geo.region" content="TW" />
<meta name="robots" content="all" />
<meta content="general" name="rating" />
<link rel="canonical"
	href="http://www.ippudo.com.tw/indexplates/profile_des.php?id=ippudo&lan=TW&store_id=7893" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
<link rel="apple-touch-icon-precomposed"
	href="http://data.indexbricks.com/logo/ippudo.png">
<link rel="stylesheet" href="css/jquery.mobile-1.3.2.min.css" />
<script src="java/jquery-1.9.1.min.js"></script>
<script src="java/jquery.mobile-1.3.2.min.js"></script>
<link rel="stylesheet" href="css/owl.carousel.css">
<link rel="stylesheet" href="css/owl.theme.css">
<link rel="stylesheet" href="css/new-index.css">
<script type="text/javascript">
	$(document).ready(function() {
		$(".trigger").click(function() {
			$(".panel").toggle("fast");
			$(this).toggleClass("active");
			return false;
		});
	});
</script>
<script src="java/jquery.ui.touch-punch.min.js"></script>
<script>
	$(document).ready(function() {
		$("#title_pic").css('width', $(window).width());
		var per = ($(window).height() - 133);
		$(".ui-content").css('min-height', per);
		$(window).resize(function() {
			$("#title_pic").css('width', $(window).width());
			var per = ($(window).height() - 133);
			$(".ui-content").css('min-height', per);
		});

		//首先將#back-to-top隱藏
		$("#back-to-top").hide();

		//當滾動條的位置處於距頂部大於100像素時，跳轉鏈接出現，否則消失
		$(function() {
			$(window).scroll(function() {
				if ($(window).scrollTop() > 100) {
					$("#back-to-top").fadeIn(1000);
				} else {
					$("#back-to-top").fadeOut(1000);
				}
			});

			//當點擊跳轉鏈接後，回到頁面頂部位置
			$("#back-to-top").click(function() {
				$('body,html').animate({
					scrollTop : 0
				}, 600);
				return false;
			});
		});
	});

	$(function() {
	});

	$("#mypanel").trigger("updatelayout");
</script>
<script>
	(function(i, s, o, g, r, a, m) {
		i['GoogleAnalyticsObject'] = r;
		i[r] = i[r] || function() {
			(i[r].q = i[r].q || []).push(arguments)
		}, i[r].l = 1 * new Date();
		a = s.createElement(o), m = s.getElementsByTagName(o)[0];
		a.async = 1;
		a.src = g;
		m.parentNode.insertBefore(a, m)
	})(window, document, 'script', '//www.google-analytics.com/analytics.js',
			'ga');

	ga('create', 'UA-63502427-5', 'auto');
	ga('send', 'pageview');
</script>

<link rel="icon" type="image/png"
	href="http://data.indexbricks.com/logo/ippudo.png" />
<style>
.ui-mobile .ui-header {
	border-color: rgb(15, 14, 14);
	background: rgb(15, 14, 14);
	color: rgb(255, 255, 255);
}
/*#image_menu{
		background: rgb(255,255,255);
		-o-mask-image: url(../indexplates/images/menu-icn_d_new@2x.png);
		-moz-mask-image: url(../indexplates/images/menu-icn_d_new@2x.png);
		-webkit-mask-image: url(../indexplates/images/menu-icn_d_new@2x.png);
		-webkit-mask-size: 90px 20px;
		-o-mask-size: 90px 20px;
		-moz-mask-size: 90px 20px;
		width: 90px;
		height: 20px;
	}
	#image_menu1{
		background: rgb(255,255,255);
		-o-mask-image: url(../indexplates/images/menu-icn_d@2x.png);
		-moz-mask-image: url(../indexplates/images/menu-icn_d@2x.png);
		-webkit-mask-image: url(../indexplates/images/menu-icn_d@2x.png);
		-o-mask-size: 25px 20px;
		-moz-mask-size: 25px 20px;
		-webkit-mask-size: 25px 20px;
		width: 25px;
		height: 20px;
	}*/
.menu_font {
	color: rgb(255, 255, 255);
	display: inline-block;
	position: absolute;
	top: 3px;
	left: 30px;
	font-size: 14px;
	width: 150px;
	font-weight: normal;
}

.navtoggle .icon-bar {
	background-color: rgb(255, 255, 255);
}

.glyphicon {
	color: rgb(255, 255, 255);
}
</style>
</head>
<body>

	<script>
		var locations = [];
	</script>
	<div data-role="page" id="profile_sub" class="profile_sub">
		<p id="back-to-top"
			style="position: fixed; bottom: 45px; right: 50px; z-index: 999999;">
			<a href="#top"><img src="images/top_icon.png" width="45px"
				id="gotop_img" class="hvr"></a>
		</p>
		<div data-role="panel" id="mypanel" data-position="left"
			data-display="reveal" data-theme="a">
			<ul data-role="listview" data-theme="a">

				<li id="Index_1"><a href="../indexplates/index.php?lan=TW"
					data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Home', 1);"><img
						src="http://data.indexbricks.com/image/function/Homepage@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">返回首頁</span></a></li>

				<li id="Intro_1"><a
					href="../indexplates/intro.php?id=ippudo&lan=TW" data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Intro', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA002_food/iA002_001a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">品牌介紹</span></a></li>
				<li id="Profile_1"><a
					href="../indexplates/profile.php?id=ippudo&lan=TW"
					data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Profile', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA002_food/iA002_003a@2x.png"
						class="ui-li-icon function-icon2" /><span class="type_name2">店鋪資訊</span></a></li>
				<li id="Photo_1"><a
					href="../indexplates/photo.php?id=ippudo&lan=TW" data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Photo', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA002_food/iA002_032a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">美味菜單</span></a></li>
				<li id="News_1"><a
					href="../indexplates/news.php?id=ippudo&lan=TW" data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'News', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA001_default/iA001_004a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">最新消息</span></a></li>
				<li id="Special_1"><a
					href="../indexplates/special.php?id=ippudo&lan=TW"
					data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'AdvancedSearch', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA002_food/iA002_006a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">達人推薦</span></a></li>
				<li id="Video_1"><a
					href="../indexplates/video.php?id=ippudo&lan=TW" data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Video', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA004_Vogue_Clothing/iA004_015a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">影音連結</span></a></li>
				<li id="Coupon_1"><a
					href="../indexplates/coupon.php?id=ippudo&lan=TW" data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Coupon', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA002_food/iA002_009a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">優惠訊息</span></a></li>
				<li id="Facebook_1"><a
					href="https://www.facebook.com/ippudo.tw" data-ajax="false"
					target="_blank"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Facebook', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA001_default/iA001_008a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">官方臉書</span></a></li>
				<li id="Multilink_1"><a
					href="../indexplates/multilink.php?id=ippudo&lan=TW"
					data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Multilink', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA002_food/iA002_014a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">品牌連結</span></a></li>
				<li id="Contact_1"><a
					href="../indexplates/contact.php?id=ippudo&lan=TW"
					data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Contact', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA004_Vogue_Clothing/iA004_037a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">客服專區</span></a></li>
			</ul>

		</div>
		<!-- /panel -->


		<div data-role="header" data-position="fixed">
			<link rel="stylesheet" href="css/font-face.css" />
			<!--<div id="panel-icon"><img src="images/menu_back_01.png" width="25" height="20" onclick="history.back()" style="cursor: pointer;"></div>
    <div id="panel-icon1"><img src="images/menu_back_01.png" width="25" height="20" onclick="history.back()" style="cursor: pointer;"></div>-->

			<!--判斷Multispecial返回上頁-->
			<div id="panel-icon">
				<span class="glyphicon glyphicon-chevron-left"
					onclick="history.back()" style="cursor: pointer;" data-ajax="false"></span>
			</div>
			<div id="panel-icon1">
				<span class="glyphicon glyphicon-chevron-left"
					onclick="history.back()" style="cursor: pointer;" data-ajax="false"></span>
			</div>

			<span class="header-title">店鋪資訊</span>


		</div>
		<!-- /header -->

		<div class="panel" id="panel">
			<ul data-role="listview" data-theme="a">
				<li id="Index_1"><a href="../indexplates/index.php?lan=TW"
					data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Home', 1);"><img
						src="http://data.indexbricks.com/image/function/Homepage@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">返回首頁</span></a></li>
				<li id="Intro_1"><a
					href="../indexplates/intro.php?id=ippudo&lan=TW" data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Intro', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA002_food/iA002_001a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">品牌介紹</span></a></li>




				<li id="Profile_1"><a
					href="../indexplates/profile.php?id=ippudo&lan=TW"
					data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Profile', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA002_food/iA002_003a@2x.png"
						class="ui-li-icon function-icon2" /><span class="type_name2">店鋪資訊</span></a></li>


				<li id="Photo_1"><a
					href="../indexplates/photo.php?id=ippudo&lan=TW" data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Photo', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA002_food/iA002_032a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">美味菜單</span></a></li>


				<li id="News_1"><a
					href="../indexplates/news.php?id=ippudo&lan=TW" data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'News', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA001_default/iA001_004a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">最新消息</span></a></li>


				<li id="Special_1"><a
					href="../indexplates/special.php?id=ippudo&lan=TW"
					data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'AdvancedSearch', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA002_food/iA002_006a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">達人推薦</span></a></li>


				<li id="Video_1"><a
					href="../indexplates/video.php?id=ippudo&lan=TW" data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Video', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA004_Vogue_Clothing/iA004_015a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">影音連結</span></a></li>


				<li id="Coupon_1"><a
					href="../indexplates/coupon.php?id=ippudo&lan=TW" data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Coupon', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA002_food/iA002_009a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">優惠訊息</span></a></li>


				<li id="Facebook_1"><a
					href="https://www.facebook.com/ippudo.tw" data-ajax="false"
					target="_blank"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Facebook', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA001_default/iA001_008a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">官方臉書</span></a></li>


				<li id="Multilink_1"><a
					href="../indexplates/multilink.php?id=ippudo&lan=TW"
					data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Multilink', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA002_food/iA002_014a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">品牌連結</span></a></li>


				<li id="Contact_1"><a
					href="../indexplates/contact.php?id=ippudo&lan=TW"
					data-ajax="false"
					onClick="ga('send', 'event', 'ippudo', 'Web', 'Contact', 1);"><img
						src="http://data.indexbricks.com/image/function_type/iA004_Vogue_Clothing/iA004_037a@2x.png"
						class="ui-li-icon function-icon1" /><span class="type_name1">客服專區</span></a></li>
			</ul>

		</div>

		<div data-role="content">

			<div class="new_content" style="padding: 15px;">


				<div id="profile_table">
					<div class="profile_tr">
						<div class="profile_td">
							<div class="profile_pic">
								<div id="sync1" class="owl-carousel">
									<div class="item">
										<img src="http://data.indexbricks.com/ippudo/images/p101.jpg"
											class="profile_item_img">
									</div>
									<div class="item">
										<img
											src="http://data.indexbricks.com/ippudo/images/141211155625950584.jpg"
											class="profile_item_img">
									</div>
									<div class="item">
										<img
											src="http://data.indexbricks.com/ippudo/images/141211155626637737.jpg"
											class="profile_item_img">
									</div>
								</div>
								<div id="sync2" class="owl-carousel" style="padding-top: 10px;">
									<div class="item">
										<img src="http://data.indexbricks.com/ippudo/images/p101.jpg"
											width="100%" style="border: 1px solid #E7E7E7;">
									</div>
									<div class="item">
										<img
											src="http://data.indexbricks.com/ippudo/images/141211155625950584.jpg"
											width="100%" style="border: 1px solid #E7E7E7;">
									</div>
									<div class="item">
										<img
											src="http://data.indexbricks.com/ippudo/images/141211155626637737.jpg"
											width="100%" style="border: 1px solid #E7E7E7;">
									</div>
								</div>
							</div>
						</div>
						<div class="profile_td1">

							<div class="list_padding">
								<div id="top_gradient"></div>
								<div class="profile_title">店家名稱：</div>
								<div class="profile_content">拉麵餐廳 / 一風堂中山本店</div>
								<div id="line1"></div>
								<a href="tel:02-2562-9222" data-ajax="false" id="phone_click">
									<div class="profile_title">店家電話：</div>

									<div class="profile_content">02-2562-9222</div>
								</a>
								<div id="line1"></div>
								<span id="address_click">
									<div class="profile_title">店家地址：</div>
									<div class="profile_content ">台北市中山區中山北路一段85號1樓 ( 近捷運中山站
										)</div>
								</span>
								<div id="line1"></div>
								<div class="profile_title">營業時間：</div>
								<div class="profile_content">
									週一至週日 11:30 - 24:00<br /> (閉店前30分鐘為最後點餐)<br /> (四人以上可接受預約)
								</div>
								<div id="line1"></div>
								<div class="profile_title">商家簡介：</div>
								<div class="profile_content">
									中山店為台灣第一家「一風堂」。打造令人放鬆的舒適空間，讓顧客無論何時來皆能輕鬆自如；色調運用兩大招牌拉麵的紅跟白，加上店員朝氣十足的笑容，營造出活潑十足的氣氛。店內另一項重要的元素即為日本傳統的風神與雷神，您能於店內充分感受一風堂精神及日本風情。<br />
									<br /> 菜單連結：<a href="http://goo.gl/bQ0fzT" target="_blank"
										style="font-weight: normal; color: #2489ce;">http://goo.gl/bQ0fzT</a>
								</div>
								<div id="bottom_gradient"></div>
							</div>
							<div class="profile_more">更多資訊...</div>

						</div>

					</div>
				</div>
			</div>


			<div class="new_content"
				style="padding: 15px; min-height: 400px; position: relative;">
				<div id="map-canvas" style="width: 100%;"></div>



			</div>

		</div>
		<!-- /content -->

		<div data-role="footer">
			<h4>Copyright &copy; LiVEBRiCKS Inc.</h4>
		</div>
		<!-- /footer -->

	</div>
	<!-- / page -->
	<script src="java/owl.carousel.min.js"></script>
	<script>
		$(document)
				.ready(
						function() {

							$('#address_click')
									.on(
											'click',
											function(e) {
												e.preventDefault();

												var $body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
														: $('body'))
														: $('html,body');
												$body
														.stop()
														.animate(
																{
																	'scrollTop' : $(
																			"#map-canvas")
																			.offset().top
																}, 1000,
																function() {
																	// Animation complete.
																});
											});

							var sync1 = $("#sync1");
							var sync2 = $("#sync2");

							sync1.owlCarousel({
								singleItem : true,
								slideSpeed : 1000,
								navigation : false,
								pagination : false,
								afterAction : syncPosition,
								responsiveRefreshRate : 200,
							});

							sync2.owlCarousel({
								items : 3,
								itemsDesktop : [ 9999, 4 ],
								itemsDesktopSmall : [ 979, 4 ],
								itemsTablet : [ 750, 4 ],
								itemsTabletSmall : false,
								itemsMobile : [ 500, 3 ],
								pagination : false,
								responsiveRefreshRate : 100,
								afterInit : function(el) {
									el.find(".owl-item").eq(0).addClass(
											"synced");
								}
							});

							function syncPosition(el) {
								var current = this.currentItem;
								$("#sync2").find(".owl-item").removeClass(
										"synced").eq(current)
										.addClass("synced")
								if ($("#sync2").data("owlCarousel") !== undefined) {
									center(current)
								}
							}

							$("#sync2").on("click", ".owl-item", function(e) {
								e.preventDefault();
								var number = $(this).data("owlItem");
								sync1.trigger("owl.goTo", number);
							});

							function center(number) {
								var sync2visible = sync2.data("owlCarousel").owl.visibleItems;
								var num = number;
								var found = false;
								for ( var i in sync2visible) {
									if (num === sync2visible[i]) {
										var found = true;
									}
								}

								if (found === false) {
									if (num > sync2visible[sync2visible.length - 1]) {
										sync2.trigger("owl.goTo", num
												- sync2visible.length + 2)
									} else {
										if (num - 1 === -1) {
											num = 0;
										}
										sync2.trigger("owl.goTo", num);
									}
								} else if (num === sync2visible[sync2visible.length - 1]) {
									sync2.trigger("owl.goTo", sync2visible[1])
								} else if (num === sync2visible[0]) {
									sync2.trigger("owl.goTo", num - 1)
								}

							}
						});
	</script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBFHON-_xtfYxAUYes2TrP1LG_6InSErDg"></script>
	<script type="text/javascript"
		src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script>
	<script>
		var map;

		var markers = [];
		$(function() {

			var image = {
				url : 'images/store_map.png',
				// This marker is 20 pixels wide by 32 pixels tall.
				size : new google.maps.Size(50, 50),
				// The origin for this image is 0,0.
				origin : new google.maps.Point(0, 0),
				// The anchor for this image is the base of the flagpole at 0,32.
				anchor : new google.maps.Point(50, 50)
			}
			var image1 = {
				url : 'images/parking_pin.png',
				// This marker is 20 pixels wide by 32 pixels tall.
				size : new google.maps.Size(50, 50),
				// The origin for this image is 0,0.
				origin : new google.maps.Point(0, 0),
				// The anchor for this image is the base of the flagpole at 0,32.
				anchor : new google.maps.Point(12, 50)
			}
			var thiscolor = $(".ui-mobile .ui-header").css('background-color');
			var style_array_from_above_here = [

			{
				"stylers" : [ {
					"hue" : colorHex(thiscolor)
				} ]
			}, {
				"featureType" : "road",
				"elementType" : "labels",
				"stylers" : [ {
					"visibility" : "on"
				} ]
			}, {
				"featureType" : "road",
				"elementType" : "geometry",
				"stylers" : [ {
					"lightness" : 100
				}, {
					"visibility" : "simplified"
				} ]
			} ];

			var myLatLng = new google.maps.LatLng(25.0504883, 121.5222772);
			var mapOptions = {
				zoom : 17,
				center : myLatLng,
				mapTypeId : google.maps.MapTypeId.ROADMAP,
				styles : style_array_from_above_here
			};
			map = new google.maps.Map(document.getElementById('map-canvas'),
					mapOptions);
			var LMarker = new google.maps.Marker({
				position : myLatLng,
				map : map,
				icon : image
			});
			google.maps.event.addListener(map, 'zoom_changed', function() {
				map.setCenter(myLatLng);
			});

			var marker, i, myoption;
			var goimg = "<img src='images/parking_info.png' width='11' height='16'>";
			function boxText(i) {
				var a = "<span onclick='showPop(" + i + ");'>";
				var Text = document.createElement("div");
				Text.setAttribute("align", "left");
				Text.style.cssText = "position:relative; border:0px solid black; margin-top: 8px; background:#333; color:#FFF; font-family:Arial,微軟正黑體; font-size:12px; padding:.5em 1em; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;";
				Text.innerHTML = "" + locations[i][0] + "<br>剩餘車位："
						+ locations[i][3] + "<div class='parking_link'>" + a
						+ goimg + "</span></div>";
				return Text;
			}

			for (i = 0; i < locations.length; i++) {
				marker = new google.maps.Marker({
					position : new google.maps.LatLng(locations[i][1],
							locations[i][2]),
					map : map,
					icon : image1,
					title : locations[i][0]
				});
				markers.push(marker);
				myoption = {
					disableAutoPan : false,
					maxWidth : 150,
					pixelOffset : new google.maps.Size(-130, 0),
					zIndex : null,
					boxStyle : {
						background : "url('http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/examples/tipbox.gif') no-repeat",
						opacity : 0.75,
						width : "200px"
					},
					closeBoxMargin : "12px 4px 2px 2px",
					closeBoxURL : "",
					infoBoxClearance : new google.maps.Size(1, 1)
				};

				var infobox = new InfoBox();
				google.maps.event.addListener(marker, 'click', (function(
						marker, i, myoption) {
					return function() {
						infobox.close();
						infobox.setOptions(myoption);
						infobox.setContent(boxText(i));
						infobox.open(map, this);
						google.maps.event.addListener(map, 'click', function() {
							infobox.close();
						});
					}
				})(marker, i, myoption));
			}

			$(window).resize(function() {
				map.setCenter(myLatLng);
			});

			setAllMap(null);

			function colorHex(that) {
				var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
				var that = that;
				if (/^(rgb|RGB)/.test(that)) {
					var aColor = that.replace(/(?:\(|\)|rgb|RGB)*/g, "").split(
							",");
					var strHex = "#";
					for (var i = 0; i < aColor.length; i++) {
						var hex = Number(aColor[i]).toString(16);
						if (hex === "0") {
							hex += hex;
						}
						strHex += hex;
					}
					if (strHex.length !== 7) {
						strHex = that;
					}
					return strHex;
				} else if (/^(rgba|RGBA)/.test(that)) {
					var aColor = that.replace(/(?:\(|\)|rgba|RGBA)*/g, "")
							.split(",");
					var strHex = "#";
					for (var i = 0; i < aColor.length - 1; i++) {
						var hex = Number(aColor[i]).toString(16);
						if (hex === "0") {
							hex += hex;
						}
						strHex += hex;
					}
					if (strHex.length !== 7) {
						strHex = that;
					}
					return strHex;
				} else if (reg.test(that)) {
					var aNum = that.replace(/#/, "").split("");
					if (aNum.length === 6) {
						return that;
					} else if (aNum.length === 3) {
						var numHex = "#";
						for (var i = 0; i < aNum.length; i += 1) {
							numHex += (aNum[i] + aNum[i]);
						}
						return numHex;
					}
				} else {
					return that;
				}
			}
			;
		});
		// Sets the map on all markers in the array.
		function setAllMap(map) {
			for (var i = 0; i < markers.length; i++) {
				markers[i].setMap(map);
			}
		}

		// Removes the markers from the map, but keeps them in the array.
		function clearMarkers() {
			setAllMap(null);
			$("#parking_show").hide();
			$("#parking_hide").show();
			var $body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
					: $('body'))
					: $('html,body');
			$body.stop().animate({
				'scrollTop' : $("#map-canvas").offset().top
			}, 1000, function() {
				// Animation complete.
			});
		}

		// Shows any markers currently in the array.
		function showMarkers() {
			setAllMap(map);
			$("#parking_hide").hide();
			$("#parking_show").show();
			var $body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html')
					: $('body'))
					: $('html,body');
			$body.stop().animate({
				'scrollTop' : $("#map-canvas").offset().top
			}, 1000, function() {
				// Animation complete.
			});
		}
		function showPop(i) {
			$("#popupBasic" + i + "").show();
		}
		function hidePop(i) {
			$(".popup_frame").hide();
		}
	</script>
</body>
</html>

<!-- header -->
<jsp:include page="/front_end/header.jsp" />
<!--background image-->
<img src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg"
	width="100%" height="" alt="">


<!-- 		<script src="https://code.jquery.com/jquery-3.2.1.min.js" -->
<!-- 			integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" -->
<!-- 			crossorigin="anonymous"></script> -->
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
