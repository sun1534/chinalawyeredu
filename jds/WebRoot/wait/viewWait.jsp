<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=gb2312" language="java" %>

<%
/**
 * <p>功能： 查看Wait</p>
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
    <TD height=30 bgColor=#FFFFFF><div align="left"></div>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center">
		<img src="../images/arr.gif" width="13" height="13">
		</div>
	      </td>
              <td width="97%"><span class="title-blank-b">待办文件&gt;&gt;查看急件</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#7F9DB9">
                  <TBODY class="pt9-18">
                    <TR>
                      <TD width=20% bgcolor="#FFD275" class="pt9-18"><div align="center">待办id:</div></TD>
                      <TD bgcolor="#ECECFF">
			  <s:property value="wait.waitid"/>
                      </TD>
                    </TR>
                    <TR>
                      <TD width=20% bgcolor="#FFD275" class="pt9-18"><div align="center">处理连接:</div></TD>
                      <TD bgcolor="#ECECFF">
			  <s:property value="wait.url"/>
                      </TD>
                    </TR>
                    <TR>
                      <TD width=20% bgcolor="#FFD275" class="pt9-18"><div align="center">标题:</div></TD>
                      <TD bgcolor="#ECECFF">
			  <s:property value="wait.subject"/>
                      </TD>
                    </TR>
                    <TR>
                      <TD width=20% bgcolor="#FFD275" class="pt9-18"><div align="center"> :</div></TD>
                      <TD bgcolor="#ECECFF">
			  <s:property value="wait.fromto"/>
                      </TD>
                    </TR>
                    <TR>
                      <TD width=20% bgcolor="#FFD275" class="pt9-18"><div align="center"> :</div></TD>
                      <TD bgcolor="#ECECFF">
			  <s:property value="wait.userid"/>
                      </TD>
                    </TR>
                    <TR>
                      <TD width=20% bgcolor="#FFD275" class="pt9-18"><div align="center">状态 1 已办 0 未办:</div></TD>
                      <TD bgcolor="#ECECFF">
			  <s:property value="wait.status"/>
                      </TD>
                    </TR>
                    <TR>
                      <TD width=20% bgcolor="#FFD275" class="pt9-18"><div align="center">流程环节:</div></TD>
                      <TD bgcolor="#ECECFF">
			  <s:property value="wait.flowstep"/>
                      </TD>
                    </TR>
                    <TR>
                      <TD width=20% bgcolor="#FFD275" class="pt9-18"><div align="center">缓急状态 3 阅件 2 办件 1 急件:</div></TD>
                      <TD bgcolor="#ECECFF">
			  <s:property value="wait.docstatus"/>
                      </TD>
                    </TR>
                    <TR>
                      <TD width=20% bgcolor="#FFD275" class="pt9-18"><div align="center">提交时间:</div></TD>
                      <TD bgcolor="#ECECFF">
			  <s:property value="wait.createtime"/>
                      </TD>
                    </TR>
                    <TR>
                      <TD  bgcolor="#FFD275" class="pt9-18"><div align="center">状态:</div></TD>
                      <TD bgcolor="#ECECFF">
<s:if test="wait.statusid==1">启用
</s:if>
<s:if test="wait.statusid==0">冻结
</s:if>
		      </TD>
                    </TR>
                    <TR bgcolor="#ECECFF">
                      <TD colspan="4" align="center">
                        <input name="back2" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
                      </TD>
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

