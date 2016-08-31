
window.onload=function(){ 
	Wall360Refresh_btn();//360wall
};
/**
 * 360wall
 */
function Wall360Refresh_btn() {
	var serchValue="20";
	$.ajax({
		async : false,
		dataType:'json',
		data:"g_size=" + serchValue,			
		type : 'post',
		url : "find360HotG_sizeByTime.action",
		success : sucFind360HotG_sizeByTime,
		error : function() {	
			sAlert("温馨提示:","<Br>对不起，请刷新！！！");

		}
	});
}	  
/**
 *360wall，成功
 * @param json
 */
function sucFind360HotG_sizeByTime(json) {
	
	var message = json.g_message;	
	if(message == "success"){
		var list=json.listT360topKeyWords;	
		if(list !=null && list.length > 0){						
			for (var i = 0; i < list.length; i++) {				
				if(i==0){
					$("#box_1").html("");
					$("#box_1").append(" <a href='"
							+list[i].newsUrl+"'  target='_blank' class='txt tp_22' style='line-height: normal;'><span class='main_title' style='padding-top: 90px;'>"
                            +list[i].title+"</span><div><p style='padding-top: 60px;'><strong>"
                            +list[i].title+"</strong><span class='sub_title'>"
                            +list[i].ltitle+"</span></p></div></a>"
                            );
				}else if(i==1){
					$("#box_2").html("");
					$("#box_2").append(" <a href='"
							+list[i].newsUrl+"' target='_blank' class='txt tp_22' style='line-height: normal;'> <span class='main_title' style='padding-top: 90px;'>"
							+list[i].title+"</span><div><p style='padding-top: 60px;'><strong>"
							+list[i].title+"</strong><span class='sub_title'>"
							+list[i].ltitle+" </span></p></div></a>"
                            );
				}else if(i==2){
					$("#box_3").html("");
					$("#box_3").append(" <a href='"
							+list[i].newsUrl+"'target='_blank' class='txt tp_12' style='line-height: normal;'> <span class='main_title' style='padding-top: 70px;'>"
							+list[i].title+"</span><div><p style='padding-top: 60px;'><strong>"
							+list[i].title+"</strong><span class='sub_title'>"
							+list[i].ltitle+" </span></p></div></a>"
                            );
				}else if(i==3){
					$("#box_4").html("");
					$("#box_4").append(" <a href='"
							+list[i].newsUrl+"' target='_blank' class='img tp_12' style='line-height: normal;'><img src='" 
							+list[i].imgUrl+"'> <span class='main_title' style='visibility: hidden; padding-top: 70px;'>"
							+list[i].title+"</span><div><p style='padding-top: 60px;'><strong>"
							+list[i].title+"</strong><span class='sub_title'>"
							+list[i].ltitle+" </span></p></div></a>"
                            );
				}else if(i==4){
					$("#box_5").html("");
					$("#box_5").append(" <a href='"
							+list[i].newsUrl+"'target='_blank' class='txt tp_11' style='line-height: normal;'><span class='main_title' style='padding-top: 30px;'>"
							+list[i].title+"</span><div><p style='padding-left: 8px; padding-right: 8px;'><strong>"
							+list[i].title+"</strong><span style='line-height:14px;' class='sub_title'>"
							+list[i].ltitle+" </span></p></div></a>"
                            );
				}else if(i==5){
                    $("#box_6").html("");
                    $("#box_6").append(" <a href='"
                    		+list[i].newsUrl+" 'target='_blank' class='img tp_12' style='line-height: normal;'><img src='"
                    		+list[i].imgUrl+"'><span class='main_title' style='visibility: hidden;padding-top: 113px;'>"
                    		+list[i].title+"</span><div><p style='padding-top:60px;'><strong>"
                    		+list[i].title+"</strong><span class='sub_title'>"
                    		+list[i].ltitle+" </span></p></div></a>"
                            );
                }else if(i==6){
                    $("#box_7").html("");
                    $("#box_7").append(" <a href='"
                    		+list[i].newsUrl+"' target='_blank' class='img tp_11' style='line-height: normal;'><img src='"
                    		+list[i].imgUrl+"'><span class='main_title' style='visibility: hidden;padding-top: 63px;'>"
                    		+list[i].title+"</span><div><p style='padding-left: 8px; padding-right: 8px;'><strong>"
                    		+list[i].title+"</strong><span style='line-height:14px;' class='sub_title'>"
                    		+list[i].ltitle+" </span></p></div></a>"
                            );
                }else if(i==7){
                    $("#box_8").html("");
                    $("#box_8").append(" <a href='"
                    		+list[i].newsUrl+"'target='_blank' class='txt tp_21' style='line-height: normal;'><span class='main_title' style='padding-top: 38px;'>"
                    		+list[i].title+"</span><div><p style='padding-top: 8px;'><strong>"
                    		+list[i].title+"</strong><span class='sub_title'>"
                    		+list[i].ltitle+" </span></p></div></a>"
                            );
                }else if(i==8){
                    $("#box_9").html("");
                    $("#box_9").append(" <a href='" 
                    		+list[i].newsUrl+"'target='_blank' class='img tp_11' style='line-height: normal;'><img src='"
                    		+list[i].imgUrl+"'><span class='main_title' style=style='visibility: hidden; padding-top: 63px;'>"
                    		+list[i].title+"</span><div><p style='padding-left: 8px; padding-right: 8px;'><strong>"
                    		+list[i].title+"</strong><span style='line-height:14px;' class='sub_title'>"
                            +"陕北油田频发漏油事件 河道及农田遭大面积污染"+" </span></p></div></a>"
                            );
                }else if(i==9){
                    $("#box_10").html("");
                    $("#box_10").append(" <a href='" 
                    		+list[i].newsUrl+"'target='_blank' class='img tp_11' style='line-height: normal;'><img src='" 
                    		+list[i].imgUrl+"'><span class='main_title' style=style='visibility: hidden; padding-top: 63px;'>"
                    		+list[i].title+"</span><div><p style='padding-left: 8px; padding-right: 8px;'><strong>"
                    		+list[i].title+"</strong><span style='line-height:14px;' class='sub_title'>"
                            +"李宇春拍戏超敬业 被张家辉三次袭胸面不改色"+" </span></p></div></a>"
                            );
                }else if(i==10){
                    $("#box_11").html("");
                    $("#box_11").append(" <a href='" 
                    		+list[i].newsUrl+"'target='_blank' class='txt tp_12' style='line-height: normal;'><span class='main_title' style=' padding-top: 75px;'>"
                    		+list[i].title+"</span><div><p style='padding-top: 45px;'><strong>"
                    		+list[i].title+"</strong><span class='sub_title'>"
                    		+list[i].ltitle+" </span></p></div></a>"
                            );
                }else if(i==11){
                    $("#box_12").html("");
                    $("#box_12").append(" <a href='" 
                    		+list[i].newsUrl+"'target='_blank' class='txt tp_11' style='line-height: normal;'><span class='main_title' style=' padding-top: 29px;'>"
                    		+list[i].title+"</span><div><p style='padding-left: 8px; padding-right: 8px;'><strong>"
                    		+list[i].title+"</strong><span style='line-height:14px;' class='sub_title'>"
                    		+list[i].ltitle+" </span></p></div></a>"
                            );
                }else if(i==12){
                    $("#box_13").html("");
                    $("#box_13").append(" <a href='" 
                    		+list[i].newsUrl+"'target='_blank' class='txt tp_11' style='line-height: normal;'><span class='main_title' style='padding-top: 29px;'>"
                    		+list[i].title+"</span><div><p style='padding-left: 8px; padding-right: 8px; '><strong>"
                    		+list[i].title+"</strong><span style='line-height:14px;' class='sub_title'>"
                    		+list[i].ltitle+" </span></p></div></a>"
                            );
                }else if(i==13){
                    $("#box_14").html("");
                    $("#box_14").append(" <a href='" 
                    		+list[i].newsUrl+"'target='_blank' class='txt tp_11' style='line-height: normal;'><span class='main_title' style='padding-top: 29px;'>"
                    		+list[i].title+"</span><div><p style='padding-left: 8px; padding-right: 8px;'><strong>"
                    		+list[i].title+"</strong><span style='line-height:14px;' class='sub_title'>"
                    		+list[i].ltitle+" </span></p></div></a>"
                            );
                }else if(i==14){
                    $("#box_15").html("");
                    $("#box_15").append(" <a href='" 
                    		+list[i].newsUrl+"'target='_blank' class='img tp_11' style='line-height: normal;'><img src='" 
                    		+list[i].imgUrl+"'><span class='main_title' style='visibility: hidden;padding-top: 63px;'>"
                    		+list[i].title+"</span><div><p style='padding-left: 8px; padding-right: 8px;'><strong>"
                    		+list[i].title+"</strong><span style='line-height:14px;' class='sub_title'>"
                    		+list[i].ltitle+" </span></p></div></a>"
                            );
                }else if(i==15){
                    $("#box_16").html("");
                    $("#box_16").append(" <a href='" 
                    		+list[i].newsUrl+"'target='_blank' class='txt tp_21' style='line-height: normal;'><span class='main_title' style='padding-top: 36px;'>"
                    		+list[i].title+"</span><div><p style='padding-top: 8px;'><strong>"
                    		+list[i].title+"</strong><span class='sub_title'>"
                    		+list[i].ltitle+" </span></p></div></a>"
                            );
                }else if(i==16){
                    $("#box_17").html("");
                    $("#box_17").append(" <a href='" 
                    		+list[i].newsUrl+"'target='_blank' class='img tp_11' style='line-height: normal;'><img src='" 
                    		+list[i].imgUrl+"'><span class='main_title' style='visibility: hidden; padding-top: 63px;'>"
                    		+list[i].title+"</span><div><p style='padding-left: 8px; padding-right: 8px;'><strong>"
                    		+list[i].title+"</strong><span style='line-height:14px;' class='sub_title'>"
                    		+list[i].ltitle+" </span></p></div></a>"
                            );
                }else if(i==17){
                    $("#box_18").html("");
                    $("#box_18").append(" <a href='" 
                    		+list[i].newsUrl+"'target='_blank' class='img tp_11' style='line-height: normal;'><img src='" 
                    		+list[i].imgUrl+"'><span class='main_title' style='visibility: hidden; padding-top: 63px;'>"
                    		+list[i].title+"</span><div><p style='padding-left: 8px; padding-right: 8px;'><strong>"
                    		+list[i].title+"</strong><span style='line-height:14px;'class='sub_title'>"
                    		+list[i].ltitle+" </span></p></div></a>"
                            );
                }else if(i==18){
                    $("#box_19").html("");
                    $("#box_19").append(" <a href='" 
                    		+list[i].newsUrl+"'target='_blank' class='txt tp_11' style='line-height: normal;'><img src='" 
                    		+list[i].imgUrl+"'><span class='main_title' style='padding-top: 63px;'>"
                    		+list[i].title+"</span><div><p style='padding-left: 8px; padding-right: 8px;'><strong>"
                    		+list[i].title+"</strong><span style='line-height:14px;' class='sub_title'>"
                    		+list[i].ltitle+" </span></p></div></a>"
                            );
                }else if(i==19){
                    $("#box_20").html("");
                    $("#box_20").append(" <a href='" 
                    		+list[i].newsUrl+"'target='_blank' class='img tp_11' style='line-height: normal;'><img src='" 
                    		+list[i].imgUrl+"'><span class='main_title' style='visibility: hidden; padding-top: 63px;'>"
                    		+list[i].title+"</span><div><p style='padding-left: 8px; padding-right: 8px;'><strong>"
                    		+list[i].title+"</strong><span style='line-height:14px;'class='sub_title'>"
                    		+list[i].ltitle+" </span></p></div></a>"
                            );
                }				
			}					
		}
	
	}else{
		sAlert("温馨提示:","<Br>对不起，请重新刷新！！！");
	}
}


/*function refresh_btn() {			
			for (var i = 0; i < 20; i++) {
				if(i==0){
					$("#box_1").html("");
					$("#box_1").append(" <a href='http://news.haosou.com/ns?q=%E5%A4%96%E4%BA%A4%E9%83%A8%E5%9B%9E%E5%BA%94%E5%AE%89%E5%80%8D%E8%AE%B2%E8%AF%9D&src=newsresou&tn=news'  target='_blank' class='txt tp_22' style='line-height: normal;'><span class='main_title' style='padding-top: 93px;'>"
                            +"外交部回应安倍讲话"+"</span>" +"<div><p style='padding-top: 60px;'><strong>"
                            +"外交部回应安倍讲话"+"</strong><span class='sub_title'>"
                            +"外交部回应安倍谈话:重大原则问题上不要掩饰"+"</span></p></div></a>"
                            );
				}else if(i==1){
					$("#box_2").html("");
					$("#box_2").append(" <a href='http://news.haosou.com/ns?q=%E5%81%87%E5%BB%BA%E8%AE%BE%E9%93%B6%E8%A1%8C&src=newsresou&tn=news'target='_blank' class='txt tp_22' style='line-height: normal;'> <span class='main_title' style='padding-top: 126px;'>"
                            +"假建设银行"+"</span><div style='height: 0px; visibility: visible;'><p style='padding-top: 60px;'><strong>"
                            +"假建设银行"+"</strong><span class='sub_title'>"
                            +"山东农民4000元开假建设银行"+" </span></p></div></a>"
                            );
				}else if(i==2){
					$("#box_3").html("");
					$("#box_3").append(" <a href='http://news.haosou.com/ns?q=%E5%A5%B3%E5%A4%A7%E5%AD%A6%E7%94%9F%E5%8D%96%E5%88%9D%E5%A4%9C%E8%A2%AB%E5%8A%AB&src=newsresou&tn=news'target='_blank' class='txt tp_12' style='line-height: normal;'> <span class='main_title' style='padding-top: 113px;'>"
                            +"女大学生卖初夜被劫"+"</span><div style='height: 0px; visibility: visible;'><p style='padding-top: 129px;'><strong>"
                            +"女大学生卖初夜被劫"+"</strong><span class='sub_title'>"
                            +"浙江女大学生2万元卖初夜反遭骗色劫财"+" </span></p></div></a>"
                            );
				}else if(i==3){
					$("#box_4").html("");
					$("#box_4").append(" <a href='http://news.haosou.com/ns?q=%E7%91%9E%E6%B5%B7%E9%9A%90%E5%BD%A2%E8%82%A1%E4%B8%9C&src=newsresou&tn=news'target='_blank' class='img tp_12' style='line-height: normal;'><img src='jpg/t01e22cc95cd3caf5e4.jpg'> <span class='main_title' style='visibility: hidden; padding-top: 113px;'>"
                            +"瑞海隐形股东"+"</span><div style='height: 0px;'><p style='padding-top: 129px;'><strong>"
                            +"瑞海隐形股东"+"</strong><span class='sub_title'>"
                            +"原天津港公安局长之子被指为瑞海隐形股东"+" </span></p></div></a>"
                            );
				}else if(i==4){
					$("#box_5").html("");
					$("#box_5").append(" <a href='http://news.haosou.com/ns?q=%E7%AB%A0%E5%AD%90%E6%80%A1%E8%A6%81%E6%B1%82%E6%92%A4%E8%9C%A1%E5%83%8F&src=newsresou&tn=news'target='_blank' class='txt tp_11' style='line-height: normal;'><span class='main_title' style='padding-top: 63px;'>"
                            +"章子怡要求撤蜡像"+"</span><div style='height: 0px;'><p style='padding-left: 8px; padding-right: 8px; padding-top: 47px;'><strong>"
                            +"章子怡要求撤蜡像"+"</strong><span class='sub_title'>"
                            +"章子怡要求撤下山寨蜡像蜡像馆:到时要撤就撤吧"+" </span></p></div></a>"
                            );
				}else if(i==5){
                    $("#box_6").html("");
                    $("#box_6").append(" <a href='http://news.haosou.com/ns?q=%E5%AE%89%E5%80%8D%20%E9%81%93%E6%AD%89&src=newsresou&tn=news'target='_blank' class='img tp_12' style='line-height: normal;'><img src='jpg/t0123efd00a29b17bb6.jpg'><span class='main_title' style='visibility: hidden;padding-top: 113px;'>"
                            +"安倍向曾侵略国道歉"+"</span><div><p style='padding-top:129px;'><strong>"
                            +"安倍向曾侵略国道歉"+"</strong><span class='sub_title'>"
                            +"安倍向曾侵略对象国道歉最后提及中国大陆"+" </span></p></div></a>"
                            );
                }else if(i==6){
                    $("#box_7").html("");
                    $("#box_7").append(" <a href='http://news.haosou.com/ns?q=%E8%B6%B3%E7%90%83%E6%88%90%E7%BB%A9%E5%BD%B1%E5%93%8D%E5%8D%87%E5%AD%A6&src=newsresou&tn=news'target='_blank' class='img tp_11' style='line-height: normal;'><img src='jpg/t0195aa67063703fe31.jpg'><span class='main_title' style='visibility: hidden;padding-top: 63px;'>"
                            +"足球成绩影响升学"+"</span><div><p style='padding-left: 8px; padding-right: 8px; padding-top: 47px;'><strong>"
                            +"足球成绩影响升学"+"</strong><span class='sub_title'>"
                            +"港媒:足球纳入内地学生素质评价或影响孩子升学"+" </span></p></div></a>"
                            );
                }else if(i==7){
                    $("#box_8").html("");
                    $("#box_8").append(" <a href='http://news.haosou.com/ns?q=%E8%A2%AB%E9%A5%BF%E5%AD%A4%E5%AF%A1%E8%80%81%E4%BA%BA%E5%8E%BB%E4%B8%96&src=newsresou&tn=news'target='_blank' class='txt tp_21' style='line-height: normal;'><span class='main_title' style='padding-top: 54px;'>"
                            +"被饿孤寡老人去世"+"</span><div><p style='padding-top: 56px;'><strong>"
                            +"被饿孤寡老人去世"+"</strong><span class='sub_title'>"
                            +"山东被饿得剩皮包骨孤寡老人去世"+" </span></p></div></a>"
                            );
                }else if(i==8){
                    $("#box_9").html("");
                    $("#box_9").append(" <a href='http://news.haosou.com/ns?q=%E9%99%95%E5%8C%97%E6%B2%B9%E7%94%B0%E6%BC%8F%E6%B2%B9%E9%A2%91%E5%8F%91&src=newsresou&tn=news'target='_blank' class='img tp_11' style='line-height: normal;'><img src='jpg/t017e15b96324beba8b.jpg'><span class='main_title' style=style='visibility: hidden; padding-top: 63px;'>"
                            +"陕北油田漏油频发"+"</span><div><p style='padding-left: 8px; padding-right: 8px; padding-top: 47px;'><strong>"
                            +"陕北油田漏油频发"+"</strong><span class='sub_title'>"
                            +"陕北油田频发漏油事件 河道及农田遭大面积污染"+" </span></p></div></a>"
                            );
                }else if(i==9){
                    $("#box_10").html("");
                    $("#box_10").append(" <a href='http://news.haosou.com/ns?q=%E6%9D%8E%E5%AE%87%E6%98%A5%E8%A2%AB%E8%A2%AD%E8%83%B8&src=newsresou&tn=news'target='_blank' class='img tp_11' style='line-height: normal;'><img src='jpg/t0147b065f519f6286b.jpg'><span class='main_title' style=style='visibility: hidden; padding-top: 63px;'>"
                            +"李宇春被袭胸"+"</span><div><p style='padding-left: 8px; padding-right: 8px; padding-top: 47px;'><strong>"
                            +"李宇春被袭胸"+"</strong><span class='sub_title'>"
                            +"李宇春拍戏超敬业 被张家辉三次袭胸面不改色"+" </span></p></div></a>"
                            );
                }else if(i==10){
                    $("#box_11").html("");
                    $("#box_11").append(" <a href='http://news.haosou.com/ns?q=%E9%A6%99%E6%B8%AF%E5%90%B8%E5%BC%95%E5%8A%9B%E4%B8%8B%E9%99%8D&src=newsresou&tn=news'target='_blank' class='txt tp_12' style='line-height: normal;'><span class='main_title' style=style='visibility: hidden; padding-top: 113px;'>"
                            +"香港吸引力下降"+"</span><div style='height: 0px;'><p style='padding-top: 129px;'><strong>"
                            +"香港吸引力下降"+"</strong><span class='sub_title'>"
                            +"香港对内地人吸引力下降 内地移港人数回落"+" </span></p></div></a>"
                            );
                }else if(i==11){
                    $("#box_12").html("");
                    $("#box_12").append(" <a href='http://news.haosou.com/ns?q=%E7%88%86%E7%82%B8%E6%A0%B8%E5%BF%83%E5%B9%B8%E5%AD%98%E8%80%85&src=newsresou&tn=news'target='_blank' class='txt tp_11' style='line-height: normal;'><span class='main_title' style=style='visibility: hidden; padding-top: 63px;'>"
                            +"爆炸核心现幸存者"+"</span><div><p style='padding-left: 8px; padding-right: 8px; padding-top: 47px;'><strong>"
                            +"爆炸核心现幸存者"+"</strong><span class='sub_title'>"
                            +"天津核心爆炸区幸存者病情危重 家人已赶到医院"+" </span></p></div></a>"
                            );
                }else if(i==12){
                    $("#box_13").html("");
                    $("#box_13").append(" <a href='http://news.haosou.com/ns?q=%E5%8C%BB%E9%99%A2%E8%AF%B7%E5%86%9C%E6%B0%91%E5%90%83%E9%A5%AD%E9%AA%97%E4%BF%9D&src=newsresou&tn=news'target='_blank' class='txt tp_11' style='line-height: normal;'><span class='main_title' style=style='padding-top: 63px;'>"
                            +"医院请农民吃饭骗保"+"</span><div><p style='padding-left: 8px; padding-right: 8px; padding-top: 47px;'><strong>"
                            +"医院请农民吃饭骗保"+"</strong><span class='sub_title'>"
                            +"贵州查出大量医患合谋骗保 医院请吃饭组织农民住院"+" </span></p></div></a>"
                            );
                }else if(i==13){
                    $("#box_14").html("");
                    $("#box_14").append(" <a href='http://news.haosou.com/ns?q=5%E9%83%A8%E9%97%A8%E7%BA%A6%E8%B0%88%E4%B8%93%E8%BD%A6&src=newsresou&tn=news'target='_blank' class='txt tp_11' style='line-height: normal;'><span class='main_title' style=style='padding-top: 63px;'>"
                            +"5部门约谈专车企业"+"</span><div><p style='padding-left: 8px; padding-right: 8px; padding-top: 47px;'><strong>"
                            +"5部门约谈专车企业"+"</strong><span class='sub_title'>"
                            +"5部门约谈滴滴打车等4家专车平台 要求限期整改"+" </span></p></div></a>"
                            );
                }else if(i==14){
                    $("#box_15").html("");
                    $("#box_15").append(" <a href='http://news.haosou.com/ns?q=%E5%BB%96%E5%87%A1%E6%96%A5%E6%B6%89%E7%88%B6%E4%BA%B2%E8%B0%A3%E8%A8%80&src=newsresou&tn=news'target='_blank' class='img tp_11' style='line-height: normal;'><img src='jpg/t01a78a10c7288745a2.jpg'><span class='main_title' style='visibility: hidden;padding-top: 63px;'>"
                            +"廖凡斥涉父亲谣言"+"</span><div style='height: 0px; visibility: visible;'><p style='padding-left: 8px; padding-right: 8px; padding-top: 47px;'><strong>"
                            +"廖凡斥涉父亲谣言"+"</strong><span class='sub_title'>"
                            +"廖凡斥涉父亲谣言 称伤及无辜老人目的何在谣言真相被曝光"+" </span></p></div></a>"
                            );
                }else if(i==15){
                    $("#box_16").html("");
                    $("#box_16").append(" <a href='http://news.haosou.com/ns?q=%E6%8D%A2%E5%8F%91%E5%9E%8B%E8%BA%B2%E6%8A%93%E6%8D%95&src=newsresou&tn=news'target='_blank' class='txt tp_21' style='line-height: normal;'><span class='main_title' style='padding-top: 54px;'>"
                            +"换发型躲抓捕"+"</span><div><p style='padding-top: 56px;'><strong>"
                            +"换发型躲抓捕"+"</strong><span class='sub_title'>"
                            +"换发型躲抓捕 自以为聪明最终还是被抓到"+" </span></p></div></a>"
                            );
                }else if(i==16){
                    $("#box_17").html("");
                    $("#box_17").append(" <a href='http://news.haosou.com/ns?q=%E8%8B%B1%E7%8E%8B%E5%AE%A4%E6%95%AC%E5%91%8A%E7%8B%97%E4%BB%94%E9%98%9F&src=newsresou&tn=news'target='_blank' class='img tp_11' style='line-height: normal;'><img src='jpg/t0125e6de96612ef634.jpg'><span class='main_title' style='visibility: hidden; padding-top: 63px;'>"
                            +"英王室敬告狗仔队"+"</span><div><p style='padding-left: 8px; padding-right: 8px; padding-top: 47px;'><strong>"
                            +"英王室敬告狗仔队"+"</strong><span class='sub_title'>"
                            +"英王室发声明敬告 狗仔队 :离乔治小王子远点"+" </span></p></div></a>"
                            );
                }else if(i==17){
                    $("#box_18").html("");
                    $("#box_18").append(" <a href='http://news.haosou.com/ns?q=%E6%9D%8E%E5%85%8B%E5%BC%BA%E5%93%80%E6%82%BC%E7%89%BA%E7%89%B2%E8%80%85&src=newsresou&tn=news'target='_blank' class='img tp_11' style='line-height: normal;'><img src='jpg/t01dc0dc4f58bc9c311.jpg'><span class='main_title' style='visibility: hidden; padding-top: 63px;'>"
                            +"李克强哀悼牺牲者"+"</span><div><p style='padding-left: 8px; padding-right: 8px; padding-top: 47px;'><strong>"
                            +"李克强哀悼牺牲者"+"</strong><span class='sub_title'>"
                            +"李克强鞠躬哀悼牺牲者：对非现役一视同仁"+" </span></p></div></a>"
                            );
                }else if(i==18){
                    $("#box_19").html("");
                    $("#box_19").append(" <a href='http://news.haosou.com/ns?q=%E5%88%98%E8%B0%A6%E5%8D%87%E7%BA%A7%E5%BD%93%E7%88%B8%E7%88%B8&src=newsresou&tn=news'target='_blank' class='txt tp_11' style='line-height: normal;'><img src='jpg/t01dc0dc4f58bc9c311.jpg'><span class='main_title' style='padding-top: 63px;'>"
                            +"刘谦升级当爸爸"+"</span><div><p style='padding-left: 8px; padding-right: 8px; padding-top: 56px;'><strong>"
                            +"刘谦升级当爸爸"+"</strong><span class='sub_title'>"
                            +"刘谦升级当爸爸 妻子产下男婴"+" </span></p></div></a>"
                            );
                }else if(i==19){
                    $("#box_20").html("");
                    $("#box_20").append(" <a href='http://news.haosou.com/ns?q=%E4%B8%AD%E7%BA%AA%E5%A7%94%E6%8A%AB%E9%9C%B2%E5%B7%A1%E8%A7%86%E6%95%85%E4%BA%8B&src=newsresou&tn=news'target='_blank' class='img tp_11' style='line-height: normal;'><img src='jpg/t013a08e32829e71733.jpg'><span class='main_title' style='visibility: hidden; padding-top: 63px;'>"
                            +"中纪委披露巡视故事"+"</span><div><p style='padding-left: 8px; padding-right: 8px; padding-top: 47px;'><strong>"
                            +"中纪委披露巡视故事"+"</strong><span class='sub_title'>"
                            +"中纪委披露巡视故事:受贿数千万官员一天被拉下马"+" </span></p></div></a>"
                            );
                }
			
			};
			
			
}
*/

