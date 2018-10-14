<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.menu.model.* , com.custommeals.model.*"%>
<%@ page import="com.post.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="cusmealSvc1" scope="page" class="com.custommeals.model.CustommealsService" />
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="postSvc2" scope="page" class="com.post.model.PostService" />
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

    <!-- star 評分 套件 add by Ning-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/post/css/starability-all.min.css" />
<!-- sweet alert2 add by Ning-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js"
	type="text/javascript"></script>

<style>
        th {
	background-color: darkgoldenrod;
}

tbody {
	background-color: bisque;
	vertical-align: middle !important;
}

.table td {
	vertical-align: middle !important;
}

table {
	opacity: 0.75;
	border-collapse: collapse;
	width: 100%;
	border-style: hidden;
	/* 	box-shadow: 0 0 0 1px #000; */
	-moz-border-radius: 5px !important;
	/* 	 box-shadow:20px 20px 50px 15px grey;	 */
}

table {
	border-collapse: collapse;
	border-radius: 30px;
	border-style: hidden; /* hide standard table (collapsed) border */
	box-shadow: 0 0 0 1px #666; /* this draws the table border  */
	text-align: center !important;
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


.btn-warning {
	color: #805500 !important;
}

h3 {
	/* 	color:#dfbe9f !important; */
	
}

a {
	margin-bottom: 5px;
}
/* effect */
.hovereffect {
	width: 100%;
	height: 100%;
	float: left;
	overflow: hidden;
	position: relative;
	text-align: center;
	cursor: default;
}

.hovereffect .overlay {
	width: 100%;
	height: 100%;
	position: absolute;
	overflow: hidden;
	top: 0;
	left: 0;
	opacity: 0;
	filter: alpha(opacity = 0);
	background-color: rgba(0, 0, 0, 0.5);
	-webkit-transition: all 0.4s cubic-bezier(0.88, -0.99, 0, 1.81);
	transition: all 0.4s cubic-bezier(0.88, -0.99, 0, 1.81);
}

.hovereffect img {
	display: block;
	position: relative;
	-webkit-transition: all 0.4s cubic-bezier(0.88, -0.99, 0, 1.81);
	transition: all 0.4s cubic-bezier(0.88, -0.99, 0, 1.81);
}

.hovereffect h2 {
	text-transform: uppercase;
	color: #fff;
	text-align: center;
	position: relative;
	font-size: 17px;
	background: rgba(0, 0, 0, 0.6);
	-webkit-transform: translatey(-100px);
	-ms-transform: translatey(-100px);
	transform: translatey(-100px);
	-webkit-transition: all 0.4s cubic-bezier(0.88, -0.99, 0, 1.81);
	transition: all 0.4s cubic-bezier(0.88, -0.99, 0, 1.81);
	padding: 10px;
}

.hovereffect a.info {
	text-decoration: none;
	display: inline-block;
	text-transform: uppercase;
	color: #fff;
	border: 1px solid #fff;
	background-color: transparent;
	opacity: 0;
	filter: alpha(opacity = 0);
	-webkit-transition: all 0.4s ease;
	transition: all 0.4s ease;
	margin: 50px 0 0;
	padding: 7px 14px;
	margin-top: 35%;
	width: 45%;
}

.hovereffect a.info:hover {
	box-shadow: 0 0 5px #fff;
}

.hovereffect:hover img {
	-ms-transform: scale(1.2);
	-webkit-transform: scale(1.2);
	transform: scale(1.2);
}

.hovereffect:hover .overlay {
	opacity: 1;
	filter: alpha(opacity = 100);
}

.hovereffect:hover h2, .hovereffect:hover a.info {
	opacity: 1;
	filter: alpha(opacity = 100);
	-ms-transform: translatey(0);
	-webkit-transform: translatey(0);
	transform: translatey(0);
}

.hovereffect:hover a.info {
	-webkit-transition-delay: .2s;
	transition-delay: .2s;
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
		@SuppressWarnings("unchecked")
		Vector<CustommealsVO> buylistCustom = (Vector<CustommealsVO>) session.getAttribute("shoppingcartCustom");
	%>
    <%
		if ((buylist != null && (buylist.size() > 0)) || (buylistCustom != null && (buylistCustom.size() > 0))) {
	%>
    <div id="div_shadow" class="py-3">
        <div class=" container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="d-flex justify-content-start" style="color: #dfbe9f;">竹風堂購物車</h1>
                    <p class="text-white">您選購的餐點如下:</p>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <table id="cart" class="table-hover table t able-striped" style="width: 100%">
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
                    	if (buylist != null && buylist.size() > 0) {
                    		
                    	
						for (int index = 0; index < buylist.size(); index++) {
								MenuVO menuVO = buylist.get(index);
					%>

                    <tr>
                        <td width="200"><span id="id_Col<%=index %>"><%=menuVO.getMenu_Id()%></span>
                            <input id="no_Col<%=index%>" type="hidden" value=<%=menuVO.getMenu_No() %>>
                            <!-- 竊取餐點pk -->
                        </td>
                        <td width="100"><span id="price_Col<%=index %>"><%=menuVO.getMenu_Price()%></span>
                        </td>
                        <td width="100">
                            <button id="minus" class="btn btn-light" style="background-color: antiquewhite" onclick="minus<%=index%>()">
                                <i class="far fa-minus-square"></i>
                            </button> <span id="quantity_Col<%=index%>"><%=menuVO.getMenu_quantity()%></span>
                            <button type="button" id="add" class="btn btn-light" style="background-color: antiquewhite" onclick="add<%=index%>()">
                                <i class="far fa-plus-square"></i>
                            </button>

                        </td>

                        <td width="100"><span id="total_Col<%=index%>"><%=menuVO.getMenu_Price() * menuVO.getMenu_quantity()%></span>
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

                    <%
						}}
					%>

                    <!-- ------------------------------------------------custommealsVO------------------分隔線 --------------------------------------------------------------------->

                    <%
					if (buylistCustom != null && buylistCustom.size() > 0) {
					for (int index = 0; index < buylistCustom.size(); index++) {
							CustommealsVO custommealsVO = buylistCustom.get(index);
					%>

                    <tr>
                        <td width="200"><span id="Cid_Col<%=index %>"><%=custommealsVO.getcustom_Name()%></span>
                            <input id="Cno_Col<%=index %>" type="hidden" value=<%=custommealsVO.getcustom_No() %>>
                            <!-- 竊取餐點pk -->
                        </td>
                        <td width="100">
                            <span id="Cprice_Col<%= index %>"><%=custommealsVO.getcustom_Price()%></span>
                        </td>
                        <td width="100">
                            <button id="minusC" class="btn btn-light" style="background-color: antiquewhite" onclick="minusC<%= index %>()">
                                <i class="far fa-minus-square"></i>
                            </button> <span id="Cquantity_Col<%=index %>"><%=custommealsVO.getcustom_Quantity()%></span>
                            <button type="button" id="addC" class="btn btn-light" style="background-color: antiquewhite" onclick="addC<%= index %>()">
                                <i class="far fa-plus-square"></i>
                            </button>

                        </td>

                        <td width="100"><span id="Ctotal_Col<%=index %>"><%=custommealsVO.getcustom_Price() * custommealsVO.getcustom_Quantity()%></span>
                        </td>
                        <td width="120">
                            <form name="deleteForm" class="formC" action="ShoppingServlet.do" method="POST">
                                <input type="hidden" name="action" value="DELETEC"> <input type="hidden" name="del" value="<%=index%>">
                                <button id="deleteC<%=index %>" class="del btn btn-light" type="button" value="刪除" style="background-color: antiquewhite">
                                    <i class="fa fa-trash" style="font-size: 20px; color: #b35900"></i>
                                </button>
                            </form>
                        </td>
                    </tr>




                    <!-- ------------------------------------------------------------------分隔線 --------------------------------------------------------------------->

                </tbody>
                <%
					}}
				%>
            </table>

        </div>
    </div>

    <div class=container>
        <div class="row">
            <div class="d-flex inline mx-auto">
                <form name="checkoutForm" action="<%=request.getContextPath()%>/front_end/menu/listAllMenu4.jsp" method="POST" style="margin: 10px;">
                    <input style="font-weight: bolder;" type="submit" value="&laquo;繼續選購" class="btn btn-warning">
                </form>
                 <form name="checkoutForm" action="ShoppingServlet.do" method="POST" style="margin: 10px;">
                    <input type="hidden" name="action" value="CHECKOUT"> <input style="font-weight: bolder;" type="submit" value="付款結帳&raquo;" class="btn btn-warning">
                </form>
            </div>
        </div>
    </div>

    <% 
	PostService postSvc = new PostService();
	List<PostVO> list = postSvc.getAllByNewFour();
	pageContext.setAttribute("list", list);
	
%>
    <!--ads-->
    <div class="container">
        <h3 class="col-md-8 col-12" style="margin-top: 100px;color:#fff;">最新自訂餐點推薦<span class="badge badge-pill badge-danger" style="font-size:18px;">New</span></h3>

        <div class="row">
            <c:forEach var="postVO" items="${list}">
                <div class="col-md-4 col-4 px-2 py-4 ">
                    <div class="card">
                        <img class="card-img img-fluid" src="<%=request.getContextPath()%>/post/postshowimage.do?post_No=${postVO.post_No}" alt="Card image" style="width: 400px; height: 300px;">
                        <div class="card-img-overlay d-flex justify-content-center align-items-center">
                            <h2 class="display-5" class="text-primary ">
                                <b>${cusmealSvc.getOneCustommeals(postVO.custom_No).custom_Name}</b>
                            </h2>
                        </div>
                    </div>

                    <div class="card px-2" style="background-color: rgba(255, 255, 255, 0.45)">
                        <h5 class="card-title text-dark my-2 px-2">${cusmealSvc1.getOneCustommeals(postVO.custom_No).custom_Name}</h5>
                        <p class="starability-result" data-rating="${postVO.post_Eva}"></p>
                        <p style="text-align: right;" class="my-0">
                            by ${memSvc.getOne_Member(postVO.mem_No).mem_Name} <span class="lnr lnr-eye " style="text-align: right;">${postVO.post_Views}</span>
                        </p>

                        <!-- 查看單一貼文action -->
                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/postServlet.do" style="margin-bottom: 0px;">
                            <input type="hidden" name="post_No" value="${postVO.post_No}" />
                            <input type="hidden" name="mem_No" value="${postVO.mem_No}" />
                            <!-- 查單一貼文 -->
                            <input type="hidden" name="action" value="getOne_For_Display" />
                            <button type="submit" class="btn btn-info btn-sm btn-block my-2">看更多
                                &raquo;</button>
                        </FORM>
                        <c:if test='${memVO.mem_No!=null}'>
                            <form method="post" id="addtoCartForm${postVO.post_No}" action="<%=request.getContextPath()%>/front_end/custommeals/custommeals.do">
                                <input type="hidden" name="action" value="insert_byPosted">
                                <input type="hidden" name="custom_No" value="${postVO.custom_No}">
                                <input type="hidden" id="mem_No" name="mem_No" value="${memVO.mem_No}" />
                                			<input type="hidden" name="requestURL" value="/front_end/shoppingCart/Cart.jsp">
                                <button type="button" class="btn btn-danger btn-sm btn-block mb-1" id="addtoCart${postVO.post_No}">加入購物車</button>
                            </form>
                            
                                <script>
    //Java完美操縱javaScript , 加入餐點進購物車
		$(function() {
			$("#addtoCart${postVO.post_No}").click(function() {
				swal({
					title : "加入購物車",
					html : "成功",
					type : "success"
				}).then(function() {
					
					  swal({
							title : "商品已放置您的購物車",
							html : "再選選其他餐點嘛",
							type : "success"
						});
	                    setTimeout(function() {
	                    	$("#addtoCartForm${postVO.post_No}").submit();
	                    }, 1200);
				
				});
			});
		});
		
	</script>
                            
                            
                            
                            
                            
                            
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <%
		}
	%>
    <c:if test="${empty shoppingcart and empty shoppingcartCustom}">
        <!-- 	the hover effect example -->
        <!-- 		<div class="col-lg-3 col-md-4 col-sm-6 col-xs-12"> -->
        <!-- 			<div class="hovereffect"> -->
        <!-- 				<img class="img-responsive" src="http://placehold.it/350x200" alt=""> -->
        <!-- 				<div class="overlay"> -->
        <!-- 					<h2>Hover effect 1v2</h2> -->
        <!-- 					<a class="info" href="#">link here</a> -->
        <!-- 				</div> -->
        <!-- 			</div> -->
        <!-- 		</div> -->
        <div class="center d-flex col-md-12" style="margin-top: 150px;">
            <div class="hovereffect center" style="width: 500px; height: 333.33px; margin-bottom: 5%;">
                <img class="rounded d-block img-responsive" src="img/stock-photo-traditional-japanese-soup-ramen-with-meat-broth-asian-noodles-seaweed-sliced-pork-eggs-and-709288393X.jpg" alt="Card image cap" style="align-self: center; width: 100%;">
                <div class="overlay">
                    <a class="info center" href="<%=request.getContextPath()%>/front_end/menu/listAllMenu4.jsp">點餐去</a>
                </div>
            </div>
        </div>
        <div class="center" style="">
            <p class="card-text center" style="color: black; font-size: 25px; margin-top: 25px; margin-bottom: 25px">你的購物車還是空的，肚子不餓嗎?</p>
            <!-- 			<button class="btn btn-warning" style="width: 500px;" -->
            <!-- 				onclick="window.location.href='noodleShop.jsp'"> -->
            <!-- 				<h3>點餐去!</h3> -->
            <!-- 			</button> -->
        </div>

    </c:if>
    <script>
        console.log($('#quantity_Col').val());
        //         $(document).ready(function() {
        //             $('#cart').DataTable();
        //         });

        //             $('#cart').DataTable({
        //                 scrollY: 300,
        //                 select: true
        //             });
        <%if(buylist!=null){
       		 for (int i = 0; i < buylist.size(); i++) {%>
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
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                }).then(function() {
                    // 					alert("submitting!")
                    swal("删除成功！", "別走，建議試試別的口味！.", "success");
                    setTimeout(function() {

                        $(".form").eq(<%=i%>).submit();
                    }, 1200);


                });
            });
        });

        //for add quantity button 
        function add<%=i%>() {
            $.ajax({
                type: "post",
                url: "ShoppingServlet.do",
                data: {
                    "action": "addCart",
                    "quantity": $("#quantity_Col<%=i%>").text(),
                    "price": $("#price_Col<%=i%>").text(),
                    "menuid": $("#id_Col<%=i%>").text(),
                    "menuno": $('#no_Col<%=i%>').val()

                },
                dataType: "json",
                success: function(menuVO) {
                    //                 	alert();
                    $("#quantity_Col<%=i%>").html(menuVO.memquantity);
                    $("#price_Col<%=i%>").html(menuVO.memprice);
                    $("#id_Col<%=i%>").html(menuVO.memid);
                    $('#total_Col<%=i%>').html(menuVO.memprice * menuVO.memquantity);

                },
                error: function() {
                    alert("連線失敗!");
                }
            })
        }
        //for minus button
        function minus<%=i%>() {
            $.ajax({
                type: "post",
                url: "ShoppingServlet.do",
                data: {
                    "action": "minusCart",
                    "quantity": $("#quantity_Col<%=i%>").text(),
                    "price": $("#price_Col<%=i%>").text(),
                    "menuid": $("#id_Col<%=i%>").text(),
                    "menuno": $('#no_Col<%=i%>').val()

                },
                dataType: "json",
                success: function(menuVO) {
                    //                 	alert(menuVO);
                    $("#quantity_Col<%=i%>").html(menuVO.memquantity);
                    $("#price_Col<%=i%>").html(menuVO.memprice);
                    $("#id_Col<%=i%>").html(menuVO.memid);
                    $('#total_Col<%=i%>').html(menuVO.memprice * menuVO.memquantity);
                },
                error: function() {
                    alert("連線失敗!");
                }
            })

        }

        <%
       		 }
        }
        %>

        //自訂餐點購物車刪除註冊
        <%if(buylistCustom!=null){
       		 for (int i = 0; i < buylistCustom.size(); i++) {%>
        $(function() {
            $("#deleteC<%=i%>").click(function() {
                //alert("11");
                swal({
                    title: "確定刪除餐點？",
                    text: "很好吃喔!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "删除",
                    cancelButtonText: "取消",
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                }).then(function() {
                    // 					alert("submitting!")
                    swal("删除成功！", "別走，建議試試別的口味！.", "success");
                    setTimeout(function() {

                        $(".formC").eq(<%=i%>).submit();
                    }, 1200);


                });
            });
        });

        //for add quantity button for custommeals 
        function addC<%=i%>() {
            $.ajax({
                type: "post",
                url: "ShoppingServlet.do",
                data: {
                    "action": "C_addCart",
                    "quantity": $("#Cquantity_Col<%=i%>").text(),
                    "price": $("#Cprice_Col<%=i%>").text(),
                    "menuid": $("#Cid_Col<%=i%>").text(),
                    "menuno": $('#Cno_Col<%=i%>').val()

                },
                dataType: "json",
                success: function(menuVO) {
                    //                 	alert();
                    $("#Cquantity_Col<%=i%>").html(menuVO.memquantity);
                    $("#Cprice_Col<%=i%>").html(menuVO.memprice);
                    $("#Cid_Col<%=i%>").html(menuVO.memid);
                    $('#Ctotal_Col<%=i%>').html(menuVO.memprice * menuVO.memquantity);

                },
                error: function() {
                    alert("連線失敗!");
                }
            })
        }
        //for minus button for custommeals 
        function minusC<%=i%>() {
            $.ajax({
                type: "post",
                url: "ShoppingServlet.do",
                data: {
                    "action": "C_minusCart",
                    "quantity": $("#Cquantity_Col<%=i%>").text(),
                    "price": $("#Cprice_Col<%=i%>").text(),
                    "menuid": $("#Cid_Col<%=i%>").text(),
                    "menuno": $('#Cno_Col<%=i%>').val()

                },
                dataType: "json",
                success: function(menuVO) {
                    //                 	alert(menuVO);
                    $("#Cquantity_Col<%=i%>").html(menuVO.memquantity);
                    $("#Cprice_Col<%=i%>").html(menuVO.memprice);
                    $("#Cid_Col<%=i%>").html(menuVO.memid);
                    $('#Ctotal_Col<%=i%>').html(menuVO.memprice * menuVO.memquantity);
                },
                error: function() {
                    alert("連線失敗!");
                }
            })

        }

        <%
       		 }
        }
        %>

    </script>

    
    
    
    <jsp:include page="/front_end/footer.jsp" flush="true" />
</body>

</html>
