<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reservation.model.*"%>

<%-- <% --%>
<!-- 	ResService resSvc = new ResService(); -->
<!-- 	List<ResVO> list = resSvc.getAll(); -->
<!-- 	pageContext.setAttribute("list",list); -->
<!-- %> -->



<%@ include file="/back_end/PostHeader.jsp" %>


     	  <div >
     			<b>所有訂位紀錄 - listAllRes.jsp</b>
     			
     			 <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputAddress">日期</label>
                            <input type="text" class="form-control" placeholder="click me!" id="f_date1" name="date">
                        </div>
                        <div class="form-group col-md-6">
                              <label for="inputCity">時段</label>
                              <select id="city-list" class="form-control" onchange="branchPush()">
                                	<option value="10:30" >10:30</option>
                                	<option value="12:00" >12:00</option>
                                	<option value="13:30" >13:30</option>
                                	<option value="15:00" >15:00</option>
                                	<option value="16:30" >16:30</option>
                                	<option value="18:00" >18:00</option>
                                	<option value="19:30" >19:30</option>
                                	<option value="21:00" >21:00</option>                   
                              </select>
                        </div>
                  </div>
                  
     			<hr>
     	  </div>
     		
              <div class="remove"  id="res2end">
                    <div class="col-md-12 text-center">
                            <div class="container" >
                                <table class="table table-striped">
	                                <thead>
	                                    <tr>
	                                        <th>訂位流水編號</th>
	                                        <th>會員名稱</th>
	                                        <th>手機號碼</th>
	                                        <th>位子編號</th>
	                                        <th>訂位紀錄成立日期</th>
	                                        <th>用餐起始時間</th>
	                                        <th>用餐結束時間</th>
	                                        <th>用餐人數</th>
	                                        <th>狀態</th>           
	                                        <th>修改狀態</th>           
	                                                   
	                                    </tr>
	                                </thead>
                                <tbody> 
					<%@ include file="page1.file" %> 
					<c:forEach var="resVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">                       
                                    <tr>    
                	
      					<c:set var="res_timefn">
      							${resVO.res_timefn}
      					</c:set>   
      					<c:set var="res_timebg">
      							${resVO.res_timefn}
      					</c:set>
      					     	
                		
                                        <td>${resVO.res_no}</td>
                                        <td>${resVO.mem_no}</td>
                                        <td>${resVO.dek_no}</td>
                                        <td>${resVO.res_submit}</td>
                                       <td> <% 
				                             java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
				                             String res_timebg = df.format(java.sql.Timestamp.valueOf((String)pageContext.getAttribute("res_timebg")));
				
				                             out.println(res_timebg);
				                          %> </td>
                                        <td> <% 
				                             java.text.DateFormat fd = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
				                             String res_timefn = fd.format(java.sql.Timestamp.valueOf((String)pageContext.getAttribute("res_timefn")));
				
				                             out.println(res_timefn);
				                          %> </td>
                                        <td>${resVO.res_people}</td>
                                        <td>${resVO.res_status}</td>
                                        
                                        <td>
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/res/res.do" style="margin-bottom: 0px;">
										     <input type="submit" value="修改">
										     <input type="hidden" name="res_no"  value="${resVO.res_no}">
										     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
										</td>
				
                                    </tr>
                                  
                   </c:forEach> 
                   
                                </tbody>
                              
                                          
                            </table>
                          <%@ include file="page2.file" %>   
                     </div>
                </div>
             </div>


<%@ include file="/back_end/PostFooter.jsp" %>	
<script>
	function zeroPush(){
	    $.ajax({
	  		url: "<%=request.getContextPath()%>/res.do", 
	  		data:{"action":"queryRes", "date2":date2, "zone2":zone2},
	  		success: function(result){
	  			console.log("===pass queryRes===")
	  			var res_timebgList = JSON.parse(result);
	  			for(var i=0; i < res_timebgList.length ;i++){
	  				var	res_timebg = res_timebgList[i];
	  				console.log(res_timebg.res_no);
	  			}
	  	}});
	}
</script>	
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
	