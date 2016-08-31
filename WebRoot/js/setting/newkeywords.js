/**
 * 点击设置最新关键词
 */
function setNewKey_click() {
	// 显示弹框
	$('#dialogBg').fadeIn(300);
	var className = "aaa_setNewKeywords";
	$('#dialog').removeAttr('class').addClass('animated ' + className + '').fadeIn();
	$("#newKeyword_serchValue").val("");// 恢复空值
	$("#newKeyword_management").css("display", "block");// 显示
	serchkeywords();// 查询最新关键词
}

/**
 * 查找全部最新关键词
 * 
 * @param
 */
function serchkeywords() {
	var Newkeyword = document.getElementById("newKeyword_serchValue").value;
	$.ajax({
		async : false,
		dataType : "json",
		data : "Newkeyword=" + Newkeyword,
		type : "post",
		url : "findNewkeywords.action",
		success : suckeywords,
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");			
		}
	});

}

/**
 * 成功 查找关键词成功
 * 
 * @param json
 */

function suckeywords(json) {
	var message = json.g_message;
	if (message == "success") {
		var list = json.listNewkeywords;
		if (list != null && list.length > 0) {
			list_size=list.length;
			$("#tablenewKeyword").remove();
			$("#tablenewKeyword_length").remove();// 移除分页大小
			$("#tablenewKeyword_wrapper").remove();

			$("#tablenewKeyword_div").append(
					"<table id='tablenewKeyword' class='display' cellspacing='0' width='98%'>"
							+ "<thead><tr>" + "<td style='width:30%;'>关键词</td>"
							+ "<td style='width:30%;'>时间</td>"
							+ "<td style='width:30%;'>操作</td>"
							+ "</tr></thead></table>");

			for (var i = 0; i < list.length; i++) {
				var ctime = list[i].ctime.replace("T", " ");
				$("#tablenewKeyword").append("<tr class='tablenewKeyword_TableTr'><td>"
										+ list[i].keyWord+ "</td><td>"
										+ ctime+ "</td><td>" 
										+"<a  href='javascript:newKeywordModify(\""+ list[i].cindex+ "\")' >修改</a>&nbsp&nbsp&nbsp&nbsp"
										+"<a  href='javascript:newKeywordDelete(\""+ list[i].cindex	+ "\")'>删除</a>&nbsp&nbsp&nbsp&nbsp"
										+ "</td></tr>");
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
	var tableFirst = $('#tablenewKeyword').dataTable({
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
		// "bSort" : true, //是否启动各个字段的排序功能
		 "aaSorting" : [[1, "desc"]], //默认的排序方式，第1列，升序排列
		"bPaginate" : true,// 分页按钮
		"bFilter" : false,// 搜索栏
		"bLengthChange" : true
	// 每行显示记录数
	});
	// var table = $('#tableManangCircle').DataTable();
	// 删除需要---------
	$('#tablenewKeyword tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			tableFirst.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});

	$("#tablenewKeyword_length").remove();// 移除分页大小
}

/**
 * 修改关键词 蒙层div显示
 * 
 * @param json
 */
function ShowDIV_newKeyword() {
	$("#HiddenBgDiv").css("display", "block");
//	$("#DialogDiv").css("top", "100px");
//	$("#DialogDiv").css("display", "block");
	$("#g_Mongolialayer").css("display", "block");// rz add 公用蒙层20150821	
}
/**
 * 修改关键词 蒙层div隐藏
 * 
 */
function closeDiv_newKeyword() {
	$("#HiddenBgDiv").css("display", "none");
	//$("#DialogDiv").css("display", "none");
	$("#g_Mongolialayer").css("display", "none");// rz add 公用蒙层20150821
}

/**
 * 最新关键字 修改
 */
function newKeywordModify(cindex) {
	$.ajax({
		async : false,
		dataType : 'json',
		data : "cindex=" + cindex,
		type : 'post',
		url : "findSingNewkeywords.action",
		success : sucNewkeyword_modify,
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
		}
	});
}

/**
 * 成功 查找指定(cindex)最新关键词 成功
 * 
 * @param json
 */
function sucNewkeyword_modify(json) {
	var message = json.g_message;
	if (message == "success") {
		var list = json.listSingNewkeywords;
		if (list != null) {
			if (list != null && list.length > 0) {
				ShowDIV_newKeyword();
				$("#HiddenBgDiv").html("");
				$("#HiddenBgDiv").append(" <div id='DialogDiv' style='opacity:0.9;z-index: 6000;'>"
										+ "<h2>修改最新关键词<a id='btnClose' onclick='closeDiv_newKeyword()'style='margin-top: 0px;cursor:pointer;'>关闭</a></h2>"
										+ "<div class='form' id='form' style=''></div></div>");

				for (var i = 0; i < list.length; i++) {
					$("#form").append("<p><center style='margin-top: 60px;'><strong style='vertical-align:top; font-size: 20px;'>关键词：</strong>" +
							"<input type='text' id='keyword_modify'  style='border: 0px; background-color: #ADD1F9; width:150px;' value='"
							+ list[i].keyWord+ "'></center></p><input type='hidden' id='cindex_modify'value='"
							+ list[i].cindex+ "' ><input style='float:right;margin-right: 220px;margin-top: 20px;' type='button' class='Button RedButton' value='保存' onclick='keywordModifyInfo()' />");
				}

			}
		}
	} else if (message == "nodata") {
		sAlert("温馨提示:", "<Br>对不起，没有数据！！！");
		return;
	} else {

	}

}

/**
 * 保存最新关键字的修改
 * 
 * @param Newkeyword
 * @param cindex
 */
function keywordModifyInfo() {
	var Newkeyword = $("#keyword_modify").val();
	var cindex = $("#cindex_modify").val();
	if (Newkeyword != "undefined" && Newkeyword != "" && cindex != "undefined" && cindex != "") {
		$.ajax({
			async : false,
			dataType : 'json',
			data : "Newkeyword=" + Newkeyword + "&cindex=" + cindex,
			type : 'post',
			url : "modifykeywordInfo.action",
			success : function(json) {
				if (json.g_message == "success") {
					closeDiv_newKeyword();// 关闭
					sAlert("温馨提示:", "<Br>最新关键字修改成功！！！");
					serchkeywords();
				} else {
					sAlert("温馨提示:", "<Br>最新关键字修改失败！！！");
				}

			},
			error : function() {
				sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
			}
		});
	}
}

/**
 * 最新关键字删除
 */
function newKeywordDelete(cindex) {
	$.ajax({
		async : false,
		dataType : 'json',
		data : "cindex=" + cindex,
		type : 'post',
		url : "DeleteNewkeywords.action",
		success : function(json) {
			if (json.g_message == "success") {
				var table = $('#tablenewKeyword').DataTable();
				table.row('.selected').remove().draw(false);
				sAlert("温馨提示:", "<Br>最新关键词删除成功！！！");
				serchkeywords();
			} else {
				sAlert("温馨提示:", "<Br>最新关键词删除失败！！！");
			}

		},
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
		}
	});
}

/**
 * 添加最新关键字 蒙层
 * 
 * @param
 * @param
 */
function addnewkeyword() {
	ShowDIV_newKeyword();
//	$("#DialogDiv").remove();
//	$("#form").remove();
	$("#HiddenBgDiv").html("");
	$("#HiddenBgDiv").append(" <div id='DialogDiv' style='opacity:0.9;z-index: 6000;'>"
							+ "<h2>添加最新关键词<a id='btnClose' onclick='closeDiv_newKeyword()' style='margin-top: 0px;cursor:pointer;'>关闭</a></h2>"
							+ "<div class='form' id='form' style=''></div></div>");

	$("#form").append("<p><center style='margin-top: 60px;'><strong style='vertical-align:top; font-size: 20px;'>关键词：</strong><input type='text' id='keyword_add'  style='border: 0px; background-color: #ADD1F9; width:150px;' value=''></center></p>"
							+ "<center><input style='float:right;margin-right: 220px;margin-top: 20px;' type='button' value='保存' class='Button RedButton' onclick='addnewkeywordInfo()' /></center>");

}

/**
 * 添加最新关键字
 * 
 * @param Newkeyword
 * @param cindex
 */
function addnewkeywordInfo() {
	var Newkeyword = $("#keyword_add").val();
	if (Newkeyword != "undefined" && Newkeyword != "") {
		$.ajax({
			async : false,
			dataType : 'json',
			data : "Newkeyword=" + Newkeyword,
			type : 'post',
			url : "addnewkeywordInfo.action",
			success : function(json) {
				if (json.g_message == "success") {
					closeDiv_newKeyword();// 关闭这个“DialogDiv”的id的div
					sAlert("温馨提示:", "<Br>最新关键词添加成功！！！");
					serchkeywords();
				} else {
					sAlert("温馨提示:", "<Br>最新关键词添加失败！！！");
				}

			},
			error : function() {
				sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
			}
		});
	}
}