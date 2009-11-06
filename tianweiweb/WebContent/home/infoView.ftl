<#import "../common/homenoleft.ftl" as home>
<#escape x as (x)!"">
<@home.home>
<div>
<div align="center">
<h2>
${info.infoname}
</h2>
</div>
<hr/>
${info.content}
</div>
<div class="clear"></div>
</@home.home>
</#escape>