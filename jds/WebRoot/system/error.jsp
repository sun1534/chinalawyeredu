<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<title></title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
</head>
<body>
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
      <div align="left">
        <table width="80%" border="0" cellspacing="0" cellpadding="4">
          <TR>
            <td width="60"><div align="center"><img src="../images/arr.gif" width="13" height="13"></div></TD>
            <td width="97%"><span class="sort-title">系统管理&gt;&gt;系统消息</span></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="middle" valign="top" bgColor="#F9F9F7">
              <TABLE align="center" border=0 cellSpacing=1 cellPadding=2 width="50%">
                <TBODY>
                  <TR class="listheadline">
                    <TD  align="center">系统消息</TD>
                  </TR>
                  <TR>
                    <TD height="40" class="listline"><div align="center">${message}</div></TD>
                  </TR>
                  <TR bgcolor="#CCCCCC">
                    <TD  align="center">  <input name="back" type=button class="mask" onClick="javascript:history.back(-1)" value="返回">
                  </TR>
                </TBODY>
              </TABLE>
              </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
<s:form name="backForm" action="${nextpage}" method="POST">
<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
</BODY>
</HTML>
