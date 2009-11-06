$(function(){
	//FocuesInput();
});

/*文本框 foucus 肖凯明*/
var FocuesInput =function(){
	$.Jxq.focusTxt('#SearchInput','找人...');
	$.Jxq.focusTxt('#addtxtinfo','附加信息(选填，45字内)');
	$.Jxq.focusClass('ie_focus');
};


function showmsg(userid, username,pic){
	$('#content').attr("value","");
//	$("#sendmsg").attr("style","display:block");
	$('#setusername').html(username);
	$('#desuserid').attr("value",userid);
//	alert(pic);
	$('#msg_img').attr("src","http://58.83.134.59/default"+pic);
}

function sendmsg(){
	if($('#content').attr("value")=="")
	{
		alert("发送内容不能为空");
		$('#content').focus();
		return;
	}
//	$("#replyForm").submit();
	$.ajax({
        type: "POST",
        url:"sendmsg.action",
        data:"content=" + $("#content").attr("value") + "&desuserid="
				+ $("#desuserid").attr("value"),
        beforeSend:function(){
        },
        success:function(date){
//        	$.blockUI({ message:data});
        	alert("发送成功");
     }});
}

function delmsg(id, diaryid){
	
	$.Jxq.delTips('#m1'+id,'#delfrereplay',-50,20,220)
	$("#msgid").attr("value",id);
	$("#diayid").attr("value",diaryid);
}


function showrepaly(id){
	
	$.ajax({
        type: "POST",
        url:"gettypegift.action",
        data:"type="+type,
        beforeSend:function(){
			
        	
        },
        success:function(date){
        	$('#r1'+id).empty();
        	$('#r1'+id).append(date);
        	
     }});
}


function deleteReply(){
				//alert(replysid);
	      var id=$("#msgid").attr("value");
	      var diaryid=$("#diayid").attr("value");
	     
			 $.ajax({
					type: "POST",
					url:"../diary/deleteDiaryReply.action",
					data:"id=" + id + "&diaryid=" + diaryid,
					success:function(data){
						var deldiv="#diaryreply_"+id;
						var deldiv_lin="#msglineid_"+id;
						$(deldiv).hide();
						$(deldiv_lin).hide();
		      }});
   $.Jxq.removeTips('#ml1'+id,'#delfrereplay')
}