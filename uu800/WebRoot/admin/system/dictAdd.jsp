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
  // 保存数据字典
   function updateDict()
   {
   
   var dictType = document.getElementById("dicttype_dictType").value;
   var dictName = document.getElementById("dicttype_dictName").value;
   
 if(dictType.replace(/(^[\s]*)|([\s]*$)/g, "") == "")
   {
    ymPrompt.alert("请输入数据字典编码",300,150,'信息提示',function(){document.getElementById("dicttype_dictType").focus();})
    return;
   }
   
   if(dictName.replace(/(^[\s]*)|([\s]*$)/g, "") == "")
   {
    ymPrompt.alert("请输入数据字典名称",300,150,'信息提示',function(){document.getElementById("dicttype_dictName").focus();})
    return;
   }
  document.form1.submit();
   }
   

	
	// 新增数据字典值
	function openAddDictWin()
	{
	   var url="dictValueAdd!input.action?dictType=${dictType}";
	    ymPrompt.win({message:url,handler:onAfterProcess,width:700,height:300,title:'新增数据字典值',iframe:true})
	}
	
	// 编辑数据字典值
	function editDictValue(value)
	{
	  var url="dictValueUpdate!input.action?dictType=${dictType}&dictCode=" + value;
	   ymPrompt.win({message:url,handler:onAfterProcess,width:700,height:300,title:'编辑数据字典值',iframe:true})
	}
	
    function onAfterProcess(returnValue)
	{
	   if(returnValue == "Add")
	   {
	     document.form1.action = "dictAdd!input.action";
	     document.form1.submit();
	   }
	}

   </script>
</head>
<body >
<s:set name = "enabledFlags" value='#{"Y":"启用","N":"禁用"}'/>
	<div class="guildNav" id="guildNav">
		<div class="crumbs">
			<div class="inCrumbs">
				<div class="crumbsTit"><em>您所在的位置：</em>系统管理 &gt; 数据字典管理 &gt; <strong>数据字典新增</strong></div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
	  <form id="form1" name="form1" action="dictAdd.action" method="post">
		<div class="main">
			<div id="DataAddArea">
				<div class="addModBtn">
					<table class="addTable">
						<tbody>
							<tr>
								<td class="w100"></td>
								<td>
									<span class="saveBtn"><span class="inbtns"><input type="button" class="btn" value="保 存" onClick="updateDict();"/></span></span>
									<span class="saveBtn"><span class="inbtns"><input type="button" class="btn" value="增加字典值" onClick="openAddDictWin();"/></span></span>
									<span class="backBtn"><span class="inbtns"><a href="javascript:history.back(-1);" class="btn">返 回</a></span></span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="addMod">
					<table class="addTable">
						<tbody>
							<tr>
								<th class="fTit w100">数据字典编码：</th>
								<td><s:textfield name="dicttype.dictType" style="ime-mode:disabled" cssClass="txt"/></td>
								<th class="fTit w100">数据字典名称：</th>
								<td><s:textfield name="dicttype.dictName" cssClass="txt"/></td>
							</tr>
							<tr>
								<th class="fTit w100">操作类型：</th>
								<td>
									<s:radio name="dicttype.opFlag" list="#{'Y':'可维护','N':'不可维护'}" listKey="key" listValue="value" value="'Y'" ></s:radio>
								</td>
								<th class="fTit w100">有效性：</th>
								<td>
									<s:radio name="dicttype.enabledFlag" list="#{'Y':'启用','N':'禁用'}" listKey="key" listValue="value" value="'Y'"></s:radio>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
				<div id="listArea">
				<div id="listBtn">
			    &nbsp;
				</div>
				<div class="listTable">
					<div class="tableMod">
						<table>
							<thead>
								<tr class="tabTh">
									<th class="t_data_code">数据字典值编码</th>
									<th class="t_data_Name">数据字典值名称</th>
									<th class="t_data_Epx">有效性</th>
									<TH class="t_data_op">操作</TH>
								</tr>
							</thead>
							<tbody class="tabBody">
							<s:iterator value="dictValues" status="status">
				            <tr>
									<td class="t_data_code">${dictCode}</td>
									<td class="t_data_Name">${dictName }</td>
									<td class="t_data_Epx"><s:property value="#enabledFlags[enabledFlag]"/></td>
									<TD class="t_data_op"><a class="t_edit" href="#" onClick="editDictValue('${dictCode}');">修改</a></TD>
							</tr>    
                    		</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
	 <script type="text/javascript" src="../js/ymPrompt.js"></script>
	<script type="text/javascript" src="../style/js/jquery.js"></script>
	<script type="text/javascript" src="../style/js/tablegrid.js"></script>
</body>

</html>