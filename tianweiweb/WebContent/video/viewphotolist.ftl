<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="photo/viewphotolist_h.ftl" >
<#assign dest=userutil.getUserinfo(viewUserid) />
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
	<div class="albumsbox">
		<#if currentUserid=viewUserid>
		<div class="title-h"><h3><a href="albumlist.action">相册</a></h3></div>
		<div class="title-info">
			<!-- tag -->
			<div class="m-tag">
				<ul>
					<li ><a href="albumlist.action"><span>我的相册</span></a></li>
					<li class="current"><a href="../photo/friendphotolist.action"><span>好友和同学们的相册</span></a></li>
				</ul>
			</div>
		</div>
		</#if>
		<!-- 相册列表 -->
		<div class="albumslist" id="ml1">
			<div class="myalbums">
				<h4><strong>好友相册</strong> - <a href="../photo/viewalbumlist.action?viewUserid=${viewUserid}" >${username}${rolename}的相册 </a> - ${album.albumName}</h4>
				<div  class="inmyalbums">
					<span class="myalbumsop">共${album.photoCount}张相片</span>
				</div>

			</div>
			<div class="list clearfix">

				<#assign i =0 />
            	<#list page.items as pl >
            	<#assign i =i+1 />

				<dl id="pl${i}">
					<dt><p class="albuminfo"><span class="intro">${pl.photoName}</span></p></dt>
					<dd>
						<a href="viewshowphoto.action?albumid=${pl.albumid}&pageNo=${(pageNo-1)*pageSize+i}&viewUserid=${viewUserid}" class="pico"><img src="${resourcepath}${pl.smallPic}"  class="album_img" alt="" /></a>

						<!-- 删除此相片 -->

					</dd>
				</dl>

				</#list>

			</div>

		</div>
		<div class="photopager">
					<div class="pager">
					<span class="page-total">共 ${page.count} 页</span>
					${pageString}
					</div>
			</div>
	</div>
</div>
<form name="pageForm" action="viewphotolist.action">
  	<input type="hidden" name="pageNo" value="" />
  	<input type="hidden" name="albumid" value="${albumid}" />
  	<input type="hidden" name="viewUserid" value="${viewUserid}" />
</form>
 </@home.home>
</#escape>