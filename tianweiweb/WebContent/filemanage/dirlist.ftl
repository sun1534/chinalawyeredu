<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="filemanage/dirlist_h.ftl" >
 <div class="in-main">
	<div class="albumsbox">
		<div class="title-h"><h3>文件管理</h3></div>
		<div class="title-info">
			<!-- tag -->
			<div class="m-tag">
				<ul>
					<#if currentRole=1>
					<li><a href="../photo/albumlist.action"><span>照片</span></a></li>
					<li <#if dirtype=1>class="current"</#if>><a href="../filemanage/dirlist.action?dirtype=1"><span>视频</span></a></li>
					<li <#if dirtype=2>class="current"</#if>><a href="../filemanage/dirlist.action?dirtype=2"><span>音频</span></a></li>
					</#if>
					<#if currentRole=2>
					<li <#if dirtype=3>class="current"</#if>><a href="../filemanage/dirlist.action?dirtype=3"><span>文件</span></a></li>
					<li <#if dirtype=1>class="current"</#if>><a href="../filemanage/dirlist.action?dirtype=1"><span>视频</span></a></li>
					<li <#if dirtype=2>class="current"</#if>><a href="../filemanage/dirlist.action?dirtype=2"><span>音频</span></a></li>
					</#if>
				</ul>
			</div>
		</div>
		<!-- 目录列表 -->
		<div class="albumslist">
			<div class="albumsop">
				<a href="javascript:getCreateDir(${dirtype})" class="createalbums">创建目录</a>
				<!--<a href="javascript:getselectDir()" class="uploadphotos">上传文件</a>-->
			</div>
			<div class="list  tablist" >
				<table class="tableBox">
					<thead>
						<tr>
							<th  class="w200">目录名称</th>
							<th>目录说明</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<#list dirs as ap>
						<tr>
							<td><a href="filelist.action?id=${ap.id}"  class="albumsname">${ap.dirname}</a></td>
							<td><span>${ap.description}</span></td>
							<td class="tboperation"><a href="javascript:deldir(${ap.id})" class="tbDelete">删除</a></td>
						</tr>
						</#list>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</@home.home>
</#escape>