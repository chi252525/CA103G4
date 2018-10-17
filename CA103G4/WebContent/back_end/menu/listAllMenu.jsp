<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>


<%
    MenuService menuSvc = new MenuService();
    List<MenuVO> list = menuSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/menu/css/theme.css" />
  
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>



<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"> 

<html>
<head>
<title>竹風堂後台管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/res/img/icon.png" />

<style>

body {
	font-family: Montserrat, Arial, "微軟正黑體", "Microsoft JhengHei" !important;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}

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
    
	width: 1600px;
	background-color: rgba(255, 255, 255, 0.45);
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:150px;
/* 	font-family: 'Noto Sans TC', sans-serif; */
    font-weight: 600;
    font-size: 20;
  }
  table, th, td {
/*     border: 2px solid rgba(255, 255, 255, 0.8); */
    border-radius: 15px;
    text-align: center;
/*     font-family: 'Noto Sans TC', sans-serif; */
    font-weight: 600;
  }
  th, td {
    padding: 5px;
    text-align: center;
/*     font-family: 'Noto Sans TC', sans-serif; */
    font-weight: 600;
  }
/*   @import url(//fonts.googleapis.com/earlyaccess/notosanstc.css); */
/*   body{background-image:url("images/woodbackground3.png");} */

</style>


</head>
<body>
<jsp:include page="/back_end/HeadquarterHeader.jsp" flush="true" />

<%-- <jsp:include page="/front_end/header.jsp" flush="true"></jsp:include> --%>
<%-- <img src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg" width="100%" height="" alt="banner"> --%>

<!-- <h4></h4> -->
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
<div class="container.fluid" style=height:1920px;">
<table>
	<tr>
		<th>餐點編號</th>
		<th>餐點名稱</th>
		<th>餐點類型</th>
		<th>餐點價格</th>
		<th>餐點介紹</th>
		<th>餐點圖片</th>
		<th>餐點狀態</th>
		<th>修改</th>
<!-- 		<th>刪除</th> -->
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${menuVO.getMenu_No()}</td>
			<td>${menuVO.getMenu_Id()}</td>
			<td>${menuVO.getMenu_Type()}</td>
			<td>${menuVO.getMenu_Price()}</td>
			<td>${menuVO.getMenu_Intro()}</td>
			<td><img src="/CA103G4/DBGifReader?menu_No=${menuVO.getMenu_No()}" style="max-width: 200px; max-height: 200px;"></td>
			<td>${menuVO.getMenu_Status()}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/menuServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="menu_No"  value="${menuVO.menu_No}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<!-- 			  <FORM METHOD="post" ACTION="menuServlet.do" style="margin-bottom: 0px;"> -->
<!-- 			     <input type="submit" value="刪除"> -->
<!-- 			     從page1.file取得的參數，藉由hidden value傳到下個頁面，可以在刪除一筆資料後繼續停留在該頁面 -->
<%-- 			     <input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<%-- 			     <input type="hidden" name="menu_No" value="${menuVO.menu_No}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
		
	</c:forEach>
</table>
<div class="container">
<div class="row">
	<div style="margin-left:480px;"></div>
	<div><%@ include file="page2.file" %></div>
</div>
</div>


<%-- <jsp:include page="/back_end/HeadquarterFooter.jsp" flush="true" /> --%>
</body>
</html>