<#escape x as (x)!"">
<#list redianlist as redian>
	<a href="/news/viewnews.action?newsid=${redian.infoid}" target="_blank">${redian.infoname}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</#list>
</#escape>
