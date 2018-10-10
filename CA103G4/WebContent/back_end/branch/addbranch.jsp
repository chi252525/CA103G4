<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
//MemServlet.java (Concroller) 存入req的memVO物件 (包括輸入資料錯誤時的memVO物件)
//   MemberVO memVO = (MemberVO)request.getAttribute("memVO");
%>
<!DOCTYPE html>
<html >
    <head>
		<title>竹風堂總公司</title> 
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		<script src="<%=request.getContextPath()%>/front_end/member/js/selectaddress.js"></script>
		<script src="<%=request.getContextPath()%>/front_end/member/js/selectmemphoto.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/branch/css/addbranch.css">

   
    </head>
  

 <body>
	<jsp:include page="/back_end/HeadquarterHeader.jsp"></jsp:include>
<!--your html   start==================================================================================-->
<div class="container col-5 rounded ">
	<form method="post" action="member.do" enctype="multipart/form-data" >
	

    <table id="back" style="padding-top:20px;">
    	<tr>
    	<th colspan="4"><h1 style="color:#ac7339;font-weight: bolder;">分店新增</h1></th>
    	</tr>
        <tr>
            <td class="td1" ><label for="branch_Name" class="col-form-label">分店名稱</label></td>
            <td class="td2" ><input type="text" class="form-control-sm" id="branch_Name" name="branch_Name" value=""></td>
            <td class="td3"><label for="branch_Tel" class="col-form-label">分店電話</label></td>
            <td class="td4"><input type="text" class="form-control-sm" id="branch_Tel" name="branch_Tel" value=""></td>
        </tr>
     
        
        <tr>
            <td class="td1" >鄉鎮區市</td>
            <td class="td2" >
                <select style="display:inline" class="nice-select" name="branch_City" id="city-list" ></select>
                <select style="display:inline" name="branch_Dist" class="nice-select" id="sector-list"></select>
            </td>
            <td class="td3"><label for="branch_Time" class="col-form-label">營業時間</label></td>
            <td class="td4"><input type="text" class="form-control-sm" id="branch_Time" name="branch_Time" value=""></td>
        </tr>
        <tr>
            <td class="td1"><label for="branch_Addr" class="col-form-label">分店地址</label></td>
            <td class="td2" ><input type="text" class="form-control-sm" id="branch_Addr" name="branch_Addr" value=""></td>
            <td class="td3"><label for="branch_Del" class="col-form-label">外送範圍</label></td>
            <td class="td4"><input type="text" class="form-control-sm" id="branch_Del" name="branch_Del" value=""></td>
        </tr>
        <tr>
            <td class="td1"><label for="branch_Lan" class="col-form-label">分店經度</label></td>
            <td class="td2" ><input type="text" class="form-control-sm" id="branch_Lan" name="branch_Lan" value=""></td>
            <td class="td3"><label for="branch_Tdesk" class="col-form-label">桌位總數</label></td>
            <td class="td4"><input type="text" class="form-control-sm" id="branch_Tdesk" name="branch_Tdesk" value=""></td>
        </tr>
        <tr>
            <td class="td1"><label for="branch_Lat" class="col-form-label">分店緯度</label></td>
            <td class="td2" ><input type="text" class="form-control-sm" id="branch_Lat" name="branch_Lat" value=""></td>
            <td class="td3" colspan="2"></td>
          
        </tr>
        <tr>
            <td colspan="4" class="tdbtn">
            <a href="<%=request.getContextPath() %>/front_end/header.jsp" class="btn btn-secondary btn-sm " align="right">取消</a>
            <input type="hidden" name="action" value="insert">
            <input type="submit" class="btn btn-secondary btn-sm " align="right" id="send"value="確認">
            
            </td>
        </tr>
    </table>
    </form>
</div>    

<!--以下勿動-->
    <!-- Footer -->
<jsp:include page="/back_end/HeadquarterFooter.jsp"></jsp:include>
	 <!-- Footer -->		
		</body>
</html>
