<span>

<a href="/userpage/index.action?userid=${user.id}">
首页
</a>
<a href="/userpage/infolist.action?userid=${user.id}">
<#if user.userRole=1>个人日记</#if><#if user.userRole=2>企业新闻</#if>
</a>
</span>
<span>
<a href="/userpage/photolist.action?userid=${user.id}">相册 </a>
</span>
${user.userName}
<br />


<h3>${diary.tile}</h3>
<div>${diary.content}</div>
<script>
function getPage(pageno){
	document.pageForm.pageNo.value=pageno;
	document.pageForm.submit();
}
</script>