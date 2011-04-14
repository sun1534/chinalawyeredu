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
  <link rel="stylesheet" type="text/css" href="../css/dialog.css" />
 <script type="text/javascript" src="../js/dialog.js"></script>
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script type="text/javascript">

  $(document).ready(function() {

  });

function setDeviceId(id)
{
   $("#username").attr("value","");
   $("#password").attr("value","");
   $("#deviceid").attr("value",id);
}
function login(){
var username1=$("#username").val();
var deviceid=$("#deviceid").val();
var password1=$("#password").val();
var username=escape(encodeURIComponent(username1));
var password=escape(encodeURIComponent(password1));
var now=new Date().getTime();
$.getJSON("../communicateguardajax/deviceLogin.action?username="+username+"&deviceId="+deviceid+"&now="+now+"&password="+password+"&flag=1",function(json){
  if(json.isok=="1"){
    alert("登录成功");
    closeDialogBox('#dialogBoxAdd');
    window.location.href="imitateCommand.action?deviceId="+deviceid;
  }else{
    alert("用户名或密码错误，请重新登录");
  }
});
}

function selectdevice(obj){
var now=new Date().getTime();
var deviceid=obj.value;
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
#mobile {position:relative;}
.mobileBack {
    bottom: 20px;
    display: block;
    height: 60px;
    left: 118px;
    position: absolute;
    width: 60px;}
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
										<li   class="current" ><a href="#" title="">设备列表</a></li>
										<!-- 当前选中 li class="current" -->
									</ul>
								</div>
								<div class="mobileTabCon">
									<div class="mobileCon">
									<input type="hidden" id="deviceid"  value="0"/>
									<s:iterator value="devicelist" status="status">
										<p><a href="#" onclick="setDeviceId(${deviceid});showDialogBox('#dialogBoxAdd',300);" >${devicename}</a></p>
									 </s:iterator>
									</div>
								</div>
							</div>
							<div class="mobileFt">
							我已经连接的设备：
							 <select name="deviceid" onchange="selectdevice(this)">
							      <option value="0" >请选择设备</option>
							     <s:iterator value="memdevice" id="entry">
						          <option value=" <s:property value="value.deviceid"/> "     <s:if test="deviceId==value.deviceid">selected="selected"</s:if>   ><s:property value="value.devicename"/></option>
						        </s:iterator>
								</select>
						 </div>
						</div>
						<a href="javascript:history.back(-1);" title="返回" class="mobileBack"   ></a>
					</div>
					<div id="mobileCon">
						<div class="mobileStatu">
							<div class="mobileStatuCon">
								<h3></h3>
								 <div class="tablist consoleresult eqHeight" style="overflow:auto" id="div1">
								   <!-- 存储返回的内容使用的 -->
										<div id="querylist" style="overflow:auto">

										</div>
									</div>
								<!-- 存储返回的内容使用的
								    <div class="tablist queryresult eqHeight" id="div2">

									 <div id="resultlist" style="overflow:auto">

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
	<div class="Overdialog" id="Overdialog"><iframe></iframe></div>
			<!-- 新增  -->
			<div class="dialogBox" id="dialogBoxAdd">
				<div class="DialogWrap">
					<a href="#" class="closeDialogBox" onclick="closeDialogBox('#dialogBoxAdd')" title="关闭">关闭</a>
					<div class="dialogMain">
						<div class="operateTabInfo">登录</div>
						<table class="operateTabBox">
							<tbody>
								<tr class="fEven">
									<td class="w120 fname">用户名：</td>
									<td class="fvalue">
									<input type="text" id="username" size="20" class="txt"/>
									</td>
								</tr>
								<tr class="fEven">
									<td class="w120 fname">密码：</td>
									<td class="fvalue">
									<input type="password" id="password" size="20"class="txt"/>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="dialogBtn"><input type="button"  value="登录" title="登录" class="btnSubmit" onclick="login()"/>　<input type="button"  value="取 消" title="取 消" class="btnBack" onclick="closeDialogBox('#dialogBoxAdd')"/></div>
				</div>
			</div>
</body>

</html>

