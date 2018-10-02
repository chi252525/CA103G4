<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
	<title>emplogin</title>
	        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <!-- linearicons CSS -->
        <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
        <!-- h&f CSS -->
        <!--your  CSS ============================================= -->

        
       <!--Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<style type="text/css">
#back {
background-color: rgba(225,225,225,0.45);
border-radius: 3rem;
box-shadow:3px 3px 5px 6px #cccccc;
}

body {
	font-family: Montserrat, Arial, "微軟正黑體", "Microsoft JhengHei" !important;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}
</style>   
</head>
<body>
<jsp:include page="/back_end/PostHeader.jsp"></jsp:include> 

<div class="container col-3 rounded " style="margin-top:200px;padding-top: 10px" id="back" >
<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:black">${message}</li>
			</c:forEach>
		</ul>
</c:if>
	<form action="emp.do" method="POST" enctype="multipart/form-data">

		<div class="form-group">
			<label for="emp_Acnum">員工帳號</label>
			<input type="text" class="form-control form-control-sm" id="emp_Acnum" name="emp_Acnum" >
		</div>
		<div class="form-group">
			<label for="emp_Psw">員工密碼</label>
			<input type="password" class="form-control form-control-sm" id="emp_Psw" name="emp_Psw" >
		</div>

		<div style="text-align: center;" >
			<input type="hidden" name="action" value="login">	
			<input type="submit" class="btn btn-warning" value="登入" style="margin-bottom:10px" >
		</div>
	</form>	
</div>
<jsp:include page="/back_end/PostFooter.jsp"></jsp:include>	
</body>
</html>