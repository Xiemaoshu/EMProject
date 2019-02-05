
var goUrl = "";
var spanObj = null;
var timeNumber = 0;
$(function(){
	 goUrl = $("#goUrl").attr("href");
	 spanObj = $("#mytime");
	 timeNumber = spanObj.html();
	function jump(timeNumber) {
		window.setTimeout(function(){
			timeNumber--;
			if(timeNumber > 0) {
				spanObj.text(timeNumber);
				jump(timeNumber);
			} else {
				location.href=goUrl;
			}
		}, 1000);
	}
	jump(timeNumber);
});


