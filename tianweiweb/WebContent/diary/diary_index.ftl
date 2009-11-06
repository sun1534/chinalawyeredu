   <#escape x as (x)!"">
  	<div class="content-body" id="huantidiv">
		<#if diarylist?size == 0>
    	<div class="box-grey sp-t10">
		 <h4>暂无博客</h4>
			<div class="box-content">
					<p>您的好友暂时未发表博客！</p>
			</div>
		 </div>
		 <#else>
			<ul class="news">
				<@s.iterator value="diarylist" status="s">
					<li><a href="../diary/viewDiary.action?diaryid=${diaryid}&diarytypeid=${diarytype.diarytypeid}&viewUserid=${viewUserid}&type=type">${tile}&nbsp;&nbsp;${subtime?string("yyyy-MM-dd")}</a></li>
				</@s.iterator>
			</ul>
			</#if>
			<#if diarylist?size != 0>
			<div style="padding-left:450px;"><a href="../diary/diarylist.action?type=friend">更多</a></div>
			</#if>
	</div>
  </#escape>
