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
        
.card-body{
	background-color: #ffe6cc;
	height:200px;
}
        
.card{
            
    height:400px;
}

.btn-warning{

	color:#805500 !important;
}

h3{
/* 	color:#dfbe9f !important; */
}

a{
 margin-bottom:5px;
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
                        <td width="200"><span id="item_id">
                                <%=menuVO.getMenu_Id()%></span>
                        </td>
                        <td width="100">
                            <%=menuVO.getMenu_Price()%>
                        </td>
                        <FORM method="post" action="ShoppingServlet.do">
                            <td id="cartAll" width="100">
                                <button type="button" id="add" class="" style="background-color: antiquewhite">
                                    <input type="hidden" name="action" value="addCart"> <i class="far fa-plus-square"></i>
                                </button> <input type="hidden" name="price" value="<%=menuVO.getMenu_Price()%>"> <input type="hidden" name="quantity" value="<%=menuVO.getMenu_quantity()%>"> <input type="hidden" name="menuid" value="<%=menuVO.getMenu_Id()%>">
                                <input type="hidden" name="menuno" value="<%menuVO.getMenu_No();%>"><span id=item_quantity>
                        </FORM>
                        <%=menuVO.getMenu_quantity()%></span>
                        <button class="" style="background-color: antiquewhite">
                            <i class="far fa-minus-square" onclick="minus()"></i>
                        </button>
                        </td>

                        <td width="100">
                            <%=menuVO.getMenu_Price() * menuVO.getMenu_quantity()%>
                        </td>
                        <td width="120">
                            <form name="deleteForm" class="form" action="ShoppingServlet.do" method="POST">
                                <input type="hidden" name="action" value="DELETE"> <input type="hidden" name="del" value="<%=index%>">
                                <button id="delete<%=index%>" class="del btn btn-light" type="button" value="刪除" style="background-color: antiquewhite">
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

    <div class=container>
        <div class="row">
            <div class="d-flex inline mx-auto">
                <form name="checkoutForm" action="ShoppingServlet.do" method="POST" style="margin: 10px;">
                    <input type="hidden" name="action" value="CHECKOUT"> <input style="font-weight: bolder;" type="submit" value="付款結帳" class="btn btn-warning">
                </form>
                <form name="checkoutForm" action="noodleShop.jsp" method="POST" style="margin: 10px;">
                    <input type="hidden" name="action" value="CHECKOUT"> <input style="font-weight: bolder;" type="submit" value="繼續選購" class="btn btn-warning">
                </form>
            </div>
        </div>
    </div>
    <!--ads-->
    <div>
    <h3 class="col-md-8 col-12" style="margin: auto; margin-top:100px;">真心推薦</h3>
    
    <div class="card-deck" style="height:400px;width:1140px;margin:auto;">
        <div class="card">
            <img class="card-img-top" src="img/31178_slow_cooker_pork_ramen_3000.jpg" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. </p>
                <p class="card-text"><small class="text-muted"></small></p>
                <a href="#" class="btn btn-warning">去看看</a>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top" src="img/27346238482_0d2ce7bb73_b.jpg" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
                <p class="card-text"><small class="text-muted"></small></p>
                <a href="#" class="btn btn-warning">去看看</a>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top" src="img/beeframen-129052-1.jpg" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. </p>
                <p class="card-text"><small class="text-muted"></small></p>
                <a href="#" class="btn btn-warning" >去看看</a>
            </div>
        </div>
          <div class="card">
            <img class="card-img-top" src="img/000PPUE410DCEE7B3216CEj.jpg" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">This is a wider card with supporting text below as a natural lead-in </p>
                <p class="card-text"><small class="text-muted"></small></p>
                <a href="#" class="btn btn-warning" >去看看</a>
            </div>
        </div>
    </div>
    </div>

    <%}%>
    <c:if test="${empty shoppingcart}">
        <div class="center d-flex col-md-12" style="margin-top: 150px;">
            <img class="rounded mx-auto d-block" src="img/stock-photo-traditional-japanese-soup-ramen-with-meat-broth-asian-noodles-seaweed-sliced-pork-eggs-and-709288393X.jpg" alt="Card image cap" style="align-self: center; width: 500px;">
        </div>
        <div class="center" style="">
            <p class="card-text center" style="color: black; font-size: 25px; margin-top: 25px; margin-bottom: 25px">你的購物車還是空的，肚子不餓嗎?</p>
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
        <%for (int i = 0; i < buylist.size(); i++) {%>
        $(function() {
            $("#delete<%=i%>").click(function() {
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
                    // 					alert("submitting!")
                    $(".form").eq(<%=i%>).submit();
                    swal("删除成功！", "別走，建議試試別的口味！.", "success");
                });
            });
        });
        <%}%>

        function add() {
            $.ajax({
                type: "post",
                url: "ShoppingServlet.do",
                data: {
                    "action": "addCart",
                    "quantity": $('#item_id').val(),
                    "menuid": $('#item_quantity').val()
                },
                dataType: "json",
                success: function(quantity) {
                    $("#item_quantity").html(quantity.menuquantity);
                },
                error: function() {
                    alert("連線失敗!");
                }
            })
        }

        function minus() {
            document.getElementById("item").innerHTML--
        }

    </script>
    <jsp:include page="/front_end/footer.jsp" flush="true" />
</body>

</html>
