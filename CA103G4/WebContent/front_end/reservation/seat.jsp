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
    
    <body onload="connect();" onunload="disconnect();">
<%@ include file="/front_end/header.jsp" %>
<body background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "
width="100%" height="">
<!-- header勿動 -->
<img src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg"
width="100%" height="" alt=""> 	
    	<nav class="navbar navbar-expand-lg mainbar">
             <div class="container-fluid list-group-flush">
                <div class="row-fluid ">
			        <h1> Reservation Room </h1>
				    <h3 id="statusOutput" class="statusOutput"></h3>
<!-- 				    <h5 id="peopleOutput" class="peopleOutput"></h5> -->
				    <input id="people" class="text-field" type="text" placeholder="訂位人數" value="${resVO.res_people}" disabled/> 
				    <form id="seatForm" action = "<%=request.getContextPath()%>/desk.do" method="post">
				     <input type="hidden" id="finalSeat" value="" name="finalSeat">
				     <div style="padding:20px" class="input-group">           
			           <div class="seat" Id="A1">A1</div>
			           &nbsp;&nbsp;
			           <div class="seat" Id="A2">A2</div>
			           &nbsp;&nbsp;
			           <div class="seat" Id="A3">A3</div>
			           &nbsp;&nbsp;
			           <div class="seat" Id="A4">A4</div>
			           &nbsp;&nbsp;
			           <div class="seat" Id="A5">A5</div>
			           &nbsp;&nbsp;
			           <div class="seat" Id="A6">A6</div>
			         </div>
					 <div style="padding:20px" class="input-group">           
			           <div class="seat" Id="B1">B1</div>
			           &nbsp;&nbsp;
			           <div class="seat" Id="B2">B2</div>
			           &nbsp;&nbsp;
			           <div class="seat" Id="B3">B3</div>
			           &nbsp;&nbsp;
			           <div class="seat" Id="B4">B4</div>
			           &nbsp;&nbsp;
			           <div class="seat" Id="B5">B5</div>
			           &nbsp;&nbsp;
			           <div class="seat" Id="B6">B6</div>
			          </div>	
					  <a href="#" class="log-in"><i class="fa fa-cog" aria-hidden="true" onClick="disconnect();"></i>取消訂位</a>
			          &nbsp; 
			          <button id="seatSubmit" type="button" class="btn btn-primary">確認送出</button>
			          <input type="hidden" name="action" value="Seats"> 
	          
			          </form> 
			    </div>
              </div>
           </nav>
		  
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <!--    css-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/reservation/css/seat.css">
    <!--  icon   -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    </body>
 <%@ include file="/front_end/footer.jsp" %>    
<script>
	
    var zone = "${bgtime}";
    var date = "${date}";
	var unSpltString;
	var Count = 0;
    var mem_no = "${resVO.mem_no}";
    var seatSet  = new Set();
    var finalSeat="";
    var MyPoint = "/MyEchoServer/" + mem_no +"/"+ date +"/"+ zone;
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
  //var endPointURL = "ws://localhost:8081/IBM_WebSocket1_Chat/MyEchoServer";
    
	var statusOutput = document.getElementById("statusOutput");
// 	var onlinePeople = document.getElementById("onlinePeople");
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
	    	console.log(mem_no);
	    	console.log(date);
	    	console.log(zone);
			updateStatus("WebSocket 成功連線");
// 			console.log("--",date);
// 			console.log("++",zone);
				zeroPush();
				function zeroPush(){
				    $.ajax({
				  		url: "<%=request.getContextPath()%>/res/res.do", 
				  		data:{"action":"pushSeat", "date":date, "zone":zone},
				  		success: function(result){
				  			console.log("---------------")
				//	  		if(result==""){
				//	  			zeroPush();
				//	  		}
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
	        	
	        }else if(jsonObj.date === date && jsonObj.zone === zone && jsonObj.mem_no !== mem_no && jsonObj.seatChange === "#F0F8FF"){ //1.所有非當事人被推波消息  2.當事人藍轉白色

			document.getElementById(jsonObj.seat).style.backgroundColor = jsonObj.seatChange;
			alert(jsonObj.seat + "座位已經開放瞜，請盡快搶位")
			
	        }else{
// 	        	console.log(event.data);
	        };
	        
		};	
		//OnClose
		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
		
		//被呼叫推播方法 
	        
	}
//---------------------------------------------------------------------	

	var count=0;
	
	var inputPeople = document.getElementById("people");
	

	$(".seat").click(function sendMessage(e){
		
		var people = inputPeople.value.trim();
		var inputSeat = e.target;
		
		    if (people === ""){  
		        alert ("人數請勿空白!");
				
		        inputPeople.focus();
				
				return;
		    }else if(people == count){   
		    	
		    	 if (inputSeat.style.backgroundColor === "red"){
			 	        alert ("人數已達上限,且此座位已被選");	
			 	        
			 	        
			 	    }else if(inputSeat.style.backgroundColor === "blue"){ 
			 	    	inputSeat.style.backgroundColor = "#F0F8FF";
			 	        alert("已取消此訂位");
			 	        count--;
			 	        var seatChange =(inputSeat.style.backgroundColor = "#F0F8FF");
			 	    	var seat = inputSeat.innerHTML.trim();
			 	        var jsonObj = {"seat" : seat, "seatChange" : seatChange, "mem_no" : mem_no, "status" : 3, "date" : date, "zone" : zone};
			 	        webSocket.send(JSON.stringify(jsonObj));
			 	        
			 	        
			 	    }else{  
			 	    	alert ("選擇人數已達上限!");
			 	        
			 	    } 
			 	    console.log("Selected Seat:" + count);
		    	
		    }else if(people > count){
		    	if (inputSeat.style.backgroundColor === "red"){
		 	        alert ("此座位已被選");	
		 	        
		 	        
		 	    }else if(inputSeat.style.backgroundColor === "blue"){ 
		 	    	inputSeat.style.backgroundColor = "#F0F8FF";
		 	        alert("已取消此訂位");
		 	        count--;
		 	        var seatChange =(inputSeat.style.backgroundColor = "#F0F8FF");
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
		 	        
		 	        
		 	    }else{
		 	    	inputSeat.style.backgroundColor = "blue"; 
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
    
</script>



</html>
