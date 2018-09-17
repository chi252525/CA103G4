<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.custommeals.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CustommealsVO custommealsVO = (CustommealsVO) request.getAttribute("CustommealsVO"); //CustommealsServlet.java(Controller), 存入req的custommealsVO物件
%>

<html>
<head>
<title>餐點資料 - listOneCustommeals.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
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
	width: 600px;
	background-color: white;
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
</style>

</head>
<body bgcolor='white'>

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>自訂餐點資料 - ListOneCustommeals.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>自訂餐點編號</th>
		<th>會員編號</th>
		<th>自訂餐點名稱</th>
		<th>自訂餐點價格</th>
		<th>餐點圖片</th>
	</tr>
	<tr>
		<td><%=custommealsVO.getCustom_No()%></td>
		<td><%=custommealsVO.getMem_No()%></td>
		<td><%=custommealsVO.getCustom_Name()%></td>
		<td><%=custommealsVO.getCustom_Price()%></td>
		<td><img src="/CA103G4/DBGifReader?custom_No=${custommealsVO.getCustom_No()}" style="max-width: 200px; max-height: 200px;"></td>
		
	</tr>
</table>

</body>
</html>