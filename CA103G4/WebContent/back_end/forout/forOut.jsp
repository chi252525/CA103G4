<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%><%-- s分頁 --%>
<%@ page import="com.orderform.model.*"%>
<%@ page import="com.orderinvoice.model.*"%>

<%
OrderformService orderfoemSvc = new OrderformService();
List<OrderformVO> list = orderfoemSvc.getForOut();
pageContext.setAttribute("forOut",list);
%>

<jsp:useBean id="ordinSvc" scope="page" class="com.orderinvoice.model.OrderinvoiceService"/>
<jsp:useBean id="menuSvc" scope="page" class="com.menu.model.MenuService"/>
<jsp:useBean id="customSvc" scope="page" class="com.custommeals.model.CustommealsService"/>        

  


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
		<th style="text-align: center">訂單編號</th>
		<th style="text-align: center">訂單類型</th>
		<th style="text-align: center">桌位</th>
		<th style="text-align: center">出餐狀態</th>
	</tr>
<%-- 分頁 --%>
<%@ include file="page1.file" %>
<%-- 員工編號，一開始為下拉式選單和按鈕，點擊後派送單狀態會變為2，成為按鈕，選擇後選單和按鈕會消失 --%><%-- s分頁 --%>
<c:forEach var="orderformVO" items="${forOut}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

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
	剩餘出餐數量:${ordinSvc.getByOrder_no(orderformVO.order_no)}
	</td>	
	
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
	      <form  METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/orderinvoice/orderinvoice.do">
	      <div class="modal-body">
	      
	      <c:forEach var="ordinVO" items="${ordinSvc.findByOrder_no(orderformVO.order_no)}">
		      <div class="form-check-inline">
		 		  <label class="form-check-label">
			    	 <input type="checkbox" name="out" class="form-check-input" value="${(ordinVO.menu_no == mull)? ordinVO.custom_no:ordinVO.menu_no}">${(ordinVO.menu_no == mull)? ordinVO.custom_no:ordinVO.menu_no}    
					    	 														   ${(ordinVO.menu_no == mull)? (customSvc.getOneCustommeals(ordinVO.custom_no)).custom_Name:menuSvc.getOneMenu(ordinVO.menu_no).menu_Id} 
					      														      數量: ${(ordinVO.menu_no == mull)? ordinVO.custom_nu:ordinVO.menu_nu}
			      </label>
			  </div>
	     	 <br>
	      </c:forEach>
	      
	      </div>
	      <div class="modal-footer">
	        <button type="submit" id="myButton" class="btn" style="background-color: #FF7F50">確認出餐</button>
	        
	        <input type="hidden" name="action" value="updateForOut"/>
	        <input type="hidden" name="odNo" value="${orderformVO.order_no}"/>
	      </div>
	      </form>
	      	<button type="button" class="btn" data-dismiss="modal" style="background-color: #A42D00">取消</button>
	    </div>
	  </div>
	</div>
</c:forEach>



<%--當某個訂單餐點出完時跑出提示--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">完成餐點</h4>
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
      </div>
      <div class="modal-body">
		訂單<%= session.getAttribute("OK") %>的餐點已經完成出餐<%-- 將此送進controll查詢單筆訂單(內用) --%>
      <br>
      <c:if test="<%= (int)session.getAttribute(\"TY\") == 0%>">
      	訂單類型:內用
      </c:if>
      <c:if test="<%= (int)session.getAttribute(\"TY\") == 1%>">
      	訂單類型:外帶
      </c:if>
      <c:if test="<%= (int)session.getAttribute(\"TY\") == 2%>">
      	訂單類型:外送
      </c:if>
      </div>
      <div class="modal-footer">

      	<a class="btn btn-outline-dark" href="<%=request.getContextPath()%>/back_end/delivery/select_page.jsp" role="button">查看外送</a>
      	<a class="btn btn-outline-dark" href="<%=request.getContextPath()%>/back_end/orderform/orderform.jsp" role="button">查看訂單</a>
  			    
      </div>
    </div>
  </div>
</div>

<c:if test="<%= session.getAttribute(\"OK\") != null %>">
<% session.removeAttribute("OK"); %>
 <script>
 $(function(){
     $('#myModal').modal({
     show:true,
     backdrop:true
     })
 });
 </script>

</c:if>


<%--  <script> -->
// $(document).ready(function(){
// 	$(#myButton).prop('disabled', true);
// 	var check = $("[name=out]");
// 	if (check.checked) {
// 		$(#myButton).prop('disabled', false);
// 	}
// });
<!-- </script> -->
--%>

</body>

</html>