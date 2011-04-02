$(function(){
	$("#open").show();
	//加载时设置高度
	//setMenuHeight();
	//点击一级菜单切换
	$("#leftmenu h3").click(function(){
		//$("#menu ul:visible").slideUp("fast").hide();
		$("#leftmenu ul:visible").hide();
		$("#open").removeAttr("id");
		$("#now_h3").removeAttr("id");
		$(this).attr("id","now_h3");
		$(this).next("ul").show();
		//$(this).next("ul").css("height",hi);
		$(this).next("ul").attr("id","open");
	});

	//点击二级菜单
	$("#leftmenu a").click(function(){
		$("#aNow").removeAttr("id");
		$(this).attr("id","aNow");
	});
	hoverBg("#leftmenu","h3","over");
});

//当窗口大小改变时重新设置大小
/*
$(window).resize(function(){
	setTimeout(setMenuHeight,0);
});
*/
//设置#open的高度的函数
/*
function setMenuHeight(){
	hi = (document.compatMode == 'CSS1Compat') ? document.documentElement.clientHeight : document.body.clientHeight;  //获取可见区大小
	hi = hi - 80;  //84是3个h3的高度加上 #menu 的margin-top 和 #menuWarp 的 bottom
	$("#open").css("height",hi);
}
*/

function hoverBg(em,o,c){
	 $(em+" "+o).hover(
	  function () {
		$(this).addClass(c);
	  },
	  function () {
		$(this).removeClass(c);
	  }
	);
}
