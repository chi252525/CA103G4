<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* , com.menu.model.*"%>
<html>
<head>
<title>Mode II 範例程式 - Cart.jsp</title>
<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="<%=request.getContextPath()%>/front_end/shoppingCart/css/shoppingCart.css"> --%>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/v4-shims.css">
<style>
table, th, td {
	border: 1px solid black;
	text-align: center;
}
</style>
</head>
<body>
	<br>
	<%
		@SuppressWarnings("unchecked")
		Vector<MenuVO> buylist = (Vector<MenuVO>) session.getAttribute("shoppingcart");
		System.out.println(request.getContextPath() + "/front_end/shoppingCart/css/shoppingCart.css");
	%>
	<%
		if (buylist != null && (buylist.size() > 0)) {
	%>

	<img src="images/tomcat.gif">
	<font size="+3">目前購物車的內容如下：（Cart.jsp）</font>

	<table id="table-1" >
		<tr>
			<th width="200">餐點名稱</th>
			<th width="100">價格</th>
			<th width="100">數量</th>
			<th width="100">總計</th>
			
			<th width="120"><img src="images/view-cart.png"></th>
		</tr>
	</table>
	<table>

		<%
			for (int index = 0; index < buylist.size(); index++) {
					MenuVO menuVO = buylist.get(index);
		%>
		<tr>
			<td width="200"><%=menuVO.getMenu_Id()%></td>
			<td width="100"><%=menuVO.getMenu_Price()%></td>
			<td width="100"><%=menuVO.getMenu_quantity()%></td>
			<td width="100"><%=menuVO.getMenu_Price() * menuVO.getMenu_quantity()%></td>
			<td width="120">
				<form name="deleteForm" action="ShoppingServlet.do" method="POST">
					<input type="hidden" name="action" value="DELETE"> 
					<input type="hidden" name="del" value="<%=index%>">
					<button type="submit" value="刪除"><i class="fa fa-trash" style="font-size: 25px;color: firebrick"></i></button>
				</form>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<p>
	<form name="checkoutForm" action="ShoppingServlet.do" method="POST">
		<input type="hidden" name="action" value="CHECKOUT"> <input
			type="submit" value="付款結帳" class="button">
	</form>
	<form name="checkoutForm" action="noodleShop.jsp" method="POST">
		<input type="hidden" name="action" value="CHECKOUT"> <input
			type="submit" value="繼續選購" class="button">
	</form>
	<%
		}
	%>
</body>
</html>