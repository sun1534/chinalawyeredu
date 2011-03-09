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
	<div class="guildNav" id="guildNav">
		<div class="crumbs">
			<div class="inCrumbs">
				<div class="crumbsTit">&nbsp;</div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
	<s:form name="form1" action="userCreate" validate="true" method="post">
<s:hidden name="pageNo" value="%{pageNo}"/>
<s:hidden name="jobid" value="%{jobid}"/>
<s:set name="setusertype" value="#{0:'超级管理员',1:'省管理员',2:'地市管理员',3:'操作员'}"/>
<s:set name = "status"  value="#{0:'正常',1:'冻结',2:'注销'}"/>
		<div class="main">
			<div id="DataAddArea">
				<div class="addModBtn">
					<table class="addTable">
						<tbody>
							<tr>
								<td class="w100"></td>
								<td>
									<span class="saveBtn"><span class="inbtns"><input name="insert" type="submit" class="btn" value="保 存" /></span></span>
									<span class="backBtn"><span class="inbtns"><input name="back2"  type="button"   class="btn" onClick="javascript:history.back(-1)" value="返 回" /></span></span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="addMod">
					<table class="addTable">
						<tbody>
							<tr>
								<th class="fTit w100">真实姓名：</th>
								<td><s:textfield name="user.name" cssClass="txt"/></td>
								<th class="fTit w100">性别：</th>
								<td>
								<s:select  cssClass="sel"
			                       name="user.gender"                       
			                       list="#{'M':'男','F':'女'}"                      
			                       required="true"
			                       cssStyle="width:190px"/>
								</td>
							</tr>
							<tr>
								<th class="fTit w100">登录名：</th>
								<td><s:textfield name="user.username" cssClass="txt"/></td>
								<th class="fTit w100">内部工号：</th>
								<td><s:textfield name="user.employeeid" cssClass="txt"/></td>
							</tr>
							<tr>
								<th class="fTit w100">登录密码：</th>
								<td><s:password name="password" cssClass="txt"/></td>
								<th class="fTit w100">状态：</th>
								<td>
								<s:select cssClass="sel" name="user.status" list="#status" cssStyle="width:190px"/>
								</td>
							</tr>
							<tr>
								<th class="fTit w100">Email：</th>
								<td><s:textfield name="user.email" cssClass="txt"/></td>
								<th class="fTit w100">职务</th>
								<td><s:textfield name="user.title" cssClass="txt"/></td>
							</tr>
							<tr>
								<th class="fTit w100">手机号码：</th>
								<td><s:textfield name="user.mobile" cssClass="txt"/></td>
								<th class="fTit w100">办公电话：</th>
								<td><s:textfield name="user.officephone" cssClass="txt"/></td>
							</tr>
							<tr>
								<th class="fTit w100">组织机构：</th>
								<td>
								<s:select cssClass="sel"
		                           name="user.tsysOrg.orgid" 
		                           listKey="key"
		                           listValue="value"
		                           list="orgmap" 
		                           cssStyle="width:190px"/>  
								</td>
								<th class="fTit w100">用户类别：</th>
								<td>
									<s:select cssClass="sel"
		                           name="user.usertype"                       
		                           list="usertypemap"                     
		                           required="true"
		                           cssStyle="width:190px"/>
								</td>
							</tr>
							<tr>
								<th class="fTit w100">归属地区：</th>
								<td colspan="3">
									<s:select cssClass="sel"
		                           name="user.tsysAreaCode.areacode" 
		                           listKey="key"
		                           listValue="value"
		                           list="areacodemap"  
		                           cssStyle="width:190px"/>
								</td>
							</tr>
							<tr>
								<th class="fTit w100" valign="top">备注：</th>
								<td colspan="3">
								<s:textarea name="user.comments" cssClass="txtArea w250"  cols="77" rows="4"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</s:form>
	</div>
	<script type="text/javascript" src="../style/js/jquery.js"></script>
	<script type="text/javascript" src="../style/js/tablegrid.js"></script>
</body>
 
</html>
 