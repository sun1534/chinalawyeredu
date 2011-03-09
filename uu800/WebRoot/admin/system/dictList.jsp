<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" id="MainHtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <title>${sysName}</title>
 <link rel="stylesheet" type="text/css" href="../style/css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../style/css/main.css" />
 <link rel="stylesheet" type="text/css" href="../style/css/page.css" />
 <link rel="stylesheet" type="text/css" href="../style/css/date.css" />
 <script type="text/javascript">
	function page(pageNo)
	{
		document.getElementById("pageNo").value = pageNo;
		document.forms[0].submit();
	}
 </script>
</head>
<body >
	<div class="guildNav" id="guildNav">
		<div class="crumbs">
			<div class="inCrumbs">
				<div class="crumbsTit"><em>您所在的位置：</em>系统管理 &gt; <strong>数据字典管理</strong></div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
	  <s:form name="form1" id="form1" action="dictList" method="POST">
	    <s:set name = "enabledFlags" value='#{"Y":"启用","N":"禁用"}'/>
        <s:set name = "operationFlags" value='#{"Y":"可维护","N":"不可维护"}'/>
		<div class="main">
			<div id="searchArea">
				<div class="searchMod">
					<div class="tit">
						<h3>数据字典查询</h3>
					</div>
					<div class="titM">
						<div id="SearchMain">
							<table class="searchTable">
								<tbody>
									<tr>
										<th class="fTit w100">数据字典编码：</th>
										<td class=""><s:textfield name="dictType" cssClass="txt"></s:textfield></td>
										<th class="fTit w100">数据字典名称：</th>
										<td class=""><s:textfield name="dictName" cssClass="txt"/></td>
										<th class="fTit w80">有效性：</th>
										<td class="">
											<s:radio name="enabledFlag" list="#{'Y':'启用','N':'禁用'}" listKey="key" listValue="value"></s:radio>
										</td>
									</tr>
									<tr>
										<th class="w100"></th>
										<td colspan="5">
											<span class="seaBtn"><span class="inbtns">
											<input class="btn" type="submit" value="查询">
											</span></span>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div id="listArea">
				<div id="listBtn">
					<span class="add"><span class="inbtns">
					<a href="dictAdd!input.action" class="btn">添加</a>
					</span></span>
				</div>
				<div class="listTable">
					<div class="tableMod">
						<table>
							<thead>
								<tr class="tabTh">
									<th class="t_data_code">数据字典编码</th>
									<th class="t_data_Name">数据字典名称</th>
									<th class="t_data_Epx">有效性</th>
									<th class="t_data_type">操作类型</th>
									<th class="t_data_op">操作</th>
								</tr>
							</thead>
							<tbody class="tabBody">
							<s:iterator value="tsysDicttypes" status="status">
				            <tr>
									<td class="t_data_code">${dictType}</td>
									<td class="t_data_Name">${dictName }</td>
									<td class="t_data_Epx"><s:property value="#enabledFlags[enabledFlag]"/></td>
									<td class="t_data_type"><s:property value="#operationFlags[opFlag]"/></td>
									<td class="t_data_op">
									<a href="dictView.action?dictType=${dictType}" title="" class="t_edit">查看</a> | 
									
				              			<a class="t_edit" href="editDict!input.action?dictType=${dictType}">修改</a>
				           		
									</td>
								</tr>    
                    		</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			${PageHtml }
		</div>
		</s:form>
	</div>
	<script type="text/javascript" src="../style/js/jquery.js"></script>
	<script type="text/javascript" src="../style/js/tablegrid.js"></script>
	<script type="text/javascript" src="../style/js/date.js"></script>
</body>
</html>