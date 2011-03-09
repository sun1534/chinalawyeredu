$(document).ready(function(){
	menuDrop("#menuOther","li","current",".submenuOther");
	toggleNav("#toggleTopNav","#topNavList",450,630,"toggleLeft","slow");
	equalHeight(".h");
	menuCurrent("#topNavList","li","current");
	menuDrop("#siderMenuList","li","current",".siderSubMenu");
	menuDrop("#siderMenuListOther","li","current",".siderSubMenu");
	//hoverDropMenu(".siderMenu","li","current",".siderSubMenu")
	tabs("#focusNews_Tab","a",".tab_bd",".tab_con","mouseover");

});

function tabs(o,otag,e,eclass,act){
	  $(o+" "+otag).each(function(index){
		$(this).bind(act,function(){
			if($(this).parent().hasClass("current")){
				return;
			}
			$(o+" "+otag).parent().removeClass("current");
			$(this).parent().addClass("current");
			$(e).find(eclass).eq(index).show().siblings(eclass).hide();
		});
	 });
}

$(function(){
    $(".toggleBd:not('.toggleBd:first')").hide();
    $('.toggleHd').click(function(){
	if( $(this).next().is(':hidden') ) {
            $('.toggleHd').removeClass('current').next().slideUp();
            $(this).toggleClass('current').next().slideDown();
	}else{
            $(this).toggleClass('current');
            $(this).next().slideUp();
        }
	return false;
    });
//	$(".siderNav li:last-child a").addClass("ac");
//	$(".siderNav a").click(function(){
//		$(this).parent().addClass("current").siblings().removeClass("current");
//	})
});



function hoverDropMenu(em,o,c,em_){
$(".l a").bind('focus',function(){if(this.blur){this.blur();}})
if($(em_).length>0){
	 $(em+" "+o).hover(
	  function () {
		$(this).addClass(c);
	  },
	  function () {
		$(this).removeClass(c);
	  }
	)}else{
		return
	}
}

function menuDrop(em,o,c,em_){
	if($(em_).length>0){
		$(em+" "+o).hover(
			function () {$(this).addClass(c);
		},function () {
			$(this).removeClass(c);
			}
		)
	}else{
		return
	}
}

function clearFocus(){
	$('a,input[type="button"]').bind('focus',function(){
	if(this.blur){ this.blur();}});
}

function toggleNav(em,o,w,w_,c,s){
	clearFocus();
	$(em).toggle(function(){
		$(o).animate({width: w_}, s);
		$(this).addClass(c);
	},function(){
		$(o).animate({width: w}, s);
		$(this).removeClass(c);
	});
}

function menuCurrent(em,o,c){
	$(em+" "+o).each(function(){
		$(em+" "+o).click(function()
		{
			$(this).addClass(c).siblings().removeClass(c);
		});
	});
}
function equalHeight (group) {
	if($(group).length>0){
	tallest = 0;
	$(group).each(function() {
		thisHeight = $(this).height();
		if(thisHeight > tallest) {tallest = thisHeight;}
	});
	$(group).height(tallest);
	}else{
		return
	}
}
jQuery.cookie = function(name, value, options) {
	if (typeof value != 'undefined') {
		options = options || {};
		if (value === null) {
			value = '';
			options.expires = -1;
		}
		var expires = '';
		if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
			var date;
			if (typeof options.expires == 'number') {
				date = new Date();
				date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
			} else {
				date = options.expires;
			}
			expires = '; expires=' + date.toUTCString();
		}
		var path = options.path ? '; path=' + options.path : '';
		var domain = options.domain ? '; domain=' + options.domain : '';
		var secure = options.secure ? '; secure' : '';
		document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
	} else {
		var cookieValue = null;
		if (document.cookie && document.cookie != '') {
			var cookies = document.cookie.split(';');
			for (var i = 0; i < cookies.length; i++) {
				var cookie = jQuery.trim(cookies[i]);
				if (cookie.substring(0, name.length + 1) == (name + '=')) {
					cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
					break;
				}
			}
		}
		return cookieValue;
	}
};
/*
$("#changeControl").click(function(){
		changeControl("#changeControl","#sider","#inMain","open","close");
});
*/
$(function(){
	var $li =$("#changeControl");
	$li.click(function(event){
		changeControl( this.id,"#changeControl","#sider","#inMain","open","close");
	});
	var cookie_n = $.cookie( "changeControl");
	if (cookie_n) {
		changeControl( cookie_n ,"#changeControl","#sider","#inMain","close","open");
	}
});

function changeControl (cookies,e,o,b,style,style_){
	if($(o).length>0){
		if($(o).is(":hidden")){
			$(e).attr('title','关闭左侧菜单');
			$(o).slideDown(8,function(){ $(o).show(); });
			$(b).animate({ marginLeft: "230px"}, 100 );
			$(e).addClass(style_);
			$(e).removeClass(style);
			$.cookie( "changeControl" ,  null , { path: '/', expires: 2 });
		}else{
			$(e).attr('title','展开左侧菜单');
			$(o).slideUp(8,function(){ $(o).hide(); });
			$(b).animate({ marginLeft: "0px"}, 100 );
			$(e).addClass(style);
			$(e).removeClass(style_);
			$.cookie( "changeControl" ,  cookies , { path: '/', expires: 2 });
		}
	}else{
		return
	}
}
