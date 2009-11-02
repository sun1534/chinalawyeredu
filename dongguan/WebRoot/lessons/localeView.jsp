<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<html>
	<head>
		<title>课程列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
.dtop{
	font-size: 10pt;
	font-weight: bold;
	padding-left: 15px;
	padding-top: 3px;
}
.commenttop{
	font-size: 8pt;
	padding-left: 30px;
}
.comment{
	font-size: 10pt;
	padding-left: 30px;
}
.dcontent{
	font-size: 9pt;
	padding-left: 15px;
	padding-top: 10px;
	padding-right: 10px;
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
					${navigator}
				</td>
			</tr>
		</table>
		<table width="99%" height="50" border="0" align="center"
			cellpadding="0" cellspacing="1">

			<tr>
				<td valign="top" width="100%">
					<div class="dtop">名称：${lesson.title}</div>
					<div class="dtop">讲师：${lesson.teachers}</div>				
					<div class="dtop">类别：
								<s:if test="lesson.lessontype==0">大型专题讲座</s:if>
								<s:elseif test="lesson.lessontype==1">学术研讨会</s:elseif>
								<s:elseif test="lesson.lessontype==2">实务培训学习</s:elseif>
								<s:elseif test="lesson.lessontype==3">其他</s:elseif>
					</div>
					<div class="dtop">学分：${lesson.xuefen}</div>
					<div class="dtop">时间：${lesson.lessondate}-${lesson.lessonend}</div>
					<div class="dtop">地点：${lesson.lessonaddress}</div>					
					<div class="dtop">限制：${lesson.maxpersons}</div>
					<s:if test="hasattach">
					<div class="dtop">附件（右键点击下载）：<s:iterator value="filelist" status="stat"><a href="download.pl?filename=${filelist[stat.index]}">${filelist[stat.index]}</a>&nbsp;&nbsp;&nbsp;</s:iterator></div>
					</s:if>
				</td>
			
        	</tr>
        </table>
		<div class="dcontent" id="lessoncontent">
				${lesson.lessoncontent}
		</div>
		<div class="dtop">评论：</div>
		<hr width="95%" align="center"/>
		<s:iterator value="lesson.lessonreplys" status="stat">	
		<div class="commenttop">评论人：${replyuser}&nbsp;&nbsp;&nbsp;评论日期:<s:date name="replytime" format="yyyy-MM-dd HH:mm"/></div>
		<hr width="95%" align="center"/>
		<div class="comment">${replycontent}</div>
		<hr width="95%" align="center"/>
		</s:iterator>
		<s:form name="form1" method="post" action="replyCreate" >
		<input type="hidden" name="lessonid" value="${lesson.lessonid}">
		<div style="padding-left:15px">
		<FCK:editor id="replycontent" height="200" width="80%"
			skinPath="../editor/skins/default/"
			basePath="../" toolbarSet="Basic"
			>
		</FCK:editor>	
		</div>
		<div style="padding-left:300px"><input type=submit value="发表评论" class="button"/></div>
		</s:form>		
	</body>
</html>