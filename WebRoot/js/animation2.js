function Alarm2() {
	var cutdown = null;
	var rightSound = null;

	soundManager.setup({
		preferFlash : true,
		flashVersion : 9,
		url : 'swf/',
		useHighPerformance : true,
		debugMode : false, // disable debug mode
		onready : function() {
			// soundManager is ready to use (create sounds and so on)
			cutdown = soundManager.createSound({
				id : 'cutdown',
				url : 'music/BEEP.mp3',
				volume : 50,
				multiShot : true,
				autoLoad : true
			});

			/*
			 * rightSound = soundManager.createSound({ id: 'rightSound', url:
			 * 'music/BEEP.mp3', volume: 50, multiShot: true, autoLoad: true });
			 */
			// for(i = 1; i <= 3; i++){
			// setTimeout("tocutdown()", i * 900);
			setTimeout("tocutdown()", 900);
			// }
			/* setTimeout("finish()", 5000); */
			// rightSound.play();
			// cutdown.play();
		}
	})
}
	function tocutdown() {
		cutdown.play();
	}
	function stopdown() {
		cutdown.pause();

		// cutdown.mute();

	}
	/*
	 * function finish(){ rightSound.play(); }
	 */

