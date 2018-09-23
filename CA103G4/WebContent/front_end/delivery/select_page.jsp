<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.delivery.model.*"%>

<jsp:useBean id="get_By_Key" scope="session" type="java.util.List<DeliveryVO>" />

<html>
<head>
<meta charset="UTF-8">
<title>Delivery: Home</title>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

#a {
	display: inline;
}
</style>

</head>
<body bgcolor='white'>
	<%--
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do"
		id="a">
		<input type="submit" value="全部外送單"><input type="hidden"
			name="action" value="listAllDelivery">
	</FORM>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do"
		id="a">
		<input type="submit" value="未完成的外送單"> <input type="hidden"
			name="deptno" value="10"> <input type="hidden" name="action"
			value="listNotOk">
	</FORM>
--%>


	<%-- s以下是複合查詢列 --%>
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do"
		id="a" name="First">
		派送單編號:<input type="text" name="deliv_no"> 員工編號: <input
			type="text" name="emp_no"> 派送單狀態:<input type="text"
			name="deliv_status"> <input type="hidden" name="action"
			value="get_By_Key"> <input type="submit" value="開始搜尋">
	</FORM>
	<br>
	<%-- s以下是新增外送派送單 --%>
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do"
		id="b">
		<input type="text" name="branch_no"> <input type="submit"
			value="+新增外送派送單"> <input type="hidden" name="action"
			value="insert">
	</FORM>
	<br>
	


	<%-- 
	<%
		if (request.getAttribute("listNotOk") == null && request.getAttribute("listAllDelivery") == null && request.getAttribute("errorMsgs") == null) {
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
--%>

	<%-- 
	<%
		if (request.getAttribute("listAllDelivery") != null) {
			session.removeAttribute("get_By_Key");
	%>
	<jsp:include page="listAllDelivery.jsp" />
	<%
		}
	%>
--%>

	<%-- 
	<%
		if (request.getAttribute("listNotOk") != null) {
			session.removeAttribute("get_By_Key");
	%>
	<jsp:include page="listNotOk.jsp" />
	<%
		}
	%>
--%>

	<%-- s查詢出來的表格 --%>
	<%
		if (session.getAttribute("get_By_Key") != null) {
	%>
	<jsp:include page="listByKey.jsp" />
	<%
		}
	%>

	<%-- s當做出某件事情時會刷新表格，一開始會跑出查詢所有的表格 --%>
	<%
		if ((session.getAttribute("get_By_Key") == null && request.getAttribute("errorMsgs") == null)||request.getAttribute("insert") != null || request.getAttribute("update") != null) {
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
	

</body>
</html>