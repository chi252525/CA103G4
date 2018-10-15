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

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/v4-shims.css">
<!-- Bootsraps-->
<script src="https://code.jquery.com/jquery-3.2.1.min.js "></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
<!-- datatable-->

<!-- <!--credit card--> 
<!-- <script src="js/card.js"></script> -->
<!-- <link rel="stylesheet" href="css/card.css"> -->
<!-- address choosing -->
<script src="<%=request.getContextPath()%>/front_end/member/js/selectaddress.js"></script>

<title>listbymem</title>

<style type="text/css">
 
 .ames{
		width: 900px;
/* 		height: 700px; */
		background-color: #AA7700;
		margin: auto; 
		
	}
	
  .tes{
  	width: 600px;
	height: 250px;
  }
	
 .card-title{
 color:#d21b1b;
 }
th{
background-color:rgba(0,0,0,0.8);
color:#fff;

}

.table-ning{
border:solid 1px;
}
td{

 valign:center;
}
</style>

</head>
<body class="shadow-lg w-100" background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png" width="100%">
	
	
	<div class="container">
		<div class="row">
			<div class="col-12 mt-3">
				<div class="card ames" style="background-color:rgba(255,255,255,0.8)">
					<div class="card-body" >
						<h3 class="card-title mt-3"  style="text-align:center;">~恭喜您!訂購餐點成功!~</h3>
						<p class="mb-2 text-muted">我們已收到您的訂餐資訊，您將會收到一封通知簡訊，如有任何疑問煩請通知竹風堂客服中心。</p>
							<p class="mb-2 text-muted">您的取餐資訊如下:</p>
						<table class="table mt-5 table-ning">
							<thead>
								<tr>
									<th>訂單編號</th>
									<th>分店名稱</th>
									<th>分店位置</th>
									<th>取餐時間</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><%= session.getAttribute("ordNo")%></td>
									<td><%= session.getAttribute("braName")%></td>
									<td><%= session.getAttribute("braAdr")%></td>
									<td><%= session.getAttribute("time")%></td>
								</tr>
									<tr>
									<td></td>
									<td></td>
									<td class="">總金額:</td>
									<td class=""><b><%= session.getAttribute("amount")%></b></td>
									</tr>
							</tbody>
						</table>
						
						<c:if test="<%= session.getAttribute(\"deliv_addres\") != null %>">
						<p class="card-text ">外送地址:<%= session.getAttribute("deliv_addres")%></p>
						</c:if>

						<c:if test="<%= session.getAttribute(\"card_number\") != null %>">						
						<p class="card-text ">付款方式:信用卡付款</p>
						<p class="card-text ">信用卡末四碼:<%= session.getAttribute("card_number")%></p>
						</c:if>

						<c:if test="<%= session.getAttribute(\"card_number\") == null && session.getAttribute(\"point\") == null%>">						
						<p class="card-text ">付款方式:現金付款</p>
						</c:if>

						<c:if test="<%= session.getAttribute(\"point\") != null%>">						
						<p class="card-text ">付款方式:竹幣付款</p>
						</c:if>
						
						
						<br> 備註:
						<textarea rows="4" class=" form-control" readonly>
						<%=session.getAttribute("ps")%>
						</textarea>


		
				
								
						<p class="card-text h4 mt-3"  style="text-align:center;"><i>~<%= session.getAttribute("braName")%>，祝您用餐愉快！~</i></p>
						
						
<div align="center"><a class="btn btn-danger mt-3"  href="<%=request.getContextPath()%>/front_end/index.jsp" role="button">回首頁逛逛</a></div>
						
								
									
				
					</div>
				</div>			
			</div>
		</div>
	</div>


<jsp:include page="/front_end/footer.jsp" flush="true" />
</body>

</html>