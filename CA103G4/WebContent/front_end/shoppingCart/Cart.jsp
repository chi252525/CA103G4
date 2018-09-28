<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.menu.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

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
    <!-- datatable-->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    <!--customized datatable css-->
    <link rel="stylesheet" href="css/datable.css">
    <style>
        table, th, td {
	border: 1px solid black;
	text-align: center;
}
</style>
</head>

<body>
    <br>
    <%
		@SuppressWarnings("unchecked")
		Vector<MenuVO> buylist = (Vector<MenuVO>) session.getAttribute("shoppingcart");
		System.out.println(request.getContextPath() + "/front_end/shoppingCart/css/shoppingCart.css");
	%>
    <%
		if (buylist != null && (buylist.size() > 0)) {
	%>

    <img src="img/logo.png">
    <font size="+3">購物車：（Cart.jsp）</font>
    <div class="container">
        <div class="row">
            <table id="example" class="display table table-striped table-bordered" style="width:100%">
                <thead style="margin-bottom: 15px; ">
                    <tr>
                        <th width="200">餐點名稱</th>
                        <th width="100">價格</th>
                        <th width="100">數量</th>
                        <th width="100">總計</th>
                        <th width="120">操作</th>
                    </tr>
                </thead>
                <tbody>

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
                        <td width="100">
                            <%=menuVO.getMenu_quantity()%>
                        </td>
                        <td width="100">
                            <%=menuVO.getMenu_Price() * menuVO.getMenu_quantity()%>
                        </td>
                        <td width="120">
                            <form name="deleteForm" action="ShoppingServlet.do" method="POST">
                                <input type="hidden" name="action" value="DELETE"> <input type="hidden" name="del" value="<%=index%>">
                                <button class="btn btn-light" type="submit" value="刪除" style="background-color: antiquewhite">
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
        <form name="checkoutForm" action="ShoppingServlet.do" method="POST">
            <input type="hidden" name="action" value="CHECKOUT"> <input type="submit" value="付款結帳" class="button">
        </form>
        <form name="checkoutForm" action="noodleShop.jsp" method="POST">
            <input type="hidden" name="action" value="CHECKOUT"> <input type="submit" value="繼續選購" class="button">
        </form>
        <%
		}
	%>
        <c:if test="${empty shoppingcart}">
            <div class="container">
                <div class="row align-items-center">

                    <div class="card " style="width: 18rem;">
                        <img class="card-img-top" src="img/cart_list.png" alt="Card image cap">
                        <div class="card-body">
                            <p class="card-text">你的購物車還是空的</p>
                        </div>

                        <button class="btn" onclick="window.location.href='noodleShop.jsp'">點餐去!</button>
                    </div>
                </div>
            </div>
            </div>
        </c:if>
        <script>
            document.getElementsByClassName("submit").

            $(document).ready(function() {
                $('#cart').DataTable();
            });

            $('#cart').DataTable({
                scrollY: 100,
                select: true
            });

        </script>
</body>

</html>
