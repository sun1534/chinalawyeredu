<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>课件列表</title>
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
					<img src="../imagesa/b_02.gif" width="4" height="7">
					${navigator}
				</td>
			</tr>
		</table>
		<table width="99%" height="316" border="0" align="center"
			cellpadding="0" cellspacing="1">

			<s:form name="form1" action="courseViewList!warelist.pl" method="post">
			<s:hidden name="pageNo" />
			<s:hidden name="typeid" />
			<tr>
				<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						align="center" class="query-table">
						<tr>
							<td align="left">
						 	课件名称:<s:textfield name="warename"/>
						 	
							<s:submit value=" 查 询 " cssClass="button" /></td>
						</tr>
					</table>
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
							<TD height="23" width="5%" align="center"
								background="../imagesa/top-bg1.gif">
								编号
							</TD>
							<TD align="center" width="40%" background="../imagesa/top-bg1.gif">
								课件名称
							</TD>
							<TD align="center"  width="15%" background="../imagesa/top-bg1.gif">
								录入时间
							</TD>
							<TD align="center"  width="10%" background="../imagesa/top-bg1.gif">
								课件学分
							</TD>
							<TD align="center"  width="10%" background="../imagesa/top-bg1.gif">
								已或学分
							</TD>
							<TD align="center" width="10%" background="../imagesa/top-bg1.gif">
								阅读次数
							</TD>							
							<TD align="center" width="10%" background="../imagesa/top-bg1.gif">
								查看
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
								<a href="coursewareView.pl?wareid=${wareid}">
								${warename}
								</a>
							</TD>
							<TD class="tab_content" align="center">
								<s:date name="createtime" format="yyyy-MM-dd"/>
							</TD>
							<TD class="tab_content" align="center">
								${xuefen}
							</TD>
							<TD class="tab_content" align="center">
							<s:if test="userlessonxf!=null"> 
								${userlessonxf.pxxf} <br/>	
							</s:if>
							<s:else>&nbsp;
							</s:else>
							</TD>
							<TD class="tab_content" align="center">
								${readcount}
							</TD>
							<TD class="tab_content" align="center">								
								<a href="coursewareView.pl?wareid=${wareid}">【查看】</a>
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
								<INPUT type="button" onClick="location.href='../courseware/courseViewList.pl'" value=" 返  回 "
									name="addbutton" class="button">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			</s:form>
		</table>
	</body>
</html>