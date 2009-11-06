<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="photo/friendphoto_h.ftl" >

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
		<div class="albumsbox">
			<div class="title-h"><h3><a href="albumlist.action">相册</a></h3></div>
			<div class="title-info">
				<!-- tag -->
				<div class="m-tag">
					<ul>
						<li><a href="albumlist.action"><span>我的相册</span></a></li>
						<li class="current"><a href="friendphotolist.action"><span>好友和同学们的相册</span></a></li>
					</ul>
				</div>
			</div>
			<!-- 相册列表 -->
			<div class="albumslist">
				<div class="albumsop">
					<a onclick="getCreateAlbum()" href="javascript:void(0)" class="createalbums">创建相册</a> <a onclick="getselectAlbum()"  class="uploadphotos" href="javascript:void(0)"  >上传相片</a>
				</div>
				<div class="list clearfix" >
				<#list page.items  as ap>
				<#assign dest=userutil.getUserinfo(ap.userid) />
					   <dl>
					   <dt>
					      <#if ap.privateFlag=="0" ><a href="../photo/viewphotolist.action?albumid=${ap.albumid}&viewUserid=${ap.userid}"  class="albumsname">${ap.albumName}</a>
						  <#elseif ap.privateFlag=="1"><a href="../photo/viewphotolist.action?albumid=${ap.albumid}&viewUserid=${ap.userid}" class="albumsname">${ap.albumName}</a>
						  <#else><img src="${staticpath}/style/images/ico/key.gif"  align="absmiddle"  border="0" alt=""><a href="javascript:void(0)" onclick="alert('您没有权限查看这个相册！')" class="albumsname">${ap.albumName}</a></#if>
						  <p class="albuminfo"><a href="../photo/viewalbumlist.action?viewUserid=${ap.userid}">${dest.userName}</a>${maprole.get(dest.userRole)} 的相册 <br /></p>
						  <p class="albuminfo">共${ap.photoCount}张相片<br /><span class="intro">${ap.remark}</span></p></dt>
						<dd>
							 <#if ap.privateFlag=="0" ><a href="../photo/viewphotolist.action?albumid=${ap.albumid}&viewUserid=${ap.userid}" class="pic">
							 <#elseif ap.privateFlag=="1" ><a href="../photo/viewphotolist.action?albumid=${ap.albumid}&viewUserid=${ap.userid}" class="pic">
							 <#else><a href="javascript:void(0)" onclick="alert('您没有权限查看这个相册！')"  class="pic"></#if>
							 <#if ap.privateFlag=="0" ><img class="album_img" src="${resourcepath}${ap.albumUrl}"   alt="" />
							 <#elseif ap.privateFlag=="1"><img class="album_img" src="${resourcepath}${ap.albumUrl}"   alt="" />
							 <#else><img class="album_img" src="${staticpath}/style/images/pub/nopower.gif"   alt="" /></#if>
							 </a>
						</dd>
				   </dl>
				</#list>
				</div>
				<div class="albunspager">
					<div class="pager">
					<span class="page-total">共 ${page.count} 页</span>
					${pageString}
					</div>
				</div>
			</div>
		</div>
	</div>
<form name="pageForm" action="friendphotolist.action">
  	<input type="hidden" name="pageNo" value="" />
</form>



</@home.home>
</#escape>