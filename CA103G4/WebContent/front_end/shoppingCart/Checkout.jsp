<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.menu.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.couponhistory.model.*"%>
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

<head>
    <title>竹風堂-結帳</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ShoppingCart.css">
    <style>
        table, th, td {
	/*             border: 1px solid black; */
	text-align: center;
	opacity: 0.9;
}

th {
	background-color: darkgoldenrod;
}

tbody {
	background-color: bisque;
}

a:hover {
	text-decoration: none;
}

/* credit card */
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

field>div {
	margin: 10px;
}

.errorMsg{
	color:red;
	font-style: oblique;
	font-weight: bold;
	margin-left:50%;
}
</style>
</head>

<body class="shadow-lg w-100" background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png" width="100%">
    <div id="div_shadow" class="py-5"">
		<div class=" container">
        <div class="row">
            <div class="col-md-12">
                <a href="<%=request.getContextPath()%>/front_end/shoppingCart/noodleShop.jsp">
                    <h1 class="d-flex justify-content-start" style="color: #dfbe9f;">竹風堂-
                        結帳</h1>
                </a>
            </div>
        </div>
    </div>
    </div>
    <div class="shadow p-1">
        <div class="container">
            <div class="row">
                <div class="col-md-12" style="">
                    <table id="" class="table-hover table table-striped" style="margin: auto;">
                        <tr>
                            <th width="200">餐點</th>
                            <th width="100">價格</th>
                            <th width="100">數量</th>
                            <th width="120">小記</th>
                            <th width="100">備註</th>
                        </tr>
                        <%
							MemberService memSrv = new MemberService();
							MemberVO memVO = memSrv.getOne_Member("M000001");
							System.out.println(memVO);
							session.setAttribute("memVO", memVO);
						%>


                        <%
							@SuppressWarnings("unchecked")
							Vector<MenuVO> buylist = (Vector<MenuVO>) session.getAttribute("shoppingcart");
							String amount = (String) request.getAttribute("amount");
						%>
                        <%
							if (buylist != null && buylist.size() != 0) {

								for (int i = 0; i < buylist.size(); i++) {
									MenuVO mv = buylist.get(i);
									String name = mv.getMenu_Id();
									Integer total = mv.getMenu_Price() * mv.getMenu_quantity();
									Integer price = mv.getMenu_Price();
									Integer quantity = mv.getMenu_quantity();
						%>
                        <tr>
                            <td width="200">
                                <%=name%>
                            </td>
                            <td width="100">
                                <%=price%>
                            </td>
                            <td width="100">
                                <%=quantity%>
                            </td>
                            <td width="100">
                                <%=total%>
                            </td>
                            <td width="120"></td>
                        </tr>
                        <%
							}
							} else if (buylist == null) { //發現購物車為空將提示
						%>
                        <tr>
                            <td colspan='5' style='height: 150px; vertical-align: middle'><b class='center'>購物車裡沒有餐點!</b></td>
                        </tr>
                        <%
							}
						%>
                        <c:if test="${not empty shoppingcart}">

                            <tr class="">
                                <td class=""></td>
                                <td class="" colspan="6" style="text-align: right;"><img src="img/coupon.png" style="margin-right: 10px;"> <!-- coupon button trigger modal -->
                                    <input id="coupon" class="btn btn-sm btn-light" type="button" data-toggle="modal" data-target="#couponModal" value="Coupon" style="background-image: url(''); width: 20%">

                                    <h4>
                                        總計 <span id="price">$
                                            <%=amount%></span>
                                    </h4>
                                </td>
                            </tr>
                        </c:if>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- filedset for 取餐方式-->
    <div class=" container">
        <div class="row">
            <div class="col-md-12">

                <fieldset>
                    <form method="post" action="<%=request.getContextPath()%>/front_end/ShoppingCart/checkoutServlet.do">
                        <%--送去orferform Serbvlet --%>
                        <input type="hidden" name="action" value="insert">
<%--                         <c:set var="action" value="insert" scope="request" /> --%>
                        <legend>
                            <b>點餐資訊</b>
                        </legend>
                        <div class="form-row">
                        
                        <!--取餐方式 -->
                            <p class="form-check form-check-inline form-group col-md-4" style="margin-right: : 10px;">
                                <b>取餐方式:</b> <input class="form-check-input" type="radio" name="eatIn&takeAway" id="takeaway" value="takeaway" checked="" style="width: 50px;" /> <label class="form-check-label">
                                    <b>外帶</b> </label> <input class="form-check-input" type="radio" name="eatIn&takeAway" id="delivery" value="delivery" style="width: 50px;"> <label class="form-check-label"><b>外送</b></label>
                                    <div class='errorMsg'>${errorMsgs.eatIn_takeAway}</div>
                            </p>
                            <!-- choose branch -->
                            <div class="form-group col-md-8">
                                <jsp:useBean id="branchSvc" scope="page" class="com.branch.model.BranchService" />
                                <b>取餐分店:</b> <select name="branch_no" class="custom-select align-items-center" id="inputGroupSelect04 stor_No delivery" aria-label="Example select with button addon" size="1" name="branch_No" style="margin-left: 5px !important; width: 50%; margin-right: 8%;display= none;" onchange="submit()">
                                    <option selected>請選擇
                                        <c:forEach var="brVO" items="${branchSvc.all}">
                                    <option value="${brVO.branch_No}">${brVO.branch_Name}
                                        </c:forEach> <input type="hidden" name="location" value="Checkout.jsp">
                                </select>
                                <div class='errorMsg'>${errorMsgs.branch_no }</div>
                            </div>
                            <!-- choose address -->
                            <div class="form-group col-md-6">
                                <b>鄉鎮區市:</b> <br> <select class="custom-select col-md-4" style="display: inline" class="nice-select" name="mem_Recounty" id="city-list" style="display: none"></select> <select class="custom-select col-md-4" name="mem_Retown" class="nice-select custom-select" id="sector-list" style="display:"></select>
  								 <div class='errorMsg'>${errorMsgs.countytwon}</div>                  
                            </div>
                            <div class="form-group col-md-8" style="width: 80%; display:;">
                                <b> 外送地址</b><input type="text" class="form-control-sm col-md-10" id="mem_Readdr" name="mem_Readdr"  style="width: 80%; margin-top: 10px;">
                                <input type="hidden" name="deliv_addres">
                                 <div class='errorMsg'>${errorMsgs.deliv_addres}</div>
                            </div>


                            <!-- 取餐時間 -->

                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-6" style="padding-left: 0px;">
                                        <div class="form-group col-md-12" style="padding-left: 5px;">
                                            <b> 取餐時間:</b>
                                            <div class="input-group date" id="datetimepicker2" data-target-input="nearest" style="margin-top: 20px; margin-bottom: 20px;">
                                                <input name="time" type="text" class="form-control datetimepicker-input" data-target="#datetimepicker2" />
                                                <div class="input-group-append" data-target="#datetimepicker2" data-toggle="datetimepicker">
                                                    <div class="input-group-text">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class='errorMsg'>${errorMsgs.time}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- payment -->
                            <p class='col-12 col-md-8' style="padding-left: 5px;">
                                <b>付款方式:</b> <br> <label>
                                    <input type="radio" class="" id="takeaway" name="order_pstatus" value="1" onclick="cash()">現金
                                </label> <label>
                                    <input type="radio" class="" id="delivery" name="order_pstatus" value="2" onclick="cardShow()" checked>信用卡

                                </label>
                            </p>
                            <!-- credit card -->
                            <div id="card" class="demo-container" style="margin: 10px;">
                                <div class="card-wrapper"></div>

                                <div class="form-container active" style="margin: 10px;">
                                    <input placeholder="Card number" type="tel" name="number" style="margin: 5px;"> 
                                    <input placeholder="Full name" type="text" name="name" style="margin: 5px;"> 
                                    <input placeholder="MM/YY" type="tel" name="expiry" style="margin: 5px;"> 
                                    <input placeholder="CVC" type="number" name="cvc" style="margin: 5px;">
                                </div>
                            </div>

                            <p class="form-group col-12 col-md-8">
                                <label for="textarea"><b>備註:</b></label>
                                <textarea name="ps" id="textarea" class="form-control" cols="45" rows="4" style="resize: none; margin: auto;"></textarea>
                            </p>

                            <!-- 						<p> -->
                            <!-- 							<label for="time">Example time</label> <input type="time" -->
                            <!-- 								id="time" /> -->
                            <!-- 						</p> -->

                            <!-- 						<p> -->
                            <!-- 							<label for="output">Example output</label> -->
                            <!-- 							<output name="result" id="output">100</output> -->
                            <!-- 						</p> -->
                        </div>
                </fieldset>
            </div>
        </div>
    </div>
    <!-- submit order -->
    <div class=container>
        <div class="row">
            <div class="d-flex ml-auto">
                <button class="btn btn-ligth" onclick="window.location.href='<%=request.getContextPath()%>/front_end/menu/listAllMenu4.jsp' style=" margin: 5px;">繼續點餐</button>

                <button type="submit" class="btn mr-auto" style="margin: 5px;">結帳</button>
                </form>
            </div>
        </div>
    </div>



    <!-- Modal -->
    <div class="modal fade" id="couponModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">請選擇優惠卷</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <jsp:useBean id="CoucatSvc" class="com.coucat.model.CoucatService" />
                <jsp:useBean id="CouponSvc" class="com.coupon.model.CouponService" />
                <jsp:useBean id="CouponhSvc" class="com.couponhistory.model.CouponhistoryService" />
                <%
					// 					CouponhistoryService couponhSvc = new CouponhistoryService();
					// 					List<CouponhistoryVO> list = couponhSvc.getCouponByMem(memVO.getMem_No());
					// 					for (CouponhistoryVO CouponhVO : list) {
					// 						System.out.println(CouponhVO.getCoup_sn());
					// 					}
				%>
                <!-- just for test -->
                <%-- 				<c:out value="${memVO}" --%>
                <%-- 					default="幹沒值!" /> --%>

                <%-- 				<c:out value="${CouponhSvc.getCouponByMem(memVO.mem_No)}" --%>
                <%-- 					default="幹沒值!" /> --%>
                <div class="modal-body">
                    <select id="coucatValue" name="coucatValue">
                        <option selected>我的優惠卷清單
                            <c:forEach var="CouponhVO" items="${CouponhSvc.getCouponByMem(memVO.mem_No)}">
                                <%--取得優惠卷序號 --%>
                                <c:set var="coupon" value="${CouponSvc.getOneCoupon(CouponhVO.coup_sn)}" />
                                <%--取得優惠卷 --%>
                                <c:set var="coucat_No" value="${coupon.coucat_No }" />
                                <%--取得優惠卷類別編號 --%>
                                <c:set var="coucat" value="${CoucatSvc.getOneCoucat(coucat_No)}" />
                                <%--取得優惠卷類別 --%>
                        <option value="${coucat.coucat_Value }">${coucat.coucat_Name }
                            <%--取得優惠卷類別名稱 --%>
                            </c:forEach>
                    </select>
                </div>
                <div class="modal-footer">
                    <form action="<%request.getContextPath();%>/front_end/shoppingCart/ShoppingServlet.do"></form>
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="reducePrice()">使用</button>
                    <input type="hidden" name="action" value="findMemCoupon"> <input type="text" id="amount" name="amount" value="<%=amount%>">
                    <button type="button" class="btn btn-light">取消</button>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/front_end/footer.jsp" flush="true" />
    <!-- credit card js -->
    <script>
        new Card({
            form: document.querySelector('form'),
            container: '.card-wrapper'
        });

        function cardShow() {
            $('#card').toggle();
        }

        function cash() {
            $('#card').hide();
        }

        function takeaway() {

        }

        function delivery() {
            $('#delivery')
        }
        //紀錄選到的CouponValue
        var couponValue; //選告全域變數
        $('#coucatValue').change(function() {
            couponValue = $(this).val();
            var discountPrice = $('#amount').val() - couponValue;
            $('#amount').text(discountPrice);
            console.log(couponValue);
            console.log(discountPrice);
        });


        // Ajax for Coupon
        function reducePrice() {
            //             alert('ajax startint !');
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/front_end/shoppingCart/ShoppingServlet.do",
                data: {
                    "action": "findMemCoupon",
                    "amount": "<%=amount%>",
                    "coucatValue": couponValue
                },
                dataType: "html",
                success: function(amount) {
                    //                     alert("折價成功!");
                    $('#price').html(
                        "<font style=color:red>\$" + amount +
                        "</font>");
                },
                error: function() {
                    alert("reduce ajax error!")
                }
            })
        }

    </script>
</body>

</html>
