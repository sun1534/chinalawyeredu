<#macro home>
<#import "footer.ftl" as footer >

<#import "head.ftl" as head >
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
<link href="../css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../js/base/jquery.js"></script>
<script type="text/javascript" src="../js/base/jquery.form.js"></script>
<script type="text/javascript" src="../js/base/jquery.blockUI.js"></script>

<script type="text/javascript" src="../js/base/com.js"></script>
<script type="text/javascript" src="../js/login.js"></script>
<script type="text/javascript" src="../js/sys.js"></script>

<script type="text/javascript" src="../js/index.js"></script>
<title>欢迎来到深圳市天威广告有限公司网站</title>
</head>
<body>
<div id="main" class="doc">
	<div class="wrap">
		<@head.header ></@head.header>
		<div class="bd">
			<div class="in-bd">
				<!-- 主体 main-->
				<div class="main">
					<#nested>
				</div>
				<!-- other-->
				<div class="other"></div>
			</div>
		</div>
	</div>
	<@footer.footer></@footer.footer>
</div>
</body>
</html>
</#macro>
