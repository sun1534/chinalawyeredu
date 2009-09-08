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
	window.location.href="theUnionCreateEditPre.pl";
}
function delGroup(groupid,parentid,type){
	if(confirm("确定要删除该律协吗？"))
		//location.href="sysGroupDelete.pl?groupid="+groupid+"&parentid="+parentid;
				location.href="theUnionDelete.pl?type="+type+"&groupid="+groupid+"&parentid="+parentid;
	else
		return false;
}
</script>
	</head>
	<body topmargin="0" leftmargin="0">
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" class="baseFontBold" style="background:../imagesa/top-bg3.gif">
					<img src="../imagesa/b_02.gif" width="4" height="7"/>
				律协列表
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
			<s:form name="form1" action="theUnionList" method="post">
				<TR>
					<TD valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							align="center" class="query-table">
							<tr>
								<td align="left">
									<s:hidden name="pageNo" value="1" />
									<span style="font-weight: bold"> 
									律协名称:<s:textfield name="groupname" size="15" /> 
									<s:if test="grouptypeview">
									<s:select name="grouptype" list="#{'2':'市律协','3':'省律协'}" headerKey="0" headerValue="全部" label="类型"/>
									</s:if>
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
									律协名称
								</TD>
								<TD align="center" background="../imagesa/top-bg1.gif">
									律协简称
								</TD>
								<TD align="center" background="../imagesa/top-bg1.gif">
									联系人
								</TD>
								<TD align="center" background="../imagesa/top-bg1.gif">
									管理员
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

									<TD class="tab_content" align="center">
										<a href="../system/sysGroupManager.pl?groupid=${groupid}">查看</a>
									</TD>
	<s:if test="hasright">
									<TD class="tab_content" align="center">
									<a href="sysGroupExcludeRight!input.pl?groupid=${groupid }">功能排除</a>
								</TD>
								</s:if>
									<TD class="tab_content" align="center">
										<a href="theUnionCreateEditPre.pl?groupid=${groupid}">修改</a>
									</TD>
									<TD class="tab_content" align="center">
										<a href="#" onclick="javascript:delGroup(${groupid},${parentid},'2')">【删除】</a>
									</TD>
								</TR>
							</s:iterator>
							<tr style='background-color="#F1F1ED"'>
								<td colspan="7" align="center">
									&nbsp;
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="1">
							<tr>
								<td height="24" align="center"
									background="../imagesa/login_bg1.gif">
									<INPUT type="button" onclick="return getAdd()" value=" 新增市律协 "
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