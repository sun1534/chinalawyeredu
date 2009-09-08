<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>编辑课件类别</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">

</head>
	<body>
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
		<s:form action="coursetypeEdit" method="post" name="form1" validate="true">
		
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1" bgcolor="#C2D6F0">
			<s:fielderror/>
			<tr>
				<td valign="top" bgcolor="#FFFFFF">
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
						<tr>
							<td height="24" colspan="5" background="../imagesa/top-bg2.gif"
								class="baseFontBold">
								<div align="left">
								</div>
								<div align="center"></div>
								<div align="center"></div>
								<div align="center"></div>
								<div align="center"></div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tab_content1" align="right">
								   课件类别：
							</td>
							<td width="75%" colspan="2" class="tab_content1">
								<s:textfield name="type.typename" size="60" />
							</td>
						</tr>
						
						<tr>
							<td class="tab_content">
							</td>
							<td colspan="2" class="tab_content">
					
									<input type="submit" class="button" value="确认">
									&nbsp;&nbsp;
									<input type="reset" class="button" value="重置">
									&nbsp;&nbsp;
									<input type="button" class="button" value="返回" onclick="history.go(-1)">
							</td>
						</tr>
					</table>
			</s:form>
	</body>
</html>