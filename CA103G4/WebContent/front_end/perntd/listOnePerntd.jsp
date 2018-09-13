<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.perntd.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  PerntdVO perntdVO = (PerntdVO) request.getAttribute("perntdVO"); //PerntdServlet.java(Controller), 存入req的perntdVO物件
%>

<html>
<head>
<title>會員個人通知資料 - listOneEmp.jsp</title>

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
		 <h3>會員個人通知資料 - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>個人通知流水號</th>
		<th>會員編號</th>
		<th>系統通知編號</th>
		<th>通知內容</th>
		<th>通知建立時間</th>
	</tr>
	<tr>
		<td><%=perntdVO.getPerntd_No()%></td>
		<td><%=perntdVO.getMem_No()%></td>
		<td><%=perntdVO.getNt_No()%></td>
		<td><%=perntdVO.getPerntd_Cont()%></td>
		<td><%=perntdVO.getPerntd_Date()%></td>
	</tr>
</table>

</body>
</html>