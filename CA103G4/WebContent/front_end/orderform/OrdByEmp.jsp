<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%><%-- s分頁 --%>
<%@ page import="com.orderinvoice.model.*"%>



<!DOCTYPE html>
<html>

<jsp:include page="/front_end/header.jsp" flush="true" />
<img src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg" width="100%" height="" alt="">
<head>
<meta charset="UTF-8">
<!-- font aewsome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.0/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/v4-shims.css">
<!-- Bootsraps-->
<script src="https://code.jquery.com/jquery-3.2.1.min.js "></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
<!-- datatable-->

<!--credit card-->
<script src="js/card.js"></script>
<link rel="stylesheet" href="css/card.css">
<!-- address choosing -->
<script src="<%=request.getContextPath()%>/front_end/member/js/selectaddress.js"></script>

<title>listbymem</title>

<style type="text/css">
 
   .ttp{
		margin-top: 10%;
	}

	.amrs{
		width: 1200px;
		height: 60px;
		background-color: #FFDDAA;
		margin: auto; 
		margin-top: 0%;
	}
	
	.tes{
		width: 600px;
		height: 250px;
	}

</style>

</head>
<body class="shadow-lg w-100" background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png" width="100%">
<%-- 背景 --%>
<jsp:include page="/back_end/PostHeader.jsp" flush="true"/>
	
	<div class="container">
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
				<c:forEach var="empVO" items="${empSvc.all}">
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
	</div>




<%-- 背景 --%>
<jsp:include page="/back_end/PostFooter.jsp" flush="true"/>
</body>

</html>