<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
    <head>
         
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    <!--     Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </head>
<body>  
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
                    <form>
                      <div style="background-color:#c48888;border-radius: 5px">
                        <div style="padding: 9px;text-align:center;height:60px;line-height:60px;color:#000000">
                        <label><h3><i class="fab fa-angellist"></i>&nbsp;<b>訂位成功</b>&nbsp;<i class="fab fa-angellist"></i></h3></label>
                        </div>
                      </div>
                        &nbsp; &nbsp;&nbsp;
                        
                      <div class="form-row">
                        <div class="form-group col-md-6">
                              <label for="inputCity">縣市</label>
                             <input type="text" style="text-align:center" class="form-control timefn" placeholder="請先選擇起始時段" disabled>
                        </div>
                        <div class="form-group col-md-6">
                              <label for="inputBranch">分店</label>
                              <input type="text" style="text-align:center" class="form-control timefn" placeholder="請先選擇起始時段" disabled>
                        </div>
                      </div>
                      
                      <div class="form-row">
                        <div class="form-group col-md-8">
                            <label for="inputAddress">日期</label>
                           <input type="text" style="text-align:center" class="form-control timefn" placeholder="請先選擇起始時段" disabled>
                        </div>
                        <div class="form-group col-md-4">
                              <label for="inputBranch">人數</label>
                             <input type="text" style="text-align:center" class="form-control timefn" placeholder="請先選擇起始時段" disabled>
                        </div>
                      </div>
                      
                     
                     <div class="form-group ">
                        <div style="text-align:center;padding-top: 20px">
                         <label for="inputTime">選擇時段</label>
                        </div >
                           <input type="text" style="text-align:center" class="form-control timefn" placeholder="請先選擇起始時段" disabled>

                        </div>
                   
                      
                      
                      <div class="form-group">
                               <div style="text-align:center;padding-top: 20px">
                                    <label for="inputAddress" id="timefn-list">用餐結束時間</label>
                                    <input type="text" style="text-align:center" class="form-control timefn" placeholder="請先選擇起始時段" disabled>
                                    
                                
                                   
                               </div>
                            
                      </div> 
                      
                      <div style="text-align: left"><b>小叮嚀!&nbsp;<i class="fas fa-bullhorn"></i></b></div>
                      &nbsp;
                      <div style="text-align: left"><b>#1.切記用餐時間為90分鐘喔!</b></div>
                      &nbsp;
                      <div style="text-align: left"><b>#2.記得要準時到喔，要不然位子會被取消!</b></div>
                      &nbsp;
                      <div style="text-align: left"><b>#3.到店裡，只須跟櫃台人員報上電話跟大名就會有專門服務人員為您帶位。</b></div>
                      
                      
                      
                      
                     
                      &nbsp;
                      &nbsp;
                       
                      <div style="text-align:right;">
                      <button type="submit" class="btn btn-primary">回首頁<i class="fas fa-check"></i></button>
                      </div>
                      
                  </form>     
                </div> 
            </div>
        </div>
    </div>  
    
 <%@ include file="/front_end/footer.jsp" %>     
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <!--    css-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/reservation/css/last.css">
    <!--  icon   -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    </body>
</html>    