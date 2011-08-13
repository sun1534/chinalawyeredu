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
<script language="javascript">
	
	function setVal(checked,userid,username){
		var frm=document.form1;
		if(checked){
			frm.usernames.value+=username+" ";
			frm.userids.value+=userid+" ";
		}else{
			frm.usernames.value=frm.usernames.value.replace(username+" ","");
			frm.userids.value=frm.userids.value.replace(userid+" ","");
		}
	}
	function checkVal(){
		var frm=document.form1;
		if(frm.usernames.value==null||frm.usernames.value==""){
			alert("请选择收件人。");
			return false;
		}
		if(frm.title.value==null||frm.title.value==""){
			alert("请输入主题。");
			frm.title.focus();
			return false;
		}
		return true;
	}
</script>
</HEAD>
<BODY >

  <TABLE width="95%"  border=0 align=center cellPadding=3 cellSpacing=1 >
    <TBODY>
 <s:form name="form1" action="msgCreate"  method="post" onsubmit="return checkVal()">
 <s:hidden name="pagenumber" value="${pagenumber}"/>
	<TR>
	<TD width="15%" class="listheadline">收件人:</TD>
	<TD  class="listline">
		<input type="text" name="usernames" size="50" readonly value="${msg.fromuname} "/>
		<s:hidden name="userids" value="${msg.fromuid} "/></TD>
	</TR>
 	<TR>
	<TD width="15%" class="listheadline">主题:</TD>
	<TD  class="listline"><s:textfield name="title" size="50" value="Re:${msg.title}"/></TD>
	</TR>

 	<TR>
	<TD class="listline" colspan="2">
	<div style="float:left;width:85%">
	<FCK:editor id="contents" height="190" width="98%"
				skinPath="../editor/skins/default/"
				basePath="../" toolbarSet="Basic"> 
	<br>
	<br>
	<br>
	<br>
	-------------------------------------<br>
	${msg.contents}
	</FCK:editor>
	</div>
	<div>
		<div style="text-align:left;font:bold;color:blue">地址列表</div>
		<div style="border:1px solid #666;valign:top;width:100px;height:200px;overflow:auto;overflow-x:hidden;float:left;text-align:left">
			<s:iterator value="userlist" status="status">
				<s:if test="msg.fromuid==userid">
					<input type="checkbox" name="userid" onclick="setVal(this.checked,'${userid}','${username}')" checked>${username}<br>
				</s:if>
				<s:else>
					<input type="checkbox" name="userid" onclick="setVal(this.checked,'${userid}','${username}')">${username}<br>
				</s:else>
			</s:iterator>
		</div>
	</div>
	</TD>
	</TR>
     <TR bgcolor="#CCCCCC">
       <TD colspan="2" align="center">
         <INPUT name="insert" type="submit" class="botton" value="发送">
 	</TD>
     </TR>
    </s:form>
   </TBODY>
 </TABLE>
             
</TABLE>
</BODY>
</HTML>
