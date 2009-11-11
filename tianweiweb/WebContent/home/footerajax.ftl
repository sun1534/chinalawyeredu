<#escape x as (x)!"">
		 友情链接：
		<#list noticelist as p>
	<a href="${p.paramvalue}" target="_blank">${p.paramname}</a> |
	</#list>
</#escape>