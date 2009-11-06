<#import "dynamicmessage.ftl" as d>
<#escape x as (x)!"">

        <#if frienddynamiclist?size == 0>
          <div class="box-grey sp-t10">
			 <h4>暂无动态</h4>
				<div class="box-content">
						<p>您的好友暂无动态！</p>
				</div>
			 </div>
       <#else>
	   <ul class="news">
       <#list frienddynamiclist as v>
        <@d.dynamic v />
	   </#list>
  </ul>
  </#if>
</#escape>