function defaultFoucus(o){
	if(!o) return;
	var o='#'+o,p=0,c,delayRun=null,t=null,arr=[],slideTime=800,len=$('.defaultFoucus_list li').length;
	arr.push('<div class="defaultFoucus_btn"><ul>');
	$(o).find('li a').each(function(i){
		arr.push('<li><a  href="' + $(this).attr('href') + '">'+'<img src="'+$(this).children('img').attr('src')+'"/>'+'<span>'+$(this).children('img').attr('alt')+'</span></a></li>');
	});
	arr.push('</ul></div>');
	$(o).append(arr.join(''));
	$('.defaultFoucus_btn li').eq(p).addClass('current');
	$('.defaultFoucus_list img').eq(p).show();
	$('.defaultFoucus_btn li').hover(function(){
		p = $('.defaultFoucus_btn li').index($('.defaultFoucus_btn li.current'));
		c = $('.defaultFoucus_btn li').index($(this));
		delayRun = setTimeout(function(){
			addCurrent(p,c);
		}, 400);
	},function(){
		clearTimeout(delayRun);
		p = c;
	});
	t = setInterval(init,3000);
	$(o).hover(function(){
		clearInterval(t);
	}, function(){
		t = setInterval(init,3000);
	});

	function init(){
		if(p==(len-1)){
			addCurrent(p,0);
		}else{
			addCurrent(p,p+1);
		}
		p = (p+1)%len;
	}
	function addCurrent(p,c){
		if(p==c) return;
		$('.defaultFoucus_btn li').removeClass('current').eq(c).addClass('current');
		if (slideTime <= 0) {
			$('.defaultFoucus_list img').eq(p).hide();
			$('.defaultFoucus_list img').eq(c).show();

		}else{
			$('.defaultFoucus_list img').eq(p).fadeOut(slideTime);
			$('.defaultFoucus_list img').eq(c).fadeIn(slideTime);
		}
	}
}

$(function(){
	defaultFoucus('defaultFoucus');
})

/*

function eachShowNo(){
	$("#play_list a img").each(function(i){
		$("<li id=no_"+i+">"+parseInt(++i)+"</li>").appendTo("#play_text_no");
	});
}
eachShowNo();
var t = n = 0
$(function(){
	$("#play_info").html($("#play_list a:first-child").find("img").attr('alt'));
	count = $("#play_list a").size();
	if(count>=2){
	$("#play_list a:not(:first-child)").hide();
	$("#play_text li:first-child").addClass("current");
	$("#play_info").click(function(){window.open($("#play_list a:first-child").attr('href'), "_blank")});
	$("#play_text li").click(function() {
		var i = $(this).text() - 1;
		n = i;
		if (i >= count) return;
		$("#play_info").html($("#play_list a").eq(i).find("img").attr('alt'));
		$("#play_info").unbind().click(function(){window.open($("#play_list a").eq(i).attr('href'), "_blank")})
		$("#play_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000);
		$(this).addClass("current").siblings().removeClass("current")

	});
	t = setInterval("showAuto()", 3000);
	$("#GG").hover(function(){clearInterval(t)}, function(){t = setInterval("showAuto()", 3000);});
	}else {
		return
	}

});

function showAuto()
{
	n = n >= (count - 1) ? 0 : ++n;
	$("#play_text li").eq(n).trigger('click');

}
*/