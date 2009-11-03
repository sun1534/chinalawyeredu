<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<html>
	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}

.dtop {
	font-size: 10pt;
	font-weight: bold;
	padding-left: 15px;
	padding-top: 3px;
}

.commenttop {
	font-size: 8pt;
	padding-left: 30px;
}

.comment {
	font-size: 10pt;
	padding-left: 30px;
}

.dcontent {
	font-size: 9pt;
	padding-left: 15px;
	padding-top: 10px;
	padding-right: 10px;
}

span {
	font-size: 10pt;
	font-weight: bold;
	padding-left: 12px;
	padding-top: 3px;
}
-->
</style>


	</head>
	<body>
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="images/b_02.gif" width="4" height="7">
					当前位置：
					<s:if test="arrange.arrangetype==1">
    		岗前培训
    	</s:if>
					<s:else> 
    		活动安排
    	</s:else>
				</td>
			</tr>
		</table>
		<table width="99%" height="50" border="0" align="center"
			cellpadding="0" cellspacing="1">

			<tr>
				<td valign="top" width="100%">
					<div class="dtop">
						内容标题：${arrange.title}
					</div>
					<div class="dtop">
						活动截止：${arrange.arrangend}
					</div>
					<div class="dtop">
						人数限制：${arrange.maxpersons}
					</div>
					
				</td>

			</tr>
		</table>
		<div class="dcontent">
			${arrange.content}
		</div>
	</body>
</html>