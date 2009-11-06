<!-- 弹出层 模块 创建目录 开始 -->			
<div class="popbox" id="createalbums" style="display:block">
	<div class="pophead">
		<div class="in-pophead">
			<h4><b>创建目录</b></h4>
			<span class="popCLose"><a title="关闭" class="closeA borange" onclick="removeCreateAlbum()" href="javascript:void(0)">×</a></span>
		</div>
	</div>
	<div class="popmain">
		<div class="in-popmain clearfix">
				<div class="increatealbums">
					<dl>
						<dt>名称：</dt>
						<dd><input type="text" maxlength="10" class="normal w150" name="" id="dirname"/></dd>
						<dt>描述：</dt>
						<dd><textarea maxlen="30" id="description"></textarea></dd>
						<input type="hidden" id="dirtype" value="${dirtype}" />
					</dl>
				</div>
		</div>
	</div>
	<div class="popfoot">
		<div class="in-popfoot">
			<div  class="addfriendBtn clearfix">
				<button title="确定" class="delBtn igreen" onclick="adddir()">确定</button>
				<button title="取消" class="cancerBtn igray" onclick="removeCreateAlbum()">取消</button>
			</div>
		</div>
	</div>
	</div>
<!-- 弹出层 模块 结束 -->