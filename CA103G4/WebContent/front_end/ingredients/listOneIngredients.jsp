<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ingredients.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	IngredientsVO ingredientsVO = (IngredientsVO) request.getAttribute("ingredientsVO"); //IngredientsServlet.java(Controller), 存入req的ingredientsVO物件
%>

<html>
<head>
<title>餐點資料 - listOneIngredients.jsp</title>

<style>
  table#table-1 {
	background-color: #ffe66f;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: #fff8d7;
	margin-top: 5px;
	margin-bottom: 5px;
	
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  
  body{
	background-image:url("images/woodbackground3.png")
}

</style>


</head>
<body bgcolor='white'>

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>自訂餐點資料 - ListOneIngredients.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>食材編號</th>
		<th>食材類別編號</th>
		<th>食材名稱</th>
		<th>食材狀態</th>
		<th>食材兌換點數</th>
		<th>食材單位</th>
		<th>食材價格</th>
		<th>食材圖片</th>

	</tr>
	<tr>
<!-- 	由ingredientsVO去呼叫各個參數對應的方法，取得每個欄位在資料庫的值 -->
		<td><%=ingredientsVO.getingdt_Id()%></td>       
		<td><%=ingredientsVO.getingdtc_Id()%></td>
		<td><%=ingredientsVO.getingdt_Name()%></td>
		<td><%=ingredientsVO.getingdt_Status()%></td>
		<td><%=ingredientsVO.getingdt_Point()%></td>
		<td><%=ingredientsVO.getingdt_Unit()%></td>
		<td><%=ingredientsVO.getingdt_Price()%></td>
		<td><img src="/CA103G4/DBGifReader3?ingdt_Id=${ingredientsVO.getingdt_Id()}" style="max-width: 200px; max-height: 200px;"></td>
		
	</tr>
</table>

</body>
</html>
