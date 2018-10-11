<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>


<%
    MenuService menuSvc = new MenuService();
    List<MenuVO> list = menuSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="menuDAO"  scope="page" class="com.menu.model.MenuDAO" />



<html>
<head>
<title>Menu_management.jsp</title>


<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">



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
    
	max-width: 1280px;
	background-color: rgba(255, 255, 255, 0.45);
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:320px;
/* 	font-family: 'Noto Sans TC', sans-serif; */
    font-weight: 600;
    font-size: 20;
  }
  table, th, td {
/*     border: 2px solid rgba(255, 255, 255, 0.8); */
    border-radius: 15px;
    text-align: left;
/*     font-family: 'Noto Sans TC', sans-serif; */
    font-weight: 600;
  }
  th, td {
    padding: 5px;
    text-align: left;
/*     font-family: 'Noto Sans TC', sans-serif; */
    font-weight: 600;
  }
/*   @import url(//fonts.googleapis.com/earlyaccess/notosanstc.css); */
/*   body{background-image:url("images/woodbackground3.png");} */

</style>






  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/menu/css/theme.css" />
  
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>





</head>
<body>
<jsp:include page="/back_end/HeadquarterHeader.jsp" flush="true" />



<%-- <jsp:include page="/front_end/header.jsp" flush="true"></jsp:include> --%>
<%-- <img src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg" width="100%" height="" alt="banner"> --%>






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
	<div class="container.fluid" style="margin-left:150px; margin-bottom:100px;">
		<div class="row" style="width:1600px; margin-bottom:0px;">
			<div class="col-md-12 mt-3" style="height:10px;">
				<%@ include file="page1.file"%><br>
	
			</div>
				<c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"><br>
				
			
				        <div class="col-md-2">
				       
				          <div class="card" style="background-color:rgba(255,255,255,0.45); margin-bottom:20px;">
				            <img class="card-img-top" 
				            	src="<%=request.getContextPath()%>/menu/menushowimage.do?menu_No=${menuVO.getMenu_No()}"
				            
				            	alt="Card image cap" style="margin-top:20px;">
				            <div class="card-body">
				              <h5 class="card-title">${menuSvc.getOneMenu(menuVO.menu_No).menu_Id} ${menuVO.menu_Id} <br> </h5>
				              <h5 class="card-title">$${menuSvc.getOneMenu(menuVO.menu_No).menu_Price} ${menuVO.menu_Price}</h5>
				              <p class="card-text" style="height:72px;">${menuSvc.getOneMenu(menuVO.menu_No).menu_Intro} ${menuVO.menu_Intro}</p>

				            	  
							  <form method="post" action="<%=request.getContextPath()%>/menu/menuServlet.do" name="form1">
								  <div class="row">
									  <div style="width:120px; margin-left:20px; margin-top:0px;">
									  	<input type="hidden" name="menu_No" value="${menuVO.getMenu_No()}">
										上下架  <input type="number" name="menu_Status" size="3" max="1" min="0" value="${menuVO.getMenu_Status()}" style="width: 50px; margin-top:5px;">
									  </div>
									  <div style="width:80px;">
<!-- 						              	 <a href="#" class="btn btn-primary" style="background-color:#dc3545; border-color:#dc3545; margin-left:0px;">確認</a> -->
						              	<input type="hidden" name="action" value="update2">
<%-- 										<input type="hidden" name="menu_No" value="${menuVO.getMenu_No()}"> --%>
<!-- 										<button type="submit" value="確認" class="btn btn-danger" data-toggle="modal" data-target="#HACK" style="background-color:#dc3545; border-color:#dc3545; color:#fff; margin-left:0px; border-radius:5px;"> -->
<!-- 										確認</button> -->
										<button type="submit" value="確認" style="background-color:#dc3545; border-color:#dc3545; color:#fff; margin-left:0px; border-radius:5px; margin-top:5px;">
										確認</button>
						              </div>
					              </div>
				              </form>
				            </div>
				          </div>
				        
				        </div>
				</c:forEach>
				
				
				
			<div class="container">
				<div class="row">
					<div style="margin-left:470px;"></div>
					<div><%@ include file="page2.file" %></div>
				</div>
			</div>	
<!-- 			<div class="col-md-12 mt-3"> -->
<%-- 				<%@ include file="page2.file"%>   --%>
<!--   			</div> -->
    	</div>
	</div>
</div>




<!-- <div class="modal fade" id="HACK"> -->
<!--   <div class="modal-dialog"> -->
<!--     <div class="modal-content bg-warning text-danger"> -->
    
<!--       <div class="modal-header"> -->
<!--         <h4 class="modal-title">Get Hacked!</h4> -->
<!--         <button type="button" class="close">¿</button> -->
<!--       </div> -->
      
<!--       <div class="modal-body"> -->
<!--         YOUR COMPUTER IS HACKED! -->
<!--       </div> -->
      
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-success"  -->
<!--         data-dismiss="modal">unlocked it by give me a like:></button> -->
<!--       </div> -->
      
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->




<script>

$(function() {
$( "#dialog" ).dialog({
modal: true,
buttons: {
Ok: function() {
$( this ).dialog( "close" );
}
}
});
});

</script>

		
<script>

</script>


  






<%-- 	<jsp:include page="/back_end/HeadquarterFooter.jsp" flush="true" /> --%>
</body>
</html>