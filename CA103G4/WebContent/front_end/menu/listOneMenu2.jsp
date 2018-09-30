<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.menu.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MenuVO menuVO = (MenuVO) request.getAttribute("menuVO"); //MenuServlet.java(Controller), 存入req的menuVO物件
%>

<html>
<head>
<title>餐點資料 - listOneMenu.jsp</title>

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
    
	width: 1440px;
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
    text-align: center;
    font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
  }
  th, td {
    padding: 5px;
    text-align: center;
    font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
  }
  @import url(//fonts.googleapis.com/earlyaccess/notosanstc.css);
  body{background-image:url("images/woodbackground3.png");}

</style>

</head>
<body bgcolor='white'>

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>
<img src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg" width="100%" height="" alt="banner">

<h4></h4>
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>餐點資料 - ListOneMenu.jsp</h3> -->
<!-- 		 <h4><a href="select_page.jsp">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->





<div class="h-100 py-5">
	<div class="container">
		<div class="row">
			
				
	
			
				
				
				        <div class="col-md-12">
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
				        
				
				
				
			
				
  			
    	</div>
	</div>
</div>


<table>
	<tr>
		
		<th>餐點名稱</th>
		
		<th>餐點價格</th>
		<th>餐點介紹</th>
		<th>餐點圖片</th>
		
	</tr>
	<tr>
		
		<td><%=menuVO.getMenu_Id()%></td>
		<td><%=menuVO.getMenu_Price()%></td>
		<td><%=menuVO.getMenu_Intro()%></td>
		<td><img src="/CA103G4/DBGifReader?menu_No=${menuVO.getMenu_No()}" style="max-width: 200px; max-height: 200px;"></td>
		
	</tr>
</table>

</body>
</html>