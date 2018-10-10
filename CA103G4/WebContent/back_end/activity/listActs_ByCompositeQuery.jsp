<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<jsp:useBean id="couSvc1" scope="page"
	class="com.coucat.model.CoucatService" />
<jsp:useBean id="listActs_ByCompositeQuery" scope="session"
	type="java.util.List<ActivityVO>" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Site Title -->
<title>ListActs_ByCompositeQuery</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!-- linearicons CSS -->
<link rel="stylesheet"
	href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.js"></script>
<!-- date -->
<script src="https://code.jquery.com/ui/1.9.1/jquery-ui.js"
	integrity="sha256-tXuytmakTtXe6NCDgoePBXiKe1gB+VA3xRvyBs/sq94="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css">

<!--Bootstrap JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>

<style>
html {
	height: 100%;
	font-family: 'PT Sans', Microsoft JhengHei, sans-serif;
	font-size: 15px;
}

body {
	font-family: Montserrat, Arial, "微軟正黑體", "Microsoft JhengHei" !important;
	background-position: center;
}
</style>
</head>
<body>

	<div class="py-5 ">
		<div class="row">
		
			<!-- 表格 -->
			<div class="col-sm-12 p-1">
				<form>
							<table class="table">
								<thead>
									<tr>
										<th>#</th>
										<th>活動名稱</th>
										<th>優惠卷類別編號</th>
										<th>預計上架日</th>
										<th>活動起始日</th>
										<th>下架日</th>
										<th>狀態</th>
										<th>操作</th>
										<th>修改</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="activityVO"
										items="${listActs_ByCompositeQuery}">
										<%@ include file="Content.file"%>
									</c:forEach>
								</tbody>
							</table>
						
				</form>
			</div>
		</div>

	</div>

</body>
</html>