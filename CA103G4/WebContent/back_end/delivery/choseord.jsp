<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderform.model.*"%>
<%@ page import="com.delivery.model.*"%>

<jsp:useBean id="ordSvc" scope="page" class="com.orderform.model.OrderformService" />

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>chose_order</title>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

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


<%-- 選擇需要派送的訂單 --%>
<c:if test="${selDel.size() != 0}">
<form  METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do">


<table class="table table-hover">
<tr>
<th>訂單編號</th>
<th>外送地址</th>
<th>
<div class="row justify-content-md-center">
<div class="col-md-auto">
請選擇
</div>
</div>
</th>
</tr>


<c:forEach var="orderformVO" items="${selDel}">

<tr>
<td><div class="form-check form-check-inline"><label class="form-check-label" for="test${orderformVO.order_no}">${orderformVO.order_no}</label></div></td>
<td><div class="form-check form-check-inline"><label class="form-check-label" for="test${orderformVO.order_no}">${orderformVO.deliv_addres}</label></div></td>
<td>
<div class="row justify-content-md-center">
<div class="col-md-auto">
<input type="checkbox" name="ordno" value="${orderformVO.order_no}" class="form-check-input" id="test${orderformVO.order_no}"/>
</div>
</div>
</td>
</tr>

</c:forEach>
</table>

<div class="row justify-content-md-center">
	<div class="col-md-auto">
	
		<div class="input-group-prepend" style=" margin-right:30px;">
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="新增派送單" class="btn btn-danger" value="Submit Button">
		</div>
	
	</div>
</div>

</form>
</c:if>


<c:if test="${selDel.size() == 0}">
目前尚無需要派送的訂單
</c:if>
<div class="row justify-content-md-center">
	<div class="col-md-auto">
	
		<form  METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do">
		
			<div class="input-group-prepend" style=" margin-right:30px;">
				<input type="hidden" name="action" value="get_By_Key">
				<input type="submit" value="返回派送單" class="btn btn-dark" value="Submit Button">
			</div>
		
		</form>
	
	</div>
</div>


<%-- 背景 --%>
<jsp:include page="/back_end/PostFooter.jsp" flush="true"/>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>