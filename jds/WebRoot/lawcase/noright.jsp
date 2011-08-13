<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<title></title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery-1.2.6.pack.js"></script> 
<script language="javascript">

</script>
</head>
<body>
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >

  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor="#F9F9F7">
              <TABLE align="center" border=0 cellSpacing=1 cellPadding=2 width="50%">
                <TBODY>
                  <TR class="listheadline">
                    <TD  align="center">系统消息</TD>
                  </TR>
                  <TR>
                    <TD height="40" class="listline"><div align="center">对不起,您没有权限进行这个操作</div></TD>
                  </TR>
                  <TR bgcolor="#CCCCCC">
                    <TD  align="center">  <input name="back" type=button class="mask" onClick="document.backForm.submit()" value="返回">
                    </TR>
                </TBODY>
              </TABLE>
              </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
<s:form name="backForm" action="javascript:history.go(-1)" method="POST">
<s:hidden name="pagenumber" value="1"/>
</s:form>
</BODY>
</HTML>
