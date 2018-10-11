<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.custommeals.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ingredients.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CustommealsVO custommealsVO = (CustommealsVO) request.getAttribute("custommealsVO"); //CustommealsServlet.java(Controller), 存入req的custommealsVO物件
	
	IngredientsService ingtSvc = new IngredientsService();
	List<IngredientsVO> tags = ingtSvc.findIngtByCustomNo(custommealsVO.getcustom_No());
	System.out.println("tags"+tags);
	pageContext.setAttribute("tags", tags);
	
%>
<jsp:useBean id="ingredientCombinationService" scope="page" class="com.ingredientcombination.model.IngredientCombinationService" />
<jsp:useBean id="ingredientsService" scope="page" class="com.ingredients.model.IngredientsService" />
<html>
<head>
<title>餐點資料 - listOneCustommeals.jsp</title>

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
    
	width: 1280px;
	background-color: rgba(255, 255, 255, 0.45);
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:0px;
	font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
    font-size: 20;
  }
  table, th, td {
/*     border: 2px solid rgba(255, 255, 255, 0.8); */
/*     border-radius: 15px; */
    text-align: center;
    font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
    border-radius: 15px;
  }
  th, td {
    padding: 5px;
    text-align: center;
    font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
  }
  @import url(//fonts.googleapis.com/earlyaccess/notosanstc.css);
  body{background-image:url("images/woodbackground3.png");}

  th {
     color: white; 
     font-weight: 800;
     text-align: center;
  }


</style>


</head>
<body bgcolor='white'>

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>

<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
<!-- <table id="table-1"> -->
<!-- <!-- 	<tr><td> --> -->
<!-- <!-- 		 <h3>自訂餐點資料 - ListOneCustommeals.jsp</h3> --> -->
<!-- <!-- 		 <h4><a href="select_page.jsp">回首頁</a></h4> --> -->
<!-- <!-- 	</td></tr> --> -->
<!-- </table> -->



<div class="container.fluid" style="width:1680px; margin-top:80px; margin-bottom:60px; margin-left:360px;">

	<div style="margin-bottom:30px;">
		<div class="display-4">${memVO.mem_Name}的訂餐紀錄
		</div>
	</div>
	<div>
		<table>
			<tr style="background-color:rgba(178, 32, 46, 1);">
				<th>自訂餐點編號</th>
				<th>自訂餐點名稱</th>
				<th>自訂餐點組合</th>
				<th>自訂餐點價格</th>
		<!-- 		<th>自訂餐點圖片</th> -->
			</tr>
			<tr>
				<td><%=custommealsVO.getcustom_No()%></td>
				<td><%=custommealsVO.getcustom_Name()%></td>
				<td><c:forEach var="ingredientsVO" items="${tags}">	
								${ingredientsVO.ingdt_Name}&nbsp;&nbsp;
							</c:forEach></td>
				<td><%=custommealsVO.getcustom_Price()%></td>
				
				
								
<%-- 				<img src="<%=request.getContextPath()%>/ingredients/ingredientsshowimage.do?ingdt_No=${ingredientsVO.getIngdt_No()}"> --%>
				
			</tr>
		</table>
	</div>





</body>
</html>