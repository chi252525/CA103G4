<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderform.model.*"%>
<%@ page import="com.delivery.model.*"%>

<jsp:useBean id="ordSvc" scope="page" class="com.orderform.model.OrderformService" />

<html>
<head>
<meta charset="UTF-8">
<title>chose_order</title>
</head>
<body>
<table>
<tr>
<th>訂單編號</th>
<th>外送地址</th>
<th>請選擇</th>
</tr>
<c:forEach var="orderformVO" items="${selDel}">

</c:forEach>
</table>




</body>
</html>