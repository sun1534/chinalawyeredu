<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script language=javascript>
<!--

function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
}
-->
</script>
</HEAD>
<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%"><span class="sort-title">站内消息&gt;&gt;我的消息</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="suggestDeletes.action" method="POST">
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                      <TD width="15%">发件人</TD>
                      <TD width="45%">主题</TD>
                      <TD width="20%">日期</TD>
                      <TD width="10%">查看</TD>
                      <TD width="10%">删除</TD>
                      </TR>
<s:iterator value="msglist" status="status">
                      <TR class=listline>
                      <TD >${fromuname}</TD>
                      <TD >${title}</TD>
                      <TD ><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></TD>
                      <TD><a href="#" onclick="document.getElementById('frame1').src='msgView.action?msgid=${msgid}'">查看</a></TD>
                      <TD><a href="msgDelete.action?msgid=${msgid}&pagenumber=${pagenumber}">删除</a></TD>
                  </TR>
</s:iterator>
${pagestring}

                  </TBODY>
              </TABLE>
</s:form>

            </TD>
          </TR>
        </TABLE>
    </div>
    <br>
    <div align="center">
   		<iframe id="frame1" src="msgCreate!input.action" frameborder=0 width=100% height=350  scrolling=no/>
    </div>
    
    </TD>
  </TR>
</TABLE>
</BODY>
</HTML>
<s:form name="pageForm" action="suggestList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="suggestCreate!input.action" method="POST">
	<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
