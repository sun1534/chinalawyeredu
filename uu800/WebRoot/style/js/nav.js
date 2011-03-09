$(document).ready(function(){
	menuDrop("#siteChannel","li","cur",".subChannel");
	menuCurrent("#sSearchTab","li","current");
	tabs("#MsTg","a","#MsTgMain",".tabCon","mouseover");
	tabs("#yhHotTab","a","#yhHotList",".tabCon","mouseover");

	hoverBg("#yhTabBd",".list","current",".list");
	hoverBg("#movieList",".l","current",".l");
	sortHover("#sortTabMain","li","current","li");
	sortHover("#NoticeList","li","current","li");
	menuDrop("#logo","#siteName","current","#allSite");
	hoverBg("#yhSearchList","dl","cur","dl")

});

function sortHover(em,o,c,em_){
if($(em_).length>0){
	 $(em+" "+o).hover(
	  function () {
		$(this).siblings().removeClass(c);
		$(this).addClass(c);
	  },
	  function () {
	  }
	)}else{
		return
	}
}




$(document).ready(function(){
	$("#siteLogin").click(function(){
			$.jDialog({
				___title:"用户登录",
				___content:"url:get?../user/login.action",
				___width:"660",
				___height:"250",
				___drag:"___boxTitle",
				___showbg:true
			});
		});
	$("#siteReg").click(function(){
			$.jDialog({
				___title:"用户注册",
				___content:"url:get?../user/userReg!input.action",
				___width:"660",
				___height:"480",
				___drag:"___boxTitle",
				___showbg:true
			});
		});
$("#sjMap a,#sjMap img").click(function(){
			$.jDialog({
				___title:"商家地图",
				___content:"img:../style/images/tmp/tt.jpg",
				___width:"758",
				___height:"508",
				___drag:"___boxTitle",
				___showbg:true
			});
		});


		$("#extLogin").click(function(){
			$.jDialog({
				___title:"用户登录",
				___content:"url:get?../user/login.action",
				___width:"660",
				___height:"250",
				___drag:"___boxTitle",
				___showbg:true
			});
		});
		$("#siteCenter").click(function(){
			$.jDialog({
				___title:"用户登录",
				___content:"url:get?../user/login.action",
				___width:"660",
				___height:"250",
				___drag:"___boxTitle",
				___showbg:true
			});
		});

		$("#extReg").click(function(){
				$.jDialog({
					___title:"用户注册",
					___content:"url:get?../user/userReg!input.action",
					___width:"660",
					___height:"480",
					___drag:"___boxTitle",
					___showbg:true
				});
			});
		$("#navLogout").click(function(){
			location.href="../user/userLogout.action";
			//$('#navLoginIn').hide();$('#navUnLogin').show();$('#regLogin').show();$('#regLoginIn').hide();
		});
		$("#centerLogout").click(function(){
			location.href="../user/userLogout.action";
			//$('#regLogin').show();$('#regLoginIn').hide();$('#navLoginIn').hide();$('#navUnLogin').show();
		});


		/*
		$("#siteNotice li a").click(function(){
				$.jDialog({
					___title:"公告详情",
					___content:"url:get?noticelist.html",
					___width:"600",
					___height:"440",
					___drag:"___boxTitle",
					___showbg:false
					//___offsets:"right-bottom"
				});

			});
*/



		$("#mobileZone  a").click(function(){
				$.jDialog({
					___title:"正在建立中",
					___content:"url:get?../loadbuliding.html",
					___width:"640",
					___height:"440",
					___drag:"___boxTitle",
					___showbg:true
				});
			});
		$(".loadBuild").click(function(){
				$.jDialog({
					___title:"正在建立中",
					___content:"url:get?../loadbuliding.html",
					___width:"640",
					___height:"440",
					___drag:"___boxTitle",
					___showbg:true
				});
			});



		$(".opDown").click(function(){
			$.jDialog({
				___title:"短信下载",
				___content:"url:get?../coupon/sendMsg.action",
				___width:"660",
				___height:"280",
				___drag:"___boxTitle",
				___showbg:true
			});
		});
		$(".buyBtn").click(function(){
			$.jDialog({
				___title:"团购信息",
				___content:"url:get?../activity/startTuan.action",
				___width:"660",
				___height:"280",
				___drag:"___boxTitle",
				___showbg:true
			});
		});
			$(".ms_btn").click(function(){
			$.jDialog({
				___title:"秒杀信息",
				___content:"url:get?../activity/startMiao.action",
				___width:"660",
				___height:"280",
				___drag:"___boxTitle",
				___showbg:true
			});
		});
			$(".msBtn").click(function(){
			$.jDialog({
				___title:"秒杀信息",
				___content:"url:get?../activity/startMiao.action",
				___width:"660",
				___height:"280",
				___drag:"___boxTitle",
				___showbg:true
			});
		});




	$(".opPrint").click(function(){
			$.jDialog({
				___title:"打印",
				___content:"url:get?../coupon/printDown.action",
				___width:"660",
				___height:"200",
				___drag:"___boxTitle",
				___showbg:true
			});
	});
$(".opFav").click(function(){
			$.jDialog({
				___title:"收藏",
				___content:"url:get?../coupon/shoucang.action",
				___width:"660",
				___height:"200",
				___drag:"___boxTitle",
				___showbg:true
			});
	});


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
  //  $(".memberNavBd:not('.memberNavBd:first')").hide();
    $('.memberNavHd span').click(function(){
	if( $(this).parent().next().is(':hidden') ) {
            $('.memberNavHd ').removeClass('current').next().slideUp();
            $(this).parent().toggleClass('current').next().slideDown();
	}else{
           $(this).parent().toggleClass('current');
            $(this).parent().next().slideUp();
        }
	return false;
    });

});

function hoverBg(em,o,c,em_){
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
			$("#sSearchTxt").attr("value",$(this).attr('rel'))

		});
	});

}

function focusTxt (txtem,defalt_val){
		/*
		txtem 所需清除vlaue的元素Id
		defalt_val 清除默认的value的值
		引用实例
		<input type="text" id="SearchInput" value="搜一下" />
		focusTxt('#SearchInput','搜一下')
		*/
		$(txtem).val(defalt_val).focus(function(){
		var val = $(this).val();
		if(val == defalt_val){
		$(this).val('');
		}
		}).blur(function(){
		var val = $(this).val();
		if(val == ''){
		$(this).val(defalt_val);
		}
		})
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
