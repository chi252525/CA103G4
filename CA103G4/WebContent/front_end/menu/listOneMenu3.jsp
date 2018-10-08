<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.menu.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MenuVO menuVO = (MenuVO) request.getAttribute("menuVO"); //MenuServlet.java(Controller), 存入req的menuVO物件
%>

<html>
<head>
<title>餐點資料 - listOneMenu.jsp</title>





</head>
<body style="background-color:rgba(255,255,255,0.45);">





11111111111111111111111111111111111111111111fffffffffffffffff
<img class="card-img-top" src="<%=request.getContextPath()%>/menu/menushowimage.do?menu_No=${menuVO.getMenu_No()}"
	alt="Card image cap" style="margin-top:20px;">
<img src="/CA103G4/front_end/custommeals/images/06.png" alt="蔬菜麵" style="max-width:80px">

<table>
	<tr>
<%-- 		<td rowspan="4"><img src="<%=request.getContextPath()%>/menu/menushowimage.do?menu_No=${menuVO.getMenu_No()}"  --%>
<!-- 					style="width: 450px; max-height: 600px;"></td> -->
		<tr><td><%=menuVO.getMenu_Id()%></td></tr>
		<tr><td><%=menuVO.getMenu_Price()%></td></tr>
		<tr><td><%=menuVO.getMenu_Intro()%></td></tr>

		
	</tr>
</table>

</body>
</html>