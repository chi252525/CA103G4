<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.empauthority.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="feaSvc" scope="page" class="com.feature.model.FeatureService" />
<!DOCTYPE html>
<html>
<head>
<title>oneEmpAuth</title>
		<!--Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<!--linearicons CSS -->
        <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
        <!-- h&f CSS -->
        <!--your  CSS ============================================= -->

        
       <!--Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


<style type="text/css">

#back {
background-color: rgba(225,225,225,0.45);
border-radius: 3rem;
box-shadow:3px 3px 5px 6px #cccccc;
}

body {
	font-family: Montserrat, Arial, "微軟正黑體", "Microsoft JhengHei" !important;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}
</style>  
</head>

<body>
<form action="empauth.do" enctype="multipart/form-data" >
 <div class="container" id="back" style="text-align:center;">
      <div class="row" >
        <div class="col-md-4">員工照片</div>
        <div class="col-md-4">權限設定</div>
        <div class="col-md-4">確認 / 取消</div>
      </div>
      <div class="row">
        <div class="col-md-4"><img src="<%=request.getContextPath()%>/empshow.do?emp_No=${empVOauth.emp_No}" style="height:80px;width:80px;border-radius:50%" ></div>
        <div class="col-md-4">
		    	<c:forEach var="fealist" items="${feaSvc.all}">
		    		<c:set var="flag" value="true"></c:set>
		        	<c:forEach var="empauthList" items="${empauthList}"><%-- 取得該員工附有的功能權限--%>					        		      	        	        			        		       	
			        		<c:if test="${fealist.fea_No == empauthList.fea_No}">
		    					<c:set var="flag" value="false"></c:set>
			        			<input type=checkbox name="fea_No" id="${fealist.fea_No}" value="${fealist.fea_No}" checked><label for="${fealist.fea_No}" class="col-form-label">${fealist.fea_Name}</label></br>
			        		</c:if>			        		        					        	  
		        	</c:forEach>
		        	
					<c:if test="${flag}">
	        			<input type=checkbox name="fea_No" id="${fealist.fea_No}" value="${fealist.fea_No}" ><label for="${fealist.fea_No}" class="col-form-label">${fealist.fea_Name}</label></br>
        			</c:if>
        			
	        		<c:set var="flag" value="true"></c:set>		        
		        </c:forEach>  

	    
        </div>
        <div class="col-md-4">
        	<input type="hidden" class="btn btn-warning btn-sm " name="emp_No" value="${empVOauth.emp_No}" >
        	<input type="hidden" class="btn btn-warning btn-sm " name="action" value="changeAuth" >
        	<input type="submit" class="btn btn-warning btn-sm " value="確認" >
        	<a href="#" class="btn btn-warning btn-sm " align="right">取消</a>
        </div>
      </div>
    </div>
 </form>
</body>
</html>