<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.couponhistory.model.*"%>

<%-- <jsp:useBean id="couponhistorySvc" scope="page" class="com.couponhistory.model.CouponhistoryService" /> --%>

<%-- 存取會員編號 --%>
<%-- <% --%>
<%-- mem = (String)session.getAttribute("memNo"); --%>
<%-- %> --%>

<%
CouponhistoryService couponhistorySvc = new CouponhistoryService();
List<CouponhistoryVO> list = couponhistorySvc.getByMem("M000001");
pageContext.setAttribute("ByName",list);
%>

<jsp:useBean id="couponSvc" scope="page" class="com.coupon.model.CouponService" />

<jsp:useBean id="coucatSvc" scope="page" class="com.coucat.model.CoucatService" />

<% 
   java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String formatDate = df.format(new java.util.Date());
%>

<jsp:useBean id="now" scope="page" class="java.util.Date" /> 


<!DOCTYPE html>
<html>

<jsp:include page="/front_end/header.jsp" flush="true" />
<img src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg" width="100%" height="" alt="">
<head>
<meta charset="UTF-8">
<!-- font aewsome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.0/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/v4-shims.css">
<!-- Bootsraps-->
<script src="https://code.jquery.com/jquery-3.2.1.min.js "></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
<!-- datatable-->

<!--credit card-->
<script src="js/card.js"></script>
<link rel="stylesheet" href="css/card.css">
<!-- address choosing -->
<script src="<%=request.getContextPath()%>/front_end/member/js/selectaddress.js"></script>

<title>listbymem</title>

<style type="text/css">
 .all{
    margin-top: 10%;
  }

  .amos{
    width: 1200px;
    background-color: #000;
    margin: auto; 
    margin-top: 5%;
  }

  .item{
    margin: 5px;
    width: 1190px;
    height: 400px;
    background-color: #880000;
  }

   .tet{
    margin-top: 5%;
  }

  .pimg{
    margin: 5px;
    width: 360px;
    height: 390px;
    background-color: #FFDDAA;
  }

  .textc{
    margin: 5px;
    width: 810px;
    height: 390px;
    background-color: #FFDDAA;
  }


</style>

</head>
<body class="shadow-lg w-100" background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png" width="100%">

<div class="container tet">
  <h1>優惠券持有紀錄</h1>
</div>



<div class="all">
	<c:if test="${ByName != null}">
	<c:forEach var="couponhistoryVO" items="${ByName}">
    <div class="d-flex flex-wrap amos">
    <div class="d-flex flex-wrap item">
      <div class="pimg">
      
      <img src ="<%=request.getContextPath()%>/DBGifReader4?coucat_No=${coucatSvc.getOneCoucat(couponSvc.getOneCoupon(couponhistoryVO.coup_sn).coucat_No).coucat_No}" style="width: 360px; height: 390px;">
          
      </div>
      
      <div class="textc">
      		<br>
         <h1>
         	優惠券名稱: ${coucatSvc.getOneCoucat(couponSvc.getOneCoupon(couponhistoryVO.coup_sn).coucat_No).coucat_Name}
         </h1>
         
         <h7>
         
         <c:if test="${coucatSvc.getOneCoucat(couponSvc.getOneCoupon(couponhistoryVO.coup_sn).coucat_No).coucat_Invalid < now or couponhistoryVO.coup_state != 0}">
         	 <不可使用>
         </c:if>
         
         <c:if test="${coucatSvc.getOneCoucat(couponSvc.getOneCoupon(couponhistoryVO.coup_sn).coucat_No).coucat_Invalid > now and couponhistoryVO.coup_state == 0}">
        	 <可使用>
         </c:if>
         
         </h7>
         
         <h2>
         	
        	<p>優惠券序號: ${couponhistoryVO.coup_sn}</p>
        	
        	
        	<c:if test="${(\"CC1\").equals(coucatSvc.getOneCoucat(couponSvc.getOneCoupon(couponhistoryVO.coup_sn).coucat_No).coucat_Cata)}">
      			<p>優惠券類型: 訂單滿額折扣</p>
      			<p>優惠券折扣:${coucatSvc.getOneCoucat(couponSvc.getOneCoupon(couponhistoryVO.coup_sn).coucat_No).coucat_Discount}</p>
        	</c:if>
        	<c:if test="${(\"CC2\").equals(coucatSvc.getOneCoucat(couponSvc.getOneCoupon(couponhistoryVO.coup_sn).coucat_No).coucat_Cata)}">
      			<p>優惠券類型: 固定金額發放</p>
      			<p>折抵金額:${coucatSvc.getOneCoucat(couponSvc.getOneCoupon(couponhistoryVO.coup_sn).coucat_No).coucat_Value}</p>
        	</c:if>
        	<c:if test="${(\"CC3\").equals(coucatSvc.getOneCoucat(couponSvc.getOneCoupon(couponhistoryVO.coup_sn).coucat_No).coucat_Cata)}">
      			<p>優惠券類型: 贈送定額竹幣</p>
      			<p>發放點數:${coucatSvc.getOneCoucat(couponSvc.getOneCoupon(couponhistoryVO.coup_sn).coucat_No).coucat_Ereep}</p>
        	</c:if>
        	
  			<P>
  			<c:if test="${couponhistoryVO.coup_state == 0}">
      			<p>使用狀態:未使用</p>
        	</c:if>
  			<c:if test="${couponhistoryVO.coup_state == 1}">
      			<p>使用狀態:已使用</p>
        	</c:if>
  			<c:if test="${couponhistoryVO.coup_state == 2}">
      			<p>使用狀態:已過期</p>
        	</c:if>  			
  			</P>

        	<p>
        	到期日:<fmt:formatDate value="${coucatSvc.getOneCoucat(couponSvc.getOneCoupon(couponhistoryVO.coup_sn).coucat_No).coucat_Invalid}" pattern="yyyy-MM-dd"/>
        	<c:if test="${coucatSvc.getOneCoucat(couponSvc.getOneCoupon(couponhistoryVO.coup_sn).coucat_No).coucat_Invalid < now}">過期
        	</c:if>
        	<c:if test="${coucatSvc.getOneCoucat(couponSvc.getOneCoupon(couponhistoryVO.coup_sn).coucat_No).coucat_Invalid > now}">未過期
        	</c:if>
        	</p>

         </h2>
      </div>
    </div>
    </div>
    </c:forEach>
    </c:if>
</div>

<jsp:include page="/front_end/footer.jsp" flush="true" />
</body>

</html>