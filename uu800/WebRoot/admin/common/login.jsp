<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="" />
 <meta name="Copyright" content="" />
 <meta name="keywords" content="" />
 <title>${sysName}-登录</title>
 <link rel="stylesheet" type="text/css" href="../style/css/login.css"/>
 <script type="text/javascript">
function loginsubmit()
{
   var errorMessages = "";
   var form = document.getElementById("userLoginForm");
     var errors = false;
     if (form.elements['username']) {
        field = form.elements['username'];
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

function changeverify()
{ 
  document.getElementById("verifyimage").value="";
  document.getElementById("verifyimage").focus="";
  document.getElementById("verifyimage").src="../../verifyCode.jsp?_id="+Math.floor(Math.random()*100000);
}
</script>
 
</head>
 
<body >
	<div class="doc">
		<div id="hd">
			<div class="mod620">
				<div id="help">
					<a href="#" title="">帮助中心</a>
				</div>
				<h1 id="logo"><a title="中山·${sysName}" href="javascript:;"><span>中山·${sysName}</span></a></h1>
				<h2>广东省中山市<span>${sysName}</span></h2>
			</div>
		</div>
		<div id="bd">
			<div id="login">
				<div class="form">
				  <form name="userLoginForm" id="userLoginForm" onsubmit="return loginsubmit();" action="../common/userLogin.action" method="POST">
					<div class="l">
						<label class="n">用户名：</label>
						<div class="v"><input type="text" name="username" id="username" value="admin" class="txt" tabindex="1" /></div>
					</div>
					<div class="l">
						<label class="n">密　码：</label>
						<div class="v"><input type="password" name="password" id="password" value="qweqwe" class="txt" tabindex="2"/></div>
					</div>
					<div class="l">
						<label class="n">验证码：</label>
						<div class="v"><input type="text" name="randnum"  id="randnum" value="" class="chkCode" tabindex="3" maxlength="4"/>
						<img src="../../verifyCode.jsp" id="verifyimage" width="60" height="20"  class="imgCode" align="absmiddle"/>
						<a href="#" title="" onclick="changeverify();" class="changeImg">换一张</a></div>
					</div>
					<div class="l">
						<label class="n">&nbsp;</label>
						<div class="v"><button type="submit" class="btn"  tabindex="4">登录</button></div>
					</div>
					</form>
				</div>
			</div>
			<div id="copyright">
				<p>Copyright &copy; 2010　　深圳市深讯信息科技发展股份有限公司 </p>
			</div>
		</div>
	</div>
</body>
 
</html>
 

