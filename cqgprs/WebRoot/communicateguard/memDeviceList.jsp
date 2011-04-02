<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-MEM设备列表管理</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />  
 <link rel="stylesheet" type="text/css" href="../css/dialog.css" />
 
 <jscalendar:head/>
    <script type="text/javascript" src="../js/dialog.js"></script> 
  <script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript" src="../js/jquery.form.js"></script>
 <script type="text/javascript">
 

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function exportit(){
  document.form1.submit();
}
function queryit(){
  document.form1.submit();
}
function deleteDevice(deviceId){
var theurl="../communicateguardajax/memDeviceDelete.action?deviceId="+deviceId;
$.getJSON(theurl,function(json){
  if(json.isok=="1"){
    alert("MEM设备信息删除成功");
    window.location.reload();
  }else{
    alert("MEM设备信息删除失败");
  }
});
}
function createEditDevicePre(isnew,deviceId)
{
	$.ajax({
		type : "POST",
		url : "../communicateguard/memDeviceCreateEdit!input.action",
		data: "isnew="+isnew+"&deviceId="+deviceId,
		beforeSend : function() {
		},
		success : function(data) {
				var ssize=$("#dialogBoxAdd").size();
   		 			if(ssize>0)
    					$("#dialogBoxAdd").replaceWith(data);
    				else
       					$("body").append(data);
    				showDialogBox('#dialogBoxAdd',400);
		}
	});
}
function setUserDevicePre(deviceId)
{
	$.ajax({
		type : "POST",
		url : "../communicateguard/memDeviceUserSet!input.action",
		data: "deviceId="+deviceId,
		beforeSend : function() {
		},
		success : function(data) {
				var ssize=$("#dialogBoxAdd").size();
   		 			if(ssize>0)
    					$("#dialogBoxAdd").replaceWith(data);
    				else
       					$("body").append(data);
    				showDialogBox('#dialogBoxAdd',400);
		}
	});
}
function setUserDevice(deviceId){

	var params= $('#deviceUserSetForm').formSerialize();
   $.ajax({
		    type:"post",
		    data:params,
			url:"../communicateguardajax/memDeviceUserSet.action",
			dataType:"json",
			success: function(json){
				  if(json.isok=="1"||json.isok=="2"){
   					 alert("MEM设备关联到用户设置成功");
   					 window.location.reload();
  				}else{
   					 alert("MEM设备关联到用户设置失败");
  				}
			}
	});		
}
function createEditDevice(action,deviceId){

	var params= $('#deviceCreateEditForm').formSerialize();
   $.ajax({
		    type:"post",
		    data:params,
			url:"../communicateguardajax/memDeviceCreateEdit.action",
			dataType:"json",
			success: function(json){
				  if(json.isok=="1"||json.isok=="2"){
   					 alert("MEM设备信息设置成功");
   					 window.location.reload();
  				}else{
   					 alert("MEM设备信息设置失败");
  				}
			}
	});		
}

</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>通信保障</a>＞<em>MEM设备管理</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="memDeviceList" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>         
                                 <s:hidden name="pageNo"/>
                                  <s:hidden name="resultType"/>
                                <td>输入设备名称：<s:textfield name="deviceName" id="deviceName" cssClass="txt" size="12"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
									 <td><input type="button" class="btnSubmit" value="新　增" onclick="createEditDevicePre('1',0);"/> </td>
							<!--  <td><input type="button" class="btnSubmit" value="删  除" onclick="deletecell()"/></td>
								-->
								</tr>
							</tbody>
						</table>
				  </div> 
					<!-- 操作模块
					<div class="operate">
						<input type="button" class="btnSubmit" title="保 存" value="新　增" onclick="getAdd()"/>
					    <input type="button" class="btnCancel" title="返 回" value="删　除"/>
					</div>
				-->
				  <div class="tablist" id="memDeviceList">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>
                       
                          <th>设备名</th>
                          <th>IP</th>
                          <th>端口</th>
                          <th>登录名</th>
                          <th>登录密码</th>
                          <th>备注</th>
                          <th>新增时间</th>
                         <th>修改</th>
                           <th>修改</th>
                           <th>关联用户</th>
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="status">
                        <tr>
                          <td><a href="memCommandList.action?deviceId=${deviceid}">${devicename}</a></td>
                          <td>${ip} </td>
                          <td>${port }</td>
                          <td>${loginName}</td>
                          <td>${loginPwd}</td>
                          <td>${description}</td>
                          <td>${createtime}</td>
                          <td><a href="#" onclick="createEditDevicePre('0','${deviceid}')">修改</a></td>
                          <td><a href="#" onclick="deleteDevice('${deviceid}')">删除</a>
                           <td><a href="#" onclick="setUserDevicePre('${deviceid}')">关联用户</a>
                          </td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                 <!--    <tfoot>
							<tr>
							   <td colspan="7" class="fright">
							     <input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/>
							   </td>
							</tr>
						 </tfoot>
			   -->
                    </table>
			  </div>

				<div  class="tabpagelist">
						<div class="pager">
							${page.pageView}
						</div>
					</div>
				</div>
			</div>
		</div>
</s:form>
</body>

</html>

