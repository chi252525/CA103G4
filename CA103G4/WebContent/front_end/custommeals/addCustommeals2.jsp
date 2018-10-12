<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.custommeals.model.*"%>
<%@ page import="com.member.model.*"%>
<% MemberVO memVO = (MemberVO) session.getAttribute("memVO"); %>

<%
//CustommealsServlet.java (Concroller) 存入req的custommealsVO物件 (包括輸入資料錯誤時的custommealsVO物件)
  CustommealsVO custommealsVO = (CustommealsVO) request.getAttribute("custommealsVO");
%>

<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" charset="UTF-8">

  <title>客製化點餐</title>
  
  
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.0/normalize.css'>

      <link rel="stylesheet" href="css/style.css">
<!--       <link rel="stylesheet" href="css/reset.css" > -->

  
</head>



<header>
<!--   <h1>製作專屬於你的拉麵組合</h1> -->
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
	margin-left:400px;
	font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
    font-size: 20;
  }
  table, th, td {
/*     border: 2px solid rgba(255, 255, 255, 0.8); */
    border-radius: 15px;
/*     text-align: center; */
    font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
  }
  th, td {
    padding: 5px;
/*     text-align: center; */
    font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
    

  }
  @import url(//fonts.googleapis.com/earlyaccess/notosanstc.css);
  body{background-image:url("images/woodbackground3.png");}

</style>
</header>

<!--
<section class="add-task-container">
  <input type="text" maxlength="12" id="taskText" placeholder="New Task..." onkeydown="if (event.keyCode == 13)
                        document.getElementById('add').click()">
  <button id="add" class="button add-button" onclick="addTask()">Add New Task</button>
</section>
-->
<body>

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>
<img src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg" width="100%" height="" alt="banner">

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>新增客製化餐點 - addCustommeals.jsp</h3></td><td> -->
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




	
	
	
	
	
	

<div class="wrapper">
	<div class="middle">
		<div class="container.fluid">
			<div class="row">
	<!-- 					<main class="content"> -->
						<div class="col-5">
							<div class="container">
							  <ul class="columns">
							
							    <li class="column ingdt_1-column">
							      <div class="column-header">
							        <h4>麵條</h4>
							      </div>
							      <ul class="task-list" id="ingdt_1">
							        <li class="task" myid="I0001" price="20">
							          <p>細拉麵20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/01.png" alt="細拉麵" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0002" price="20">
							          <p>烏龍麵20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/02.png" alt="烏龍麵" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0003" price="20">
							          <p>刀削麵20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/03.png" alt="刀削麵" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0004" price="20">
							          <p>蕎麥麵20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/04.png" alt="蕎麥麵" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0005" price="30">
							          <p>墨魚麵30
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/05.png" alt="墨魚麵" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0006" price="20">
							          <p>蔬菜麵20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/06.png" alt="蔬菜麵" style="max-width:80px"></p>
							        </li>
							      </ul>
							    </li>
							    
							    <li class="column ingdt_2-column">
							      <div class="column-header">
							        <h4>湯頭</h4>
							      </div>
							      <ul class="task-list" id="ingdt_2">
<!-- 							        <li class="task" myid="I0007" price="20"> -->
<!-- 							          <p>醬油湯20 -->
<!-- 							          <br> -->
<!-- 							            <img src="/CA103G4/front_end/custommeals/images/07.png" alt="醬油湯" style="max-width:80px"></p> -->
<!-- 							        </li> -->
	<!-- 						        <li class="task" myid="I0008" price="30"> -->
	<!-- 						          <p>泡菜湯30 -->
	<!-- 						          <br> -->
	<!-- 						            <img src="/CA103G4/front_end/custommeals/images/08.png" alt="泡菜湯" style="max-width:80px"></p> -->
	<!-- 						        </li> -->
							        <li class="task" myid="I0009" price="30">
							          <p>牛肉湯30
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/09.png" alt="牛肉湯" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0010" price="30">
							          <p>麻辣湯30
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/10.png" alt="麻辣湯" style="max-width:80px"></p>
							        </li>
	<!-- 						        <li class="task" myid="I0011" price="20"> -->
	<!-- 						          <p>番茄湯20 -->
	<!-- 						          <br> -->
	<!-- 						            <img src="/CA103G4/front_end/custommeals/images/11.png" alt="番茄湯" style="max-width:80px"></p> -->
	<!-- 						        </li> -->
							
<!-- 							        <li class="task" myid="I0012" price="20"> -->
<!-- 							          <p>雞湯20 -->
<!-- 							          <br> -->
<!-- 							            <img src="/CA103G4/front_end/custommeals/images/12.png" alt="雞湯" style="max-width:80px"></p> -->
<!-- 							        </li> -->
							
							        <li class="task" myid="I0013" price="20">
							          <p>豚骨湯20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/13.png" alt="豚骨湯" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0014" price="20">
							          <p>味增湯20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/14.png" alt="味增湯" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0015" price="30">
							          <p>咖哩湯30
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/15.png" alt="咖哩湯" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0016" price="20">
							          <p>鹽味湯20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/16.png" alt="鹽味湯" style="max-width:80px"></p>
							        </li>
	<!-- 						        <li class="task" myid="I0017" price="30"> -->
	<!-- 						          <p>海鮮湯30 -->
	<!-- 						          <br> -->
	<!-- 						            <img src="/CA103G4/front_end/custommeals/images/17.png" alt="海鮮湯" style="max-width:80px"></p> -->
	<!-- 						        </li> -->
							      </ul>
							    </li>
							
							    <li class="column ingdt_3-column">
							      <div class="column-header">
							        <h4>主餐</h4>
							      </div>
							      <ul class="task-list" id="ingdt_3">
							        <li class="task" myid="I0018" price="50">
							          <p>叉燒肉50
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/18.png" alt="叉燒肉" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0019" price="70">
							          <p>炸豬排70
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/19.png" alt="炸豬排" style="max-width:80px"></p>
							        </li>
							
							        <li class="task" myid="I0020" price="50">
							          <p>炸雞柳50
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/20.png" alt="炸雞柳" style="max-width:80px"></p>
							        </li>
							
							        <li class="task" myid="I0021" price="60">
							          <p>唐揚雞塊60
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/21.png" alt="唐揚雞" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0022" price="80">
							          <p>鮮蝦80
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/22.png" alt="鮮蝦" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0023" price="100">
							          <p>小龍蝦100
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/23.png" alt="小龍蝦" style="max-width:80px"></p>
							        </li>
							
							        <li class="task" myid="I0024" price="50">
							          <p>櫻花蝦50
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/24.png" alt="櫻花蝦" style="max-width:80px"></p>
							        </li>
							
							        <li class="task" myid="I0025" price="80">
							          <p>鴨胸80
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/25.png" alt="鴨胸" style="max-width:80px"></p>
							        </li>
							
							        <li class="task" myid="I0026" price="60">
							          <p>魷魚圈60
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/26.png" alt="魷魚圈" style="max-width:80px"></p>
							        </li>
							
							        <li class="task" myid="I0027" price="60">
							          <p>蛤蠣60
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/27.png" alt="蛤蠣" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0028" price="70">
							          <p>虱目魚70
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/28.png" alt="虱目魚" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0029" price="90">
							          <p>牛肉90
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/29.png" alt="牛肉" style="max-width:80px"></p>
							        </li>
							      </ul>
							    </li>
							    
							    <li class="column ingdt_4-column">
							      <div class="column-header">
							        <h4>附餐</h4>
							      </div>
							      <ul class="task-list" id="ingdt_4">
							        <li class="task" myid="I0030" price="20">
							          <p>荷包蛋20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/30.png" alt="荷包蛋" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0031" price="20">
							          <p>溫泉蛋20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/31.png" alt="溫泉蛋" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0032" price="20">
							          <p>滷蛋20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/32.png" alt="滷蛋" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0033" price="20">
							          <p>貢丸20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/33.png" alt="貢丸" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0034" price="20">
							          <p>豆皮20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/34.png" alt="豆皮" style="max-width:80px"></p>
							        </li>
	<!-- 						        <li class="task" myid="I0035" price="20"> -->
	<!-- 						          <p>筍乾20 -->
	<!-- 						          <br> -->
	<!-- 						            <img src="/CA103G4/front_end/custommeals/images/35.png" alt="筍乾" style="max-width:80px"></p> -->
	<!-- 						        </li> -->
							        <li class="task" myid="I0036" price="20">
							          <p>小魚乾20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/36.png" alt="小魚乾" style="max-width:80px"></p>
							        </li>
							      </ul>
							    </li>
	
							  </ul>
							</div>
						</div>
						<div class="col-4">
							  <ul class="columns">
							  	<li class="column trash-column" id="combination_area">
								      <div class="column-header2">
								        <h4>組合區</h4>
								      </div>
									      <ul class="task-list2" id="combination"></ul>								  		
									  <form method="post" id="sform" action="custommeals.do" name="shoppingForm" class="shoppingForm">
<%-- 									  <form method="post" id="menuform" name="shoppingForm" class="shoppingForm" action="<%=request.getContextPath()%>/front_end/shoppingCart/ShoppingServlet.do"> --%>
<div class="container">
										
	<div class="row">
		<div style="width:60px;">
		</div>
		<div style="width:450px;">													
										      <div> &nbsp;&nbsp;&nbsp;會員編號&nbsp;&nbsp;&nbsp; 	${memVO.mem_No}</div>
                                                <input class="form-control" type="hidden" name="mem_No" size="45" value="${memVO.mem_No}" /><br>
											<div class="form-inline">
                                                &nbsp;&nbsp;&nbsp;餐點名稱&nbsp;&nbsp;&nbsp;
                                                <input class="form-control" type="text" name="custom_Name" size="45" style="margin-top:10px; width:250px;" required /><br>
											</div>
											<div class="form-inline">
                                                &nbsp;&nbsp;&nbsp;餐點價格&nbsp;&nbsp;&nbsp;
                                                <input  class="form-control custom_Price" type="text" id="custom_Price" name="custom_Price" size="45"  readonly style="margin-top:10px; width:250px;"  /><br>
											</div>
											<table>
											   <tr style="display: none;">
										            <td>食材選擇:</td>
										            <td>
										            	 麵條
										                <input id="I0001" type="checkbox" name="ingredients" value="I0001"> 細拉麵20
										                <input id="I0002" type="checkbox" name="ingredients" value="I0002"> 烏龍麵20
										                <input id="I0003" type="checkbox" name="ingredients" value="I0003"> 刀削麵20
										                <input id="I0004" type="checkbox" name="ingredients" value="I0004"> 蕎麥麵20
										                <input id="I0005" type="checkbox" name="ingredients" value="I0005"> 墨魚麵30
										                <input id="I0006" type="checkbox" name="ingredients" value="I0006"> 蔬菜麵20
										                <br>
										               	 湯頭
										                <input id="I0007" type="checkbox" name="ingredients" value="I0007"> 醬油湯20
										                <input id="I0008" type="checkbox" name="ingredients" value="I0008"> 泡菜湯30
										                <input id="I0009" type="checkbox" name="ingredients" value="I0009"> 牛肉湯30
										                <input id="I0010" type="checkbox" name="ingredients" value="I0010"> 麻辣湯30
										                <input id="I0011" type="checkbox" name="ingredients" value="I0011"> 番茄湯20
										                <input id="I0012" type="checkbox" name="ingredients" value="I0012"> 雞湯20
										                <input id="I0013" type="checkbox" name="ingredients" value="I0013"> 豚骨湯20
										                <input id="I0014" type="checkbox" name="ingredients" value="I0014"> 味增湯20
										                <input id="I0015" type="checkbox" name="ingredients" value="I0015"> 咖哩湯30
										                <input id="I0016" type="checkbox" name="ingredients" value="I0016"> 鹽味湯20
										                <input id="I0017" type="checkbox" name="ingredients" value="I0017"> 海鮮湯30
										                <br>
														主餐
										                <input id="I0018" type="checkbox" name="ingredients" value="I0018"> 叉燒肉50
										                <input id="I0019" type="checkbox" name="ingredients" value="I0019"> 炸豬排70
										                <input id="I0020" type="checkbox" name="ingredients" value="I0020"> 炸雞柳50
										                <input id="I0021" type="checkbox" name="ingredients" value="I0021"> 唐揚雞塊60
										                <input id="I0022" type="checkbox" name="ingredients" value="I0022"> 鮮蝦80
										                <input id="I0023" type="checkbox" name="ingredients" value="I0023"> 龍蝦100
										                <input id="I0024" type="checkbox" name="ingredients" value="I0024"> 櫻花蝦50
										                <input id="I0025" type="checkbox" name="ingredients" value="I0025"> 鴨胸80
										                <input id="I0026" type="checkbox" name="ingredients" value="I0026"> 魷魚圈60
										                <input id="I0027" type="checkbox" name="ingredients" value="I0027"> 蛤蠣60
										                <input id="I0028" type="checkbox" name="ingredients" value="I0028"> 虱目魚70
										                <input id="I0029" type="checkbox" name="ingredients" value="I0029"> 牛肉90
										                <br>
														副餐
										                <input id="I0030" type="checkbox" name="ingredients" value="I0030"> 荷包蛋20
										                <input id="I0031" type="checkbox" name="ingredients" value="I0031"> 溫泉蛋20
										                <input id="I0032" type="checkbox" name="ingredients" value="I0032"> 滷蛋20
										                <input id="I0033" type="checkbox" name="ingredients" value="I0033"> 貢丸20
										                <input id="I0034" type="checkbox" name="ingredients" value="I0034"> 豆皮20
										                <input id="I0035" type="checkbox" name="ingredients" value="I0035"> 筍乾20
										                <input id="I0036" type="checkbox" name="ingredients" value="I0036"> 小魚乾20
										                <br>
														配料
										                <input id="I0037" type="checkbox" name="ingredients" value="I0037"> 韓式泡菜20
										                <input id="I0038" type="checkbox" name="ingredients" value="I0038"> 台式泡菜20
										                <input id="I0039" type="checkbox" name="ingredients" value="I0039"> 海帶20
										                <input id="I0040" type="checkbox" name="ingredients" value="I0040"> 海苔15
										                <input id="I0041" type="checkbox" name="ingredients" value="I0041"> 燙青菜30
										                <input id="I0042" type="checkbox" name="ingredients" value="I0042"> 高麗菜絲25
										                <br>
														調味
										                <input id="I0043" type="checkbox" name="ingredients" value="I0043"> 蔥花5
										                <input id="I0044" type="checkbox" name="ingredients" value="I0044"> 柴魚5
										                <input id="I0045" type="checkbox" name="ingredients" value="I0045"> 胡椒5
										                <input id="I0046" type="checkbox" name="ingredients" value="I0046"> 辣椒5
										                <input id="I0047" type="checkbox" name="ingredients" value="I0047"> 玫瑰鹽5
										                <input id="I0048" type="checkbox" name="ingredients" value="I0048"> 特調醬汁5
										                <br>
										            </td>
										        </tr>
											</table>
		</div>
		<div style="width:150px;">						  
									   <div class="column-button">
								        <button id="getPrice" type="button" class="button confirm-button confirmBtn btn btn-light">計算金額</button>
								       </div>
									  
	
									   <div class="column-button">
										   <button id="insert" class="button confirm-button btn btn-light" type="submit" value="ADD" disabled="true">送出新增</button>
										   <input type="hidden" name="action" value="insert">
										   <input type="hidden" name="requestURL" value="/front_end/custommeals/addCustommeals2.jsp">
									   </div>
		</div>
	</div>
</div>

				
	
								      </form>
								     
		
								      <br>
	
							    </li>
	
							    <li class="column ingdt_5-column">
							      <div class="column-header">
							        <h4>配料</h4>
							      </div>
							      <ul class="task-list" id="ingdt_5">
							        <li class="task" myid="I0037" price="20">
							          <p>韓式泡菜20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/37.png" alt="韓式泡菜" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0038" price="20">
							          <p>台式泡菜20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/38.png" alt="台式泡菜" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0039" price="20">
							          <p>海帶20
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/39.png" alt="海帶" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0040" price="15">
							          <p>海苔15
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/40.png" alt="海苔" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0041" price="30">
							          <p>燙青菜30
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/41.png" alt="燙青菜" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0042" price="25">
							          <p>高麗菜絲25
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/42.png" alt="高麗菜絲" style="max-width:80px"></p>
							        </li>
							      </ul>
							    </li>
							    
							    <li class="column ingdt_6-column">
							      <div class="column-header">
							        <h4>調味料</h4>
							      </div>
							      <ul class="task-list" id="ingdt_6">
							        <li class="task" myid="I0043" price="5">
							          <p>蔥花5
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/43.png" alt="蔥花" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0044" price="5">
							          <p>柴魚5
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/44.png" alt="柴魚" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0045" price="5">
							          <p>胡椒5
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/45.png" alt="胡椒" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0046" price="5">
							          <p>辣椒5
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/46.png" alt="辣椒" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0047" price="5">
							          <p>玫瑰鹽5
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/47.png" alt="玫瑰鹽" style="max-width:80px"></p>
							        </li>
							        <li class="task" myid="I0048" price="5">
							          <p>特調醬汁5
							          <br>
							            <img src="/CA103G4/front_end/custommeals/images/48.png" alt="玫瑰鹽" style="max-width:80px"></p>
							        </li>
							      </ul>
							    </li>
	
							  </ul>
						</div>
	<!-- 					</main> -->
			</div>
		</div>
	</div>
</div>

 





<footer>
</footer>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/dragula/3.7.2/dragula.js'></script>

	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	  crossorigin="anonymous"></script>
  
    
<!--   	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" -->
<!-- 		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" -->
<!-- 		crossorigin="anonymous"></script> -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

  

    <script  src="js/index.js"></script>
    <script>
        $(function(){
        	$('#combination').on('change', function() {
        		console.log('combination')
        	})
        	console.log('123')
            $('.confirmBtn').on('click', function(){
            	var count = 0;
                var arr = [];
                var list = $('#combination').find('li');
                console.log(list)
                for(var i=0; i< list.length; i++){
                    var id =$(list[i]).attr('myId');
                    $('#'+id).prop('checked', true);
                    console.log($('#'+id))
                    count = count + parseInt($(list[i]).attr('price'));
                }
                console.log(count)
                $(".custom_Price").val(count);
               // $('#sform').submit();
            });
        });
    </script>
    
    <script>
			$(function() {
				$("#dialog").dialog({
					modal : true,
					buttons : {
						Ok : function() {
							$(this).dialog("close");
						}
					}
				});
			});
			//Java完美操縱javaScript , 加入餐點進購物車
		
			$(function() {
				$("#insert").click(function() {
					swal({
						title : "加入購物車",
						html : "成功",
						type : "success"
					}).then(function() {
						$("#sform").submit();
					});
				});
			});
		
		
		//開啟新增按鈕
		$("#getPrice").click(function(){
			alert('ok');
			$("#insert").prop("disabled",false);
// 			document.getElementById('insert').disabled=false;
		});
		
    </script>
    
    <script>
//         jQuery(function($) {
//         	var count = 0;
//         	var check = $('.sum').find('input[type="checkbox"]');
//         	$('.result-text'); 
//           check.prop('checked', false); 

        	
//         	check.on('change', function() {
//         		if($(this).prop('checked')) {
//         			count = count + parseInt($(this).val());
//         		} else {
//         			count = count - parseInt($(this).val());
//         		}
//         		$('.cb-count').text(count);


//         	})
//         });
    </script>



</body>

</html>
