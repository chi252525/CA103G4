<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    <!--     Latest compiled and minified CSS -->
<!--         <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"> -->
       
    </head>
<body>
   <nav class="navbar hd"></nav>
    <nav class="navbar navbar-expand-lg mainbar">
      
       <div class="title-box">
        <a class="navbar-brand title" href="#">竹風堂</a>
        </div> 
    <!-- 縮起來的3-lines  -->    
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
    <!--  -->   

          <div class="collapse navbar-collapse " id="navbarNavAltMarkup">
            <ul class="navbar-nav content">
            
            
                 
             <li class="nav-item dropdown b">
                 <a class="nav-item nav-link p aboutUs nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-file main-icon" aria-hidden="true"></i><c class="icon-font">關於我們資料編輯</c></a></li>
              &nbsp; &nbsp;
              <li class="nav-item dropdown b">
                 <a class="nav-item nav-link p material nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-coffee main-icon" aria-hidden="true"></i><c class="icon-font">食品項目管理</c>
                 </a>
                     <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                      <a class="dropdown-item" href="#">產品項目管理</a>
                      <a class="dropdown-item" href="#">餐點管理</a>
                    </div>
              </li>
              &nbsp; &nbsp;
              <li class="nav-item dropdown b"> 
                 <a class="nav-item nav-link p member nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user-md main-icon" aria-hidden="true" ></i><c class="icon-font">會員管理</c></a>
                     <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                      <a class="dropdown-item" href="#">留言檢舉管理</a>
                      <a class="dropdown-item" href="#">儲值管理</a>
                    </div>
                 </li>
              &nbsp;&nbsp;
              <li class="nav-item dropdown b">
                 <a class="nav-item nav-link p branch nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-university main-icon" aria-hidden="true"></i><c class="icon-font">分店管理</c></a></li>
              &nbsp;&nbsp;
              <li class="nav-item dropdown b">
                 <a class="nav-item nav-link p activity nav-link dropdown-toggle" href="<%=request.getContextPath()%>/back_end/activity/addCoupon.jsp" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-users main-icon" aria-hidden="true"></i><c class="icon-font">活動管理</c></a>
                     <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                      <a class="dropdown-item" href="#">活動設定</a>
                      <a class="dropdown-item" href="#">優惠卷發放設定</a>
                    </div>
                 </li>
              
            </ul> 
          </div>

          <ul class="nav navbar-nav pull-right" style="padding:8px;">
                <a href="#" class="btn btn-sm  log-in"><i class="fa fa-user" aria-hidden="true"></i>登入</a>
<!--
               &nbsp;
                <a href="#" class="btn btn-sm  register"><i class="fa fa-cog" aria-hidden="true"></i>註冊</a>
-->
          </ul>

    </nav> 
    <nav class="navbar hd"></nav>
<!-- content start--> 
