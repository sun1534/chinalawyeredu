<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<!--
<jscalendar:head/>
<link rel="stylesheet" href="../struts/jscalendar/calendar-${curuser.style==1?"bule":""}${curuser.style==2?"brown
":""}${curuser.style==3?"green":""}.css" type="text/css"/>
-->
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style>

</HEAD>
<BODY >

  <TABLE width="95%"  border=0 align=center cellPadding=3 cellSpacing=1 >
    <TBODY>
 <s:form name="form1" action="msgCreate"  method="post">
 <s:hidden name="pagenumber" value="${pagenumber}"/>
	<TR>
	<TD width="15%" class="listheadline">发件人:</TD>
	<TD  class="listline">${msg.fromuname}</TD>
	</TR>
 	<TR>
	<TD width="15%" class="listheadline">主题:</TD>
	<TD  class="listline">${msg.title}</TD>
	</TR>

 	<TR>
	<TD class="listline" colspan="2">
	<div style="float:left;width:85%">
	<FCK:editor id="contents" height="190" width="98%"
				skinPath="../editor/skins/default/"
				basePath="../" toolbarSet="Basic"> 
			${msg.contents}
	</FCK:editor>
	</div>

	</TD>
	</TR>
     <TR bgcolor="#CCCCCC">
       <TD colspan="2" align="center">
         <INPUT name="insert" type="button" onclick="location.href='msgReply.action?msgid=${msg.msgid}'" class="botton" value="回复">
 	</TD>
     </TR>
    </s:form>
   </TBODY>
 </TABLE>
             
</TABLE>
</BODY>
</HTML>
