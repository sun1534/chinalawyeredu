//menuDrop(".selType","li","current","#selTypeList");

$(function(){
var _wrap=$('#miniNoticeList');
var _interval=2000;
var _moving;
_wrap.hover(function(){
clearInterval(_moving);
},function(){
_moving=setInterval(function(){
var _field=_wrap.find('li:first');
var _h=_field.height();
_field.animate({marginTop:-_h+'px'},600,function(){
_field.css('marginTop',0).appendTo(_wrap);
})
},_interval)
}).trigger('mouseleave');
});

function tabtoggle(e,o,style,style_){
	if($(o).is(":hidden")){
		$(e).html("<span>收起</span><em></em>");
		$(e).attr('title','收起');
		$(o).slideDown("fast",function(){ $(o).show(); });
		$(e).addClass(style_);
		$(e).removeClass(style);
	}else{
		$(e).html("<span>展开</span><em></em>");
		$(e).attr('title','展开');
		$(o).slideUp("fast",function(){ $(o).hide(); });
		$(e).addClass(style);
		$(e).removeClass(style_);
	}

}



$(document).ready(function(){
	$(".msgDown").click(function(){
			$.jDialog({
				___title:"短信下载",
				___content:"url:get?../coupon/sendMsg.action",
				___width:"660",
				___height:"280",
				___drag:"___boxTitle",
				___showbg:true
			});
		});
	$(".printDown").click(function(){
			$.jDialog({
				___title:"打印",
				___content:"url:get?../coupon/printDown.action",
				___width:"660",
				___height:"200",
				___drag:"___boxTitle",
				___showbg:true
			});
	});
	$(".WapDown").click(function(){
			$.jDialog({
				___title:"Wap下载",
				___content:"url:get?../coupon/sendMsg.action",
				___width:"660",
				___height:"280",
				___drag:"___boxTitle",
				___showbg:true
			});
	});
	$(".cartDwon").click(function(){
			$.jDialog({
				___title:"一卡通",
				___content:"url:get?../loadbuliding.html",
				___width:"660",
				___height:"420",
				___drag:"___boxTitle",
				___showbg:true
			});
	});

	$("#memberNoticeList li a").click(function(){
		$.jDialog({
			___title:"公告详情",
			___content:"url:get?../notice/viewPopNotice.action",
			___width:"600",
			___height:"440",
			___drag:"___boxTitle",
			___showbg:false
		});
	});


});


