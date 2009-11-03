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
function getAdd(){
	window.location.href="diaochaCreate!input.pl";
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

			<s:form name="form1" action="diaochaList" method="post">
			<s:hidden name="pageNo" />
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
							<TD height="23" align="center"
								background="../imagesa/top-bg1.gif">
								调查名称
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								创建人
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								状态
							</TD>				
							<TD align="center" background="../imagesa/top-bg1.gif">
								回复次数
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								调查结果
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								调查问题
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								修改
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								删除
							</TD>
						</tr>

						<s:iterator value="page.items" status="stat">
						<TR>
							<!--<TD class="tab_content" align="center">
									<input type="checkbox" name="check" value="${diaochaid}"/>
							</TD> -->
							<TD class="tab_content" align="left">
&nbsp;&nbsp;
								${title}
							</TD>
							
							<TD class="tab_content" align="center">
								${createuser}
							</TD>
							<TD class="tab_content" align="center">
								<s:if test="state==1">草稿</s:if>
								<s:elseif test="state==2">发布</s:elseif>
								<s:elseif test="state==3">停止</s:elseif>
							</TD>

							<TD class="tab_content" align="center">
								<s:if test="replycount>0">
								<a href="replyList.pl?diaochaid=${diaochaid}">${replycount}</a>
								</s:if>
								<s:else>暂无</s:else>
							</TD>
							<TD class="tab_content" align="center">
								<a href="diaochaStat.pl?diaochaid=${diaochaid}">查看</a>							
							</TD>
							<TD class="tab_content" align="center">
								<a href="wentiList.pl?diaochaid=${diaochaid}">查看</a>
							</TD>
							<TD class="tab_content" align="center">
								<a href="diaochaEdit!input.pl?diaochaid=${diaochaid}">修改</a>
							</TD>
							<TD class="tab_content" align="center">
								<a href="diaochaDelete.pl?diaochaid=${diaochaid}">【删除】</a>
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
								<INPUT type="button" onClick="return getAdd()" value="新增调查"
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