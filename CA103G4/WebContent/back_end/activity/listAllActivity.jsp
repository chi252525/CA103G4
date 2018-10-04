<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<jsp:useBean id="couSvc1" scope="page"
	class="com.coucat.model.CoucatService" />
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

	 <!-- JqueryUI -->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
 		var $JUI = $.noConflict(true);
	</script>
 
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
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
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
              <a href="<%=request.getContextPath()%>/back_end/activity/addCoupon.jsp" class=" nav-link">優惠卷設定</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/back_end/activity/listAllActivity.jsp">廣告設定</a>
            </li>
          </ul>
        </div>
      </div>
     
      <div class="row">
        <div class="col-md-12 p-4">
        <div class="row">
         <div class="col-md-6 ">
          <p class="text-dark">活動查詢</p>
          <ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activityServlet.do" name="form1">
        <b>輸入廣告編號:</b>
        <input type="text" name="act_No" value="" placeholder="YYYYMM-SSSS"><br>
           
       <b>輸入廣告名稱:</b>
       <input type="text" name="act_Name" value="" placeholder="廣告名稱"><br>
       
       <b>選擇優惠卷類別:</b>
       <select size="1" name="coucat_No" >
         <c:forEach var="coucatVO" items="${couSvc1.all}" > 
          <option value="${coucatVO.coucat_No}">${coucatVO.coucat_Cata}
         </c:forEach>   
       </select><br>
	   
	   <p>活動開始日從: <input type="text" id="datepicker1"></p>
	     <p>活動結束日: <input type="text" id="datepicker2"></p>
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listActs_ByCompositeQuery">
     </FORM>
  </li>
</ul>
           </div>
           
           <div class="col-md-6 ">
          <div class="row">
             
             <div class="col-12">
           <p class="text-dark"></p>
          </div>
          <div class="col-6 ">
          
          </div>
            <div class="col-6 ">
          <a href="<%=request.getContextPath()%>/back_end/activity/addAct.jsp" class="btn btn-primary btn-sm active float-right" role="button" aria-pressed="true">新增</a>
          </div>
          </div> 
         
           </div>
          </div>
          
 <script>
  $( function() {
    $JUI( "#datepicker1" ).datepicker();
    $JUI( "#datepicker2" ).datepicker();
  } );
  </script>
          

          
        <%@ include file="pages/page1.file"%>  
   
          </div>
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
                            <c:forEach var="activityVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>" >
                          <tr>
                          <td>${activityVO.act_No}</td>
                            <td>${activityVO.act_Name}</td>
                            <td>${activityVO.coucat_No}</td>   
                             <td>	<fmt:formatDate value="${activityVO.act_PreAddTime}"
									pattern="yyyy/MM/dd-HH:mm" /></td>
                             <td><fmt:formatDate value="${activityVO.act_Start}"
									pattern="yyyy/MM/dd-HH:mm" /></td>
                            <td><fmt:formatDate value="${activityVO.act_End}"
									pattern="yyyy/MM/dd-HH:mm" /></td>
                            <c:if test="${activityVO.act_Status==1}"><td>上架中</td></c:if>
						   <c:if test="${activityVO.act_Status==0}"><td>下架</td></c:if>
                             <td>
                             <button type="button" class="btn btn-secondary"><span class="lnr lnr-arrow-up"></span></button>
                             <button type="button" class="btn btn-secondary"><span class="lnr lnr-arrow-down"></span></button>
                             </td>
                              <td>
                               <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity/activityServlet.do" style="margin-bottom: 0px;">
			     				<input type="hidden" name="act_No"      value="${activityVO.act_No}">
			     				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     				<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     				<input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
                              <button type="submit" class="btn btn-secondary"><span class="lnr lnr-pencil"></span></button></FORM></td>
                          </tr>
                    
             </c:forEach>
                        </tbody>
                      </table>
                       <%@ include file="pages/page2.file"%>
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