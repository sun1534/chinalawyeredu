<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="photo/viewalbumlist_h.ftl" >
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
						<li class="current" ><a href="friendphotolist.action"><span>好友和同学们的相册</span></a></li>
					</ul>
				</div>
			</div>
			</#if>
			<!-- 相册列表 -->
			<div class="albumslist">
				<div class="albumsop" style="text-align:left">
					<b>${username}${rolename}</b> 的相册
					<!--<a onclick="getCreateAlbum()" href="javascript:void(0)" class="createalbums">创建相册</a> <a onclick="getselectAlbum()"  class="uploadphotos" href="javascript:void(0)"  >上传相片</a>-->
				</div>
				<div class="list clearfix" >
				<#list albumPage.items  as ap>
					<dl class="albumlist_dl">
						<dt>
									<#if ap.privateFlag=="0" || viewUserid=currentUserid ><a href="viewphotolist.action?albumid=${ap.albumid}&albumName=${ap.albumName}&viewUserid=${viewUserid}"  class="albumsname">${ap.albumName}</a>
									 <#elseif ap.privateFlag=="1" && viewerisfriend ><a href="viewphotolist.action?albumid=${ap.albumid}&albumName=${ap.albumName}&viewUserid=${viewUserid}" class="albumsname">${ap.albumName}</a>
									 <#else><img src="../style/images/ico/key.gif"  align="absmiddle"  border="0" alt=""><a href="javascript:void(0)" onclick="alert('您没有权限查看这个相册！')" class="albumsname">${ap.albumName}</a></#if><p class="albuminfo">共${ap.photoCount}张相片<br /><span class="intro">${ap.remark}</span></p></dt>
						<dd>
									 <#if ap.privateFlag=="0" || viewUserid=currentUserid ><a href="viewphotolist.action?albumid=${ap.albumid}&albumName=${ap.albumName}&viewUserid=${viewUserid}" class="pic">
									 <#elseif ap.privateFlag=="1" && viewerisfriend ><a href="viewphotolist.action?albumid=${ap.albumid}&albumName=${ap.albumName}&viewUserid=${viewUserid}" class="pic">
									 <#else><a href="javascript:void(0)" onclick="alert('您没有权限查看这个相册！')"  class="pic"></#if>
									 <#if ap.privateFlag=="0"  || viewUserid=currentUserid ><img class="album_img" src="${resourcepath}${ap.albumUrl}"   alt="" />
									 <#elseif ap.privateFlag=="1" && viewerisfriend ><img class="album_img" src="${resourcepath}${ap.albumUrl}"   alt="" />
									 <#else><img class="album_img" src="${staticpath}/style/images/pub/nopower.gif"   alt="" /></#if>
									 </a>
						</dd>
					</dl>
					</#list>
				</div>
				<div class="albunspager">
					<div class="pager">
					<span class="page-total">共 ${albumPage.count} 页</span>
					${pageString}
					<!--	<span class="page-total">共3页</span>
						<span class="page-move">上一页</span>
						<span class="page-current" title="第1页">1</span>
						<a href="#" title="跳到第2页">2</a>
						<a href="#" title="跳到第3页">3</a>
						<a href="#" class="page-move" title="跳到第2页">下一页</a> -->
					</div>
				</div>
			</div>
		</div>
	</div>
<form name="pageForm" action="viewalbumlist.action">
  	<input type="hidden" name="pageNo" value="" />
  	<input type="hidden" name="viewUserid" value="${viewUserid}" />
</form>

</@home.home>
</#escape>