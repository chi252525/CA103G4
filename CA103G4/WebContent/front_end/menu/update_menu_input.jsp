<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>

<%
//PerntdServlet.java (Concroller) 存入req的perntdVO物件 (包括幫忙取出的perntdVO, 也包括輸入資料錯誤時的perntdVO物件)
  MenuVO menuVO = (MenuVO) request.getAttribute("menuVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<!--     Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    
    <!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<title>資料修改 - update_menu_input.jsp</title>

<style>
  table#table-1 {
	background-color: rgba(255, 255, 255, 0.45);
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
    
	width: 960px;
	background-color: rgba(255, 255, 255, 0.45);
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:320px;
/* 	font-family: 'Noto Sans TC', sans-serif; */
    font-weight: 600;
    font-size: 20;
  }
  table, th, td {
/*     border: 2px solid rgba(255, 255, 255, 0.8); */
    border-radius: 15px;
    text-align: left;
/*     font-family: 'Noto Sans TC', sans-serif; */
    font-weight: 600;
  }
  th, td {
    padding: 5px;
    text-align: left;
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

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>餐點資料修改</h3> -->
<!-- 		 <h4><a href="select_page.jsp">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<!-- <FORM METHOD="post" ACTION="menu.do" name="form1" enctype="multipart/form-data"> -->
<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<td>餐點編號:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=menuVO.getMenu_No()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>餐點名稱:</td> -->
<%-- 		<td><input type="TEXT" name="menu_Id" size="45"	value="<%=menuVO.getMenu_Id()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>餐點類型:</td> -->
<%-- 		<td><input type="TEXT" name="menu_Type" size="45"	value="<%=menuVO.getMenu_Type()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>餐點價格:</td> -->
<%-- 		<td><input type="TEXT" name="menu_Price" size="45"	value="<%=menuVO.getMenu_Price()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>餐點介紹:</td> -->
<%-- 		<td><input type="textarea" col="50" row="5" name="menu_Intro" size="45"	value="<%=menuVO.getMenu_Intro()%>" /></td> --%>
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<!-- 		<td>餐點圖片:</td> -->
<!-- 		<td><input type="file" class="menu_Photo" name="menu_Photo" size="45"></td> -->
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<!-- 		<td>餐點狀態:</td> -->
<%-- 		<td><input type="number" name="menu_Status" max="1" min="0" size="3" value="<%=menuVO.getMenu_Status()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td> -->
<!-- 	        <p>ImgPreview</p> -->
<!-- 	     	<img class="preview" style="max-width: 200px; max-height: 200px;"> -->
<!-- 	        <div class="size"></div> -->
<!-- 		</td> -->
<!-- 	</tr> -->
	
<!-- </table> -->

<!-- <br> -->
<!-- <input type="hidden" name="action" value="update"> -->
<%-- <input type="hidden" name="menu_No" value="<%=menuVO.getMenu_No()%>"> --%>
<!-- <input type="submit" value="送出修改"></FORM> -->



<div class="container" style="margin-top:50px; position:absulete; margin-left:680px;">
<div class="row" style="margin:0px auto;">
<div class=col-6>
<div class="card" style="padding:30px; border-radius:10px;">
<FORM METHOD="post" ACTION="menu.do" name="form1" enctype="multipart/form-data">
	<div class=card-title>
	餐點編號&nbsp;<%=menuVO.getMenu_No()%>
	</div>
	<div class=card-title>
	餐點名稱&nbsp;<input type="TEXT" name="menu_Id" size="45"	value="<%=menuVO.getMenu_Id()%>" />
	</div>
	<div class=card-title>
	餐點類型&nbsp;<input type="TEXT" name="menu_Type" size="45"	value="<%=menuVO.getMenu_Type()%>" />
	</div>
	<div class=card-title>
	餐點價格&nbsp;<input type="TEXT" name="menu_Price" size="45"	value="<%=menuVO.getMenu_Price()%>" />
	</div>
	<div class=card-title>
	餐點介紹&nbsp;<input type="TEXT" name="menu_Intro" size="45"	value="<%=menuVO.getMenu_Intro()%>" />
	</div>
	<div class=card-title>
	餐點狀態&nbsp;<input type="number" name="menu_Status" max="1" min="0" size="3" value="<%=menuVO.getMenu_Status()%>" />
	</div>
	<div class=card-title>
	餐點照片&nbsp;<input type="file" class="menu_Photo" name="menu_Photo" size="50">
	</div>
	<div class=card-title>
		<img class="preview" style="max-width: 200px; max-height: 200px;">
	</div>
	<div>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="menu_No" value="<%=menuVO.getMenu_No()%>">
	    <button type="submit" value="送出新增"> 修改 </button>
	</div>
</FORM>
</div>
</div>
</div>
</div>








<jsp:include page="/back_end/HeadquarterFooter.jsp" flush="true" />
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>



</html>