<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.storedrecord.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  StoredrecordVO SrVO = (StoredrecordVO) request.getAttribute("srVO"); //EmpServlet.java(Concroller), 存入req的SrVO物件
%>

<html>
<head>
<title>儲值歷史紀錄 - listOneEmp.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>儲值歷史紀錄 - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/arrow-back-icon.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>儲值流水單號</th>
		<th>會員編號</th>
		<th>儲值日期</th>
		<th>儲值點數</th>
		<th>回饋紅利點數(竹幣)</th>
		<th>儲值完成狀態</th>
	</tr>
	<tr>
		<td><%=SrVO.getStor_No()%></td>
		<td><%=SrVO.getMem_No()%></td>
		<td><%=SrVO.getStor_Date()%></td>
		<td><%=SrVO.getStor_Point()%></td>
		<td><%=SrVO.getStor_Point()%></td>
		<td><%=SrVO.getStor_Status()%></td>
	</tr>
</table>

</body>
</html>