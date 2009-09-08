<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title><%=com.changpeng.common.Constants.SYS_NAME%>-${navigator}</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
		<script type="text/javascript" src="../js/common.js"></script>
		<script language="javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
	window.location.href="sysGroupCreateEdit!input.pl";
}
function delGroup(groupid,parentid){
	if(confirm("确定要删除该部门吗？"))
		location.href="sysGroupDelete.pl?groupid="+groupid+"&parentid="+parentid;
	else
		return false;
}
</script>
	</head>
	<body topmargin="0" leftmargin="0">
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="../imagesa/b_02.gif" width="4" height="7">
					系统管理->部门列表
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
			<s:form name="form1" action="sysGroupList" method="post">
				<TR>
					<TD valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							align="center" class="query-table">
							<tr>
								<td align="left">
									<s:hidden name="pageNo" value="1" />
									<span style="font-weight: bold"> 
									部门名称:<s:textfield name="groupname" size="15" />  
									<s:submit value=" 查询 " cssClass="button" /> 
								    </span>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="1">
							<tr>
								<td height="24" align="right"
									background="../imagesa/login_bg1.gif">
									${page.pageView}
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#EDEDED">
							<tr>
								<TD height="23" align="center"
									background="../imagesa/top-bg1.gif">
									部门名称
								</TD>
								<TD align="center" background="../imagesa/top-bg1.gif">
									部门简称
								</TD>
								<TD align="center" background="../imagesa/top-bg1.gif">
									联系人
								</TD>
								<s:if test="hasright">
								<TD align="center" background="../imagesa/top-bg1.gif">
									功能排除
								</TD>
								</s:if>
							
								<TD align="center" background="../imagesa/top-bg1.gif">
									修改
								</TD>
								<TD align="center" background="../imagesa/top-bg1.gif">
									删除
								</TD>
							</TR>
							<s:iterator value="page.items" status="stat">
								<TR>
									<TD class="tab_content" align="center">
										${groupname}
									</TD>
									<TD class="tab_content" align="center">
										${groupenname}
									</TD>
									<TD class="tab_content" align="center">
										${contacter}
									</TD>

							<s:if test="hasright">
									<TD class="tab_content" align="center">
									<a href="sysGroupExcludeRight!input.pl?groupid=${groupid }">功能排除</a>
								</TD>
								</s:if>

									<TD class="tab_content" align="center">
										<a href="sysGroupCreateEdit!input.pl?groupid=${groupid}">修改</a>
									</TD>
									<TD class="tab_content" align="center">
										<a href="#" onClick="javascript:delGroup(${groupid},${parentid})">【删除】</a>
									</TD>
								</TR>
							</s:iterator>
							<tr style="background-color='#F1F1ED'">
								<td colspan="7" align="center">&nbsp;
									
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="1">
							<tr>
								<td height="24" align="center"
									background="../imagesa/login_bg1.gif">
									<INPUT type="button" onClick="return getAdd()" value=" 新增部门 "
										name="addbutton" class="button">

								</TD>
							</TR>
						</table>
					</TD>
				</TR>
			</s:form>
		</TABLE>
	</body>
</html>