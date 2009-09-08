<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-修改密码</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">

<style type="text/css">
<!--
.STYLE1 {color: #FFFFFF}
-->
</style>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="font"><img src="../imagesa/b_02.gif" width="4" height="7"> 当前位置：<%=com.changpeng.common.Constants.SYS_NAME%> &gt;&gt; 修改密码  </td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    <s:form name="form1" action="passwdChange" method="post" validate="true">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="5" background="../imagesa/top-bg2.gif" class="baseFontBold"></td>
        </tr>
      <tr>
        <td width="37%" class="tab_content1" align="right">旧密码：</td>
        <td width="63%" colspan="2" class="tab_content1"> 　
          <s:password name="oldpass" size="25" maxlength="25"/>
        </td>
        </tr>
      <tr>
        <td class="tab_content" align="right">新密码：</td>
         <td width="63%" colspan="2" class="tab_content1"> 　
          <s:password name="newpass" size="25" maxlength="25"/>
         </td>
        </tr>
      <tr>
        <td class="tab_content1"  align="right">重新输入：</td>
        <td colspan="2" class="tab_content1">　
           <s:password name="passagain" size="25" maxlength="25"/>
        </td>
        </tr>
      <tr>
        <td class="tab_content">                    </td>
        <td colspan="2" class="tab_content">　
          
          <input type="submit" name="Submit" value=" 确 认 ">
          　
          <input type="reset" name="Submit2" value=" 重 置 ">
        </td>
      </tr>
    </table>
    </s:form>
    </td>
  </tr>
</table>
</body>
</html>