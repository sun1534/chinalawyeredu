
/*
	修正弹出层。增加弹出层可以拖动 添加class=dialogMove  即可实现拖动
*/

function showDialogBox(e,w,h){
	//$("html").css("overflow","hidden")
	//$(".blockOverlay").css("overflow","hidden")
	//$(".window").css("overflow","hidden")
	if(!h){
		$(".dialogBoxS").css("height", $(e).height());
		$.blockUI({
            message: $(e),
            css: {
                top:  ($(window).height() - $(e).height()) /2 + 'px',
                left: ($(window).width() - w) /2 + 'px',
                width: w
            }
        });
		dragDiaologBox(e,w);
	}else{
		$(e).css("height",h);
		$(".dialogBoxS").css("height",h);
		$.blockUI({
            message: $(e),
            css: {
                top:  ($(window).height() - h) /2 + 'px',
                left: ($(window).width() - w) /2 + 'px',
                width: w,
				height:h
            }
        });
		dragDiaologBox(e,w,h);
	}
}

function dragDiaologBox(e,w,h){
	if($(e).find(".dialogMove")){
		var _move=false;
		var _x,_y,top,left;
		minLeft = 0;
		minTop = 0;
		maxLeft = $(window).width() - w;
		if(!h){
			maxTop = $(window).height() - $(e).height();
		}else{
			maxTop = $(window).height() - h;
		}
		$(".dialogMove").mousedown(function(e){
				_move=true;
				_x=e.pageX-parseInt($(".blockMsg ").css("left"));
				_y=e.pageY-parseInt($(".blockMsg").css("top"));
				$(e).fadeTo('slow', 0.5);
		});
		$(document).mousemove(function(e){
			if(_move){
				var x=e.pageX-_x;
				var y=e.pageY-_y;

				if((e.pageX - _x) < minLeft)
					left = minLeft;
				else if((e.pageX - _x) > maxLeft)
					left = maxLeft;
				else
					left = e.pageX - _x;
				if((e.pageY - _y) < minTop)
					top = minTop;
				else if((e.pageY - _y) > maxTop)
					top = maxTop;
				else
					top = e.pageY - _y;
				$(".blockMsg").css({top:top,left:left});
			}
		}).mouseup(function(){
		_move=false;$(e).fadeTo("fast", 1);
	  });
	}else{
		return false;
	}
}
function closeDialogBox(e,time){
		if(!time){$.unblockUI();
		}else{
		setTimeout(function(){$.unblockUI();},time);
		}
}
/*
	操作提示
	分遮罩层和无遮罩层2中，提示信息均为居中位置
	默认是2秒自动关闭操作提示
*/
function showOperatMsg(e,w,Overlay){
	if(Overlay=="0"){
		$.blockUI({
            message: $(e),
            fadeIn: 500,
            fadeOut: 500,
            timeout: 2000,
            showOverlay: false,
             css: {
                top:  ($(window).height() - $(e).height()) /2 + 'px',
                left: ($(window).width() - w) /2 + 'px',
                width: w
            }
        });
	}else{
		$.blockUI({
            message: $(e),
            fadeIn: 500,
            fadeOut: 500,
            timeout: 2000,
            showOverlay: true,
             css: {
                top:  ($(window).height() - $(e).height()) /2 + 'px',
                left: ($(window).width() - w) /2 + 'px',
                width: w
            }
        });
	}
}
