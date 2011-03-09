<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" id="MainHtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <title>${sysName}</title>
 <link rel="stylesheet" type="text/css" href="../style/css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../style/css/main.css" />
 <link rel="stylesheet" type="text/css" href="../style/css/error.css" />
 <script type="text/javascript">
function changeverify()
{ 
  document.getElementById("verifyimage").value="";
  document.getElementById("verifyimage").focus="";
  document.getElementById("verifyimage").src="<%=request.getContextPath()%>/verifyCode.jsp?_id="+Math.floor(Math.random()*100000);
}
</script>
</head>
<body >
	<div class="guildNav" id="guildNav">
		<div class="crumbs">
			<div class="inCrumbs">
				<div class="crumbsTit"><em>您所在的位置：</em><strong>系统登录</strong></div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
		<div class="main">
			<div id="login">
				<div class="form">
				  <form name="userLoginForm" id="userLoginForm" action="../userLogin.action" method="POST">
				  <s:hidden name="nextpage"></s:hidden>
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
						<img src="<%=request.getContextPath()%>/verifyCode.jsp" id="verifyimage" width="60" height="20"  class="imgCode" align="absmiddle"/>
						<a href="#" title="" onclick="changeverify();" class="changeImg">换一张</a></div>
					</div>
					<div class="l">
						<label class="n">&nbsp;</label>
						<div class="v"><button type="submit" class="btn"  tabindex="4">登录</button></div>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="../style/js/jquery.js"></script>
	<script type="text/javascript" src="../style/js/tablegrid.js"></script>
</body>
</html>