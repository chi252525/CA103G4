<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.menu.model.*"%>
<html>
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
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<head>
    <title>µ≤±b - Checkout.jsp</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ShoppingCart.css">
    <style>
        table,
        th,
        td {
            border: 1px solid black;
            text-align: center;
        }

    </style>
</head>

<body>
    <img src="images/tomcat.gif">
    <font size="+3">Á´πÈ¢®??- ÁµêÂ∏≥ÔºöÔ?Checkout.jspÔº?/font>
        <hr>
        <p>
            <table id="table-1" style="margin: auto;">
                <tr>
                    <th width="200">¿\¬I¶W∫Ÿ</th>
                    <th width="100">≥Êª˘</th>
                    <th width="100">º∆∂q</th>
                    <th width="120">
                        <h3>¡`√B</h3>
                    </th>
                    <th width="100">≥∆µ˘</th>
                </tr>



                <%
			@SuppressWarnings("unchecked")
			Vector<MenuVO> buylist = (Vector<MenuVO>) session.getAttribute("shoppingcart");
			String amount = (String) request.getAttribute("amount");
		%>
                <%
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
		%>


                <tr>
                    <td colspan="6" style="text-align: right;">
                        <font size="+2">Á∏ΩÈ?È°çÔ?
                            <h4>
                                $
                                <%=amount%>
                            </h4>
                        </font>
                    </td>
                </tr>
            </table>


            <p>
                <div calss=container>
                    <div class="row">
                        <div class="d-flex mx-auto">
                            <button class="btn btn-ligth" onclick="window.location.href='noodleShop.jsp'" style="margin: 5px;">ÁπºÁ?ÈªûÈ?</button>

                            <button class="btn" onclick="window.location.href='noodleShop.jsp'" style="margin: 5px;">ÁµêÂ∏≥??</button>
                        </div>
                    </div>
                </div>

</body>

</html>
