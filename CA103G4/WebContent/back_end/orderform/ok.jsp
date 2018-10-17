<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.orderform.model.*"%>
  
<%
pageContext.setAttribute("empVO",request.getSession().getAttribute("empVO"));
%>

<jsp:useBean id="listEmps_ByCompositeQuery" scope="request" type="java.util.List<OrderformVO>" />


<jsp:useBean id="beSvc" scope="page" class="com.branch.model.BranchService" />

<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>orderform</title>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>


<style>
  table#table-1 {
	background-color: orange;
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
<!-- https://www.toodoo.com/db/color.html 色碼表-->
<style type="text/css">

	.btncolor{
		background-color: #FF7F50;
	}
</style>

</head>
<body bgcolor='white'>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="container">

	
	<table class="table table-hover">
	<tr>
		<th>訂單編號</th>
		<th>桌號</th>
		<th>會員編號</th>
		<th>派送單編號</th>
		<th>訂單類型</th>
		<th>訂單金額</th>
		<th>外送地址</th>
		<th>訂單成立時間</th>
		<th>付款狀態</th>
		<th>訂單狀態</th>
	</tr>
	
	<%@ include file="pages/page1_ByCompositeQuery.file" %>

	<c:forEach var="ordVO" items="${listEmps_ByCompositeQuery}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<tr>
	<%-- 訂單  --%>
	<td>${ordVO.order_no}</td>
	
	<%-- 桌位  --%>
	<c:if test="${ordVO.dek_no != null}">
	<td>${(ordVO.dek_no).substring(7)}</td>
	</c:if>
	<c:if test="${ordVO.dek_no == null}">
	<td>無</td>
	</c:if>

	<c:if test="${ordVO.mem_no != null}">
	<td>${ordVO.mem_no}</td>
	</c:if>
	<c:if test="${ordVO.mem_no == null}">
	<td>無</td>
	</c:if>
	
	<%-- 外送單  --%>
	<c:if test="${ordVO.deliv_no != null}">
	<td>${ordVO.deliv_no}</td>
	</c:if>
	<c:if test="${ordVO.deliv_no == null and ordVO.order_type == 2}">
	<td>尚未派送</td>
	</c:if>
	<c:if test="${ordVO.deliv_no == null and ordVO.order_type != 2}">
	<td>無需派送</td>
	</c:if>
	
	<%-- 訂單類型  --%>
	<c:if test="${ordVO.order_type == 0}">
	<td>內用</td>
	</c:if>
	<c:if test="${ordVO.order_type == 1}">
	<td>外帶</td>
	</c:if>
	<c:if test="${ordVO.order_type == 2}">
	<td>外送</td>
	</c:if>
	
	<td>${ordVO.order_price}元</td>
	
	<c:if test="${ordVO.deliv_addres != null}">
	<td>${ordVO.deliv_addres}</td>
	</c:if>
	<c:if test="${ordVO.deliv_addres == null}">
	<td>無外送地址</td>
	</c:if>
	
	<td>${ordVO.order_date}</td>
	
	
	<c:if test="${ordVO.order_pstatus == 1}">
	<td>未付款</td>
	</c:if>
	<c:if test="${ordVO.order_pstatus == 2}">
	<td>現金付款</td>
	</c:if>
	<c:if test="${ordVO.order_pstatus == 3}">
	<td>信用卡付款</td>
	</c:if>
	<c:if test="${ordVO.order_pstatus == 4}">
	<td>竹幣付款</td>
	</c:if>
	
	<td>
	<c:if test="${ordVO.order_status == 1}">
	處理中
	</c:if>
	
	
	<c:if test="${ordVO.order_status == 2 and ordVO.order_type == 2}">
	已出餐
	</c:if>
	<c:if test="${ordVO.order_status == 2 and ordVO.order_type != 2}">
	<c:if test="${ordVO.order_pstatus == 1}">
	<form METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/orderform/orderform.do">
	<input type="hidden" name="action" value="ordOk">
	<input type="hidden" name="ord_no" value="${ordVO.order_no}">
	<input type="submit" value="確認完成">
	</form>	
	</c:if>
	</c:if>
	
	<c:if test="${ordVO.order_status == 3}">
	已完成
	</c:if>
	</td>
	
	</tr>
</c:forEach>
	</table>
	<%@ include file="pages/page2_ByCompositeQuery.file" %>
<%-- 從上面的div class="container"延伸下來，讓include也能排版 --%>
</div>

</body>

</html>