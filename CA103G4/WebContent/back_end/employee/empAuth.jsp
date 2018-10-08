<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.empauthority.model.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="branchSvc" scope="page" class="com.branch.model.BranchService" />
<jsp:useBean id="feaSvc" scope="page" class="com.feature.model.FeatureService" />
<%
EmpVO empVO = (EmpVO) session.getAttribute("empVO");
//取得分店的員工
EmpService empsvc = new EmpService();
List<EmpVO> listemp = new ArrayList();
listemp = empsvc.getEmpAllByBranchNo(empVO.getBranch_No());
pageContext.setAttribute("listemp",listemp);

%>


<!DOCTYPE html>
<html>
<head>
	<title>empAuthor</title>

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

/* hr{ */
/*     margin-bottom: 0 !important; */
/* } */
</style>   
</head>
<body>
<jsp:include page="/back_end/PostHeader.jsp"></jsp:include> 

<div class="container rounded " style="margin-top:200px;padding-top: 10px" id="back" >

	<c:forEach var="branchVO" items="${branchSvc.getAll()}">
		<c:if test="${empVO.branch_No == branchVO.branch_No}">
			${branchVO.branch_Name}
		</c:if>
	</c:forEach>
<%@ include file="pages/page1.file"  %>		
<HR>
	<div class="row" style="text-align:center;">
		<div class="col-md-2">員工編號</div>		
		<div class="col-md-2">員工姓名</div>
		<div class="col-md-2">員工職稱</div>
		<div class="col-md-2">員工照片</div>
		<div class="col-md-2">員工權限</div>
		<div class="col-md-2">修改</div>
	</div>

	<c:forEach var="emp_list" items="${listemp}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<HR style="margin-bottom: 0 !important;">

		<div class="row" style="text-align:center;">
			<div class="col-md-2" style="vertical-align: middle;line-height:120px;">${emp_list.emp_No}</div>
			<div class="col-md-2" style="vertical-align: middle;line-height:120px">${emp_list.emp_Name}</div>
			<div class="col-md-2" style="vertical-align: middle;line-height:120px">${emp_list.emp_Pos}</div>
			<div class="col-md-2" style="vertical-align: middle;line-height:120px"><img src="<%=request.getContextPath()%>/empshow.do?emp_No=${emp_list.emp_No}" style="height:80px;width:80px;border-radius:50%" ></div>
			<div class="col-md-2" style="vertical-align: middle;line-height::120px">						
<%
	EmpauthorityService empauth = new EmpauthorityService();
	List<EmpauthorityVO> empFeaList = new ArrayList();
	EmpVO empNoList = (EmpVO)pageContext.getAttribute("emp_list");//取得分店員工編號
	empFeaList = empauth.oneEmpFealist((empNoList).getEmp_No());//依員工編號取得員工權限編號
	pageContext.setAttribute("empFeaList", empFeaList);
%>

			<c:forEach var="empFeaList" items="${empFeaList}">
				<c:forEach var="feaVO" items="${feaSvc.all}">
					<c:if test="${empFeaList.fea_No == feaVO.fea_No}">
					${feaVO.fea_Name}</br>
					</c:if>
				</c:forEach>								
			</c:forEach>
								
			</div>
			<div class="col-md-2" style="vertical-align: middle;line-height::120px"><a  href="<%=request.getContextPath() %>/empauth.do?emp_No=${emp_list.emp_No}&action=updateAuthor" class="btn btn-warning btn-sm " style="margin-top:45px;">修改</a></div>
		</div>
	</c:forEach>

</div>
<%@ include file="pages/page2.file" %>
<c:if test="${openModal != null}">

<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" style="z-index:1">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content" style="z-index:2">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalCenterTitle">修改權限</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       			<jsp:include page="/back_end/employee/OneEmpAuth.jsp"></jsp:include>
       </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


 </c:if>
        <script>
    		 $("#exampleModalCenter").modal('show');   		 
        </script>



<jsp:include page="/back_end/PostFooter.jsp"></jsp:include>	

</body>
</html>