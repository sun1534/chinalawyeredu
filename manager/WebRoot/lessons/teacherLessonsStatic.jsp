<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>课程听课情况统计列表</title>
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
		<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
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
					课程听课情况统计列表
				</td>
			</tr>
		</table>
		<table width="99%" height="316" border="0" align="center"
			cellpadding="0" cellspacing="1">

			<s:form name="form1" action="teacherLessonsStatic" method="post">
			<tr>
				<td valign="top">
			
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						align="center" class="query-table">
						<tr>
							<td align="left">
							<s:hidden name="pageNo" value="1"/> 
                     
                            名称:<s:textfield name="title" size="25"/>
                            <s:if test="!listall">
                            <s:hidden name="teacherid"/>
                            </s:if>
                            <s:else>
                        授课老师:<s:select name="teacherid" list="teacherList" listKey="userid" listValue="username" headerKey="0" headerValue="全部"/>
                            
                            </s:else>
			
							<s:submit value=" 统 计 " cssClass="button" /></td>
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
						
							<TD height="23" align="center" width="50%" background="../imagesa/top-bg1.gif">
								课程名称
							</TD>
						
							<TD align="center" width="15%" background="../imagesa/top-bg1.gif">
								听课次数
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								听课详情
							</TD>
						</tr>

						<s:iterator value="page.items" status="stat">
						<TR>
				
       
							<TD class="tab_content" align="left" title="${title}">&nbsp;
								<a href="lessonsView.pl?lessonid=${lessonid}">${title}</a>
							</TD>
						<TD class="tab_content" align="center">
								${count}
							</TD>
							<TD class="tab_content" align="center">
								<a href="lessonListenList.pl?lessonid=${lessonid}">查看</a>
							</TD>
						
						</TR>

 					</s:iterator>

						<tr style="background-color=#F1F1ED">
							<td colspan="7" align="center">&nbsp;
							</td>
						</tr>

					</table>
					
				</td>
			</tr>
			</s:form>




		</table>
	</body>
</html>