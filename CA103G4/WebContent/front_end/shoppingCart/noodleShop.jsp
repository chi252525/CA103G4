<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>
<html>
<head>
<meta charset="UTF-8">

<title>竹風堂經典拉麵</title>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/ShoppingCart.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/v4-shims.css">

<!-- sweet alert2 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js"
	type="text/javascript"></script>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js "></script>
</head>
<body>




	<jsp:useBean id="menuSvc" scope="page"
		class="com.menu.model.MenuService" />

	<c:forEach var="menuVO" items="${menuSvc.all}">
		<c:if test="${menuVO.menu_Status == 1}">
			<form id="menuform" name="shoppingForm"
				action="<%=request.getContextPath()%>/front_end/shoppingCart/ShoppingServlet.do"
				method="POST">

				<table>
					<tr>
						<div>圖片</div>
					</tr>
					<tr>
						<div>
							<h3>${menuVO.menu_Id}</h3>
						</div>
					</tr>
					<tr>
						<div>餐點價格：${menuVO.menu_Price}</div>
					</tr>
					<tr>
						<div>餐點介紹：${menuVO.menu_Intro}</div>
					</tr>
					<tr>
						<div>
							數量：<input type="number" name="quantity" size="3" value=1
								style="width: 40px">
						</div>
					</tr>
					<tr>
						<div>
							<input id="buy" type="submit" class="submit" value="放入購物車">
						</div>
					</tr>
				</table>
				<input type="hidden" name="menuno" value="${menuVO.menu_No}">
				<input type="hidden" name="menuid" value="${menuVO.menu_Id}">
				<input type="hidden" name="type" value="${menuVO.menu_Type}">
				<input type="hidden" name="price" value="${menuVO.menu_Price}">
				<input type="hidden" name="status" value="${menuVO.menu_Status}">
				<input type="hidden" name="action" value="ADD">
			</form>
		</c:if>
	</c:forEach>
	<a href="Cart.jsp"><i class="fas fa-shopping-cart"
		style="font-size: 40px;"></i></a>

	<script>
// 		$(function() {
// 			$(".submit").click(function(){	
// 						swal("提示","操作成功","success");
// 					});
// 		});
		
// 		$(".swal2-confirm").click(function(){
// 			$("#menuform").submit();
// 		});
		
		</script>
</body>
</html>