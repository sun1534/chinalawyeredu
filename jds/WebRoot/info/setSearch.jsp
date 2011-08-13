<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查询set</p>
 * <p>作者： 吴桂荣</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-08-28</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
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
		 <span class="title-blank-b">信息管理&gt;&gt;查询权限</span>
	      </td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="searchForm" action="setSearch.action" method="post">
                <TABLE cellSpacing=1 cellPadding=3 width="60%" align=center  border=0>
                  <TBODY>
                    <TR>
                      <TD width="15%" class="listheadline">设置id:</TD>
                      <TD width="35%" class="listline"><INPUT name="setExample.setid"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">公告类别:</TD>
                      <TD width="35%" class="listline"><INPUT name="setExample.typeid"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">用户id:</TD>
                      <TD width="35%" class="listline"><INPUT name="setExample.userid"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">用户名:</TD>
                      <TD width="35%" class="listline"><INPUT name="setExample.username"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">权限:</TD>
                      <TD width="35%" class="listline"><INPUT name="setExample.powerid"/></TD>
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
