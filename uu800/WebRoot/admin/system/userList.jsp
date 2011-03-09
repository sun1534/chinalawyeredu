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
				<div class="crumbsTit">&nbsp;</div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
	 <s:form name="search" action="userList" method="POST">
	 <s:hidden name="orgid"/>
	 <s:set name = "setstatus"  value="#{0:'正常',1:'冻结',2:'注销'}"/>
		<div class="main">
			<div id="searchArea">
				<div class="searchMod">
					<div class="tit">
						<h3>用户查询</h3>
					</div>
					<div class="titM">
						<div id="SearchMain">
							<table class="searchTable">
								<tbody>
									<tr>
										<th class="fTit w100">登录名：</th>
										<td class=""><s:textfield name="username" cssClass="txt"/></td>
										<th class="fTit w100">姓名：</th>
										<td class=""><s:textfield name="name" cssClass="txt"/></td>
										<th class="fTit w80">范围：</th>
										<td class="">
										 <label><s:checkbox name="findall"/>所有组织</label>
										</td>
									</tr>
									<tr>
										<th class="w100"></th>
										<td colspan="5">
											<span class="seaBtn"><span class="inbtns"><input type="submit" name="Submit" class="btn" value="查询"></span></span>
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
					  
                       <input type="button" onClick="document.createForm.submit()" class="btn" value="新增用户"/>
					</span></span>
				</div>
				<div class="listTable">
					<div class="tableMod">
						<table>
							<thead>
								<tr class="tabTh">
									<th class="t_user_name">登录名</th>
									<th class="t_user_relName">真实姓名</th>
									<th class="t_user_region">归属地区</th>
									<th class="t_user_stat">状态</th>
									<th class="t_user_time">创建时间</th>
									<th class="t_user_edit_pra">角色修改</th>
									<th class="t_user_op">操作</th>
								</tr>
							</thead>
							<tbody class="tabBody">
						    <s:iterator value="userlist" status="status">
	                      <TR>
	                        <TD class="t_user_name">${username}</TD>
	                        <TD class="t_user_relName">${name}</TD>
	                        <TD class="t_user_region">${tsysAreaCode.areaname}</TD>
	                        <TD class="t_user_stat"><s:property value="#setstatus[status]"/></TD>
	                        <TD class="t_user_time"><s:date name="createtime" format="yyyy-MM-dd"/></TD>
	                        <TD class="t_user_edit_pra">
		                       <a class="t_edit" href="userRoleEdit!input.action?userid=${userid}&orgid=${tsysOrg.orgid}&jobid=${jobid}">修改</a> 
		                    </TD>
		                    <TD class="t_user_op">
		                    	<a class="t_view" href="userView.action?userid=${userid}&pagenumber=${pagenumber}&orgid=${tsysOrg.orgid}"> 查看</a> | 
		                 		<a class="t_edit" href="userEdit!input.action?userid=${userid}&orgid=${tsysOrg.orgid}&jobid=${jobid}">编辑</a> | 
		                        <a class="t_edit" href="userDelete.action?userid=${userid}&orgid=${tsysOrg.orgid}&jobid=${jobid}">删除</a>
		                    </TD>
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
<s:form name="createForm" action="userCreate!input.action" meTDod="POST">
    <s:hidden name="orgid" value="%{orgid}"/>
    <s:hidden name="jobid" value="%{jobid}"/>
	<s:hidden name="pageNo" value="%{pageNo}"/>
</s:form>
<s:form name="searchForm" action="userSearch!input.action" meTDod="POST">
<s:hidden name="flag" value="in"/>
</s:form>
 

