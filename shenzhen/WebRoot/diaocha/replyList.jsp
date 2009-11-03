<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>调查列表</title>
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

</script>
<jscalendar:head/>
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
		<table width="99%" height="316" border="0" align="center"
			cellpadding="0" cellspacing="1">

			<s:form name="form1" action="replyList" method="post">
			<s:hidden name="pageNo"/>
			<tr>
				<td valign="top">
			
				
					<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="page-table">
						<tr>
							<td align="right">${page.pageView}</td>
						</tr>
					</table>

					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
						<tr>
						 
							<TD height="23" align="center"
								background="../imagesa/top-bg1.gif">
								回复人
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								回复日期
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								查看答复
							</TD>
						</tr>

						<s:iterator value="page.items" status="stat">
						<TR>
							
							<TD class="tab_content" align="center">
								${(page.items)[stat.index]}
							</TD>
							
							<TD class="tab_content" align="center">
								${(pageTime.items)[stat.index]}
							</TD>
							<TD class="tab_content" align="center">
								<a href="replyView.pl?diaochaid=${diaochaid}&replyuser=${(page.items)[stat.index]}">查看答复</a>
							</TD>

						</TR>

 					</s:iterator>

						<tr background-color"#F1F1ED>
							<td colspan="7" align="center">
								&nbsp;


							</td>
						</tr>

					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr>
							<td height="24" align="center"
								background="../imagesa/login_bg1.gif">
								<INPUT type="button" onClick="history.go(-1)" value=" 返  回 "
									name="addbutton" class="button">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			</s:form>




		</table>
	</body>
</html>