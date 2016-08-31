$(document).ready(function() {
	g_rankBaiduTopCout();//公用的右边数据查询（排名，关键字，搜索指数）
});
/**
 * 查询网络热点
 */
function networkHot() {
	$("#Searchresult").html("");
	var serchValue = "1";// 网络热点
	$.ajax({
		async : false,
		dataType : 'json',
		data : "g_serchValue=" + serchValue,
		type : 'post',
		url : "findBaiduHot50ByTime.action",
		success : function(json) {
			if (json.g_message == "success") {
				var list = json.listTTopKeyWord;
				if (list != null && list.length > 0) {
					for (var i = 0; i < list.length; i++) {
						var ctime = (list[i].ctime).substr(0, 10);
						$("#Searchresult").append(
								"<li>" + "<p class=\"p1\">" + list[i].keyWord
										+ "</p>" + "<p class=\"p2\">" + ctime
										+ "</p>" + "</li>");
					}
				}
				var num_entries = $("#Searchresult li").length;
				var showCount = 10;
				$("#total").html(
						"一共有 " + num_entries + " 条数据 每页显示" + showCount + "条");
				var initPagination = function() {

					// 创建分页
					$("#Pagination").pagination(num_entries, {
						num_edge_entries : 1, // 边缘页数
						num_display_entries : 6, // 主体页数
						callback : pageselectCallback,
						items_per_page : showCount
					// 每页显示1项
					});
				}();
				function pageselectCallback(page_index, jq) {
					var max_elem = Math.min((page_index + 1) * showCount,
							num_entries);
					$("#htcList").html("");
					for (var i = page_index * showCount; i < max_elem; i++) {
						var new_content = $("#Searchresult li:eq(" + i + ")")
								.clone();
						$("#htcList").append(new_content); // 装载对应分页的内容
					}
					return false;
				}
			} else {
				sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
			}

		},
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
		}
	});
	
}

/**
 * 查询网络热点（通过自选日期）
 */
function networkHot01() {
	$("#Searchresult").html("");
	//var serchValue = "1";// 网络热点
	var dateTime=document.getElementById('topKeywordDate').value;
	$.ajax({
		async : false,
		dataType : 'json',
		data : "topKeywordDate=" + dateTime,
		type : 'post',
		url : "findTopKeyword.action",
		success : function(json) {
			if (json.g_message == "success") {
				var list = json.topKeywordList;
				var max=0;
				if (list != null && list.length > 0) {
					if(list.length>78){
						max=78;
					}else{
						max=list.length;
					}
					for (var i = 0; i < max; i++) {
						var ctime = (list[i].ctime).substr(0, 10);
						$("#Searchresult").append(
								"<li>" + "<p class=\"p1\">" + list[i].keyWord
										+ "</p>" + "<p class=\"p2\">" + ctime
										+ "</p>" + "</li>");
					}
				}			
				var num_entries = $("#Searchresult li").length;
				var showCount = 10;
				$("#total").html(
						"一共有 " + num_entries + " 条数据 每页显示" + showCount + "条");
				var initPagination = function() {

					// 创建分页
					$("#Pagination").pagination(num_entries, {
						num_edge_entries : 1, // 边缘页数
						num_display_entries : 6, // 主体页数
						callback : pageselectCallback,
						items_per_page : showCount
					// 每页显示1项
					});
				}();
				function pageselectCallback(page_index, jq) {
					var max_elem = Math.min((page_index + 1) * showCount,
							num_entries);
					$("#htcList").html("");
					for (var i = page_index * showCount; i < max_elem; i++) {
						var new_content = $("#Searchresult li:eq(" + i + ")")
								.clone();
						$("#htcList").append(new_content); // 装载对应分页的内容
					}
					return false;
				}
			}else if(json.g_message == "nodata"){
				
				sAlert("温馨提示:", "<Br>对不起，没有数据！！！");
			}else{
				sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
			}

		},
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
		}
	});
}




/**
 * 查询定网预警
 */
function networkWarn() {
	$("#Searchresult1").html("");
	
	var dateTime=document.getElementById('netAddrsWarnDate').value;
	//var g_size=78;
	$.ajax({
		async : false,
		dataType : 'json',
		data : "topKeywordDate=" + dateTime,
		type : 'post',
		url : "findNetAddrsWarnG_sizeByTwoTime.action",
		success : function(json) {
			if (json.g_message == "success") {
				var list = json.netAddrWarnList;
				if (list != null && list.length > 0) {
					$("#Searchresult1").html("");
					for (var i = 0; i < list.length; i++) {
						var ctime = (list[i].ctime).substr(0, 10);
						$("#Searchresult1").append(
								"<li>" + "<p class=\"p1\">" + list[i].keyWord
										+ "</p>" + "<p class=\"p2\">" + ctime
										+ "</p>" + "</li>");
					}
				}
				
				var num_entries = $("#Searchresult1 li").length;
				var showCount = 10;
				$("#total").html("一共有 " + num_entries + " 条数据 每页显示" + showCount + "条");
				var initPagination = function() {

					// 创建分页
					$("#Pagination1").pagination(num_entries, {
						num_edge_entries : 1, // 边缘页数
						num_display_entries : 6, // 主体页数
						callback : pageselectCallback,
						items_per_page : showCount
					// 每页显示1项
					});
				}();
				function pageselectCallback(page_index, jq) {
					var max_elem = Math.min((page_index + 1) * showCount, num_entries);
					$("#htcList1").html("");
					for (var i = page_index * showCount; i < max_elem; i++) {
						var new_content = $("#Searchresult1 li:eq(" + i + ")").clone();
						$("#htcList1").append(new_content); // 装载对应分页的内容
					}
					return false;
				}
			
			}else if(json.g_message == "nodata"){
				sAlert("温馨提示:", "<Br>对不起，没有数据！！！");
			}else{
				sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
			}

		},
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
		}
	});
	
	
	

}
/**
 * 查询当日预警
 */
function todayWarn() {
	$("#Searchresult2").html("");
	//var g_size=78;
	$.ajax({
		async : false,
		dataType : 'json',
		//data : "g_size="+g_size,
		type : 'post',
		url : "findAlarmLookInfo.action",
		success : function(json) {
			if (json.g_message == "success") {
				var list = json.listLookAlarm;
				if (list != null && list.length > 0) {
					for (var i = 0; i < list.length; i++) {
						var ctime = (list[i].ctime).substr(0, 10);
						$("#Searchresult2").append(
								"<li>" + "<p class=\"p1\">" + list[i].keyWord
										+ "</p>" + "<p class=\"p2\">" + ctime
										+ "</p>" + "</li>");
					}
				}
	
	var num_entries = $("#Searchresult2 li").length;
	var showCount = 10;
	$("#total").html("一共有 " + num_entries + " 条数据 每页显示" + showCount + "条");
	var initPagination = function() {

		// 创建分页
		$("#Pagination2").pagination(num_entries, {
			num_edge_entries : 1, // 边缘页数
			num_display_entries : 6, // 主体页数
			callback : pageselectCallback,
			items_per_page : showCount
		// 每页显示1项
		});
	}();
	function pageselectCallback(page_index, jq) {
		var max_elem = Math.min((page_index + 1) * showCount, num_entries);
		$("#htcList2").html("");
		for (var i = page_index * showCount; i < max_elem; i++) {
			var new_content = $("#Searchresult2 li:eq(" + i + ")").clone();
			$("#htcList2").append(new_content); // 装载对应分页的内容
		}
		return false;
	}

}
			else if(json.g_message == "nodata"){
				sAlert("温馨提示:", "<Br>对不起，没有数据！！！");
			}else{
				sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
			}

		},
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
		}
	});
	
	
	

}
/**
 * 查询最新消息
 */
/*function newMessage_old() {
	$("#Searchresult3").html("");
	var dateTime=document.getElementById('RResultDate').value;
	//var serchValue = "1";// 网络热点
	$.ajax({
		async : false,
		dataType : 'json',
		data : "RResultDate=" + dateTime,
		type : 'post',
		url : "findRResult.action",
		success : function(json) {
			if (json.g_message == "success") {						
				var list = json.rresultList;
				//console.log(list);
				if (list != null && list.length > 0) {
					for (var i = 0; i < list.length; i++) {
						var ctime = (list[i].publishTime).substr(0, 10);
						$("#Searchresult3").append(
								"<li>" + "<p class=\"p1\">" + list[i].keyWord
										+ "</p>" + "<p class=\"p2\">" + ctime
										+ "</p>" + "</li>");
					}
					
					var num_entries = $("#Searchresult3 li").length;
					var showCount = 15;
					$("#total").html("一共有 " + num_entries + " 条数据 每页显示" + showCount + "条");
					var initPagination = function() {

						// 创建分页
						$("#Pagination3").pagination(num_entries, {
							num_edge_entries : 1, // 边缘页数
							num_display_entries : 6, // 主体页数
							callback : pageselectCallback,
							items_per_page : showCount
						// 每页显示1项
						});
					}();
					function pageselectCallback(page_index, jq) {
						var max_elem = Math.min((page_index + 1) * showCount, num_entries);
						$("#htcList3").html("");
						for (var i = page_index * showCount; i < max_elem; i++) {
							var new_content = $("#Searchresult3 li:eq(" + i + ")").clone();
							$("#htcList3").append(new_content); // 装载对应分页的内容
						}
						return false;
					}
					
				}
			
			}
			
		
		},
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
		}
	});
		
}*/
/**
 * 查询百度搜索根据最新关键字设置来的最新消息
 * @ rz
 */
function newMessage() {
	$("#Searchresult3").html("");
	var dateTime=document.getElementById('RResultDate').value;
	$.ajax({
		async : false,
		dataType : 'json',
		data : "RResultDate=" + dateTime,
		type : 'post',
		url : "findBaiduNewsSerch.action",
		success : function(json) {
			if (json.g_message == "success") {						
				var list = json.tBaiduNewsSerchsList;				
				if (list != null && list.length > 0) {
					for (var i = 0; i < list.length; i++) {
						var ctime = (list[i].publicTime).substr(0, 10);
						$("#Searchresult3").append(
								"<li>" + "<p class=\"p1\">" + list[i].title
										+ "</p>" + "<p class=\"p2\">" + ctime
										+ "</p>" + "</li>");
					}
					
					var num_entries = $("#Searchresult3 li").length;
					var showCount = 10;
					$("#total").html("一共有 " + num_entries + " 条数据 每页显示" + showCount + "条");
					var initPagination = function() {

						// 创建分页
						$("#Pagination3").pagination(num_entries, {
							num_edge_entries : 1, // 边缘页数
							num_display_entries : 6, // 主体页数
							callback : pageselectCallback,
							items_per_page : showCount
						// 每页显示1项
						});
					}();
					function pageselectCallback(page_index, jq) {
						var max_elem = Math.min((page_index + 1) * showCount, num_entries);
						$("#htcList3").html("");
						for (var i = page_index * showCount; i < max_elem; i++) {
							var new_content = $("#Searchresult3 li:eq(" + i + ")").clone();
							$("#htcList3").append(new_content); // 装载对应分页的内容
						}
						return false;
					}
					
				}
			
			}else if(json.g_message == "nodata"){
				sAlert("温馨提示:", "<Br>对不起，没有数据！！！");
			}else{
				sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
			}
			
		
		},
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
		}
	});	
}
/**
 * 查询往日预警
 */
function evenWarn() {
	
	$("#Searchresult4").html("");
	var dateTime=document.getElementById('HResultDate').value;
	$.ajax({
		async : false,
		dataType : 'json',
		data : "HResultDate=" + dateTime,
		type : 'post',
		url : "findHResult.action",
		success : function(json) {
			if (json.g_message == "success") {						
				var list = json.hresultList;
				//console.log(list);
				if (list != null && list.length > 0) {
					for (var i = 0; i < list.length; i++) {
						var ctime = (list[i].ctime).substr(0, 10);
						$("#Searchresult4").append(
								"<li>" + "<p class=\"p1\">" + list[i].keyWord
										+ "</p>" + "<p class=\"p2\">" + ctime
										+ "</p>" + "</li>");
					}
					
					
					
					var num_entries = $("#Searchresult4 li").length;
					var showCount = 10;
					$("#total").html("一共有 " + num_entries + " 条数据 每页显示" + showCount + "条");
					var initPagination = function() {

						// 创建分页
						$("#Pagination4").pagination(num_entries, {
							num_edge_entries : 1, // 边缘页数
							num_display_entries : 6, // 主体页数
							callback : pageselectCallback,
							items_per_page : showCount
						// 每页显示1项
						});
					}();
					function pageselectCallback(page_index, jq) {
						var max_elem = Math.min((page_index + 1) * showCount, num_entries);
						$("#htcList4").html("");
						for (var i = page_index * showCount; i < max_elem; i++) {
							var new_content = $("#Searchresult4 li:eq(" + i + ")").clone();
							$("#htcList4").append(new_content); // 装载对应分页的内容
						}
						return false;
					}
					
				}
			
			}else if(json.g_message == "nodata"){
				sAlert("温馨提示:", "<Br>对不起，没有数据！！！");
			}else{
				sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
			}
			
		
		},
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
		}
	});

}
/**
 * 设置
 */
function settingFour() {

}
/**
 * 首页
 */
function firstPage() {

}
/**
 * 网络热点，点网预警，当日预警，最新消息，往日预警公用的右边数据查询（排名，关键字，搜索指数）
 */
function g_rankBaiduTopCout() {
	var g_size = "13";// 13条
	var type = "1";// 热点
	$.ajax({
		async : false,
		dataType : "json",
		data : "g_size=" + g_size + "&g_serchValue=" + type,
		type : "post",
		url : "findBauduHotG_sizeByTime.action",
		success : sucFindBauduHotG_sizeByTime,
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
		}
	});

}
/**
 * 网络热点，点网预警，当日预警，最新消息，往日预警公用的右边数据查询（排名，关键字，搜索指数）
 * 成功查询
 * @param json
 */
function sucFindBauduHotG_sizeByTime(json) {
	var message = json.g_message;
	if (message == "success") {
		var list = json.listTTopKeyWord;		
		for (var i = 0; i < list.length; i++) {	
			if(i<3){
				//网络热点右侧排名
				$("#netHot_rank_ul").append(
						"<li><div class='list_hd'>" 
						+ "<span class='num_top'>" + (i+1)+"</span>"
						+ "<span class='list_name'>" + list[i].keyWord+"</span>"
						+ "<span class='click_num'>" + list[i].count+"</span>"
								+ "</div></li>");
				//定网预警右侧排名
				$("#netAddr_rank_ul").append(
						"<li><div class='list_hd'>" 
						+ "<span class='num_top'>" + (i+1)+"</span>"
						+ "<span class='list_name'>" + list[i].keyWord+"</span>"
						+ "<span class='click_num'>" + list[i].count+"</span>"
								+ "</div></li>");
				//当日预警右侧排名
				$("#todayWarn_rank_ul").append(
						"<li><div class='list_hd'>" 
						+ "<span class='num_top'>" + (i+1)+"</span>"
						+ "<span class='list_name'>" + list[i].keyWord+"</span>"
						+ "<span class='click_num'>" + list[i].count+"</span>"
								+ "</div></li>");
				//最新消息右侧排名
				$("#newest_rank_ul").append(
						"<li><div class='list_hd'>" 
						+ "<span class='num_top'>" + (i+1)+"</span>"
						+ "<span class='list_name'>" + list[i].keyWord+"</span>"
						+ "<span class='click_num'>" + list[i].count+"</span>"
								+ "</div></li>");
				//往日预警右侧排名
				$("#oldWarn_rank_ul").append(
						"<li><div class='list_hd'>" 
						+ "<span class='num_top'>" + (i+1)+"</span>"
						+ "<span class='list_name'>" + list[i].keyWord+"</span>"
						+ "<span class='click_num'>" + list[i].count+"</span>"
								+ "</div></li>");
				
			}else{
				//网络热点右侧排名
				$("#netHot_rank_ul").append(
						"<li><div class='list_hd'>" 
						+ "<span class='num_top2'>" + (i+1)+"</span>"
						+ "<span class='list_name'>" + list[i].keyWord+"</span>"
						+ "<span class='click_num'>" + list[i].count+"</span>"
								+ "</div></li>");
				//定网预警右侧排名
				$("#netAddr_rank_ul").append(
						"<li><div class='list_hd'>" 
						+ "<span class='num_top2'>" + (i+1)+"</span>"
						+ "<span class='list_name'>" + list[i].keyWord+"</span>"
						+ "<span class='click_num'>" + list[i].count+"</span>"
								+ "</div></li>");
				//当日预警右侧排名
				$("#todayWarn_rank_ul").append(
						"<li><div class='list_hd'>" 
						+ "<span class='num_top2'>" + (i+1)+"</span>"
						+ "<span class='list_name'>" + list[i].keyWord+"</span>"
						+ "<span class='click_num'>" + list[i].count+"</span>"
								+ "</div></li>");
				//最新消息右侧排名
				$("#newest_rank_ul").append(
						"<li><div class='list_hd'>" 
						+ "<span class='num_top2'>" + (i+1)+"</span>"
						+ "<span class='list_name'>" + list[i].keyWord+"</span>"
						+ "<span class='click_num'>" + list[i].count+"</span>"
								+ "</div></li>");
				//往日预警右侧排名
				$("#oldWarn_rank_ul").append(
						"<li><div class='list_hd'>" 
						+ "<span class='num_top2'>" + (i+1)+"</span>"
						+ "<span class='list_name'>" + list[i].keyWord+"</span>"
						+ "<span class='click_num'>" + list[i].count+"</span>"
								+ "</div></li>");
			}
			
		}
		
	} else {
		sAlert("温馨提示:", "<Br>后台抓取数据中！！！");
	}

}
/**
 * 弹窗代码
 */
function sAlert(strTitle, strContent) {
	var msgw, msgh, bordercolor;
	msgw = 300;// 提示窗口的宽度
	msgh = 160;// 提示窗口的高度
	// titleheight=25;//提示窗口标题高度
	bordercolor = "#36648B";// 提示窗口的边框颜色
	titlecolor = "#99CCFF";// 提示窗口的标题颜色

	var sWidth, sHeight;
	sWidth = document.body.offsetWidth;
	sHeight = screen.height;
	var bgObj = document.createElement("div");
	bgObj.setAttribute('id', 'bgDivTwo');
	bgObj.style.position = "absolute";
	bgObj.style.top = "0";
	bgObj.style.background = "#777"; // 浅灰色
	// bgObj.style.background="#00FFFF"; //天空蓝
	// bgObj.style.background="#FF00FF";//高贵紫
	// bgObj.style.background="#99FF00";//自然绿
	// bgObj.style.background="#FFFFFF";

	bgObj.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
	bgObj.style.opacity = "0.6";
	bgObj.style.left = "0";
	// bgObj.style.width=sWidth + "px";
	// bgObj.style.height=sHeight + "px";
	bgObj.style.width = "100%";
	bgObj.style.height = "200%";
	bgObj.style.zIndex = "10000";
	document.body.appendChild(bgObj);

	var msgObj = document.createElement("div");
	msgObj.setAttribute("id", "msgDiv");
	msgObj.setAttribute("align", "center");
	// msgObj.style.background="#DBE4F0";//设置背景色
	msgObj.style.background = "#FFFFFF";// 设置背景色(白色)
	// msgObj.style.background="#CCFFFF";//设置背景色
	// msgObj.style.background="#36648B";//弹窗设置背景色
	msgObj.style.borderRadius = "15px solid " + bordercolor; // border
	msgObj.style.borderRadius = "8px";// 弹窗圆角
	// msgObj.style.position = "absolute";
	msgObj.style.position = "fixed";
	msgObj.style.left = "50%";
	msgObj.style.top = "50%";
	// msgObj.style.font="20px/1.6em Verdana, Geneva, Arial, Helvetica,
	// sans-serif";
	msgObj.style.font = "20px/1.6em verdana,geneva,arial,helvetica,sans-serif,宋体";
	msgObj.style.marginLeft = "-121px";
	msgObj.style.marginTop = -20 + document.documentElement.scrollTop + "px";
	msgObj.style.width = msgw + "px"; // 宽度
	// msgObj.style.height =msgObj.scrollHeight+38;
	msgObj.style.height = msgh + "px";// 高度

	msgObj.style.textAlign = "center";
	msgObj.style.lineHeight = "25px";
	msgObj.style.zIndex = "10001";

	var title = document.createElement("h4");
	title.setAttribute("id", "msgTitle");
	title.setAttribute("align", "right");
	title.style.margin = "0";
	title.style.padding = "0";
	title.style.background = bordercolor; // bordercolor
	title.style.filter = "progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
	title.style.opacity = "0.75";
	title.style.borderRadius = "8px";
	title.style.borderColor = bordercolor;
	title.style.height = "0px";
	title.style.font = "16px Verdana, Geneva, Arial, Helvetica, sans-serif";
	title.style.color = "white"; // 设置“关闭”字体色
	title.style.cursor = "pointer";
	title.title = "点击关闭";
	title.innerHTML = "<table border='0' width='100%'><tr><td align='left'><b>"
			+ strTitle + "</b></td><td>关闭</td></tr></table></div>";
	title.onclick = function() {
		document.body.removeChild(bgObj);
		document.getElementById("msgDiv").removeChild(title);
		document.body.removeChild(msgObj);
	};
	document.body.appendChild(msgObj);
	document.getElementById("msgDiv").appendChild(title);
	var txt = document.createElement("p");
	txt.style.margin = "1em 0";
	txt.setAttribute("id", "msgTxt");
	txt.innerHTML = strContent;
	document.getElementById("msgDiv").appendChild(txt);
}