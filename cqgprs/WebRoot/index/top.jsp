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
  }
  f5pressed=false;
}
function pressedF5(){
	//  alert("f5pressed");
	 f5pressed=true;
}
$(document).ready(function(){
//getGbAlarmCnts();
 setTimeout('getGbAlarmCnts()',2*1000);
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
//setTimeout('getGbAlarmCnts()',5*1000);
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
	if(counts>60){
	   if(hd&&!hascancel){
		 clearInterval(hd);
		 hascancel=true;
	   }
	   top.document.title = "${sysName}";
	}
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
					<li><a  id="menu3" href="javascript:void(0)" title="统计分析"><span>统计分析</span></a></li> 
					<li><a  id="menu4" href="javascript:void(0)" title="网络质量"><span>网络质量</span></a></li> 
					<li><a  id="menu5" href="javascript:void(0)" title="通信保障"><span>通信保障</span></a></li> 
					<li><a  id="menu6" href="javascript:void(0)" title="用户行为"><span>用户行为</span></a></li> 
					<li><a  id="menu7" href="javascript:void(0)" title="行业客户"><span>行业客户</span></a></li> 
					<li><a  id="menu8" href="javascript:void(0)" title="系统警告"><span>系统警告</span></a></li> 
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
	loadUrl("#quickmenu","#menu2","left-recordSearch.html","jilu_mobile_1.html");
	loadUrl("#quickmenu","#menu3","left-total.html","total_total_1.html");
	loadUrl("#quickmenu","#menu4","left-net.html","net_resource_1.html");
	loadUrl("#quickmenu","#menu5","left-msg.html","msg_mobile_1.html");
	loadUrl("#quickmenu","#menu6","left-user.html","user_top1000_1.html");
	loadUrl("#quickmenu","#menu7","left-guest.html","guest_ovewview_1.html");
	loadUrl("#quickmenu","#menu8","left-warning.html","warning_no.html");
   */ 
 
 function loadUrl(o,rightcode,infourl,haschild){
	//alert(em+" "+o);
	//	$(em+" "+o).each(function(){
	//	alert("====");
			$("#quickmenu "+o).click(function()
			{
			    if(infourl!="null"&&infourl.length!=0)
				  parent.mainFrame.location=infourl;
				 
				parent.leftFrame.showMenu(rightcode);  
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
			});
		//});
	}
	
	</script> 
<script type="text/javascript" src="../js/top.js"></script> 
</body> 
</html> 