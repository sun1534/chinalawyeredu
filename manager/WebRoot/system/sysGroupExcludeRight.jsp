<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title><%=com.changpeng.common.Constants.SYS_NAME%></title>
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

</script>
	</head>
	<body topmargin="0" leftmargin="0">
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="../imagesa/b_02.gif" width="4" height="7">
				系统管理&gt;&gt;部门功能排除
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
			<s:form name="form1" action="sysGroupExcludeRight" method="post">
			<s:hidden name="groupid"/>
			<s:hidden name="backurl"/>
				<TR>
					<TD valign="top">
					
						
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#EDEDED">
							<tr>
								<TD height="23" align="center" background="../imagesa/top-bg1.gif">
									选择
								</TD>
								<TD align="center" background="../imagesa/top-bg1.gif">
									排除的功能
								</TD>
								<TD align="center" background="../imagesa/top-bg1.gif">
									子部门是否也排除
								</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
									备注说明
								</TD>
							
							</TR>
							<s:iterator value="allRights" status="stat">
								<TR>
							 <TD class="tab_content" align="center">
								 <s:if test="excludedRightcode.contains(rightcode)">
								 <input type="checkbox" name="excludedRightcode" value="${rightcode }" checked/>
								</s:if>
								<s:else>
									 <input type="checkbox" name="excludedRightcode" value="${rightcode }" />
								</s:else>
									</TD>
									<TD class="tab_content" align="center">
										${rightname}
									</TD>
									<TD class="tab_content" align="center">
								     <select name="recursion">
									    <option value="0">不排除</option>
										<option value="1">也排除</option>
									 </select>
									</TD>
							     
									<TD class="tab_content" align="center">
								  	 <s:if test="excludedRightcode.contains(rightcode)">
								 <input type="text" name="remarks" value="<s:property value="maps[rightcode].remarks"/>" size="35"/>
								</s:if>
								<s:else>
									 <input type="text" name="remarks" size="35"/>
								</s:else>
								   
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
									<INPUT type="submit" value=" 确认排除 " name="addbutton" class="button">

								</TD>
							</TR>
						</table>
					</TD>
				</TR>
			</s:form>
		</TABLE>
	</body>
</html>