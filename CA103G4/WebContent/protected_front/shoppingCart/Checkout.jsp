<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* ,java.lang.*, com.menu.model.* , com.custommeals.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.couponhistory.model.*"%>
<%@ page import="com.ingredients.model.*"%>
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
/* 	font-style: oblique; */
	font-weight: bold;
	margin-left:50%;
	
}

	.perchase{
        	border-radius: 50em;
			border: 1px solid #C1E4FE;
			background: #3899EC;
			width: 250px;
			height:40px;
			margin-top:10px;
        }
        
	.perchase:hover{
        	background:#5cabf0;
        }
        
         .card_data{
        	border-radius:10px;
        	border-radius: 6px;
   		 	border: 1px solid #C1E4FE;
        	 width:250px;
        	margin:auto;
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
</style>
</head>

<body class="shadow-lg w-100" background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png" width="100%">
    <div id="div_shadow" class="py-5">
      <div class=" container">
        <div class="row">
            <div class="col-md-12">

                <h1 class="d-flex justify-content-start" style="color: #dfbe9f;">竹風堂-結帳</h1>
                
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
                            <th width="120">總計</th>
                            <th width="100">備註</th>
           
                        <%
                    @SuppressWarnings("unchecked")
                    Vector<MenuVO> buylist = (Vector<MenuVO>) session.getAttribute("shoppingcart");
                    Vector<CustommealsVO> buylistCustom = (Vector<CustommealsVO>) session.getAttribute("shoppingcartCustom");
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
                    
							} 
                    if (buylistCustom != null && buylistCustom.size() != 0) {
                    	
                    	for (int i = 0; i < buylistCustom.size(); i++) {
                    		CustommealsVO cv = buylistCustom.get(i);
                            String name = cv.getcustom_Name();
                            Integer total = cv.getcustom_Price() * cv.getcustom_Quantity();
                            Integer price = cv.getcustom_Price();
                            Integer quantity = cv.getcustom_Quantity();
                            StringBuilder stb = new StringBuilder();
                            for(IngredientsVO ig :buylistCustom.get(i).getIngredientsList()){
                            	stb.append(ig.getingdt_Name()+" ,");
                            }
                            %>
                                <tr>
                                    <td width="200" data-toggle="tooltip" data-html="true" data-placement="bottom" title="<div class=badge badge-pill badge-warning>客製拉麵食材: </div><span><%=stb%></span>" data-content="">
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
                    	
                    }
                    
                    if (buylist == null && buylistCustom == null) { //發現購物車為空將提示
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
                                     	   總計 <span id="price">$ <%=amount%></span>
                                     	   
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
                    <form method="post" action="<%=request.getContextPath() %>/front_end/shoppingCart/checkoutServlet.do">
                        <%--送去orferform Serbvlet --%>
                        <input type="hidden" name="action" value="insert">
                        <%--                         <c:set var="action" value="insert" scope="session" /> --%>
                        <legend>
                            <b>點餐資訊</b>
                        </legend>
                        <div class="form-row">

                            <!--取餐方式 -->
                            <p class="form-check form-check-inline form-group col-md-4" style="margin-right: : 10px;">
                                <b>取餐方式:</b>
<%--                                 <c:out value="${eatIntakeAway}" default="幹"></c:out> --%>
                                <input class="form-check-input" type="radio" name="eatIn&takeAway" id="takeaway" value="takeaway" checked style="width: 50px;" onclick="takeAway();" ${(eatIntakeAway=='takeaway' or eatIntakeAway==null)? 'checked' :'' }>
                                <label id="takeAway" class="form-check-label"><b>外帶</b> </label>
                                <input class="form-check-input" type="radio" name="eatIn&takeAway" id="delivery" value="delivery" style="width: 50px;" onclick="Delivery();" ${(eatIntakeAway=='delivery')? 'checked' :'' }>
                                <label class="form-check-label"><b>外送</b></label>
                                <div class='errorMsg'>${errorMsgs.eatIn_takeAway}</div>

                            </p>
                            <!-- choose branch -->
                            <div id='branch' class="form-group col-md-8">
                                <jsp:useBean id="branchSvc" scope="page" class="com.branch.model.BranchService" />
                                <b>訂餐分店:</b> <select name="branch_no" class="custom-select align-items-center" id="inputGroupSelect04 stor_No delivery" aria-label="Example select with button addon" size="1" name="branch_No" style="margin-left: 5px !important; width: 50%; margin-right: 8%;display= none;">
                                    <option selected>請選擇
                                        <c:forEach var="brVO" items="${branchSvc.all}">
                                    <option value="${brVO.branch_No}" ${(branch_No==brVO.branch_No)? 'selected' :'' }>${brVO.branch_Name}
                                        </c:forEach> <input type="hidden" name="location" value="Checkout.jsp">
                                </select>
                                <div class='errorMsg'>${errorMsgs.branch_no }</div>
                            </div>
                            <!-- choose address -->
                            <div id='address' class='col-12 col-md-12' style="display:none;">
                                <div class="form-group col-md-6" style="padding-left:0px">
                                    <b>鄉鎮區市:</b> <br> <select class="custom-select col-md-4" style="display: inline" class="nice-select" name="mem_Recounty" id="city-list" style="display: none"></select> <select class="custom-select col-md-4" name="mem_Retown" class="nice-select custom-select" id="sector-list" style="display:"></select>
                                    <div class='errorMsg'>${errorMsgs.countytwon}</div>
                                </div>
                                <div class="form-group col-md-8" style="width: 80%; padding-left:0px;">
                                    <b> 外送地址</b><input type="text" class="form-control-sm col-md-10" id="mem_Readdr" name="deliv_addres" style="width: 80%; margin-top: 10px;">
                                    <div class='errorMsg'>${errorMsgs.deliv_addres}</div>
                                </div>
                            </div>

                            <!-- 取餐時間 -->

                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-6" style="padding-left: 0px;">
                                        <div class="form-inline  col-md-12" style="padding-left: 5px;">
                                            <b> 取餐時間:</b>
                                            <div class="input-group date" id="datetimepicker2" data-target-input="nearest" style="margin-top: 20px; margin-bottom: 20px;margin-left:5px;">
                                                <input name="time" type="text" class="form-control datetimepicker-input" data-target="#datetimepicker2" value="${time}" style="margin-left:5px;">
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

							${order_pstatus}
                            <div class="btn-group btn-group-toggle col-12 col-md-8" data-toggle="buttons" style="padding-left: 5px;">
                                <b>付款方式:</b>
                                <label class="btn btn-secondary" onclick="cash()">
                                    <input type="radio" name="order_pstatus" value="1" autocomplete="off" ${order_pstatus ==1	 ? 'checked' : ''}> 現金
                                </label>
                                <label class="btn btn-secondary" onclick="cardShow()">
                                    <input type="radio" name="order_pstatus" value="2" autocomplete="off" ${order_pstatus ==2 ? 'checked' : ''}> 信用卡
                                </label>
                                 <label class="btn btn-secondary" onclick="point()">
                                    <input type="radio" name="order_pstatus" value="3" autocomplete="off" ${order_pstatus ==3 ? 'checked' : ''}> 竹幣
                                </label>
                            </div><br>
                          	<!-- 點數購買 -->
                          	<div id="point" style="display:${order_pstatus ==3 ? '' : 'none'};">
                          	<!-- 點數不足提示 -->
								<div class="errorMsg col-md-4" style=" border-radius:6px;margin-left:100px;margin-top:20px; background-color: rgb(255,255,255,0.5);" >
									${errorMsgs.point_insufficient}
								</div>
							</div>
                            <c:if test="${order_pstatus ne 1}">
                                <!-- 只要不為現金就是顯示信用卡 -->
                                <!-- credit card -->
                                <div id="card" class="demo-container col-12 col-md-6" style="margin: 10px; display: ${order_pstatus ==2 ? '' : 'none'};">
                                    <div class="card-wrapper" style="margin-left: 0px; width: 350px;"></div>
                                    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/270939/icons-cards.svg" type=""style="margin-top:5px;"></img>
                                    <div class="form-container active" style="margin: 10px;">
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
                                    </div>
                                </div>
                            </c:if>
                            <!-- ps column -->
                            <p class="form-group col-12 col-md-8">
                                <label for="textarea"><b>備註:</b></label>
                                <textarea name="ps" id="textarea" class="form-control" cols="45" rows="4" style="resize: none; margin: auto;"></textarea>
                            </p>


                        </div>
                </fieldset>
            </div>
        </div>
    </div>
    <!-- submit order -->
    <%--     <c:if test="${not empty shoppingcart}"><!--確認購物車不為空 --> --%>
    <div class=container>
        <div class="row">
            <div class="d-flex ml-auto">
            	<c:if test="${ not empty shoppingcart or not empty shoppingcartCustom}"><!-- 一台購物車不為空即可結帳 -->
                	<a class="btn btn-warning btn-lg" href="<%=request.getContextPath()%>/front_end/menu/listAllMenu4.jsp" style="margin: 5px;">繼續點餐</a>
                	<input id="forOrderAmount" type="hidden" name="amount" value="<%=amount %>"><!-- 傳遞金額 -->
                	<button type="submit" class="btn btn-warning btn-lg" style="margin: 5px;">結帳</button>
				</c:if>
				<c:if test="${empty shoppingcart and empty shoppingcartCustom}"><!-- 兩個購物車同時為空  -->
				<a href="<%=request.getContextPath()%>/front_end/menu/listAllMenu4.jsp" class='btn btn-warning'>點餐去</a>
				</c:if>
            </div>
        </div>
    </div>
    </form>
  
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
                    <button id="couponUse" type="button" class="btn btn-outline-primary btn-sm" data-dismiss="modal" onclick="reducePrice()">使用</button>
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

        function cardShow() { //開啟信用卡輸入
            $('#card').show();
            $('#point').hide();
        }

        function cash() { //隱藏信用卡輸入
            $('#card').hide();
            $('#point').hide();
        }
        
        function point(){//開啟點數
        	 $('#point').show();
        	 $('#card').hide();
        }

        function takeAway() { //隱藏地址輸入

            $('#address').hide();
        }

        function Delivery() { //開啟地址輸入

            $('#address').show();

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
                      alert(amount);
                    $('#price').html("<font style=color:red>\$ " + amount + "</font>");
            		$('#forOrderAmount').val(amount);
            		$('#couponUse').attr('disabled',true);
                },
                error: function() {
                    alert("reduce ajax error!")
                }
            })
            
            
        }
        //popover
        $(function () {
        	  $('[data-toggle="popover"]').popover()
        	})
		//tooltip
		$(function () {
  			$('[data-toggle="tooltip"]').tooltip()
		})
    </script>
</body>

</html>
