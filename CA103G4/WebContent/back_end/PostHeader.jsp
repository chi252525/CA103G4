<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    <!--     Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <script>
    
     //left bar change
        $(function(){
            $("#btn1").click(function(){
                $("#first-page").hide();
                $("#second-page").fadeToggle(1500);
            });
            
            $(".button").click(function(){
                $("#first-page").fadeToggle(1500);
                $("#second-page").hide();    
            });
            
            $("#btn2").click(function(){
                $("#first-page").hide();
                $("#second-page2").fadeToggle(1500);
            });
            
            $(".button2").click(function(){
                $("#first-page").fadeToggle(1500);
                $("#second-page2").hide();
            });
            
            $("#btn3").click(function(){
                $("#first-page").hide();
                $("#second-page3").fadeToggle(1500);
            });
            
            $(".button3").click(function(){
                $("#first-page").fadeToggle(1500);
                $("#second-page3").hide();
            });
        });
    
         
   //right change      
         $(function(){
        	 $("#res1").click(function(e){ 
            	 aa();
            	 
                 $("#res1end").fadeToggle(500);
             });
             $("#res2").click(function(e){ 
            	 aa();
   
                 $("#res2end").fadeToggle(500);
             });
             $("#res3").click(function(e){ 
            	 aa();
                 $("#res3end").fadeToggle(500);
             });
         
             
             
             function aa(){
            	 for(var i = 0;i<3;i++){
            	 	$(".remove")[i].style.display="none";

            	 }
             }
        });
   
   //seat
   		 $(function(){
            $(".seat").click(function(e){
            e.target.style.backgroundColor="red";
            })
            
         });
         $(function(){
             $(".refresh").click(function(){
              $(".seat").style.backgroundColor="white";
            })
            
         });
        
        
    </script> 
    
    <style>
        @font-face {
            font-family: 'Senty Golden Bell';
            src: url(/CA103G4/back_end/fonts/SentyGoldenBell.woff2) format('woff2'),
                url(/CA103G4/back_end/fonts/SentyGoldenBell.woff) format('woff');
            font-weight: normal;
            font-style: normal;
        }
        

        @font-face {
            font-family: 'SentyWen';
            src: url(/CA103G4/back_end/fonts/SentyWen.woff2) format('woff2'),
                url(/CA103G4/back_end/fonts/SentyWen.woff) format('woff');
            font-weight: normal;
            font-style: normal;
        }

    </style> 
    
    </head>
<body>
    <div class="container-fluid list-group-flush">
        <div class="row-fluid ">
        
<!--      col-12 col-md-3  -->
       <div class="col-12 col-md-3  setting">
            <div><img src="/CA103G4/back_end/img/Image_5d63021.gif" width="60%" class="rounded mx-auto d-block"></div>
            <div><h1 id="title">
                <b>竹風堂</b><img src="/CA103G4/back_end/img/baboo.png" width="15%">
            </h1></div>
            &nbsp;

<!--   first page       -->
            
            <div class="list-group" id="first-page" >
                <a href="#" class="list-group-item list-group-item-action" style="background-color: #FAECD1" ><img src="/CA103G4/back_end/img/pen_.png" width="17%">&nbsp;訂單管理</a>
                
                <a href="#" id="btn1" class="list-group-item list-group-item-action" style="background-color: #FAECD1"><img src="/CA103G4/back_end/img/table.png" width="17%">&nbsp;訂位管理</a>
                
                <a href="#" class="list-group-item list-group-item-action" style="background-color: #FAECD1"><img src="/CA103G4/back_end/img/deliver.png" width="17%">&nbsp;外送管理</a>
                
                <a href="#" class="list-group-item list-group-item-action" style="background-color: #FAECD1"><img src="/CA103G4/back_end/img/1631-steaming-bowl.png" width="17%">&nbsp;出餐管理</a>
                
                <a href="#" id="btn3" class="list-group-item list-group-item-action" style="background-color: #FAECD1"><img src="/CA103G4/back_end/img/employee.png" width="18%">&nbsp;員工管理</a>
                
                <a href="#" class="list-group-item list-group-item-action" style="background-color: #FAECD1"><img src="/CA103G4/back_end/img/chat.png" width="17%">&nbsp;即時線上客服</a>
                
                <a href="#" class="list-group-item list-group-item-action" style="background-color: #FAECD1"><img src="/CA103G4/back_end/img/a2853ec9dc4feaaaee66e74ea46a78e2.png" width="12%">&nbsp;&nbsp;食材顯示設定</a>
                
                <a href="#"  id="btn2" class="list-group-item list-group-item-action" style="background-color: #FAECD1"><img src="/CA103G4/back_end/img/00.png" width="18%">&nbsp;收營結帳管理</a>
            </div>
            
<!--     second page    -->
    		<div>
	            <div class="list-group" style="display:none" id="second-page">
	                <a href="#" class="list-group-item list-group-item-action button" style="background-color: #FAECD1">上一頁</a>
	                 
	                <a href="research_page.jsp" class="list-group-item list-group-item-action" style="background-color: #FAECD1" id="res1"><b>&#187;</b>&nbsp;線上預訂設定</a>
	                
	                <a href="listAllRes.jsp" class="list-group-item list-group-item-action" style="background-color: #FAECD1" id="res2"><b>&#187;</b>&nbsp;訂位記錄管理</a>
	                
	                <a href="#" class="list-group-item list-group-item-action" style="background-color: #FAECD1" id="res3"><b>&#187;</b>&nbsp;現場座位管理</a>
	                
	                <a href="#" class="list-group-item list-group-item-action" style="background-color: #FAECD1" ><b>&#187;</b>&nbsp;候位顧客資料管理</a>
	                
	            </div>
            
	            <div class="list-group" style="display:none" id="second-page2">
	                 <a href="#" class="list-group-item list-group-item-action button2" style="background-color: #FAECD1" >上一頁</a>
	                 
	                <a href="#" class="list-group-item list-group-item-action" style="background-color: #FAECD1" ><b>&#187;</b>&nbsp;結帳</a>
	                
	                <a href="#" class="list-group-item list-group-item-action" style="background-color: #FAECD1"><b>&#187;</b>&nbsp;外帶自取身份確認</a>
	                
	            </div>
	            
	            <div class="list-group" style="display:none" id="second-page3">
	                 <a href="#" class="list-group-item list-group-item-action button3" style="background-color: #FAECD1" >上一頁</a>
	                 
	                <a href="#" class="list-group-item list-group-item-action" style="background-color: #FAECD1" ><b>&#187;</b>&nbsp;員工資料權限設定</a>
	                
	                <a href="#" class="list-group-item list-group-item-action" style="background-color: #FAECD1"><b>&#187;</b>&nbsp;員工權限修改</a>
	                
	            </div>
    	  </div>  
    	  
         
       </div>
<!--    col-12 col-md-3 end-->
        

          <div class="col-12 col-md-9 setting1">
<!--登出列-->
                  	<nav class="navbar navbar-expand-lg navbar-dark" id="log-out" style="height:70px;padding:15px;border-radius: 10px;" >
                     
                       <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                       </button>

                       <div><b>登出&nbsp;</b><b class="fas fa-sign-out-alt" style="margin-right:auto;" ></b></div>
                       <div style="margin-left:auto;"><b>Hello~</b></div>&nbsp;
                       <i class="fas fa-chevron-left"></i><div><span><b>店長</b></span></div><i class="fas fa-chevron-right"></i>
                       <div><span><b>呂竹</b></span></div>&nbsp;
                       <div ><img src="/CA103G4/back_end/img/drew.jpg" class="drew"></div>
                     
                    </nav>
<!--   旋轉拉麵  -->
                    <a class="ramei" ><img src="/CA103G4/back_end/img/ramei.png" width="125" height="125"></a>
<!--         收尋欄     -->
                &nbsp;              
                <form action="" style="padding-top: 20px;font-family: 'SentyWen'" >
                    <div class="input-group">
                        <input type="text"  placeholder="請輸入.." class="form-control" >
                        <div class="input-group-append">
                            <button class="btn btn-info"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                </form>
                
               &nbsp;
   
