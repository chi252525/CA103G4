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
<script src="<%=request.getContextPath()%>/front_end/shoppingCart/js/card.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/shoppingCart/css/card.css">
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
</style>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>

<body>
    <%
		MemberService memSrv = new MemberService();         //模擬登入
		MemberVO memVO = memSrv.getOne_Member("M000005");
		System.out.println(memVO);
		session.setAttribute("memVO", memVO);
	%>
    <div class="container col-12" style="height: 400px">
        <div class="row">
            <div class="col-md-6" style="margin: 60px">
                <form method="post" action="storedrecord.do">
                    <h3>付款方式</h3>
                    <select class="custom-select">
                        <option selected>Open this select menu</option>
                        <option value="1">信用卡</option>
                    </select>
                    <h3>儲值帳戶</h3>
                    <select class="custom-select" name="mem_No">
                        <option selected>Open this select menu</option>
                        <option value="${memVO.mem_No}">${memVO.mem_Id}</option>
                    </select>
						<input type="hidden" name="stor_Point" value="${stor_Point}">
						<input type="hidden" name="stor_Status" value="1">
                    <!-- credit card -->
                    <div id="card" class="demo-container col-12 col-md-6" style="margin: 10px;">
                        <div class="card-wrapper" style="margin-left: 0px; width: 350px;"></div>
                        <div class="form-container active" style="margin: 10px;">
                            <div class="form-inline">
                                <input placeholder="Card number" type="tel" name="number" style="margin: 5px;" value="${card_number}">
                                <div class='errorMsg' style="margin-left: 0px;">${errorMsgs.card_number}</div>
                            </div>
                            <div class="form-inline">
                                <input placeholder="Full name" type="text" name="name" style="margin: 5px;" value="${name}">
                                <div class='errorMsg' style="margin-left: 0px;">${errorMsgs.full_name}</div>
                            </div>
                            <div class="form-inline">
                                <input placeholder="MM/YY" type="tel" name="expiry" style="margin: 5px;" value="${expiry}">
                                <div class='errorMsg' style="margin-left: 0px;">${errorMsgs.expiry}</div>
                            </div>
                            <div class="form-inline">
                                <input placeholder="CVC" type="number" name="cvc" style="margin: 5px;" value="${cvc}">
                                <div class='errorMsg' style="margin-left: 0px;">${errorMsgs.cvc}</div>
                            </div>
                        </div>

                    </div>

                    <button type="submit" class="btn btn-light" name="action" value="insert">確認送出</button>
                </form>
            </div>
            <div class="col-md-4 .offset-md-4" style="margin: 60px">
             	   要儲值的商品為: ${stor_Point} 點</div>
        </div>
    </div>
    <jsp:include page="/front_end/footer.jsp" />

</body>

</html>
