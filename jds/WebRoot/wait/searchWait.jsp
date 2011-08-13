<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=gb2312" language="java" %>

<%
/**
 * <p>功能： 查询Wait</p>
 * <p>作者： zrb</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2005-04-29</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<link href="../css/system.css" rel="stylesheet" type="text/css">
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
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%">
		 <span class="title-blank-b">待办文件&gt;&gt;查询急件</span>
	      </td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
<s:form name="'searchForm'" action="'searchWait.action'" method="'post'">
                <TABLE cellSpacing=1 cellPadding=3 width="60%" align=center  border=0>
                  <TBODY>
                    <TR>
                      <TD width="21%" bgcolor="#FFD275" class="pt9-18"><div align="right">待办id:</div></TD>
                      <TD bgcolor="#ECECFF"><INPUT name="waitExample.waitid" class="input1"> </TD>
                    </TR>
                    <TR>
                      <TD width="21%" bgcolor="#FFD275" class="pt9-18"><div align="right">处理连接:</div></TD>
                      <TD bgcolor="#ECECFF"><INPUT name="waitExample.url" class="input1"> </TD>
                    </TR>
                    <TR>
                      <TD width="21%" bgcolor="#FFD275" class="pt9-18"><div align="right">标题:</div></TD>
                      <TD bgcolor="#ECECFF"><INPUT name="waitExample.subject" class="input1"> </TD>
                    </TR>
                    <TR>
                      <TD width="21%" bgcolor="#FFD275" class="pt9-18"><div align="right"> :</div></TD>
                      <TD bgcolor="#ECECFF"><INPUT name="waitExample.fromto" class="input1"> </TD>
                    </TR>
                    <TR>
                      <TD width="21%" bgcolor="#FFD275" class="pt9-18"><div align="right"> :</div></TD>
                      <TD bgcolor="#ECECFF"><INPUT name="waitExample.userid" class="input1"> </TD>
                    </TR>
                    <TR>
                      <TD width="21%" bgcolor="#FFD275" class="pt9-18"><div align="right">状态 1 已办 0 未办:</div></TD>
                      <TD bgcolor="#ECECFF"><INPUT name="waitExample.status" class="input1"> </TD>
                    </TR>
                    <TR>
                      <TD width="21%" bgcolor="#FFD275" class="pt9-18"><div align="right">流程环节:</div></TD>
                      <TD bgcolor="#ECECFF"><INPUT name="waitExample.flowstep" class="input1"> </TD>
                    </TR>
                    <TR>
                      <TD width="21%" bgcolor="#FFD275" class="pt9-18"><div align="right">缓急状态 3 阅件 2 办件 1 急件:</div></TD>
                      <TD bgcolor="#ECECFF"><INPUT name="waitExample.docstatus" class="input1"> </TD>
                    </TR>
                    <TR>
                      <TD width="21%" bgcolor="#FFD275" class="pt9-18"><div align="right">提交时间:</div></TD>
                      <TD bgcolor="#ECECFF"><INPUT name="waitExample.createtime" class="input1"> </TD>
                    </TR>
                    <TR bgcolor="#ECECFF">
                      <TD colspan="9" align="center">
                       <input  class="botton" type="submit" value="查询">&nbsp;
		       <input name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
                      </TD>
                    </TR>
                  </TBODY>
                </TABLE>
<s:hidden name="'flag'" value="'out'"/>
</s:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>

