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
<div class="navguild"><span><a href="../home/home.action">首页</a>->合作伙伴</span></div>
<div class="partenger clearfix">

		<ul id="bubble">
			<#list page.items as p>
			<li><a href="#"  title=${p.description}><img width="126" height="58" src="${p.logo}"></a></li>
			</#list>
		</ul>


<form name="pageForm" action="../home/partnerlist.action">
	<input type="hidden" name="pageNo" value="this.pageNo" />
</form>
</div>
<div style="padding:10px;text-align:center;">${pageString}</div>
</@home.home>
</#escape>
