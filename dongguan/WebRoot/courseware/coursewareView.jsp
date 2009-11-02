<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>课件查看</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}

-->
.btn {
	BORDER-RIGHT: #002D96 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #002D96 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid : DXImageTransform . Microsoft .
		Gradient(GradientType = 0, StartColorStr = #FFFFFF, EndColorStr =
		#9DBCEA);
	BORDER-LEFT: #002D96 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #002D96 1px solid
}
</style>
		<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
		<script language="javascript">
function SetCwinHeight(fname)
{
 var cwin=document.getElementById(fname);
 if (document.getElementById)
 {
  if (cwin && !window.opera)
  {
   if (cwin.contentDocument && cwin.contentDocument.body.offsetHeight)
    cwin.height = cwin.contentDocument.body.offsetHeight; 
   else if(cwin.Document && cwin.Document.body.scrollHeight)
    cwin.height = cwin.Document.body.scrollHeight;
  }
 }
}
</script>
	</head>
	<body>
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="images/b_02.gif" width="4" height="7">
					${navigator}
				</td>
			</tr>
		</table>
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1">
			<tr>
				<td valign="top">
					<div
						style="font-weight: bold; color: blue; font-size: 8pt; padding-left: 15px;">
						友情提醒：
						<br>
						1、课件最末尾有“停止收看”按钮，点击该按钮才能记录课时及学分。
						<br>
						2、看完课件请记得点击页面下面的“停止收看”按钮，否则将无法记入课时及学分。
						<br>
						3、学习时间未满时，课件每5分钟会弹出窗口，请及时点击，否则该次观看将失效。
					</div>
				</td>
			</tr>
		</table>
		<br>
		<div style="padding-left: 15px;">
			<iframe src="../coursewarefiles/${ware.warefile}" width="100%"
				id="cwin" onload="Javascript:SetCwinHeight('cwin')" frameborder="0"
				scrolling="no"></iframe>
		</div>
		<div align="center">
			<input type="button" value="停止收看" onclick="signAndReturn()" class="btn">
			&nbsp;&nbsp;
			<input type="button" value=" 返  回 "
				onclick="location.href='../courseware/courseViewList!warelist.pl?typeid=${ware.coursetype.typeid}'"
				class="btn">
			<input type="hidden" id="curtime" name="curtime">
		</div>
		<script language="javascript">
  var begintime=getTime(); //进入页面时间
  var curtime=begintime; //当前时间
  var temptime=begintime; //中间变量
  var ShowNextSeconds=300; //弹出窗口间隔 单位秒
  var past=0; //在页面已待时间
  var waretime=${ware.waretime}*60; //课时
  var pxminutes=${pxminutes}*60; //已看时间
  
  <s:if test="ware.waretime>pxminutes&&roleid==1">
  var ctimer=setInterval(showconfirm,ShowNextSeconds*1000);
  
  function showconfirm(){
  	curtime=getTime();
  	past=curtime-temptime;
  	
  	if(past>=ShowNextSeconds){
  		temptime=curtime;
  		if(!confirm("是否继续观看？如果按取消，将计入课时并返回课件列表页面！")){
			clearInterval(ctimer);
			signAndReturn();
		}/**else{
			alert("确定");
		}*/
  	}
  	if((curtime-begintime)>(waretime-pxminutes)){
  		if(!confirm("您学习的时间已够获取该课件所有学分，是否继续观看,按取消返回课件列表页面")){ //返回列表页面并记录时间及积分，清除弹出窗口事件			
			clearInterval(ctimer);
			signAndReturn();
		}else{ //记录学分及时间，清除定时时间,并将开始时间设置为当前点		
			signtime();
			begintime=getTime();
			clearInterval(ctimer);
			return;
		}
  	}
					
  }
  </s:if>
  function getTime(){
	var d = new Date() 
	var vYear = d.getFullYear() 
	var vMon = d.getMonth() + 1 
	var vDay = d.getDate() 
	var h = d.getHours(); 
	var m = d.getMinutes(); 
	var se = d.getSeconds(); 
	curtime=vYear+(vMon<10 ? "0" + vMon : vMon)+(vDay<10 ? "0"+ vDay : vDay)+(h<10 ? "0"+ h : h)+(m<10 ? "0" + m : m)+(se<10 ? "0" +se : se); 
	return Number(curtime);
  }
  function signtime(){
	var now=getTime();
	pxminutes=now-begintime; //单位秒
	var url="../wareajax/wareLook.pl?pxminutes="+pxminutes;
    var myAjax = new Ajax.Request(url,{method: 'post',onComplete:showResponse});	     
  }
  function showResponse(originalRequest){
    var res=eval('(' + originalRequest.responseText + ')');
    alert("您总共已学习了该课件"+res.pxminutes+"分钟，获取积分"+res.xuefen+"分");
}
  function signAndReturn(){
  	<s:if test="roleid==1">
  	signtime();
  	</s:if>
  	document.location.href="../courseware/courseViewList!warelist.pl?typeid=${ware.coursetype.typeid}";
  }
</script>

	</body>
</html>