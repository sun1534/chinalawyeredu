<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <title>${sysName }-重新登录</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
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

<body >
	<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>您所在是位置:</span><a>首页</a>＞<em>重新登录</em>
				</div>
			</div>
	</div>
	<div class="main">
	<s:form name="form1" action="login" method="post">
	  <s:hidden name="pasivelogin" value="true"/>
		<div class="inmain">
		 
			<div class="wrap">
				<!-- 操作模块 -->
				<div class="operate">
					<input type="submit" class="btnSubmit" title="登 录" value="登 录"/>
					<!-- <input type="button" class="btnCancel" title="返 回" value="返 回" onclick="history.go(-1)"/>
					 -->
				</div>
				<div class="operateTab">
					<!--<div class="operateTabInfo">密码错误！</div>-->
					
					<table id="editPwd" class="operateTabBox w350">
					
						<tbody>
							<tr class="fOdd">
								<td class="w80 fname">登录账号：</td>
								<td class="fvalue"><s:textfield name="loginname" maxlength="25" cssClass="txt w200"/></td>
							</tr>
							<tr class="fEven">
								<td class="fname">密    码：</td>
								<td><s:password name="password" maxlength="25" cssClass="txt w200"/></td>
							</tr>
							
						</tbody>
						
					</table>
				</div>
				
			</div>
	
		</div>
		</s:form>	
	</div>
	<!--  -->

</body>

</html>

