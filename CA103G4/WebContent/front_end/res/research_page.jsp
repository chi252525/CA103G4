<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reservation.model.*"%>



<%@ include file="/front_end/PostHeader.jsp" %> 

			<div>
     			<b>查詢單一訂位紀錄 - research_page.jsp</b>
     			<hr>
     		</div>
			<hr>
			
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
			  <li><a href='listAllRes.jsp'>List</a> all Res.  <br><br></li>
			<li>
			    <FORM METHOD="post" ACTION="res.do" >
			        <b>輸入訂位流水編號 (如R000000001):</b>
			        <input type="text" name="res_no">
			        <input type="hidden" name="action" value="getOne_For_Display">
			        <input type="submit" value="送出">
			    </FORM>
			  </li>
			
			  <jsp:useBean id="resSvc" scope="page" class="com.reservation.model.ResService" />
			   
			  <li>
			  	<FORM METHOD="post" ACTION="res.do" >
			       <b>選擇訂位流水編號:</b>
			       <select size="1" name="res_no">
			         <c:forEach var="resVO" items="${resSvc.all}" > 
			          <option value="${resVO.res_no}">${resVO.res_no}
			         </c:forEach>   
			       </select>
			       <input type="hidden" name="action" value="getOne_For_Display">
			       <input type="submit" value="送出">
			    </FORM>
			  </li>
			  
			</ul>
			
			
			
			
			<ul>
			  <li><a href='addRes.jsp'>Add</a> a new Res.</li>
			</ul>


<%@ include file="/front_end/PostFooter.jsp" %> 