$(function(){$('#yhScollMain').simple_slider({'leftID': 'sLeft','rightID': 'sRight'})})
$(function(){$('#yhScollMain2').simple_slider({'leftID': 'sLeft2','rightID': 'sRight2'})})
;(function($) {
var totalCount = 0,selector,options,firstPos = 0,index=0;dis=2;
	$.fn.simple_slider = function(settings) {
		settings = $.extend({}, $.fn.simple_slider.defaults, settings);
		selector = this.selector;
		options = settings;
		totalCount = $(selector + " .l").size();
		$("#" + options.leftID).click(function (){
					_goLeft();
		});
		$("#" + options.rightID).click(function (){
					_goRight();
		});
		_pagefrash();

		function _pagefrash(){
			if($(selector + " .l").length<=dis){
				$("#" + options.leftID).addClass("arrLeft_disabled");
				$("#" + options.rightID).addClass("arrRight_disabled");
			}
			$(selector + " .l").each(function(i){
				if (i >= index+options.display || i < index){
					$(this).fadeOut();
				}else{
					$(this).fadeIn();
				}
			});
			_checkNavigation();
		}
		function _goLeft(){
			if (index > 0){
				index=index-dis;
				if(index<0)
				{
					index=0;
				}
				_pagefrash();
			}
		}
		function _goRight(){
			if (index < totalCount-dis){
				index=index+dis;
				if(index>totalCount-dis)
				{
					index=totalCount-dis;
				}
				_pagefrash();
			}
		}
		function _checkNavigation(){
			if (index == 0){
				$("#" + options.leftID).addClass("arrLeft_disabled");
				$("#" + options.leftID).removeClass("arrLeft");
			} else {
				$("#" + options.leftID).removeClass("arrLeft_disabled");
				$("#" + options.leftID).addClass("arrLeft");
			}
			if ( index >= totalCount-dis){
				$("#" + options.rightID).addClass("arrRight_disabled");
				$("#" + options.rightID).removeClass("arrRight");
			} else {
				$("#" + options.rightID).removeClass("arrRight_disabled");
				$("#" + options.rightID).addClass("arrRight");


			}
		}

	}
	$.fn.simple_slider.defaults = {
		leftID				:	null,
		rightID				:	null
	};

})(jQuery);