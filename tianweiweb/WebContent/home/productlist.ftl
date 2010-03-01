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
<div class="navguild"><span><a href="../home/home.action">首页</a>->产品介绍</span></div>
<div class="prolist clearfix">
<#list page.items as t>
<dl>
	<dt><img  width="330" height="270" src="${t.pic}"  /></dt>
	<dd>
		<p><b>产品介绍</b></p>
		<p>${t.name}</p>
		<p>简介：${t.description}</p>
		<p>原价：${t.price} ${t.unit} 优惠价：<#assign yp=t.price*t.feerate/100 /> ${yp} ${t.unit}</p>
		<p><a href="javascript:dinggou(${t.id})">订购</a> | <a href="#">详细介绍</a> | <a href="#">案例</a></p>
	</dd>
</dl>
</#list>
${pageString}
</div>
<script>
function dinggou(id){
	$.ajax({
	    type: "POST",
	    data:"productid="+id,
	    url:"/progress/orderproduct.action",
	    success:function(data){
	    	$.blockUI({message:data});
	    }});
}
</script>
</@home.home>
</#escape>
