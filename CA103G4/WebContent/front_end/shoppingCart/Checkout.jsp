<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.menu.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/front_end/header.jsp" flush="true" />
<!--background image-->
<img src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg"
	width="100%" height="" alt="">
<meta charset="utf-8">
<!-- font aewsome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/v4-shims.css">
<!-- Bootsraps-->
<script src="https://code.jquery.com/jquery-3.2.1.min.js "></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
<!-- datatable-->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<head>
<title>竹風堂-結帳</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/ShoppingCart.css">
<style>
table, th, td {
	/*             border: 1px solid black; */
	text-align: center;
}

a:hover {
	text-decoration: none;
}
</style>
</head>

<body class="shadow-lg w-100"
	background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png"
	width="100%">
	<div id="div_shadow" class="py-5"">
		<div class=" container">
			<div class="row">
				<div class="col-md-12">
					<a
						href="<%=request.getContextPath()%>/front_end/shoppingCart/noodleShop.jsp">
						<h1 class="d-flex justify-content-start" style="color: #dfbe9f;">竹風堂-
							結帳</h1>
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class="shadow p-1">
		<div class="container">
			<div class="row">
				<div class="col-md-12" style="">
					<table id="" class="table-hover table table-striped"
						style="margin: auto;">
						<tr>
							<th width="200">餐點</th>
							<th width="100">價格</th>
							<th width="100">數量</th>
							<th width="120">
								<h3>小記</h3>
							</th>
							<th width="100">備註</th>
						</tr>



						<%
							@SuppressWarnings("unchecked")
							Vector<MenuVO> buylist = (Vector<MenuVO>) session.getAttribute("shoppingcart");
							String amount = (String) request.getAttribute("amount");
						%>
						<%
							for (int i = 0; i < buylist.size(); i++) {
								MenuVO mv = buylist.get(i);
								String name = mv.getMenu_Id();
								Integer total = mv.getMenu_Price() * mv.getMenu_quantity();
								Integer price = mv.getMenu_Price();
								Integer quantity = mv.getMenu_quantity();
						%>
						<tr>
							<td width="200"><%=name%></td>
							<td width="100"><%=price%></td>
							<td width="100"><%=quantity%></td>
							<td width="100"><%=total%></td>
							<td width="120"></td>
						</tr>
						<%
							}
						%>


						<tr>
							<td colspan="6" style="text-align: right;"><font size="+2">總記
									<h4>
										$
										<%=amount%>
									</h4>
							</font></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>


	<p>
	<div class=container>
		<div class="row">
			<div class="d-flex ml-auto">
				<button class="btn btn-ligth"
					onclick="window.location.href='noodleShop.jsp'"
					style="margin: 5px;">繼續點餐</button>

				<button class="btn mr-auto"
					onclick="window.location.href='noodleShop.jsp'"
					style="margin: 5px;">結帳</button>
					<!-- coupon button -->>
				<input id="coupon" class="btn" type="button" data-toggle="modal"
		data-target="#couponModal" style="background-image: url(img/printable-coupons.jpg); width: 100px; height: 50px; margin: 5px;">
			</div>
		</div>
	</div>

	<!-- Button trigger modal -->
	

	<!-- Modal -->
	<div class="modal fade" id="couponModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">請選擇優惠卷</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<jsp:useBean id="couponSvc" class="com.couponhistory.model.CouponhistoryService"/>
				<div class="modal-body">
					<select>
<%-- 						<c:set var="memID" value="${memVO.mem_Id}"/> --%>
						<c:forEach var="CouponVO" items="${couponSvc.getCouponByMem(memVO.mem_Id)}">
							<option value="${CouponVO}">${CouponVO}
						</c:forEach>
					</select>
				</div>
				<div class="modal-footer">
				<form action="<%request.getContextPath()%>/front_end/shoppingCart/ShoppingServlet.do"></form>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">使用</button>
						<input name="action" value="findMemCoupon">
					<button type="button" class="btn btn-light">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/front_end/footer.jsp" flush="true" />
	<script>
	
	</script>
</body>

</html>
