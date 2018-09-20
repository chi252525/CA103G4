<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Ingredients: Home</title>

<style>
  table#table-1 {
	background-color: #ffe66f;
    border: 2px solid black;
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

<style>
  table {
	width: 800px;
	background-color: rgba(255, 255, 255, 0.35);
	margin-top: 5px;
	margin-bottom: 5px;
	
	
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  
  body{
	background-image:url("images/woodbackground3.png")
}

</style>


</head>
<body bgcolor='white'>

<jsp:include page="/front_end/header.jsp" flush="true"></jsp:include>

<table id="table-1">
   <tr><td><h3>IBM Ingredients: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Ingredients: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">  
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>            
<!-- 			forEach迴圈顯示 若發生錯誤時的訊息 -->
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllIngredients.jsp'>List</a> all Ingredients.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="ingredients.do" >
        <b>輸入食材編號 (如I0001):</b>
        <input type="text" name="ingdt_Id">
        <input type="hidden" name="action" value="getOne_For_Display">     
        <input type="submit" value="送出">
    </FORM>
  </li>
<!--                                                  類別名稱="套件名稱"                                                                       -->
  <jsp:useBean id="ingredientsSvc" scope="page" class="com.ingredients.model.IngredientsService" />
   
  <li>
     <FORM METHOD="post" ACTION="ingredients.do" >
       <b>選擇食材編號:</b>
       <select size="1" name="ingdt_Id">
         <c:forEach var="ingredientsVO" items="${ingredientsSvc.all}" >              
          <option value="${ingredientsVO.ingdt_Id}">${ingredientsVO.ingdt_Id}
<!--           		用forEach迴圈取得ingredientsVO內，主鍵的值，放入下拉式選單 -->
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>


<h3>新增食材</h3>

<ul>
  <li><a href='addIngredients.jsp'>Add</a> a new Ingredients.</li>
</ul>

</body>
</html>