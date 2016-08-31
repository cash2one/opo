<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/zzsc.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">

<link rel="stylesheet" type="text/css" href="<%=path%>/css/pagination.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/yx_rotaion.css">

<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery.dataTables.css" >
<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery-ui.css">
<!-- 引入日期查询input--css -->
<link type="text/css" rel="stylesheet" href="<%=path%>/css/jquery-ui-1.9.2.custom.css" >

<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.min.js"></script>

<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/mainTwo/jquery.yx_rotaion.js"></script>
<script type="text/javascript" src="<%=path%>/js/mainTwo/script.js"></script>
<script type="text/javascript" src="<%=path%>/js/mainTwo/jquery.pagination.js"></script>
<!-- <script type="text/javascript" src="js/mainTwo/xhdata.js" ></script> -->
<script type="text/javascript" src="<%=path%>/js/main.js"></script>
<script type="text/javascript" src="<%=path%>/js/mainTwo/settingAlert.js"></script>
<script type="text/javascript" charset="utf8" src="<%=path%>/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=path%>/js/setting/setUnionKeyword.js"></script>
<script type="text/javascript" src="<%=path%>/js/setting/newkeywords.js"></script>
<script type="text/javascript" src="<%=path%>/js/setting/setKeywordList.js"></script>
<script type="text/javascript" src="<%=path%>/js/setting/setNetAddr.js"></script>
<script type="text/javascript" src="<%=path%>/js/firstPage/firstPageHot.js"></script>
<script type="text/javascript" src="<%=path%>/js/firstPage/firstPageEarth.js"></script>
<!-- 引入日期查询input--js -->
<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="<%=path%>/js/share.js"></script>
<!-- 引入预警播放--js -->
<script type="text/javascript" src="<%=path%>/js/soundmanager2-nodebug-jsmin.js"></script>
<script type="text/javascript" src="<%=path%>/js/animation2.js"></script>
<script type="text/javascript" src="<%=path%>/js/mp3-player-button.js"></script>
<script type="text/javascript" src="<%=path%>/js/soundmanager2.js"></script>
<script type="text/javascript" src="<%=path%>/js/result/Alarm.js"></script>


</head>

<body>

	<div class="box">
		<div class="header">
			<div class="logo">
				<img src="images/logo.png" />
			</div>
			<span class="tit"></span>
		</div>
		<div id="notice-tit" class="top_nav">
			<ul>
				<li class="select"><a href='javascript:firstPage()'>首页</a></li>
				<li><a href='javascript:networkHot()'>查询网络热点</a></li>
				<li><a href='javascript:networkWarn()'>查询定网预警</a></li>
				<li><a href='javascript:todayWarn()'>查询当日预警</a></li>
				<li><a href='javascript:newMessage()'>查询最新消息</a></li>
				<li><a href='javascript:evenWarn()'>查询往日预警</a></li>
			</ul>
		</div>

		<div class="content2">

			<div id="notice-con">
				<div class="mod" style="display:block;">
					<div class="reset">
						<ul>
							<li><a class="aaa_setSensitiveKey" href='javascript:setSensitiveKey_click()'>设置敏感关键词</a></li>
							<li><a class="aaa_setUnionKey" href='javascript:setUnionKey_click()'>设置组合关键词</a></li>
							<li><a class="aaa_setNetAddr" href='javascript:setNetAddr_click()'>设置定网网址</a></li>
							<li><a class="aaa_setNewKey" href='javascript:setNewKey_click()'>设置最新关键词</a></li>
							<li><a href='javascript:stopdown()'>解除警报</a></li>
						</ul>
					</div>
					<!-- <div style="width:1028px;height:418px;background:#ccc;margin-left:124px;" class="new_wall"></div> -->
					<div style="width:1028px;height:418px;margin-left:124px;" class="new_wall">
						<iframe src="360Wall/360Wall.jsp" width="1028px" height="418px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
					</div>
					<div class="cloud_word">
						<div class="first_left">
							<div class="main-page">
								<div class="left">
									<div class="nav-back"></div>
									<div class="nav">
										<div class="on" onclick="amusementClick('2')">实时热点</div>
										<div onclick="amusementClick('3')">今日热点</div>
										<div onclick="amusementClick('4')">七日热点</div>
										<div onclick="amusementClick('5')">民生热点</div>
										<!-- <div onclick="amusementClick('6')">游戏</div>
										<div onclick="amusementClick('7')">汽车</div>
										<div onclick="amusementClick('8')">科技</div>
										<div onclick="amusementClick('9')">生活</div>
										<div onclick="amusementClick('10')">热搜</div> -->
									</div>
								</div>
								<div class="right">
									<div class="content-back"></div>
									<div class="content">
										<div id="g_content_div"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="first_right">
							<span class="word_tit">舆情热词</span>
							<span class="banner_left"></span>
							<span class="banner_right"></span>
							<div id="tagbox">
								<a href="#">美女</a> <a class="red" href="#">写真</a> <a href="#">贴图</a> <a href="#">灌水</a> <a class="blue" href="#">大片</a>
								<a href="#">小说</a> <a class="red" href="#">欧美</a> <a class="yellow" href="#">日韩</a> <a href="#">诱人</a> <a class="red" href="#">搞笑</a>
								
								<a href="#">性感</a> <a class="blue"	href="#">壁纸</a> <a class="blue" href="#">泳装</a> <a class="red" href="#">言情</a> <a class="yellow" href="#">浪漫</a>
								<a class="yellow" href="#">耽美</a> <a class="blue" href="#">爆笑</a> <a class="yellow" href="#">美眉</a> <a class="blue" href="#">同人</a> <a class="blue" href="#">武侠</a>
								<a class="red"	href="#">魔幻</a> <a href="#">教案</a> <a href="#">论文</a> <a class="yellow" href="#">妩媚</a> <a href="#">黑丝</a> 
								<a href="#">诱惑</a> <a class="blue" href="#">科幻</a> <a class="red" href="#">恐怖</a> <a class="red" href="#">性感</a> <a class="blue" href="#">TXT下载</a>
								<a href="#">音乐</a> <a class="blue"	href="#">游戏</a> <a class="blue" href="#">CG资源</a> <a href="#">YunFile</a> <a href="#">课件</a>
								<a href="#">幽默</a> <a href="#">波霸</a> <a href="#">私人照</a> <a href="#">名校</a> <a class="red" href="#">赚钱</a>
								<a class="yellow" href="#">千脑</a> <a class="yellow" href="#">清純</a> <a class="yellow" href="#">云电脑</a>

							</div>
						</div>
					</div>
				</div>
				<div class="mod" style="display:none">
					<span class="intro">当前位置：网络热点</span>
					<div class="reset">
						<ul>
							<li><a class="aaa_setSensitiveKey" href='javascript:setSensitiveKey_click()'>设置敏感关键词</a></li>
							<li><a class="aaa_setUnionKey" href='javascript:setUnionKey_click()'>设置组合关键词</a></li>
							<li><a class="aaa_setNetAddr" href='javascript:setNetAddr_click()'>设置定网网址</a></li>
							<li><a class="aaa_setNewKey" href='javascript:setNewKey_click()'>设置最新关键词</a></li>
							<li><a href="#">解除警报</a></li>
						</ul>
					</div>

					<div id="model">
					    <input id="topKeywordDate"  type="text" class="ui-datepicker-time" readonly value="" />
                        <button style="font-family:Microsoft Yahei;  top: -30px;"onclick="networkHot01()">搜索消息</button>
						<ul id="htcList"></ul>
						<div id="Pagination" class="pagination">
							<!-- 这里显示分页 -->
						</div>
						<ul id="Searchresult" style="display:none;"></ul>
					</div>
					
					<div class="rank">
						<div class="box_chart"></div>
						<div class="rank_bottom">
							<div class="list_tit">
								<span class="tit_index">排名</span> <span class="tit_word">关键词</span> <span class="tit_value">搜索指数</span>
							</div>
							<ul class="i_list" id="netHot_rank_ul">

							</ul>
						</div>

					</div>
					<div style='clear:both'></div>
				</div>
				<div class="mod" style="display:none">
					<span class="intro">当前位置：定网预警</span>
					<div class="reset">
						<ul>
							<li><a class="aaa_setSensitiveKey" href='javascript:setSensitiveKey_click()'>设置敏感关键词</a></li>
							<li><a class="aaa_setUnionKey" href='javascript:setUnionKey_click()'>设置组合关键词</a></li>
							<li><a class="aaa_setNetAddr" href='javascript:setNetAddr_click()'>设置定网网址</a></li>
							<li><a class="aaa_setNewKey" href='javascript:setNewKey_click()'>设置最新关键词</a></li>
							<li><a href="#">解除警报</a></li>
						</ul>
					</div>
					<div id="model">
					    <input id="netAddrsWarnDate" type="text" class="ui-datepicker-time" readonly value="" />
                        <button style="font-family:Microsoft Yahei;  top: -30px;"onclick="networkWarn()">搜索消息</button>
						<ul id="htcList1"></ul>
						<div id="Pagination1" class="pagination">
							<!-- 这里显示分页 -->
						</div>
						<ul id="Searchresult1" style="display:none;"></ul>
					</div>
					<div class="rank">
						<div class="box_chart"></div>
						<div class="rank_bottom">
							<div class="list_tit">
								<span class="tit_index">排名</span> <span class="tit_word">关键词</span> <span class="tit_value">搜索指数</span>
							</div>
							<ul class="i_list" id="netAddr_rank_ul">

							</ul>
						</div>

					</div>
				</div>

				<div class="mod" style="display:none">
					<span class="intro">当前位置：当日预警</span>
					<div class="reset">
						<ul>
							<li><a class="aaa_setSensitiveKey" href='javascript:setSensitiveKey_click()'>设置敏感关键词</a></li>
							<li><a class="aaa_setUnionKey" href='javascript:setUnionKey_click()'>设置组合关键词</a></li>
							<li><a class="aaa_setNetAddr" href='javascript:setNetAddr_click()'>设置定网网址</a></li>
							<li><a class="aaa_setNewKey" href='javascript:setNewKey_click()'>设置最新关键词</a></li>
							<li><a href="#">解除警报</a></li>
						</ul>
					</div>
					<div id="model">
						<ul id="htcList2"></ul>
						<div id="Pagination2" class="pagination">
							<!-- 这里显示分页 -->
						</div>
						<ul id="Searchresult2" style="display:none;"></ul>
					</div>
					<div class="rank">
						<div class="box_chart"></div>
						<div class="rank_bottom">
							<div class="list_tit">
								<span class="tit_index">排名</span> <span class="tit_word">关键词</span> <span class="tit_value">搜索指数</span>
							</div>
							<ul class="i_list" id="todayWarn_rank_ul">

							</ul>
						</div>

					</div>
				</div>

				<div class="mod" style="display:none">
					<span class="intro">当前位置：最新消息</span>
					<div class="reset">
						<ul>
							<li><a class="aaa_setSensitiveKey" href='javascript:setSensitiveKey_click()'>设置敏感关键词</a></li>
							<li><a class="aaa_setUnionKey" href='javascript:setUnionKey_click()'>设置组合关键词</a></li>
							<li><a class="aaa_setNetAddr" href='javascript:setNetAddr_click()'>设置定网网址</a></li>
							<li><a class="aaa_setNewKey" href='javascript:setNewKey_click()'>设置最新关键词</a></li>
							<li><a href="#">解除警报</a></li>
						</ul>
					</div>
					<div id="model">					
					<input id="RResultDate" type="text" class="ui-datepicker-time" readonly value="" />
                    <button style="font-family:Microsoft Yahei;  top: -30px;"onclick="newMessage()">搜索消息</button>
						<ul id="htcList3"></ul>
						<div id="Pagination3" class="pagination">
							<!-- 这里显示分页 -->
						</div>
						<ul id="Searchresult3" style="display:none;">
						
						</ul>
					</div>
					<div class="rank">
						<div class="box_chart"></div>
						<div class="rank_bottom">
							<div class="list_tit">
								<span class="tit_index">排名</span> <span class="tit_word">关键词</span> <span class="tit_value">搜索指数</span>
							</div>
							<ul class="i_list" id="newest_rank_ul">

							</ul>
						</div>

					</div>
				</div>

				<div class="mod" style="display:none">
					<span class="intro">当前位置：往日预警</span>
					<div class="reset">
						<ul>
							<li><a class="aaa_setSensitiveKey" href='javascript:setSensitiveKey_click()'>设置敏感关键词</a></li>
							<li><a class="aaa_setUnionKey" href='javascript:setUnionKey_click()'>设置组合关键词</a></li>
							<li><a class="aaa_setNetAddr" href='javascript:setNetAddr_click()'>设置定网网址</a></li>
							<li><a class="aaa_setNewKey" href='javascript:setNewKey_click()'>设置最新关键词</a></li>
							<li><a href="#">解除警报</a></li>
						</ul>
					</div>
					<div id="model">
					<input id="HResultDate" type="text" class="ui-datepicker-time" readonly value="" />
                    <button style="font-family:Microsoft Yahei;  top: -30px;"onclick="evenWarn()">搜索消息</button>
						<ul id="htcList4"></ul>
						<div id="Pagination4" class="pagination">
							<!-- 这里显示分页 -->
						</div>
						<ul id="Searchresult4" style="display:none;"></ul>
					</div>
					<div class="rank">
						<div class="box_chart"></div>
						<div class="rank_bottom">
							<div class="list_tit">
								<span class="tit_index">排名</span> <span class="tit_word">关键词</span> <span class="tit_value">搜索指数</span>
							</div>
							<ul class="i_list" id="oldWarn_rank_ul">

							</ul>
						</div>

					</div>
				</div>
				<div style="clear:both"></div>
			</div>

		</div>
<div style='clear:both;'></div>


	</div>

	<div id="dialogBg"></div>
	<div id="dialog" class="animated">
		<img class="dialogIco" width="50" height="50" src="images/ico1 (1).png" alt="" />
		<div class="dialogTop">
			<a href='javascript:closwDiaglog()' class="claseDialogBtn">关闭</a>
		</div>
		<div class="plus-tag tagbtn clearfix" id="myTags"></div>
		<div class="plus-tag-add">		
			<!---------设置联合关键词  start--------->
			<div id="unionKeyword_management" style="font-family:Microsoft Yahei; display: none">
				<div style="padding-left:26px;" class="main">
					<div>
						<input type="text" id='unionKeyword_serchValue' style='margin-left: 14px;' maxlength="30" placeholder='请输入关键字'>
						<button class="Button RedButton" style="font-family:Microsoft Yahei;  margin-left: 217px;margin-top: -101px;" onclick="serchUnionKeywordList()">搜索</button>
						<button class="Button RedButton" style="font-family:Microsoft Yahei; margin-left: 317px;margin-top: -101px;" onclick="add_unionKeyword()">添加</button>
					</div>
					<div id='tableUnionKeyword_div' style="float:left;width: 98%;margin-bottom:20px;  margin-left: 10px;">
						<table style="" id='tableUnionKeyword' class="display" cellspacing="0" width="98%">
							<thead>
								<tr>
									<td>关键词</td>
									<td>时间</td>
									<td>操作</td>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<!--------设置联合关键词  end--------->
			<!---------设置最新关键词  start---------------------------------------------------------------------->
			<div id="newKeyword_management" style="font-family:Microsoft Yahei; display: none">
				<div style="padding-left:26px;" class="main">
					<div>
						<input type="text" id='newKeyword_serchValue' style='margin-left: 14px;' maxlength="30" placeholder='请输入关键字'>
						<button class="Button RedButton" style="font-family:Microsoft Yahei;  margin-left: 217px;margin-top: -101px;" onclick="serchVaguekeywords()">搜索</button>
						<button class="Button RedButton" style="font-family:Microsoft Yahei; margin-left: 317px;margin-top: -101px;" onclick="addnewkeyword()">添加</button>
					</div>
					<div id='tablenewKeyword_div' style="float:left;width: 98%;margin-bottom:20px;  margin-left: 10px;">
						<table style="" id='tablenewKeyword' class="display" cellspacing="0" width="98%">
							<thead>
								<tr>
									<td>关键词</td>
									<td>时间</td>
									<td>操作</td>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<!---------设置最新关键词  end----------------------------------------------------------------------->
			<!---------设置敏感关键词  start---------------------------------------------------------------------->
			<div id="sensitiveKeyword_management" style="font-family:Microsoft Yahei; display: none">
				<div style="padding-left:26px;" class="main">
					<div>
						<input type="text" id='sensitiveKeyword_serchValue' style='margin-left: 14px;' maxlength="30" placeholder='请输入关键字'>
						<button class="Button RedButton" style="font-family:Microsoft Yahei;  margin-left: 217px;margin-top: -101px;" onclick="serchKeywordList()">搜索</button>
						<button class="Button RedButton" style="font-family:Microsoft Yahei; margin-left: 317px;margin-top: -101px;" onclick="addSensitiveKeyword()">添加</button>
					</div>
					<div id='tableKeyword_div' style="float:left;width: 98%;margin-bottom:20px;  margin-left: 10px;">
						<table style="" id='tableKeyword' class="display" cellspacing="0" width="98%">
							<thead>
								<tr>
									<td>关键词</td>
									<td>时间</td>
									<td>操作</td>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<!---------设置敏感关键词  end----------------------------------------------------------------------->
			<!---------设置网址  start--------->
			<div id="netAddr_management" style="font-family:Microsoft Yahei;display: none">
				<div style="padding-left:26px;" class="main">
					<div>						
						<input type="text" id='netAddr_serchValue' style='margin-left: 14px;' maxlength="30" placeholder='请输入关键字'>
						<button class="Button RedButton" style="font-family:Microsoft Yahei;  margin-left: 217px;margin-top: -101px;" onclick="serchNetAddr()">搜索</button>
						<button class="Button RedButton" style="font-family:Microsoft Yahei; margin-left: 317px;margin-top: -101px;" onclick="add_netAddr()">添加</button>


					</div>
					<div id='tableNetAddr_div' style="float:left;width: 98%;margin-bottom:20px;  margin-left: 10px;">
						<table style="" id='tableNetAddr' class="display" cellspacing="0" width="98%">
							<thead>
								<tr>
									<td>关键词</td>
									<td>时间</td>
									<td>操作</td>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<!---------设置网址  end--------->
		</div>
	</div>




	<!-- 公用蒙层  rz-->
	<div id="g_Mongolialayer" style="position:fixed;top: 0px; opacity: 0.6; left: 0px; width: 100%; height: 200%; z-index: 5000;  background: rgb(119, 119, 119);display:none;"></div>

	<!-- ----------公用------修改、添加 蒙层div----------start--------------- -->
	<div id="HiddenBgDiv"></div>
	<!-- ----------------关键字修改、添加蒙层div----------end------------- -->


	<div class="bottom">
		<p>版权所有&nbsp;：&nbsp;河北科技大学</p>
		<p>技术支持&nbsp;：&nbsp;河北科技大学大数据与社会计算机研究中心</p>
	</div>
<!-- 插入日期input--下拉div--START--------======================================------------- -->
 <div class="ui-datepicker-css" style="  top: 325px;left: 287px;">	
    <div class="ui-datepicker-quick">
        <p>快捷日期<a class="ui-close-date">X</a></p>
        <div>
            <input class="ui-date-quick-button" type="button" value="今天" alt="0"  name=""/>
            <input class="ui-date-quick-button" type="button" value="昨天" alt="-1" name=""/>
            <input class="ui-date-quick-button" type="button" value="7天内" alt="-6" name=""/>
            <input class="ui-date-quick-button" type="button" value="14天内" alt="-13" name=""/>
            <input class="ui-date-quick-button" type="button" value="30天内" alt="-29" name=""/>
            <input class="ui-date-quick-button" type="button" value="60天内" alt="-59" name=""/>
        </div>
    </div>
    <div class="ui-datepicker-choose" >
        <p>自选日期</p>
        <div class="ui-datepicker-date">
            <input name="startDate" id="startDate" class="startDate" readonly value="2014/12/20" type="text">
           -
            <input name="endDate" id="endDate" class="endDate" readonly  value="2014/12/20" type="text" disabled onChange="datePickers()">
        
        </div>
    </div>
</div>
<!-- 插入日期input--下拉div--END---------==================================------------ -->
	<%-- //页面弹出消息框，相当于alert--%>
	<div id="dialog-message" title='提示信息'>
		<p></p>
	</div>
	<%-- //页面弹出确认消息框，相当于confirm--%>
	<div id="dialog-confirm" title='提示确认信息框'>
		<p></p>
	</div>
	<script type="text/javascript">
		/**
		 * 设置选择On，背景色变蓝色
		 */
		$(".main-page .nav div").mouseenter(function() {
			var $this = $(this);
			var index = $this.index();
		}).mouseleave(function() {
			var $this = $(this);
			var index = $this.index();
		}).click(function() {
			var $this = $(this);
			var index = $this.index();
			var l = -(index * 368);
			$(".main-page .nav div").removeClass("on");
			$(".main-page .nav div").eq(index).addClass("on");
			//$(".main-page .content div:eq(0)").stop().animate({ "margin-top": l }, 500);
		});
	</script>
	<!--隐藏的报警音乐-->
	<input type="hidden" value="Cut Down" onclick="tocutdown()" />
<!-- <input type="button" value="Finish" onclick="finish()" /> -->
 <!-- <p><a href="music/BEEP.mp3" class="sm2_button">Walking</a> Walking</p> -->
</body>
</html>
