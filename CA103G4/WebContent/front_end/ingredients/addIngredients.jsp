<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--     告知瀏覽器 編碼                                                   告知伺服器 編碼 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ingredients.model.*"%>

<%
//IngredientsServlet.java (Controller) 存入req的ingredientsVO物件 (包括輸入資料錯誤時的ingredientsVO物件)
  IngredientsVO ingredientsVO = (IngredientsVO) request.getAttribute("ingredientsVO");    //從Request取出  IngredientsVO 裡面的物件
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

<title>新增客製化餐點 - addingredients.jsp</title>

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
<img src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg" width="100%" height="" alt="banner">

<table id="table-1">
	<tr><td>
		 <h3>新增食材 - addIngredients.jsp</h3></td><td>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
					<!--進行上傳工作之Servlet -->
<FORM METHOD="post" ACTION="ingredients.do" name="form1" enctype="multipart/form-data">       
															<!-- 上傳三要素第一步 用post方法，表單標籤內加上enctype="multipart/form-data -->
<table>

	<tr>
		<td>食材類別編號:</td>
		<td><input type="TEXT" name="ingdtc_Id" size="45"	
			value="<%=(ingredientsVO==null)?"T0001":ingredientsVO.getingdtc_Id()%>" /></td>
<!-- 			如果ingredientsVO是空值，則填入T0001的預設值 -->
	</tr>
	<tr>
		<td>食材名稱:</td>
		<td><input type="TEXT" name="ingdt_Name" size="45"	
			value="<%=(ingredientsVO==null)?"細拉麵":ingredientsVO.getingdt_Name()%>" /></td>
	</tr>
	
	<tr>
		<td>食材狀態:</td>
		<td><input type="TEXT" name="ingdt_Status" size="45"	
			value="<%=(ingredientsVO==null)?"1":ingredientsVO.getingdt_Status()%>" /></td>
	</tr>
	
	<tr>
		<td>食材兌換點數:</td>
		<td><input type="TEXT" name="ingdt_Point" size="45"	
			value="<%=(ingredientsVO==null)?"1":ingredientsVO.getingdt_Point()%>" /></td>
	</tr>
	
	<tr>
		<td>食材單位:</td>
		<td><input type="TEXT" name="ingdt_Unit" size="45"	
			value="<%=(ingredientsVO==null)?"一份":ingredientsVO.getingdt_Unit()%>" /></td>
	</tr>
	
	<tr>
		<td>食材價格:</td>
		<td><input type="TEXT" name="ingdt_Price" size="45"	
			value="<%=(ingredientsVO==null)?"30":ingredientsVO.getingdt_Price()%>" /></td>
	</tr>
	
	<tr>
		<td>食材圖片:</td>
		<td><input type="file" class="ingdt_Photo" name="ingdt_Photo" size="45"></td>
	</tr>
	
	<tr>
		<td></td>
		<td>
	        <p></p>
	     	<img class="preview" style="max-width: 200px; max-height: 200px;">
	        <div class="size"></div>
		</td>
	</tr>
		
	
</table>


<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>



 
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


</html>