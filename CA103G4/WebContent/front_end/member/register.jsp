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
		<!-- Mobile Specific Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- Favicon-->
		<link rel="shortcut icon" href="">
		<!-- Meta Description -->
		<meta name="description" content="">
		<!-- Meta Keyword -->
		<meta name="keywords" content="">
		<!-- Site Title -->
		<title>Register</title> 
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
    <table id="back" style="width:800px;">
        <tr>
            <td colspan="2" id="img-td">
                <input type="file" id="fileElem"  accept="image/*" style="display:none"  onchange="handleFiles()" name="mem_Photo">
                <a href="javascript:doClick()" style="color:black"><img class="custom-img" src="<%= request.getContextPath() %>/front_end/member/img/no-photo.png" id="img" ></a>
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
            <td class="td1" ><label for="mem_Id" class="col-form-label">帳號</label></td><td class="td2" ><input type="text" class="form-control" id="mem_Id" name="mem_Id" value="${(memVOreg==null)? '':memVOreg.mem_Id}"></td><td colspan="2">預設線上訂單收件人資料：(以下非必填)</td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Pw" class="col-form-label">密碼</label></td><td class="td2" ><input type="password" class="form-control" id="mem_Pw" name="mem_Pw" value="${(memVOreg==null)? '':memVOreg.mem_Pw}"></td>
            <td class="td3"><label for="mem_Receiver" class="col-form-label">收件人</label></td><td class="td4"><input type="text" class="form-control" id="mem_Receiver" name="mem_Receiver" value="${(memVOreg==null)? '':memVOreg.mem_Receiver}"></td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Name" class="col-form-label">姓名</label></td><td class="td2" ><input type="text" class="form-control" id="mem_Name" name="mem_Name" value="${(memVOreg==null)? '': memVOreg.mem_Name}"></td>
            <td class="td3"><label for="mem_Repno" class="col-form-label">郵遞區號</label></td><td class="td4"><input type="text" class="form-control" id="mem_Repno" name="mem_Repno" value="${(memVOreg==null)? '':memVOreg.mem_Repno}"></td>
        </tr>
        <tr>
            <td class="td1">性別</td>
            <td class="td2 mem_Gender" >
                <input  type="radio" name="mem_Gender" id="mem_Gender1" value="ms1" ${(memVOreg.mem_Gender == 'ms1' ) ?'checked':'' } >
                <label  for="mem_Gender1">男</label>
                <input  type="radio" name="mem_Gender" id="mem_Gender2" value="ms2" ${(memVOreg.mem_Gender == 'ms2' ) ?'checked':'' } >
                <label  for="mem_Gender2">女</label>
            </td>
            <td class="td3">鄉鎮區市</td>
            <td class="td4">
                <select style="display:inline" class="nice-select" name="mem_Recounty" id="city-list" ></select>
                <select style="display:inline" name="mem_Retown" class="nice-select" id="sector-list"></select>
            </td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Bir" class="col-form-label">生日</label></td><td class="td2" ><input type="Date" class="form-control" id="mem_Bir" name="mem_Bir" value="${(memVOreg==null)? '':memVOreg.mem_Bir}" "></td>
            <td class="td3"><label for="mem_Readdr" class="col-form-label">地址</label></td><td class="td4"><input type="text" class="form-control" id="mem_Readdr" name="mem_Readdr" value="${(memVOreg==null)? '':memVOreg.mem_Readdr}"></td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Mail" class="col-form-label">信箱</label></td><td class="td2" ><input type="email" class="form-control" id="mem_Mail" name="mem_Mail" value="${(memVOreg==null)? '':memVOreg.mem_Mail}"></td>
            <td class="td3"><label for="mem_Cardnum" class="col-form-label">信用卡</label></td><td class="td4"><input type="text" class="form-control" id="mem_Cardnum" name="mem_Cardnum" value="${(memVOreg==null)? '':memVOreg.mem_Cardnum}"></td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Phone" class="col-form-label">電話</label></td><td class="td2" ><input type="text" class="form-control" id="mem_Phone" name="mem_Phone" value="${(memVOreg==null)? '':memVOreg.mem_Phone}"></td>
            <td class="td3"><label for="mem_Carddue" class="col-form-label">截止日</label></td><td class="td4"><input type="text" class="form-control" id="mem_Carddue" name="mem_Carddue" value="${(memVOreg==null)? '':memVOreg.mem_Carddue}"></td>
        </tr>
        <tr>
            <td colspan="4" class="tdbtn">
            <a href="<%=request.getContextPath() %>/front_end/index.jsp" class="btn btn-warning btn-sm " align="right">取消</a>
            <input type="hidden" name="action" value="insert">
            <button type="submit" class="btn btn-warning btn-sm " align="right" id="send">確認</button>
            
            </td>
        </tr>
    </table>
    </form>
</div>    
<a href="javascript:getinfo()" ><img  src="<%= request.getContextPath() %>/front_end/img/cutie.png" ></a>
<script>
function getinfo(){
	$("#mem_Id").val("bigbear");
	$("#mem_Pw").val("123456");
	$("#mem_Name").val("大雄");
	$("#mem_Bir").val("1990-01-22");
	$("#mem_Mail").val("abow129@gmail.com");
	$("#mem_Phone").val("0965231002");
	$("#mem_Receiver").val("小胖");
	$("#mem_Repno").val("32022");
	$("#mem_Readdr").val("中央西路二段281號1樓號");
	$("#mem_Cardnum").val("1111-1222-1111-1111");
	$("#mem_Carddue").val("04/22");
}
</script>



<!--以下勿動-->
    <!-- Footer -->
<jsp:include page="/front_end/footer.jsp"></jsp:include>
	 <!-- Footer -->		
		</body>
	</html>
