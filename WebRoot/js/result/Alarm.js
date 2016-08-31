$(document).ready(function() {	
	searchAlarm();//报警查询
});
/**
 * 查询当日警报
 */
function searchAlarm() {
	var g_size=78;
	$.ajax({
		async : false,
		dataType : 'json',
		type : 'post',
		data : "g_size="+g_size,
		url : "findAlarmInfo.action",
		success : function(json) {
			if (json.g_message == "success") {				
				Alarm();
			}else{
				
			}
		}
		
	});

}

function Alarm() {
	soundManager.setup({
		preferFlash : true,
		flashVersion : 9,
		url : 'swf/',
		useHighPerformance : true,
		debugMode : false, // disable debug mode
		onready : function() {		
			cutdown = soundManager.createSound({
				id : 'cutdown',
				url : 'music/BEEP2.mp3',
				volume : 50,
				multiShot : true,
				autoLoad : true
			});				
			cutdown.play();	
			setInterval_Alarm();
		}
	 
	});

}

var inter_Alarm;
/**
 * 延迟判断
 */
var over = false;
var inter_Alarm;
function setInterval_Alarm(){
	over = false;	
	inter_Alarm = setInterval(start_alarm,38000);
}
function start_alarm(){
	if(over){
		clearInterval(inter_Alarm);
		return;	
	}else{
		cutdown.play();
	}

}
/*	function tocutdown() {
		cutdown.play();
	}*/
	function stopdown() {
		var g_size=78;
		$.ajax({
			async : false,
			dataType : 'json',
			type : 'post',
			data : "g_size="+g_size,
			url : "modifyAlarmInfo.action",
			success : function(json) {
				if (json.g_message == "success") {
					over = true;
					cutdown.pause();
					sAlert("温馨提示:", "<Br>警报解除成功！！！");					
				} else {
					sAlert("温馨提示:", "<Br>警报解除失败！！！");
				}

			},
			error : function() {
				sAlert("温馨提示:", "<Br>对不起，请重新刷新！！！");
			}
		});

	}


