<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.storedrecord.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	List<StoredrecordVO> list = (List<StoredrecordVO>) session.getAttribute("list");
	System.out.println("strlist=" + list);
	session.setAttribute("strlist", list);
%>
<jsp:include page="/back_end/HeadquarterHeader.jsp" flush="true" />
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- font aewsome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="theme.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/v4-shims.css">

    <!-- Bootsraps-->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!-- datepicker-->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />

    <!-- My css for transaction page-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/storedrecord/css/transaction_back_end.css">

    <style>
    </style>
</head>

<body class="shadow-lg w-100" >
    <form method="post" action="storedrecord.do">
        <input type="hidden" name="location" value="backEnd">
        <!--本網頁路徑提示 -->
        <div id="div_shadow" class="py-5">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <a id="a1" href="<%=request.getContextPath() %>/back_end/storedrecord/transaction_mang.jsp""><h1
								class=" d-flex justify-content-start" style="font-weight: bolder;">儲值管理</h1></a>
                    </div>
                </div>
            </div>
        </div>
        <jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
        <div class="py-1" style="">
            <div class="container">
                <div class="row ">
                    <div id="div1" class="col-md-12 d-flex" style="margin-top: 10px">
                        <select class="custom-select align-items-center" name="mem_No" style="width: 30%;">
                            <option selected>會員編號
                                <c:forEach var="memVO" items="${memSvc.all}">
                            <option value="${memVO.mem_No}">${memVO.mem_No}
                                </c:forEach>
                        </select><input type="hidden" name="action" value="findByMem_no">
                        <button class="btn btn-sm align-items-center" style="height: 35px; width: 35px; background-color: antiquewhite;">
                            <i class="fas fa-search" style="font-size: 20px; color: grey"></i>
                        </button>
                        <!-- data picker -->
                        <!--                         <div class="container"> -->
                        <!--                             <div class="row"> -->
                        <!--                                 <div class="col-sm-6"> -->
                        <!--                                     <div class="form-group"> -->
                        <!--                                         <div class="input-group date" id="datetimepicker2" data-target-input="nearest"> -->
                        <!--                                             <input name ="time" type="text" class="form-control datetimepicker-input" data-target="#datetimepicker2"> -->

                        <!--                                             <div class="input-group-append" data-target="#datetimepicker2" data-toggle="datetimepicker"> -->
                        <!--                                                 <div class="input-group-text"> -->
                        <!--                                                     <i class="fa fa-calendar"></i> -->
                        <!--                                                 </div> -->
                        <!--                                             </div> -->
                        <!--                                         </div> -->
                        <!--                                     </div> -->
                        <!--                                 </div> -->
                        <!--                             </div> -->
                        <!--                         </div> -->
                        
                        <form method="post" action="<%=request.getContextPath()%>/front_end/storedrecord/storedrecord.do">
                        <div class="container">
                            <div class="col-sm-12 col-md-6">
                                <div class="form-group">
                                    <div class="input-group date" id="datetimepicker11" data-target-input="nearest">
                                        <input name="monthAndYear"type="text" class="form-control datetimepicker-input" data-target="#datetimepicker11"/>
                                        <div class="input-group-append" data-target="#datetimepicker11" data-toggle="datetimepicker">
                                            <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                        </div>
                                        <button class="btn btn-light" type=submit style=;>顯示</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type=hidden name=action value=findByMon_Year>
                        <input type=hidden name=location value=backEnd>
                        </form>
                    </div>
                </div>
            </div>
            <div class="shadow p-2">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12" style="">
                            <table class="table datatable">
                                <thead>
                                    <tr>
                                        <th>#儲值流水單號</th>
                                        <th>會員編號</th>
                                        <th>儲值日期</th>
                                        <th>儲值竹幣</th>
<!--                                         <th>回饋竹幣</th> -->
                                        <th>儲值狀態</th>
                                    </tr>
                                </thead>
                                <%@ include file="pages/page1.file"%>
                                <tbody>
                                    <c:forEach var="StoredrecordVO" items="${strlist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                                        <tr>
                                            <td>${StoredrecordVO.stor_No}</td>
                                            <td>${StoredrecordVO.mem_No}</td>
                                            <td>
                                                <fmt:formatDate value="${StoredrecordVO.stor_Date}" pattern="yyyy-MM-dd HH:mm:ss" />
                                            </td>
                                            <td>${StoredrecordVO.stor_Point}</td>
<%--                                             <td>${StoredrecordVO.drew_Point}</td> --%>
                                            <c:choose>
                                                <c:when test="${StoredrecordVO.stor_Status==1}">
                                                    <td>成功</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>失敗</td>
                                                </c:otherwise>
                                            </c:choose>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <%@ include file="pages/page2.file"%>
                        </div>
                    </div>
                </div>
            </div>

            <jsp:include page="/back_end/HeadquarterFooter.jsp" flush="true" />

            <script>
                <!--Timestampicker-->
//                 $(function() {
//                     $('#datetimepicker2').datetimepicker({
//                         locale: 'zh'
//                     });
//                 });

                $('#pageSelect').change(function() {
                    $('#pageForm').submit();
                })

                //datepicker
                $(function() {
                    $('#datetimepicker11').datetimepicker({
                        viewMode: 'years',
                        format: 'MM/YYYY'
                    });
                });

            </script>

<!--             <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script> -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </form>
</body>

</html>
