<#escape x as (x)!"">

	
	<!-- 弹出层 模块 编辑相册 开始 -->
	<div class="popbox" id="editalbums" style="display:none">
		<div class="pophead">
			<div class="in-pophead">
				<h4><b>编辑相册</b></h4>
				<span class="popCLose"><a title="关闭" class="closeA borange" href="javascript:removeEditAlbum()">×</a></span>
			</div>
		</div>
		<div class="popmain">
			<div class="in-popmain clearfix">
					<div class="increatealbums">
						<dl>
							<input type="hidden" value="${album.albumid}" />
							<dt>相册名称：</dt>
							<dd><input type="text" class="normal w150" name="" id="albumName" value="${album.albumName}"/></dd>
							<dt>隐私设置：</dt>
							<dd><select name="" id="privateFlag">
									<option value="0" <#if album.privateFlag.equals("0") > selected="selected" </#if> class="normal">所有用户可见</option>
									<option value="1" <#if album.privateFlag.equals("1") > selected="selected" </#if> class="normal">好友及同学可见</option>
									<option value="2" <#if album.privateFlag.equals("2") > selected="selected" </#if> class="normal">私密相册</option>
								</select></dd>
							<dt>&nbsp;</dt>
							<dd><strong>默认向所有用户开放相册浏览</strong></dd>
							<dt>相册描述：</dt>
							<dd><textarea id="remark">${album.remark}</textarea></dd>
						</dl>
					</div>
			</div>
		</div>
		<div class="popfoot">
			<div class="in-popfoot">
				<div  class="addfriendBtn clearfix">
					<button title="确定" class="delBtn igreen" onclick="editalbum(${album.albumid},$('#albumName').attr('value'),$('#privateFlag').attr('value'),$('#remark').attr('value'))">确定</button>
					<button title="取消" class="cancerBtn igray" onclick="removeEditAlbum()">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 弹出层 模块 结束 -->
	</#escape>