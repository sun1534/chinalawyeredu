<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="diary/diarylist_h.ftl" >
<!-- <script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.blockUI.js"></script> -->



<!-- 2.0 html zrj 2009-6-4 -->
<div class="in-main">
	<!-- 访问他人主页 开始-->
	<#if viewUserid!=currentUserid>
	<div class="s-sub">
		<div class="in-s-sub">
			<ul>
				<li><a href="../home/home.action?viewUserid=${viewUserid}&viewUserrole=${viewUserrole}" class="current"><span>主页</span></a></li>
				<#if p_blogphoto>
				<li><a title="博客" class="normal" href="../diary/proFriendDiaryList.action?viewUserid=${viewUserid}&friendID=${viewUserid}"><span>博客</span></a></li>
				<li><a title="相册" class="normal" href="../photo/viewalbumlist.action?viewUserid=${viewUserid}&viewUserrole=${viewUserrole}"><span>相册</span></a></li>
				</#if>
				<li><a href="../friend/friendList.action?viewUserid=${viewUserid}"><span>好友</span></a></li>
				<li><a href="../classcenter/coreClassHome.action?viewUserid=${viewUserid}&viewUserrole=${viewUserrole}"><span>班级</span></a></li>
				<li><a title="投票" class="normal" href="../vote/myVoteView.action?viewUserid=${viewUserid}&userid=${viewUserid}"><span>投票</span></a></li>
				<li><a title="礼物" class="normal" href="../gift/receivePresent.action?viewUserid=${viewUserid}&userid=${viewUserid}"><span>礼物</span></a></li>
			</ul>
		</div>
	</div>
	</#if>
<!-- 访问他人主页 结束-->
	<div class="blogbox">
		<div class="title-h"><h3><a href="../diary/diarylist.action">博客</a></h3></div>
		<!-- tag start -->
		<div class="title-info">
			<div class="m-tag">
				<ul>
					<li ><a ><span>${username}<#if roler==1>同学<#elseif roler==2>家长<#else>老师</#if>的博客</span></a></li>

				</ul>
				<!--<a href="../diary/addDProiary!input.action" class="creatblog" title="写新博客">写新博客</a>-->
			</div>
		</div>
		<!-- tag end -->
		<!-- bloglist start -->
		<div class="bloglist">
			<!-- list start  -->
			<#if frienDiarydList?size == 0>
			   <div class="Tips">
					<div class="InTips Tips-gray">
						<h4>系统提示</h4>
						<div class="TipsCon">
							<p>
								暂无博客
							</p>
						</div>
					</div>
				</div>
              </#if>
			 <@s.iterator value="frienDiarydList" status="s">
			<div class="list">
				<h4><a href="../diary/viewDiary.action?viewUserid=${userid}&diaryid=${diaryid}&diarytypeid=${diarytypeId}&type=friend"  >${tile}</a></h4>
				<div class="writeinfo"><span class="time">发表于：${createTime} | 分类：${typename}</span></div>
				<div class="intro">
					<p>
						${content}
					</p>
				</div>
				<div class="blog-op">
					<a href="../diary/viewDiary.action?viewUserid=${userid}&diaryid=${diaryid}&diarytypeid=${diarytypeId}&type=friend" " class="read">阅读（${clickCount}）</a><a href="../diary/viewDiary.action?viewUserid=${userid}&diaryid=${diaryid}&diarytypeid=${diarytypeId}&type=friend" " class="comments">评论（${replyCount}）</a> <a href="../diary/viewDiary.action?viewUserid=${userid}&diaryid=${diaryid}&diarytypeid=${diarytypeId}&type=friend" " class="readall">查看全文>></a>
				</div>
			</div>
			</@s.iterator>
			<!-- list end  -->


		</div>
		<!-- bloglist end -->
	</div>
</div>

<!-- 2.0 html end zrj 2009-6-4 -->

 </@home.home>
 </#escape>
