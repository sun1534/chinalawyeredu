$(function(){
	$('#switchbar').click(function(){
	if ($("#wapFrame",parent.document.body).attr("cols")=="205,9,*"){
			$("#switchbar").removeClass("current");
			$("#switchbar").attr("title","展开左侧菜单");
			$("#wapFrame",parent.document.body).attr("cols","0,9,*");
		}
		else{
			$("#switchbar").addClass("current");
			$("#switchbar").attr("title","关闭左侧菜单");
			$("#wapFrame",parent.document.body).attr("cols","205,9,*");
		}
	})
})
$(function(){
	$('#topSwitchbar').click(function(){
	if ($("#wapFrame",parent.document.body).attr("cols")=="205,9,*"){
			$("#topSwitchbar").addClass("current");
			$("#topSwitchbar").attr("title","展开左侧菜单");
			$("#wapFrame",parent.document.body).attr("cols","0,9,*");
		}
		else{
			$("#topSwitchbar").removeClass("current");
			$("#topSwitchbar").attr("title","关闭左侧菜单");
			$("#wapFrame",parent.document.body).attr("cols","205,9,*");
		}
	})
})
