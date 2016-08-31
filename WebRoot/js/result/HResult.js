/**
 * 加载页面
 */
function serchHResultList_click(){
	//显示弹框		
		$('#dialogBg').fadeIn(300);
		var className="aaa_setSensitiveKey";
		$('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
		
		$("#sensitiveKeyword_management").css("display","block");//显示
		serchHResultList();
		
}



/**
 * 按照时间范围查询最新消息
 */
function serchHResultList() {
	var dateTime=document.getElementById('HResultDate').value;
	alert(dateTime);
	$.ajax({
		async : false,		
		type : 'post',
		dataType:'json',
		data:"HResultDate=" + dateTime,
		url : "findHResult.action",
		success : sucSerchHResultList,
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
function sucSerchHResultList(json) {
	var message = json.g_message;
	if(message == "success"){
		var list=json.hresultList;
		if(list !=null && list.length > 0){			
			$("#tableHResult").remove();
			$("#tableHResult_wrapper").remove();
			
			$("#tableHResult_div").append("<table id='tableHResult' class='display' cellspacing='0' width='98%'>" +
					"<thead><tr>" +
					"<td>关键词</td>" +
					"<td>创建日期</td>" +
					"</tr></thead></table>"
					);
			 
			for (var i = 0; i < list.length; i++) {	
				var tr_KeywordId_remove='removeKeyword'+list[i].cindex;
				//将字符串中的T去掉--替换成空格
				alert(list[i].ctime);
				var time=list[i].ctime;
				time=time.replace("T"," ");
				
				$("#tableHResult").append("<tr class='tableKeyword_TableTr' id='"+tr_KeywordId_remove
						+"'><td>"+list[i].keyWord+"</td>" 
						+"<td>"+time+"</td>"
						+"</tr>");
			}	
			
			
		}
	}else if(message == "nodata"){		
		//sAlert("温馨提示:","<Br>对不起，没有数据！！！");
		$( "#dialog-message" ).children("p").html("对不起，没有数据！！！");
        $( "#dialog-message" ).dialog("open");
        return;
	}else{
		return;
	}	
     
	 var pageSize=5;
	 var tableFirst =$('#tableHResult').dataTable({ 		  
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
    //   "aaSorting" : [[0, "asc"]], //默认的排序方式，第1列，升序排列  						
			"bPaginate" : true,// 分页按钮
			"bFilter" : false,// 搜索栏
			"bLengthChange" : true// 每行显示记录数							
	  });
	 // var table = $('#tableManangCircle').DataTable();
	 //删除需要，删除后更新内容---------
	  $('#tableHResult tbody').on( 'click', 'tr', function () {
		  if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	       } else {
	    	   tableFirst.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	       }
	   } ); 
	  
	  $("#tableHResult_length").remove();//移除分页大小
}










