<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.orderform.model.*"%>
<%@ page import="com.orderinvoice.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.ingredients.model.*"%>
<%@ page import="com.post.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>>
<jsp:useBean id="menuService"  scope="page" class="com.menu.model.MenuService" />
<jsp:useBean id="custommealsService" scope="page" class="com.custommeals.model.CustommealsService" />
<%-- <jsp:useBean id="ingredientCombinationService" scope="page" class="com.ingredientcombination.model.IngredientCombinationService" /> --%>
<%-- <jsp:useBean id="ingredientsService" scope="page" class="com.ingredients.model.IngredientsService" /> --%>

<%
OrderformService ofsvc = new OrderformService();
List<OrderformVO> orderformlist = new ArrayList();
MemberVO memVO = (MemberVO) session.getAttribute("memVO");
orderformlist = ofsvc.getOrderNoByMemNo(memVO.getMem_No());
pageContext.setAttribute("orderformlist",orderformlist);


OrderinvoiceService orderinvoiceSvc = new OrderinvoiceService();
List<OrderinvoiceVO> orderinvoicelist = new ArrayList();

IngredientsService ingredientsSvc = new IngredientsService();

for(int i = 0; i < orderformlist.size() ; i++ ){
	System.out.println("--------------------------");
	System.out.println(i);
	System.out.println(orderformlist.get(i).getOrder_no());
	System.out.println(orderformlist.get(i).getOrderInvoiceList());
	
	OrderformVO orderformVO = orderformlist.get(i);
	List<OrderinvoiceVO> orderInvoiceList = orderinvoiceSvc.findByOrder_no(orderformVO.getOrder_no());
	orderformVO.setOrderInvoiceList(orderInvoiceList);
	
	
	for(int y = 0; y< orderInvoiceList.size() ; y++ ){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(y);
		System.out.println(orderInvoiceList.get(y).getCustom_no());
		System.out.println(orderInvoiceList.get(y).getCustom_ingdt_List());
		
		OrderinvoiceVO orderinvoiceVO = orderInvoiceList.get(y);
		List<IngredientsVO> custom_ingdt_List = ingredientsSvc.findIngtByCustomNo(orderinvoiceVO.getCustom_no());
		orderinvoiceVO.setCustom_ingdt_List(custom_ingdt_List);
		System.out.println(orderInvoiceList.get(y).getCustom_ingdt_List());
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	
	System.out.println(orderformlist.get(i).getOrderInvoiceList());
}

// System.out.println("AAA"+orderformlist.get(0).getOrderInvoiceList().get(0).getCustom_ingdt_List());

%>












<html>
<head>
<title>我的訂單</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/res/img/icon.png" />
<style>
  table#table-1 {
	background-color: rgba(255, 255, 255, 0.45);
/*     border: 2px solid black; */
/* 	border-radius: 15px; */
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
    
	max-width: 1920px;
	background-color: rgba(255, 255, 255, 0.45);
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:320px;
	font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
    font-size: 20;

  }
  table, th, td {
/*  border: 2px solid rgba(0, 0, 0, 1); 
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



</style>


  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/menu/css/theme.css" />



</head>
<body background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "	width="100%">

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>
<img src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg" width="100%" height="" alt="banner">


<!-- <h3>我的訂單紀錄</h3> -->


<!-- <table> -->
	
<!-- 	<tr> -->
<!-- 		<th>訂單編號</th> -->
<!-- 		<th>訂單價格</th> -->
<!-- 		<td>餐點編號</td> -->
<!-- 		<td>餐點名稱</td> -->
<!-- 		<td>自訂餐點編號</td> -->
<!-- 		<td>自訂餐點名稱</td> -->
<!-- 		<td>自訂餐點食材組合</td>		 -->
<!-- 	</tr> -->
		
<%-- 	<c:forEach var="orderFormVO" items="${orderformlist}"> --%>

<!-- 	<tr> -->
<%-- 		<td>${orderFormVO.order_no}</td> --%>
<%-- 		<td>${orderFormVO.order_price}</td> --%>
	
	
<%-- 		<c:forEach var="orderinvoiceVO" items="${orderFormVO.getOrderInvoiceList()}"> --%>
		
<%-- 			<td>${orderinvoiceVO.menu_no}</td> --%>
<%-- 			<td>${orderinvoiceVO.custom_no}</td> --%>

	
<!-- 		<tr> -->
<%-- 			<td>${menuService.getOneMenu(orderinvoiceVO.menu_no).menu_Id}</td>  --%>
<%-- 			<td>${custommealsService.getOneCustommeals(orderinvoiceVO.custom_no).custom_Name}</td> --%>
	
<%-- 			<c:forEach var="ingredientsVO" items="${orderinvoiceVO.getCustom_ingdt_List()}">	 --%>
<%-- 				<td>${ingredientsVO.ingdt_Name}</td> --%>
<%-- 			</c:forEach>	 --%>
<!-- 		</tr> -->
<%-- 		</c:forEach>	 --%>
<!-- 	</tr>	 -->
<%-- 	</c:forEach> --%>
<!-- </table>	 -->
	

<div class="h-100 py-5" style="backgrond-color:rgba(0,0,0,0.3);">
	<div class="container.fluid" style="width:1920px; margin-left:240px;">
		<div class="row">
			<div class="">
			</div>
				<c:forEach var="orderFormVO" items="${orderformlist}"><br>
				
				        <div class="" style="width:360px;">

				          <div class="card" style="background-color:rgba(255,255,255,0.45); margin-bottom:20px; margin-right:20px; border-radius:5px;">

<!-- 					            <img class="card-img-top"  -->
<%-- 					            	src="<%=request.getContextPath()%>/menu/menushowimage.do?menu_No=${menuVO.getMenu_No()}" --%>
					            
<!-- 					            	alt="Card image cap" style="margin-top:20px;"> -->
					            	
					            <div class="card-body"">
					           	  <h5 class="card-title">訂單編號&nbsp;&nbsp;${orderFormVO.order_no}</h5>
					              <h5 class="card-title">訂單金額&nbsp;&nbsp;${orderFormVO.order_price}</h5>
					              <c:forEach var="orderinvoiceVO" items="${orderFormVO.getOrderInvoiceList()}">
					            	    <h5 class="card-title">餐點編號&nbsp;${orderinvoiceVO.menu_no}
																			${orderinvoiceVO.custom_no}</h5>
					            	    <h5 class="card-title">餐點名稱&nbsp;${menuService.getOneMenu(orderinvoiceVO.menu_no).menu_Id}
					            	    									${custommealsService.getOneCustommeals(orderinvoiceVO.custom_no).custom_Name}</h5>
					            	    			
					            	    		<c:if test="${orderinvoiceVO.custom_no != null}">
					            	    		<div style="background-color:rgba(255,255,245,0.45); border-radius:5px; padding:10px;">
					            	    		
						            	  			<h5 class="card-title">食材搭配&nbsp;</h5>
						            	  			<c:forEach var="ingredientsVO" items="${orderinvoiceVO.getCustom_ingdt_List()}">	
						            	  			<p class="card-title">&nbsp;&nbsp;${ingredientsVO.ingdt_Name}</p>
						            	  			 
					            	  			
													</c:forEach>	
					            	 			 </div>
					            	 			</c:if>
					           
					              </c:forEach>  
					            	  
					            	  
					            </div>
				          </div>
				        
				        </div>
				</c:forEach>
			<div class="col-md-12 mt-3">
  			</div>
    	</div>
	</div>
</div>
	
<%-- 	 <img src="<%=request.getContextPath()%>/ingredients/ingredientsshowimage.do?ingdt_Id${ingredientsVO.getIngdt_Id()}"> --%>
	



</body>
</html>