<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.custommeals.model.*"%>

<%
//CustommealsServlet.java (Concroller) 存入req的custommealsVO物件 (包括輸入資料錯誤時的custommealsVO物件)
  CustommealsVO custommealsVO = (CustommealsVO) request.getAttribute("custommealsVO");
%>

<!DOCTYPE html>
<html lang="en" >

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" charset="UTF-8">

  <title>新增客製化餐點 - addcustommeals.jsp</title>
  
  
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
<img src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg"
		width="100% height="" alt="banner">

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




	
	
	
	
	
	

<!-- <div class="wrapper"> -->
<!-- <div class="middle"> -->
	<div class="container.fluid">
		<div class="row">
	<!-- 				<main class="content"> -->
					<div class="col-6">
						<div class="container">
						  <ul class="columns">
						
						    <li class="column ingdt_1-column">
						      <div class="column-header">
						        <h4>麵條</h4>
						      </div>
						      <ul class="task-list" id="ingdt_1">
						        <li class="task" myid="I0001">
						          <p>細拉麵
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/01.png" alt="細拉麵" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0002">
						          <p>烏龍麵
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/02.png" alt="烏龍麵" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0003">
						          <p>刀削麵
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/03.png" alt="刀削麵" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0004">
						          <p>蕎麥麵
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/04.png" alt="蕎麥麵" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0005">
						          <p>墨魚麵
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/05.png" alt="墨魚麵" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0006">
						          <p>蔬菜麵
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
						        <li class="task" myid="I0007">
						          <p>醬油湯
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/07.png" alt="醬油湯" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0008">
						          <p>泡菜湯
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/08.png" alt="泡菜湯" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0009">
						          <p>牛肉湯
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/09.png" alt="牛肉湯" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0010">
						          <p>麻辣湯
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/10.png" alt="麻辣湯" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0011">
						          <p>番茄湯
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/11.png" alt="番茄湯" style="max-width:80px"></p>
						        </li>
						
						        <li class="task" myid="I0012">
						          <p>雞湯
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/12.png" alt="雞湯" style="max-width:80px"></p>
						        </li>
						
						        <li class="task" myid="I0013">
						          <p>豚骨湯
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/13.png" alt="豚骨湯" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0014">
						          <p>味增湯
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/14.png" alt="味增湯" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0015">
						          <p>咖哩湯
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/15.png" alt="咖哩湯" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0015-1">
						          <p>鹽味湯
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/15-1.png" alt="鹽味湯" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0015-2">
						          <p>海鮮湯
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/15-2.png" alt="海鮮湯" style="max-width:80px"></p>
						        </li>
						      </ul>
						    </li>
						
						    <li class="column ingdt_3-column">
						      <div class="column-header">
						        <h4>主餐</h4>
						      </div>
						      <ul class="task-list" id="ingdt_3">
						        <li class="task" myid="I0016">
						          <p>叉燒肉
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/16.png" alt="叉燒肉" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0017">
						          <p>炸豬排
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/17.png" alt="炸豬排" style="max-width:80px"></p>
						        </li>
						
						        <li class="task" myid="I0018">
						          <p>炸雞柳
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/18.png" alt="炸雞柳" style="max-width:80px"></p>
						        </li>
						
						        <li class="task" myid="I0019">
						          <p>唐揚雞塊
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/19.png" alt="唐揚雞" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0020">
						          <p>鮮蝦
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/20.png" alt="鮮蝦" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0021">
						          <p>小龍蝦
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/21.png" alt="小龍蝦" style="max-width:80px"></p>
						        </li>
						
						        <li class="task" myid="I0022">
						          <p>櫻花蝦
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/22.png" alt="櫻花蝦" style="max-width:80px"></p>
						        </li>
						
						        <li class="task" myid="I0023">
						          <p>鴨胸
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/23.png" alt="鴨胸" style="max-width:80px"></p>
						        </li>
						
						        <li class="task" myid="I0024">
						          <p>魷魚圈
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/24.png" alt="魷魚圈" style="max-width:80px"></p>
						        </li>
						
						        <li class="task" myid="I0025">
						          <p>蛤蠣
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/25.png" alt="蛤蠣" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0026">
						          <p>虱目魚
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/26.png" alt="虱目魚" style="max-width:80px"></p>
						        </li>
						      </ul>
						    </li>
						    

						    
						  </ul>
						</div>
					</div>
					<div class="col-4" id="position_fixed">
				<!-- <div class="right-sidebar"> -->
				
						  <ul class="columns">
						  	<li class="column trash-column">
						      <div class="column-header2">
						        <h4>組合區</h4>
						      </div>
						      <ul class="task-list" id="combination">
						
						      </ul>
						      <div class="column-button">
						        <button class="button delete-button" onclick="emptyTrash()">重新選擇</button>
						      </div>
						<!--      <form id="sform" action="submit">-->
						      <div class="column-button">
						<!--       	<input type="button" name="action" value="insert" class="button confirm-button confirmBtn"> -->
						        <button class="button confirm-button confirmBtn">確認組合</button>
						      </div>
						<!--      </form>-->
						    </li>
						  	<li class="column ingdt_4-column">
						      <div class="column-header">
						        <h4>附餐</h4>
						      </div>
						      <ul class="task-list" id="ingdt_4">
						        <li class="task" myid="I0027">
						          <p>荷包蛋
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/27.png" alt="荷包蛋" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0028">
						          <p>溫泉蛋
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/28.png" alt="溫泉蛋" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0029">
						          <p>滷蛋
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/29.png" alt="滷蛋" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0030">
						          <p>貢丸
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/30.png" alt="貢丸" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0031">
						          <p>豆皮
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/31.png" alt="豆皮" style="max-width:80px"></p>
						        </li>
						<!--         <li class="task" myid="I0032"> -->
						<!--           <p>筍乾 -->
						<!--           <br> -->
						<!--             <img src="/CA103G4/front_end/custommeals/images/32.png" alt="筍乾" style="max-width:80px"></p> -->
						<!--         </li> -->
						        <li class="task" myid="I0033">
						          <p>小魚乾
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/33.png" alt="小魚乾" style="max-width:80px"></p>
						        </li>
						      </ul>
						    </li>
						    
						    <li class="column ingdt_5-column">
						      <div class="column-header">
						        <h4>配料</h4>
						      </div>
						      <ul class="task-list" id="ingdt_5">
						        <li class="task" myid="I0034">
						          <p>韓式泡菜
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/34.png" alt="韓式泡菜" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0035">
						          <p>台式泡菜
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/35.png" alt="台式泡菜" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0036">
						          <p>海帶
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/36.png" alt="海帶" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0037">
						          <p>海苔
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/37.png" alt="海苔" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0038">
						          <p>燙青菜
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/38.png" alt="燙青菜" style="max-width:80px"></p>
						        </li>
						      </ul>
						    </li>
						    
						    <li class="column ingdt_6-column">
						      <div class="column-header">
						        <h4>調味料</h4>
						      </div>
						      <ul class="task-list" id="ingdt_6">
						        <li class="task" myid="I0039">
						          <p>蔥花
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/39.png" alt="蔥花" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0040">
						          <p>柴魚
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/40.png" alt="柴魚" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0041">
						          <p>胡椒
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/41.png" alt="胡椒" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0042">
						          <p>辣椒
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/42.png" alt="辣椒" style="max-width:80px"></p>
						        </li>
						        <li class="task" myid="I0043">
						          <p>玫瑰鹽
						          <br>
						            <img src="/CA103G4/front_end/custommeals/images/43.png" alt="玫瑰鹽" style="max-width:80px"></p>
						        </li>
						      </ul>
						    </li>
						  

						
						  </ul>
					</div>
				<!-- </div> -->
	<!-- 				</main> -->
		</div>
	</div>
<!-- </div> -->
<!-- </div> -->

 

<!-- <div class="right-sidebar"> -->
<form METHOD="post" id="sform" action="custommeals.do" name="form1">
	<input type="hidden" name ="action" value="insert">
    <table>
    
    
    <tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mem_No" size="45"	
			value="<%=(custommealsVO==null)?"M000001":custommealsVO.getmem_No()%>" /></td>
	</tr>
	<tr>
		<td>自訂餐點名稱:</td>
		<td><input type="TEXT" name="custom_Name" size="45"	
			value="<%=(custommealsVO==null)?"海陸雙拼大餐":custommealsVO.getcustom_Name()%>" /></td>
	</tr>
	<tr>
		<td>自訂餐點價格:</td>
		<td><input type="TEXT" name="custom_Price" size="45"	
			value="<%=(custommealsVO==null)?"480":custommealsVO.getcustom_Price()%>" /></td>
	</tr>
    
    
    <tr style="display: block;">
<!-- 	<tr> -->
            <td>食材選擇:</td>
            <td>
            	 麵條
                <input id="I0001" type="checkbox" name="ingredients" value="I0001"> 細拉麵
                <input id="I0002" type="checkbox" name="ingredients" value="I0002"> 烏龍麵
                <input id="I0003" type="checkbox" name="ingredients" value="I0003"> 刀削麵
                <input id="I0004" type="checkbox" name="ingredients" value="I0004"> 蕎麥麵
                <input id="I0005" type="checkbox" name="ingredients" value="I0005"> 墨魚麵
                <input id="I0006" type="checkbox" name="ingredients" value="I0006"> 蔬菜麵
                <br>
               	 湯頭
                <input id="I0007" type="checkbox" name="ingredients" value="I0007"> 醬油湯
                <input id="I0008" type="checkbox" name="ingredients" value="I0008"> 泡菜湯
                <input id="I0009" type="checkbox" name="ingredients" value="I0009"> 牛肉湯
                <input id="I0010" type="checkbox" name="ingredients" value="I0010"> 麻辣湯
                <input id="I0011" type="checkbox" name="ingredients" value="I0011"> 番茄湯
                <input id="I0012" type="checkbox" name="ingredients" value="I0012"> 雞湯
                <input id="I0013" type="checkbox" name="ingredients" value="I0013"> 豚骨湯
                <input id="I0014" type="checkbox" name="ingredients" value="I0014"> 味增湯
                <input id="I0015" type="checkbox" name="ingredients" value="I0015"> 咖哩湯
                <input id="I0015-1" type="checkbox" name="ingredients" value="I0015-1"> 鹽味湯
                <input id="I0015-2" type="checkbox" name="ingredients" value="I0015-2"> 海鮮湯
                <br>
				主餐
                <input id="I0016" type="checkbox" name="ingredients" value="I0016"> 叉燒肉
                <input id="I0017" type="checkbox" name="ingredients" value="I0017"> 炸豬排
                <input id="I0018" type="checkbox" name="ingredients" value="I0018"> 炸雞柳
                <input id="I0019" type="checkbox" name="ingredients" value="I0019"> 唐揚雞塊
                <input id="I0020" type="checkbox" name="ingredients" value="I0020"> 鮮蝦
                <input id="I0021" type="checkbox" name="ingredients" value="I0021"> 龍蝦
                <input id="I0022" type="checkbox" name="ingredients" value="I0022"> 櫻花蝦
                <input id="I0023" type="checkbox" name="ingredients" value="I0023"> 鴨胸
                <input id="I0024" type="checkbox" name="ingredients" value="I0024"> 魷魚圈
                <input id="I0025" type="checkbox" name="ingredients" value="I0025"> 蛤蠣
                <input id="I0026" type="checkbox" name="ingredients" value="I0026"> 虱目魚
                <br>
				副餐
                <input id="I0027" type="checkbox" name="ingredients" value="I0027"> 荷包蛋
                <input id="I0028" type="checkbox" name="ingredients" value="I0028"> 溫泉蛋
                <input id="I0029" type="checkbox" name="ingredients" value="I0029"> 滷蛋
                <input id="I0030" type="checkbox" name="ingredients" value="I0030"> 貢丸
                <input id="I0031" type="checkbox" name="ingredients" value="I0031"> 豆皮
<!--                 <input id="I0032" type="checkbox" name="ingredients" value="I0032"> 筍乾 -->
                <input id="I0033" type="checkbox" name="ingredients" value="I0033"> 小魚乾
                <br>
				配料
                <input id="I0034" type="checkbox" name="ingredients" value="I0034"> 韓式泡菜
                <input id="I0035" type="checkbox" name="ingredients" value="I0035"> 台式泡菜
                <input id="I0036" type="checkbox" name="ingredients" value="I0036"> 海帶
                <input id="I0037" type="checkbox" name="ingredients" value="I0037"> 海苔
                <input id="I0038" type="checkbox" name="ingredients" value="I0038"> 燙青菜
                <br>
				調味
                <input id="I0039" type="checkbox" name="ingredients" value="I0039"> 蔥花
                <input id="I0040" type="checkbox" name="ingredients" value="I0040"> 柴魚
                <input id="I0041" type="checkbox" name="ingredients" value="I0041"> 胡椒
                <input id="I0042" type="checkbox" name="ingredients" value="I0042"> 辣椒
                <input id="I0043" type="checkbox" name="ingredients" value="I0043"> 玫瑰鹽
                <br>
            </td>
        </tr>
    </table>
    
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</form>
</div>
</div>


<footer>
<!--   <p>Built with <a href="https://github.com/bevacqua/dragula" target="_blank">Dragula</a> and Vanilla JS by <a href="http://nikkipantony.com" target="_blank">Nikki Pantony</a></p> -->
</footer>
 <script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
  <script src='https://cdnjs.cloudflare.com/ajax/libs/dragula/3.7.2/dragula.js'></script>

  

    <script  src="js/index.js"></script>
    <script>
        $(function(){
            $('.confirmBtn').on('click', function(){
                var arr = [];
                var list = $('#combination').find('li');
                for(var i=0; i< list.length; i++){
                    var id =$(list[i]).attr('myId');
                    $('#'+id).attr('checked', true);
                }
                $('#sform').submit();
            });
        });
    </script>



</body>

</html>
