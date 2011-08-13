<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../images/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 1px;
	margin-right: 2px;
	margin-bottom: 1px;
}
-->
</style>
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF><div align="left"></div>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60"><div align="center"><img src="../images/ico_arrow_title.gif" width="13" height="13"></div></td>
              <td width="97%"><span class="title-blank-b">系统管理&gt;&gt;用户明细</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="middle" valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY class="pt9-18">
                    <TR>
                      <TD width="15%" bgcolor="#FFD275" class="pt9-18"><div align="center">用户名:</div></TD>
                      <TD width="35%" bgcolor="#ECECFF"><s:property value="user.loginname"/></TD>
                      <TD width="15%" bgcolor="#FFD275" class="pt9-18"><div align="center">密 码:</div></TD>
                      <TD width="35%" bgcolor="#ECECFF"><s:property value="user.password"/></TD>
                    </TR>
                    <TR>
                      <TD  bgcolor="#FFD275" class="pt9-18"><div align="center">姓 名:</div></TD>
                      <TD bgcolor="#ECECFF"><s:property value="user.username"/></TD>
                      <TD bgcolor="#FFD275" class="pt9-18"><div align="center">公 司:</div></TD>
                      <TD bgcolor="#ECECFF"><s:property value="user.topmCorp.corpname"/> </TD>
                    </TR>
                    <TR>
                      <TD  bgcolor="#FFD275" class="pt9-18"><div align="center">电 话:</div></TD>
                      <TD bgcolor="#ECECFF"><s:property value="user.phone"/></TD>
                      <TD bgcolor="#FFD275" class="pt9-18"><div align="center">手 机:</div></TD>
                      <TD bgcolor="#ECECFF"><s:property value="user.mobile"/></TD>
                    </TR>
                    <TR>
                      <TD  bgcolor="#FFD275" class="pt9-18"><div align="center">EMAIL:</div></TD>
                      <TD bgcolor="#ECECFF"><s:property value="user.email"/> </TD>
                      <TD bgcolor="#FFD275" class="pt9-18"><div align="center">状 态:</div></TD>
                      <TD bgcolor="#ECECFF">
 <s:if test="user.statusid==1"> 启用 </s:if>
 <s:if test="user.statusid==0"> 冻结 </s:if>
                      </TD>
                    </TR>
                    <TR>
                      <TD  bgcolor="#FFD275" class="pt9-18"><div align="center">描 述:</div></TD>
                      <TD colspan=4 bgcolor="#ECECFF"><s:property value="user.description"/></TD>
                    </TR>
                    <TR bgcolor="#ECECFF">
                      <TD colspan="4" align="center">

                        <input name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回"></TD>
                    </TR>
                  </TBODY>
                </TABLE>
                <p>&nbsp;</p>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
