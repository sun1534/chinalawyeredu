<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="diary/diarylist_h.ftl" >
<#assign iclass="" />
<#if currentRole=1>
<#assign iclass="tab-green" />
</#if>
<#if currentRole=2>
<#assign iclass="tab-blue" />
</#if>
<#if currentRole=3>
<#assign iclass="tab-orange" />
</#if>



<!-- 2.0 html zrj 2009-6-4 -->
<div class="in-main">
	<!-- 访问他人主页 开始-->
	<#if viewUserid!=currentUserid>
	<div class="s-sub">
		<div class="in-s-sub">
			<ul>
				<li><a href="../home/home.action?viewUserid=${viewUserid}&viewUserrole=${viewUserrole}" class="current"><span>主页</span></a></li>
				<#if p_blogphoto>
				<li><a title="博客" class="normal" href="../diary/proFriendDiaryList.action?friendID=${viewUserid}"><span>博客</span></a></li>
				<li><a title="相册" class="normal" href="../photo/viewalbumlist.action?viewUserid=${viewUserid}&viewUserrole=${viewUserrole}"><span>相册</span></a></li>
				</#if>
				<li><a href="../friend/friendList.action?viewUserid=${viewUserid}"><span>好友</span></a></li>
				<li><a href="../classcenter/coreClassHome.action?viewUserid=${viewUserid}&viewUserrole=${viewUserrole}"><span>班级</span></a></li>
				<li><a title="投票" class="normal" href="../vote/myVoteView.action?userid=${viewUserid}"><span>投票</span></a></li>
				<li><a title="礼物" class="normal" href="../gift/receivePresent.action?userid=${viewUserid}"><span>礼物</span></a></li>
			</ul>
		</div>
	</div>
	</#if>
<!-- 访问他人主页 结束-->
	<div class="blogbox">
		<div class="title-h"><h3><a href="../diary/diarylist.action">博客</a></h3></div>
		<div class="title-info">
			<!-- tag -->

		</div>


		<div class="Tips"  id="delblogtips">
				<div class="InTips  Tips-green">
					<h4>系统提示</h4>
					<div class="TipsCon">
						<p>您访问的博客已删除！</p>
						<div class="TipsConbtn">

						</div>
					</div>
				</div>
			</div>




	</div>
</div>

</@home.home>
</#escape>