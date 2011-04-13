<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-手机模拟器</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script type="text/javascript">

  $(document).ready(function() {

  });

function execommand(commandid){
 $("#userpdperrorall").show();
 $("#start").hide();

var now=new Date().getTime();
$.getJSON("../communicateguardajax/imitateExcecommand.action?commandId="+commandid+"&now="+now,function(json){
   $("#resultlist").empty();
   $("#querylist").empty();
   $("#resultlist").html(json.result);
   $("#querylist").html(json.orgresult);
   $("#userpdperrorall").hide();
   $("#start").show();
});
}


function selectdevice(obj){
var deviceid=obj.value;
var now=new Date().getTime();
if(deviceid!=0)
$.getJSON("../communicateguardajax/deviceLogin.action?deviceId="+deviceid+"&now="+now+"&flag=2",function(json){
  if(json.isok=="1"){
    window.location.href="imitateCommand.action?deviceId="+deviceid;
  }else{
    alert("获取设备命令出错！");
  }
});
}

</script>

 <style>
.consoleresult{
	float:left;
	background-color:black;
	color:white;
	width:99%;
	height:auto!important;
	height:420px;
	min-height:420px;
}

.queryresult{
	margin-left:50%;
	height:auto!important;
	height:420px;
	min-height:420px;
}
.mobileTabCon {height:300px;overflow:hidden;}
.mobileCon {width:242px;height:120px;overflow-y:auto;overflow-x:hidden;}
#resultlist {width:242px;height:180px;overflow:auto;word-break:break-all;word-wrap:break-word;}
#querylist {
	overflow:auto;
	height:450px;
	word-break:break-all;word-wrap:break-word;
}
</style>

</head>

<body >
	<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>您所在是位置:</span><a>通信保障</a>＞<em>手机模拟器</em>
				</div>
			</div>
	</div>
	<div class="main">
		<div class="inmain">
			<div class="wrap">
				<div id="mobileMain">
					<div id="mobile">
						<div class="mobileWrap">
							<div class="mobileBd">
								<div class="mobileTab">
									<ul>
										<li <s:if test="status==1">class="current"</s:if> ><a href="imitateCommand.action?deviceId=${deviceId}" title="">标准命令</a></li>
										<li <s:if test="status==2">class="current"</s:if> ><a href="imitateCommand.action?deviceId=${deviceId}&status=2" title="">自定义命令</a></li>
										<li <s:if test="status==3">class="current"</s:if> ><a href="imitateCommand.action?deviceId=${deviceId}&status=3" title="">紧急维护命令</a></li>
										<!-- 当前选中 li class="current" -->
									</ul>
								</div>
								<div class="mobileTabCon">
									<div class="mobileCon">
									  <s:iterator value="commandlist" status="status">
										<p><a href="#" onclick="execommand(${commandid});">${commananame}</a></p>
									 </s:iterator>
									</div>
									<pre id="resultlist" >
									</pre>
								</div>
							</div>
							<div class="mobileFt">
							我已经连接的设备：
							 <select name="deviceid" onchange="selectdevice(this)">
							      <option value="0">请选择设备</option>
							     <s:iterator value="memdevice" id="entry">   
						          <option value=" <s:property value="value.deviceid"/> " <s:if test="deviceId==value.deviceid">selected="selected"</s:if> ><s:property value="value.devicename"/></option>  
						        </s:iterator>
								</select>
						 </div>
						</div>
						
					</div>
					<div id="mobileCon">
						<div class="mobileStatu">
							<div class="mobileStatuCon">
								<h3 ><div id="start"> 请选择左边命令执行</div><div class="tablist" id="userpdperrorall" style="display: none"><img src="../images/loading.gif"/></div></h3>
								 <div class="tablist consoleresult eqHeight" style="overflow:auto" id="div1">
								   <!-- 存储返回的内容使用的 -->
										<pre id="querylist" style="overflow:auto">
									     暂无原始结果
										</pre>
									</div>
								<!--
								    <div class="tablist queryresult eqHeight" id="div2">
							   <!-- 存储返回的内容使用的
									 <div id="resultlist" style="overflow:auto">
									     暂无解析结果
									 </div>
								-->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--  -->

</body>

</html>

