<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}-登录</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="generator" content="EditPlus" />
<meta name="author" content="zrj" />
<style type="text/css">
body{ font-family:"宋体"; font-size:12px;line-height:150%;background:url(css/images/dbg.jpg); color:#333;text-align:left; margin:0; padding:0;}

form,p,input{margin:0;padding:0;}
.loging_bg {width:330px;height:270px;padding:140px 0 0 580px;background:url(css/images/loginbg.jpg);}
.loging_bg form p{;width:220px;height:25px;float:left;margin-bottom:4px;text-align:right;}
.loging_bg form input{;width:150px;}
.loging_bg form p img{;margin-bottom:-6px;*margin-bottom:-4px;}
.loging_bg form input.yz{;width:85px;}
.loging_bg form input.btn{;width:85px;margin-right:70px;}

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
</head>

<body style="background:url(css/images/dbg.jpg);">
<div class="loging_bg">
	<s:form name="form1" method="post" action="login" onsubmit="return submitOnClick();">
		<p>用户名：<s:textfield name="loginname" maxlength="25" onkeydown="submitForm()"/></p>
		<p>密码：<s:password name="password" maxlength="25" onkeydown="submitForm()"/></p>
		<p>验证码：<s:textfield name="randnum" onkeydown="submitForm()" cssClass="yz"/>&nbsp;<img src="validateCode.action" height="20px"/></p>
		<p><input class="btn" type="submit"  value="登  陆"></p>
	</s:form>
</div>
</body>
</html>