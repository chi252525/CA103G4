<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>


<%
	ActivityService actSvc = new ActivityService();
	List<ActivityVO> list = null;
	if (request.getAttribute("actlist") == null) {
		list = actSvc.findNewAct();
		pageContext.setAttribute("list", list);
	} else {
		list = (List<ActivityVO>) request.getAttribute("actlist");
		pageContext.setAttribute("list", list);
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Site Title -->
<title>活動快訊</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/res/img/icon.png" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!-- linearicons CSS -->
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
<!--JS BS4-->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<!-- Shave v2.1.3 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/shave/2.1.3/shave.min.js"></script>


<style>
html {
	height: 100%;
}

body {

	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
	font-family: Montserrat, Arial, "微軟正黑體", "Microsoft JhengHei" !important;
	font-weight: 400;
}



.promo {
	background: #ccc;
	padding: 3px;
}

.expire {
	color: red;
}

.adimg {
	width: 100%;
	height: 500px;
}

#carousel-ctrl .item img {
	height: 100%;
}

.item {
	margin: 5px;
	width: 250px;
	height: 80px;
	background-color: #fa0;
}

div.shavetext {
	white-space: nowrap;
	width: 50px;
	overflow: hidden;
	text-overflow: ellipsis;
}
.button-ning {
  padding: 15px 25px;
  font-size: 24px;
  text-align: center;
  cursor: pointer;
  outline: none;
  color: #fff;
  background-color: #c60909;
  border: none;
  border-radius: 15px;
  box-shadow: 0 9px #999;
}

.button-ning:hover {background-color: #c60909}

.button-ning:active {
  background-color: #c60909;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
</style>
</head>
<jsp:include page="/front_end/header.jsp" flush="true" />

<body onLoad="connect();" onunload="disconnect();"
	background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "
	width="100%" height="">
	
		<div class="container ">
	</div>
	
	
	
	<!--your html start==================================================================================-->
	<script>$('.carousel').carousel()</script>
	<div class="container ">
		<div class="row " style="background-color: rgba(255, 255, 255, 0.45)">
			<div class="my-4"></div>
			<!-- 廣告輪播圖開始 -->
			<div class="carousel slide" data-ride="carousel"
				id="carouselArchitecture">
				<ol class="carousel-indicators">
					<c:forEach varStatus="s" items="${list}">
						<li data-target="#carouselArchitecture" data-slide-to="${s.index}"
							class=" ${s.first?'active':''}"><i></i></li>
					</c:forEach>
				</ol>
				<div class="carousel-inner" role="listbox">
					<c:forEach varStatus="s" var="actVO" items="${list}">
						<div class="carousel-item ${s.first?'active':''}">
						<a href="<%=request.getContextPath()%>/activity/activityServlet.do?action=getOne_For_Display&act_No=${actVO.act_No}">
							<img class="img-fluid"
								src="<%=request.getContextPath()%>/activity/activityshowimage.do?act_No=${actVO.act_No}"
								data-holder-rendered="true">
								</a>
						</div>
					</c:forEach>
				</div>
			</div>

			<!-- 廣告輪播圖end -->
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
		<!-- 分頁及內容開始 -->
		<div class="my-1 col-12">
			<div class="row">
				<div class="col-12 px-0">
					<div class="card border-secondary "	style="background-color: rgba(255, 255, 255, 0.45)">
						<div class="card-body text-secondary">
							<div class="col-4 px-0">
								<form method="post"	action="<%=request.getContextPath()%>/activity/activityServlet.do">
									<input type="hidden" name="action" value="findbyactcata">
									<select class="form-control combobox" name="act_Cat"
										onchange="submit()">
										<option disabled selected>--請選擇--</option>
										<option value="AC1">新品上市</option>
										<option value="AC2">優惠折扣</option>
									</select>
								</form>
								  <div id="myID" class="WebSocket"></div>
    							<span id="output" class="WebSocket"></span>
							</div>
						</div>
					</div>

				</div>
			</div>
			<%@ include file="pages/page1.file"%>
		</div>

		<c:forEach var="activityVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<div class="col-12"
				style="background-color: rgba(255, 255, 255, 0.45)">
				<div class="py-4 px-2">
					<div class="container">
						<div class="row">
							<div class=" col-md-12">
								<div class="row"
									style="background-color: rgba(255, 255, 255, 0.45)">
									<div class=" col-4 px-1 py-1 ">
										<img class="img-fluid d-block mx-auto my-auto"
											style="width: 400px; height: 250px;"
											src="<%=request.getContextPath()%>/activity/activityshowsmallpic.do?act_No=${activityVO.act_No}">
									</div>
									<div class="col-8 py-1 my-2 ">
										<div class="col-12 mx-0 px-0">
											<h5>
												<b>${activityVO.act_Name}</b>
											</h5>
										</div>
										<div class="d-flex ">
											<div class="p-0">
												活動期間
												<fmt:formatDate value="${activityVO.act_PreAddTime}"
													pattern="yyyy-MM-dd " />
												~
												<fmt:formatDate value="${activityVO.act_PreOffTime}"
													pattern="yyyy-MM-dd " />
											</div>
											<div class="p-0">.${activityVO.act_Views}&nbsp;Views</div>

										</div>

										<div class="row">
											<h5 class="mb-1 text-dark my-1"></h5>
											<hr>
											<div class="col-12 mt-2 shavetext"></div>
											<div class="col-12 ">
												<div class="row">
													<div class="col-8 mt-4">
														<a
															href="<%=request.getContextPath()%>/activity/activityServlet.do?action=getOne_For_Display&act_No=${activityVO.act_No}"
															class="btn btn-outline-primary btn-sm">More..</a>
													</div>
													<div class="col-4 mt-4">
													<c:if test=""> </c:if>
														<button class="button-ning"
															id="${activityVO.act_No}">取得優惠卷</button>

														<p class="mb-0 test ${activityVO.act_No}"
															style="color: red;"></p>
													</div>
												</div>
			<script type="text/javascript">
            
            $(document).ready(function() {
				$("#${activityVO.act_No}").click(function() {
					$.ajax({
                        type: "post",
                        url: "<%=request.getContextPath()%>/couponhistory/CouponhistoryServlet.do",
						data : {
								"action" : "insert",
								'mem_No' : '${memVO.mem_No}',
								'coucat_No' : '${activityVO.coucat_No}'
								},
						dataType : "json",
						success : function(result) {
							if (result.status === 'success')
							$(".${activityVO.act_No}").html(result.msg);
							else
							alert("提示訊息 Oops!: "+ result.msg);
							},
						error : function() {
								alert("提示訊息Oops!沒取到優惠券");
									}
								})

								});
						});
												</script>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</c:forEach>

<!-- 有會員上線時通知 -->
<script>
var MyPoint = "/ActivityServer";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
var webSocket;
var myID;
function connect() {
	// 建立 websocket 物件
	webSocket = new WebSocket(endPointURL);

	webSocket.onmessage = function(event) {
		console.log(event.data); 
		var obj =JSON.parse(event.data);
		console.log(obj.act_No); 
// 		document.getElementById("myID").innerHTML = obj.act_No;
		var new_act_No= obj.act_No;
		document.getElementById("new_act").src="<%=request.getContextPath()%>/activity/activityshowimage.do?act_No="+new_act_No;
		$("#newnotify").modal({show: true});
		webSocket.onclose = function(event) {
			var mySpan = document.getElementById("output");
			mySpan.innerHTML = "WebSocket連線已關閉";
		};
		
	}
	

	
	
	
	}
	
	
	
	
</script>

<div class="modal" tabindex="-1" role="dialog" id="newnotify">
  <div class="modal-dialog" role="dialog" aria-hidden="true" >
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">新活動通知!!</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>竹風堂最新優惠活動，給懂得品嚐美食的您</p>
        <img class="img-fluid" id="new_act"src="">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">我知道了</button>
      
      </div>
    </div>
  </div>
</div>

		<!-- page2的內容 -->
		<div class="col-12  my-2">
			<div class="row justify-content-center">
				<div class="col-4 px-2 py-1">
					<%@ include file="pages/page2.file"%>
				</div>
				<!-- 內容結束-->
			</div>
		</div>
	</div>


	<!-- container end -->

	<jsp:include page="/front_end/footer.jsp" flush="true" />
</body>

</html>
