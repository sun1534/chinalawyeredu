<#escape x as (x)!"">
<!-- 弹出层 模块 上传相片 开始 -->
<div class="popbox" id="uploadpic" style="display:block" >
		<div class="pophead">
			<div class="in-pophead">
				<h4><b>上传文件</b></h4>
				<span class="popCLose"><a title="关闭" class="closeA borange" onclick="history.go(0)">×</a></span>
			</div>
		</div>
		<div class="popmain">
			<div class="in-popmain clearfix">
				<div class="increatealbums">
					<link href="../css/_style/swfupload.css" rel="stylesheet" type="text/css" />
					上传到：${dir.dirname}

					<table width="100%" cellspacing="4" cellpadding="4" border="0" bgcolor="#FCFCFC">
						<tr>
						<td class="DH1">
							<table width="100%" cellspacing="4" cellpadding="4" border="0" bgcolor="#FCFCFC">
							<tr>
							<td class="DH2">
							<STRONG>批量上传文件</STRONG>
							</td><td class="DH2" align="right"></td>
							</tr>
							</table>
					<div class="relationinfodes ">
						<div id="content" class="list">
							<form id="form1" action="" method="post" enctype="multipart/form-data">

									<input type="hidden" id="jxq_" value="${jxq_}" />
								<table id="idFileList" width="100%" >
									<thead>
									<tr>
										<th width=40%><B>文件名</B></th>
										<th width=20%><B>文件大小</B></th>
										<th width=30%><B>状态</B></th>
										<th width=10%>&nbsp;</th>
									</tr>
									</thead>
									</table>
									等待上传 <span id="idFileListCount">0</span> 个 ，成功上传 <span id="idFileListSuccessUploadCount">0</span> 个
									<div id="divSWFUploadUI" style="visibility: hidden;"></div>

									<noscript style="display: block; margin: 10px 25px; padding: 10px 15px;">
										很抱歉，相片上传界面无法载入，请将浏览器设置成支持JavaScript。
									</noscript>
									<div id="divLoadingContent" class="content" style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
										相片上传界面正在载入，请稍后...
									</div>
									<div id="divLongLoading" class="content" style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
										相片上传界面载入失败，请确保浏览器已经开启对JavaScript的支持，并且已经安装可以工作的Flash插件版本。
									</div>
									<div id="divAlternateContent" class="content" style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
										很抱歉，相片上传界面无法载入，请安装或者升级您的Flash插件。
										请访问： <a href="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" target="_blank">Adobe网站</a> 获取最新的Flash插件。
									</div>
							</form>
						</div>
					</div>
					</td>
					</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="popfoot">
			<div class="in-popfoot">
			<table width="100%" >
				<tr>
					<td align="center" width="15%">
						<span id="spanButtonPlaceholder"></span>
					</td>
					<td align="left">
						<input id="btnUpload" type="button" value="上传文件" class="delBtn igreen" />
						<input id="btnCancel" type="button" value="取消上传" disabled="disabled" class="cancerBtn igray" />
					</td>
					<td align="right">
					<div  class="addfriendBtn clearfix">
					<button title="取消" class="cancerBtn igray" onclick="location.reload(true)">关闭</button>
				</div>
					</td>
				</tr>
			</table>
			</div>
		</div>
	</div>
	<!-- 弹出层 模块 结束 -->
</#escape>