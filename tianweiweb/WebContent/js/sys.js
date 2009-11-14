
var islight = false;
function getmessage(){
	var msg1;
	var msg2;
	var msg3;
	$.ajax({
		type:"post",
    	url:"../home/messageCountShow.action",//url:"../message/displaymessagecount.action",
    	success:function(data){
    	   if(data.length>20){
    	   	data="0,0,0";
    	   }
		   msgcnt=data.split(",");
		   msg1=packmsg(msgcnt[0]);
		   msg2=packmsg(msgcnt[1]);
		   msg3=packmsg(msgcnt[2]);
		   if(!isNaN(msg1)){
    	   		msg1=0;
    	   }
    	   if(!isNaN(msg2)){
    	   		msg2=0;
    	   }
    	   if(!isNaN(msg3)){
    	   		msg3=0;
    	   }
		   //$("#topsysmsg").html(msg1+"条");
		   //$("#topshortmsg").html(msg2+"条");
		   //$("#topreply").html(msg3+"条");
		   /*if((msg1!=0||msg2!=0||msg3!=0)&&!islight){
		   		setInterval('lighttitle()', 1000);
		   }*/
		   
		   if(msg2!=0){
		   	setInterval('lighttitle()', 1000);
		   	$("#unreadmsg").show();
		   }else{
		   	$("#unreadmsg").hide();
		   }
    	}    	
	});
}

var t = 0;
var title ;
function lighttitle(){
	if(t == 0){
		document.title="【新消息】"+title;
		t=1;
	}else{
		document.title="【　　　】"+title;
		t=0;
	}
	
}

function packmsg(m){
	var msg;
	if(m>0){
		msg="<img border=0 src='http://58.83.134.61/ln/css/images/icon/newmsg1.gif' />"+m;
	}else{
		msg=m
	}
	return msg;
}

$(document).ready(function(){
	title = $('title').html();
	//alert(title);
	getmessage();
	setInterval('getmessage()', 60000);
})

