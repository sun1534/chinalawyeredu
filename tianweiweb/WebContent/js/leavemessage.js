$(document).ready(function(){
//	var msg1;
//	var msg2;
//	var msg3;
//	$.ajax({
//		type:"post",
//    	url:"../home/messageCountShow.action",//url:"../message/displaymessagecount.action",
//    	success:function(data){
//    	   if(data.length>20){
//    	   	data="0,0,0";
//    	   }
//		   msgcnt=data.split(",");
//		   msg1=packmsg(msgcnt[0]);
//		   msg2=packmsg(msgcnt[1]);
//		   msg3=packmsg(msgcnt[2]);
//		   if(!isNaN(msg1)){
//    	   		msg1=0;
//    	   }
//    	   if(!isNaN(msg2)){
//    	   		msg2=0;
//    	   }
//    	   if(!isNaN(msg3)){
//    	   		msg3=0;
//    	   }
//		   $("#topshortmsg1").html(msg1+"条");
//		   $("#topsysmsg1").html(msg2+"条");
//		   $("#topreply1").html(msg3+"条");
//    	}    	
//	});
//	$.Jxq.tab("#myselfmovement","li","#myself_con",".con_","mouseover");

})


/*
 * 删除回复后不重新刷新页面
 */
function deletereply1(messageid1,replyfloor1,viewUserid1){	
	//alert("replyfloor:"+replyfloor+";messageid:"+messageid+",viewUserid=="+viewUserid);
	if(messageid1=="close"){
		$("#os").hide();
		 $("#pb2").hide();
		 $("#pb2").attr("value","");
		 $("#pb2shareid").attr("value","");
		 }else if(messageid1=="delete"){
			    var a=$("#pb2").attr("value");
				var c= "comm"+a;
			 	var b=$("#os").attr("value");
			 	var d="comm"+b;
			 	viewUserid1=$("#pb2shareid").attr("value");
			 $.ajax({
					type: "POST",
					url:"../message/delleavemessage.action",
					data:"messageid=" +a+"&&viewUserid="+viewUserid1,
					beforeSend:function(){
					},
					success:function(data){
						$.ajax({
							type: "POST",
							url:"../message/leavemessagelist.action",
							data:"messageid=" + b+"&&viewUserid="+viewUserid1,
							beforeSend:function(){
						    },
						 success:function(usermessagelist){
							$("#"+d).empty();
							$("#"+d).append(usermessagelist);
						    }});
			      }});
			 $("#os").hide();
			 $("#pb2").hide();
			 $("#pb2").attr("value","");
			 $("#pb2shareid").attr("value","");
		}else{
		 $("#os").show();
		 $("#os").attr("value",replyfloor1);
		 $("#pb2").attr("value",messageid1);
		 $("#pb2shareid").attr("value",viewUserid1);
		 $("#pb2").show();
		}
}
/*
 * 增加留言
 */

function addmessage(){
	var content = document.form1.levid.value;
	var viewUserid= document.form1.viewUserid.value;
	if(content==null||content <= 0){
		alert("请输留言内容!");
		document.form1.levid.focus();
		return false;
	}else	
	{
		document.form1.action = "leavemessage.action?viewUserid="+viewUserid;
	    document.form1.submit();
	    return true;
	}
	
}

/*
 * 删除留言
 */

function deleteleave(messageid,viewUserid){
	if(messageid=="close"){
		 $("#os").hide();
		 $("#pb").hide();
		 $("#pb").attr("value","");
	}else if(messageid=="delete"){
		 window.location.href = "../message/delleavemessage.action?messageid="+ $("#pb").attr("value")+"&&viewUserid="+$("#tt").attr("value")+"";	
	}else if(messageid=="home"){
		 //window.location.href = "../message/delleavemessage.action?ishome='ishome'&messageid="+ $("#pb").attr("value")+"&&viewUserid="+$("#tt").attr("value")+"";
		$("#os").hide();
		$("#pb").hide();
		$.ajax({
			type: "POST",
			url:"../message/delleavemessage.action",
		    data:"messageid="+ $("#pb").attr("value")+"&&viewUserid="+$("#tt").attr("value")+"",
			success:function(data){
				setContent("../message/homeleveamessage.action?viewUserid="+$("#viewUserid").attr("value"),"#leavemessagediv");
			}
		});
		$("#pb").attr("value","");
	}else{
		$("#os").show();
		$("#pb").attr("value",messageid);
		$("#tt").attr("value",viewUserid);
		$("#pb").show();
	}
}
/*
 * 增加留言的回复
 */
function addComm(messageid){
	var c= "comm"+messageid;
	var b= "t"+messageid;
	if($("#"+b).attr("value")==null||$("#"+b).attr("value")<=0){
		return false;
	}
	$.ajax({
		type: "POST",
		url:"../message/leavemessage.action",
	    data:"content=" + $("#"+b).attr("value")+"&&viewUserid="+$("#viewUserid").attr("value")+"&&messageid="+messageid,
		success:function(data){
			$.ajax({
				type: "POST",
				url:"../message/leavemessagelist.action",
				data:"messageid="+messageid+"&&viewUserid="+$("#viewUserid").attr("value"),
				success:function(shareComList){
					$("#"+c).empty();
					$("#"+c).append(shareComList);
			    }});
		$("#"+b).attr("value","");

		}});
	return false;
}




/*
 * 对回复的收取
 */
function closereply(messageid,viewUserid){
	var b= "b"+messageid;
	var a="a"+messageid;
	$("#"+a).show();
	$("#"+b).hide();
	 
}


/*
 * 对回复的展开
 */
function showreplay(shareid,viewUserid){
	var jiantou=$("#"+"jiantou"+shareid);
	var c= "comm"+shareid;
	var commdiv=$("#"+c);
	if(commdiv.is(":hidden")){
		commdiv.show();
		jiantou.removeClass("jiantou");
		jiantou.addClass("jiantou2");
	}else{
		jiantou.removeClass("jiantou2");
		jiantou.addClass("jiantou");
		commdiv.hide();
		return;
	}
	//$("#"+b).show();
	$.ajax({
		type: "POST",
		url:"../message/leavemessagelist.action",
		data:"messageid=" + shareid+"&&viewUserid="+viewUserid,
	success:function(usermessagelist){
		commdiv.empty();
		commdiv.append(usermessagelist);		
	}});
}


function getpage(pageno){
	document.pageForm.pageNo.value=pageno;
	document.pageForm.submit();
}


function msgcentersend(){
	if($("#msgcontent").attr("value")==""){
		
	}else{
		
	}
}

function checkall(obj){
	var livalue="input[name='"+obj.attr("id")+"i']";
	if(obj.attr("checked")){
		$(livalue).attr("checked",true);
	}else{
		$(livalue).attr("checked",false);
	}
	
}
function showlist(obj){
	var bodyobj=obj.parent().parent().parent().parent().children(".contentmain-body");
	bodyobj.toggle();
	if(bodyobj.is(":hidden")){
		obj.children().hide();
		obj.children().next().show();
	}else{
		obj.children().show();
		obj.children().next().hide();
	}
}
function show1(obj){
	hideall();
	$(obj).show();
	$(obj+"li").attr("class","current");
}
function hideall(){
	$("#tealist").hide();
	$("#prtlist").hide();
	$("#stulist").hide();
	
	$("#tealistli").attr("class","");
	$("#prtlistli").attr("class","");
	$("#stulistli").attr("class","");
}
$(document).ready(function(){
	var options={success:showResponse};
	$("#saveBtn1").click(function(){
	  $("#form1").ajaxSubmit(options);
	});
});

function showResponse(data){
	$.blockUI({ message: data });
}


function backmessage(parentid,srcuserid){
	$.ajax({
       type: "POST",
       url:"../message/backMessageaction!input.action",
       data:"parentid="+parentid+"&srcuserid="+srcuserid,
       success:function(data){
       	if(data.length<1000){
       		$.blockUI({ message:data});
       	}else{
       		document.body.innerHTML=data;
       	}
    	}});
    	
}