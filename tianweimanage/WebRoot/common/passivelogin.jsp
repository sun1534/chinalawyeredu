<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html>
<head>
<title>${sysName }-重新登录</title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {color: #FFFFFF}
-->
</style>
</head>
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
  if(isEmpty(document.form1.randnum.value)){
    alert("请输入验证码，不能为空！");
    document.form1.randnum.focus();
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
            <TD align="center" valign="top" bgColor="#F9F9F7">
      <form name="form1" method="post" action="../common/login.action" onsubmit="return submitOnClick();">
     <s:hidden name="pasivelogin" value="true"/>
              <table width="300" border="0" align="center" cellpadding="2" cellspacing="1" class="text_white">

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
                <td align="left" class="listline"><img src="../common/validateCode.action" height="20px"/></td>
                </tr>
                <tr>
                  <td colspan="3" align="center" class="listline">
                    <input name="x" type=submit class="mask" id="x" value="登录">                
                    </td>
                </tr>


              </table>            
               </form>
                <p>&nbsp;</p></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
