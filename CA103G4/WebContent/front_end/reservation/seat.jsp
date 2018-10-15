<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
    <head>
         
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    <!--     Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </head>
    
<style>
        @font-face {
            font-family: 'Senty Golden Bell';
            src: url(/CA103G4/front_end/fonts/SentyGoldenBell.woff2) format('woff2'),
                url(/CA103G4/front_end/fonts/SentyGoldenBell.woff) format('woff');
            font-weight: normal;
            font-style: normal;
        }
</style>        
    
    <body onload="connect();" onunload="disconnect();">
<%@ include file="/front_end/header.jsp" %>
<body background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "
width="100%" height="">
<!-- header勿動 -->

				<div class="title-box">
					<h1 class="titleDrew"> 歡迎來到竹風堂</h1>
				    <h3 id="statusOutput" class="statusOutput"></h3>

				    <input id="people" class="text-field" type="text" placeholder="訂位人數" value="${resVO.res_people}" style="display:none;" disabled/> 
				</div> 
				<div class="seatfood">  
                    <div class="seatfood2"> 
				    <form id="seatForm" action = "<%=request.getContextPath()%>/desk.do" method="post">
				     <input type="hidden" id="finalSeat" value="" name="finalSeat">
				   
				     <div style="padding:20px" class="input-group">           
			           <div class="seat A1" Id="A1">A1</div>
			           &nbsp;&nbsp;
			           <div class="seat A2" Id="A2">A2</div>
			           &nbsp;&nbsp;
			           <div class="seat A3" Id="A3">A3</div>
			           &nbsp;&nbsp;
			           <div class="seat A4" Id="A4">A4</div>
			           &nbsp;&nbsp;
			           <div class="seat A5" Id="A5">A5</div>
			           &nbsp;&nbsp;
			           <div class="seat A6" Id="A6">A6</div>
			            &nbsp;&nbsp;
			           <div class="seat A7" Id="A7">A7</div>
			            &nbsp;&nbsp;
			           <div class="seat A8" Id="A8">A8</div>
			            &nbsp;&nbsp;
			           <div class="seat A9" Id="A9">A9</div>
			            &nbsp;&nbsp;
			           <div class="seat A10" Id="A10">A10</div>
			            &nbsp;&nbsp;
			           <div class="seat B1" Id="B1">B1</div>
			            &nbsp;&nbsp;
			           <div class="seat B2" Id="B2">B2</div>
			            &nbsp;&nbsp;
			           <div class="seat B3" Id="B3">B3</div>
			            &nbsp;&nbsp;
			           <div class="seat B4" Id="B4">B4</div>
			            &nbsp;&nbsp;
			           <div class="seat B5" Id="B5">B5</div>
			            &nbsp;&nbsp;
			           <div class="seat B6" Id="B6">B6</div>
			            &nbsp;&nbsp;
			           <div class="seat B7" Id="B7">B7</div>
			            &nbsp;&nbsp;
			           <div class="seat B8" Id="B8">B8</div>
			            &nbsp;&nbsp;
			           <div class="seat B9" Id="B9">B9</div>
			            &nbsp;&nbsp;
			           <div class="seat B10" Id="B10">B10</div>
                         &nbsp;&nbsp;
			           <div class="seat B11" Id="B11">B11</div>
			            &nbsp;&nbsp;
			           <div class="seat B12" Id="B12">B12</div>
			            &nbsp;&nbsp;
			           <div class="seat B13" Id="B13">B13</div>
			            &nbsp;&nbsp;
			           <div class="seat B14" Id="B14">B14</div>
                          &nbsp;&nbsp;
			           <div class="seat B15" Id="B15">B15</div>
			            &nbsp;&nbsp;
			           <div class="seat B16" Id="B16">B16</div>
			            &nbsp;&nbsp;
			           <div class="seat B17" Id="B17">B17</div>
			            &nbsp;&nbsp;
			           <div class="seat B18" Id="B18">B18</div>
			            &nbsp;&nbsp;
			           <div class="seat B19" Id="B19">B19</div>
			            &nbsp;&nbsp;
			           <div class="seat B20" Id="B20">B20</div>
			            &nbsp;&nbsp;
			           <div class="seat C1" Id="C1">C1</div>
			            &nbsp;&nbsp;
			           <div class="seat C2" Id="C2">C2</div>
			            &nbsp;&nbsp;
			           <div class="seat C3" Id="C3">C3</div>
			            &nbsp;&nbsp;
			           <div class="seat C4" Id="C4">C4</div>
			            &nbsp;&nbsp;
			           <div class="seat C5" Id="C5">C5</div>
			            &nbsp;&nbsp;
			           <div class="seat C6" Id="C6">C6</div>
			            &nbsp;&nbsp;
			           <div class="seat C7" Id="C7">C7</div>
			            &nbsp;&nbsp;
			           <div class="seat C8" Id="C8">C8</div>
			            &nbsp;&nbsp;
			           <div class="seat C9" Id="C9">C9</div>
			            &nbsp;&nbsp;
			           <div class="seat C10" Id="C10">C10</div>
			           &nbsp;&nbsp;
			           <div class="seat C11" Id="C11">C11</div>
			            &nbsp;&nbsp;
			           <div class="seat C12" Id="C12">C12</div>
			            &nbsp;&nbsp;
			           <div class="seat C13" Id="C13">C13</div>
			            &nbsp;&nbsp;
			           <div class="seat C14" Id="C14">C14</div>
                          &nbsp;&nbsp;
			           <div class="seat C15" Id="C15">C15</div>
			            &nbsp;&nbsp;
			           <div class="seat C16" Id="C16">C16</div>
			            &nbsp;&nbsp;
			           <div class="seat C17" Id="C17">C17</div>
			            &nbsp;&nbsp;
			           <div class="seat C18" Id="C18">C18</div>
			            &nbsp;&nbsp;
			           <div class="seat C19" Id="C19">C19</div>
			            &nbsp;&nbsp;
			           <div class="seat C20" Id="C20">C20</div>
			           &nbsp;&nbsp;
			           <div class="seat C21" Id="C21">C21</div>
			            &nbsp;&nbsp;
			           <div class="seat C22" Id="C22">C22</div>
			            &nbsp;&nbsp;
			           <div class="seat C23" Id="C23">C23</div>
			            &nbsp;&nbsp;
			           <div class="seat C24" Id="C24">C24</div>
			            &nbsp;&nbsp;
			           <div class="seat C25" Id="C25">C25</div>
			            &nbsp;&nbsp;
			           <div class="seat C26" Id="C26">C26</div>
			            &nbsp;&nbsp;
			           <div class="seat C27" Id="C27">C27</div>
			            &nbsp;&nbsp;
			           <div class="seat C28" Id="C28">C28</div>
			            &nbsp;&nbsp;
			           <div class="seat C29" Id="C29">C29</div>
			            &nbsp;&nbsp;
			           <div class="seat C30" Id="C30">C30</div>
			           &nbsp;&nbsp;
			           <div class="seat C31" Id="C31">C31</div>
			            &nbsp;&nbsp;
			           <div class="seat C32" Id="C32">C32</div>
			            &nbsp;&nbsp;
			           <div class="seat D1" Id="D1">D1</div>
			            &nbsp;&nbsp;
			           <div class="seat D2" Id="D2">D2</div>
			            &nbsp;&nbsp;
			           <div class="seat D3" Id="D3">D3</div>
			            &nbsp;&nbsp;
			           <div class="seat D4" Id="D4">D4</div>
			            &nbsp;&nbsp;
			           <div class="seat D5" Id="D5">D5</div>
			            &nbsp;&nbsp;
			           <div class="seat D6" Id="D6">D6</div>
			            &nbsp;&nbsp;
			           <div class="seat D7" Id="D7">D7</div>
			            &nbsp;&nbsp;
			           <div class="seat D8" Id="D8">D8</div>
			            &nbsp;&nbsp;
			           <div class="seat D9" Id="D9">D9</div>
			            &nbsp;&nbsp;
			           <div class="seat D10" Id="D10">D10</div>
			           &nbsp;&nbsp;
			           <div class="seat D11" Id="D11">D11</div>
			            &nbsp;&nbsp;
			           <div class="seat D12" Id="D12">D12</div>
			            &nbsp;&nbsp;
			           <div class="seat D13" Id="D13">D13</div>
			            &nbsp;&nbsp;
			           <div class="seat D14" Id="D14">D14</div>
                          &nbsp;&nbsp;
			           <div class="seat D15" Id="D15">D15</div>
			            &nbsp;&nbsp;
			           <div class="seat D16" Id="D16">D16</div>
			            &nbsp;&nbsp;
			           <div class="seat D17" Id="D17">D17</div>
			            &nbsp;&nbsp;
			           <div class="seat D18" Id="D18">D18</div>
			           
			           <div class="D-blue">
			           <div class="seat D-blue-seat" >藍</div>
                       <text>藍色位子為<b>您已選位子</b>。</text>
                       </div>
                       <div class="D-red">
			           <div class="seat D-red-seat" >紅</div>
			           <text>紅色位子為<b>已被訂位子,不可選</b>。</text>
			           </div>
			           <div class="D-white">
			           <div class="seat D-white-seat">白</div>
			           <text>白色位子<b>為開放位子，要搶要快喔!!!</b></text>
			           </div>
			         </div>
			       		
					  <button href="#" class="cancel" onClick="disconnect();"><i class="fa fa-cog" aria-hidden="true" ></i>取消訂位</button>
			          &nbsp; 
			          <button id="seatSubmit" type="button" class="submitSeat"  onClick="successSubmit ();">確認送出 <i class="fas fa-utensils"></i></button>
			          <input type="hidden" name="action" value="Seats"> 
	          
			          </form>
			       </div>  
				</div>
<%-- <%@ include file="/front_end/footer.jsp" %> 		   --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <!--    css-->
    <link rel="stylesheet" href="/CA103G4/front_end/reservation/css/seat.css">
    <!--  icon   -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    </body>
   
<script>
	
    var zone = "${bgtime}";
    var date = "${date}";
	var unSpltString;
	var Count = 0;
    var mem_no = "${memVO.mem_No}";
    var trigger;
    var seatSet  = new Set();
    var finalSeat="";
    var MyPoint = "/MyEchoServer/" + mem_no +"/"+ date +"/"+ zone +"/"+ trigger +"/";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
  //var endPointURL = "ws://localhost:8081/IBM_WebSocket1_Chat/MyEchoServer";
    
	var statusOutput = document.getElementById("statusOutput");

	var webSocket;
	
	$(function(){
		$('#seatSubmit').click(function(){
			$('#finalSeat').val(finalSeat);
			$('#seatForm').submit();
		});
	});
	
	function connect() {
		// 建立 websocket 物件
	    webSocket = new WebSocket(endPointURL);//觸發右邊@OnOpen
		
		webSocket.onopen = function(event) {
			zeroPush();
	    	console.log("(seat.jsp)員工編號:",mem_no);
	    	console.log("(seat.jsp)日期:",date);
	    	console.log("(seat.jsp)時段:",zone);
			updateStatus("歡迎來到竹風堂,訂位系統已開啟!");
	
				function zeroPush(){
				    $.ajax({
				  		url: "<%=request.getContextPath()%>/res.do", 
				  		data:{"action":"pushSeat", "date":date, "zone":zone},
				  		success: function(result){
				  			console.log("---------------")
				
				  		unSpltString = result;
				  		console.log("位子推播", unSpltString);
				  		var tokens = unSpltString.split(":");
					  		tokens.forEach(function(element) {
					  			console.log(element);
					  			if(element !== ""){
					  			document.getElementById(element).style.backgroundColor = "red";
					  			}else{
					  				//引開空字串
					  			}
					  		});
				  	}});
				}
		};
		
		//OnMessage		
		webSocket.onmessage = function(event) {
			
			
	        var jsonObj = JSON.parse(event.data);
			
	        if(jsonObj.date === date && jsonObj.zone === zone && jsonObj.mem_no !== mem_no && jsonObj.seatChange === "blue"){ //1.不是當前使用者，被推播紅色 2.當事者已選擇變藍色
	        	jsonObj.seatChange = "red";
	        	document.getElementById(jsonObj.seat).style.backgroundColor = jsonObj.seatChange;
	        	
	        }else if(jsonObj.date === date && jsonObj.zone === zone && jsonObj.mem_no !== mem_no && jsonObj.seatChange == "aliceblue"){ //1.所有非當事人被推波消息  2.當事人藍轉白色

			document.getElementById(jsonObj.seat).style.backgroundColor = jsonObj.seatChange;
			alert(jsonObj.seat + "座位已經開放瞜，請盡快搶位")
			
	        }else{
// 	        	console.log(event.data);
	        };
	        
		};	
		//OnClose
		webSocket.onclose = function(event) {
			updateStatus("歡迎來到竹風堂,訂位系統已關闢");
		};
		
		//被呼叫推播方法 
	        
	}
//---------------------------------------------------------------------	

	var count=0;
	
	var inputPeople = document.getElementById("people");
	

	$(".seat").click(function sendMessage(e){
		
		var people = inputPeople.value.trim();
		var inputSeat = e.target;
		
		    if (people === ""){  // 1/3:空白
		        alert ("人數請勿空白!");
				// 	          跳出視窗
		        inputPeople.focus();
				// 自動回到要輸入的地方(已點擊)
				return;
		    }else if(people == count){   // 2/3: 人數達上限
		    	
		    	 if (inputSeat.style.backgroundColor === "red"){
			 	        alert ("人數已達上限,且此座位已被選");	
			 	        
			 	        
			 	    }else if(inputSeat.style.backgroundColor === "blue"){ //當事人取消選取座位
			 	    	inputSeat.style.backgroundColor = "aliceblue";
			 	        alert("已取消此訂位");
			 	        count--;
			 	        var seatChange =(inputSeat.style.backgroundColor = "aliceblue");
			 	    	var seat = inputSeat.innerHTML.trim();
			 	        var jsonObj = {"seat" : seat, "seatChange" : seatChange, "mem_no" : mem_no, "status" : 3, "date" : date, "zone" : zone};
			 	        webSocket.send(JSON.stringify(jsonObj));
			 	        //推波給非當事人，位子開放
			 	        
			 	    }else{  //選取白色
			 	    	alert ("選擇人數已達上限!");
			 	        
			 	    } 
			 	    console.log("Selected Seat:" + count);
		    	
		    }else if(people > count){
		    	if (inputSeat.style.backgroundColor == "red"){
		 	        alert ("此座位已被選");	
		 	        
		 	        
		 	    }else if(inputSeat.style.backgroundColor == "blue"){ //當事人取消選取座位
		 	    	inputSeat.style.backgroundColor = "aliceblue";
		 	        alert("已取消此訂位");
		 	        count--;
		 	        var seatChange =(inputSeat.style.backgroundColor = "aliceblue");
		 	    	var seat = inputSeat.innerHTML.trim();
		 	    	var seletedSeat = "";
		 	    	seatSet.delete(seat + ":");
		 	    	seatSet.forEach(function (item) {
			 	    	seletedSeat	+= item.toString() 		 	    	   
			 	    	   console.log("以選位子",seletedSeat);
			 	    	});
		 	    	finalSeat = seletedSeat;
		 	    	console.log("final",finalSeat);
		 	    	
		 	        var jsonObj = {"seat" : seat, "seatChange" : seatChange, "mem_no" : mem_no, "status" : 2, "date" : date, "zone" : zone};
		 	        webSocket.send(JSON.stringify(jsonObj));
		 	        
		 	        //推波給非當事人，位子開放
		 	    }else{
		 	    	inputSeat.style.backgroundColor = "blue"; //選取一般座位
// 		 	    	alert("成功選取座位");
		 	    	count++;
		 	    	var seatChange =(inputSeat.style.backgroundColor = "blue"); 
		 	    	var seat = inputSeat.innerHTML.trim();
		 	    	var seletedSeat = "";
		 	    	seatSet.add(seat + ":");
		 	    	seatSet.forEach(function (item) {
			 	    	seletedSeat	+= item.toString() 		 	    	   
			 	    	   console.log("以選位子",seletedSeat);
			 	    	});
		 	    	finalSeat = seletedSeat;
		 	    	console.log("final",finalSeat);
		 	        var jsonObj = {"seat" : seat, "seatChange" : seatChange, "mem_no" : mem_no, "status" : 1, "date" : date, "zone" : zone};//"{" + "}" --->json 物件的輸入法 裡面放name、value用 " : " 隔中間
		 	        webSocket.send(JSON.stringify(jsonObj));//觸發右邊的 @Onmessage
		 	        
		 	    } 
		 	    console.log("Personal Selected Seat:" + count);
		    }
		  			 	   
	     })   

	
	function disconnect () {
		webSocket.close();
		
	}

	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
	
	function successSubmit () {
		trigger = 'a';
		
	}
    
</script>



</html>
