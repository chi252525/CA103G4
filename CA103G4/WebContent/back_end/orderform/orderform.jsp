<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
<%
pageContext.setAttribute("empVO",request.getSession().getAttribute("empVO"));
%>


<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<meta charset="UTF-8">
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
	<input type="submit" value="+新增外送單" class="btn btn-dark" value="Submit Button"> 
	<input type="hidden" name="action" value="selectOrd">
	</FORM>
	
	<br>
	<%-- 以下是複合查詢 --%>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/orderform/orderform.do" name="First"  class="form-inline" role="form">
		
		<div class="form-row align-items-center">
		
			<div class="input-group-prepend" style=" margin-right:70px;">
				 <span class="input-group-text">訂單編號:</span>
				 <input type="text" name="deliv_no">
			</div>
			
			<div class="input-group-prepend" style=" margin-right:70px;">
			 <span class="input-group-text">員工姓名</span>
			 <select size="1" name="emp_no" class="form-control" id="exampleSelect1">
	 			<option  value="">
				<c:forEach var="empVO" items="${empSvc.getOutEmpByBranchNo(empVO.branch_No)}">
				<option value="${empVO.emp_No}" ${(deliveryVO.emp_no==empVO.emp_No)? 'selected':'' } >${empVO.emp_Name}
				</c:forEach>
			</select>
			</div>
			
			<div class="input-group-prepend" style=" margin-right:70px;">
	  			<span class="input-group-text">派送單狀態:</span>
	  			<input type="text" name="deliv_status">
	  		</div>
	  		
			 <div class="input-group-prepend" style=" margin-right:30px;">
				 <input type="hidden" name="action" value="get_By_Key"> 
				 <input type="submit" value="開始搜尋" class="btn btn-dark" value="Submit Button">
			 </div>
		</div>
	</FORM>
	<br>































<%-- 從上面的div class="container"延伸下來，讓include也能排版 --%>
</div>







<%-- 背景 --%>
<jsp:include page="/back_end/PostFooter.jsp" flush="true"/>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>



</body>

</html>