<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%><%-- s分頁 --%>
<%@ page import="com.delivery.model.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.branch.model.*"%>
<%-- <%
//   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
//   response.setHeader("Pragma","no-cache");        //HTTP 1.0
//   response.setDateHeader ("Expires", 0);
 %> --%>
<%-- <jsp:useBean id="delSvc" scope="page" class="com.delivery.model.DeliveryService" /> --%>

<%
pageContext.setAttribute("empVO",request.getSession().getAttribute("empVO"));
%>



<%-- s分頁 --%>
<jsp:useBean id="get_By_Key" scope="session" type="java.util.List<DeliveryVO>" />
<jsp:useBean id="beSvc" scope="page" class="com.branch.model.BranchService" />


<html>
<head>
<meta charset="UTF-8">
<title>所有部門 - listByKey.jsp</title>

<style>
  table#table-1 {
	background-color: orange;
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
	background-color: white;
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
</style>

</head>
<body>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<jsp:useBean id="deSvc" scope="page" class="com.delivery.model.DeliveryService" />

<table class="table table-hover">
	<tr>
		<th>派送單編號</th>
		<th>分店名稱</th>
		<th>員工</th>
		<th>派送單狀態</th>
	</tr>
<%-- 分頁 --%>
<%@ include file="page1.file" %>
<%-- 員工編號，一開始為下拉式選單和按鈕，點擊後派送單狀態會變為2，成為按鈕，選擇後選單和按鈕會消失 --%><%-- s分頁 --%>
<c:forEach var="deliveryVO" items="${get_By_Key}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<tr>
	<td>${deliveryVO.deliv_no}</td>
	<td>${beSvc.getBranch(deliveryVO.branch_no).branch_Name}</td>
		
	<td>
	
	<jsp:useBean id="empSvc" scope="page" class="com.employee.model.EmpService" />

	<c:if test="${(deSvc.out).size() != (empSvc.getOutEmpByBranchNo(empVO.branch_No)).size()}">
		<c:if test="${deliveryVO.emp_no == null}">
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do">
				<select size="1" name="emp_no">
					<c:forEach var="empVO" items="${empSvc.getOutEmpByBranchNo(empVO.branch_No)}">
						<c:if test="${!(deSvc.out).contains(empVO.emp_No)}">
							<option value="${empVO.emp_No}" ${(deliveryVO.emp_no==empVO.emp_No)? 'selected':'' } >${empVO.emp_Name}
						</c:if>
					</c:forEach>
				</select>
							
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="deliv_no"  value="${deliveryVO.deliv_no}">
					<input type="hidden" name="deliv_status"  value="${deliveryVO.deliv_status}">
					<input type="hidden" name="whichPage"	value="<%=whichPage%>">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="submit" value="指派外送員">
			</form>	
		</c:if>
	</c:if>
	
	<c:if test="${(deSvc.out).size() == (empSvc.getOutEmpByBranchNo(empVO.branch_No)).size() and deliveryVO.emp_no == null}">
		目前尚無可派送員工
	</c:if>
	
						
		<c:if test="${deliveryVO.emp_no != null}">			
					<c:forEach var="empVO" items="${empSvc.getOutEmpByBranchNo(empVO.branch_No)}">
					<c:if test="${deliveryVO.emp_no==empVO.emp_No}">
					${empVO.emp_No}-${empVO.emp_Name}
					</c:if>
					</c:forEach>
		</c:if>
		</td>
			
<%-- 下面可以操控狀態，1為等待派送文字，2時為可以點擊確認的按鈕，點擊後會成為3，派送完成的字樣--%>
		
		<td>
			<c:if test="${deliveryVO.deliv_status == '1'}">
				 等待派送
			</c:if>
			<c:if test="${deliveryVO.deliv_status == '2'}">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/delivery/delivery.do">
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="deliv_no"  value="${deliveryVO.deliv_no}">
					<input type="hidden" name="emp_no"  value="${deliveryVO.emp_no}">
					<input type="hidden" name="deliv_status"  value="${deliveryVO.deliv_status}">
					<input type="hidden" name="whichPage"	value="<%=whichPage%>">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="submit" value="確認完成">
				</form>	
			</c:if>
			<c:if test="${deliveryVO.deliv_status == '3'}">
				 派送完成
			</c:if>
		</td>
	</tr>
</c:forEach>
	
</table>
<%-- 分頁 --%>
<%@ include file="page2.file" %>

</body>
</html>