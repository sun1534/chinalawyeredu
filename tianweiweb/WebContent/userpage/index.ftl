<span>
<a href="/userpage/index.action?userid=${user.id}">
首页
</a>
<a href="/userpage/infolist.action?userid=${user.id}">
<#if user.userRole=1>个人日记</#if><#if user.userRole=2>企业新闻</#if>
</a>
</span>
<span>
<a href="/userpage/phostlist.action?userid=${user.id}">相册 </a>
</span>
${user.userName}
<br />
<#list infos as info>
<a href="/userpage/infoview.action?diaryid=${info.diaryid}&userid=${user.id}">${info.tile}</a>
<br />
</#list>
<#list photos as photo>
<img src="/${photo.smallPic}" />
</#list>
