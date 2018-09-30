<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>


<%
    MenuService menuSvc = new MenuService();
    List<MenuVO> list = menuSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>listAllMenu2.jsp</title>

<style>
  table#table-1 {
	background-color: rgba(255, 255, 255, 0.45);
/*     border: 2px solid black; */
	border-radius: 15px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: white;
    display: inline;
  }
</style>

<style>
  table {
    
	max-width: 1280px;
	background-color: rgba(255, 255, 255, 0.45);
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:320px;
	font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
    font-size: 20;
  }
  table, th, td {
/*     border: 2px solid rgba(255, 255, 255, 0.8); */
    border-radius: 15px;
    text-align: left;
    font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
  }
  th, td {
    padding: 5px;
    text-align: left;
    font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
  }
  @import url(//fonts.googleapis.com/earlyaccess/notosanstc.css);
  body{background-image:url("images/woodbackground3.png");}

</style>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" "<%=request.getContextPath()%>/front_end/menu/css/theme.css" type="text/css">
  


</head>
<body bgcolor='white'>

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>
<img src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg" width="100%" height="" alt="banner">

<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>listAllMenu.jsp</h3> -->
<!-- 		 <h4><a href="select_page.jsp">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>



<div class="h-100 py-5">
	<div class="container">
		<div class="row">
			<div class="col-md-12 mt-3">
				<%@ include file="page1.file"%><br>
	
			</div>
				<c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"><br>
				
				        <div class="col-md-3">
				          <div class="card" style="background-color:rgba(255,255,255,0.45); margin-bottom:20px;">
				
				            <img class="card-img-top" 
				            	src="<%=request.getContextPath()%>/menu/menushowimage.do?menu_No=${menuVO.getMenu_No()}"
				            
				            	alt="Card image cap" style="margin-top:20px;">
				            	
				            <div class="card-body">
				              <h5 class="card-title">${menuSvc.getOneMenu(menuVO.menu_No).menu_Id} ${menuVO.menu_Id}
				                <br> </h5>
				              <h5 class="card-title">$${menuSvc.getOneMenu(menuVO.menu_No).menu_Price} ${menuVO.menu_Price}</h5>
				              <p class="card-text" style="height:72px;">${menuSvc.getOneMenu(menuVO.menu_No).menu_Intro} ${menuVO.menu_Intro}</p>
				              <a href="#" class="btn btn-primary" style="background-color:#dc3545; border-color:#dc3545; margin-left:60px;">我要訂餐</a>
				            </div>
				          </div>
				        </div>
				        
				
				</c:forEach>
				
			<div class="col-md-12 mt-3">
				<%@ include file="page2.file"%>  
  			</div>
    	</div>
	</div>
</div>


  
  
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>






<!-- <table> -->

<%-- 	<%@ include file="page1.file" %>  --%>
<%-- 	<c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
		
<!-- 		<tr> -->
			
<%-- 			<td rowspan="3"><img src="/CA103G4/DBGifReader?menu_No=${menuVO.getMenu_No()}" style="max-width: 200px; max-height: 200px;"></td> --%>
<%-- 			<td>${menuVO.getMenu_Id()}</td><br> --%>
<%-- 			<td>${menuVO.getMenu_Price()}</td><br> --%>
<%-- 			<td>${menuVO.getMenu_Intro()}</td> --%>
			
<!-- 		</tr> -->
		
<%-- 	</c:forEach> --%>
<!-- </table> -->
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>