<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.custommeals.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	CustommealsService custommealsSvc = new CustommealsService();
    List<CustommealsVO> list = custommealsSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有客製化餐點 - listAllCustommeals.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有客製化餐點 - listAllCustommeals.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>自訂餐點編號</th>
		<th>會員編號</th>
		<th>自訂餐點名稱</th>
		<th>自訂餐點價格</th>
		<th>餐點圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="custommealsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${custommealsVO.getcCustom_No()}</td>
			<td>${custommealsVO.getMem_No()}</td>
			<td>${custommealsVO.getCustom_Name()}</td>
			<td>${custommealsVO.getCustom_Price()}</td>
			<td><img src="/CA103G4/DBGifReader?custom_No=${custommealsVO.getCustom_No()}" style="max-width: 200px; max-height: 200px;"></td>
			
			<td>
			  <FORM METHOD="post" ACTION="custommeals.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="custom_No"  value="${custommealsVO.custom_No}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="custommeals.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <!-- 從page1.file取得的參數，藉由hidden value傳到下個頁面，可以在刪除一筆資料後繼續停留在該頁面 -->
			     <input type="hidden" name="whichPage" value="<%=whichPage%>">
			     <input type="hidden" name="custom_No" value="${custommealsVO.custom_No}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
		
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>