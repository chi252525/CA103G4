<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>

<%
//PerntdServlet.java (Concroller) 存入req的perntdVO物件 (包括輸入資料錯誤時的perntdVO物件)
  MenuVO menuVO = (MenuVO)request.getAttribute("menuVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<!--     Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    
    <!-- Latest compiled and minified JavaScript -->
<!-- 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
<!-- 	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script> -->

	<meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/menu/css/theme.css" />
	  
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>




<title>竹風堂後台管理系統</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/res/img/icon.png" />

<style>

	body {
		font-family: Montserrat, Arial, "微軟正黑體", "Microsoft JhengHei" !important;
		background-repeat: no-repeat;
		background-attachment: fixed;
		background-position: center;
		background-size: cover;
	}


  table#table-1 {
	background-color: rgba(77, 80, 120, 0.8);
/*     border: 2px solid black; */
	border-radius: 15px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: white;
    display: inline;
  }
</style>

<style>
  table {
    
	width: 1280px;
	background-color: rgba(77, 80, 120, 0.8);
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:320px;
	font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
    font-size: 20;
  }
  table, th, td {
/*     border: 2px solid rgba(255, 255, 255, 0.8); */
    border-radius: 15px;
    text-align: center;
/*     font-family: 'Noto Sans TC', sans-serif; */
    font-weight: 600;
  }
  th, td {
    padding: 5px;
    text-align: center;
    font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
  }
/*   @import url(//fonts.googleapis.com/earlyaccess/notosanstc.css); */
/*   body{background-image:url("images/woodbackground3.png");} */

</style>

<script type="text/javascript">

$(document).ready(function (){

    function format_float(num, pos)
    {
        var size = Math.pow(10, pos);
        return Math.round(num * size) / size;
    }

    function preview(input) {

        if (input.files && input.files[0]) {
            var reader = new FileReader();
            var index = 0//input.name.slice(-1);
            reader.onload = function (e) {
                $('.preview:eq('+index+')').attr('src', e.target.result);
                var KB = format_float(e.total / 1024,2);
                $('.size:eq('+index+')').text("檔案大小：" + KB + " KB");               
            }

            reader.readAsDataURL(input.files[0]);
        }

    }

    $("body").on("change", ".menu_Photo", function (){
        preview(this);
    })
    
})
</script>

</head>
<body>
<jsp:include page="/back_end/HeadquarterHeader.jsp" flush="true" />

<%-- <jsp:include page="/front_end/header.jsp" flush="true"></jsp:include> --%>
<%-- <img src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg" width="100%" height="" alt="banner"> --%>

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>新增經典餐點</h3></td><td> -->
<!-- 		 <h4><a href="select_page.jsp">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>



<div class="container" style="margin-top:80px; position:absulete; margin-left:580px;">
<!-- <div class="container" style="margin-top:100px; position:absulete; margin-left:680px;"> -->
<div class="row" style="margin:0px auto;">
<div style="width:700px;">
<div class="card" style="padding:30px; border-radius:10px; background-color:rgba(255,255,255,0.8);">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/menuServlet.do" name="form1" enctype="multipart/form-data">
	<div class=card-title>
	餐點名稱&nbsp;<input type="TEXT" name="menu_Id" size="70"	value="<%=(menuVO==null)?"青蔥豚骨拉麵":menuVO.getMenu_Id()%>" />
	</div>
		         <input type="hidden" name="menu_Type" size="70"	value="<%=(menuVO==null)?"經典餐點":menuVO.getMenu_Type()%>" />
	<div class=card-title>
	餐點價格&nbsp;<input type="TEXT" name="menu_Price" size="70"	value="<%=(menuVO==null)?"200":menuVO.getMenu_Price()%>" />
	</div>
	<div class=card-title>
	餐點介紹&nbsp;<input type="TEXT" name="menu_Intro" size="70"	value="<%=(menuVO==null)?"濃郁豚骨湯頭、青蔥與叉燒肉香味碰撞的完美組合。":menuVO.getMenu_Intro()%>" />
	</div>
	<div class=card-title>
	餐點狀態&nbsp;<input type="TEXT" name="menu_Status" size="70"	value="<%=(menuVO==null)?"1":menuVO.getMenu_Status()%>" />
	</div>
	<div class=card-title>
	餐點照片&nbsp;<input type="file" class="menu_Photo" name="menu_Photo" size="70">
	</div>
	<div class=card-title>
		<img class="preview" style="max-width: 200px; max-height: 200px;">
	</div>
	<div>
		<input type="hidden" name="action" value="insert">
	    <button type="submit" value="送出新增"> 新增 </button>
	</div>
</FORM>
</div>
</div>
</div>
</div>











<%-- <jsp:include page="/back_end/HeadquarterFooter.jsp" flush="true" /> --%>
</body>





<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>



</html>