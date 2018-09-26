<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>
<html>
<head>
<meta charset="UTF-8">
 
<title>竹風堂經典拉麵</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ShoppingCart.css">
</head>
<body>
 
 <!--  
       第一種action寫法: <form name="shoppingForm" action="Shopping.html" method="POST">
       第二種action寫法: <form name="shoppingForm" action="/IBM_MVC/Shopping.html" method="POST">
       第三種action寫法: <form name="shoppingForm" action="<%=request.getContextPath()%>/Shopping.html" method="POST">
 -->
 <!-- 
       當某網頁可能成為被forward的網頁時, 此網頁內的所有html連結 , 如果採用相對路徑寫法時, 因為會被加上原先forward者的路徑
       在更複雜的MVC架構中, 上面第三種寫法, 先以request.getContextPath()方法, 先取得環境(Servlet Context)目錄路徑的寫法,
       將是最佳解決方案
 -->


<jsp:useBean id="menuSvc" scope="page" class="com.menu.model.MenuService" />

<c:forEach var="menuVO" items="${menuSvc.all}">
<c:if test="${menuVO.menu_Status == 1}">
  <form name="shoppingForm" action="<%=request.getContextPath()%>" method="POST">
  
    <table>
    <tr><div>圖片</div></tr>
      <tr><div>${menuVO.menu_No}</div></tr>
      <tr><div>${menuVO.menu_Id}</div></tr>
      <tr><div>${menuVO.menu_Type}</div></tr>
      <tr><div>${menuVO.menu_Price}</div></tr>
      <tr><div>${menuVO.menu_Intro}</div></tr>
      <tr><div>${menuVO.menu_Status}</div></tr>
      <tr><div>數量：<input type="text" name="quantity" size="3" value=1></div></tr>
      <tr><div>     <input type="submit" class="button" value="放入購物車"> </div></tr>
   </table>
      <input type="hidden" name="menuno" value="${menuVO.menu_No}">
      <input type="hidden" name="menuid" value="${menuVO.menu_Id}">
      <input type="hidden" name="type" value="${menuVO.menu_Type}">
      <input type="hidden" name="price" value="${menuVO.menu_Price}">
      <input type="hidden" name="status" value="${menuVO.menu_Status}">
      <input type="hidden" name="action" value="ADD">
  </form>
 </c:if>
 </c:forEach>
 
</body>
</html>