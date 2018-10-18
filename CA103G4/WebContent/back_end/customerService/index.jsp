<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<jsp:include page="/back_end/PostHeader.jsp" flush="true" />
<%-- <img src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg" width="100%" height="" alt=""> --%>

<head>
    <meta charset="BIG5">
    <title>chat</title>
    <!--css-->
    <link rel="stylesheet" href="chatroom.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js "></script>
    <style>
        #userName {
	width: 50px;
}

#userName {
	width: 65px !important;
	/* 	margin-left: 10px; */
	/* 	margin-right: 10px; */
}

#message {
	/* 	border-radius: 5px; */
	
}

.ChatMessageRight {
	padding: 0px 15px 0px 15px;
	text-align: right;
}

.ChatMessageLeft {
	padding: 0px 15px 0px 15px;
}

.ChatBubbleRight {
	background-color: antiquewhite;
	border-radius: 5px;
	padding: 5px 8px 6px;
}

.ChatBubbleLeft {
	background-color: #d9d9d9;
	border-radius: 5px;
	padding: 5px 8px 6px;
}

#ChatZone {
	width: 400px;
	padding: 0px;
}

#MessageList {
	overflow-y: auto;
}

#chatbody {
	background-color: burlywood;
	height: 650px;
}

#message {
	/* 	margin	:5px; */
	resize: none;
	width: 100%;
}

#service {
	color: white;
}

.center {
	width: auto;
	display: table;
	margin-left: auto;
	margin-right: auto;
	margin-top: auto;
	margin-bottom: auto;
}

.text-center {
	text-align: center;
}

.fa-user-circle {
	margin: 15px;
	font-size: 50px
}

.ChatMessageRight.fa-user-circle {
	right: 0px;
}

.ChatMessageLeft.fa-user-circle {
	left: 0px;
}

.fa-user-circle {
	margin: 8px;
}

#send {
	background-color: white;
}

#send:hover {
	color: dodgerblue;
}

.sysMsg {
	width: 100%;
	text-align: center;
	color: #999999;
	margin: 5px 0px;
	font-size: 13px
}

body{
/*     background-image: url(ad_03.png); */
    opacity: 0.9;
}
</style>
</head>

<body id='chatbody' onload="connect();" onunload="disconnect();" style="">
    <div id="ChatZone" class="col-12 col-md-12 float-right" style="height: 80%;">
        <nav class="navbar navbar-dark bg-dark ">
            <!-- Navbar content -->
            <h3 id='service' class="center">Customer Service</h3>
        </nav>
        <div id="ChatZone" class="" style="background-color: white; height: 65%;">
            <div id="MessageList" style="height: 100%;">
                <!-- 	<textarea readonly name="" id="dialog" cols="48" rows="20"></textarea> -->
            </div>
            <div class="form-inline">
                <textarea id="message" rows="3" cols="46" class="text-field form-control" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage();"></textarea>
                <!--                <input type="submit" id="sendMessage" class="btn btn-primary" value="送出" onclick="sendMessage();" />-->
                <button type="button" class="badge badge-pill badge-warning" onclick="can1()">問候1</button>
                <button type="button" class="badge badge-pill badge-warning" onclick="can2()">問候2</button>
                <button type="button" class="badge badge-pill badge-warning" onclick="can3()">日期</button>
                <button type="button" class="badge badge-pill badge-warning" onclick="can4()">餐點</button>
                <button type="button" class="badge badge-pill badge-warning" onclick="can5()">稍等</button>
                <button type="button" class="badge badge-pill badge-warning" onclick="can6()">反應</button>
                <button type="button" class="badge badge-pill badge-warning" onclick="can7()">迴避</button>
                <div id="send" class="btn col-12 d-flex">
                    <div class="mr-auto">
                        <input id="userName" class="text-field form-control" type="text"  value="${empVO.emp_Name}" readonly style="width:100% !important;"/>
                    </div>
                    <div class="ml-auto">
                        <input type="button" id="connect" class="btn" value="連線" onclick="connect();" /> <input type="button" id="disconnect" class="btn" value="離線" onclick="disconnect();" /> <i class="fas fa-location-arrow ml-auto" onclick="sendMessage();"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        var point = "/CustomerService/" + $('#userName').val()+"/"+"E000000002";
        var host = window.location.host; //localhost:8081
        var path = window.location.pathname; ///privateChatRoom/index.jsp
        var webCtx = path.substring(0, path.indexOf('/', 1));
        var endPointUrl = "ws://" + host + webCtx + point;

        var webSocket;
        var outputmessage;

        function connect() {
            webSocket = new WebSocket(endPointUrl);
            //連線成功送出系統訊息
            webSocket.onopen = function(event) {
                console.log(inputusername.value.trim() + " connected!");
                var jsonObj = {

                    "username": inputusername.value.trim(),
                    "type": 'sysMsg',
                    'message': ' 連線客服系統成功!'
                };
                webSocket.send(JSON.stringify(jsonObj));
                document.getElementById('connect').disabled = true;
                document.getElementById('disconnect').disabled = false;
                document.getElementById('userName').readOnly = true;
            };

            webSocket.onmessage = function(event) {
                //                 var dialog = document.getElementById("dialog");
                var jsonObj = JSON.parse(event.data);
                var id = jsonObj.username;
                var msg = jsonObj.message + "\n";
                var time = jsonObj.time;
                var picEmp = jsonObj.picEmp;
                var picMem = jsonObj.picMem;
                if (inputusername.value.trim() == jsonObj.username && jsonObj.type == 'userMsg') {
                    // 					dialog.style.textAlign = "right";
                    $('#MessageList')
                        .append(
                            '<div class=ChatMessageRight>' + picEmp + '<div class= ChatBubbleRight>' +
                            msg + '<br><sapn class=sysMsg>' + time + '</span></div></div>');
                } else if (inputusername.value.trim() != jsonObj.username && jsonObj.type == 'userMsg') {
                    // 					dialog.style.textAlign = "left";
                    $('#MessageList').append(
                        '<div class=ChatMessageLeft>' + picMem + '<span>' +
                        id +'</span><div class= ChatBubbleLeft>' +
                        msg + '<br><sapn class=sysMsg>' + time + '</div></div>');

                } else if (jsonObj.type == 'sysMsg'&& inputusername.value.trim() == jsonObj.username) {
                    $('#MessageList').append(
                        '<div class=sysMsg>客服人員 ' + id+" "+ msg + '</div></div>');
                    //     				alert(event.data);
                }
                document.getElementById('MessageList').scrollTop =  document.getElementById('MessageList').scrollHeight;	
            };
           
            webSocket.onclose = function(event) {
                console.log("disconnected!");
            }
        }

        var inputusername = document.getElementById("userName");
        var inputmessage = document.getElementById("message");

        function sendMessage() {

            var jsonObj = {

                "username": inputusername.value.trim(),
                "message": inputmessage.value,
                "type": 'userMsg',
                'time': new Date().toLocaleString(),
                'picEmp': '<img class="nav-item " src="<%=request.getContextPath()%>/empshow.do?emp_No=${(empVO == null)? '': empVO.emp_No} " class="drew" style="height:50px;width:50px;border-radius:50%;display:${(empVO == null )? 'none': ''}">'
            };

            webSocket.send(JSON.stringify(jsonObj));
            outputmessage = JSON.stringify(jsonObj); //save output message
            inputmessage.value = "";
            inputmessage.focus();
        }

        function disconnect() {

            webSocket.close();
            //             document.getElementById('sendMessage').disabled = true;
            document.getElementById('connect').disabled = false;
            document.getElementById('disconnect').disabled = true;
//             document.getElementById('userName').readOnly = true;

        }
		
        function can1(){
        	$('#message').val('竹風堂您好~');
        	sendMessage();
        }
        
        function can2(){
        	$('#message').val('請問有什麼可以幫助您的嗎?');
        	sendMessage();
        }
        
        function can3(){
        	$('#message').val('請問您的用餐日期是?');
        	sendMessage();
        }
        
        function can4(){
        	$('#message').val('請問您所點選的餐點是?');
        	sendMessage();
        }
        
        function can5(){
        	$('#message').val('好的, 請問您稍等一會。');
        	sendMessage();
        }
        
        function can6(){
        	$('#message').val('好的, 關於您的提議我們會像主管反應的。');
        	sendMessage();
        }
        
        function can7(){
        	$('#message').val('系統忙線中..請稍後再撥。');
        	sendMessage();
        }
        
    </script>
    <jsp:include page="/back_end/PostFooter.jsp" flush="true" />
</body>

</html>
