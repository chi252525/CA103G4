<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Perntd: Home</title>

<style>
  table#table-1 {
	width: 450px;
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
   <tr><td><h3>IBM Perntd: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Perntd: Home</p>

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
  <li><a href='listAllPerntd.jsp'>List</a> all Perntds.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="perntd.do" >
        <b>輸入個人通知流水號 (如P000001):</b>
        <input type="text" name="perntd_No">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="perntdSvc" scope="page" class="com.perntd.model.PerntdService" />
   
  <li>
     <FORM METHOD="post" ACTION="perntd.do" >
       <b>選擇個人通知流水號:</b>
       <select size="1" name="perntd_No">
         <c:forEach var="perntdVO" items="${perntdSvc.all}" > 
          <option value="${perntdVO.perntd_No}">${perntdVO.perntd_No}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>


<h3>個人通知管理</h3>

<ul>
  <li><a href='addPerntd.jsp'>Add</a> a new Perntd.</li>
</ul>

</body>
</html>