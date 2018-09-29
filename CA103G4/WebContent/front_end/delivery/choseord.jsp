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

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<c:if test="${selDel.size() != 0}">
<form  METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do">
<table>
<tr>
<th>訂單編號</th>
<th>外送地址</th>
<th>請選擇</th>
</tr>
<c:forEach var="orderformVO" items="${selDel}">
<tr>
<td>${orderformVO.order_no}</td>
<td>${orderformVO.deliv_addres}</td>
<td>
<input type="checkbox" name="ordno" value="${orderformVO.order_no}"/>
</td>
</tr>
</c:forEach>
</table>
<input type="hidden" name="action" value="insert">
<input type="submit" value="新增外送派送單">
</form>
</c:if>

<c:if test="${selDel.size() == 0}">
目前尚未需要派送的訂單
</c:if>
<form  METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do">
<input type="hidden" name="action" value="get_By_Key">
<input type="submit" value="返回派送單">
</form>

</body>
</html>