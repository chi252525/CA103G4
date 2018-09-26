<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%
	ActivityService actSvc = new ActivityService();
	List<ActivityVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Site Title -->
<title>Backend_All_Post</title>
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
</head>
<body>
<%@ include file="/front_end/PostHeader.jsp" %> 
<div class="container-fluid">
    <div class="row">
            </div>
           
            <div class="col-sm-12 col-12 col-lg-12">
                <h1>廣告活動設定</h1>
                  <div class="float-right"><button type="button" class="btn btn-primary">新增</button></div><br>
                 <h4>查詢:</h4>
                 <div class="container-fluid">
                 <div class="row">
               
                 <!--/*依活動日期搜尋區塊-->     
                  <div class="test col-md-6">
                  <p>依活動日期</p>
                 <p>Start Date: <input type="text" class="border-right-0" id="datepicker1" style="width:100px; margin-right: -1px;">
                 <span class="span" style="z-index:100;">~</span><input type="text" class="border-left-0" id="datepicker2" style="width:100px; margin-left:-1px;"></p>
                 <p>End  Date: <input type="text" class="border-right-0" id="datepicker3" style="width:100px; margin-right: -1px;">
                 <span class="span" style="z-index:100;">~</span><input type="text" class="border-left-0" id="datepicker4" style="width:100px; margin-left:-1px;"></p>
                </div>
                <!--依活動日期搜尋區塊*/-->  
                <div class="test col-md-6">
                 <p>依活動類型</p>
                <div class="form-group">
	  				<select class="form-control">
	  					<option value="">1</option>
	  					<option value="">2</option>
	  					<option value="">3</option>
	  				</select>
	  			</div>
                </div>
                <div class="test col-md-6">
                 col-6
                </div>
                <div class="test col-md-6">
                 col-6
                </div>
	  			</div>
	  			</div>
<script>
    $(function() {
        $("#datepicker1").datepicker();
        $("#datepicker2").datepicker();
        $("#datepicker3").datepicker();
        $("#datepicker4").datepicker();
    });
</script>
<table class="table table-hover">
    <thead>
        <tr>
            <th>#</th>
            <th>廣告名稱</th>
            <th>優惠卷編號</th>
            <th>廣告類別</th>
            <th>廣告輪播</th>
            <th>廣告圖片</th>
            <th>開始日期</th>
             <th>結束日期</th>
             <th>是否使用優惠卷</th>
              <td><span class="lnr lnr-pencil"></span></td>
        </tr>
    </thead>
    <tbody>
        <tr>
            <th scope="row">1</th>
              <td>廣告名稱</td>
            <td>優惠卷編號</td>
            <td>廣告類別</td>
            <td>廣告輪播</td>
            <td>廣告圖片</td>
            <td>開始日期</td>
             <td>結束日期</td>
             <td>是否使用優惠卷</td>
              <td><span class="lnr lnr-pencil"></span></td>
        </tr>
        <tr>
            <th scope="row">2</th>
             <td>廣告名稱</td>
            <td>優惠卷編號</td>
            <td>廣告類別</td>
            <td>廣告輪播</td>
            <td>廣告圖片</td>
            <td>廣告內容(不秀)</td>
            <td>開始日期</td>
             <td>結束日期</td>
             <td>是否使用優惠卷</td>
              <td><span class="lnr lnr-pencil"></span></td>
        </tr>
    </tbody>
</table>

            </div>
           
    </div>
<%@ include file="/front_end/PostFooter.jsp" %> 
	
</body>
</html>