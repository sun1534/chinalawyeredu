<#escape x as (x)!"">
<#list entlist as ent>
<li class="logo"><a href="/userpage/index.action?userid=${ent.id}"  target="_blank"><img src="${ent.logo}" /><br />${ent.userName}</a></li>
</#list>
</#escape>
