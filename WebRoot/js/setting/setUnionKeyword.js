var list_size=1;//表格数据
/**
 * 点击设置组合关键词
 */
function setUnionKey_click(){
	//显示弹框		
		$('#dialogBg').fadeIn(300);
		var className="aaa_setUnionKey";
		$('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
		$("#netAddr_serchValue").val("");//恢复空值
		
		$("#unionKeyword_management").css("display","block");//显示
		serchUnionKeywordList();//查询组合关键词
}




/**
 * 查询组合关键词List
 */
function serchUnionKeywordList() {
	
	var serchValue=document.getElementById("unionKeyword_serchValue").value;	
	$.ajax({
		async : false,
		dataType:'json',
		data:"g_serchValue=" + serchValue,			
		type : 'post',
		url : "serchUnionKeywordListFy.action",
		success : sucSerchUnionKeywordList,
		error : function() {	
			sAlert("温馨提示:","<Br>对不起，请重新刷新！！！");
//			$( "#dialog-message" ).children("p").html("对不起，请重新刷新！！！");
//	        $( "#dialog-message" ).dialog("open");
		}
	});
}
/**
 *查询组合关键词List，成功
 * @param json
 */
function sucSerchUnionKeywordList(json) {
	
	var message = json.g_message;	
	if(message == "success"){
		var list=json.listUnionKeyword;
		if(list !=null && list.length > 0){		
			list_size=list.length;
			$("#tableUnionKeyword").remove();
			$("#tableUnionKeyword_length").remove();//移除分页大小
			$("#tableUnionKeyword_wrapper").remove();
			
			$("#tableUnionKeyword_div").append("<table id='tableUnionKeyword' class='display' cellspacing='0' width='98%'>" +
					"<thead><tr>" +
					"<td style='width:30%;'>关键词</td>" +
					"<td style='width:30%;'>时间</td>" +
					"<td style='width:30%;'>操作</td>" +									
					"</tr></thead></table>"
					);
			 
			for (var i = 0; i < list.length; i++) {				
				var ctime = list[i].ctime.replace("T", " ");
				$("#tableUnionKeyword").append("<tr class='tableUnionKeyword_TableTr'>" 
						+"<td>"+list[i].head+"</td>" 
						+"<td>"+ctime+"</td>"										
						+"<td><a  href='javascript:findUnionKeyword(\""+list[i].cindex+"\")'>修改</a>&nbsp&nbsp&nbsp&nbsp" +														
							"<a  href='javascript:delete_unionKeyword(\""+list[i].cindex+"\")'>删除</a>&nbsp&nbsp&nbsp&nbsp"+
							"</td>"
						+"</tr>");
			}	
			
			
		}
	}else if(message == "nodata"){	
		if(list_size>1){
			sAlert("温馨提示:","<Br>对不起，没有数据！！！");	
		}		
        return;
	}else{
		return;
	}	
	
	 var pageSize=5;		 	
	 var tableFirst =$('#tableUnionKeyword').dataTable({ 		  
			"oLanguage" : { // 汉化
				"sProcessing":   "处理中...",
				"sLengthMenu":   "显示 _MENU_ 项结果",
				"sZeroRecords":  "没有匹配结果",
				"sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
				"sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
				"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
				"sInfoPostFix":  "",
				"sSearch":       "搜索:",
				"sUrl":          "",
				"sEmptyTable":     "表中数据为空",
				"sLoadingRecords": "载入中...",
				"sInfoThousands":  ",",
				"oPaginate": {
					"sFirst":    "首页",
					"sPrevious": "上页",
					"sNext":     "下页",
					"sLast":     "末页"
				}

			},		  
	   "aLengthMenu" : [10, 20, 40], //更改显示记录数选项  
       "iDisplayLength" : pageSize, //默认显示的记录数
       "bPaginate" : true, //是否显示（应用）分页器  
       "bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
       "sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
     //  "bSort" : true, //是否启动各个字段的排序功能  
       "aaSorting" : [[1, "desc"]], //默认的排序方式，(0)第1列，升序排列  						
			"bPaginate" : true,// 分页按钮
			"bFilter" : false,// 搜索栏
			"bLengthChange" : true// 每行显示记录数							
	  });
	 // var table = $('#tableManangCircle').DataTable();
	 //删除需要---------
	  $('#tableUnionKeyword tbody').on( 'click', 'tr', function () {
		  if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	       } else {
	    	   tableFirst.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	       }
	   } ); 	
	  
	  $("#tableUnionKeyword_length").remove();//移除分页大小
}
/**
 * 修改或添加组合关键词  蒙层div显示
 */
function showDiv_unionKeyword() {
	 $("#HiddenBgDiv").css("display", "block"); 	
	 $("#DialogDiv").css("top", "100px");
	 $("#DialogDiv").css("display", "block");
	 $("#g_Mongolialayer").css("display", "block");//rz add 公用蒙层20150821   
}
/**
 * 修改或添加组合关键词  蒙层div隐藏
 * 
 */
function closeDiv_unionKeyword() {
	  $("#HiddenBgDiv").css("display", "none");
	  $("#DialogDiv").css("display", "none");
	  $("#g_Mongolialayer").css("display", "none");//rz add 公用蒙层20150821
}
/**
 * 单个组合关键词信息 点击修改
 */
function findUnionKeyword(cindex){	
  	$.ajax({
  		async : false,  		
  		dataType:'json',
		data:"g_id=" + cindex,			
  		type : 'post',
  		url : "findUnionKeyword.action",
  		success : sucFindUnionKeyword,
  		error : function() {	
  			sAlert("温馨提示:","<Br>对不起，请重新刷新！！！");
  		}
  	});
  }

/**
 * 单个组合关键词信息 点击修改 查询成功
 * 
 * @param json
 */
function sucFindUnionKeyword(json) {
	var message = json.g_message;
	if (message == "success") {
		var unionKeywordOne = json.unionKeywordOne;		
		if(unionKeywordOne !=null){
			showDiv_unionKeyword();			
//			$("#DialogDiv").remove();
//			$("#form_unionKeyword").remove();	
			$("#HiddenBgDiv").html("");
			$("#HiddenBgDiv").append(" <div id='DialogDiv' style='opacity:0.9;z-index: 6000;'>"
					 +"<h2>修改组合关键词<a id='btnClose' onclick='closeDiv_unionKeyword()'style='margin-top: 0px;cursor:pointer;'>关闭</a></h2>" 
					 +"<div class='form' id='form_unionKeyword' ></div></div>" );						
			$("#form_unionKeyword").append("<p><center style='margin-top: 60px;'><strong style='vertical-align:top; font-size: 20px;'>关键词：</strong>" 
					+"<input type='text' id='head_input_unionKeyword' maxlength='20' style='border: 0px; background-color: #ADD1F9; width:150px;' value='"+unionKeywordOne.head
					+"'></center></p><input type='hidden' id='cindex_input_unionKeyword' value='"+unionKeywordOne.cindex
					+"' ><input style='float:right;margin-right: 220px;margin-top: 20px;' type='button' class='Button RedButton' value='保存' onclick='modify_unionKeyword()' />");
		
		
		}
		
	} else if (message == "nodata") {
		sAlert("温馨提示:","<Br>对不起，没有数据！！！");
		return;
	} else {
		sAlert("温馨提示:","<Br>对不起,请重新刷新！！！");
	}
		
}
/**
 * 点击保存  修改组合关键词
 */
function modify_unionKeyword() {
	var head=$("#head_input_unionKeyword").val();
	var cindex=$("#cindex_input_unionKeyword").val();
	if(head!="undefined" && head!="" && cindex!="undefined" && cindex!=""){
		$.ajax({
			async : false,
			dataType:'json',
			data:"head=" + head+"&g_id=" + cindex,			
			type : 'post',
			url : "modify_unionKeyword.action",
			success : function(json) {
				if(json.g_message=="success"){
					closeDiv_unionKeyword();//关闭
					sAlert("温馨提示:","<Br>组合关键字修改成功！！！");
					serchUnionKeywordList();//更新列表
				}else{
					sAlert("温馨提示:","<Br>组合关键字修改失败！！！");
				}
				
			},
			error : function() {	
				sAlert("温馨提示:","<Br>对不起，请重新刷新！！！");
			}
		});
	}
}
/**
 * 点击添加 组合关键字 蒙层
 */
function add_unionKeyword() {
	showDiv_unionKeyword();
	$("#HiddenBgDiv").html("");
	$("#HiddenBgDiv").append(" <div id='DialogDiv' style='opacity:0.9;z-index: 6000;'>"
							+ "<h2>添加组合关键词<a id='btnClose' onclick='closeDiv_unionKeyword()' style='margin-top: 0px;cursor:pointer;'>关闭</a></h2>"
							+ "<div class='form' id='form_unionKeyword' style=''></div></div>");
	$("#form_unionKeyword").append("<p><center style='margin-top: 60px;'><strong style='vertical-align:top; font-size: 20px;'>关键词：</strong>" 
			+"<input type='text' id='head_input_unionKeyword' maxlength='20' style='border: 0px; background-color: #ADD1F9; width:150px;' value=''></center></p>"
			+ "<center><input style='float:right;margin-right: 220px;margin-top: 20px;' type='button' value='保存' class='Button RedButton' onclick='save_unionKeyword()' /></center>");

}
/**
 * 保存组合关键字
 */
function save_unionKeyword() {
	var head = $("#head_input_unionKeyword").val();
	if (head != "undefined" && head != "") {
		$.ajax({
			async : false,
			dataType : 'json',
			data : "head=" + head,
			type : 'post',
			url : "save_unionKeyword.action",
			success : function(json) {
				if (json.g_message == "success") {
					closeDiv_unionKeyword();// 关闭
					sAlert("温馨提示:", "<Br>组合关键词添加成功！！！");
					serchUnionKeywordList();//更新列表
				} else {
					sAlert("温馨提示:", "<Br>组合关键词添加失败！！！");
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
function delete_unionKeyword(cindex) {
	$.ajax({
		async : false,
		dataType : 'json',
		data : "g_id=" + cindex,
		type : 'post',
		url : "delete_unionKeyword.action",
		success : function(json) {
			if (json.g_message == "success") {
				sAlert("温馨提示:", "<Br>删除成功！！！");				
				var table = $('#tableUnionKeyword').DataTable();
				table.row('.selected').remove().draw(false);
				serchUnionKeywordList();//更新列表
			} else {
				sAlert("温馨提示:", "<Br>删除失败！！！");
				
			}

		},
		error : function() {
			sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");			
		}
	});
}