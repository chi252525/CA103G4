<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="BIG5">
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

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do"
		id="a">
		<input type="submit" value="全部外送單"><input type="hidden"
			name="action" value="listAllDelivery">
	</FORM>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do"
		id="a" name="First">
		<input type="submit" value="未完成的外送單"> <input type="hidden"
			name="deptno" value="10"> <input type="hidden" name="action"
			value="listNotOk">
	</FORM>
	
	<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do"
		id="a">
派送單編號:<input type="text" name="deliv_no"> 
  員工編號: <input type="text" name="emp_no"> 
派送單狀態:<input type="text" name="deliv_status"> 
		<input type="hidden" name="action" value="get_By_Key"> 
		<input type="submit" value="開始搜尋">
	</FORM>



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


	<%
		if (request.getAttribute("listAllDelivery") != null) {
	%>
	<jsp:include page="listAllDelivery.jsp" />
	<%
		}
	%>


	<%
		if (request.getAttribute("listNotOk") != null) {
	%>
	<jsp:include page="listNotOk.jsp" />
	<%
		}
	%>

	<%
		if (request.getAttribute("get_By_Key") != null) {
	%>
	<jsp:include page="listByKey.jsp" />
	<%
		}
	%>



</body>
</html>