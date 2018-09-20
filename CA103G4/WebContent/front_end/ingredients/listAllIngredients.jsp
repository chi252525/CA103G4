<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ingredients.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	IngredientsService ingredientsSvc = new IngredientsService();
    List<IngredientsVO> list = ingredientsSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有食材 - listAllIngredients.jsp</title>

<style>
  table#table-1 {
	background-color: rgba(255, 255, 255, 0.45);
    border: 2px solid black;
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
    
	width: 960px;
	background-color: rgba(255, 255, 255, 0.45);
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:580px;
  }
  table, th, td {
    border: 2px solid #ffffff;
    text-align: center;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  
  body{
	background-image:url("images/woodbackground3.png")
}

</style>

</head>
<body>

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有食材 - listAllIngredients.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

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
		<th>食材編號</th>
		<th>食材類別編號</th>
		<th>食材名稱</th>
		<th>食材狀態</th>
		<th>食材兌換點數</th>
		<th>食材單位</th>
		<th>食材價格</th>
		<th>食材圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="ingredientsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ingredientsVO.getingdt_Id()}</td>
			<td>${ingredientsVO.getingdtc_Id()}</td>
			<td>${ingredientsVO.getingdt_Name()}</td>
			<td>${ingredientsVO.getingdt_Status()}</td>
			<td>${ingredientsVO.getingdt_Point()}</td>
			<td>${ingredientsVO.getingdt_Unit()}</td>
			<td>${ingredientsVO.getingdt_Price()}</td>
			<td><img src="/CA103G4/DBGifReader3?ingdt_Id=${ingredientsVO.getingdt_Id()}" style="max-width: 200px; max-height: 200px;"></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/ingredients/ingredients.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ingdt_Id"  value="${ingredientsVO.ingdt_Id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/ingredients/ingredients.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <!-- 從page1.file取得的參數，藉由hidden value傳到下個頁面，可以在刪除一筆資料後繼續停留在該頁面 -->
			     <input type="hidden" name="whichPage" value="<%=whichPage%>">
			     <input type="hidden" name="ingdt_Id" value="${ingredientsVO.ingdt_Id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
		
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>