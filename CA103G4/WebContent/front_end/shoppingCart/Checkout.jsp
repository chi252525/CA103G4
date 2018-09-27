<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* , com.menu.model.*"%>
<html>
<head>
<title>Mode II 範例程式 - Checkout.jsp</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/ShoppingCart.css">
<style>
table, th, td {
	border: 1px solid black;
	text-align: center;
}
</style>
</head>
<body>
	<img src="images/tomcat.gif">
	<font size="+3">網路書店 - 結帳：（Checkout.jsp）</font>
	<hr>
	<p>
	<table id="table-1" style="margin: auto;">
		<tr>
			<th width="200">餐點名稱</th>
			<th width="100">價格</th>
			<th width="100">數量</th>
			<th width="120"><h3>總價</h3></th>
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
			<td colspan="6" style="text-align: right;"><font size="+2">總金額：
					<h4>
						$<%=amount%></h4>
			</font></td>
		</tr>
	</table>


	<p>
		<a href="noodleShop.jsp"><font size="+1"> 是 否 繼 續 購 物</font></a>
</body>
</html>