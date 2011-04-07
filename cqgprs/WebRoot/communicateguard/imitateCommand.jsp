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
var now=new Date().getTime();
$.getJSON("../communicateguardajax/imitateExcecommand.action?commandId="+commandid+"&now="+now,function(json){
   $("#resultlist").empty();
   $("#querylist").empty();
   
   $("#resultlist").html(json.orgresult);
   $("#querylist").html(json.result);
});
}

</script>
 
 <style>
.consoleresult{
	float:left;
	background-color:black;
	color:white;
	width:49%;
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
								</div>
							</div>
							<div class="mobileFt">
								请选择状态：<select name="">
									<option value="" selected="selected">选择状态</option>
								</select>
							</div>
						</div>
					</div>
					<div id="mobileCon">
						<div class="mobileStatu">
							<div class="mobileStatuCon">
								<h3 id="aabbcc">正在执行<span id="aa"></span></>秒，<span id="bb"></span></>秒，<span id="cc"></span></>秒</h3>
								 <div class="tablist consoleresult eqHeight" style="overflow:auto" id="div1">
								   <!-- 存储返回的内容使用的 -->
										<div id="querylist" style="overflow:auto">
										dsfsddsfsd
										</div>
									</div>

								    <div class="tablist queryresult eqHeight" id="div2">
							   <!-- 存储返回的内容使用的 -->
									 <div id="resultlist" style="overflow:auto">
									 sdfsdfsd
									 </div>
								
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

