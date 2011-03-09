<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" id="MainHtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <title>${sysName}</title>
 <link rel="stylesheet" type="text/css" href="../style/css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../style/css/main.css" />
 <link rel="stylesheet" type="text/css" href="../style/css/page.css" />
</head>
 
<body >
 <s:set name = "enabledFlags" value='#{"Y":"启用","N":"禁用"}'/>
	<div class="guildNav" id="guildNav">
		<div class="crumbs">
			<div class="inCrumbs">
				<div class="crumbsTit"><em>您所在的位置：</em>系统管理 &gt; 数据字典管理 &gt; <strong>数据字典查看</strong></div>
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
								<td>${dicttype.dictType }</td>
								<th class="fTit w100">数据字典名称：</th>
								<td>${dicttype.dictName }</td>
							</tr>
							<tr>
								<th class="fTit w100">操作类型：</th>
								<td><s:set name="opFlags" value='#{"Y":"可维护","N":"不可维护"}'></s:set>
                      				<s:property value="#opFlags[dicttype.opFlag]"/>
                      			</td>
								<th class="fTit w100">有效性：</th>
								<td><s:property value="#enabledFlags[dicttype.enabledFlag]"/></td>
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
								</tr>
							</thead>
							<tbody class="tabBody">
							<s:iterator value="dictValues" status="status">
				            <tr>
									<td class="t_data_code">${dictCode}</td>
									<td class="t_data_Name">${dictName }</td>
									<td class="t_data_Epx"><s:property value="#enabledFlags[enabledFlag]"/></td>
							</tr>    
                    		</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="../style/js/jquery.js"></script>
	<script type="text/javascript" src="../style/js/tablegrid.js"></script>
</body>
</html>

