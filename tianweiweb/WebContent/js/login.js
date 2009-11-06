function changeImg(){
    var imgSrc = $("#imgObj");
    
    var src = imgSrc.attr("src");
    imgSrc.attr("src",chgUrl(src));
	}
	//时间戳
	//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
	function chgUrl(url){
    var timestamp = (new Date()).valueOf();
    //url = url.substring(0,27);
    if((url.indexOf("&")>=0)){
        url = url + "&timestamp=" + timestamp;
    }else{
        url = url + "?timestamp=" + timestamp;
    }
    return url;
}
	
function stlogin(){
	 $("#loginform").ajaxSubmit({
		 success:function(data){
		 	if(data=="ok"){
		 		location.href="../user/userbaseview.action";
		 	}else{
		 		alert(data);
		 	}
	 	 }
	 });
}


function checkIden(node){
	if(node.selectedIndex == 2){
	var targetNode;
	targetNode = $("#name");
		targetNode.show();
		 $("#phoneui").show();
	$("#teltxt").attr("value",'请输入你家长的手机号')
		}else{
		var targetNode;
		targetNode = $("#name");
		targetNode.hide();
		 $("#username").attr("value",'');//清空内容
		  $("#teltxt").attr("value",'请输入您的手机号')
		$("#usernameid").show();
	}
}
