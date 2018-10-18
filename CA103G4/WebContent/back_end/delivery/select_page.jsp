<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.delivery.model.*"%>

<%
pageContext.setAttribute("empVO",request.getSession().getAttribute("empVO"));
%>

<%-- 
<%
  response.setHeader("Cache-Control","no-store"); //HTTP 1.1
  response.setHeader("Pragma","no-cache");        //HTTP 1.0
  response.setDateHeader ("Expires", 0);
%>
--%>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/res/img/icon.png" />

<title>Delivery: Home</title>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

</head>
<body bgcolor='white'>


<%-- 背景 --%>
<jsp:include page="/back_end/PostHeader.jsp" flush="true"/>

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

	<jsp:useBean id="empSvc" scope="page" class="com.employee.model.EmpService" />
<!-- default -->
	<%-- s以下是複合查詢列 --%>
<div class="container">

	<%-- s以下是新增外送派送單 --%>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do">
	<input type="submit" value="+新增外送單" class="btn btn-dark" value="Submit Button"> 
	<input type="hidden" name="action" value="selectOrd">
	</FORM>
	
	<br>
	<%-- 以下是複合查詢 --%>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do" name="First"  class="form-inline" role="form">
		
		<div class="form-row align-items-center">
		
			<div class="input-group-prepend" style=" margin-right:70px;">
				 <span class="input-group-text">派送單編號:</span>
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

	<%-- 查詢出來的表格 --%>
	<%
		if (session.getAttribute("get_By_Key") != null) {
	%>
	<jsp:include page="listByKey.jsp" />
	<%
		}
	%>

	<%-- 當做出某件事情時會刷新表格，一開始會跑出查詢所有的表格 --%>
	<%
		if ((session.getAttribute("get_By_Key") == null && request.getAttribute("errorMsgs") == null) || request.getAttribute("insert") != null) {
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
<%-- 從上面的div class="container"延伸下來，讓include也能排版 --%>
</div>

<%-- 背景 --%>
<jsp:include page="/back_end/PostFooter.jsp" flush="true"/>

 <script src="https://code.jquery.com/jquery-3.2.1.min.js "></script>>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>