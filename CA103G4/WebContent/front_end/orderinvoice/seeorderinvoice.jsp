<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%><%-- s分頁 --%>
<%@ page import="com.orderinvoice.model.*"%>

<jsp:useBean id="couponhistorySvc" scope="page" class="com.couponhistory.model.CouponhistoryService" />

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
	height: 250px;
  }
	
 


</style>

</head>
<body class="shadow-lg w-100" background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png" width="100%">
	
	
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="card ames">
					<div class="card-body">
						<h1 class="card-title">取餐資訊</h1>
						<h2 class="card-subtitle mb-2 text-muted"><%= session.getAttribute("ordNo")%></h2>
						<p class="card-text h4">分店名稱:<%= session.getAttribute("braName")%></p>
						<p class="card-text h4">分店位址:<%= session.getAttribute("braAdr")%></p>
						<p class="card-text h4">取餐時間:<%= session.getAttribute("time")%></p>	
						
						<c:if test="<%= session.getAttribute(\"deliv_addres\") != null %>">
						<p class="card-text h4">外送地址:<%= request.getParameter("deliv_addres")%></p>
						</c:if>

						<c:if test="<%= session.getAttribute(\"card_number\") != null %>">						
						<p class="card-text h4">付款方式:信用卡</p>
						<p class="card-text h4">信用卡末四碼:<%= session.getAttribute("card_number")%></p>
						</c:if>

						<c:if test="<%= session.getAttribute(\"card_number\") == null && session.getAttribute(\"point\") == null%>">						
						<p class="card-text h4">付款方式:現金</p>
						</c:if>

						<c:if test="<%= session.getAttribute(\"point\") != null%>">						
						<p class="card-text h4">付款方式:</p>
						</c:if>
						
						<p class="card-text h4">總金額:<%= session.getAttribute("amount")%></p>
						<p class="card-text h4 tes">備註:<br><%= session.getAttribute("ps")%></p>
						
						<div class="container">
							<div class="row">
								<div class="col">
								</div>
						<p class="card-text h4"><%= session.getAttribute("braName")%>，祝您用餐愉快！</p>
								<div class="col">
								</div>
							</div>			
						</div>
						
						<%-- 按鈕  --%>
						<div class="container">
							<div class="row">
								<div class="col">
								</div>
									<div class="col-1">
									<a href="#" class="card-link">確認</a>
									<!-- 連到哪要改一下 -->
									</div>
								<div class="col">
								</div>
							</div>			
						</div>
						
					</div>
				</div>			
			</div>
		</div>
	</div>


<jsp:include page="/front_end/footer.jsp" flush="true" />
</body>

</html>