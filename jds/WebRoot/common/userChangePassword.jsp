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
            <td width="97%"><span class="sort-title">个人设置&gt;&gt;修改密码</span></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor="#F9F9F7">
<s:form name="form1" action="userChangePassword" validate="true" method="post">
<s:hidden name="flag" value="save"/>
              <br>
            <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1>
              <TBODY class="pt9-18">
                <TR>
                  <TD width="15%" align="center" class="listheadline">用户名:</TD>
                  <TD width="35%" class="listline" > <s:property  value="user.username"/> </TD>
                </TR>
                <TR>
                  <TD width="15%" class="listheadline"  ><div align="center">旧密码:</div></TD>
                  <TD width="35%" class="listline" > <s:password  name="oldpassword"  required="true"/> </TD>
                </TR>
                <TR>
                  <TD class="listheadline"  ><div align="center">新密码:</div></TD>
                  <TD class="listline" > <s:password  name="password1"  required="true"/> </TD>
                </TR>
                <TR>
                  <TD class="listheadline"  ><div align="center">重复新密码:</div></TD>
                  <TD class="listline" > <s:password  name="password2"  required="true"/> </TD>
                </TR>
                <TR >
                  <TD colspan="2" class="listline"  align="center">
                    <INPUT name="insert" type="submit" value="保存">
&nbsp;
        <input name="back2" type=button class="mask" onClick="javascript:history.back(-1)" value="返回"></TD>
                </TR>
              </TBODY>
            </TABLE>
</s:form>
            <br></TD>
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
