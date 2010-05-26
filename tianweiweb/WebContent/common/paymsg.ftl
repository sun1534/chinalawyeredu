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
<title>支付结果-系统提示</title>
<style type="text/css">
.box-green { border: 1px solid #6CB53D;padding:10px 10px 10px 25px;background: #E9FAE7; }
.box-green h4 { font-size: 12px; font-weight: bolder;color:#006600}
.box-green .box-content p {}
.box-green .box-content p {line-height:160%;font-weight:normal;color:#000000}
</style>
</head>
<body>
<div id="main" class="doc">
	<div class="wrap">
		<@head.header ></@head.header>
		<div class="bd">
			<div class="box-green">
				<h4>支付提示</h4>
				<div class="box-content">
					<p>${message}</p>
				</div>
			</div>
		</div>
	</div>
	<@footer.footer></@footer.footer>
</div>

</body>
</html>
</#macro>



