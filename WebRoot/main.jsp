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
<title>舆情预警</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/css/pagination.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.js" ></script>
<script type="text/javascript" src="<%=path%>/js/script.js" ></script>
<script type="text/javascript" src="<%=path%>/js/jquery.pagination.js" ></script>
<%-- <script type="text/javascript" src="<%=path%>/js/xhdata.js" ></script> --%>
<script type="text/javascript" src="<%=path%>/js/main.js" ></script>
</head>

<body>
	<div style="height:1090px;" class="box">
		<div class="header">
			<div class="logo">
				<img src="images/logo.png" />
			</div>
			<span class="tit"></span>
		</div>
		<div id="notice-tit" class="top_nav">
			<ul>				
				<li class="select"><a href='javascript:networkHot()'>查询网络热点</a></li>
				<li><a href='javascript:networkWarn()'>查询定网预警</a></li>
				<li><a href='javascript:todayWarn()'>查询当日预警</a></li>
				<li><a href='javascript:newMessage()'>查询最新消息</a></li>
				<li><a href='javascript:evenWarn()'>查询往日预警</a></li>
				<li><a href='javascript:settingFour()'>设置</a></li>								
			</ul>
		</div>
		<div class="content">
			<div class="rank">
				<div class="rank_bottom">
					<div class="list_tit">
						<span class="tit_index">排名</span> <span class="tit_word">关键词</span> <span class="tit_value">搜索指数</span>
					</div>
					<ul class="i_list">
						<li>
							<div class="list_hd">
								<span class="num_top">1</span> <span class="list_name">花千骨</span> <span class="click_num">30222</span>
							</div>
						</li>
						<li>
							<div class="list_hd">
								<span class="num_top">2</span> <span class="list_name">宁泽涛</span> <span class="click_num">30222</span>
							</div>
						</li>
						<li>
							<div class="list_hd">
								<span class="num_top">3</span> <span class="list_name">盗墓笔记</span> <span class="click_num">30222</span>
							</div>
						</li>
						<li>
							<div class="list_hd">
								<span class="num_top2">4</span> <span class="list_name">霍建华</span> <span class="click_num">30222</span>
							</div>
						</li>
						<li>
							<div class="list_hd">
								<span class="num_top2">5</span> <span class="list_name">花千骨</span> <span class="click_num">30222</span>
							</div>
						</li>
						<li>
							<div class="list_hd">
								<span class="num_top2">6</span> <span class="list_name">花千骨</span> <span class="click_num">30222</span>
							</div>
						</li>
						<li>
							<div class="list_hd">
								<span class="num_top2">7</span> <span class="list_name">花千骨</span> <span class="click_num">30222</span>
							</div>
						</li>
						<li>
							<div class="list_hd">
								<span class="num_top2">8</span> <span class="list_name">花千骨</span> <span class="click_num">30222</span>
							</div>
						</li>
						<li>
							<div class="list_hd">
								<span class="num_top2">9</span> <span class="list_name">花千骨</span> <span class="click_num">30222</span>
							</div>
						</li>
						<li>
							<div class="list_hd">
								<span class="num_top2">10</span> <span class="list_name">花千骨</span> <span class="click_num">30222</span>
							</div>
						</li>
						<li>
							<div class="list_hd">
								<span class="num_top2">11</span> <span class="list_name">花千骨</span> <span class="click_num">30222</span>
							</div>
						</li>
						<li>
							<div class="list_hd">
								<span class="num_top2">12</span> <span class="list_name">花千骨</span> <span class="click_num">30222</span>
							</div>
						</li>
						<li>
							<div class="list_hd">
								<span class="num_top2">13</span> <span class="list_name">花千骨</span> <span class="click_num">30222</span>
							</div>
						</li>
					</ul>
				</div>
				<div class="box_chart"></div>
			</div>
			<div id="notice-con">
				<div class="mod" style="display:block;">
					<span class="intro">当前位置：网络热点</span>
					<div id="model">
						<ul id="htcList"></ul>
						<div id="Pagination" class="pagination">
							<!-- 这里显示分页 -->
						</div>
						<ul id="Searchresult" style="display:none;"></ul>
					</div>
				</div>
				<div class="mod" style="display:none">
					<span class="intro">当前位置：定网预警</span>
					<div id="model">
						<ul id="htcList1"></ul>
						<div id="Pagination1" class="pagination">
							<!-- 这里显示分页 -->
						</div>
						<ul id="Searchresult1" style="display:none;"></ul>
					</div>
				</div>
				<div class="mod" style="display:none">
					<span class="intro">当前位置：当日预警</span>
					<div id="model">
						<ul id="htcList2"></ul>
						<div id="Pagination2" class="pagination">
							<!-- 这里显示分页 -->
						</div>
						<ul id="Searchresult2" style="display:none;"></ul>
					</div>
				</div>
				<div class="mod" style="display:none">
					<span class="intro">当前位置：最新消息</span>
					<div id="model">
						<ul id="htcList3"></ul>
						<div id="Pagination3" class="pagination">
							<!-- 这里显示分页 -->
						</div>
						<ul id="Searchresult3" style="display:none;"></ul>
					</div>
				</div>
				<div class="mod" style="display:none">
					<span class="intro">当前位置：往日预警</span>
					<div id="model">
						<ul id="htcList4"></ul>
						<div id="Pagination4" class="pagination">
							<!-- 这里显示分页 -->
						</div>
						<ul id="Searchresult4" style="display:none;"></ul>
					</div>
				</div>
				<div class="mod" style="display:none">公yi益内容面板</div>
			</div>
		</div>
		<div class="bottom">
			<p>版权所有&nbsp;：&nbsp;河北科技大学</p>
			<p>技术支持&nbsp;：&nbsp;河北科技大学大数据与社会计算机研究中心</p>
		</div>
	</div>



</body>
</html>
