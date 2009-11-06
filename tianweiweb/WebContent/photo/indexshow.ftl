 <#escape x as (x)!"">
    <#if photolist.size()==0 >
	<div class="box-grey sp-t10">
	  <h4>没有找到照片</h4>
		<#if viewUserid=currentUserid>
		<div class="box-content">
			<p>您可以到这里<a href="../photo/albumlist.action">上传照片</a></p>
		</div>
	    </#if>
	    <#if viewUserid!=currentUserid>
	    <div class="box-content">
			<p>您的朋友暂时没有上传照片！</p>
		</div>
	     </#if>

	</div>
    <#else>
     <ul class="image-list-normal img-128 sp-t10" >
      <#list photolist as pl >
        <li><a href="../photo/viewphotolist.action?viewUserid=${viewUserid}&albumid=${pl.albumid}#..${pl.albumname}"><img src="${resourcepath}${pl.albumpic}" /></a></li>
      </#list>
      </ul>
    </#if>
  </#escape>