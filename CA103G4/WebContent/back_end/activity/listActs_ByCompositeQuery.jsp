<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="couSvc1" scope="page"
	class="com.coucat.model.CoucatService" />
<jsp:useBean id="listActs_ByCompositeQuery" scope="request" type="java.util.List<ActivityVO>" /> <!-- 於EL此行可省略 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Site Title -->
<title>Backend_All_Act</title>

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
<script
  src="https://code.jquery.com/ui/1.9.1/jquery-ui.js"
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
	font-size: 20px;
}

body {
	font-family: Montserrat, Arial, "微軟正黑體", "Microsoft JhengHei" !important;
	background-position: center;
}


</style>
</head>
<body>
<jsp:include page="/back_end/HeadquarterHeader.jsp" flush="true" />
<div class="py-5 " >
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <ul class="nav nav-tabs">
            <li class="nav-item">
              <a href="<%=request.getContextPath()%>/back_end/activity/listAllCoucat.jsp" class=" nav-link">優惠卷設定</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/back_end/activity/listAllActivity.jsp">廣告設定</a>
            </li>
          </ul>
        </div>
      </div>
     
      
      
      <div class="row">
      
           <%@ include file="pages/page1_ByCompositeQuery.file" %>
          <!-- 表格 -->
            <div class="col-md-12 p-1">
          <form>
            <div class="row">
                    <div class="col-md-12">
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
                            <c:forEach var="activityVO" items="${listActs_ByCompositeQuery}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>" >
                          <tr ${(activityVO.act_No==param.act_No) ? 'bgcolor=#CCCCFF':''}>
                          <td>${activityVO.act_No}</td>
                            <td>${activityVO.act_Name}</td>
                            <td>${activityVO.coucat_No}</td>   
                             <td>	<fmt:formatDate value="${activityVO.act_PreAddTime}"
									pattern="yyyy/MM/dd-HH:mm" /></td>
                             <td><fmt:formatDate value="${activityVO.act_Start}"
									pattern="yyyy/MM/dd-HH:mm" /></td>
                            <td><fmt:formatDate value="${activityVO.act_End}"
									pattern="yyyy/MM/dd-HH:mm" /></td>
                            <td>
                            
                            
							<td>上架中</td>
						
                            
                          
                        
                             <td><button type="button" class="btn btn-secondary"><span class="lnr lnr-arrow-up"></span></button>
                             	<button type="button" class="btn btn-secondary"><span class="lnr lnr-arrow-down"></span></button>
                             </td>
                              <td><button type="button" class="btn btn-secondary"><span class="lnr lnr-pencil"></span></button></td>
                          </tr>
                    
             </c:forEach>
                        </tbody>
                      </table>
                                <br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>
                      <%@ include file="pages/page2_ByCompositeQuery.file" %>
              </div>

            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
<jsp:include page="/back_end/HeadquarterFooter.jsp" flush="true" />
</body>
</html>