<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.menu.model.*, com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="/front_end/header.jsp" flush="true" />
<!--background image-->
<img src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg" width="100%" height="" alt="">
<meta charset="utf-8">
<!-- font aewsome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.0/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/v4-shims.css">
<!-- Bootsraps-->
<script src="https://code.jquery.com/jquery-3.2.1.min.js "></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
<!-- datatable-->


<!--credit card-->
<script src="js/card.js"></script>
<link rel="stylesheet" href="css/card.css">
<!-- address choosing -->
<script src="<%=request.getContextPath()%>/front_end/member/js/selectaddress.js"></script>
<style>
    .list-group-item {
	height: 100px;
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

.errorMsg{
	color:red;
	font-style: oblique;
	font-weight: bold;
	margin-left:50%;
	
}
 .demo-container {
            width: 100%;
            max-width: 350px;
            margin: 50px auto;
        }

        form {
            margin: 30px;
        }
        input {
            width: 200px;
            margin: 10px auto;
            display: block;
        }
        
        .card_data{
        	border-radius:10px;
        	border-radius: 6px;
   		 	border: 1px solid #C1E4FE;
        	 width:250px;
        	margin:auto;
        }
        
        .perchase{
        	border-radius: 50em;
			border: 1px solid #C1E4FE;
			background: #b35900;
			width: 100%;
			height:40px;
			margin-top:10px;
			color: #FFFFFF;
        }
        
        .perchase:hover{
        	cursor: pointer;
    		background: #cc6600;
   			 border: 1px solid #4EB7F5;
        }
        
        .header {
    height: 72px;
    padding: 0 30px;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-align: center;
    align-items: center;
    border-bottom: 1px solid #DFE5EB;
}

.summary {
    opacity: 1;
    width: 366px;
    will-change: transform, opacity;
    -ms-flex-direction: column;
    flex-direction: column;
    background-color: white;
    position: relative;
    border-radius: 6px;
    width: 100%;	
    margin: 0px 0 0 0;
    padding: 0;
    list-style: none;
    opacity:0.8;
}

.detPurchase {
    padding: 30px;
    overflow: hidden;
    transform-origin: top center;
}

.summary li {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-pack: justify;
    justify-content: space-between;
    font-size: 14px;
    position: relative;
    color: #162D3D;
    margin-bottom: 12px;
    line-height: 18px;
    font-family: "Helvetica55";
}

@media (max-width: 37.5em)
.summary {
    width: 100%;
}

.summary li:last-child {
    padding: 12px 0;
    margin: 24px 0 0 0;
    border-top: 1px solid #DFE5EB;
    border-bottom: 1px solid #DFE5EB;
    font-family: "Helvetica65";
}

.detPurchase span {
    margin: 18px 0 0 0;
    font-size: 14px;
    color: #32536A;
    font-family: "Helvetica55";
    width: 100%;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-pack: center;
    justify-content: center;
    -ms-flex-align: center;
    align-items: center;
    
}




</style>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>

<body background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png" width="100%">
    <%
// 		MemberService memSrv = new MemberService();         //模擬登入
// 		MemberVO memVO = memSrv.getOne_Member("M000005");
// 		System.out.println(memVO);
// 		session.setAttribute("memVO", memVO);
	%>
    <div class="container col-12" style="height: 400px">
        <div class="row">
            <div class="col-md-4" style="margin: 40px">
                <form method="post" action="storedrecord.do">
                    <div class="col-12 col-md-12 summary" style="padding:30px;">
                        <h3>付款方式</h3>
                        <select class="custom-select">
                            <option selected>Open this select menu</option>
                            <option value="1">信用卡</option>
                        </select>
                        <h3 style="margin-top:15px;">儲值帳戶</h3>
                        <select class="custom-select" name="mem_No">
                            <option selected>Open this select menu</option>
                            <option value="${memVO.mem_No}" ${(memVO.mem_No==mem_No)? 'selected' :'' }>${mem_Id}</option>
                        </select>
                        <input type="hidden" name="stor_Point" value="${stor_Point}">
                    </div>
                    <!-- credit card -->
                    <div id="card" class="demo-container col-12 col-md-6" style="margin: 10px;margin-top:50px;">
                        <div class="card-wrapper" style="margin-left: 0px; width: 350px;"></div>
                        <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/270939/icons-cards.svg" type=""></img>
                        <div class="form-container active" style="margin: 10px;width:350px;">
                            <div class="form-inline">
                                <input class="card_data" placeholder="Card number" type="tel" name="number" style="margin: 5px;" value="${card_number}">
                                <div class='errorMsg' style="margin-left: 0px;">${errorMsgs.card_number}</div>
                            </div>
                            <div class="form-inline">
                                <input class="card_data" placeholder="Full name" type="text" name="name" style="margin: 5px;" value="${name}">
                                <div class='errorMsg' style="margin-left: 0px;">${errorMsgs.full_name}</div>
                            </div>
                            <div class="form-inline">
                                <input class="card_data" placeholder="MM/YY" type="tel" name="expiry" style="margin: 5px;" value="${expiry}">
                                <div class='errorMsg' style="margin-left: 0px;">${errorMsgs.expiry}</div>
                            </div>
                            <div class="form-inline">
                                <input class="card_data" placeholder="CVC" type="number" name="cvc" style="margin: 5px;" value="${cvc}">
                                <div class='errorMsg' style="margin-left: 0px;">${errorMsgs.cvc}</div>
                            </div>
                            <div>
                                <!--                     <a href="addNewtransaction.jsp" class="btn btn-light">上一步</a> -->
                            </div>
                        </div>
						<button type="button" class="btn btn-sm" style="margin-top:5px;" onclick="magic()"><i class="far fa-plus-square"><b>神奇小按鈕</b></i></button>
                    </div>


            </div>
            <div class="col-10 col-sm-8 col-md-4 .offset-md-6" style="margin-top: 69px;margin-left:160px;">
                <div class="summary stepCard">
                    <div class="header">
                        <h3> 要購買的商品為:</h3>
                    </div>
                    <div class="detPurchase">
                        <ul class="summary">
                            <li class="sProduct">
                                <b>${stor_Point} 竹幣</b>
                                <c>$ ${stor_Point}</c>
                            </li>
                            <li class="sSubtotal">
                                <b>Subtotal</b>
                                <c>$ ${stor_Point}</c>
                            </li>
                            <li class="sVat">
                                <b>VAT (17.00%)</b>
                                <c>$ ${stor_Point*0.17}</c>
                            </li>
                            <li class="sTotal">
                                <b>Total</b>
                                <c>$ ${stor_Point*1.17}</c>
                            </li>
                        </ul>
                        <button type="submit" class="perchase" name="action" value="insertWithMemUpdate"><img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/270939/Lock.svg" type="image/svg+xml" style="margin:5px; border-radius: 50em;"><b>確認購買</b></button><br>
                        <span style="width: 100%;"><img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/270939/icon-sslSmall.svg" type="image/svg+xml" style="margin-right:20px;"></img>Safe & Secure Payment</span>
                        </form>
                    </div>
                    <div class="errorMsg center" style="margin: auto;">${errorMsgs.stor_failur}</div>
	
                </div>
            </div>
        </div>
        <jsp:include page="/front_end/footer.jsp" />
        <script>
            new Card({
                form: document.querySelector('form'),
                container: '.card-wrapper'
            });
            
            function magic(){
            	$('.card_data').eq(0).val('4539 4201 1635 1760');
            	$('.card_data').eq(1).val('Nash-Lin');
            	$('.card_data').eq(2).val('05 / 22');
            }

        </script>
</body>

</html>
