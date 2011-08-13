<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  response.setDateHeader("Expires",   0);   
  response.setHeader("Pragma","no-cache");   
  response.setHeader("Cache-Control","no-store");     
%>
<html>
<head>
<title></title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {color: #FFFFFF}
-->
</style>
</head>
<script language="JavaScript" type="text/JavaScript">
function loginsubmit()
{
   var errorMessages = "";
   var form = document.getElementById("userLoginForm");
     var errors = false;
     if (form.elements['loginname']) {
        field = form.elements['loginname'];
        var error = "请输入登录名!";
        if (field.value != null && (field.value == "" || field.value.replace(/^\s+|\s+$/g,"").length == 0)) {
           errors = true;
		   errorMessages=error;
        }
     }
    if(errors){
      alert(errorMessages);
      field.focus();
      return false;
     }

     if (form.elements['password']) {
        field = form.elements['password'];
        var error = "请输入密码!";
        if (field.value != null && (field.value == "" || field.value.replace(/^\s+|\s+$/g,"").length == 0)) {
           errors = true;
		   errorMessages=error;
        }
     }
    if(errors){
      alert(errorMessages);
      field.focus();
      return false;
     }
     if (form.elements['randnum']) {
        field = form.elements['randnum'];
        var error = "请输入验证码!";
        if (field.value != null && (field.value == "" || field.value.replace(/^\s+|\s+$/g,"").length == 0)) {
           errors = true;
		   errorMessages=error;
        }
     }
    if(errors){
      alert(errorMessages);
      field.focus();
      return false;
     }
     return true;
}
function reset()
{
document.userLoginForm.reset()
var form = document.getElementById("userLoginForm");
field = form.elements['loginname'];
field.focus();
return false;
}
</script>
<body>
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
      <div align="left">
        <table width="80%" border="0" cellspacing="0" cellpadding="4">
          <TR>
            <td width="60"><div align="center"><img src="../images/arr.gif" width="13" height="13"></div></TD>
            <td width="97%" class="sort-title">登录系统</TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="middle" valign="top" bgColor="#F9F9F7">
              <br>
              <table width="300" border="0" align="center" cellpadding="2" cellspacing="1" class="text_white">
<form name="userLoginForm" action="userLogin.action" method="post" onsubmit=return(loginsubmit())>
<s:hidden name="flag" value="toback"/>
<s:hidden name="backurl" value="${nextpage}"/>
                <tr>
                  <td colspan="3" class="listheadline">登录</td>
                  </tr>
                <tr>
                  <td  class="listheadline">帐　号：</td>
                  <td colspan="2"  class="listline"><input name="loginname" type="text" class="input1" size="18"></td>
                </tr>
                <tr>
                  <td class="listheadline">密　码：</td>
                  <td colspan="2" class="listline"><input name="password" type="password" class="input1" size="18"></td>
                </tr>
                <tr>
                  <td class="listheadline">验证码：</td>
                  <td class="listline"><input name="randnum" type="text" class="input1" size="5"></td>
                <td align="left" class="listline"><img src="../servlet/randomImgCodeServlet"height="18px"/></td>
                </tr>
                <tr>
                  <td colspan="3" align="center" class="listline">
                    <input name="x" type=submit class="mask" id="x" value="确定">
                    <input name="重置" type=reset class="mask" value="重填">                    </td>
                </tr>
 </form>
              </table>              <p>&nbsp;</p></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
