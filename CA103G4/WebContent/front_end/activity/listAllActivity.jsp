<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%
	ActivityService actSvc = new ActivityService();
	List<ActivityVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Site Title -->
<title>活動快訊</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<!-- linearicons CSS -->
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
<!--your  CSS ============================================= -->

   <!--JS BS4-->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<style>
html {
	height: 100%;
}

body {
	background-image: url(img/woodbackground3.png);
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
	font-family: 'Noto Sans TC', sans-serif;
	font-weight: 400;
}
.container-margin{
margin-top:20px;
margin-bottom:20px;
opacity:0.8;
padding-bottom: 760px;
}

.coupon {
    border: 5px dotted #bbb;
    width: 80%;
    border-radius: 15px;
    margin: 0 auto;
    max-width: 600px;
}

.container {
    padding: 2px 16px;
    background-color: #f1f1f1;
}

.promo {
    background: #ccc;
    padding: 3px;
}

.expire {
    color: red;
}
#carousel-ctrl .item img{ 
 		height:100%;
 	} 
</style>
</head>
<jsp:include page="/front_end/header.jsp" flush="true" />
<body
	background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "
	width="100%" height="">
	<!--your html start==================================================================================-->
	    <section class="section-gap">
	<div class="col-xs-12 col-sm-8 col-sm-offset-0">
		
			<div id="carousel-id2" class="carousel slide" data-ride="carousel">
			    <!-- 幻燈片小圓點區 -->
			    <ol class="carousel-indicators">
				    <c:forEach varStatus="s" items="${actSvc.getAll()}">
				    	<li data-target="#carousel-id2" data-slide-to="${s.index}" class="${s.first?'active':''}"></li>
				    </c:forEach>
			    </ol>
			    <!-- 幻燈片主圖區 -->
			    <div class="carousel-inner ">
				    <c:forEach varStatus="s" var="adVO" items="${avtSvc.getAll()}">
				     	<div class="item ${s.first?'active':''}">
				        	<img class="adimg" src="<%= request.getContextPath()%>/activity/activityshowimage?act_No=${activityVO.act_No}" alt="">
					    </div>
				    </c:forEach>
			    </div>
			    <!-- 上下頁控制區 -->
				    <a class="left carousel-control" href="#carousel-id2" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> 
				    <a class="right carousel-control" href="#carousel-id2" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a> 
			</div>
		</div>
</section>

            <script>$('.carousel').carousel()</script>
	
	<!-- 優惠卷 -->
	
	<div class="container container-margin">
		<div class="row">
		
		 <div class="row">
            <div class="col-md-12 my-5" >優惠卷</div>
          </div>
        
		<div class="col-md-4">
	<div class="coupon">
  <div class="container">
    <h3>Company Logo</h3>
  </div>
  <img src="/img/first-big-pic.jpg" alt="Avatar" style="width:100%;">
  <div class="container" style="background-color:white">
    <h4><b>20% OFF YOUR PURCHASE</b></h4> 
  </div>
  <div class="container">
    <p>Use Promo Code: <span class="promo">BOH232</span></p>
    <p class="expire">Expires: Jan 03, 2017</p>
  </div>
</div>
		
		</div>
       <div class="col-md-4">
	<div class="coupon">
  <div class="container">
    <h3>Company Logo</h3>
  </div>
  <img src="/img/first-big-pic.jpg" alt="Avatar" style="width:100%;">
  <div class="container" style="background-color:white">
    <h4><b>20% OFF YOUR PURCHASE</b></h4> 
  </div>
  <div class="container">
    <p>Use Promo Code: <span class="promo">BOH232</span></p>
    <p class="expire">Expires: Jan 03, 2017</p>
  </div>
</div>
		
		</div>
       <div class="col-md-4">
	<div class="coupon">
  <div class="container">
    <h3>Company Logo</h3>
  </div>
  <img src="/img/first-big-pic.jpg" alt="Avatar" style="width:100%;">
  <div class="container" style="background-color:white">
    <h4><b>20% OFF YOUR PURCHASE</b></h4> 
  </div>
  <div class="container">
    <p>Use Promo Code: <span class="promo">BOH232</span></p>
    <p class="expire">Expires: Jan 03, 2017</p>
  </div>
</div>
		
		</div>
       
        
        
        
      
		
		
		
		
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<div class="col-sm-12 col-7 col-lg-8 ">
				<%@ include file="pages/page1.file"%>
				<!-- Blog Post -->
				<c:forEach var="activityVO" items="${list}">
					<div class="card mb-4">
						<img class="card-img-top"
							src="<%=request.getContextPath()%>/activity/activityshowimage.do?act_No=${activityVO.act_No}"
							alt="Card image cap">
						<div class="card-body">
							<h2 class="card-title">${activityVO.act_Name}</h2>
							<p class="card-text">${activityVO.act_Content}</p>
							<a href="#" class="btn btn-primary">Read More &rarr;</a>
						</div>
						<div class="card-footer text-muted">
							活動期間:
							<fmt:formatDate value="${activityVO.act_Start}"
								pattern="yyyy-MM-dd HH:mm" />
							~
							<fmt:formatDate value="${activityVO.act_End}"
								pattern="yyyy-MM-dd HH:mm" />
						</div>
					</div>
				</c:forEach>
				<%@ include file="pages/page2.file"%>
			</div>
			<!-- 側邊攔 -->
			<div class="col-sm-12 col-5 col-lg-4">
				col-5 col-lg-4
				<form class="form-inline">
					<label class="sr-only" for="inlineFormInputGroup">Username</label>
					<div class="input-group mb-2 mr-sm-2 mb-sm-0">
						<input type="text" class="form-control" id="inlineFormInputGroup"
							placeholder="Username">
					</div>

					<button type="submit" class="btn btn-danger">Go</button>
				</form>

				<!--條件查詢bar-->
				<div class="form-group">
					<label>name</label> <select class="form-control">
						<option value="">1</option>
						<option value="">2</option>
						<option value="">3</option>
						<option value="">4</option>
						<option value="">5</option>
						<option value="">6</option>
						<option value="">7</option>
						<option value="">8</option>
						<option value="">9</option>
						<option value="">10</option>
					</select>
				</div>




			</div>
		</div>
	</div>

	<jsp:include page="/front_end/footer.jsp" flush="true" />
</body>
</html>
