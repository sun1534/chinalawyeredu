
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>调查统计</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/css.css" rel="stylesheet" type="text/css">

<style type ="text/css">
<!--
body {
	background-color: #DAE7F6;
}
.ms-standardheader {
	font-size: 1em;
	margin: 0em;
	text-align: left;
	color: #525252;
}

.ms-surveyHBarLabel {
	font-size: 9pt;
	font-family: Simsun, 宋体, tahoma, sans-serif;
	color: #7f7f7f;
	text-decoration: none;;
	height: 3.75pt;
	margin: 0;
	padding: 0;
	border: none;
	background-color: white;
}

.ms-surveyHBarB {
	font-size: 2px;
	background-color: #CCCCCC;
	border: solid;
	border-width: 1px;
	border-color: #CCCCCC;
}

.ms-hidden {
	position: absolute;
	left: 0px;
	top: -500px;
	width: 1px;
	height: 1px;
	overflow: hidden;
}
.ms-surveyHBar {
	font-size: 2px;
	background-color: #3966bf;
	border: solid;
	border-width: 5px;
	border-color: #3966bf;
}
.sort-title {
	FONT-WEIGHT: bold; FONT-SIZE: 13px; COLOR: #333333; FONT-FAMILY: Arial, Helvetica, sans-serif
}
-->
</style>
<script language="javascript">
function div(a,b){
	var c=(a/b)*100+"";
	if(c.length>4)
	  c=c.substring(0,4);
	c=c+"%";
	document.write(c);
}
</script>

	</head>
	<body>
	<table align="center">
		<tr>
			<td align="center" class="sort-title">"${diaocha.title}"统计结果</td>
		</tr>
		<tr>
		<td>
		<s:iterator value="wentilist" status="status">
		<H3 class="ms-standardheader" >
			${status.index+1}. ${title}
		</H3>
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td style="height: 3.75pt"></td>
			</tr>
			
			<s:iterator value="diaochaoptions" status="status2">
			<tr>
				<td style="font-size: .01em; width: 1pt; padding-right: 17pt">
					&nbsp;
				</td>
				<td class="ms-vb" style="padding-bottom: 5px">
					${options}.${title}
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="left">
					<table border="0" cellpadding="0" cellspacing="0"
						style="width: 375pt">
						<tr>
							<td class="ms-surveyHBarLabel"
								style="font-size: .01em; width: 5pt">
								&nbsp;
							</td>
							<td class="ms-SurveyHBarLabel" style="width: 70pt">
								<label class="ms-hidden">
									答复数量
								</label>
								${replycount}&nbsp;								
								<label class="ms-hidden">
									答复百分比
								</label>
								<s:set name="rate" value="0" />
								<s:if test="diaochareplys.size()!=0">
								<s:set name="rate" value="replycount*100/diaochareplys.size()" />
								</s:if>
								<s:property value="#rate" />%
								&nbsp;
							</td>
							<td class="ms-surveyHBarB" style="height: 11pt">
								
								<table border="0" cellpadding="0" cellspacing="0" height="100%"
									width="<s:property value="#rate" />%">
									<tr>
										<td class="ms-surveyHBar">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="height: 7.5pt"></td>
			</tr>
			</s:iterator>
			
			<tr>
				<td></td>
				<td valign="top" colspan="3">
					<hr size="1" class="ms-surveyHR">
					<div class="ms-surveyTotal">
						总计: <s:property value="diaochareplys.size()" />
					</div>
				</td>
			</tr>
		</table>
		<br>
		</s:iterator>
		</td>
	</tr>
	<tr><td align="center"><input type="button" value=" 返  回 " class="button" onclick="history.go(-1)"></td></tr>
	</table>
</body>
</html>