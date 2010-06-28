<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" id="Tophtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<link rel="stylesheet" type="text/css" href="../css/reset.css" /> 
<link rel="stylesheet" type="text/css" href="../css/header.css" /> 
<script type="text/javascript" src="../js/jquery.js"></script> 
<title>${sysName}-主菜单</title> 
<script language="javascript" type="text/javascript">
var issubmit=false;
var f5pressed=false;
function submitform (){
	 issubmit=true;
	 document.form1.submit();
}
function exitform(){
	 // alert("f5pressed:::"+f5pressed+",,,"+window.location.href);
  if(!issubmit&&!f5pressed){//如果没有提交,直接关闭窗口的话  
   // var url="../commonajax/ajaxlogout.action";
   // $.post(url,{now:new Date().getTime()});
     
    $.getJSON("../commonajax/ajaxlogout.pl", {"now":new Date().getTime()}, function(resp){
      }
    ); 
  }
  f5pressed=false;
}
function pressedF5(){
	//  alert("f5pressed");
	 f5pressed=true;
}
$(document).ready(function(){
//getGbAlarmCnts();
 setTimeout('getGbAlarmCnts()',5*1000);
});
var ajaxurl="../alarmajax/getGbLinkAlarmCnt.action";
var islight = false;
var hd;
var hascancel=false;
var t=0;
var counts=0;
function getGbAlarmCnts(){
var now=new Date().getTime();
$.getJSON(ajaxurl+"?now="+now,function(json){
  var ac=json.alarmcount;
  if(ac>0&&!islight){
	islight=true;
    hd=setInterval('lighttitle()', 1000);
    hascancel=false;
    counts=0;
  }else if(ac<=0){
    islight=false;
    top.document.title="${sysName}";
    if(hd&&!hascancel){
      clearInterval(hd);
      hascancel=true;
    }
  }
});
setTimeout('getGbAlarmCnts()',30*1000);
}
function lighttitle(){
	if(t == 0){
		top.document.title="【链路告警】${sysName}";
		t=1;
	}else{
		top.document.title="【　　　　】${sysName}";
		t=0;
	}
	counts++;
	//if(counts>60){
	//   if(hd&&!hascancel){
	//	 clearInterval(hd);
	//	 hascancel=true;
    //  }
	//   top.document.title = "${sysName}";
	//}
}
</script>
</head> 
 
<body onunload="exitform()"> 
	<div class="header"> 
		<h1 id="login"><img src="../images/logo.jpg" alt="" /></h1> 
		<!-- 登录用户信息 开始 --> 
		<div class="login-userinfo"> 
			<div class="userinfo"> 
			<form name="form1" method="post" action="../common/logout.action" target="_top">
				<ul> 
					<li><b><s:property value="#session.loginUser.username"/></b>，欢迎您！</li> 
					<li><a href="../common/passwdChange!input.action" target="mainFrame" title="修改密码" class="infoset">修改密码</a></li> 
					<li><a href="javascript:void(0)" onclick="submitform()" target="_top" title="注销" class="logout">注销</a></li> 
				</ul> 
				</form>
			</div> 
		</div> 
		<!-- 登录用户信息 结束 --> 
		<!-- 快捷菜单 开始 --> 
		<div class="quickmenu" id="quickmenu"> 
				<ul> 
				<s:iterator value="#session.loginUser.topMenus" status="stat">
				<s:if test="ismenu">
				<li><a id="${rightcode}" title="${rightname}" href="javascript:void(0)"><span>${rightname}</span></a></li> 
				</s:if>
				</s:iterator>
			    <!-- 
					<li class="current"><a id="menu1" title="概　　况" href="javascript:void(0)"><span>概　　况</span></a></li> 
					<li><a  id="menu2" href="javascript:void(0)" title="记录查询"><span>记录查询</span></a></li> 
				 --> 
				</ul> 
		</div> 
		<!-- 快捷菜单 结束 --> 
		<!-- 注销  
		<a title="返回首页" class="backhome"  href="javascript:void(0)" onclick="parent.leftFrame.location='left.html';parent.mainFrame.location='info.html';">返回首页</a>
		--> 
	</div> 
	<script type="text/javascript"> 

	$("#quickmenu ul li:first").addClass("current");
	<s:iterator value="#session.loginUser.topMenus" status="stat">
		loadUrl("#${rightcode}","${rightcode}","${linkurl}","${hasChild}");
	</s:iterator>
	/*
	loadUrl("#quickmenu","#menu1","left.html","info.html");
   */ 
 
 function loadUrl(o,rightcode,infourl,haschild){
			$("#quickmenu "+o).click(function()
			{
			_loadUrl(rightcode,infourl,haschild,true);
			   
			});
	}	
	</script> 
<script type="text/javascript" src="../js/top.js"></script> 
<script type="text/javascript">
function _loadUrl(rightcode,infourl,haschild,ismashang){
 if(infourl!="null"&&infourl.length!=0)
				  parent.mainFrame.location=infourl;
				if(ismashang){
				 parent.leftFrame.showMenu(rightcode);  
				}
				else{
				 setTimeout("showmenu()",500);
				}
				if(haschild&&haschild=="false"){
			    //	alert("收起左边");
				 	parent.switchFrame.document.getElementById('switchbar').className="switchbarimg";
	        	    parent.switchFrame.document.getElementById('switchbar').title="展开左侧菜单";
		            parent.document.getElementById('wapFrame').cols="0,9,*";   
				}else{
				//	alert("展开左边");
				    parent.document.getElementById('wapFrame').cols="175,9,*";
		            parent.switchFrame.document.getElementById('switchbar').className="switchbarimg_";
		            parent.switchFrame.document.getElementById('switchbar').title="关闭左侧菜单";
				}
}
<s:iterator value="#session.loginUser.topMenus" status="stat">
	<s:if test="#stat.index==0">
		   var infourl="${linkurl}";
		   var haschild="${hasChild}";
		    var rightcode="${rightcode}";
		    _loadUrl(rightcode,infourl,haschild,false);
		 /*    if(infourl!="null"&&infourl.length!=0)
				  parent.mainFrame.location=infourl;
				 
				 setTimeout("showmenu()",500);
				parent.leftFrame.showMenu(rightcode);  
				if(haschild&&haschild=="false"){
				 	parent.switchFrame.document.getElementById('switchbar').className="switchbarimg";
	        	    parent.switchFrame.document.getElementById('switchbar').title="展开左侧菜单";
		            parent.document.getElementById('wapFrame').cols="0,9,*";   
				}else{
				    parent.document.getElementById('wapFrame').cols="175,9,*";
		            parent.switchFrame.document.getElementById('switchbar').className="switchbarimg_";
		            parent.switchFrame.document.getElementById('switchbar').title="关闭左侧菜单";
				}*/
		</s:if>
</s:iterator>
  function showmenu(){
	parent.leftFrame.showMenu(rightcode);  
  }
</script>
</body> 
</html> 