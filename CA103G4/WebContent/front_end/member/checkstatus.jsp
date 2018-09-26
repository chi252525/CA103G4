<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
//MemServlet.java (Concroller) 存入req的memVO物件 (包括輸入資料錯誤時的memVO物件)
	
session.getAttribute("mem_Name");

%>
<!DOCTYPE html>
<html >
    <head>
		<!-- Mobile Specific Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- Favicon-->
		<link rel="shortcut icon" href="">
		<!-- Meta Description -->
		<meta name="description" content="">
		<!-- Meta Keyword -->
		<meta name="keywords" content="">
		<!-- Site Title -->
		<title>check_status</title> 
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <!--your  CSS ============================================= -->
       

       <!--Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/member/css/checkstatus.css">
    </head>
    

 <body>

		<img
			src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg"
			width="100%" height="" alt="">
<!--your html   start==================================================================================-->
<div class="container rounded col-3" style="padding-bottom:170px;" >
	<form method="post" action="member.do" enctype="multipart/form-data" >
	
	    <table id="back">
	    	<tr>
	    		<c:if test="${not empty errorMsgs}">
					<font style="color:black;" >請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:black">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
	    	</tr>
	        <tr>
	        	<td class="tb1"><span align="center">Hi！${mem_Name}，歡迎你加入竹風堂。<br>麻煩幫我輸入驗證碼</span></td>
	        </tr>
	        <tr>
	            <td class="tdbtn">
	            <input type=text value="輸入驗證碼"  name="authCode">
	            <input type="hidden" name="action" value="checkstatus">
	            <input type="submit" class="btn btn-warning btn-sm " align="center" value="驗證">           
	            </td>
	        </tr>
	    </table>
	    
    </form>
</div>    

<!--以下勿動-->
    <!-- Footer -->
<%-- <jsp:include page="/front_end/footer.jsp"></jsp:include> --%>
	 <!-- Footer -->		
		</body>
	</html>
