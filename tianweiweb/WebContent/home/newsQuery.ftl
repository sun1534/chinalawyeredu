<#import "../common/homenoleft.ftl" as home>
<#escape x as (x)!"">
<@home.home>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script src="../js/base/jquery.js" language="javascript" type="text/javascript"></script>
<script src="../js/base/jquery.form.js" language="javascript" type="text/javascript"></script>
<script src="../js/base/jquery.blockUI.js" language="javascript" type="text/javascript"></script>
<script src="../js/home.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript" src="../js/login.js"></script>
<div class="navguild"><span><a href="../home/home.action">首页</a>->最新活动</span></div>

<div class="newlist">
	<ul>
		<#list page.items as news>
		<li>·<a href="/news/viewnews.action?newsid=${news.infoid}" target="_blank">${news.infoname}</a><span class="">${news.createtime}<span></span></li>
  		</#list>
	</ul>

</div>
</@home.home>
</#escape>
