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
				<div class="crumbsTit"><em>您所在的位置：</em>系统管理 &gt; <strong>日志查询</strong></div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
	<s:form name="form1" id="form1" action="loggerQuery">
	<s:set name="setstatus" value="#{true:'成功',false:'失败'}"/>
		<div class="main">
			<div id="searchArea">
				<div class="searchMod">
					<div class="tit">
						<h3>日志查询</h3>
					</div>
					<div class="titM">
						<div id="SearchMain">
							<table class="searchTable">
								<tbody>
									<tr>
										<th class="fTit w100">操作用户登录名:</th>
										<td class="w250"><s:textfield name="loginName" cssClass="txt"></s:textfield></td>
										<th class="fTit w100">操作时间：</th>
										<td >
										<span class="TimeStart">
										<s:textfield name="opDateBegin" cssClass="txt time" onclick="fPopCalendar(event,this,this)" onfocus="this.select()"></s:textfield>
										></span>至<span class="TimeEnd">
										<s:textfield type="text" name="opDateEnd" cssClass="txt time" onclick="fPopCalendar(event,this,this)" onfocus="this.select()"/>
										</span>
										</td>
									</tr>
									<tr>
										<th class="fTit w100">操作类型：</th>
										<td class="w250">
										<s:select name="logType" cssClass="sel" list="logTypeMap" listKey="key" listValue="value" headerValue="--请选择--" headerKey=""></s:select>
										</td>
										<th class="fTit w100">企业名称：</th>
										<td class="w250">
											<s:textfield name="corpCode" cssClass="txt"></s:textfield>
										</td>
									</tr>
									<tr>
										<th class="w100"></th>
										<td colspan="3">
											<span class="seaBtn"><span class="inbtns">
											<input type="submit" class="btn" value="查询"/>
											</span>
											</span>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div id="listArea">
				<div class="listTable">
					<div class="tableMod">
						<table>
							<thead>
								<tr class="tabTh">
									<th class="t_logUser_type">用户类型</th>
									<th class="t_logName">企业名称</th>
									<th class="t_logUser">操作用户</th>
									<th class="t_logIp" style="text-align:center;">IP地址</th>
									<th class="t_log_type">日志类型</th>
									<th class="t_logRes">操作结果</th>
									<th class="t_logView">详细</th>
								</tr>
							</thead>
							<tbody class="tabBody">
							<s:iterator value="tsysLogs" status="stats">
		                      <TR align="center">
		                        <TD class="t_logUser_type">${usertype}</TD>
		                        <TD class="t_logName">${corpname }</TD>
		                        <TD class="t_logUser">${username}</TD>
		                        <TD class="t_logIp">${ipaddr}</TD>
		                        <TD class="t_logRes"><s:property value="dictmap['Log_Type'][logtype]"/></TD>
		                        <TD class="t_logRes"><s:property value="#setstatus[result]"/></TD>
		                        <TD class="t_logView"><a href="viewSysLog.action?logid=${logid}" class="t_view">查看</a></TD>
		                      </TR>
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