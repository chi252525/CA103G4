<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>儲值查詢</title>

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

<table id="table-1">
   <tr><td><h3>儲值記錄查詢</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Emp: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllStoredrecord.jsp'>List</a> all StoredRecord.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="storedrecord.do" >
        <b>輸入儲值流水單號 (如BXXXXXXXXX):</b>
        <input type="text" name="stor_No">
        <input type="hidden" name="action" value="findByPrimaryKey">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="srSv" scope="page" class="com.storedrecord.model.StoredrecordService" />
   
  <li>
     <FORM METHOD="post" ACTION="storedrecord.do" >
       <b>選擇儲值流水單號:</b>
       <select size="1" name="stor_No">
         <c:forEach var="storedrecordVO" items="${srSv.all}" > 
          <option value="${storedrecordVO.stor_No}">${storedrecordVO.stor_No}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="findByPrimaryKey">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="memSv" scope="page" class="com.member.model.MemberService" />
  
  <li>
     <FORM METHOD="post" ACTION="storedrecord.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="mem_No">
         <c:forEach var="memVO" items="${memSv.all}" > 
          <option value="${memVO.mem_No}">${memVO.mem_No}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="findByMem_no">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>儲值紀錄管理</h3>

<ul>
  <li><a href='addStoredrecord.jsp'>Add</a> a new StoredRecord.</li>
</ul>

</body>
</html>