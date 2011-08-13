<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%
	
%>

<HTML>
	<HEAD>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">

		<link rel="stylesheet"
			href="../struts/jscalendar/calendar-${curuser.style==1?"
			bule":""}${curuser.style==2? "brown":""}${curuser.style==3?
			"green":""}.css" type="text/css" />
		<link href="../css/system.css" rel="stylesheet" type="text/css">
		<link href="../css/system_${curuser.style}.css" rel="stylesheet"
			type="text/css">
		<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style>
		<jscalendar:head />
	</HEAD>
	<BODY>
		<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=5>
			<TR>
				<TD height=30 bgColor=#FFFFFF>
					<div align="left">
						<table width="80%" border="0" cellspacing="0" cellpadding="4">
							<tr>
								<td width="60">
									<div align="center">
										<img src="../images/arr.gif" width="13" height="13">
									</div>
								</td>
								<td width="97%">

									<span class="sort-title">诉讼案件&gt;&gt;转结案 </span>
								</td>
							</tr>
						</table>
					</div>
				</TD>
			</TR>
			<TR>
				<TD height="171" valign="top" bgColor=#FFFFFF>
					<div align="center">
						<TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999
							border=0>
							<TR>
								<TD valign="top" bgColor=#F9F9F7>
									<br>
									<table width="580" border=0 align=center cellpadding=3
										cellspacing=1>
										<tbody>
											<s:form name="form1" action="case2endpre" method="post">
												<s:fielderror />
												<s:actionerror />

												<s:hidden name="pagenumber" value="${pagenumber}" />

												<tr>
													<td class="listheadline">
														案件进度备注信息：
													</td>
													<TD class="listline">
														<s:hidden name="tonext" />
														<s:hidden name="actionid" />
														<s:hidden name="nodeid" />
														<s:hidden name="caseid" />
														<s:textarea name="planlogs" cols="40" rows="5" />
													</TD>

												</tr>



												<tr bgcolor="#CCCCCC">
													<td colspan="4" align="center">
														<input name="insert" type="submit" class="botton"
															value="保存">
														&nbsp;
														<input name="back" type=button class="botton"
															onClick="javascript:history.back(-1)" value="返回">
													</td>
												</tr>
											</s:form>
										</tbody>
									</table>
									<br>
								</TD>
							</TR>
						</TABLE>
					</div>
				</TD>
			</TR>
		</TABLE>
	</BODY>
</HTML>
