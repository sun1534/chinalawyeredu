<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>${ webinfo.sysname}-重新登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">

<style type="text/css">
<!--
.STYLE1 {color: #FFFFFF}
-->
</style>
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" type="text/javascript">
function submitOnClick()
{
  if(isEmpty(document.form1.loginname.value)){
    alert("请输入用户账号，不能为空！");
    document.form1.loginname.focus();
    return false;
  }else{
    if(getStringLength(document.form1.loginname.value)>20){
      alert("请输入正确用户账号，长度1-20字符！");
      document.form1.loginname.focus();
      return false;
    }
    /*else{
      if(!isGoodChar(document.form1.loginname.value)){
        alert("请输入正确用户账号，为字母、数字、汉字！");
        document.form1.loginname.focus();
        return false;
      }
    }*/
  }
  if(isEmpty(document.form1.password.value)){
    alert("请输入用户密码，不能为空！");
    document.form1.password.focus();
    return false;
  }else{
    if(getStringLength(document.form1.password.value)>20 || getStringLength(document.form1.password.value)<4){
      alert("请输入正确用户密码，长度4-20字符！");
      document.form1.password.focus();
      return false;
    }
    //else{
    //  if(!isLetterNumber(document.form1.password.value)){
    //    alert("请输入正确用户密码，为字母、数字！");
    //    document.form1.password.focus();
    //    return false;
    //  }
    //}
  }
  document.form1.submit();
  return true;
}
function submitForm(){
	if(event.keyCode==13){
  	   submitOnClick();
  }
}
</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="font"><img src="images/b_02.gif" width="4" height="7"> 当前位置：${ webinfo.sysname} &gt;&gt; 重新登录  </td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
     <s:form name="form1" method="post" action="login">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="5" background="../imagesa/top-bg2.gif" class="baseFontBold"></td>
        </tr>
      <tr>
        <td width="37%" class="tab_content1"  align="right">帐号：</td>
        <td width="63%" colspan="2" class="tab_content1"><s:textfield name="loginname" size="25" maxlength="25" onkeydown="submitForm()"/> 
            <s:hidden name="pasivelogin" value="true"/></td>
          
        </tr>
      <tr>
        <td class="tab_content"  align="right">密码：</td>
        <td colspan="2" class="tab_content"><s:password name="password" size="25" maxlength="25" onkeydown="submitForm()"/> 
        </td>
        </tr>
        <!-- 
      <tr>
        <td class="tab_content1" align="right">
          验证码：
         </td>
        <td colspan="2" class="tab_content1"><s:textfield name="randnum" size="4" maxlength="5" onkeydown="submitForm()"/>
            <img src="../validateCode.pl">
        </td>
        </tr> -->
      <tr>
        <td class="tab_content"  align="center"></td>
        <td colspan="2" class="tab_content">　
           <input type="button" name="Submit" value=" 登 录 "  onClick="submitOnClick()">
                
              <input type="reset" name="Submit2" value=" 重 填 "></td>
        </tr>
    </table>
    </s:form>
    </td>
  </tr>
</table>
</body>
</html>