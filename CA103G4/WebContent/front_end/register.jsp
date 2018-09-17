<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html >
    <head>
		<!-- Mobile Specific Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- Favicon-->
		<link rel="shortcut icon" href="">
		<!-- Meta Description -->
		<meta name="description" content="">
		<!-- Meta Keyword -->
		<meta name="keywords" content="">
		<!-- Site Title -->
		<title>Register</title> 
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <!-- linearicons CSS -->
        <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
        <!-- h&f CSS -->
        <link rel="stylesheet" href="/chiapao/front_end/css/branchheader.css">
        <link rel="stylesheet" href="/chiapao/front_end/css/branchfooter.css">
        <!--your  CSS ============================================= -->
        <script src="/chiapao/front_end/js/selectmemphoto.js"></script>
      


        <link rel="stylesheet" href="/chiapao/front_end/css/register.css">
       <!--Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		<script src="/chiapao/front_end/js/selectaddress.js"></script>
    <style>
        /* 請複製這一段到你自己的CSS*/
        html {
            height: 100%;           
        }
        /*font */ 
        @import url(//fonts.googleapis.com/earlyaccess/notosanstc.css);
        body {
            background-image: url(/chiapao/front_end/img/woodbackground3.png);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center;
            background-size: cover;
            font-family: 'Noto Sans TC', sans-serif;;
            font-weight: 400;
        }  
    </style>
    </head>
    <body>
<section>
<header id="header-whole">
    <div class="header-top" id="header">
        <nav class="navbar-self navbar navbar-expand-lg navbar-dark bg-black">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav nav-fill mx-auto">
                    <li class="nav-item active"><a class="nav-link" href="#">分店資訊
							<span class="sr-only">(current)</span>
					</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">餐點介紹</a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="#"></a>
                    </li>
                    <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 客製化拉麵 </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a> <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="#">線上預約訂位</a>

                    </li>
                    <li class="nav-item"><a class="nav-link" href="#">餐點評分分享</a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="#">線上客服</a>
                    </li>
                    <li>&nbsp;</li>
                    <li>&nbsp;</li>
                    <li>&nbsp;</li>
                    <li class="imgicon"><a href="..."><span class="lnr lnr-cart"></span></a></li>
                    <li class="imgicon"><a href="gallery.html"></a><span class="lnr lnr-alarm"></span></li>
                    <li class="imgicon"><a href="gallery.html"></a><span class="lnr lnr-user"></span></li>

                </ul>
            </div>
        </nav>

    </div>
</header>
        <!--header js-->                     
        <script> $(window).scroll(function() {
                if ($(this).scrollTop() > 0) {
                    $('#header').addClass('header-scrolled');
                } else {
                    $('#header').removeClass('header-scrolled');
                }
            });
        </script>
        <!--background image-->
    <img src="/chiapao/front_end/img/top-banner1.jpg" width="100%" height="" alt="">  
</section>

<!--your html   start==================================================================================-->
<div class="container col-5 rounded ">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:black">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	<form method="POST" action="/mem.do" enctype="multipart/form-data">
    <table  id="back">
        <tr>
            <td colspan="4" id="img-td">
                <input type="file" id="fileElem" multiple accept="image/*" style="display:none" onchange="handleFiles(this.files)" name="mem_Photo">
                <a href="javascript:doClick()" style="color:black">選一張照片吧~</a>
                <div id="fileList">
                </div>
            </td>
        </tr>
        <tr>
            <td class="td1" ><label for="mem_Id" class="col-form-label">帳號</label></td><td class="td2" ><input type="text" class="form-control-sm" id="mem_Id" name="mem_Id"></td><td colspan="2">預設線上訂單收件人資料：(以下非必填)</td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Pw" class="col-form-label">密碼</label></td><td class="td2" ><input type="password" class="form-control-sm" id="mem_Pw" name="mem_Pw"></td>
            <td class="td3"><label for="mem_Receiver" class="col-form-label">收件人</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Receiver" name="mem_Receiver"></td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Name" class="col-form-label">姓名</label></td><td class="td2" ><input type="text" class="form-control-sm" id="mem_Name" name="mem_Name"></td>
            <td class="td3"><label for="mem_Repno" class="col-form-label">郵遞區號</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Repno" name="mem_Repno"></td>
        </tr>
        <tr>
            <td class="td1">性別</td>
            <td class="td2 mem_Gender" >
                <input  type="radio" name="mem_Gender" id="mem_Gender1" value="ms1">
                <label  for="mem_Gender1">男</label>
                <input  type="radio" name="mem_Gender" id="mem_Gender2" value="ms2">
                <label  for="mem_Gender2">女</label>
            </td>
            <td class="td3">鄉鎮區市</td>
            <td class="td4">
                <select style="display:inline" class="nice-select" name="mem_Recounty" id="city-list" ></select>
                <select style="display:inline" name="mem_Retown" class="nice-select" id="sector-list"></select>
            </td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Bir" class="col-form-label">生日</label></td><td class="td2" ><input type="Date" class="form-control-sm" id="mem_Bir" name="mem_Bir"></td>
            <td class="td3"><label for="mem_Readdr" class="col-form-label">地址</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Readdr" name="mem_Readdr"></td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Mail" class="col-form-label">信箱</label></td><td class="td2" ><input type="email" class="form-control-sm" id="mem_Mail" name="mem_Mail"></td>
            <td class="td3"><label for="mem_Cardnum" class="col-form-label">信用卡</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Cardnum" name="mem_Cardnum"></td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Phone" class="col-form-label">電話</label></td><td class="td2" ><input type="text" class="form-control-sm" id="mem_Phone" name="mem_Phone"></td>
            <td class="td3"><label for="mem_Carddue" class="col-form-label">截止日</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Carddue" name="mem_Carddue"></td>
        </tr>
        <tr>
            <td colspan="4" class="tdbtn">
            <input type="button" class="btn btn-warning btn-sm " align="right" value="取消">
            <input type="hidden" name="action" value="insert">
            <input type="submit" class="btn btn-warning btn-sm " align="right" value="確認">
            
            </td>
        </tr>
    </table>
    </form>
</div>    

<!--以下勿動-->
    <!-- Footer -->
<section>
    <footer class="page-footer font-small special-color-dark pt-4">
        <div class="container">
        </div>
        <div class="footer-copyright text-center py-3 footer">© 2018
            <a href="https://mdbootstrap.com/bootstrap-tutorial/"> 資策會</a>
        </div>
    </footer>
</section>
	 <!-- Footer -->		
		</body>
	</html>
