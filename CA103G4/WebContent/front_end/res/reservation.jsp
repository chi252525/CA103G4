<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>





  

  <%@ include file="/front_end/PostHeader.jsp" %>
  	

  
    <div class="container" >
        <div class=" row centered-form" style="padding-top: 100px">
            <div class="panel panel-default background" style="margin: auto">
                <div style="padding: 25px">
                    <form>
                      <div style="background-color:	#F08080;border-radius: 5px">
                        <div style="padding: 9px;text-align:center;height:60px;line-height:30px">
                        <label><h3><b>訂位系統</b></h3></label>
                        </div>
                      </div>
                        &nbsp; &nbsp;&nbsp;
                        
                      <div class="form-row">
                        <div class="form-group col-md-6">
                              <label for="inputCity">縣市</label>
                              <select id="city-list" onchange="changeCity(this.selectedIndex)" class="form-control">
                                
                              </select>
                        </div>
                        <div class="form-group col-md-6">
                              <label for="inputBranch">分店</label>
                              <select id="branch-list" class="form-control">
                                
                          
                              </select>
                        </div>
                      </div>
                      
                      <div class="form-row">
                        <div class="form-group col-md-8">
                            <label for="inputAddress">日期</label>
                            <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St">
                        </div>
                        <div class="form-group col-md-4">
                              <label for="inputBranch">人數</label>
                              <select id="inputState" class="form-control">
                                <option selected>請選擇...</option>
                                <option>1人</option>
                                <option>2人</option>
                                <option>3人</option>
                                <option>4人</option>
                                <option>5人</option>
                                <option>6人</option>
                                <option>7人</option>
                                <option>8人</option>
                                <option>9人</option>
                                <option>10人</option>
                              </select>
                        </div>
                      </div>
                      
                     
                     <div class="form-group ">
                        <div style="text-align:center;padding-top: 20px">
                         <label for="inputTime">選擇時段</label>
                        </div >
                            <div class="btn-group" role="group" style="padding: 0px" id="timebg-list" onchange="changeTimebg(this.selectedIndex)">
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn1" value="">10:30</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                   <button type="button" class="btn btn2" value="">12:00</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn3" value="">13:30</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn4" value="">15:00</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn5" value="">16:30</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn6" value="">18:00</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn7" value="">19:30</button>
                                  </div>
                                  <div class="btn-group" role="group">
                                    <button type="button" class="btn btn8" value="">21:00</button>
                                  </div>
                            </div>
                        </div>
                   
                      
                      
                      <div class="form-group">
                               <div style="text-align:center;padding-top: 20px">
                                    <label for="inputAddress" id="timefn-list">用餐結束時間</label>
                                    <input type="text" style="text-align:center" class="form-control timefn" placeholder="請先選擇起始時段" disabled>
                                    
                                    <input type="text" style="text-align:center;display: none" class="form-control timefn" id="timefn1" placeholder="12:00" disabled>
                                    <input type="text" style="text-align:center;display: none" class="form-control timefn" id="timefn2" placeholder="13:30" disabled>
                                    <input type="text" style="text-align:center;display: none" class="form-control timefn" id="timefn3" placeholder="15:00" disabled>
                                    <input type="text" style="text-align:center;display: none" class="form-control timefn" id="timefn4" placeholder="16:30"  disabled>
                                    <input type="text" style="text-align:center;display: none" class="form-control timefn" id="timefn5" placeholder="18:00"  disabled>
                                    <input type="text" style="text-align:center;display: none" class="form-control timefn" id="timefn6" placeholder="19:30"  disabled>
                                    <input type="text" style="text-align:center;display: none" class="form-control timefn" id="timefn7" placeholder="21:00"  disabled>
                                    <input type="text" style="text-align:center;display: none" class="form-control timefn" id="timefn8" placeholder="22:30"  disabled>
                                   
                               </div>
                            
                      </div> <div style="text-align: right"><b>#1.切記用餐時間為90分鐘喔!</b></div>
                      
                      
                      
                      <div style="padding-top: 50px;padding-left: 30px" >
                          <b>選擇座位 &nbsp; &#8595;</b>  
                      </div>
                      &nbsp;
                      <div style="width: 100px;height: 100px;padding-left: 30px">
                      <img src="/CA103G4/front_end/img/cutie.png" width="180%" class="cutie">
                      </div>
                       
                      <div style="text-align:right;">
                      <button type="submit" class="btn btn-primary">確認送出</button>
                      </div>
                      
                  </form>     
                </div> 
            </div>
        </div>
    </div>
       
  
  
  
  <script type="text/javascript" bb>
//city-branch      
            var cities = ['台北','新北','桃園','台中','高雄'];
            var citySelect=document.getElementById("city-list");
			var inner="";
			for(var i=0;i<cities.length;i++){
				inner=inner+'<option value=i>'+cities[i]+'</option>';
			}
			citySelect.innerHTML=inner;
      
            var branches=new Array();
            branches[0]=['中山店','信義店','仁愛店'];
            branches[1]=['板橋店','淡水店','蘆洲店'];
            branches[2]=['中壢店','平鎮店','桃園店'];
            branches[3]=['西屯店','北屯店','南屯店'];
            branches[4]=['愛河店','美麗華店','墾丁店'];
            function changeCity(index){
				var Brinner="";
				for(var i=0;i<branches[index].length;i++){
					Brinner=Brinner+'<option value=i>'+branches[index][i]+'</option>';
				}
				var branchSelect=document.getElementById("branch-list");
				branchSelect.innerHTML=Brinner;
			}
			changeCity(document.getElementById("city-list").selectedIndex);
  </script> 
   <script aa>   
//btn-time    
       $(function(){
        	 $(".btn1").click(function(){ 
            	 aa();
            	 
                 $("#timefn1").show();
             });
             $(".btn2").click(function(){ 
            	 aa();
   
                 $("#timefn2").show();
             });
             $(".btn3").click(function(){ 
            	 aa();
                 $("#timefn3").show();
             });
             $(".btn4").click(function(){ 
            	 aa();
                 $("#timefn4").show();
             });
             $(".btn5").click(function(){ 
            	 aa();
                 $("#timefn5").show();
             });
             $(".btn6").click(function(){ 
            	 aa();
                 $("#timefn6").show();
             });
             $(".btn7").click(function(){ 
            	 aa();
                 $("#timefn7").show();
             });
             $(".btn8").click(function(){ 
            	 aa();
                 $("#timefn8").show();
             });
         
             
             
             function aa(){
            	 for(var i = 0;i<9;i++){
            	 	$(".timefn")[i].style.display="none";

            	 }
             }
        });
     
           
      
  </script>
  
  
 
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/res.css"> 
 <%@ include file="/front_end/PostFooter.jsp" %> 
