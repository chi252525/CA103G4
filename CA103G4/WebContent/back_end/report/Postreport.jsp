<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report_msg.model.*"%>
<%@ page import="com.post.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.member.model.*"%>
<jsp:useBean id="cusmealSvc1" scope="page"
	class="com.custommeals.model.CustommealsService" />
<jsp:useBean id="memSvc" scope="page"
	class="com.member.model.MemberService" />
<%
	ReportService rptSvc = new ReportService();
	List<ReportVO> list = rptSvc.getAll();
	session.setAttribute("list", list);
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
<script src="https://code.jquery.com/jquery-3.2.1.min.js "></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<!-- Sweet alert -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.28.1/sweetalert2.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.28.1/sweetalert2.css">

<style>
.test {
	border: solid, 1px;
}
</style>
</head>
<body>

<jsp:include page="/back_end/HeadquarterHeader.jsp" flush="true" />
	<div class="container-fluid">
		<div class="row"></div>
		<div class="col-sm-12 col-12 col-lg-12">
			<h5>貼文檢舉管理</h5>
			<br>
			<div class="container-fluid">
				<div class="row mx-0">
				<%@ include file="pages/page1.file"%>
					<div class="col-md-8 mx-0">
						<form class="form-inline" method="post" action="<%=request.getContextPath()%>/report/reportServlet.do">
							<div class="form-group">
								<select class="form-control" name="rpt_Status">
									<option value='RS0'>未處理</option>
									<option value='RS1'>已處理</option>
								</select>
								
								<input type="hidden" name="rpt_No" value="${reportVO.rpt_No}" />
								<input type="hidden" name="action" value="getReportByStatus" />
							</div>
								
							<button type="submit" class="btn btn-primary"  >查詢</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		
				<script>
			 function getReportByStatus2() {
	                // 				console.log("0000");
	                $.ajax({
	                    type: "post",
	                    url: "<%=request.getContextPath()%>/report/reportServlet.do",
	                    data: {
	                        "action": "getReportByStatus2"
	                    },
	                    dataType: "json",
	                    success: function(result) { //result 為後端送回來的資料名稱
	                        console.log(result);
	                        for (i = 0; i < result.length; i++) {
	                            $("#tbody").append("<tr><td>" + result[i].rpt_No + "</td><td>" + result[i].post_No +
	                                "</td><td>" + result[i].mem_No + "</td><td>" + result[i].rpt_Rsm +
	                                "</td><td>" + result[i].rpt_Status + "</td><td>" + result[i].rpt_Time +
	                                "</td><td><input type=\"button\" class=\"update btn btn-warning btn-sm\" value=\"修改\" style=\"display:none\"/></td>" +
	                                "<td><input type=\"button\" class=\"del btn btn-danger btn-sm\" value=\"刪除\" style=\"display:none\"/></td>");
	                           
	                        }
	                    },
	                    error: function() {
	                        alert("error!")
	                    }
	                })
	            }
			
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
		
		<table class="table table-hover">
			<thead>
				<tr>
					<th>#處理流水號</th>
					<th>檢舉貼文編號</th>
					<th>檢舉會員</th>
					<th>檢舉原因</th>
					<th>處理狀態</th>
					<th>檢舉時間</th>
					<th>通過/不通過</th>
				</tr>
			</thead>
			<c:forEach var="reportVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<div class="col-12">
				<p id="test"></p>
				
				
				</div>
				<tbody  id="tbody">
					<tr ${(reportVO.rpt_No==param.rpt_No) ? 'bgcolor=#CCCCFF':''}>
						<td>${reportVO.rpt_No}</td>
						<td>
						<a href="<%=request.getContextPath()%>/post/postServlet.do?post_No=${reportVO.post_No}&action=getOne_For_Display"/>${reportVO.post_No}</td>
						<td>${reportVO.mem_No}${memSvc.getOne_Member(reportVO.mem_No).mem_Name}</td>


						<c:if test='${reportVO.rpt_Rsm.equals("RR1")}'>
							<td>貼文內容不實</td>
						</c:if>
						<c:if test='${reportVO.rpt_Rsm.equals("RR2")}'>
							<td>廣告內容</td>
						</c:if>
						<c:if test='${reportVO.rpt_Rsm.equals("RR3")}'>
							<td>無關餐點分享</td>
						</c:if>
						<c:if test='${reportVO.rpt_Rsm.equals("RR4")}'>
							<td>就是想檢舉</td>
						</c:if>

						<td>${(reportVO.rpt_Status=='RS0')?'未處理':'已處理'}</td>
						<td><fmt:formatDate value="${reportVO.rpt_Time}"
								pattern="MM月dd日 HH:mm:ss" /></td>
						<td>
						<!-- 一顆postSvc -->
						<jsp:useBean id="postSvc" scope="page"	class="com.post.model.PostService" />


						<!--通過的btn -->
							<div class="btn-group" role="group" aria-label="Basic example">

								<form method="post"
									action="<%=request.getContextPath()%>/post/postServlet.do" id="updateStatus${reportVO.rpt_No}">
									<button type="submit" class="btn btn-success" ${(reportVO.rpt_Status=='RS1')?'disabled':'' }><span class="lnr lnr-checkmark-circle"></span></button>
										<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    				 <input type="hidden" name="whichPage"	value="<%=whichPage%>">  
										<input type="hidden" name="rpt_No" value="${reportVO.rpt_No}" />
										<input type="hidden" name="post_No" value="${postSvc.getOne_Post(reportVO.post_No).post_No}" />
										<input type="hidden" name="action" value="updatePostStatus" /> 
							
								</form>
							<script type="text/javascript">
					
								document.querySelector('#updateStatus${reportVO.rpt_No}').addEventListener('submit', function(e) {
									  var form = this;

									  e.preventDefault(); // <--- prevent form from submitting

									  swal({
									      title: "確定隱藏貼文嗎?",
									      text: "此貼文將不可再被看到",
									      icon: "warning",
									      buttons: [
									        '否',
									        '是'
									      ],
									      dangerMode: true,
									    }).then(function(isConfirm) {
									      if (isConfirm) {
									        swal({
									          title: '成功',
									          text: '貼文已經被隱藏',
									          icon: 'success'
									        }).then(function() {
									          form.submit(); // <--- submit form programmatically
									        });
									      } else {
									        swal("Cancelled", "貼文還好好的存在", "error");
									      }
									    })
									});
						
								</script>

								<!-- 不通過的btn -->
								<form method="post"
									action="<%=request.getContextPath()%>/report/reportServlet.do" id="updateRepotStatusOnly${reportVO.rpt_No}">
										<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    				 		<input type="hidden" name="whichPage"	value="<%=whichPage%>">  
										<input type="hidden" name="rpt_No"
											value="${reportVO.rpt_No}" />
										<input type="hidden" name="action" value="updateReportStatus" /> 
									<button type="submit" class="btn btn-dark" ${(reportVO.rpt_Status=='RS1')?'disabled':''}>
									<span
											class="lnr lnr-cross"></span></button>
								</form>
								
								
						
								
								
								
								
								
								
								
									<script type="text/javascript">
					
								document.querySelector('#updateRepotStatusOnly${reportVO.rpt_No}').addEventListener('submit', function(e) {
									  var form = this;
									  e.preventDefault(); // <--- prevent form from submitting

									  swal({
									      title: "確定不隱藏貼文嗎?",
									      text: "此貼文將繼續被看到",
									      icon: "warning",
									      buttons: [
									        '否',
									        '是'
									      ],
									      dangerMode: true,
									    }).then(function(isConfirm) {
									      if (isConfirm) {
									        swal({
									          title: '成功',
									          text: '處理完成',
									          icon: 'success'
									        }).then(function() {
									          form.submit(); // <--- submit form programmatically
									        });
									      } else {
									        swal("Cancelled", "貼文還好好的存在", "error");
									      }
									    })
									});
						
								</script>
								
								

							</div> 
						</td>
					</tr>
				</tbody>
			</c:forEach>

		</table>
<%@ include file="pages/page2.file"%>
	</div>


<jsp:include page="/back_end/HeadquarterFooter.jsp" flush="true" />
</body>
</html>