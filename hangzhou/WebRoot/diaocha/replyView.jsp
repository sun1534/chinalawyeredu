<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>调查问题</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">		
		<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
.xian {
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 1px;
	border-left-width: 0px;
	border-bottom-color: black;
	color: black;
	background-color: #F2F8FF;
}
.sort-title {
	FONT-WEIGHT: bold; FONT-SIZE: 13px; COLOR: #333333; FONT-FAMILY: Arial, Helvetica, sans-serif
}

-->
</style>
	</head>
	<body>
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="images/b_02.gif" width="4" height="7">
					${navigator}
				</td>
			</tr>
		</table>
		<br>

		<TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
			<TR>
				<TD valign="top" bgColor=#F9F9F7>
					<TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
						<TR>
							<TD valign="top" bgColor=#F9F9F7>
								<div id="content">
								<TABLE width="100%" border=0 align=center cellPadding=3 cellSpacing=1>
									<TBODY>
										<TR>
											<TD class="tab_content">
												<div align="center" class="sort-title">
													${diaocha.title}
												</div>
											</TD>
										</TR>
										<TR>
											<TD class="tab_content">
												<div align="left" style="color:blue">
													&nbsp;&nbsp;&nbsp;&nbsp;${diaocha.shuoming}
												</div>
											</TD>
										</TR>
										
										<s:iterator value="wentilist" status="status">
										<TR>
											<TD  class="tab_content">
												${status.index+1}.${title}&nbsp;&nbsp;
												<s:if test="wentileixing==1||wentileixing==2">
												（<input type="text" name="replys" id="wenti${status.index+1}" value="${anwsers[status.index]}" size="5" class="xian" readonly/>）
												</s:if>
											</TD>
										</TR>
										<s:if test="wentileixing==1">
										<s:iterator value="diaochaoptions" status="status2">
										<TR>
											<TD  class="tab_content">
												&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  value="${options}" name="wenti${status.index+1}">${options}.${title}
												<s:if test="others"><input type="text" size="80" maxlength="80" class="xian" name="other" value="${replysothers[status.index]}"/></s:if>
											</TD>
										</TR>
										</s:iterator>
										</s:if>
										<s:elseif test="wentileixing==2">
										<s:iterator value="diaochaoptions" status="options">
										<TR>
											<TD  class="tab_content">
												&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" value="${options}" name="wenti${status.index+1}">${options}.${title}
												<s:if test="others"><input type="text" size="80" maxlength="80" class="xian" name="other" value="${replysothers[status.index]}"/></s:if>
											</TD>
										</TR>
										</s:iterator>
										</s:elseif>
										<s:elseif test="wentileixing==3">										
										<TR>
											<TD  class="tab_content">
												&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="replys" value="${anwsers[status.index]}" size="80" maxlength="80" class="xian" />
											</TD>
										</TR>
										</s:elseif>									
										<s:elseif test="wentileixing==4">										
										<TR>
											<TD  class="tab_content">
												&nbsp;&nbsp;&nbsp;&nbsp;<textarea name="replys" cols="60" rows="5">${anwsers[status.index]}</textarea>
											</TD>
										</TR>
										</s:elseif>	
										</s:iterator>
									</TBODY>
								</TABLE>
								</div>
							</TD>
						</TR>
					</TABLE>
					
					<table width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr>
							<td height="24" align="center"
								background="../imagesa/login_bg1.gif">	
								<input type="button" class="button" value=" 返  回 " onclick="history.go(-1)">	
							</td>
						</tr>
					</table>
				</TD>
			</TR>
		</table>
	
	</body>
</html>