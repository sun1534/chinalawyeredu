<#escape x as (x)!"">
<#list noticelist as notice>
	公告：<a href="/news/viewnews.action?newsid=${notice.infoid}" target="_blank">${notice.infoname}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</#list>
</#escape>
