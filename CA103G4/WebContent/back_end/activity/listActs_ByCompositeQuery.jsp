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
<style>
body {
	font-family: Montserrat, Arial, "微軟正黑體", "Microsoft JhengHei" !important;
	background-position: center;
}
</style>
</head>
<body>
	<div class="row">
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
					<c:forEach var="activityVO" items="${listActs_ByCompositeQuery}">
						<%@ include file="Content.file"%>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>