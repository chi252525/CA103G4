<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Custommeals: Home</title>

<style>
  table#table-1 {
	width: 650px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>

<table id="table-1">
   <tr><td><h3>IBM Custommeals: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Custommeals: Home</p>

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
  <li><a href='listAllCustommeals.jsp'>List</a> all Custommeals.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="custommeals.do" >
        <b>輸入自訂餐點編號 (如C0000000001):</b>
        <input type="text" name="custom_No">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="custommealsSvc" scope="page" class="com.custommeals.model.CustommealsService" />
   
  <li>
     <FORM METHOD="post" ACTION="custommeals.do" >
       <b>選擇自訂餐點編號:</b>
       <select size="1" name="custom_No">
         <c:forEach var="custommealsVO" items="${custommealsSvc.all}" > 
          <option value="${custommealsVO.custom_No}">${custommealsVO.custom_No}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>


<h3>新增客製化餐點</h3>

<ul>
  <li><a href='addCustommeals.jsp'>Add</a> a new Menu.</li>
</ul>

</body>
</html>