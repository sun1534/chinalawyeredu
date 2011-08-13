<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查询process</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-10-15</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
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
		 <span class="title-blank-b">流程管理&gt;&gt;查询流程</span>
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
<s:form name="searchForm" action="processSearch.action" method="post">
                <TABLE cellSpacing=1 cellPadding=3 width="60%" align=center  border=0>
                  <TBODY>
                    <TR>
                      <TD width="15%" class="listheadline">流程id:</TD>
                      <TD width="35%" class="listline"><INPUT name="Example.processid"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">流程名称:</TD>
                      <TD width="35%" class="listline"><INPUT name="Example.processname"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">描述:</TD>
                      <TD width="35%" class="listline"><INPUT name="Example.description"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">创建时间:</TD>
                      <TD width="35%" class="listline"><INPUT name="Example.createtime"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">状态:</TD>
                      <TD width="35%" class="listline"><INPUT name="Example.statusid"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">创建者:</TD>
                      <TD width="35%" class="listline"><INPUT name="Example.authorid"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">使用模块:</TD>
                      <TD width="35%" class="listline"><INPUT name="Example.functionid"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">群组id:</TD>
                      <TD width="35%" class="listline"><INPUT name="Example.groupid"/></TD>
                    </TR>
                    <TR bgcolor="#CCCCCC">
                       <TD colspan="4" align="center">
                       <input  class="botton" type="submit" value="查询">&nbsp;
		       <input name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
                      </TD>
                    </TR>
                  </TBODY>
                </TABLE>
</s:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
