<#escape x as (x)!"">
<#list newslist as news>
<li><a href="/news/viewnews.action?newsid=${news.infoid}" target="_blank">${news.infoname}</a></li>
</#list>
</#escape>
