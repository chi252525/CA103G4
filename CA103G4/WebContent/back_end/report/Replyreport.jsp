<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report_msg.model.*"%>
<%
	ReportService rptSvc = new ReportService();
	List<ReportVO> list = rptSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Site Title -->
<title>Backend_Reply_Reported</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!-- linearicons CSS -->
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
<!--Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>

<style>
.test {
	border: solid, 1px;
}
</style>
</head>
<body>

	<div class="container-fluid">
		<div class="row"></div>
		<div class="col-sm-12 col-12 col-lg-12">
			<h1>貼文檢舉管理</h1>
			<br>
			<h4>查詢:</h4>
			<div class="container-fluid">
				<div class="row">
					<div class="test col-md-6">
						<form class="form-inline" action="/action_page.php">
							<div class="form-group">
								<select class="form-control">
									<option value="RS0">未處理</option>
									<option value="RS1">已處理-通過</option>
									<option value="RS2">已處理-不通過</option>
								</select>
							</div>
							<button type="button" class="btn btn-primary">查詢</button>
						</form>
	
					</div>
					<div class="test col-md-6">col-6</div>
					<div class="test col-md-6">col-6</div>
				</div>
			</div>

			<%@ include file="pages/page1.file"%>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>檢舉會員編號</th>
						<th>檢舉原因</th>
						<th>處理狀態</th>
						<th>檢舉時間</th>
						<th><span class="lnr lnr-pencil">修改</span></th>
					</tr>
				</thead>
				<c:forEach var="reportVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<tbody>
						<tr>
							<td>${reportVO.rply_No}</td>
							<td>${reportVO.mem_No}</td>
							<td>${reportVO.rpt_Rsm}</td>
							<td>${reportVO.rpt_Status}</td>
							<td>${reportVO.rpt_Time}</td>
							<td>
							
							<form method="post" action="<%=request.getContextPath()%>/report/reportServlet.do">
							<button type="submit" class="btn btn-primary">
						  	<input type="hidden" name="rply_No" value="${reportVO.rply_No}" >
						  	<input type="hidden" name="mem_No" value="${reportVO.mem_No}" >
						  	<input type="hidden" name="rpt_Status" value="${reportVO.rpt_Status}" >
						  	<input type="hidden" name="action" value="update"/>
							<span class="lnr lnr-pencil"></span></button>
							</form>
							
							
							
							</td>
						</tr>
					</tbody>
				</c:forEach>

			</table>
			<%@ include file="pages/page2.file"%>
		</div>

	</div>
</body>
</html>