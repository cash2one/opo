

$(document).ready(function() {	
	earthHotWord();//舆情热词地球	
});
/**
 * 舆情热词地球
 */
function earthHotWord() {
	var g_size=45;
	$.ajax({
		async : false,
		dataType:'json',
		data:"g_size=" + g_size,			
		type : 'post',
		url : "findEarthHotWordG_size.action",
		success : sucFindEarthHotWordG_size,
		error : function() {	
			sAlert("温馨提示:","<Br>对不起，请重新刷新！！！");
		}
	});
}	  
/**
 *查询百度topList，成功
 * @param json
 */
function sucFindEarthHotWordG_size(json) {
	var message = json.g_message;	
	if(message == "success"){
		$("#tagbox").html("");		
		var list=json.addrTempsList;	
		if(list !=null && list.length > 0){			
			var str="";
			for (var i = 0; i < list.length; i++) {	
				var url=list[i].url;
				if(url==""){
					url="http://news.baidu.com/ns?cl=2&rn=20&tn=news&word="+list[i].keyWord;
				}		
				if(i%3==0){//红色
					str+="<a class='red'  target='_blank' href='"+url+"'>"+list[i].keyWord+"</a>";
				}else if(i%5==0){//蓝色
					str+="<a class='blue' target='_blank' href='"+url+"'>"+list[i].keyWord+"</a>";
				}else if(i%4==0){//黄色
					str+="<a class='yellow' target='_blank' href='"+url+"'>"+list[i].keyWord+"</a>";
				}else if(i%6==0){//绿色
					str+="<a class='blue' target='_blank' href='"+url+"'>"+list[i].keyWord+"</a>";
				}else if(i%7==0){//黄色
					str+="<a class='yellow' target='_blank' href='"+url+"'>"+list[i].keyWord+"</a>";
				}else if(i%2==0){//白色
					str+="<a  target='_blank' href='"+url+"'>"+list[i].keyWord+"</a>";
				}else{//绿色
					str+="<a class='green' target='_blank' href='"+url+"'>"+list[i].keyWord+"</a>";
				}
							
			}
		  $("#tagbox").append(str);
		}
	
	}else{
		sAlert("温馨提示:","<Br>对不起，请重新刷新！！！");
	}
}