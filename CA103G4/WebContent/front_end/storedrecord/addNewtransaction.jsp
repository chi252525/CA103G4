<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/front_end/header.jsp" flush="true" />
<!--background image-->
<img src="<%=request.getContextPath()%>/front_end/img/top-banner1.jpg" width="100%" height="" alt="">
<meta charset="utf-8">
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
<script src="<%=request.getContextPath()%>/front_end/shoppingCart/js/card.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/shoppingCart/css/card.css">
<!-- address choosing -->
<script src="<%=request.getContextPath()%>/front_end/member/js/selectaddress.js"></script>

<link href="https://fonts.googleapis.com/css?family=Fira+Sans" rel="stylesheet">
<style>
    .list-group-item {
        height: 100px;
    }

    .center {
        width: auto;
        display: table;
        margin-left: auto;
        margin-right: auto;
        margin-top: auto;
        margin-bottom: auto;
    }

    .text-center {
        text-align: center;
    }
    
    .inputGroup {
    background-color: #fff;
    display: block;
    margin: 10px 0;
    position: relative;

    label {
      padding: 12px 30px;
      width: 100%;
      display: block;
      text-align: left;
      color: #3C454C;
      cursor: pointer;
      position: relative;
      z-index: 2;
      transition: color 200ms ease-in;
      overflow: hidden;

      &:before {
        width: 10px;
        height: 10px;
        border-radius: 50%;
        content: '';
        background-color: #5562eb;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%) scale3d(1, 1, 1);
        transition: all 300ms cubic-bezier(0.4, 0.0, 0.2, 1);
        opacity: 0;
        z-index: -1;
      }

      &:after {
        width: 32px;
        height: 32px;
        content: '';
        border: 2px solid #D1D7DC;
        background-color: #fff;
        background-image: url("data:image/svg+xml,%3Csvg width='32' height='32' viewBox='0 0 32 32' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M5.414 11L4 12.414l5.414 5.414L20.828 6.414 19.414 5l-10 10z' fill='%23fff' fill-rule='nonzero'/%3E%3C/svg%3E ");
        background-repeat: no-repeat;
        background-position: 2px 3px;
        border-radius: 50%;
        z-index: 2;
        position: absolute;
        right: 30px;
        top: 50%;
        transform: translateY(-50%);
        cursor: pointer;
        transition: all 200ms ease-in;
      }
    }

    input:checked ~ label {
      color: #fff;

      &:before {
        transform: translate(-50%, -50%) scale3d(56, 56, 1);
        opacity: 1;
      }

      &:after {
        background-color: #54E0C7;
        border-color: #54E0C7;
      }
    }

    input {
      width: 32px;
      height: 32px;
      order: 1;
      z-index: 2;
      position: absolute;
      right: 30px;
      top: 50%;
      transform: translateY(-50%);
      cursor: pointer;
      visibility: hidden;
    }
  }


// codepen formatting
.form {
  padding: 0 16px;
  max-width: 550px;
  margin: 50px auto;
  font-size: 18px;
  font-weight: 600;
  line-height: 36px;
}

body {
  background-color: #D1D7DC;
  font-family: 'Fira Sans', sans-serif;
}

*,
*::before,
*::after {
  box-sizing: inherit;
}

html {
  box-sizing: border-box;
}

code {
  background-color: #9AA3AC;
  padding: 0 8px;
}
    

</style>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>

<body background="<%=request.getContextPath()%>/front_end/img/woodbackground3.png" width="100%">
    <div class="container-fluid col-12" style="height:400px">
        <div class="row">
            <div class="col-md-8" style="margin-top: 10%;padding-left: 0px;">
                <div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img class="d-block w-100" src="images/ad_01.png" alt="First slide" style="">
                        </div>
                        <div class="carousel-item">
                            <img class="d-block w-100" src="images/ad_07.png" alt="Second slide">
                        </div>
                        <div class="carousel-item">
                            <img class="d-block w-100" src="images/ad_06.png" alt="Third slide">
                        </div>
                    </div>
                </div>
            </div>
            <div class="list-group mr-auto col-12 col-md-4" id="list-tab" role="tablist" style="height: 100% ;font-size:20px;">

                <h2 style="margin-top: 30%;">竹風堂-竹幣儲值</h2>
                <form method="post" action="storedrecord.do">
					
                    <select name="stor_Point" class="custom-select custom-select-lg mb-3" size="5" style="height:90%">
                        <option selected style="font-size:40px;" value="請選擇">產品選擇</option>
                        <option value="500">500竹幣</option>
                        <option value="1000">1000竹幣</option>
                        <option value="1500">1500竹幣</option>
                        <option value="2000">2000竹幣</option>
                    </select>

                    <button type="submit" class="btn btn-lg" name="action" value="getPoint">儲值點數</button>
                </form>
            </div>



        </div>

    </div>
    <jsp:include page="/front_end/footer.jsp" />
    <script>
        <%for(int i=500;i<=2000;i+=500){%>

        function addValue <%=i%>() {

            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/front_end/storedrecord/storedrecord.do",
                data: {
                    "action": "insert",
                    "stor_Point": "<%=i%>",

                },
                dataType: "html",
                success: function(amount) {

                },
                error: function() {
                    alert("reduce ajax error!")
                }
            })

        }
        <%}%>

    </script>
</body>

</html>
