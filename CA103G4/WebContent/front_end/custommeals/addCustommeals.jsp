<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.custommeals.model.*"%>

<%
//CustommealsServlet.java (Concroller) 存入req的custommealsVO物件 (包括輸入資料錯誤時的custommealsVO物件)
  CustommealsVO custommealsVO = (CustommealsVO) request.getAttribute("custommealsVO");
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

<title>新增客製化餐點 - addcustommeals.jsp</title>

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
/*     text-align: center; */
    font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
  }
  th, td {
    padding: 5px;
/*     text-align: center; */
    font-family: 'Noto Sans TC', sans-serif;
    font-weight: 600;
  }
  @import url(//fonts.googleapis.com/earlyaccess/notosanstc.css);
  body{background-image:url("images/woodbackground3.png");}

</style>


<!-- <script type="text/javascript"> -->

// $(document).ready(function (){

//     function format_float(num, pos)
//     {
//         var size = Math.pow(10, pos);
//         return Math.round(num * size) / size;
//     }

//     function preview(input) {

//         if (input.files && input.files[0]) {
//             var reader = new FileReader();
//             var index = 0//input.name.slice(-1);
//             reader.onload = function (e) {
//                 $('.preview:eq('+index+')').attr('src', e.target.result);
//                 var KB = format_float(e.total / 1024,2);
//                 $('.size:eq('+index+')').text("檔案大小：" + KB + " KB");               
//             }

//             reader.readAsDataURL(input.files[0]);
//         }

//     }

//     $("body").on("change", ".custom_Photo", function (){
//         preview(this);
//     })
    
// })
<!-- </script> -->

</head>
<body bgcolor='white'>

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>

<table id="table-1">
	<tr><td>
		 <h3>新增客製化餐點 - addCustommeals.jsp</h3></td><td>
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

<FORM METHOD="post" ACTION="custommeals.do" name="form1" class="sum">
<table>

	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mem_No" size="45"	
			value="<%=(custommealsVO==null)?"M000001":custommealsVO.getmem_No()%>" /></td>
	</tr>
	<tr>
		<td>自訂餐點名稱:</td>
		<td><input type="TEXT" name="custom_Name" size="45"	
			value="<%=(custommealsVO==null)?"海陸雙拼大餐":custommealsVO.getcustom_Name()%>" /></td>
	</tr>
	<tr>
		<td>自訂餐點價格:</td>

		<td><input type="TEXT" name="custom_Price" size="45"	
			value="<%=(custommealsVO==null)?"480":custommealsVO.getcustom_Price()%>" /></td>
	</tr>	
	<tr>	
		<td>餐點價格計算:</td>
	
		<td>	  
			<div class="result-area">
		    <span class="cb-count">0</span>元
		    <p class="result-text"></p>
		  	</div>
	    </td>	
	</tr>
	
	<tr>
		<td>食材選擇:</td>
		<td>
            	 麵條
                <input id="I0001" type="checkbox" name="ingredients" value="I0001" price="20"> 細拉麵20
                <input id="I0002" type="checkbox" name="ingredients" value="I0002" price="20"> 烏龍麵20
                <input id="I0003" type="checkbox" name="ingredients" value="I0003" price="20"> 刀削麵20
                <input id="I0004" type="checkbox" name="ingredients" value="I0004" price="20"> 蕎麥麵20
                <input id="I0005" type="checkbox" name="ingredients" value="I0005" price="30"> 墨魚麵30
                <input id="I0006" type="checkbox" name="ingredients" value="I0006" price="20"> 蔬菜麵20
                <br>
               	 湯頭
                <input id="I0007" type="checkbox" name="ingredients" value="I0007"> 醬油湯20
                <input id="I0008" type="checkbox" name="ingredients" value="I0008"> 泡菜湯30
                <input id="I0009" type="checkbox" name="ingredients" value="I0009"> 牛肉湯30
                <input id="I0010" type="checkbox" name="ingredients" value="I0010"> 麻辣湯30
                <input id="I0011" type="checkbox" name="ingredients" value="I0011"> 番茄湯20
                <input id="I0012" type="checkbox" name="ingredients" value="I0012"> 雞湯20
                <input id="I0013" type="checkbox" name="ingredients" value="I0013"> 豚骨湯20
                <input id="I0014" type="checkbox" name="ingredients" value="I0014"> 味增湯20
                <input id="I0015" type="checkbox" name="ingredients" value="I0015"> 咖哩湯30
                <input id="I0015-1" type="checkbox" name="ingredients" value="I0015-1"> 鹽味湯
                <input id="I0015-2" type="checkbox" name="ingredients" value="I0015-2"> 海鮮湯
                <br>
				主餐
                <input id="I0016" type="checkbox" name="ingredients" value="I0016"> 叉燒肉50
                <input id="I0017" type="checkbox" name="ingredients" value="I0017"> 炸豬排70
                <input id="I0018" type="checkbox" name="ingredients" value="I0018"> 炸雞柳50
                <input id="I0019" type="checkbox" name="ingredients" value="I0019"> 唐揚雞塊60
                <input id="I0020" type="checkbox" name="ingredients" value="I0020"> 鮮蝦80
                <input id="I0021" type="checkbox" name="ingredients" value="I0021"> 龍蝦100
                <input id="I0022" type="checkbox" name="ingredients" value="I0022"> 櫻花蝦50
                <input id="I0023" type="checkbox" name="ingredients" value="I0023"> 鴨胸80
                <input id="I0024" type="checkbox" name="ingredients" value="I0024"> 魷魚圈60
                <input id="I0025" type="checkbox" name="ingredients" value="I0025"> 蛤蠣60
                <input id="I0026" type="checkbox" name="ingredients" value="I0026"> 虱目魚70
                <br>
				副餐
                <input id="I0027" type="checkbox" name="ingredients" value="I0027"> 荷包蛋20
                <input id="I0028" type="checkbox" name="ingredients" value="I0028"> 溫泉蛋20
                <input id="I0029" type="checkbox" name="ingredients" value="I0029"> 滷蛋20
                <input id="I0030" type="checkbox" name="ingredients" value="I0030"> 貢丸20
                <input id="I0031" type="checkbox" name="ingredients" value="I0031"> 豆皮20
                <input id="I0032" type="checkbox" name="ingredients" value="I0032"> 筍乾20
                <input id="I0033" type="checkbox" name="ingredients" value="I0033"> 小魚乾20
                <br>
				配料
                <input id="I0034" type="checkbox" name="ingredients" value="I0034"> 韓式泡菜20
                <input id="I0035" type="checkbox" name="ingredients" value="I0035"> 台式泡菜20
                <input id="I0036" type="checkbox" name="ingredients" value="I0036"> 海帶20
                <input id="I0037" type="checkbox" name="ingredients" value="I0037"> 海苔15
                <input id="I0038" type="checkbox" name="ingredients" value="I0038"> 燙青菜30
                <br>
				調味
                <input id="I0039" type="checkbox" name="ingredients" value="I0039"> 蔥花5
                <input id="I0040" type="checkbox" name="ingredients" value="I0040"> 柴魚5
                <input id="I0041" type="checkbox" name="ingredients" value="I0041"> 胡椒5
                <input id="I0042" type="checkbox" name="ingredients" value="I0042"> 辣椒5
                <input id="I0043" type="checkbox" name="ingredients" value="I0043"> 玫瑰鹽5
                <br>
		</td>
	</tr>
	<tr>
	  <div class="result-area">
	    <span class="cb-count">0</span>元
	    <p class="result-text"></p>
	  </div>
	</tr>		
	
<!-- 	<tr> -->
<!-- 		<select name="s1" multiple>  -->
<!-- 			<option value="111">111</option>  -->
<!-- 			<option value="222">222</option>  -->
<!-- 			<option value="333">333</option>  -->
<!-- 			<option value="444">444</option>  -->
<!-- 			<option value="555">555</option>  -->
<!-- 		</select>  -->
<!-- 		<input type="button" value="test" onclick="test()">  -->
<!-- 	</tr> -->
	
	<script> 
		function test() { 
			var arr = new Array();
			var s = document.forms[0].s1; 
			for(var i=0;i<s.options.length;i++) 
				if(s.options[i].selected) 
					arr.push(s.options[i].value);
			alert(arr.join());	 
		} 
		
		
		
        jQuery(function($) {
        	var count = 0;
        	var check = $('.sum').find('input[type="checkbox"]');
        	$('.result-text'); 
          check.prop('checked', false); 

        	
        	check.on('change', function() {
        		if($(this).prop('checked')) {
        			count = count + parseInt($(this).attr('price'));
        		} else {
        			count = count - parseInt($(this).attr('price'));
        		}
        		$('.cb-count').text(count);


        	})
        });
		
		
	</script>
	
	
	
<!-- 	<tr> -->
<!-- 		<td>餐點圖片:</td> -->
<!-- 		<td><input type="file" class="custom_Photo" name="custom_Photo" size="45"></td> -->
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<!-- 		<td></td> -->
<!-- 		<td> -->
<!-- 	        <p>ImgPreview</p> -->
<!-- 	     	<img class="preview" style="max-width: 200px; max-height: 200px;"> -->
<!-- 	        <div class="size"></div> -->
<!-- 		</td> -->
<!-- 	</tr> -->
		
	
</table>


<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>

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