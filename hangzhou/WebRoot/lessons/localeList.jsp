<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>现场课程</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}

.tab {
	font-size: 9pt;
	color: #666666;
	background-color: #F2F8FF;
	background-repeat: no-repeat;
	background-position: right bottom;
	height: 15px;
}
-->
</style>

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
		<s:form name="form1" action="lessonsLocalList" method="post">
			<s:hidden name="pageNo" /> 
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
						align="center" class="query-table">
			<tr>
				<td align="left">
		
				<span style="font-weight: bold">
			 	课程年份:<s:select name="year" list="years" headerKey="-1" headerValue="请选择" onchange="document.form1.submit()"/>
			 	（从【${from}】到【${end}】）
			 	</span>
			 	</td>
			</tr>
     	 </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				align="center" class="page-table">
				<tr>
					<td align="right">
						${page.pageView}
					</td>
				</tr>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#EDEDED">
				<tr>
					<TD height="23" width="27%" align="center" background="../imagesa/top-bg1.gif">
						课程名称
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						主讲人
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						课程类别
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						课时学分
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						授课时间
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						授课地点
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						人数限制
					</TD>
				</tr>
				<s:iterator value="lessonList" status="stat">
					<TR>
						<TD class="tab_content" align="left">
							<a href="lessonsLocalView.pl?lessonid=${lessonid}">${title}</a>
						</TD>				
						<TD class="tab_content" align="center">
							${teachers}
						</TD>
						<TD class="tab_content" align="center">
							<s:if test="lessontype==0">
									大型专题讲座
								</s:if>
							<s:elseif test="lessontype==1">
									学术研讨会
								</s:elseif>
							<s:elseif test="lessontype==2">
									实务培训学习
								</s:elseif>
							<s:elseif test="lessontype==3">
									其他
								</s:elseif>
						</TD>
						
						<TD class="tab_content" align="center">
							${xuefen}
						</TD>

						<TD class="tab_content" align="center">
							${lessondate}

						</TD>
						<TD class="tab_content" align="center">
							${lessonaddress}
						</TD>
						<TD class="tab_content" align="center">
							<s:if test="maxpersons==0">
							无限制
							</s:if>
							<s:else>
							${maxpersons}
							</s:else>
						</TD>

					</TR>
				</s:iterator>
			
			</table>

		</s:form>

	</body>
</html>