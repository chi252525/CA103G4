<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>

<title>點餐.jsp</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/ShoppingCart.css">
</head>
<body>
	<img src="images/tomcat.gif">
	<font size="+3">網路書店：（EShop.jsp）</font>
	<hr>
	<table id="table-1">
		<tr>
			<th width="200">餐點內容</th>
			<th width="100">價格</th>
			<th width="100">數量</th>
			<th width="100">價格</th>
			<th width="120">小記</th>
			<th width="120"><img src="images/shopping-cart.png" width="45px"
				height="35px"></th>
		</tr>
	</table>

	<!--  
       第一種action寫法: <form name="shoppingForm" action="Shopping.html" method="POST">
       第二種action寫法: <form name="shoppingForm" action="/IBM_MVC/Shopping.html" method="POST">
       第三種action寫法: <form name="shoppingForm" action="<%=request.getContextPath()%>/Shopping.html" method="POST">
 -->
	<!-- 
       當某網頁可能成為被forward的網頁時, 此網頁內的所有html連結 , 如果採用相對路徑寫法時, 因為會被加上原先forward者的路徑
       在更複雜的MVC架構中, 上面第三種寫法, 先以request.getContextPath()方法, 先取得環境(Servlet Context)目錄路徑的寫法,
       將是最佳解決方案
 -->

	<form name="shoppingForm" action="Shopping.html" method="POST">
		<table>
			<tr>
				<td width="200"><div align="center">極致豚骨玉子麵</div></td>
				<td width="100"><div align="center">吳永志</div></td>
				<td width="100"><div align="center">基峰</div></td>
				<td width="100"><div align="center">600</div></td>
				<td width="120"><div align="center">
						數量：<input type="text" name="quantity" size="3" value=1>
					</div></td>
				<td width="120"><div align="center">
						<input type="submit" class="button" value="放入購物車">
					</div></td>
			</tr>
		</table>
		<input type="hidden" name="name" value="不一樣的養生法"> <input
			type="hidden" name="author" value="吳永志"> <input type="hidden"
			name="publisher" value="基峰"> <input type="hidden"
			name="price" value="600"> <input type="hidden" name="action"
			value="ADD">
	</form>

	<form name="shoppingForm" action="Shopping.html" method="POST">
		<table>
			<tr>
				<td width="200"><div align="center">紐西蘭牛肉拉麵</div></td>
				<td width="100"><div align="center">J.K 羅琳</div></td>
				<td width="100"><div align="center">皇冠</div></td>
				<td width="100"><div align="center">200</div></td>
				<td width="120"><div align="center">
						數量：<input type="text" name="quantity" size="3" value=1>
					</div></td>
				<td width="120"><div align="center">
						<input type="submit" class="button" value="放入購物車">
					</div></td>
			</tr>
		</table>
		<input type="hidden" name="name" value="哈利波特-神秘的魔法石"> <input
			type="hidden" name="author" value="J.K 羅琳"> <input
			type="hidden" name="publisher" value="皇冠"> <input
			type="hidden" name="price" value="200"> <input type="hidden"
			name="action" value="ADD">
	</form>

	<form name="shoppingForm" action="Shopping.html" method="POST">
		<table>
			<tr>
				<td width="200"><div align="center">地獄麻辣拉麵</div></td>
				<td width="100"><div align="center">蕭蓉蓉</div></td>
				<td width="100"><div align="center">平安</div></td>
				<td width="100"><div align="center">190</div></td>
				<td width="120"><div align="center">
						數量：<input type="text" name="quantity" size="3" value=1>
					</div></td>
				<td width="120"><div align="center">
						<input type="submit" class="button" value="放入購物車">
					</div></td>
			</tr>
		</table>
		<input type="hidden" name="name" value="麻辣女教師"> <input
			type="hidden" name="author" value="蕭蓉蓉"> <input type="hidden"
			name="publisher" value="平安"> <input type="hidden"
			name="price" value="190"> <input type="hidden" name="action"
			value="ADD">
	</form>

	<form name="shoppingForm" action="Shopping.html" method="POST">
		<table>
			<tr>
				<td width="200"><div align="center">虱目魚雞湯塩味拉麵</div></td>
				<td width="100"><div align="center">劉墉</div></td>
				<td width="100"><div align="center">水雲齋</div></td>
				<td width="100"><div align="center">180</div></td>
				<td width="120"><div align="center">
						數量：<input type="text" name="quantity" size="3" value=1>
					</div></td>
				<td width="120"><div align="center">
						<input type="submit" class="button" value="放入購物車">
					</div></td>
			</tr>
		</table>
		<input type="hidden" name="name" value="把話說到心窩"> <input
			type="hidden" name="author" value="劉墉"> <input type="hidden"
			name="publisher" value="水雲齋"> <input type="hidden"
			name="price" value="180"> <input type="hidden" name="action"
			value="ADD">
	</form>

	<form name="shoppingForm" action="Shopping.html" method="POST">
		<table>
			<tr>
				<td width="200"><div align="center">辣味刀削牛肉蔥花</div></td>
				<td width="100"><div align="center">高行健</div></td>
				<td width="100"><div align="center">聯經</div></td>
				<td width="100"><div align="center">300</div></td>
				<td width="120"><div align="center">
						數量：<input type="text" name="quantity" size="3" value=1>
					</div></td>
				<td width="120"><div align="center">
						<input type="submit" class="button" value="放入購物車">
					</div></td>
			</tr>
		</table>
		<input type="hidden" name="name" value="一個人聖經"> <input
			type="hidden" name="author" value="高行健"> <input type="hidden"
			name="publisher" value="聯經"> <input type="hidden"
			name="price" value="300"> <input type="hidden" name="action"
			value="ADD">
	</form>


	<p>
<%-- 		<jsp:include page="/front_end/shoppingCart/shoppingCart.jsp" --%>
<%-- 			flush="true" /> --%>
</body>
</html>