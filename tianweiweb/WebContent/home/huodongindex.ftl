<#escape x as (x)!"">

<#list huodonglist as huodong>
	<a href="/news/viewnews.action?newsid=${huodong.infoid}"  target="_blank">${huodong.infoname}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</#list>
  
</#escape>
