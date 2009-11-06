<#escape x as (x)!"">
<div class="popbox" id="editalbums" style="display:block">
	<div class="pophead">
		<div class="in-pophead">
			<h4><b>编辑目录</b></h4>
			<span class="popCLose"><a title="关闭" class="closeA borange" href="javascript:removeEditAlbum()">×</a></span>
		</div>
	</div>
	<div class="popmain">
		<div class="in-popmain clearfix">
				<div class="increatealbums">
					<dl>
						<input type="hidden" value="${dir.id}" />
						<dt>目录名称：</dt>
						<dd><input type="text" class="normal w150" name="" id="dirname" value="${dir.dirname}"/></dd>
						<dt>目录描述：</dt>
						<dd><textarea id="description">${dir.description}</textarea></dd>
					</dl>
				</div>
		</div>
	</div>
	<div class="popfoot">
		<div class="in-popfoot">
			<div  class="addfriendBtn clearfix">
				<button title="确定" class="delBtn igreen" onclick="editalbum(${dir.id},$('#dirname').attr('value'),$('#description').attr('value'))">确定</button>
				<button title="取消" class="cancerBtn igray" onclick="removeEditAlbum()">取消</button>
			</div>
		</div>
	</div>
</div>
</#escape>