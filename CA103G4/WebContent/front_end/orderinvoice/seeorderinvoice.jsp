<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%><%-- s分頁 --%>
<%@ page import="com.orderinvoice.model.*"%>

<%-- <jsp:useBean id="couponhistorySvc" scope="page" class="com.couponhistory.model.CouponhistoryService" /> --%>

<%-- 存取會員編號 --%>
<%-- <% --%>
<%-- mem = (String)session.getAttribute("memNo"); --%>
<%-- %> --%>




<!DOCTYPE html>
<html>

<jsp:include page="/front_end/header.jsp" flush="true" />
<img src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg" width="100%" height="" alt="">
<head>
<meta charset="UTF-8">
<!-- font aewsome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.0/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/v4-shims.css">
<!-- Bootsraps-->
<script src="https://code.jquery.com/jquery-3.2.1.min.js "></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
<!-- datatable-->

<!--credit card-->
<script src="js/card.js"></script>
<link rel="stylesheet" href="css/card.css">
<!-- address choosing -->
<script src="<%=request.getContextPath()%>/front_end/member/js/selectaddress.js"></script>

<title>listbymem</title>

<style type="text/css">
 
 .ames{
		width: 800px;
		height: 700px;
		background-color: #AA7700;
		margin: auto; 
		margin-top: 25%;
	}
	
  .tes{
  	width: 600px;
	height: 200px;
  }
	
 


</style>

</head>
<body class="shadow-lg w-100" background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png" width="100%">

<div class="card text-center ames">
		<div class="card-header">
			取餐資訊
		</div>
		<div class="card-body">
			<h5 class="card-title"><%= request.getAttribute("ordNo")%></h5>
			<p class="card-text">分店名稱:<%= request.getAttribute("braName")%></p>
			<p class="card-text">取餐時間:<%= request.getAttribute("time")%></p>
			
			<c:if test="<%=request.getAttribute(\"deliv_addres\") != null %>">
			<p class="card-text">外送地址:<%= request.getParameter("deliv_addres")%></p>
			</c:if>
			<c:if test="<%=request.getAttribute(\"deliv_addres\") == null %>">
			<p class="card-text">外送地址:無</p>
			</c:if>
			
			<p class="card-text">信用卡末四碼:<%= request.getAttribute("card_number")%></p>
			<p class="card-text">總金額:<%= request.getAttribute("amount")%></p>
			<p class="card-text">備註:</p>
			<p class="card-text tes"><%= request.getAttribute("ps")%></p>
			
			<a href="#" class="btn btn-primary">確認</a>
		</div>
		<div class="card-footer text-muted">
			電話:<%= request.getAttribute("braTel")%>
		</div>
	</div>





<jsp:include page="/front_end/footer.jsp" flush="true" />
</body>

</html>