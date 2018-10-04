<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Orderform: Home</title>

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


</head>
<body bgcolor='white'>

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>

<table id="table-1">
   <tr><td><h3>IBM Orderform: Home</h3><h4></h4></td></tr>
</table>

<p>This is the Home page for IBM Orderform: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
<!--   <li><a href='listAllCustommeals.jsp'>List</a> all Custommeals.  <br><br></li> -->
  
  
  <li>
    <FORM METHOD="post" ACTION="orderform.do" >
        <b>輸入會員編號 (如M000001):</b>
        <input type="text" name="mem_No">
        <input type="hidden" name="action" value="getOne_For_Display_By_Mem_No">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="orderformSvc" scope="page" class="com.orderform.model.OrderformService" />
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="orderform.do" > -->
<!--        <b>選擇會員編號:</b> -->
<!--        <select size="1" name="mem_No"> -->
<%--          <c:forEach var="orderformVO" items="${orderformSvc.all}" >  --%>
<%--           <option value="${orderformVO.mem_No}">${orderformVO.mem_No} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display_By_Mem_No"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  
</ul>


<!-- <h3>新增客製化餐點</h3> -->

<!-- <ul> -->
<!--   <li><a href='addCustommeals.jsp'>Add</a> a new Custommeals.</li> -->
<!-- </ul> -->

</body>
</html>