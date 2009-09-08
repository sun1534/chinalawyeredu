<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>课件类别列表</title>
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

			<s:form name="form1" action="courseViewList" method="post">
			<s:hidden name="pageNo" />
			<s:hidden name="typeid" />
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
					<!--	 <TD align="center" background="../imagesa/top-bg1.gif">
					        <s:checkbox name="checkAll" onclick="getCheckAll(this)"/>
					      </TD>
					       -->
							<TD height="23" width="10%" align="center"
								background="../imagesa/top-bg1.gif">
								编号
							</TD>
							<TD align="center" width="55%" background="../imagesa/top-bg1.gif">
								课件类别
							</TD>							
							<TD align="center" width="15%" background="../imagesa/top-bg1.gif">
								课件数量
							</TD>	
							<TD align="center" width="20%" background="../imagesa/top-bg1.gif">
								查看课件
							</TD>						
							
						</tr>

						<s:iterator value="page.items" status="stat">
						<TR>
							<!--<TD class="tab_content" align="center">
									<input type="checkbox" name="check" value="${diaochaid}"/>
							</TD> -->
							<TD class="tab_content" align="center">
								${stat.index+1}
							</TD>
							
							<TD class="tab_content" align="left">
								&nbsp;&nbsp;
								<a href="courseViewList!warelist.pl?typeid=${typeid}">
								${typename}
								</a>
							</TD>
					
							<TD class="tab_content" align="center">
								${warecount}
							</TD>
							<TD class="tab_content" align="center">
								<a href="courseViewList!warelist.pl?typeid=${typeid}">查看课件</a>
							</TD>		
						</TR>

 					</s:iterator>

						<tr>
							<td colspan="7" align="center">
								&nbsp;
							</td>
						</tr>

					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr>
							<td height="24" align="center"
								background="../imagesa/login_bg1.gif">
								
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