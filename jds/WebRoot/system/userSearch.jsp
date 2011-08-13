<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 查询user</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-09-27</p>
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
		 <span class="title-blank-b">用户管理&gt;&gt;查询用户</span>
	      </td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="middle" valign="top" bgColor=#F9F9F7>
<s:form name="searchForm" action="userSearch.action" method="post">
                <TABLE cellSpacing=1 cellPadding=3 width="60%" align=center  border=0>
                  <TBODY>
                    <TR>
                      <TD width="15%" class="listheadline">用户姓名:</TD>
                      <TD width="35%" class="listline"><INPUT name="userExample.username"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">登录名:</TD>
                      <TD width="35%" class="listline"><INPUT name="userExample.loginname"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">部门:</TD>
                      <TD width="35%" class="listline"><INPUT name="departmentname"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">电话:</TD>
                      <TD width="35%" class="listline"><INPUT name="userExample.phone"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">手机:</TD>
                      <TD width="35%" class="listline"><INPUT name="userExample.mobile"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">分机号:</TD>
                      <TD width="35%" class="listline"><INPUT name="userExample.userno"/></TD>
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
