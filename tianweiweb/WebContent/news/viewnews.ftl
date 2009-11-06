<#import "../common/homenoleft.ftl" as home>
<#escape x as (x)!"">
<@home.home>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script src="../js/base/jquery.js" language="javascript" type="text/javascript"></script>
<script src="../js/base/jquery.form.js" language="javascript" type="text/javascript"></script>
<script src="../js/base/jquery.blockUI.js" language="javascript" type="text/javascript"></script>
<script src="../js/home.js" language="javascript" type="text/javascript"></script>
<script src="../js/regist.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript" src="../js/login.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<div class="navguild"><span><a href="../home/home.action">首页</a>-><#if info.typeid=1>新闻</#if><#if info.typeid=2>活动</#if><#if info.typeid=100>公告</#if></span></div>

	<div id="" class="newlist">
		<h4>${info.infoname}</h4>
		<p class="newad">发布时间：${info.createtime}    发布人：天威广告</p>
		<div class="newscon">
			${info.content}
		</div>
	</div>
</@home.home>
</#escape>
