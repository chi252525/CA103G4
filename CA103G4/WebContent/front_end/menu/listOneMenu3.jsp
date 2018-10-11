<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.menu.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MenuVO menuVO = (MenuVO) request.getAttribute("menuVO"); //MenuServlet.java(Controller), 存入req的menuVO物件
%>

<%
//   MenuDAO dao = new MenuDAO();
//   MenuVO menuVO2 = dao.findByPrimaryKey(menuVO.getMenu_No());
%>

<html>
<head>
<title>餐點資料 - listOneMenu.jsp</title>





</head>
<body style="background-color:rgba(255,255,255,0.45);">





MODAL跳出頁面<br>
MODAL跳出頁面
<img class="card-img-top" src="<%=request.getContextPath()%>/menu/menushowimage.do?menu_No=${menuVO.getMenu_No()}"
	alt="Card image cap" style="margin-top:20px;">
<img src="/CA103G4/front_end/custommeals/images/06.png" alt="蔬菜麵" style="max-width:80px">
<img src="/CA103G4/front_end/custommeals/images/01.png" alt="細拉麵" style="max-wi3265dth:80px">

<%-- 		<td rowspan="4"><img src="<%=request.getContextPath()%>/menu/menushowimage.do?menu_No=${menuVO.getMenu_No()}"  --%>
<!-- 					style="width: 450px; max-height: 600px;"></td> -->
		${menuVO.getMenu_Id()}
		${menuVO.getMenu_Price()}
		${menuVO.getMenu_Intro()}

		
	


</body>
</html>