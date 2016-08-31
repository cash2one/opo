
/**
 * 点击设置定网地址
 */
function setNetAddr_click(){
	//显示弹框		
		$('#dialogBg').fadeIn(300);
		var className="aaa_setNetAddr";
		$('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
		$("#unionKeyword_serchValue").val("");//恢复空值
		
		$("#netAddr_management").css("display","block");//显示
		serchNetAddr();//查询定网地址
}
/**
 * 查找全部最新网址
 * 
 * @param
 */
function serchNetAddr() {
	var serchValue = document.getElementById("netAddr_serchValue").value;
	$.ajax({
		async : false,
		dataType : "json",
		data : "g_serchValue=" + serchValue,
		type : "post",
		url : "findNetAddrListFy.action",
		success : sucSerchNetAddr,
		error : function() {			
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
		}
	});

}

/**
 * 成功 查找网址成功
 * 
 * @param json
 */

function sucSerchNetAddr(json) {
	var message = json.g_message;
	if (message == "success") {
		var list = json.listNetAddr;
		if (list != null && list.length > 0) {
			list_size=list.length;
			$("#tableNetAddr").remove();
			$("#tableNetAddr_length").remove();// 移除分页大小
			$("#tableNetAddr_wrapper").remove();

			$("#tableNetAddr_div").append(
					"<table id='tableNetAddr' class='display' cellspacing='0' width='98%'>"
							+ "<thead><tr>" + "<td style='width:30%;'>关键词</td>"
							+ "<td style='width:30%;'>时间</td>"
							+ "<td style='width:30%;'>操作</td>"
							+ "</tr></thead></table>");

			for (var i = 0; i < list.length; i++) {
				var ctime = list[i].ctime.replace("T", " ");
				$("#tableNetAddr").append("<tr class='tableNetAddr_TableTr'>"
						+ "<td>"+ list[i].url + "</td>"
						+ "<td>" + ctime+ "</td>"
						+ "<td>"+ "<a  href='javascript:findSingNetAddr(\""+ list[i].cindex+ "\")'>修改</a>&nbsp&nbsp&nbsp&nbsp"
								+ "<a  href='javascript:netAddrDelete(\""+ list[i].cindex+ "\")'>删除</a>&nbsp&nbsp&nbsp&nbsp" + "</td>"
								+ "</tr>");
			}

		}
	} else if (message == "nodata") {		
		if(list_size>1){
			sAlert("温馨提示:","<Br>对不起，没有数据！！！");	
		}
		return;
	} else {
		return;
	}

	var pageSize = 5;
	var tableFirst = $('#tableNetAddr').dataTable({
		"oLanguage" : { // 汉化
			"sProcessing" : "处理中...",
			"sLengthMenu" : "显示 _MENU_ 项结果",
			"sZeroRecords" : "没有匹配结果",
			"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
			"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
			"sInfoPostFix" : "",
			"sSearch" : "搜索:",
			"sUrl" : "",
			"sEmptyTable" : "表中数据为空",
			"sLoadingRecords" : "载入中...",
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上页",
				"sNext" : "下页",
				"sLast" : "末页"
			}

		},
		"aLengthMenu" : [ 10, 20, 40 ], // 更改显示记录数选项
		"iDisplayLength" : pageSize, // 默认显示的记录数
		"bPaginate" : true, // 是否显示（应用）分页器
		"bInfo" : true, // 是否显示页脚信息，DataTables插件左下角显示记录数
		"sPaginationType" : "full_numbers", // 详细分页组，可以支持直接跳转到某页
		 "bSort" : true, //是否启动各个字段的排序功能
		 "aaSorting" : [[1, "desc"]], //默认的排序方式，第1列，升序排列
		"bPaginate" : true,// 分页按钮
		"bFilter" : false,// 搜索栏
		"bLengthChange" : true
	// 每行显示记录数
	});
	// var table = $('#tableManangCircle').DataTable();
	// 删除需要---------
	$('#tableNetAddr tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			tableFirst.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});

	$("#tableNetAddr_length").remove();// 移除分页大小
}
/**
 * 修改或添加定网网址  蒙层div显示
 */
function showDiv_netAddr() {
	 $("#HiddenBgDiv").css("display", "block"); 	
	 $("#DialogDiv").css("top", "100px");
	 $("#DialogDiv").css("display", "block");
	 $("#g_Mongolialayer").css("display", "block");//rz add 公用蒙层20150821   
}
/**
 * 修改或添加定网网址   蒙层div隐藏
 * 
 */
function closeDiv_netAddr() {
	  $("#HiddenBgDiv").css("display", "none");
	  $("#DialogDiv").css("display", "none");
	  $("#g_Mongolialayer").css("display", "none");//rz add 公用蒙层20150821
}
/**
 * 单个网址 点击修改
 */
function findSingNetAddr(cindex) {
	$.ajax({
		async : false,
		dataType : 'json',
		data : "g_id=" + cindex,
		type : 'post',
		url : "findSingNetAddr.action",
		success : sucFindSingNetAddr,
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
		}
	});
}

/**
 * 单个网址 点击修改 查询成功
 * 
 * @param json
 */
function sucFindSingNetAddr(json) {
	var message = json.g_message;
	if (message == "success") {
		var list = json.listNetAddr;
		if (list != null && list.length > 0) {
			showDiv_netAddr();
			$("#HiddenBgDiv").html("");
			$("#HiddenBgDiv").append(" <div id='DialogDiv' style='opacity:0.9;z-index: 6000;'>"
					 +"<h2>修改定网网址<a id='btnClose' onclick='closeDiv_netAddr()' style='margin-top: 0px;cursor:pointer;'>关闭</a></h2>" 
					 +"<div class='form' id='form_netAddr' ></div></div>" );						
		
			
			
			for (var i = 0; i < list.length; i++) {
				$("#form_netAddr").append("<p><center style='margin-top: 60px;'><strong style='vertical-align:top; font-size: 20px;'>关键词：</strong>" 
						+"<input type='text' id='url_input_netAddr' maxlength='190' style='border: 0px; background-color: #ADD1F9; width: 236px;' value='"+list[i].url
						+"'></center></p><input type='hidden' id='cindex_input_netAddr' value='"+list[i].cindex
						+"' ><input style='float:right;margin-right: 220px;margin-top: 20px;' type='button' class='Button RedButton' value='保存' onclick='checkUrl_modify()' />");
			
			}
		}
	} else if (message == "nodata") {
		sAlert("温馨提示:", "<Br>对不起，没有数据！！！");
		return;
	} else {
		sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
	}
}
/**
 * 修改url 前点击保存时验证
 */
function checkUrl_modify() {
	var url = document.getElementById("url_input_netAddr").value;		
	var true_false = CheckUrl(url);
	if(true_false){
		modify_netAddr();//修改
	}else{
		sAlert("温馨提示:", "<Br>格式不对！！！");
	}
}
/**
 * 添加url 前点击保存时验证
 */
function checkUrl_save() {
	var url = document.getElementById("url_input_netAddr").value;		
	var true_false = CheckUrl(url);
	if(true_false){
		save_netAddr();//添加
	}else{
		sAlert("温馨提示:", "<Br>格式不对！！！");
	}
}
/**
 * check url
 * @param str
 * @returns {Boolean}
 */
function CheckUrl(str_url) { 
	     /*  var strRegex = "^((https|http|ftp|rtsp|mms)?://)"  
	       + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@  
	       + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184  
	       + "|" // 允许IP和DOMAIN（域名） 
	       + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.  
	       + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名  
	       + "[a-z]{2,6})" // first level domain- .com or .museum  
	       + "(:[0-9]{1,4})?" // 端口- :80  
	       + "((/?)|" // a slash isn't required if there is no file name  
	       + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$"; */
	       var strRegex ="(https?|http|ftp|mms):\/\/([A-z0-9]+[_\-]?[A-z0-9]+\.)*[A-z0-9]+\-?[A-z0-9]+\.[A-z]{2,}(\/.*)*\/?";	      
	       var re=new RegExp(strRegex);  
	       if (re.test(str_url)){ 
	           return (true);  
	       }else{  
	           return (false);  
	       } 
} 


/**
 * 保存最新网址的修改
 */
function modify_netAddr() {
	var url = $("#url_input_netAddr").val();
	var cindex = $("#cindex_input_netAddr").val();
	if (url != "undefined" && url != "" && cindex != "undefined" && cindex != "") {
		$.ajax({
			async : false,
			dataType : 'json',
			data : "url=" + url + "&g_id=" + cindex,
			type : 'post',
			url : "modify_netAddr.action",
			success : function(json) {
				if (json.g_message == "success") {	
					closeDiv_netAddr();//关闭
					sAlert("温馨提示:", "<Br>修改成功！！！");
					serchNetAddr();
				} else {					
					sAlert("温馨提示:", "<Br>修改失败！！！");
				}

			},
			error : function() {
				sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");				
			}
		});
	}else{
		sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
	}
}
/**
 * 点击添加 定位网址 蒙层
 */
function add_netAddr() {
	showDiv_netAddr();
	$("#HiddenBgDiv").html("");
	$("#HiddenBgDiv").append(" <div id='DialogDiv' style='opacity:0.9;z-index: 6000;'>"
							+ "<h2>添加定位网址<a id='btnClose' onclick='closeDiv_netAddr()' style='margin-top: 0px;cursor:pointer;'>关闭</a></h2>"
							+ "<div class='form' id='form_netAddr' style=''></div></div>");
	$("#form_netAddr").append("<p><center style='margin-top: 60px;'><strong style='vertical-align:top; font-size: 20px;'>url：</strong>" 
			+"<input type='text' id='url_input_netAddr' maxlength='190' style='border: 0px; background-color: #ADD1F9;width: 236px;' value=''></center></p>"
			+ "<center><input style='float:right;margin-right: 220px;margin-top: 20px;' type='button' value='保存' class='Button RedButton' onclick='checkUrl_save()' /></center>");

}
/**
 * 添加最新网址
 */
function save_netAddr() {
	var url = $("#url_input_netAddr").val();
	if (url != "undefined" && url != "") {
		$.ajax({
			async : false,
			dataType : 'json',
			data : "url=" + url,
			type : 'post',
			url : "save_netAddrInfo.action",
			success : function(json) {
				if (json.g_message == "success") {					
					closeDiv_netAddr();//关闭
					sAlert("温馨提示:", "<Br>网址添加成功");
					serchNetAddr();
				} else {
					sAlert("温馨提示:", "<Br>网址添加失败");
					
				}

			},
			error : function() {
				sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
			}
		});
	}
}
/**
 * 最新网址删除
 */
function netAddrDelete(cindex) {
	$.ajax({
		async : false,
		dataType : 'json',
		data : "g_id=" + cindex,
		type : 'post',
		url : "deleteNetAddrOne.action",
		success : function(json) {
			if (json.g_message == "success") {				
				sAlert("温馨提示:", "<Br>删除成功");
				var table = $('#tableNetAddr').DataTable();
				table.row('.selected').remove().draw(false);
				serchNetAddr();
			} else {				
				sAlert("温馨提示:", "<Br>删除失败");
			}

		},
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
		}
	});
}