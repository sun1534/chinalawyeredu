<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="photo/photolist_h.ftl" >
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
		<div class="title-h"><h3><a href="albumlist.action">文件管理</a></h3></div>
		<div class="title-info">
			<!-- tag -->
			<div class="m-tag">
				<ul>
					<li class="current"><a href="albumlist.action"><span>相册</span></a></li>
					<li><a href="../photo/friendphotolist.action"><span>其他</span></a></li>
				</ul>
			</div>
		</div>
		<!-- 相册列表 -->
		<div class="albumslist" id="ml1">
			<div class="myalbums">
				<h4><strong>我的相册</strong> - ${album.albumName}</h4>
				<div  class="inmyalbums">
					<span class="myalbumsop">共${album.photoCount}张相片|<a onclick="getEditAlbum(${album.albumid})" href="javascript:void(0)">编辑相册</a>|<a onclick="getUploadPhoto(${album.albumid})"  class="uploadphotos" href="javascript:void(0)"  >上传相片</a>|<a  id="m1" href="javascript:void(0)" onclick="$.Jxq.delTips('#m1','#m1_',-60,20,330)" class="delalbums">删除相册</a></span>
					<a onclick="getCreateAlbum()" href="javascript:void(0)" class="createalbums">创建相册</a>
					<!-- 删除相册 -->
					<div style="display:none" class="webmenu" id="m1_">
						<div class="tooltips-del">
							<div class="intooltips-del">
								<span>确认删除该相册吗，如有相片也会被一起删除？</span>
								<input type="button" class="delBtn igreen" onclick="delalbum(${album.albumid})" title="确定" value="确定"/>
								<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#m1_')" title="取消" value="取消"/>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="list clearfix">

				<#assign i =0 />
            	<#list photopage.items as pl >
            	<#assign i =i+1 />

				<dl id="pl${i}">
					<dt><p class="albuminfo"><span class="intro">${pl.photoName}</span></p></dt>
					<dd>
						<a href="showphoto.action?albumid=${pl.albumid}&pageNo=${(pageNo-1)*pageSize+i}" class="pico"><img src="${resourcepath}${pl.smallPic}"  class="album_img" alt="" /></a>
						<span title="删除此相片" class="closeA borange delpic"  id="p${i}" href="javascript:void(0)" onclick="getDelPhoto(${pl.albumid},${pl.photoid},${i})">×</span>
						<!-- 删除此相片 -->

					</dd>
				</dl>

				</#list>
				<!--

				<dl id="pl2">
					<dt><p class="albuminfo"><span class="intro">梧桐山风景真美！</span></p></dt>
					<dd>
						<a href="des.html" class="pico"><img src="../style/images/p2.jpg" width="60" height="80" alt="" /></a>
						<span title="删除此相片" class="closeA borange delpic"  id="p2" href="javascript:void(0)" onclick="$.Jxq.delTips('#p2','#p2_',-60,5,150)">×</span>

						<div style="display:none" class="webmenu" id="p2_">
							<div class="tooltips-del">
								<div class="intooltips-del">
									<span>确认删除该相片？</span>
									<input type="button" class="delBtn igreen" onclick="$.Jxq.removeTips('#pl2','#p2_')" title="确定" value="确定"/>
									<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#p2_')" title="取消" value="取消"/>
								</div>
							</div>
						</div>
					</dd>
				</dl>

				-->
			</div>

		</div>
		<div class="photopager">
					<div class="pager">
					<span class="page-total">共 ${photopage.count} 页</span>
					${pageString}
					</div>
			</div>
	</div>
</div>
<form name="pageForm" action="photolist.action">
  	<input type="hidden" name="pageNo" value="" />
  	<input type="hidden" name="albumid" value="${albumid}" />
  	<input type="hidden" name="albumName" value="${albumName}" />
</form>
 </@home.home>
</#escape>