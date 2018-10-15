<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>

<jsp:include page="/front_end/header.jsp" />
<html>

<head>
    <meta charset="UTF-8">
    <title>Untitled Document</title>

    <!-- font aewsome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/v4-shims.css">

    <!--shoppingCart.css-->
    <link rel="stylesheet" href="css/shoppingCart.css">
</head>

<body class="shadow-lg w-100">
    <%
		Vector<MenuVO> buylist = (Vector<MenuVO>) session.getAttribute("shoppingcart");
		if (buylist != null && buylist.size() > 0) {
	%>





    <img src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg" width="100%" height="" alt="">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-4">
                <h1 class="d-flex justify-content-start">您的拉麵</h1>
            </div>
        </div>
    </div>


    <div class="container">
        <div class="row">
            <div class="col-12 col-md-12 ">
                <form action="">
                    <ul class="list-group">

                        <li class="list-group-item list-group-item-light d-flex"><strong>餐點內容</strong>
                            <strong>價格</strong><strong>數量</strong><strong>小記</strong></li>
                        <%
							for (int i = 0; i < buylist.size(); i++) {
									MenuVO menuVO = buylist.get(i);
						%>
                        <li class="list-group-item list-group-item-dark d-flex"><strong>
                                <%=menuVO.getMenu_Id()%></strong>
                            <strong>
                                <%=menuVO.getMenu_Price()%></strong> <strong></strong> <input type="number" min="1" max="10" value="2">
                            <button id="del" class="ml-auto align-self-center btn-light btn">
                                <i class="fa fa-trash ml-auto" style="font-size: 20px; color: red"></i>
                            </button>
                            <%
 	}
 %>
                            <!-- 							</button></li> -->
                            <!-- 						<li -->
                            <!-- 							class="list-group-item list-group-item-light d-flex navbar-btn"> -->
                            <!-- 							<strong>唐楊炸雞</strong> <input type="number" min="1" max="10" -->
                            <!-- 							value="1"> -->
                            <!-- 							<button id="del" class="ml-auto align-self-center btn btn-light"> -->
                            <!-- 								<i class="fa fa-trash" style="font-size: 20px; color: red"></i> -->
                            <!-- <!-- 							</button> -->
                            <!-- <!-- 						</li> -->
                            <!-- 						<li class="list-group-item list-group-item-dark d-flex"><strong> -->
                            <!-- 								豪華醬油拉麵</strong> <input type="number" min="1" max="10" value="2"> -->
                            <!-- 							<button id="del" class="ml-auto align-self-center btn btn-light"> -->
                            <!-- 								<i class="fa fa-trash" style="font-size: 20px; color: red"></i> -->
                            <!-- 							</button> </a></li> -->
                            <hr>
                    </ul>
                </form>
                <div class="d-flex">
                    <span class="ml-auto">總計:</span>
                </div>
                <form action="">
                    <fieldset class="myfieldset">
                        <legend class="mylegend">取餐方式</legend>
                        <div class="">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
                                <label class="form-check-label" for="inlineRadio1"> 外帶 </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">
                                <label class="form-check-label" for="inlineRadio2"> 外送 </label>
                            </div>
                        </div>
                    </fieldset>
                </form>

                <button class="btn btn-light">結帳</button>
            </div>
        </div>
    </div>

    <%
		}
	%>
</body>

</html>
