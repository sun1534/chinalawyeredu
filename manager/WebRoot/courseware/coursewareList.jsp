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
function getAdd(){
	window.location.href="coursewareCreate!input.pl?typeid=${typeid}";
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

			<s:form name="form1" action="coursewareList" method="post">
			<s:hidden name="pageNo" />
			<s:hidden name="typeid" />
			<tr>
				<td valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
						align="center" class="query-table">
						<tr>
							<td align="left">
							<span style="font-weight: bold">
						 	课件名称:<s:textfield name="warename"/>					 	
							<s:submit value=" 查 询 " cssClass="button" />
							</span><br>
							<span style="color:red">
							注意：修改课件时，不允许课件上传；上传课件不能被修改。
							</span>
							</td>
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
							<TD align="center" width="30%" background="../imagesa/top-bg1.gif">
								课件名称
							</TD>
							<TD align="center"  width="15%" background="../imagesa/top-bg1.gif">
								录入时间
							</TD>
							<TD align="center"  width="10%" background="../imagesa/top-bg1.gif">
								是否上传
							</TD>	
							<TD align="center"  width="10%" background="../imagesa/top-bg1.gif">
								学分
							</TD>					
							<TD align="center" width="10%" background="../imagesa/top-bg1.gif">
								阅读次数
							</TD>							
							<TD align="center" width="10%" background="../imagesa/top-bg1.gif">
								修改
							</TD>
							<TD align="center" width="10%" background="../imagesa/top-bg1.gif">
								删除
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
								<s:if test="isupload">是</s:if>
								<s:else>否</s:else>
							</TD>
							<TD class="tab_content" align="center">
								${xuefen}
							</TD>
							<TD class="tab_content" align="center">
								${readcount}
							</TD>
							<TD class="tab_content" align="center">								
								<s:if test="isupload">【修改】</s:if>
								<s:else><a href="coursewareEdit!input.pl?wareid=${wareid}">【修改】</a></s:else>
							</TD>
							<TD class="tab_content" align="center">
								<a href="coursewareDelete.pl?typeid=${coursetype.typeid}&wareid=${wareid}">【删除】</a>
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
								<INPUT type="button" onClick="return getAdd()" value="新增课件"
									name="addbutton" class="button">
								&nbsp;&nbsp;
								<INPUT type="button" onClick="location.href='coursetypeList.pl'" value=" 返  回 "
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