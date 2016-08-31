/**
 * 
 */

var w,h,className;
function getSrceenWH(){
	w = $(window).width()+250;
	h = $(window).height()+100;
	$('#dialogBg').width(w).height(h);
}

window.onresize = function(){  
	getSrceenWH();
}; 
$(window).resize();  

$(function(){
	getSrceenWH();
	
		
});

/**
 * 关闭弹窗
 */
function closwDiaglog(){
		$('#dialogBg').fadeOut(300,function(){
		$('#dialog').addClass('bounceOutUp').fadeOut();
	});
	setTimeout(hideDivManySetting,1000);//延迟执行
	
}
function hideDivManySetting(){
	$("#unionKeyword_management").css("display","none");//组合关键词不显示
	$("#newKeyword_management").css("display","none");//最新关键词不显示
	$("#sensitiveKeyword_management").css("display","none");//敏感关键词不显示
	$("#netAddr_management").css("display","none");//定位网址不显示
}