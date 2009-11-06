 <#escape x as (x)!"">
<!-- 评论 -->
<div id="commentlist" class="replaylist">
	<div class="replaytotle">
		<span>${commentpage.totalCount}条评论 </span><a href="#quickreply">马上评论</a>
	</div>
	<!-- 留言列表 -->
	<div class="Mymsg-list" id="Mymsg-list">
		<div class="in-Mymsg-list">
			<ul>
			<#assign i=0 >
			<#list commentpage.items as cp >
            <#assign i=i+1 >
		    <#assign dest=userutil.getUserinfo(cp.userid) />
				<li id="mag_item_">
					<div class="Mymsg-entry">
						<p class="img"><a href="../home/home.action?viewUserid=${cp.userid}"><img src="${resourcepath}${dest.logo}" width="61" height="61" alt="" /></a><span class="name"><a href="../home/home.action?viewUserid=${cp.userid}">${dest.userName}</a></span></p>
						<div id="blog_mag" class="Mymsg-info">
							<div class="Mymsg-con">${cp.content}</div>
							<p class="Mymsg-time">${cp.createTime}
							<#if photoismine || cp.userid = currentUserid >
							<a id="mag_a_id${i}" class="ico a-delmsg" href="javascript:void(0)" onclick="showdelcomment(${i},${cp.id})">删除评论</a>
							</p>
							</#if>
						</div>
					</div>
				</li>
			</#list>
			</ul>
		</div>
	</div>
	<!-- 分页 -->
	<div class="formop">
		<div class="pager">
      	         <span class="page-total">共 ${commentpage.count} 页</span>
                  ${pageString}
         </div>
	</div>
	<input id="photoid" type="hidden" value="${photoid}" />
    <input id="pageNo" type="hidden" value="${pageNo}" />
	<!-- 分页 end -->
</div>
  </#escape>