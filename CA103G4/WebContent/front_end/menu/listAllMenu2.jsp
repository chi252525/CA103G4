<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MenuService menuSvc = new MenuService();
    List<MenuVO> list = menuSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>listAllEmp.jsp</title>

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
  <link rel="stylesheet" href="theme.css" type="text/css">


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

<table>
	<tr>
<%-- 		<td rowspan="3"><img src="/CA103G4/DBGifReader?menu_No=${menuVO.getMenu_No()}" style="max-width: 480px; max-height: 480px;"></td> --%>
		<td rowspan="4">
			<img src="/CA103G4/front_end/menu/images/特製鹽味拉麵.png" alt="特製鹽味拉麵" style="max-width:480px">
		</td>
	</tr>
	<tr>
		<td>特製拉麵</td>
	</tr>
	<tr>
		<td>$130</td>
	</tr>
	<tr>
		<td>特製九州溫泉蛋、叉燒肉、京都筍乾及提味青蔥，搭配出完美組合，是超高人氣</td>
	</tr>	
</table>

<table>
	<tr>
		<td rowspan="4">
			<img src="/CA103G4/front_end/menu/images/特製鹽味拉麵.png" alt="特製鹽味拉麵" style="max-width:480px">
		</td>
	</tr>
	<tr>
		<td>叉燒拉麵</td>
	</tr>
	<tr>
		<td>$125</td>
	</tr>
	<tr>
		<td>由日本東京進口醬油醃漬的招牌叉燒肉肥瘦適中，特殊口味與眾不同</td>
	</tr>	
</table>







  <div class="py-5" style="background-image: url('images/woodbackground3.png');">
    <div class="container">
      <div class="row">
        <div class="col-md-3">
          <div class="card">
            <img class="card-img-top" src="images/特製鹽味拉麵.png" alt="Card image cap">
            <ul class="list-group list-group-flush">
              <li class="list-group-item">${menuSvc.getOneMenu(menuVO.menu_Id).menu_Id}
                <a href="#" class="card-link">Card link</a>
              </li>
              <li class="list-group-item">$130 </li>
              <li class="list-group-item">特製九州溫泉蛋、叉燒肉、京都筍乾及提味青蔥，搭配出完美組合，是超高人氣</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <pingendo onclick="window.open('https://pingendo.com/', '_blank')" style="cursor:pointer;position: fixed;bottom: 10px;right:10px;padding:4px;background-color: #00b0eb;border-radius: 8px; width:250px;display:flex;flex-direction:row;align-items:center;justify-content:center;font-size:14px;color:white">Made with Pingendo Free&nbsp;&nbsp;
    <img src="https://pingendo.com/site-assets/Pingendo_logo_big.png" class="d-block" alt="Pingendo logo" height="16">





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