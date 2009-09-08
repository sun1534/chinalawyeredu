<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-系统管理-系统管理员列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
<jscalendar:head />
<!-- 引入prototype.js -->

<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<script language="javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
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
					${navigator}
				</td>
			</tr>
		</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<s:form name="form1" action="sysOperLogList" method="post">
		<TR>
			<TD valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
							align="center" class="query-table">
	 		<tr>
					<td align="left">
					<s:hidden name="pageNo" value="1"/> 
					<span style="font-weight: bold">
					操作时间:<jscalendar:jscalendar	name="opTime" format="%Y-%m-%d" showstime="false" cssClass="text1" />
 
					<!-- 通过json的方式来实现 --> 
					 <s:submit value=" 查 询 " cssClass="button" />
					</span>
					</td>
						
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				align="center" class="page-table">
				<tr>
					<td align="right">${page.pageView}</td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#EDEDED">
				<TR>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">操作人员</TD>
					<TD align="center" background="../imagesa/top-bg1.gif">对应功能</TD>
					<TD align="center" background="../imagesa/top-bg1.gif">操作时间</TD>
					<TD align="center" width="60%" background="../imagesa/top-bg1.gif">操作结果</TD>
				</TR>
				<s:iterator value="page.items" status="stat">
					<TR>
						<TD align="center" class="tab_content" >${username}</TD>
						<TD align="center" class="tab_content" ><s:property
							value="@com.changpeng.system.util.RightTree@rightNameMap[rightCode]" /></TD>
						<TD align="center" class="tab_content" >${opTime}</TD>
						<TD align="center" class="tab_content" >${opResult}</TD>
					</TR>
				</s:iterator>
				<tr background-color"#F1F1ED>
								<td colspan="7" align="center">&nbsp;
									
								</td>
							</tr>
			</table>
			<TABLE cellSpacing="1" cellPadding="1" width="100%" align="center"
				border="0" class="content-table">
				<TR>
					<TD align="center">&nbsp;</TD>
				</TR>
			</TABLE>
			</TD>
		</TR>
	</s:form>
</TABLE>
</body>
</html>