<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ingredients.model.*"%>

<%
//IngredientsServlet.java (Controller) 存入req的ingredientsVO物件 (包括幫忙取出的ingredientsVO, 也包括輸入資料錯誤時的ingredientsVO物件)
  IngredientsVO ingredientsVO = (IngredientsVO) request.getAttribute("ingredientsVO");      //從Request取出  IngredientsVO 裡面的物件
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<!--     Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    
    <!-- Latest compiled and minified JavaScript -->
<!-- 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<title>資料修改 - update_ingredients_input.jsp</title>

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
    
	width: 1280px;
	background-color: rgba(255, 255, 255, 0.45);
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:400px;
	font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
    font-size: 20;
  }
  table, th, td {
/*     border: 2px solid rgba(255, 255, 255, 0.8); */
    border-radius: 15px;
    text-align: center;
    font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
  }
  th, td {
    padding: 5px;
    text-align: center;
    font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
  }
  @import url(//fonts.googleapis.com/earlyaccess/notosanstc.css);
  body{background-image:url("images/woodbackground3.png");}

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

    $("body").on("change", ".ingdt_Photo", function (){
        preview(this);
    })
    
})
</script>

</head>
<body bgcolor='white'>

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>

<table id="table-1">
	<tr><td>
		 <h3>資料修改 - update_ingredients_input.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

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

<FORM METHOD="post" ACTION="ingredients.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>食材編號:<font color=red><b>*</b></font></td>
		<td><%=ingredientsVO.getingdt_Id()%></td>
	</tr>
	<tr>
		<td>食材類別編號:</td>
		<td><input type="TEXT" name="ingdtc_Id" size="45"	value="<%=ingredientsVO.getingdtc_Id()%>" /></td>
	</tr>
	<tr>
		<td>食材名稱:</td>
		<td><input type="TEXT" name="ingdt_Name" size="45"	value="<%=ingredientsVO.getingdt_Name()%>" /></td>
	</tr>
	<tr>
		<td>食材狀態:</td>
		<td><input type="TEXT" name="ingdt_Status" size="45" value="<%=ingredientsVO.getingdt_Status()%>" /></td>
	</tr>
	<tr>
		<td>食材兌換點數:</td>
		<td><input type="TEXT" name="ingdt_Point" size="45"	value="<%=ingredientsVO.getingdt_Point()%>" /></td>
	</tr>
	<tr>
		<td>食材單位:</td>
		<td><input type="TEXT" name="ingdt_Unit" size="45"	value="<%=ingredientsVO.getingdt_Unit()%>" /></td>
	</tr>
	<tr>
		<td>食材價格:</td>
		<td><input type="TEXT" name="ingdt_Price" size="45" value="<%=ingredientsVO.getingdt_Price()%>" /></td>
	</tr>
	
	<tr>
		<td>食材圖片:</td>
		<td><input type="file" class="ingdt_Photo" name="ingdt_Photo" size="45"></td>
	</tr>
	
	<tr>
		<td></td>
		<td>
	        <p>ImgPreview</p>
	     	<img class="preview" style="max-width: 200px; max-height: 200px;">
	        <div class="size"></div>
		</td>
	</tr>
	

	
</table>

<br>
<input type="hidden" name="action" value="update">

<input type="hidden" name="ingdt_Id" value="<%=ingredientsVO.getingdt_Id()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y/m/d',         //format:'Y-m-d H:i:s',
 		   value: '<%="2018/10/19"%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
        
</script>
</html>