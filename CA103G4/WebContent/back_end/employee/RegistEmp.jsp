<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.feature.model.*"%>
<%@ page import="com.branch.model.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="java.util.*"%>
<% 

FeatureService feasvc = new FeatureService(); 
List<FeatureVO> fealist = new ArrayList();
fealist = feasvc.getAll();
pageContext.setAttribute("fealist", fealist);

BranchService branSvc = new BranchService();
List<BranchVO> branlist = new ArrayList();
branlist = branSvc.getAll();
pageContext.setAttribute("branlist",branlist);

%>


<!DOCTYPE html>
<html >
	<title>竹風堂-分店員工新增</title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/res/img/icon.png" />
    <head>
		<!-- Site Title -->
		<title>Post</title> 
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <!-- linearicons CSS -->
        <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
        <!-- h&f CSS -->
        <!--your  CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="css/RegistEmp.css">
        
       <!--Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="js/selectmemphoto.js"></script>
    </head>
<body>
<jsp:include page="/back_end/PostHeader.jsp"></jsp:include>
<!--your html   start==================================================================================-->

<div class="container col-5 rounded " style="padding-top: 100px;">
<Form action="emp.do" method="POST" enctype="multipart/form-data" >
    <table  id="back">
    			<c:if test="${not empty errorMsgs}">
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:black">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
        <tr>
            <td colspan="2" id="img-td">
                <input type="file" id="fileElem" multiple accept="image/*" style="display:none" onchange="handleFiles()" name="emp_Photo">
                <a href="javascript:doClick()"><img class="custom-img" src="<%= request.getContextPath() %>/back_end/employee/img/no-photo.png" id="img"></a>
                <div id="fileList">              
                </div>
            </td>
        </tr>
        <tr>
            <td class="td1"><label for="branch_No" class="col-form-label">分店編號</label>				
				</td>
				<td class="td2" >
            <select class="form-control-sm" id="branch_No" name="branch_No">
            	<c:forEach var="branchVO" items="${branlist}" >
					<option value="${branchVO.branch_No}">${branchVO.branch_Name}</option>             	
				</c:forEach>
        
            </select>
            </td>
        </tr>
        <tr>
            <td class="td1" ><label for="emp_Acnum" class="col-form-label">帳號</label></td><td class="td2" ><input type="text" class="form-control-sm empinput" id="emp_Acnum" name="emp_Acnum" value="${empVOregist.emp_Acnum}"></td>
        </tr>
        <tr>
            <td class="td1"><label for="emp_Psw" class="col-form-label">密碼</label></td><td class="td2" ><input type="password" class="form-control-sm empinput" id="emp_Psw" name="emp_Psw" value="${empVOregist.emp_Psw }"></td>
        </tr>
        <tr>
            <td class="td1">性別</td>
            <td class="td2 emp_Gender" >
                <input  type="radio" name="emp_Gender" id="emp_Gender1" value="1" >
                <label  for="emp_Gender1" style="padding-right: 20px">男</label>
                <input  type="radio" name="emp_Gender" id="emp_Gender2" value="2">
                <label  for="emp_Gender2">女</label>
            </td>
        </tr>

        <tr>
            <td class="td1"><label for="emp_Name" class="col-form-label">員工姓名</label></td><td class="td2" ><input type="text" class="form-control-sm empinput" id="emp_Name" name="emp_Name" value="${empVOregist.emp_Name }">
        </tr>
        <tr>
            <td class="td1"><label for="emp_Tel" class="col-form-label">員工電話</label></td>
            <td class="td2" ><input type="text" class="form-control-sm empinput" id="emp_Tel" name="emp_Tel" value="${empVOregist.emp_Tel }"></td>
        </tr>        

        <tr>
            <td class="td1"><label for="emp_Pos" class="col-form-label">員工職稱</label></td>
            <td class="td2" >
                <select class="form-control-sm" id="emp_Pos" name="emp_Pos">
                    <option value="請選擇">請選擇</option>
                    <option value="店長">店長</option>
                    <option value="服務生">服務生</option>
                    <option value="外送員">外送員</option>
                    <option value="櫃台人員">外送員</option>
                    <option value="客服人員">客服人員</option>
                </select>    
            </td>
        </tr>
        <tr>
            <td colspan="2" class="td2 fea">功能使用權限</td>
        </tr>
        <tr><td  style="padding-left:50px;">
        	<c:forEach var="featureVO" items="${fealist}" begin="1" end="5" >
  					<input type=checkbox name="fea_No" id="${featureVO.fea_No}" value="${featureVO.fea_No}"><label for="${featureVO.fea_No}" class="col-form-label">${featureVO.fea_Name}</label></br> 
			</c:forEach>
			</td>
			<td style="padding-left:80px;">
        	<c:forEach var="featureVO" items="${fealist}" begin="6" >
  					<input type=checkbox name="fea_No" id="${featureVO.fea_No}" value="${featureVO.fea_No}"><label for="${featureVO.fea_No}" class="col-form-label">${featureVO.fea_Name}</label></br>  
			</c:forEach>			
			</td>

        </tr>

        <tr>
            <td colspan="2" class="tdbtn">
            <input type="button" class="btn btn-warning btn-sm " align="right" id="td2cancel" value="取消">
            <input type="hidden" name="action" value="empinsert">            
            <input type="submit" class="btn btn-warning btn-sm " align="right" id="td2yes" value="確認">
            </td>
        </tr>
    </table>
</Form>
</div>    
<a href="javascript:getinfo()" ><img  src="<%= request.getContextPath() %>/front_end/img/cutie.png" ></a>
<!--以下勿動-->
<script>
function getinfo(){
	$("#emp_Acnum").val("aaaa");
	$("#emp_Psw").val("123456");
	$("#emp_Name").val("小珠");
	$("#emp_Tel").val("0933124555");
}
</script>
<jsp:include page="/back_end/PostFooter.jsp"></jsp:include>		
</body>
</html>
