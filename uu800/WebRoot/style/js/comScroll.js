$(function(){
	var scrtime;
 	$("#yhHotListList").hover(function(){
		clearInterval(scrtime);
	},function(){

	scrtime = setInterval(function(){
		var $ul = $("#yhHotListList ul");
		var liHeight = $ul.find("li:last").height();
		$ul.animate({marginTop : liHeight+0 +"px"},1000,function(){

		$ul.find("li:last").prependTo($ul)
		$ul.find("li:first").hide();
		$ul.css({marginTop:0});
		$ul.find("li:first").fadeIn(1000);
		});
	},3000);

	}).trigger("mouseleave");


});



