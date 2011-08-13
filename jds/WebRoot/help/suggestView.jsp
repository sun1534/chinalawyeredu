<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<%
/**
 * <p>功能： 查看suggest</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-18</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<style type="text/css">

.commenttop{
	font-size: 8pt;
	padding-left: 10px;
}
.comment{
	font-size: 10pt;
	padding-left: 30px;
}
.dtitle{
	font-size: 10pt;
	font-weight: bold;
	padding-left: 15px;
	padding-top: 3px;
	text-align:center;
}
.dcontent{
	font-size: 9pt;
	padding-left: 15px;
	padding-top: 10px;
}
</style>
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF><div align="left"></div>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center">
		<img src="../images/arr.gif" width="13" height="13">
		</div>
	      </td>
              <td width="97%"><span class="sort-title">问题与建议&gt;&gt;查看问题与建议</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
            	<div class="dtitle">${suggest.title}</div>
            	<div class="dcontent">${suggest.contents}</div>
                
                <br>
				<hr width="95%" align="center"/>
				<s:iterator value="suglist" status="stat">	
				<div class="commenttop">回复人：${createuser}&nbsp;&nbsp;&nbsp;回复日期:<s:date name="createtime" format="yyyy-MM-dd HH:mm"/></div>
				<hr width="95%" align="center"/>
				<div class="comment">${contents}</div>
				<hr width="95%" align="center"/>
				</s:iterator>
               	<s:form name="form1" action="suggestCreate" method="post">
               	<s:hidden name="suggest.parentid" value="${suggest.suggestid}"/>
                <div style="padding-left:15px">
				<FCK:editor id="suggest.contents" height="250" width="80%"
					skinPath="../editor/skins/default/"
					basePath="../" toolbarSet="Basic"
				>
				</FCK:editor>	
				</div>
				<div style="padding-left:300px">
				<input type=submit value="回复" class="button"/>
				<input name="back" type=button class="button" onClick="document.backForm.submit()" value="返回">
				</div>
				</s:form>
				<s:form name="backForm" action="suggestList.action" method="POST">
			   	<s:hidden name="pagenumber" value="${pagenumber}"/>
			   </s:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
