<#escape x as (x)!"">
<!-- 弹出层 模块 选择相册 开始 -->
<div class="popbox" id="selectalbum" style="display:block" >
		<div class="pophead">
			<div class="in-pophead">
				<h4><b>上传相片</b></h4>
				<span class="popCLose"><a title="关闭" class="closeA borange" href="albumlist.action">×</a></span>
			</div>
		</div>
		<div class="popmain">
			<div class="in-popmain clearfix">
					<div class="increatealbums">
			<#if albumlist.size() != 0 >
			请选择要上传的相册:
			<select id="uploadalbumid">
				<#list albumlist as al >
				<option value="${al.albumid}">${al.albumName}</option>
				</#list>
			</select>
			<#else>
			请先创建相册:
				<button title="" class="cancerBtn igray" onclick="removeSelectalbum();getCreateAlbum();" >创建相册</a>
			</#if>
				</div>
			</div>
		</div>
		<div class="popfoot">
			<div class="in-popfoot">
				<div  class="addfriendBtn clearfix">
				<#if albumlist.size() != 0 >
					<button title="确定" class="cancerBtn igray" onclick="getUploadPhoto($('#uploadalbumid').attr('value'))">确定</button>
				
				</#if>
					<button title="取消" class="cancerBtn igray" onclick="window.location.href='albumlist.action'">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 弹出层 模块 结束 -->
</#escape>