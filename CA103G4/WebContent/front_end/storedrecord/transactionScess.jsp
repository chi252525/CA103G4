<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.menu.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
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

<head>
    <title>竹風堂-儲值</title>
    <style>
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

        /*         hover effect */
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
            position: absolute;
            overflow: hidden;
            width: 80%;
            height: 80%;
            left: 10%;
            top: 10%;
            border-bottom: 1px solid #FFF;
            border-top: 1px solid #FFF;
            -webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
            transition: opacity 0.35s, transform 0.35s;
            -webkit-transform: scale(0, 1);
            -ms-transform: scale(0, 1);
            transform: scale(0, 1);
        }

        .hovereffect:hover .overlay {
            opacity: 1;
            filter: alpha(opacity=100);
            -webkit-transform: scale(1);
            -ms-transform: scale(1);
            transform: scale(1);
        }

        .hovereffect img {
            display: block;
            position: relative;
            -webkit-transition: all 0.35s;
            transition: all 0.35s;
        }

        .hovereffect:hover img {
            filter:
                url('data:image/svg+xml;charset=utf-8,<svg xmlns="http://www.w3.org/2000/svg"><filter id="filter"><feComponentTransfer color-interpolation-filters="sRGB"><feFuncR type="linear" slope="0.6" /><feFuncG type="linear" slope="0.6" /><feFuncB type="linear" slope="0.6" /></feComponentTransfer></filter></svg>#filter');
            filter: brightness(0.6);
            -webkit-filter: brightness(0.6);
        }

        .hovereffect h2 {
            text-transform: uppercase;
            text-align: center;
            position: relative;
            font-size: 17px;
            background-color: transparent;
            color: #FFF;
            padding: 1em 0;
            opacity: 0;
            filter: alpha(opacity=0);
            -webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
            transition: opacity 0.35s, transform 0.35s;
            -webkit-transform: translate3d(0, -100%, 0);
            transform: translate3d(0, -100%, 0);
        }

        .hovereffect a,
        .hovereffect p {
            color: #FFF;
            padding: 1em 0;
            opacity: 0;
            filter: alpha(opacity=0);
            -webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
            transition: opacity 0.35s, transform 0.35s;
            -webkit-transform: translate3d(0, 100%, 0);
            transform: translate3d(0, 100%, 0);
        }

        .hovereffect:hover a,
        .hovereffect:hover p,
        .hovereffect:hover h2 {
            opacity: 1;
            filter: alpha(opacity=100);
            -webkit-transform: translate3d(0, 0, 0);
            transform: translate3d(0, 0, 0);
        }

    </style>
</head>

<body class="shadow-lg w-100" background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png" width="100%">

    <div class="center d-flex col-md-12" style="margin-top: 150px;">
        <div class="hovereffect center" style="width: 1000px;height:333.33px;margin-bottom: 5%;">
            <img src="images/ad_03.png" class="rounded d-block img-responsive" alt="..." style="align-self: center;width:100%; ">
            <div class="overlay">
            <c:if test="${empty errorMsgs }">
                <h1 style="color:white;">儲值成功</h1>
            </c:if>
            <c:if test="${not empty errorMsgs}">
            	<h1 style="color:white;">${errorMsgs.stor_failur }</h1>
            </c:if>
                <p>
                    <a href="<%=request.getContextPath()%>/front_end/menu/listAllMenu4.jsp" style="margin:25%;">去點餐</a>
                </p>
            </div>
        </div>
    </div>



</body>
<jsp:include page="/front_end/footer.jsp" flush="true" />

</html>
