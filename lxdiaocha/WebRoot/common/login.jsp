<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-登录</title>
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
    }else{
      if(!isGoodChar(document.form1.loginname.value)){
        alert("请输入正确用户账号，为字母、数字、汉字！");
        document.form1.loginname.focus();
        return false;
      }
    }
  }
  if(isEmpty(document.form1.password.value)){
    alert("请输入用户密码，不能为空！");
    document.form1.password.focus();
    return false;
  }
  
  /*else{
    if(getStringLength(document.form1.password.value)>20 || getStringLength(document.form1.password.value)<4){
      alert("请输入正确用户密码，长度4-20字符！");
      document.form1.password.focus();
      return false;
    }else{
      if(!isLetterNumber(document.form1.password.value)){
        alert("请输入正确用户密码，为字母、数字！");
        document.form1.password.focus();
        return false;
      }
    }
  } */
  document.form1.submit();
  return true;
}
function submitForm(e){
	e=e||window.event
	if(event.keyCode==13){
  	   submitOnClick();
  }
}
</script>
</head>

<body "bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div align="center" style="background-color:#2679bf">
<table id="Table_01" width="1024" height="617" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<img src="../imagesa/login_01.gif" width="226" height="139" alt=""></td>
		<td>
			<img src="../imagesa/login_02.gif" width="568" height="139" alt=""></td>
		<td>
			<img src="../imagesa/login_03.gif" width="230" height="139" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../imagesa/login_04.gif" width="226" height="45" alt=""></td>
		<td>
			<img src="../imagesa/login_05.gif" width="568" height="45" alt=""></td>
		<td>
			<img src="../imagesa/login_06.gif" width="230" height="45" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../imagesa/login_07.gif" width="226" height="221" alt=""></td>
		<td valign="top" background="../imagesa/login_bg1.gif">
        <s:form name="form1" method="post" action="login">
        <table width="98%" height="150" border="0" align="center" cellpadding="0" cellspacing="1">
          <tr>
            <td height="24" colspan="5" class="baseFontBold">&nbsp;            </td>
          </tr>
          <tr>
            <td width="37%" class="tab_content1" align="right">帐号：</td>
            <td width="63%" colspan="2" class="tab_content1">
             <s:textfield name="loginname" size="25" maxlength="25" onkeydown="submitForm()"/>
            </td>
          </tr>
          <tr>
            <td class="tab_content" align="right">密码：</td>
            <td colspan="2" class="tab_content" align="left">
              <s:password name="password" size="25" maxlength="25" onkeydown="submitForm()"/>              
            </td>
          </tr>
          <tr>
            <td class="tab_content1" align="right">验证码：              </td>
            <td colspan="2" class="tab_content1"> 
            <s:textfield name="randnum" size="4" maxlength="4" onkeydown="submitForm()"/>
            <img src="../validateCode.pl">
            　 </td>
          </tr>
          <tr>
            <td class="tab_content" align="right"><s:checkbox name="savecookie"/>              </td>
            <td colspan="2" class="tab_content"> 
            记住帐号
            </td>
          </tr>
          <tr>
            <td height="62" class="tab_content1">            </td>
            <td height="80" colspan="2" class="tab_content1">　
           
              <input type="button" class="button" name="Submit" value=" 登 录 "  onClick="submitOnClick()">
                
              <input type="reset" class="button" name="Submit2" value=" 重 填 ">
            </td>
          </tr>
      </table>
      </s:form>
      </td>
		<td>
			<img src="../imagesa/login_09.gif" width="230" height="221" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../imagesa/login_10.gif" width="226" height="212" alt=""></td>
		<td><table width="100%" height="212" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="22"><img src="../imagesa/login_11_1.gif" width="568" height="22"></td>
          </tr>
          <tr>
            <td background="../imagesa/tab_bg.gif"><div align="center" class="timing_null"><%=com.changpeng.common.Constants.COMPANY_NAME%>&nbsp;&nbsp;版权所有</div></td>
          </tr>
          <tr>
            <td height="146"><img src="../imagesa/login_11_3.gif" width="568" height="146"></td>
          </tr>
        </table></td>
		<td>
			<img src="../imagesa/login_12.gif" width="230" height="212" alt=""></td>
	</tr>
</table>
</div>
</body>
</html>