<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>360热搜</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/360Wall/css/style.css" >
<link rel="stylesheet" type="text/css" href="<%=path%>/360Wall/css/jiathis_share.css" >
<script type="text/javascript" src="<%=path%>/360Wall/js/360Wall.js"></script>
<script type="text/javascript" src="<%=path%>/360Wall/js/jquery-1.8.2.min.js"></script>
</head>

<body style="width: auto;">
	<div id="wrapper">
		<div id="content" class="clearfix">
			<div class="container" style="height: 418px; margin-top: 0px;">
				<div id="hotword" style="width: 1028px;">
					<div id="box_1" class="box" data-which="1" data-anim="1" style="width: 24.5%; height: 48%; left: 0px; top: 0px; background-color: rgb(57, 170, 121); background-position: initial initial; background-repeat: initial initial;font-size:23px;"></div>
					<div id="box_2" class="box" data-which="2" data-anim="1" style="width: 24.5%; height: 48%; left: 260px; top: 0px; background-color: rgb(31, 156, 102); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_3" class="box" data-which="3" data-anim="1" style="width: 12%; height: 48%; left: 520px; top: 0px; background-color: rgb(137, 55, 30); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_4" class="box" data-which="4" data-anim="1" style="width: 12%; height: 48%; left: 650px; top: 0px; background-color: rgb(30, 101, 151); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_5" class="box" data-which="5" data-anim="1" style="width: 12%; height: 24%; left: 780px; top: 0px; background-color: rgb(31, 156, 102); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_6" class="box" data-which="6" data-anim="1" style="width: 12%; height: 48%; left: 910px; top: 0px; background-color: rgb(165, 90, 67); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_7" class="box" data-which="7" data-anim="1" style="width: 12%; height: 24%; left: 780px; top: 101px; background-color: rgb(20, 135, 85); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_8" class="box" data-which="8" data-anim="1" style="width: 24.5%; height: 24%; left: 0px; top: 210px; background-color: rgb(30, 101, 151); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_9" class="box" data-which="9" data-anim="1" style="width: 12%; height: 24%; left: 0px; top: 311px; background-color: rgb(20, 135, 85); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_10" class="box" data-which="10" data-anim="1" style="width: 12%; height: 24%; left: 130px; top: 311px; background-color: rgb(137, 55, 30); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_11" class="box" data-which="11" data-anim="1" style="width: 12%; height: 48%; left: 260px; top: 210px; background-color: rgb(20, 135, 85); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_12" class="box" data-which="12" data-anim="1" style="width: 12%; height: 24%; left: 390px; top: 210px; background-color: rgb(30, 101, 151); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_13" class="box" data-which="13" data-anim="1" style="width: 12%; height: 24%; left: 390px; top: 311px; background-color: rgb(55, 127, 178); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_14" class="box" data-which="14" data-anim="1" style="width: 12%; height: 24%; left: 520px; top: 210px; background-color: rgb(55, 127, 178); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_15" class="box" data-which="15" data-anim="1" style="width: 12%; height: 24%; left: 650px; top: 210px; background-color: rgb(165, 90, 67); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_16" class="box" data-which="16" data-anim="1" style="width: 24.5%; height: 24%; left: 520px; top: 311px; background-color: rgb(55, 127, 178); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_17" class="box" data-which="17" data-anim="1" style="width: 12%; height: 24%; left: 780px; top: 210px; background-color: rgb(31, 156, 102); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_18" class="box" data-which="18" data-anim="1" style="width: 12%; height: 24%; left: 910px; top: 210px; background-color: rgb(55, 127, 178); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_19" class="box" data-which="19" data-anim="1" style="width: 12%; height: 24%; left: 780px; top: 311px; background-color: rgb(31, 156, 102); background-position: initial initial; background-repeat: initial initial;"></div>
					<div id="box_20" class="box" data-which="20" data-anim="1" style="width: 12%; height: 24%; left: 910px; top: 311px; background-color: rgb(20, 135, 85); background-position: initial initial; background-repeat: initial initial;"></div>
				</div>
				<div class="refresh_btn" id="refresh_btn" style="top: 185px;">
					<span style='height:44px;width:30px;cursor:pointer;display:block;background-image:url(images/nav-40.png);filter:alpha(opacity=50);opacity:0.5;'  onclick="Wall360Refresh_btn()"></span>
				</div>
			</div>
		</div>
		<div class="push"></div>
	</div>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
$(".box").hover(function(){
$(this).find('div').show();
},function(){
$(this).find('div').hide();
});
</script>

</body>
</html>
