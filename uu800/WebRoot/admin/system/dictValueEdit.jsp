<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" id="MainHtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <title>${sysName}</title>
 <link rel="stylesheet" type="text/css" href="../style/css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../style/css/main.css" />
 <link rel="stylesheet" type="text/css" href="../style/css/page.css" />
 <link rel="stylesheet" type="text/css" href="../ymPromptSkin/vista/ymPrompt.css" />
 <script type="text/javascript">
  
  function onAddDictValue()
  {
   var dictName = document.getElementById("dictvalue_dictName").value;
   if(dictName.replace(/(^[\s]*)|([\s]*$)/g, "") == "")
   {
     document.getElementById("spanMsg").innerHTML = "<font color='red'>请输入数据字典值名称</font>";
     document.getElementById("dictvalue_dictName").focus();
     return;
   }

    var url='updateDictValue.action';
	var params = Form.serialize('form2');
	var myajax = new Ajax.Request(url,
			{
		method:'post',
		parameters:params,
		onComplete:processResponse,
		asynchronous:true
			}
	);
   }
   
   function processResponse(request)
   {
      var action = JSON.parse(request.responseText);
	if(action.tip == "OK")
	{
		 window.parent.ymPrompt.doHandler("Add",true);
	}
	else if(action.tip == "InputDictValueCode")
	 {
	  document.getElementById("spanMsg").innerHTML = "<font color='red'>请输入数据字典值名称</font>";
      document.getElementById("dictvalue_dictName").focus();
	 }
	 else if(action.tip == "InputDictValueName")
	 {
	     document.getElementById("spanMsg").innerHTML = "<font color='red'>请输入数据字典值名称</font>";
    	 document.getElementById("dictvalue_dictName").focus();
	 }
	 else if(action.tip == "DictValueCodeRepeat")
	 {
	   document.getElementById("spanMsg").innerHTML = "<font color='red'>已经存在相同的数据字典值编码</font>";
       document.getElementById("dictvalue_dictName").focus();
	 }
	 else if(action.tip == "SystemError")
	 {
	    document.getElementById("spanMsg").innerHTML = "<font color='red'>系统异常,请联系系统管理员</font>";
	 }
   }
   
     function onProcess(value)
  {
    window.parent.ymPrompt.doHandler(value,true);
  }
  </script>
</head>
<body >
  <form name="form2" id="form2"  method="POST">
     <s:set name="dict" value="dictCode"></s:set>
     <s:hidden name="dictCode"></s:hidden>
	<div class="guildNav" id="guildNav">
		<div class="crumbs">
			<div class="inCrumbs">
				<div class="crumbsTit">
				<span id="spanMsg">&nbsp;</span>
				</div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
		<div class="main">
			<div id="DataAddArea">
				<div class="addModBtn">
					<table class="addTable">
						<tbody>
							<tr>
								<td class="w100"></td>
								<td>
									<span class="saveBtn"><span class="inbtns"><input class="btn" type="button" value="修 改" onclick="onAddDictValue();"/></span></span>
									<span class="backBtn"><span class="inbtns"><input class="btn" type="button" value="关 闭" onclick="onProcess('Close');"/></span></span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="addMod">
					<table class="addTable">
						<tbody>
							<tr>
								<th class="fTit w120">数据字典值编码：<font color="red">*</font></th>
								<td><s:textfield name="dictvalue.dictCode" disabled="true" cssClass="txt"/></td>
								<th class="fTit w120">数据字典值名称：<font color="red">*</font></th>
								<td><s:textfield name="dictvalue.dictName" cssClass="txt"/></td>
							</tr>
							<tr>
								<th class="fTit w120">有效性：</th>
								<td colspan="3">
									<s:radio name="dictvalue.enabledFlag" list="#{'Y':'启用','N':'禁用'}" listKey="key" listValue="value" value="'Y'"></s:radio>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</form>
	 <script type="text/javascript" src="../js/ymPrompt.js"></script>
 	<script type="text/javascript" src="../js/prototype-1.4.0.js"></script>
 	<script type="text/javascript" src="../js/json2.js"></script>
</body>
</html>