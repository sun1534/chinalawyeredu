<#macro home myheader>
<#import "footer.ftl" as footer >
<#if myheader!="">
	<#import "../${myheader}" as mheader >
</#if>

<#assign currentRole=currentRole />
<#import "left.ftl" as left >
<#import "head.ftl" as head >
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
<link rel="stylesheet" type="text/css" href="../css/default/reset.css" />
<link rel="stylesheet" type="text/css" href="../css/default/layout.css" />
<link rel="stylesheet" type="text/css" href="../css/default/myhome.css" />
<link rel="stylesheet" type="text/css" href="../css/default/form.css" />
<link rel="stylesheet" type="text/css" href="../css/default/popbox.css" />
<link rel="stylesheet" type="text/css" href="../css/default/temp.css" />
<link rel="stylesheet" type="text/css" href="../css/default/tips.css" />
<link rel="stylesheet" type="text/css" href="../css/default/jxq.css" />
<link rel="stylesheet" type="text/css" href="../css/default/msg.css" />
<link rel="stylesheet" type="text/css" href="../css/default/top.css" />
<link rel="stylesheet" type="text/css" href="../css/default/pager.css" />


<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/left-menu.css" rel="stylesheet" type="text/css"  />

<script type="text/javascript" src="../js/base/jquery.js"></script>
<script type="text/javascript" src="../js/base/jquery.form.js"></script>
<script type="text/javascript" src="../js/base/jquery.blockUI.js"></script>
<script type="text/javascript" src="../js/base/com.js"></script>
<script type="text/javascript" src="../js/sys.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<@mheader.head ></@mheader.head>
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
				<!-- 侧边 sidebar -->
				<div class="sidebar">
					<@left.left>
					</@left.left>
				</div>
				<!-- other-->
				<div class="other"></div>
			</div>
		</div>
	</div>
	<@footer.footer></@footer.footer>
</div>
<div class="overshadow" id = "os" style="display:none">
  <iframe width="100%" height="100%" marginheight="0" frameborder="0" marginwidth="0" scrolling="no"></iframe>
</div>
</body>
</html>
</#macro>
