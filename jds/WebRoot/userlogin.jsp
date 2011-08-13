<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<title>嘉得信-综合信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-image: url(images/login_bg.jpg);
}
.style1 {color: #FFFFFF}
-->
</style></head>
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
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="170" valign="middle">&nbsp;</td>
  </tr>
  <tr>
    <td valign="top">
      <table width="100" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><img src="images/login_1.jpg" width="485" height="137" alt=""></td>
      </tr>
      <tr>

        <td background="images/login_2.jpg">
         <form name="userLoginForm" action="common/userLogin.action" method="POST">
          <table width="292" border="0" align="center" cellpadding="2" cellspacing="0" class="text_white">

         <tr>
            <td width="56"><span class="style1">帐　号：</span></td>
            <td width="144" colspan="2"><input name="loginname" type="text" class="input1" size="18"></td>
            <td width="9">&nbsp;</td>
            <td width="62"><span class="style1">忘记密码？</span></td>
          </tr>
          <tr>
            <td><span class="style1">密　码：</span></td>
            <td colspan="2"><input name="password" type="password" class="input1" value="" size="18"></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td><span class="style1">验证码：</span></td>
            <td valign="middle"><input name="randnum" type="text" class="input1" size="5"></td>
            <td align="left" valign="middle"><img src="randomImgCode.action" height="30" /></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="2">
			 <input type="image" src="images/login_sign.gif" onClick="return loginsubmit()">             <a href="#"><img src="images/login_rew.gif" width="54" height="21" border="0"  onClick="document.userLoginForm.reset()" alt=""></a>
			</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
      
          </table>
 </form>
        </td>

      </tr>
      <tr>
        <td><img src="images/login_3.jpg" width="485" height="16" alt=""></td>
      </tr>
      <tr>
        <td><img src="images/login_bottom.jpg" width="485" height="88" alt=""></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
