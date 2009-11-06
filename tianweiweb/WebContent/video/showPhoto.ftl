<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="photo/showPhoto_h.ftl" >
<script type="text/javascript">
	$(function(){
		EditPicName();
	})
</script>
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
					<li class="current"><a href="albumlist.action"><span>我的相册</span></a></li>
					<li><a href="../photo/friendphotolist.action"><span>好友和同学们的相册</span></a></li>
				</ul>
			</div>
		</div>
		<#if page.items.size()!=0>
		<#assign snsphoto = page.items.get(0) />
		</#if>
		<!-- 相册列表 -->
		<div class="albumslist" id="ml1">
			<div class="myalbums">
				<h4><strong>我的相册</strong> -  <a href="photolist.action?albumid=${album.albumid}" > ${album.albumName} </a></h4>
				<div  class="inmyalbums">
					<span class="myalbumsop">共${album.photoCount}张相片|
						<a href="javascript:setfront(${snsphoto.photoid},${albumid},${pageNo})">设置为封面</a>|
						<a onclick="getUploadPhoto(${albumid})"  class="uploadphotos" href="javascript:void(0)"  >上传相片</a>|
						<a href="albumlist.action">我的相册列表</a>|
						<!-- <a onclick="$.Jxq.ShowDialog('#editalbums',400,-120,-100)" href="javascript:void(0)">编辑相册名称</a>| -->
						<a  id="m1" href="javascript:void(0)" onclick="$.Jxq.delTips('#m1','#m1_',-60,20,200)" class="delalbums">删除这张照片</a>|
						<a href="#quickreply">评论</a></span>
						<span class="photopage"><a href="${previous}">上一张</a>|<a href="${next}">下一张</a></span>
						<a onclick="getCreateAlbum()" href="javascript:void(0)" class="createalbums">创建相册</a>
						<!-- 删除相册 -->
						<div style="display:none" class="webmenu" id="m1_">
							<div class="tooltips-del">
								<div class="intooltips-del">
									<span>确认删除该相片？</span>
									<input type="button" class="delBtn igreen" onclick="delthisphoto(${snsphoto.photoid},${albumid})" title="确定" value="确定"/>
									<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#m1_')" title="取消" value="取消"/>
								</div>
							</div>
						</div>
				</div>
			</div>

			<h5 id="edit_picname" class="photoname">相片名称：<span  ><span id="photoname1" >${snsphoto.photoName}</span>&nbsp;<a onclick="picedit_show()" href="javascript:void(0)">编辑名称</a></span>
			<input type="text" value="${snsphoto.photoName}" maxlength="20" class="normal w150" name="" id="input_editpic"/>&nbsp;<input class="delBtn igreen" type="button" id="sumbit_editpic" onclick="editphoto(${snsphoto.photoid})" value="确定" />&nbsp;<input class="delBtn gray" type="button" id="remove_editpic" value="取消" /></h5>
			<div class="list clearfix">
				<div class="picitem">

					<a href="${previous}" class="pager-pre" title="上一张">上一张</a><a href="${next}" class="pager-next" title="下一张">下一张</a>
					<img src="${resourcepath}${snsphoto.bigPic}"  alt="" />
				</div>

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
											<p class="Mymsg-time">${cp.createTime}<a id="mag_a_id${i}" class="ico a-delmsg" href="javascript:void(0)" onclick="showdelcomment(${i},${cp.id})">删除评论</a></p>

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
					<input id="pageNo" type="hidden"  value="1" />
					<!-- 分页 end -->
				</div>
				<!-- 发布评论 -->
				<div class="replaylistform" >

					<!-- 留言板 -->
					<a name="quickreply"></a>
					<h3 id="msgico">评论</h3>
						<div class="form Mymsg">
							<textarea id="commcontent" class="h80" style="width:97%" maxlen="100"></textarea>
						<p class="formop"><input type="button" value="发表" title="发表" class="delBtn igreen" onclick ="addcomment(${photoid})" id="addcommbutton"/>（最多100字）</p>
						</div>

				</div>
				<!-- 发布评论 end -->

			</div>

		</div>
	</div>
</div>
<!-- 删除留言 -->
<div style="display:none;" id="mag_del">
	<div class="tooltips-del">
		<div class="intooltips-del">
			<span>确认删除该评论吗？</span>
			<input type="button" id="delmsgid" rel="" class="delBtn igreen" onclick="delcomment();" title="确定" value="确定"/>
			<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#mag_del')" title="取消" value="取消"/>
		</div>
	</div>
</div>
<!-- 删除留言 end -->

 </@home.home>
</#escape>