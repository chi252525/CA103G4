<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/css/headquarter.css">
    <!--     Latest compiled and minified CSS -->
<!--         <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"> -->
       
    </head>
<body>
   <nav class="navbar hd"></nav>
    <nav class="navbar navbar-expand-lg mainbar">
      
       <div class="title-box">
        <a class="navbar-brand title" href="<%=request.getContextPath()%>/back_end/employee/empLogin_HQ.jsp">竹風堂</a>
        </div> 
    <!-- 縮起來的3-lines  -->    
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
    <!--  -->   

          <div class="collapse navbar-collapse " id="navbarNavAltMarkup">
            <ul class="navbar-nav content">
            
            
                 
             <li class="nav-item dropdown b">
                 <a id="xxx" class="nav-item nav-link p aboutUs nav-link dropdown-toggle" href="" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-file main-icon" aria-hidden="true"></i><c class="icon-font">關於我們資料編輯</c></a></li>
             <script>
	            	 $('#xxx').click(function(){
	              		window.location.href = '<%=request.getContextPath()%>/front_end/menu/addMenu.jsp';
	              	});
             </script>
              &nbsp; &nbsp;
              <li class="nav-item dropdown b">
                 <a class="nav-item nav-link p material nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-coffee main-icon" aria-hidden="true"></i><c class="icon-font">餐點項目管理</c>
                 </a>
                     <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/back_end/menu/addMenu.jsp">新增經典餐點</a>
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/back_end/menu/Menu_management.jsp">經典餐點上下架</a>
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/back_end/menu/listAllMenu.jsp">所有經典餐點</a>
                    </div>
              </li>
              &nbsp; &nbsp;
              <li class="nav-item dropdown b"> 
                 <a class="nav-item nav-link p member nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user-md main-icon" aria-hidden="true" ></i><c class="icon-font">會員管理</c></a>
                     <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/back_end/report/Postreport.jsp">貼文檢舉管理</a>
                      <a class="dropdown-item" href="<%=request.getContextPath()%>/back_end/storedrecord/transaction_mang.jsp">儲值管理</a>
                    </div>
                 </li>
              &nbsp;&nbsp;
              <li class="nav-item dropdown b">
                 <a class="nav-item nav-link p branch nav-link dropdown-toggle" id="yyy" href="" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-university main-icon" aria-hidden="true"></i><c class="icon-font">分店管理</c></a></li>
              <script>
	            	 $('#yyy').click(function(){
	              		window.location.href = '<%=request.getContextPath()%>/back_end/branch/branch_mang.jsp';
	              	});
             </script>
              &nbsp;&nbsp;
              <li class="nav-item dropdown b">
                 <a class="nav-item nav-link p activity nav-link dropdown-toggle" id="zzz" href="" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-users main-icon" aria-hidden="true"></i><c class="icon-font">活動管理</c></a></li>
              <script>
	            	 $('#zzz').click(function(){
	              		window.location.href = '<%=request.getContextPath()%>/back_end/activity/addCoupon.jsp';
	              	});
             </script>
            </ul> 
          </div>

          <ul class="nav navbar-nav pull-right" style="padding:8px;">
                <a href="<%=request.getContextPath()%>/back_end/employee/empLogin_HQ.jsp" class="btn btn-sm  log-in"><i class="fa fa-user" aria-hidden="true"></i>登入</a>
<!--
               &nbsp;
                <a href="#" class="btn btn-sm  register"><i class="fa fa-cog" aria-hidden="true"></i>註冊</a>
-->
          </ul>

    </nav> 
    <nav class="navbar hd"></nav>
<!-- content start--> 
