<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.orderform.model.*"%>
  
<%
pageContext.setAttribute("empVO",request.getSession().getAttribute("empVO"));
%>

<%-- <jsp:useBean id="listEmps_ByCompositeQuery" scope="request" type="java.util.List<OrderformVO>" /> --%>


<jsp:useBean id="beSvc" scope="page" class="com.branch.model.BranchService" />
<jsp:useBean id="dsSvc" scope="page" class="com.orderinvoice.model.OrderinvoiceService" />
<jsp:useBean id="menuSvc" scope="page" class="com.menu.model.MenuService"/>
<jsp:useBean id="customSvc" scope="page" class="com.custommeals.model.CustommealsService"/>   

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

	<%-- s以下是新增外送派送單 --%>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do">
	<input type="submit" value="營收分析" class="btn btn-dark" value="Submit Button"> 
	<input type="hidden" name="action" value="selectOrd">
	</FORM>
	
	<br>
	
	<jsp:useBean id="deSvc" scope="page" class="com.desk.model.DeskService" />
	<%-- 以下是複合查詢 --%>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/orderform/orderform.do" class="form-inline" role="form" name="First">
		
		<div class="form-row align-items-center">
		
			<div class="input-group-prepend" style=" margin-right:70px;">
				 <span class="input-group-text">訂單編號:</span>
				 <input type="text" name="order_no">
			</div>
			
			<div class="input-group-prepend" style=" margin-right:70px;">
			 <span class="input-group-text">桌位編號</span>
			 <select size="1" name="dek_no" class="form-control" id="exampleSelect1">
	 			<option  value="">
				<c:forEach var="deskVO" items="${deSvc.getByBrano(empVO.branch_No)}">
				<option value="${deskVO.dek_no}">${(deskVO.dek_no).substring(7)}
				</c:forEach>
			</select>
			</div>
			
			<div class="input-group-prepend" style=" margin-right:70px;">
	  			<span class="input-group-text">訂單成立時間:</span>
	  			<input type="text" id="f_date1" name="hiredate">
	  		</div>
	  		
			 <div class="input-group-prepend" style=" margin-right:30px;">
				 <input type="hidden" name="action" value="listEmps_ByCompositeQuery"> 
				 <input type="submit" value="開始搜尋" class="btn btn-dark" value="Submit Button">
			 </div>
		</div>
	</FORM>
	<br>

</div>

<%
 if (request.getAttribute("listEmps_ByCompositeQuery") != null) {
%>
	<jsp:include page="ok.jsp" />
<%
 }
%>


<%-- 背景 --%>
<jsp:include page="/back_end/PostFooter.jsp" flush="true"/>

 <script src="https://code.jquery.com/jquery-3.2.1.min.js "></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<c:if test="${listEmps_ByCompositeQuery != null}">
<c:forEach var="ordVO" items="${listEmps_ByCompositeQuery}">

<div class="modal fade" id="Ooo${ordVO.order_no}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">

	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalCenterTitle">訂單明細</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      <c:forEach var="dsVO" items="${dsSvc.findByOrder_no(ordVO.order_no)}">
		      <c:if test="${dsVO.menu_no != null}">
			  	${menuSvc.getOneMenu(dsVO.menu_no).menu_Id}
			  </c:if>
		      <c:if test="${dsVO.custom_no != null}">
			  	${customSvc.getOneCustommeals(dsVO.custom_no).custom_Name}
			  </c:if>
			   <br>
		  </c:forEach>
	      </div>
	     <button type="button" class="btn" data-dismiss="modal" style="background-color: #A42D00">確認</button>
	    </div>
	  </div>
	</div>

</c:forEach>
</c:if>







</body>

<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>
 $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       //step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: new Date(),              // value:   new Date(),
		   //startDate:	        '2017/07/10',  // 起始日
           maxDate:              new Date()  // 去除今日(不含)之後
        });
</script>

    <%
    if (request.getAttribute("listEmps_ByCompositeQuery") == null) {
	%>
	<script>
		function myFunction() {
			document.getElementsByName("First")[0].submit();
		}

		$(document).ready(myFunction);
	</script>
	<%
		}	
	%>

</html>