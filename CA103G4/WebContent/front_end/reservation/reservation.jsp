<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>





  

  <%@ include file="/front_end/header.jsp" %>
 <body background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png "
	width="100%" height="">
<!-- header勿動 -->
	<img src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg"
			width="100%" height="" alt=""> 	

  
    <div class="container" >
        <div class=" row centered-form" style="padding-top: 100px">
            <div class="panel panel-default background" style="margin: auto">
                <div style="padding: 25px">
                    <form action = "<%=request.getContextPath()%>/desk.do" method="post">
                      <div style="background-color:	#F08080;border-radius: 5px">
                        <div style="padding: 9px;text-align:center;height:60px;line-height:30px">
                        <label><h3><b>訂位系統</b></h3></label>
                        </div>
                      </div>
                        &nbsp; &nbsp;&nbsp;
                        
                      <div class="form-row">
                        <div class="form-group col-md-6">
                              <label for="inputCity">縣市</label>
                              <select id="city-list" class="form-control" onchange="branchPush()">
                                	<option value="台北市">台北市</option>
                                	<option value="高雄市">高雄市</option>
                                	<option value="桃園市">桃園市</option>
                                	<option value="台東縣">台東縣</option>
                                	<option value="台中市">台中市</option>                   
                              </select>
                        </div>
                        <div class="form-group col-md-6">
                              <label for="inputBranch">分店</label>
                              <select id="branch-list" class="form-control" name="branch_no">
                                <option selected>請先選擇縣市</option>
                          
                              </select>
                        </div>
                      </div>
                      
                      <div class="form-row">
                        <div class="form-group col-md-8">
                            <label for="inputAddress">日期</label>
                            <input type="text" class="form-control" placeholder="click me!" id="f_date1" name="date">
                        </div>
                        <div class="form-group col-md-4">
                              <label for="inputBranch">人數</label>
                              <select id="inputState" class="form-control" name="res_people">
                                <option value="" selected>請選擇...</option>
                                <option value="1">1人</option>
                                <option value="2">2人</option>
                                <option value="3">3人</option>
                                <option value="4">4人</option>
                                <option value="5">5人</option>
                                <option value="6">6人</option>
                                <option value="7">7人</option>
                                <option value="8">8人</option>
                                <option value="9">9人</option>
                                <option value="10">10人</option>
                              </select>
                        </div>
                      </div>
                      
                     
                     <div class="form-group ">
                        <div style="text-align:center;padding-top: 20px">
                         <label for="inputTime">選擇時段</label>
                        </div >
                            <div class="btn-group" role="group" style="padding: 0px" id="timebg-list" onchange="changeTimebg(this.selectedIndex)">
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn1" value="10:30" >10:30</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                   <button type="button" class="btn btn2" value="12:00"  >12:00</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn3" value="13:30" >13:30</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn4" value="15:00" >15:00</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn5" value="16:30" >16:30</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn6" value="18:00" >18:00</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn7" value="19:30" >19:30</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn8" value="21:00" >21:00</button>
                                  </div>
                                  <input type="hidden" id="bgtime" value="" name="bgtime">
                            </div>
                        </div>
                   
                      
                      
                      <div class="form-group">
                               <div style="text-align:center;padding-top: 20px">
                                    <label for="inputAddress" id="timefn-list">用餐結束時間</label>
                                    <input type="text" style="text-align:center" class="form-control timefn" placeholder="請先選擇起始時段" disabled>
                                    
                                    <input type="text" style="text-align:center;display: none"  class="form-control timefn" id="timefn1" placeholder="12:00" value="12:00" disabled>
                                    <input type="text" style="text-align:center;display: none"  class="form-control timefn" id="timefn2" placeholder="13:30" value="13:30" disabled>
                                    <input type="text" style="text-align:center;display: none"  class="form-control timefn" id="timefn3" placeholder="15:00" value="15:00" disabled>
                                    <input type="text" style="text-align:center;display: none"  class="form-control timefn" id="timefn4" placeholder="16:30" value="16:30" disabled>
                                    <input type="text" style="text-align:center;display: none"  class="form-control timefn" id="timefn5" placeholder="18:00" value="18:00" disabled>
                                    <input type="text" style="text-align:center;display: none"  class="form-control timefn" id="timefn6" placeholder="19:30" value="19:30" disabled>
                                    <input type="text" style="text-align:center;display: none"  class="form-control timefn" id="timefn7" placeholder="21:00" value="21:00" disabled>
                                    <input type="text" style="text-align:center;display: none"  class="form-control timefn" id="timefn8" placeholder="22:30" value="22:30" disabled>
                                   <input type="hidden" id="fntime" value="" name="fntime">
                               </div>
                            
                      </div> <div style="text-align: right"><b>#1.切記用餐時間為90分鐘喔!</b></div>
                      
                      
                      
<!--                       <div style="padding-top: 50px;padding-left: 30px" > -->
<!--                           <b>選擇座位 &nbsp; &#8595;</b>   -->
<!--                       </div> -->
<!--                       &nbsp; -->
                      <div style="width: 600px;height: 100px;padding-top: 60px;">
							<c:if test="${not empty errorMsgs}">
							<font style="color:red;" >請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color:red">${message}</li>
								    </c:forEach>
								</ul>
							</c:if>
                      </div>
                       
                      <div style="text-align:right;">
                      <input type="hidden" name="action" value="addDesk">
                      <input type="hidden" name="mem_no" value="${memVO.mem_No}">
                      <button type="submit" class="btn btn-primary">確認送出</button>
                      </div>
                      
                  </form>     
                </div> 
            </div>
        </div>
    </div>
       
  
  
  
  <script type="text/javascript" bb>
//city-branch      
//             var cities = ['台北','新北','桃園','台中','高雄'];
//             var citySelect=document.getElementById("city-list");
// 			var inner="";
// 			for(var i=0;i<cities.length;i++){
// 				inner=inner+'<option value=' + i + '>'+cities[i]+'</option>';
// 			}
// 			citySelect.innerHTML=inner;
      
//             var branches=new Array();
//             branches[0]=['中山店','信義店','仁愛店'];
//             branches[1]=['板橋店','淡水店','蘆洲店'];
//             branches[2]=['中壢店','平鎮店','桃園店'];
//             branches[3]=['西屯店','北屯店','南屯店'];
//             branches[4]=['愛河店','美麗華店','墾丁店'];
//             function changeCity(index){
// 				var Brinner="";
// 				for(var i=0;i<branches[index].length;i++){
// 					Brinner=Brinner+'<option value=i>'+branches[index][i]+'</option>';
// 				}
// 				var branchSelect=document.getElementById("branch-list");
// 				branchSelect.innerHTML=Brinner;
// 			}
// 			changeCity(document.getElementById("city-list").selectedIndex);
  </script> 
   <script aa>   
//btn-time    
       $(function(){
        	 $(".btn1").click(function(){ 
            	 aa();
            	 
                 $("#timefn1").show();
                 $('#bgtime').val($(this).val());
                 $('#fntime').val($("#timefn1").val());   
             });
             $(".btn2").click(function(){ 
            	 aa();
   
                 $("#timefn2").show();
                 $('#bgtime').val($(this).val());
                 $('#fntime').val($("#timefn2").val());
             });
             $(".btn3").click(function(){ 
            	 aa();
                 $("#timefn3").show();
                 $('#bgtime').val($(this).val());
                 $('#fntime').val($("#timefn3").val());
             });
             $(".btn4").click(function(){ 
            	 aa();
                 $("#timefn4").show();
                 $('#bgtime').val($(this).val());
                 $('#fntime').val($("#timefn4").val());
             });
             $(".btn5").click(function(){ 
            	 aa();
                 $("#timefn5").show();
                 $('#bgtime').val($(this).val());
                 $('#fntime').val($("#timefn5").val());
             });
             $(".btn6").click(function(){ 
            	 aa();
                 $("#timefn6").show();
                 $('#bgtime').val($(this).val());
                 $('#fntime').val($("#timefn6").val());
             });
             $(".btn7").click(function(){ 
            	 aa();
                 $("#timefn7").show();
                 $('#bgtime').val($(this).val());
                 $('#fntime').val($("#timefn7").val());
             });
             $(".btn8").click(function(){ 
            	 aa();
                 $("#timefn8").show();
                 $('#bgtime').val($(this).val());
                 $('#fntime').val($("#timefn8").val());
             });
         
             
             
             function aa(){
            	 for(var i = 0;i<9;i++){
            	 	$(".timefn")[i].style.display="none";

            	 }
             }
        });
     //btn onclick color
		$(function(){
       	 $(".btn").click(function(e){ 
           	aa();
               e.target.style.backgroundColor="#F08080";
               e.target.style.color="aliceblue";
               $('#xxx').val($(this).val());
       	 });
       	 function aa(){
           	 for(var i = 0;i<9;i++){
           	 	$(".btn")[i].style.backgroundColor="#DDDDDD";
           	 	$(".btn")[i].style.color="#F08080";

           	 }
            }
       });  
     
		
		function branchPush(){
			
		    $.ajax({
		  		url: "<%=request.getContextPath()%>/branch.do", 
		  		data:{"action":"cityToBranch", "city": $('#city-list').val()},
		  		success: function(result){
// 		  			console.log(result.split(";"));
// 		  			console.log(result.spit(":"));
					var arr = result.split(":");
					var arr1 = arr[0].split(",");
					var arr2 = arr[1].split(",");
					console.log(arr1,arr2);
					$('#branch-list').empty();
					for(var i = 0; i < arr1.length-1; i++){
						console.log(arr[i]);
						$('#branch-list').append("<option  value = '" + arr1[i] + "'>" + arr2[i] + "</option>");
					}
		  	}});
		}
           
      
  </script>
  
  
 
<link rel="stylesheet" href="/CA103G4/front_end/reservation/css/res.css"> 
 <%@ include file="/front_end/footer.jsp" %> 
 <!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


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
