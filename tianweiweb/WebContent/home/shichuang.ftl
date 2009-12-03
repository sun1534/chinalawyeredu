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
<title>欢迎来到深圳市天威广告有限公司网站</title>
</head>
<body>
<div class="vvideo">
	<div class="invvideo">
		<div class="vvideoh">
		<div class="ssTips2">
		<b>温馨提示:</b>如果鼠标无法操作，请用上下左右键控制焦点 ，用删除键进行返回,回车键进行选择！
	</div></div>
		<div class="vvideom" >
		<iframe width="640" height="526" src="../szsc/index.htm" frameborder="0" scrolling="no" id="scrolling"></iframe>
		</div>
		<div class="vvideof"></div>
	</div>
	<div class="vvideolist">
		<h3>今日更新</h3>
		<ul>
			<#list shichuangs as shichuang>
				<li><img src="../images/video_doc.gif" width="7" height="7" alt="" align="absmiddle" /><a  href="#" onclick="window.open('${shichuang.url}','newwindow', 'height=526,width=640, top=100, left=50, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')" >${shichuang.title}</a></li>
			</#list>
		</ul>
		<div class="vvideolistf"></div>
	</div>
	<div class="clear"></div>
	<div class="ssTips">
		<b>温馨提示:</b>如果鼠标无法操作，请用上下左右键控制焦点 ，用删除键进行返回,回车键进行选择！
	</div>
</div>
</body>
</html>