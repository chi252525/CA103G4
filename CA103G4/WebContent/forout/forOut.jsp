<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%><%-- s分頁 --%>
<%@ page import="com.orderform.model.*"%>

<%
OrderformService orderfoemSvc = new OrderformService();
List<OrderformVO> list = orderfoemSvc.getForOut();
pageContext.setAttribute("forOut",list);
%>

<%! int count; %>

<jsp:useBean id="ordinSvc" scope="page" class="com.orderinvoice.model.OrderinvoiceService"/>

<%-- s分頁 --%>
<jsp:useBean id="forOut" scope="page" type="java.util.List<OrderformVO>" />

<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>listbymem</title>
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




<%-- 背景 --%>
<jsp:include page="/back_end/PostHeader.jsp" flush="true"/>

<div class="container">

<table class="table table-hover">
	<tr>
		<th style="text-align: center">訂單編號</th>
		<th style="text-align: center">訂單類型</th>
		<th style="text-align: center">桌位流水號</th>
		<th style="text-align: center">訂單狀態</th>
	</tr>
<%-- 分頁 --%>
<%@ include file="page1.file" %>
<%-- 員工編號，一開始為下拉式選單和按鈕，點擊後派送單狀態會變為2，成為按鈕，選擇後選單和按鈕會消失 --%><%-- s分頁 --%>
<c:forEach var="orderformVO" items="${forOut}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

<c:forEach var="ordinVO" items="${ordinSvc.findByOrder_no(orderformVO.order_no)}">
<%count++;%>
</c:forEach>

<tr>

	<td>
	<button type="button" class="btn btncolor" data-toggle="modal" data-target="#Joo${orderformVO.order_no}">
  	${orderformVO.order_no}
	</button>
	</td>
	
	<c:if test="${orderformVO.order_type == 0}">
	<td>內用</td>		
	</c:if>

	<c:if test="${orderformVO.order_type == 1}">
	<td>外帶</td>		
	</c:if>

	<c:if test="${orderformVO.order_type == 2}">
	<td>外送</td>		
	</c:if>

	<c:if test="${orderformVO.dek_no == null}">
	<td>無桌號</td>
	</c:if>

	<c:if test="${orderformVO.dek_no != null}">
	<td>${orderformVO.dek_no}</td>
	</c:if>

	<td>
	剩餘出餐數量:<%= count %>
	</td>	
	
	<% count = 0; %>
	
	</tr>
	

</c:forEach>
	
</table>
<%-- 分頁 --%>
<%@ include file="page2.file" %>

</div>




<%-- 背景 --%>
<jsp:include page="/back_end/PostFooter.jsp" flush="true"/>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


<c:forEach var="orderformVO" items="${forOut}">
<!-- Modal -->
	<div class="modal fade" id="Joo${orderformVO.order_no}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalCenterTitle">選取完成的餐點</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <form  METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do">
	      <div class="modal-body">
	      
	      
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary">確認出餐</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
	      </div>
	      </form>
	    </div>
	  </div>
	</div>
</c:forEach>


</body>

</html>