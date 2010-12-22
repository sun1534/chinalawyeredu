<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-MEM设备命令管理</title>
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

function deleteCommand(deviceId){
var theurl="../communicateguardajax/memDeviceCommandDelete.action?commandId="+deviceId;
$.getJSON(theurl,function(json){
  if(json.isok=="1"){
    alert("MEM设备命令删除成功");
    window.location.reload();
  }else{
    alert("MEM设备命令删除失败");
  }
});
}
function createEditCommandPre(isnew,deviceId)
{
	$.ajax({
		type : "POST",
		url : "../communicateguard/memDeviceCommandCreateEdit!input.action",
		data: "isnew="+isnew+"&commandId="+deviceId,
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

function createEditCommand(action,deviceId){

	var params= $('#commandCreateEditForm').formSerialize();
   $.ajax({
		    type:"post",
		    data:params,
			url:"../communicateguardajax/memDeviceCommandCreateEdit.action",
			dataType:"json",
			success: function(json){
				  if(json.isok=="1"||json.isok=="2"){
   					 alert("MEM设备命令设置成功");
   					 window.location.reload();
  				}else{
   					 alert("MEM设备命令设置失败");
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
						<span>您所在是位置:</span><a>通信保障</a>＞<em>MEM设备命令管理</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="memCommandList" method="POST">	
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
                                <td>设备列表：<s:select name="deviceId" list="deviceList" headerKey="0" headerValue="全部"/>&nbsp;</td>
                                <td>命令名称：<s:textfield name="name" id="name" cssClass="txt" size="12"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
								 <td><input type="button" class="btnSubmit" value="新　增" onclick="createEditCommandPre('1',0);"/> </td>
								</tr>
							</tbody>
						</table>
				  </div> 
				
				
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>
                       
                          <th>命令名称</th>
                          <th>脚本脚本</th>
                          <th>对应设备</th>
                          <th>新增时间</th>
                        
                          <th>修改</th>
                       
                          <th>删除</th>
                          </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="status">
                        <tr>
                          <td>${commananame}</td>
                          <td>${commandscript} </td>
                          <td><s:property value="deviceList[deviceid]"/></td>
                          <td>${createtime}</td>
               <td><a href="#" onclick="createEditCommandPre('0','${commandid}')">修改</a></td>
                          <td><a href="#" onclick="deleteCommand('${commandid}')">删除</a>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
             <!--        <tfoot>
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

