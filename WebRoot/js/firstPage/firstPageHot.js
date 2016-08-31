

$(document).ready(function() {	
	amusementClick("2");//热点	
});
/**
 * 点击热点，今日热点，七日关注，民生热点等div
 */
function amusementClick(serchValue) {
	
	$.ajax({
		async : false,
		dataType:'json',
		data:"g_serchValue=" + serchValue,			
		type : 'post',
		url : "findBaiduHotByTime.action",
		success : sucFindBaiduHotByTime,
		error : function() {	
			sAlert("温馨提示:","<Br>对不起，请重新刷新！！！");
		}
	});
}	  
/**
 *查询百度topList，成功
 * @param json
 */
function sucFindBaiduHotByTime(json) {
	
	var message = json.g_message;	
	if(message == "success"){
		$("#g_content_div").html("");
		$("#g_content_div").append("<ul class='ui1'><li class='s1'>热点</li><li class='s2'>搜索量</li><li class='s3'>统计时间</li></ul>" );		
		var list=json.listTTopKeyWord;	
		if(list !=null && list.length > 0){			
			var str="<ul style='margin-left:-40px;'>";
			for (var i = 0; i < list.length; i++) {				
				//var ctime = (list[i].ctime).replace("T", " ");				
				var ctime = (list[i].ctime).substr(0,10);
				var url="http://news.baidu.com/ns?cl=2&rn=20&tn=news&word="+list[i].keyWord;
				//var ctime = ctime.replace("T", " ");
				str+="<li><ul class='ui2'>" 
					+"<li class='x1'>"+(i+1)+"</li>" 
					+"<li class='x2'>"+"<a target='_blank' href='"+url+"'>"+list[i].keyWord+"</a>"+"</li>"
					+"<li class='x3'>"+list[i].count+"</li>"
					+"<li class='x4'>"+ctime+"</li></ul></li>";				
			}
			str+="</ul>";
		  $("#g_content_div").append(str);
		}
	
	}else{
		sAlert("温馨提示:","<Br>对不起，请重新刷新！！！");
	}
}