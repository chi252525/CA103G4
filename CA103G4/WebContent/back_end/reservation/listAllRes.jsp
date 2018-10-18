<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reservation.model.*"%>





<%@ include file="/back_end/PostHeader.jsp" %>


     	  <div >
     			<b>所有訂位紀錄 - listAllRes.jsp</b>
     			
     			 <div class="form-row dateCss">
                        <div class="form-group col-md-5">
                            <label for="inputAddress">日期</label>
                            <input type="text" class="form-control date" placeholder="click me!" id="f_date1" name="date">
                        </div>
                        <div class="form-group col-md-5 zone">
                              <label for="inputCity">時段</label>
                              <select id="zone" class="form-control">
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
                        <div class="form-group col-md-2 btnDiv">
                        <button type="submit" class="btn searchRes" onclick="zeroPush();">確認送出</button>
                        </div>
                  </div>
                  
     			<hr>
     	  </div>
     		
              <div class="remove"  id="res2end">
                    <div class="col-md-12 text-center">
                            <div class="container" >
                                <table class="table table-striped">
	                                <thead id="mainName">
	                                    
												              
	                                    
		                            </thead>
	                                <tbody id="content"> 

	                                  
	                   
	                                </tbody>
                              
                                          
                            </table>

                     </div>
                </div>
             </div>

<link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/reservation/css/listAllRes.css"> 
<%@ include file="/back_end/PostFooter.jsp" %>	
<script>
	var date2;
	var zone2;
	$(function(){
		$('.date').change(function(){
			date2 = $(this).val();
			console.log(date2);
		});
	});	
	$(function(){
		$('#zone').change(function(){
			zone2 = $('#zone').val();
			console.log(zone2);
		});
	});
	function zeroPush(){
	    $.ajax({
	  		url: "<%=request.getContextPath()%>/res.do", 
	  		data:{"action":"queryRes", "date2":date2, "zone2":zone2},
	  		success: function(result){
	  			console.log("===pass queryRes===");
	  			console.log(result);
	  			$("#mainName").empty();
  				$("#mainName").append("<tr><th>訂位流水編號</th><th>會員名稱</th><th>手機號碼</th><th>位子編號</th><th>訂位紀錄成立日期</th><th>用餐起始時間</th><th>用餐結束時間</th><th>用餐人數</th><th>狀態</th><th>修改狀態</th></tr>")
	  			var res_timebgList = JSON.parse(result);
	  			for(var i=0; i < res_timebgList.length ;i++){
	  				
	  				var	res_timebg = eval("("+res_timebgList[i]+")");
	  				console.log(res_timebg.deskId);


			        var status0 = "訂位已取消";
			        var status1 = "訂位已確認(未到)";
			        var status2 = "已報到";
					var selected ="請選擇";
			        
					var trString="";
	  				trString += "<tr>";

	  				trString += "<td>";
 				    trString += res_timebg.Res_no;
 				    trString += "</td>";
 				    
 				    trString += "<td>";
				    trString += res_timebg.Mem_Name;
				    trString += "</td>";
				    
				    trString += "<td>";
 				    trString += res_timebg.Mem_Phone;
 				    trString += "</td>";
 				    
 				    trString += "<td>";
				    trString += res_timebg.deskId;
				    trString += "</td>";
				    
 				    trString += "<td>";
				    trString += res_timebg.Res_submit;
				    trString += "</td>";
				    
 				    trString += "<td>";
				    trString += res_timebg.Res_timebg;
				    trString += "</td>";
				    
 				    trString += "<td>";
				    trString += res_timebg.Res_timefn;
				    trString += "</td>";
				    
 				    trString += "<td>";
				    trString += res_timebg.Res_people;
				    trString += "</td>";
				    
 				    trString += "<td class='test'>";
 				   if(res_timebg.Res_status == 0){
 					  trString += status0;  
 					
 				   }else if (res_timebg.Res_status == 1){
 					  trString += status1; 
 					 
 				   }else{
 					  trString += status2; 
 	
 				   }				    
				    trString += "</td>";
				    
 				    trString += "<td>";
 				    

				    trString += "<select class='aa'>";
				    trString += "<option value='0'>" + status0 + "</option>";
				    trString += "<option value='2'>" + status2 + "</option>";
				    trString += "</select>";
				    
				    trString += "</td>";
	  				
 				    trString += "</tr>";
 				    $("#content").append(trString);
			    	 $(".aa").change(function(){ 
			    		 
			        	 console.log($(this).val()); 
	
			        	 $.ajax({
			     	  		url: "<%=request.getContextPath()%>/res.do", 
			     	  		data:{"action":"updateStatus", "res_no":res_timebg.Res_no, "status":$(this).val()},
			     	  		success: function(result){
			     	  			console.log("pass ajax");
			     	  		   if(result === 'success')	{
// 			     	  			   $(this).parent().prev().text($(this).val());
									if($(".aa").val() == 0){
// 										$(".aa").prev($('.test').text(status0));
										$(".aa").parent().prev().text(status0);
									}else if($(".aa").val() == 2){
// 										$(".aa").prev($('.test').text(status2));
										$(".aa").parent().prev().text(status2);
									}
			     	  			
			     	  		   }
			     	  		}
			        	 });
			         });
	  		    }
			  }
	  	});
	}
	
	
	

</script>	

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
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
	