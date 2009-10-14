<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-登录</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/login.css" />
 <script type="text/javascript">
function submitOnClick()
{
  var _loginname=document.getElementById("loginname").value;
  var _password=document.getElementById("password").value;
  if(_loginname==""||_loginname.length==0){
    alert("请输入您的登录账号，不能为空！");
    document.form1.loginname.focus();
    return false;
  }
  if(_password==""||_password.length==0){
    alert("请输入您的密码，不能为空！");
    document.form1.password.focus();
    return false;
  }
  document.form1.submit();
  return true;
}
</script>
</head>

<body>
<div class="loginbg">
	<div class="login">
    	<div class="login_left">
        </div>
    	<div class="login_right">
                <div class="login_xm">
                </div>
			   <div class="form">
			   <s:form name="form1" method="post" action="login" onsubmit="return submitOnClick();">
						<p><label>帐号：</label>
						   <s:textfield name="loginname" maxlength="25" cssClass="logintxt" id="loginname"/>
								</p>
						<p><label>密码：</label>
							<s:password name="password" maxlength="25"  cssClass="logintxt" id="password"/>
						</p>
						<p class="p_login"></p>
						<p class="p_button"><input name="" type="image" src="../images/loginbtn.jpg"/></p>
			</s:form>
			 </div>
        </div>
        <div class="clear"></div>

    </div>
	<div class="Powered">版权所有 中国移动通信集团重庆有限公司</div>
</div>
</body>
</html>
