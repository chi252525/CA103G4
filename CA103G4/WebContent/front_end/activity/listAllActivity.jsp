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
.adimg{
		width:100%;
		height:500px;
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

 <script>$('.carousel').carousel()</script>
	
	<div class="container px-0" style="background-color:rgba(255,255,255,0.45)">
	
	<div class="my-4"></div>
		<!-- 廣告輪播圖開始 -->
      <div class="carousel slide" data-ride="carousel" id="carouselArchitecture" >
          <ol class="carousel-indicators">
            <c:forEach varStatus="s" items="${list}">
            <li data-target="#carouselArchitecture" data-slide-to="${s.index}" class=" ${s.first?'active':''}">
              <i></i>
            </li>
              </c:forEach>
          </ol>
          <div class="carousel-inner" role="listbox">
             <c:forEach varStatus="s" var="actVO" items="${list}">
            <div class="carousel-item ${s.first?'active':''}">
              <img class="img-fluid" src="<%=request.getContextPath()%>/activity/activityshowimage.do?act_No=${actVO.act_No}
              " data-holder-rendered="true" > </div>
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
			
			
	<%@ include file="pages/page1.file"%>
	
<c:forEach var="activityVO" items="${list}">
	 <div class="py-4 px-2"  >
    <div class="container" style="background-color:rgba(255,255,255,0.45)">
      <div class="row">
        <div class=" col-md-12">
          <div class="row">
            <div class="text-center col-4 px-1 py-1">
              <img class="img-fluid d-block" src="img/ad_001.jpg" > </div>
            <div class="col-8">
              <h5 class="mb-3 text-dark my-2">
                <b>${activityVO.act_Name}</b>
              </h5>
              <p class="my-1">活動期間
							<fmt:formatDate value="${activityVO.act_Start}"
								pattern="yyyy-MM-dd " />
							~
							<fmt:formatDate value="${activityVO.act_End}"
								pattern="yyyy-MM-dd " /></p> </p>
              <a href="#" class="btn btn-outline-primary">More..</a>
              <a class="btn btn-danger" href="#">取得優惠卷</a>
            </div>
          </div>
        </div>
      </div>
  
    </div>
  </div>
		</c:forEach>
		<%@ include file="pages/page2.file"%>
	
			
			
			
			
			
			
			
	

		
		
	 

	
	
</div>
<!-- container end -->
</body>
</html>
