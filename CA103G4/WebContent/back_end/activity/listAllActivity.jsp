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
	List<ActivityVO> list=null;
	if(request.getAttribute("listActs_ByCompositeQuery")==null){
		 list = actSvc.getAll();
		pageContext.setAttribute("list", list);
	}else{
		 list =(List<ActivityVO>)session.getAttribute("listActs_ByCompositeQuery");
		pageContext.setAttribute("listActs_ByCompositeQuery", list);
	}
	
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
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}

.item {
	margin-left: 5px;
}
</style>
</head>
<body>
	<jsp:include page="/back_end/HeadquarterHeader.jsp" flush="true" />

	<div class="py-2 ">
		<div class="container-fluid px-5 mb-5">
			<div class="row">
				<ul class="nav nav-tabs">
					<li class="nav-item"><a
						href="<%=request.getContextPath()%>/back_end/activity/addCoupon.jsp"
						class=" nav-link">優惠卷設定</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/back_end/activity/listAllActivity.jsp?action=listActs_ByCompositeQuery">廣告設定</a>
					</li>
				</ul>

				<div class="card">
					<div class="card-body">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/activity/activityServlet.do">
							<div class="d-flex flex-wrap ">
								<div class="item">
									<label for="sel1" class="text-dark">分類</label> <select
										class="form-control combobox" id="sel1" name="act_Cat">
										<option disabled selected value>--請選擇--</option>
										<option value="AC1">新品上市</option>
										<option value="AC2">優惠折扣</option>
										<option value="AC3">分店限定</option>
									</select>
								</div>

								<div class="item">
									<label for="sel1" class="text-dark">廣告編號</label> <input
										type="text" name="act_No" class="form-control" value=""
										placeholder="YYYYMM-SSSS">
								</div>
								<div class="item ">
									<label for="sel1" class="text-dark">廣告名稱</label> <input
										type="text" name="act_Name" class="form-control" value=""
										placeholder="輸入廣告名稱">
								</div>
								<div class="item">
									<label for="sel1" class="text-dark">活動開始日從:</label> <input
										type="text" id="datepicker1" name="act_Start"
										class="form-control">
								</div>
								<div class="item">
									<label for="sel1" class="text-dark">到</label> <input
										type="text" name="act_End" id="datepicker2"
										class="form-control">
								</div>

								<div class="item">
									<label for="sel1" class="text-dark">優惠卷類別</label> <select
										size="1" class="form-control combobox" name="coucat_No">
										<option disabled selected value>--請選擇--</option>
										<c:forEach var="coucatVO" items="${couSvc1.all}">
											<option value="${coucatVO.coucat_No}">${coucatVO.coucat_Name}
										</c:forEach>
									</select>
								</div>
								<div class="item py-1">
									<button type="submit"
										class=" btn btn-sm btn-success float-right mt-5 " value="">送出</button>
									<input type="hidden" name="action"	value="listActs_ByCompositeQuery">
								</div>
						</FORM>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="col-12">
		<a href="<%=request.getContextPath()%>/back_end/activity/addAct.jsp"
			class="btn btn-primary btn-sm active float-right" role="button"
			aria-pressed="true">新增</a>
	</div>





	<script>
			$(function() {
				$JUI("#datepicker1").datepicker();
				$JUI("#datepicker2").datepicker();
			});
		</script>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>




<%  int rowsPerPage = 3;  //每頁的筆數    
    int rowNumber=0;      //總筆數
    int pageNumber=0;     //總頁數      
    int whichPage=1;      //第幾頁
    int pageIndexArray[]=null;
    int pageIndex=0; 
%>

<%  
    rowNumber=list.size();
    if (rowNumber%rowsPerPage !=0)
         pageNumber=rowNumber/rowsPerPage + 1;
    else pageNumber=rowNumber/rowsPerPage;    

    pageIndexArray=new int[pageNumber]; 
    for (int i=1 ; i<=pageIndexArray.length ; i++)
         pageIndexArray[i-1]=i*rowsPerPage-rowsPerPage;
%>

<%  try {
       whichPage = Integer.parseInt(request.getParameter("whichPage"));
       pageIndex=pageIndexArray[whichPage-1];
    } catch (NumberFormatException e) { //第一次執行的時候
       whichPage=1;
       pageIndex=0;
    } catch (ArrayIndexOutOfBoundsException e) { //總頁數之外的錯誤頁數
         if (pageNumber>0){
              whichPage=pageNumber;
              pageIndex=pageIndexArray[pageNumber-1];
         }
    } 
%>

<%if (pageNumber>0){%>
  <b><font color=red
  >page<%=whichPage%>/<%=pageNumber%></font></b>
<%}%>

<b>Total<font color=red
><%=rowNumber%></font>筆</b>

	<!-- 表格 -->
	<div class="col-md-12 p-1">
		<ul id="myTab" class="nav nav-tabs">
			<li><a
				class="nav-link <%=request.getAttribute("display") == null ? "" : "active"%>"
				href="#offContent" data-toggle="tab" aria-controls="offContent">
					未上架 </a></li>
			<li><a
				class="nav-link  <%=request.getAttribute("display") == null ? "active" : ""%>"
				href="#onContent" data-toggle="tab"> 已上架 </a></li>
			<li><a
				class="nav-link  <%=request.getAttribute("listActs_ByCompositeQuery") == null ? "" : "active"%>"
				href="#queryContent" data-toggle="tab"> 查詢結果 </a></li>
		</ul>

		<div id="myTabContent" class="tab-content">
			<div id="offContent"
				class="tab-pane fade  <%=request.getAttribute("display") == null ? "" : "active show"%>"
				id="offContent">
				<!-- 未上架的廣告 -->
				<br>
				<table class="table">
					<thead>
						<th>#</th>
						<th>活動名稱</th>
						<th>活動分類</th>
						<th>預計上架日</th>
						<th>開始日</th>
						<th>預計下架日</th>
						<th>結束日</th>
						<th>狀態</th>
						<th>操作</th>
						<th>修改</th>
					</thead>

					<c:forEach var="activityVO" items="${list}">
						<c:if test="${activityVO.act_Status == 0}">
							<%@ include file="Content.file"%>
						</c:if>
					</c:forEach>

				</table>
			</div>

			<!--已上架的廣告  -->
			<div id="onContent"
				class="tab-pane fade  <%=request.getAttribute("display") == null ? "active show" : ""%>"
				id="onContent">
				<br>
				<table class="table">
					<thead>
						<th>#</th>
						<th>活動名稱</th>
						<th>活動分類</th>
						<th>預計上架日</th>
						<th>開始日</th>
						<th>預計下架日</th>
						<th>結束日</th>
						<th>狀態</th>
						<th>操作</th>
						<th>修改</th>
					</thead>

					<c:forEach var="activityVO" items="${list}">
						<c:if test="${activityVO.act_Status == 1}">
							<%@ include file="Content.file"%>
						</c:if>
					</c:forEach>


				</table>
			</div>

		</div>


	</div>
<c:if test="${openModal!=null}">

<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
				
			<div class="modal-header">
                <h5 class="modal-title" id="myModalLabel">查詢結果</h5>
            </div>
			
			<div class="modal-body">
			<div class="container-fluid">
			
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="listActs_ByCompositeQuery.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
                <button type="button" class="btn btn-primary">確定</button>
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#exampleModalLong").modal({show: true});
        </script>
 </c:if>



</body>
</html>