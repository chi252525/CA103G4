<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.menu.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<jsp:include page="/front_end/header.jsp" flush="true" />
<!--background image-->
<img src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg" width="100%" height="" alt="">

<head>

    <title>竹風堂購物車</title>
    <!-- <link rel="stylesheet" type="text/css" -->
    <%-- 	href="<%=request.getContextPath()%>/front_end/shoppingCart/css/shoppingCart.css"> --%>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/v4-shims.css">

    <!-- Bootsraps-->
    <script src="https://code.jquery.com/jquery-3.2.1.min.js "></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />


    <!--customized datatable css-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/shoppingCart/css/branch.css">

    <!-- sweet alert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
    <!-- font awesome -->

    <style>
        th {
	background-color: darkgoldenrod;
}

tbody {
	background-color: bisque;
}

table {
	opacity: 0.75;
	border-collapse: collapse;
	width: 100%;
	border-style: hidden;
	/* 	box-shadow: 0 0 0 1px #000; */
	border-radius: 1em px;
}

table {
	border-collapse: collapse;
	border-radius: 30px;
	border-style: hidden; /* hide standard table (collapsed) border */
	box-shadow: 0 0 0 1px #666; /* this draws the table border  */
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
</head>

<body background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png" width="100%">

    <br>
    <%
		@SuppressWarnings("unchecked")
		Vector<MenuVO> buylist = (Vector<MenuVO>) session.getAttribute("shoppingcart");
		System.out.println(request.getContextPath() + "/front_end/shoppingCart/css/shoppingCart.css");
	%>
    <%
		if (buylist != null && (buylist.size() > 0)) {
	%>
    <div id="div_shadow" class="py-5">
        <div class=" container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="d-flex justify-content-start" style="color: #dfbe9f;">風堂購物車</h1>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <table id="cart" class="table-hover table table-striped" style="width: 100%">
                <thead style="margin-bottom: 15px;">
                    <tr>
                        <th width="200">餐點名稱</th>
                        <th width="100">價格</th>
                        <th width="100">數量</th>
                        <th width="100">總計</th>
                        <th width="120">操作</th>
                    </tr>
                </thead>
                <tbody id="tbody">

                    <%
						for (int index = 0; index < buylist.size(); index++) {
								MenuVO menuVO = buylist.get(index);
					%>
                    <tr>
                        <td width="200">
                            <%=menuVO.getMenu_Id()%>
                        </td>
                        <td width="100">
                            <%=menuVO.getMenu_Price()%>
                        </td>
                        <td id="add" width="100"><button id="add" class="" style="background-color: antiquewhite" onclick="add()">
                                <i class="far fa-plus-square"></i>
                            </button> <span id=item>
                                <%=menuVO.getMenu_quantity()%></span>
                            <button class="" style="background-color: antiquewhite">
                                <i class="far fa-minus-square" onclick="minus()"></i>
                            </button></td>
                        <td width="100">
                            <%=menuVO.getMenu_Price() * menuVO.getMenu_quantity()%>
                        </td>
                        <td width="120">
                            <form id="form<%=index%>>" name="deleteForm" action="ShoppingServlet.do" method="POST">
                                <input type="hidden" name="action" value="DELETE"> <input type="hidden" name="del" value="<%=index%>">
                                <button id="delete" class="del btn btn-light" type="submit" value="刪除" style="background-color: antiquewhite">
                                    <i class="fa fa-trash" style="font-size: 20px; color: #b35900"></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                </tbody>
                <%
					}
				%>
            </table>

        </div>
    </div>
    <p>
        <div class=container>
            <div class="row">
                <div class="d-flex inline mx-auto">
                    <form name="checkoutForm" action="ShoppingServlet.do" method="POST" style="margin: 10px;">
                        <input type="hidden" name="action" value="CHECKOUT"> <input style="font-weight: bolder;" type="submit" value="付款結帳" class="btn btn-warning">
                    </form>
                    <form name="checkoutForm" action="noodleShop.jsp" method="POST" style="margin: 10px;">
                        <input type="hidden" name="action" value="CHECKOUT"> <input style="font-weight: bolder;"type="submit" value="繼續選購" class="btn btn-warning">
                    </form>
                </div>
            </div>
        </div>
        <%
		}
	%>
        <c:if test="${empty shoppingcart}">
            <div class="center d-flex col-md-12" style="margin-top:250px;">
                <img class="rounded mx-auto d-block" src="img/hero-bg3.jpg" alt="Card image cap" style="align-self: center; width: 500px;">
            </div>
            <div class="center" style="">
                <p class="card-text center" style="color:black;font-size: 25px; margin-top:25px;margin-bottom:25px">你的購物車還是空的，肚子不餓嗎?</p>
                <button class="btn btn-warning" style="width: 500px;" onclick="window.location.href='noodleShop.jsp'">
                    <h3>點餐去!</h3>
                </button>
            </div>

        </c:if>

        <script>
            $(document).ready(function() {
                $('#cart').DataTable();
            });

            //             $('#cart').DataTable({
            //                 scrollY: 300,
            //                 select: true
            //             });

            $(function() {
                $("#delete").click(function() {
                    //alert("11");
                    swal({
                        title: "確定刪除餐點？",
                        text: "很好吃喔!",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "删除",
                        cancelButtonText: "取消",
                        closeOnConfirm: false
                    }).then(function() {
                        $("#form0").submit();
                        swal("删除成功！", "恭喜，数据删除成功！.", "success");
                    });
                });
            });

            function add() {
                document.getElementById("item").innerHTML++;
            }

            function minus() {
                document.getElementById("item").innerHTML--
            }

        </script>
        <jsp:include page="/front_end/footer.jsp" flush="true" />
</body>

</html>
