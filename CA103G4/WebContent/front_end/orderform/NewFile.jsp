<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.orderform.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/front_end/orderform/orderform.do"
		id="a" name="First">
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="新增">
			
</FORM>
</body>
</html>