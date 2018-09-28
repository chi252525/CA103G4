<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>>
<%@ page import="com.member.model.*"%>
<% MemberVO memVO = (MemberVO) session.getAttribute("memVO"); %>
<%session.getAttribute("mem_Id"); %>




<!DOCTYPE html>
<html >
    <head>
		<!-- Mobile Specific Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- Favicon-->
		<link rel="shortcut icon" href="">
		<!-- Meta Description -->
		<meta name="description" content="">
		<!-- Meta Keyword -->
		<meta name="keywords" content="">
		<!-- Site Title -->
		<title>MemberInfo</title> 
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <!--your  CSS ============================================= -->
       

       <!--Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		<script src="<%=request.getContextPath()%>/front_end/member/js/selectmemphoto.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/member/css/updateMem.css">
    </head>
    

 <body>
	<jsp:include page="/front_end/header.jsp"></jsp:include>
		
		<img
			src="<%= request.getContextPath() %>/front_end/img/top-banner1.jpg"
			width="100%" height="" alt="">
<!--your html   start==================================================================================-->

<div class="container col-5 rounded ">

	<form method="post" action="member.do" enctype="multipart/form-data" >
			
    <table id="back">
        <tr>
            <td colspan="2" id="img-td">
            	<input type="file" id="fileElem"  accept="image/*" style="display:none"  onchange="handleFiles()" name="mem_Photo">
                <a href="javascript:doClick()" style="color:black"><img class="custom-img" src="<%=request.getContextPath()%>/front_end/member/member.do?mem_No=<%= memVO.getMem_No()%>" id="img" ></a>
            </td>
            <td colspan="2" align="left">
				<c:if test="${not empty errorMsgs}">
					<font style="color:black;" >請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:black">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
            </td>
        </tr>
        <tr>
            <td class="td1" ><label for="mem_Id" class="col-form-label">帳號</label></td><td class="td2" ><input type="text" class="form-control-sm" id="mem_Id" name="mem_Id" value="${mem_Id}" readonly="readonly" style="background-color:#FFD4D4;"></td><td colspan="2" >預設線上訂單收件人資料：(以下非必填)</td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Pw" class="col-form-label">密碼</label></td><td class="td2" ><input type="password" class="form-control-sm" id="mem_Pw" name="mem_Pw" value="<%=memVO.getMem_Pw()%>" ></td>
            <td class="td3"><label for="mem_Receiver" class="col-form-label">收件人</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Receiver" name="mem_Receiver" value="<%=(memVO.getMem_Receiver()==null)? "":memVO.getMem_Receiver()%>" ></td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Name" class="col-form-label">姓名</label></td><td class="td2" ><input type="text" class="form-control-sm" id="mem_Name" name="mem_Name" value="<%=memVO.getMem_Name()%>" ></td>
            <td class="td3"><label for="mem_Repno" class="col-form-label">郵遞區號</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Repno" name="mem_Repno" value="<%=(memVO.getMem_Repno()==null)? "":memVO.getMem_Repno()%>" ></td>
        </tr>
        <tr>
            <td class="td1">性別</td>
            <td class="td2 mem_Gender" >
                <input  type="radio" name="mem_Gender" id="mem_Gender1" value="ms1" ${(memVO.mem_Gender == 'ms1' ) ?'checked':'' } >
                <label  for="mem_Gender1">男</label>
                <input  type="radio" name="mem_Gender" id="mem_Gender2" value="ms2" ${(memVO.mem_Gender == 'ms2' ) ?'checked':'' } >
                <label  for="mem_Gender2">女</label>
            </td>
            <td class="td3">鄉鎮區市</td>
            <td class="td4">
                <select style="display:inline;" class="nice-select" name="mem_Recounty" id="city-list" >
                </select>                
               
                <select style="display:inline;" name="mem_Retown" class="nice-select" id="sector-list" > 
                </select>

            </td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Bir" class="col-form-label">生日</label></td><td class="td2" ><input type="Date" class="form-control-sm" id="mem_Bir" name="mem_Bir" value="<fmt:formatDate value="<%=memVO.getMem_Bir()%>" pattern="yyyy-MM-dd"/>"></td>
            <td class="td3"><label for="mem_Readdr" class="col-form-label">地址</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Readdr" name="mem_Readdr" value="<%=(memVO.getMem_Readdr()==null)? "":memVO.getMem_Readdr()%>" ></td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Mail" class="col-form-label">信箱</label></td><td class="td2" ><input type="email" class="form-control-sm" id="mem_Mail" name="mem_Mail" value="<%=memVO.getMem_Mail()%>" readonly="readonly"></td>
            <td class="td3"><label for="mem_Cardnum" class="col-form-label">信用卡</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Cardnum" name="mem_Cardnum" value="<%=(memVO.getMem_Cardnum()==null)? "":memVO.getMem_Cardnum()%>" ></td>
        </tr>
        <tr>
            <td class="td1"><label for="mem_Phone" class="col-form-label">電話</label></td><td class="td2" ><input type="text" class="form-control-sm" id="mem_Phone" name="mem_Phone" value="<%=memVO.getMem_Phone()%>" readonly="readonly"></td>
            <td class="td3"><label for="mem_Carddue" class="col-form-label">截止日</label></td><td class="td4"><input type="text" class="form-control-sm" id="mem_Carddue" name="mem_Carddue" value="<%=(memVO.getMem_Carddue()==null)? "":memVO.getMem_Carddue()%>"></td>
        </tr>
        <tr>
            <td colspan="4" class="tdbtn">
            <a href="<%=request.getContextPath() %>/front_end/header.jsp" class="btn btn-warning btn-sm " align="right" style="width:80px">取消</a>
            <input type="hidden" name="action" value="update">
            <input type="submit" class="btn btn-warning btn-sm " align="right" id="send" style="width:80px" value="確認修改   "> 
            </td>
        </tr>
    </table>
    </form>
</div>  

  
<script>

var city=['請選擇','基隆市','臺北市','新北市','桃園市','新竹市','新竹縣','苗栗縣','臺中市','彰化縣','南投縣','雲林縣','嘉義市','嘉義縣','臺南市','高雄市','屏東縣','臺東縣','花蓮縣','宜蘭縣','澎湖縣','金門縣','連江縣'];

var sectors=new Array();
sectors[0]=['請選擇'];
sectors[1]=['仁愛區','信義區','中正區','中山區','安樂區','暖暖區','七堵區'];
sectors[2]=['中正區','大同區','中山區','松山區','大安區','萬華區','信義區', '士林區','北投區','內湖區','南港區','文山區'];	
sectors[3]=['萬里區','金山區','板橋區','汐止區','深坑區','石碇區','瑞芳區','平溪區','雙溪區','貢寮區','新店區','坪林區','烏來區','永和區','中和區','土城區','三峽區','樹林區','鶯歌區','三重區','新莊區','泰山區','林口區','蘆洲區','五股區','八里區','淡水區','三芝區','石門區'];	
sectors[4]=['中壢區','平鎮區','龍潭區','楊梅區','新屋區','觀音區','桃園區','龜山區','八德區','大溪區','復興區','大園區','蘆竹區'];
sectors[5]=['東區','北區','香山區'];
sectors[6]=['竹北市','湖口鄉','新豐鄉','新埔鎮','關西鎮','芎林鄉','寶山鄉','竹東鎮','五峰鄉','橫山鄉','尖石鄉','北埔鄉','峨眉鄉',];
sectors[7]=['竹南鎮','頭份市','三灣鄉','南庄鄉','獅潭鄉','後龍鎮','通霄鎮','苑裡鎮','苗栗市','造橋鄉','頭屋鄉','公館鄉','大湖鄉','泰安鄉','銅鑼鄉','三義鄉','西湖鄉','卓蘭鎮'];
sectors[8]=['中區','東區','南區','西區','北區','北屯區','西屯區','南屯區','太平區','大里區','霧峰區','烏日區','豐原區','后里區','石岡區','東勢區','和平區','新社區','潭子區','大雅區','神岡區','大肚區','沙鹿區','龍井區','梧棲區','清水區','大甲區','外埔區','大安區',];
sectors[9]=['彰化市','芬園鄉','花壇鄉','秀水鄉','鹿港鎮','福興鄉','線西鄉','和美鎮','伸港鄉','員林市','社頭鄉','永靖鄉','埔心鄉','溪湖鎮','大村鄉','埔鹽鄉','田中鎮','北斗鎮','田尾鄉','埤頭鄉','溪州鄉','竹塘鄉','二林鎮','大城鄉','芳苑鄉','二水鄉',];
sectors[10]=['南投市','中寮鄉','草屯鎮','國姓鄉','埔里鎮','仁愛鄉','名間鄉','集集鎮','水里鄉','魚池鄉','信義鄉','竹山鎮','鹿谷鄉'];
sectors[11]=['斗南鎮','大埤鄉','虎尾鎮','土庫鎮','褒忠鄉','東勢鄉','臺西鄉','崙背鄉','麥寮鄉','斗六市','林內鄉','古坑鄉','莿桐鄉','西螺鎮','二崙鄉','北港鎮','水林鄉','口湖鄉','四湖鄉','元長鄉'];
sectors[12]=['東區','西區'];
sectors[13]=['番路鄉','梅山鄉','竹崎鄉','阿里山鄉','中埔鄉','大埔鄉','水上鄉','鹿草鄉','太保市','朴子市','東石鄉','六腳鄉','新港鄉','民雄鄉','大林鎮','溪口鄉','義竹鄉','布袋鎮'];
sectors[14]=['中西區','東區','南區','北區','安平區','安南區','永康區','歸仁區','新化區','左鎮區','玉井區','楠西區','南化區','仁德區','關廟區','龍崎區','官田區','麻豆區','佳里區','西港區','七股區','將軍區','學甲區','北門區','新營區','後壁區','白河區','東山區','六甲區','下營區','柳營區','鹽水區','善化區','大內區','山上區','安定區'];
sectors[15]=['新興區','前金區','苓雅區','鹽埕區','鼓山區','旗津區','前鎮區','三民區','楠梓區','小港區','左營區','仁武區','大社區','東沙群島','南沙群島','岡山區','路竹區','阿蓮區','田寮區','燕巢區','橋頭區','梓官區','彌陀區','永安區','湖內區','鳳山區','大寮區','林園區','鳥松區','大樹區','旗山區','美濃區','六龜區','內門區','杉林區','甲仙區','桃源區','那瑪夏區','茂林區','茄萣區'];
sectors[16]=[
    '屏東市','三地門鄉','霧臺鄉','瑪家鄉','九如鄉','里港鄉','高樹鄉','鹽埔鄉','長治鄉','麟洛鄉','竹田鄉','內埔鄉','萬丹鄉','潮州鎮','泰武鄉','來義鄉','萬巒鄉','崁頂鄉','新埤鄉','南州鄉','林邊鄉','東港鎮','琉球鄉','佳冬鄉','新園鄉','枋寮鄉','枋山鄉','春日鄉','獅子鄉','車城鄉','牡丹鄉','恆春鎮','滿州鄉'];                
sectors[17]=['臺東市','綠島鄉','蘭嶼鄉','延平鄉','卑南鄉','鹿野鄉','關山鎮','海端鄉','池上鄉','東河鄉','成功鎮','長濱鄉','太麻里鄉','金峰鄉','大武鄉','達仁鄉'];
sectors[18]=['花蓮市','新城鄉','秀林鄉','吉安鄉','壽豐鄉','鳳林鎮','光復鄉','豐濱鄉','瑞穗鄉','萬榮鄉','玉里鎮','卓溪鄉','富里鄉'];
sectors[19]=['宜蘭市', '頭城鎮','礁溪鄉','壯圍鄉','員山鄉','羅東鎮','三星鄉','大同鄉','五結鄉','冬山鄉','蘇澳鎮','南澳鄉','釣魚臺'];
sectors[20]=['馬公市','西嶼鄉','望安鄉','七美鄉','白沙鄉','湖西鄉'];
sectors[21]=['金沙鎮','金湖鎮','金寧鄉','金城鎮','烈嶼鄉','烏坵鄉'];
sectors[22]=['南竿鄉','北竿鄉','莒光鄉','東引鄉']; 
sectors[23]=['東沙群島','南沙群島'];


$(document).ready(function(){
    var county="<%= memVO.getMem_Recounty() %>";
    var town = "<%= memVO.getMem_Retown()%>";
    var inner="";

    	
    	for(var i=0;i<city.length;i++){
    		inner=inner+'<option value='+city[i]+' id=city'+i+'\>'+city[i]+'</option>';
    	}
		
    $("#city-list").html(inner)
    $("#city-list").change(function(){
        index=this.selectedIndex;
        var Sinner="";

		  for(var i=0;i<sectors[index].length;i++){
		      Sinner=Sinner+'<option value='+sectors[index][i]+'\>'+sectors[index][i]+'</option>';
			}		
			        
        $("#sector-list").html(Sinner);
    });
    $("#city-list").change();
});

$(function(){
	var town = "<%= memVO.getMem_Retown()%>";
	var county="<%= memVO.getMem_Recounty() %>";
	for(var i=0;i<city.length;i++){
		if(county == city[i]){
			document.getElementById('city-list').getElementsByTagName('option')[i].selected = 'selected';
		}
	}
	$("#city-list").change(function(){
		index = document.getElementById('city-list').selectedIndex;
		console.log(index);
		console.log(town);
		for(var i=0;i < sectors[index].length;i++){
			if(town == sectors[index][i]){
				document.getElementById('sector-list').getElementsByTagName('option')[i].selected = 'selected';
			}
		}
	});
	$("#city-list").change();
});

</script>
<!--以下勿動-->
    <!-- Footer -->
<jsp:include page="/front_end/footer.jsp"></jsp:include>
	 <!-- Footer -->		
		</body>
	</html>
