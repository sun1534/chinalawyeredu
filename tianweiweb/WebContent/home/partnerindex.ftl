<#escape x as (x)!"">
<ul id="bubble">
	<#list partnerlist as p>
	<li><a href="#"  title=${p.description}><img width="126" height="58" src="${p.logo}"></a></li>
	</#list>
	<li><a href="../home/partnerlist.action"  title=${p.description}><img width="126" height="58" src="../images/more.jpg"></a></li>
</ul>

</#escape>