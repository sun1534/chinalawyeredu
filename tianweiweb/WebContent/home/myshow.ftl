<#escape x as (x)!"">
<ul>
<#list userlist as user>
<li class="face">
<a href="/userpage/index.action?userid=${user.id}"  target="_blank">
<img src="../${user.pic}" />
<br />${user.userName}(<#if user.gender=1>男</#if><#if user.gender=2>女</#if>)<br />
</a>
</li>
</#list>
</ul>
</#escape>
