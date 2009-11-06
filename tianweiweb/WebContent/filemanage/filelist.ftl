<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="filemanage/filelist_h.ftl" >
<div class="in-main">
	<div class="albumsbox">
		<div class="title-h"><h3>文件管理</h3></div>
		<div class="title-info">
			<!-- tag -->
			<div class="m-tag">
				<ul>
					<#if currentRole=1>
					<li><a href="../photo/albumlist.action"><span>照片</span></a></li>
					<li <#if dir.dirtype=1>class="current"</#if>><a href="../filemanage/dirlist.action?dirtype=1"><span>视频</span></a></li>
					<li <#if dir.dirtype=2>class="current"</#if>><a href="../filemanage/dirlist.action?dirtype=2"><span>音频</span></a></li>
					</#if>
					<#if currentRole=2>
					<li <#if dir.dirtype=3>class="current"</#if>><a href="../filemanage/dirlist.action?dirtype=3"><span>文件</span></a></li>
					<li <#if dir.dirtype=1>class="current"</#if>><a href="../filemanage/dirlist.action?dirtype=1"><span>视频</span></a></li>
					<li <#if dir.dirtype=2>class="current"</#if>><a href="../filemanage/dirlist.action?dirtype=2"><span>音频</span></a></li>
					</#if>
				</ul>
			</div>
		</div>
		<!-- 相册列表 -->
		<div class="albumslist" id="ml1">
			<div class="myalbums">
				<h4><strong>我的文件</strong> - ${dir.dirname}</h4>
				<div  class="inmyalbums">
					<span class="myalbumsop"><a onclick="getEditDir(${dir.id})" href="javascript:void(0)">编辑目录</a>|
					<a onclick="getUploadFile(${dir.id})"  class="uploadphotos" href="javascript:void(0)">上传文件</a>|
					<a  id="m1" href="javascript:void(0)" onclick="javascript:deldir(${dir.id})" class="delalbums">删除目录</a></span>
					<!-- 删除目录 -->
					<div style="display:none" class="webmenu" id="m1_">
						<div class="tooltips-del">
							<div class="intooltips-del">
								<span>确认删除该目录吗，如有文件也会被一起删除？</span>
								<input type="button" class="delBtn igreen" onclick="deldir(${dir.id})" title="确定" value="确定"/>
								<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#m1_')" title="取消" value="取消"/>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="list  tablist" >
				<table class="tableBox">
					<thead>
						<tr>
							<th  class="w200">文件名称</th>
							<th>上传时间</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<#list page.items as ap>
						<tr>
							<td>${ap.fileName}</td>
							<td><span>${ap.createTime}</span></td>
							<td class="tboperation"><a href="${ap.url}" class="tbdown">下载</a></td>
							<td class="tboperation"><a href="javascript:delfile(${ap.id})" class="tbDelete">删除</a></td>
						</tr>
						</#list>
					</tbody>
				</table>
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
<form name="pageForm" action="photolist.action">
  	<input type="hidden" name="pageNo" value="" />
  	<input type="hidden" name="id" value="${id}" />
</form>
 </@home.home>
</#escape>