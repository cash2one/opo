/**
 * 点击设置敏感关键词
 */
function setSensitiveKey_click(){
	//显示弹框		
		$('#dialogBg').fadeIn(300);
		var className="aaa_setSensitiveKey";
		$('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
		$("#sensitiveKeyword_serchValue").val("");//恢复空值
		
		$("#sensitiveKeyword_management").css("display","block");//显示
		serchKeywordList();//查询敏感关键词
}

/**
 * 模糊查询敏感词关键词
 */
function serchKeywordList() {
	var keywords = document.getElementById("sensitiveKeyword_serchValue").value;
	$.ajax({
		async : false,				
		data : "keyword=" + keywords,
  		type : "post",
  		url : "findVagueKeywords.action",
		//url : "findKeywordList.action",
		success : sucfindKeyword,
		error : function() {				
			$( "#dialog-message" ).children("p").html("对不起，请重新刷新！！！");
	        $( "#dialog-message" ).dialog("open");
		}
	});
}
/**
 * 查询敏感词List，成功
 * @param json
 */
function sucfindKeyword(json) {
	var message = json.g_message;
	if(message == "success"){
		var list=json.tKeywordList;
		if(list !=null && list.length > 0){	
			list_size=list.length;
			$("#tableKeyword").remove();
			$("#tableKeyword_wrapper").remove();
			
			$("#tableKeyword_div").append("<table id='tableKeyword' class='display' cellspacing='0' width='98%'>" +
					"<thead><tr>" +
					"<td>敏感词</td>" +
					"<td>创建时间</td>" +
					"<td>操作</td>" +
					"</tr></thead></table>"
					);
			 
			for (var i = 0; i < list.length; i++) {	
				var tr_KeywordId_remove='removeKeyword'+list[i].cindex;
				//将字符串中的T去掉--替换成空格
				var time=list[i].ctime;
				time=time.replace("T"," ");
				
				$("#tableKeyword").append("<tr class='tableKeyword_TableTr' id='"+tr_KeywordId_remove
						+"'><td>"+list[i].keyWord+"</td>" 
						+"<td>"+time+"</td>"
						+"<td><a  href='javascript:keyword_Mlook(\""+list[i].cindex+"\")'>修改</a>&nbsp&nbsp&nbsp&nbsp" +	
							"<a  href='javascript:keyword_Delete(\""+list[i].cindex+"\")'>删除</a>&nbsp&nbsp&nbsp&nbsp"+
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
	 var tableFirst =$('#tableKeyword').dataTable({ 		  
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
       "aaSorting" : [[1, "desc"]], //默认的排序方式，第1列，升序排列  						
			"bPaginate" : true,// 分页按钮
			"bFilter" : false,// 搜索栏
			"bLengthChange" : true// 每行显示记录数							
	  });
	 // var table = $('#tableManangCircle').DataTable();
	 //删除需要，删除后更新内容---------
	  $('#tableKeyword tbody').on( 'click', 'tr', function () {
		  if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	       } else {
	    	   tableFirst.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	       }
	   } ); 
	  
	  $("#tableKeyword_length").remove();//移除分页大小
}

/**
 * 删除一条敏感词，byid;
 */
function keyword_Delete(cindex) {
	$.ajax({
		async : false,
		dataType:'json',
		data:"keyword_id=" + cindex,			
		type : 'post',
		url : "keyword_Delete.action",
		success : suckeyword_Delete,
		error :  function() {	
  			sAlert("温馨提示:","<Br>对不起，请重新刷新！！！");
  		}
	});
}
/**
 * 成功删除敏感词
 * @param json
 */
function suckeyword_Delete(json){
	if (json.g_message == "success") {
		var table = $('#tableKeyword').DataTable();
		table.row('.selected').remove().draw(false);
		sAlert("温馨提示:","<Br>最新关键词删除成功！！！");
		serchKeywordList();
	} else {
		sAlert("温馨提示:","<Br>最新关键词删除失败！！！");
	}

}

/**
 *  添加最新关键字  蒙层
 * @param 
 * @param 
 */
function addSensitiveKeyword() {
	ShowDIV();					
	$("#DialogDiv").remove();
	$("#form").remove();							
	$("#HiddenBgDiv").append(
			" <div id='DialogDiv' style='opacity:0.9;z-index: 6000;'>" +
			"<h2>添加敏感关键词<a id='btnClose' onclick='closeDiv(DialogDiv)' style='margin-top: 0px;cursor:pointer;'>关闭</a></h2>" +
			"<div class='form' id='form' style=''>" +
			"</div>" +
			"</div>"
	);
    $("#form").append(
    		"<p><center style='margin-top: 60px;'><strong style='vertical-align:top; font-size: 20px;'>关键词：</strong><input type='text' id='keyword_add' maxlength='20' style='border: 0px; background-color: #ADD1F9; width:150px;' value=''></center></p>" +
			"<center><input style='float:right;margin-right: 220px;margin-top: 20px;' type='button' value='保存' class='Button RedButton' onclick='keyword_Add()' /></center>");
			}


/**
 * 添加一条敏感词;
 * 
 */
function keyword_Add() {
	var keyWord=document.getElementById("keyword_add").value;
	if(keyWord!="undefind" && keyWord!==""){
		$.ajax({
			async : false,
			dataType:'json',
			data:"keyword=" + keyWord,
			type : 'post',
			url : "keyword_add.action",
			success : suckeyword_Add,
			error : function() {	
				sAlert("温馨提示:","<Br>对不起，请重新刷新！！！");
			}
		});
	}
	
}
/**
 * 成功添加敏感词
 * @param json
 */
function suckeyword_Add(json){
		if(json.g_message=="success"){
			closeDiv(DialogDiv);//关闭这个“DialogDiv”的id的div
			sAlert("温馨提示:","<Br>最新关键词添加成功！！！");
			serchKeywordList();
		}else{
			sAlert("温馨提示:","<Br>最新关键词添加失败！！！");
		}
		
	
}

/**
 * 修改关键词  蒙层div显示
 * @param json
 */
function ShowDIV() {
	 $("#HiddenBgDiv").css("display", "block"); 
	 $("#HiddenBgDiv").css("display", "block");
    // $("#BgDiv").css({ display: "block", height: $(document).height() });
     var yscroll = document.documentElement.scrollTop;
    
     $("#DialogDiv").css("top", "100px");
	 $("#DialogDiv").css("display", "block");
	 $("#g_Mongolialayer").css("display", "block");//rz add 公用蒙层20150821
	 
    document.documentElement.scrollTop = 0;
}
/**
 * 修改关键词  蒙层div隐藏
 * 
 */
function closeDiv(thisObjID) {
	  $("#HiddenBgDiv").css("display", "none");
	 /*  $("#HiddenBgDiv").css("visibility", "hidden"); */
	 // $("#BgDiv").css("display", "none");
	  $("#DialogDiv").css("display", "none");
	  $("#g_Mongolialayer").css("display", "none");//rz add 公用蒙层20150821
}




/**
 * 修改前查询一条敏感词;
 */
function keyword_Mlook(cindex) {
	$.ajax({
		async : false,
		dataType:'json',
		data:"keyword_id=" + cindex,
		type : 'post',
		url : "keywordList_look.action",
		success : suckeywordList_look,
		error : function() {				
			$( "#dialog-message" ).children("p").html("对不起，请重新刷新！！！");
	        $( "#dialog-message" ).dialog("open");
		}
	});
}
/**
 * 成功在修改前查询敏感词
 * @param json
 */
function suckeywordList_look(json){	
	var message = json.g_message;	
	if(message == "success"){
	
		var tkeyword=json.tkeyword;	
		if (tkeyword != null ) {
			
			ShowDIV();			
			$("#DialogDiv").remove();
			$("#form").remove();										
			$("#HiddenBgDiv").append(" <div id='DialogDiv' style='opacity:0.9;z-index: 6000;'>" +
					"<h2>修改敏感关键词<a id='btnClose' onclick='closeDiv(DialogDiv)'style='margin-top: 0px;cursor:pointer;'>关闭</a></h2>" +
					"<div class='form' id='form' style=''>" +					
					"</div>" +
					"</div>"
					 );

				
			$("#form").append("<p><center style='margin-top: 60px;'>" +
					"<strong style='vertical-align:top; font-size: 20px;'>关键词：</strong>" +
					"<input type='text' id='keyword_modify' maxlength='20' style='border: 0px; background-color: #ADD1F9; width:150px;' value='"
					+tkeyword.keyWord+"'></center></p>"
					+"<input type='hidden' id='cindex_modify'value='"+tkeyword.cindex+"' ><input style='float:right;margin-right: 220px;margin-top: 20px;' type='button' class='Button RedButton' value='保存' onclick='keyword_Modify()' />");
					
		}
	} else if (message == "nodata") {
		sAlert("温馨提示:","<Br>对不起，没有数据！！！");
		return;
	} else {

	}
}

/**
 * 修改敏感词;
 */
function keyword_Modify() {
	var sensitivekeyword=$("#keyword_modify").val();
	var cindex=$("#cindex_modify").val();
	$.ajax({
		async : false,
		dataType:'json',
		data:"keyword_id=" + cindex+"&keyword="+sensitivekeyword,
		type : 'post',
		url : "keyword_modify.action",
		success : suckeyword_modify,
		error : function() {	
			sAlert("温馨提示:","<Br>对不起，请重新刷新！！！");
		}
	});
}
/**
 * 成功修改敏感词
 * @param json
 */
function suckeyword_modify(json){
	if(json.g_message=="success"){
		closeDiv(DialogDiv);//关闭这个“DialogDiv”的id的div
		sAlert("温馨提示:","<Br>最新关键字修改成功！！！");
		serchKeywordList();
	}else{
		sAlert("温馨提示:","<Br>最新关键字修改失败！！！");
	}
	
}











