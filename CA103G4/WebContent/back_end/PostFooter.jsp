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
            src: url(/CA103G4/front_end/fonts/SentyGoldenBell.woff2) format('woff2'),
                url(/CA103G4/front_end/fonts/SentyGoldenBell.woff) format('woff');
            font-weight: normal;
            font-style: normal;
        }
        

        @font-face {
            font-family: 'SentyWen';
            src: url(/CA103G4/front_end/fonts/SentyWen.woff2) format('woff2'),
                url(/CA103G4/front_end/fonts/SentyWen.woff) format('woff');
            font-weight: normal;
            font-style: normal;
        }

    </style> 
    
    </head>
<body>
    <div class="container-fluid list-group-flush">

          <div class="col-12 col-md-9 setting1">
<!--登出列-->
                  	<nav class="navbar navbar-expand-lg navbar-dark" id="log-out" style="height:70px;padding:15px;border-radius: 10px;" >
                     
                       <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                       </button>

                       <div><b>登出&nbsp;</b><b class="fas fa-sign-out-alt" style="margin-right:auto;" ></b></div>
                       <div style="margin-left:auto;"><b>Hello~</b></div>&nbsp;
                       <i class="fas fa-chevron-left"></i><div><span><b>店長</b></span></div><i class="fas fa-chevron-right"></i>
                       <div><span><b>呂竹</b></span></div>&nbsp;
                       <div ><img src="/CA103G4/front_end/img/drew.jpg" class="drew"></div>
                    </nav>
<!--   旋轉拉麵  -->
                    <a class="ramei" ><img src="/CA103G4/front_end/img/ramei.png" width="125" height="125"></a>
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
         </div>
       </div>
    </div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->

<!--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <!--    css-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/css/aside01.css">
    <!--  icon   -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</body>
</html>      
