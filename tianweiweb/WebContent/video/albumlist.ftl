<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="photo/albumlist_h.ftl" >
 <div class="in-main">
		<div class="albumsbox">
			<div class="title-h"><h3>文件管理</h3></div>
			<div class="title-info">
				<!-- tag -->
				<div class="m-tag">
					<ul>
						<li class="current"><a href="albumlist.action"><span>照片</span></a></li>
						<li><a href="friendphotolist.action"><span>视频</span></a></li>
						<li><a href="friendphotolist.action"><span>音频</span></a></li>
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
					<dl class="albumlist_dl">
						<dt><a href="photolist.action?albumid=${ap.albumid}&albumName=${ap.albumName}"  class="albumsname">${ap.albumName}</a><p class="albuminfo">共${ap.photoCount}张相片<br /><span class="intro">${ap.remark}</span></p></dt>
						<dd>
							<a href="photolist.action?albumid=${ap.albumid}&albumName=${ap.albumName}" class="pic"><img class="album_img" src="${resourcepath}${ap.albumUrl}"   alt="" /></a>
						</dd>
					</dl>
					</#list>
				</div>
				<div class="albunspager">
					<div class="pager">
					<span class="page-total">共 ${page.count} 页</span>
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
<form name="pageForm" action="albumlist.action">
  	<input type="hidden" name="pageNo" value="" />
</form>

</@home.home>
</#escape>