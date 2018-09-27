<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>>
<%@ page import="com.member.model.*"%>
<% MemberVO memVO = (MemberVO) session.getAttribute("memVO"); %>
<%session.getAttribute("mem_Id"); %>




<!DOCTYPE html>
<html >
    <head>
		<!-- Mobile Specific Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- Favicon-->
		<link rel="shortcut icon" href="">
		<!-- Meta Description -->
		<meta name="description" content="">
		<!-- Meta Keyword -->
		<meta name="keywords" content="">
		<!-- Site Title -->
		<title>MemberInfo</title> 
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <!--your  CSS ============================================= -->
       

       <!--Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		<script src="<%=request.getContextPath()%>/front_end/member/js/selectaddress.js"></script>
		<script src="<%=request.getContextPath()%>/front_end/member/js/selectmemphoto.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/member/css/register.css">
    </head>
    

 <body>
	<jsp:include page="/front_end/header.jsp"></jsp:include>
		
		<img
			src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg"
			width="100%" height="" alt="">
<!--your html   start==================================================================================-->

<div class="container col-5 rounded ">
	<form method="post" action="member.do" enctype="multipart/form-data" >
			
    <table id="back">
        <tr>
            <td colspan="2" id="img-td">
                <img class="custom-img" src="<%=request.getContextPath()%>/front_end/member/member.do?mem_No=<%= memVO.getMem_No()%>" id="img" >
            </td>
            <td colspan="2" align="left">
				<c:if test="${not empty errorMsgs}">
					<font style="color:black;" >請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:black">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
            </td>
        </tr>
        <tr>
            <td class="td1" ><label for="mem_Id" class="col-form-label">帳號</label></td><td class="td2" ><input type="text" class="form-control-sm" id="mem_Id" name="mem_Id" value="${mem_Id}" readonly="readonly"></td><td colspan="2" readonly="readonly">預設線上訂單收件人資料：(以下非必填)</td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Pw" class="col-form-label">密碼</label></td><td class="td2" ><input type="password" class="form-control-sm" id="mem_Pw" name="mem_Pw" value="<%=memVO.getMem_Pw()%>" readonly="readonly"></td>
            <td class="td3"><label for="mem_Receiver" class="col-form-label">收件人</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Receiver" name="mem_Receiver" value="<%=(memVO.getMem_Receiver()==null)? "":memVO.getMem_Receiver()%>" readonly="readonly"></td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Name" class="col-form-label">姓名</label></td><td class="td2" ><input type="text" class="form-control-sm" id="mem_Name" name="mem_Name" value="<%=memVO.getMem_Name()%>" readonly="readonly"></td>
            <td class="td3"><label for="mem_Repno" class="col-form-label">郵遞區號</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Repno" name="mem_Repno" value="<%=(memVO.getMem_Repno()==null)? "":memVO.getMem_Repno()%>" readonly="readonly"></td>
        </tr>
        <tr>
            <td class="td1">性別</td>
            <td class="td2 mem_Gender" >
                <input  type="radio" name="mem_Gender" id="mem_Gender1" value="ms1" ${(memVO.mem_Gender == 'ms1' ) ?'checked':'' } readonly="readonly">
                <label  for="mem_Gender1">男</label>
                <input  type="radio" name="mem_Gender" id="mem_Gender2" value="ms2" ${(memVO.mem_Gender == 'ms2' ) ?'checked':'' } readonly="readonly">
                <label  for="mem_Gender2">女</label>
            </td>
            <td class="td3">鄉鎮區市</td>
            <td class="td4">
                <select style="display:inline" class="nice-select" name="mem_Recounty" id="city-list" ></select>
                <select style="display:inline" name="mem_Retown" class="nice-select" id="sector-list"></select>
            </td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Bir" class="col-form-label">生日</label></td><td class="td2" ><input type="Date" class="form-control-sm" id="mem_Bir" name="mem_Bir" value="<fmt:formatDate value="<%=memVO.getMem_Bir()%>" pattern="yyyy-MM-dd"/>" readonly="readonly"></td>
            <td class="td3"><label for="mem_Readdr" class="col-form-label">地址</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Readdr" name="mem_Readdr" value="<%=(memVO.getMem_Readdr()==null)? "":memVO.getMem_Readdr()%>" readonly="readonly"></td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Mail" class="col-form-label">信箱</label></td><td class="td2" ><input type="email" class="form-control-sm" id="mem_Mail" name="mem_Mail" value="<%=memVO.getMem_Mail()%>" readonly="readonly"></td>
            <td class="td3"><label for="mem_Cardnum" class="col-form-label">信用卡</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Cardnum" name="mem_Cardnum" value="<%=(memVO.getMem_Cardnum()==null)? "":memVO.getMem_Cardnum()%>" readonly="readonly"></td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Phone" class="col-form-label">電話</label></td><td class="td2" ><input type="text" class="form-control-sm" id="mem_Phone" name="mem_Phone" value="<%=memVO.getMem_Phone()%>" readonly="readonly"></td>
            <td class="td3"><label for="mem_Carddue" class="col-form-label">截止日</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Carddue" name="mem_Carddue" value="<%=(memVO.getMem_Carddue()==null)? "":memVO.getMem_Carddue()%>"readonly="readonly"></td>
        </tr>
        <tr>
            <td colspan="4" class="tdbtn">
            <a href="<%=request.getContextPath() %>/front_end/header.jsp" class="btn btn-warning btn-sm " align="right">返回</a>
            <input type="hidden" name="action" value="update">
            <input type="submit" class="btn btn-warning btn-sm " align="right" id="send"value="修改">
            
            </td>
        </tr>
    </table>
    </form>
</div>    

<!--以下勿動-->
    <!-- Footer -->
<jsp:include page="/front_end/footer.jsp"></jsp:include>
	 <!-- Footer -->		
		</body>
	</html>