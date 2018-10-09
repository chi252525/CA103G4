<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
//MemServlet.java (Concroller) 存入req的MemberO物件
  MemberVO memVO = (MemberVO)request.getAttribute("memVO");
%>
<!DOCTYPE html>
<html>
<head>
<title>login</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <!-- linearicons CSS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		<!-- myself CSS -->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/member/css/login.css">	
</head>
<jsp:include page="/front_end/header.jsp" flush="true" />
<body background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "
	width="100%" height="">
<!-- header勿動 -->
	<img src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg"
			width="100%" height="" alt="">

<!--以下為login區塊-->
<div class="container col-3 loginarea" style="padding-bottom:30px;">	

				<c:if test="${not empty errorMsgs}">
					<font style="color:red;" >請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:black">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				
		<form action="member.do" method="post" >	
            <div class="tablebg" >
				<table >					
					<tr class="tr1">
						<td class="td1" style="padding-top:20px;">帳號</td> 
						<td class="td2" style="padding-top:20px;">
							<input class="form-control-sm custom-input" type=text size=15 name="mem_Id">
						</td>
					</tr>
					<tr>
						<td class="td1">密碼</td> 
						<td class="td2">
							<input class="form-control-sm custom-input" type=password  size=15 name="mem_Pw"></td></tr>
					<tr >
						<td align="left">
                            <a href="<%= request.getContextPath() %>/front_end/member/register.jsp" class="custom-btn btn btn-warning btn-sm"  style="margin-left: 30px;" >註冊</a>
                        </td>
                        <td align="right">
							<a href="<%=request.getContextPath() %>/front_end/index.jsp" class="custom-btn btn btn-warning btn-sm" align="right">取消</a>
							<input type="hidden" name="action" value="loginhandler">
                            <input class="custom-btn btn btn-warning btn-sm" type="submit" value="確認" > 
						</td>
					</tr>					
				</table>
            </div>
		</form>
</div>
<!-- footer勿動 -->
<jsp:include page="/front_end/footer.jsp"></jsp:include>
</body>
</html>