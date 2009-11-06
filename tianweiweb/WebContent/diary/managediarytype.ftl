<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="diary/managediarytype_h.ftl" >
<div class="in-main">
	<div class="blogbox">
		<div class="title-h"><h3>博客</h3></div>
		<div class="title-info">
			<!-- tag -->
			<div class="m-tag">
				<ul>
					<li><a href="../diary/diarylist.action"><span>我的博客</span></a></li>
					<li><a href="../diary/diarylist.action?type=friend"><span>好友和同学们的博客</span></a></li>
				</ul>
				<a href="../diary/addDProiary!input.action" class="creatblog" title="写新博客">写新博客</a>
			</div>
		</div>
		<!-- 写blog -->
		<div class="writeblog">
			<h4>管理博客分类</h4>
			<div class="blog_line">
				<div class="in-writeblog" id="baseform">
				
				
				
				
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 编辑类型对话框层 -->
<div style="display:none" id="editblogcat" class="popbox">
	<input type="hidden" id="mgediarytypeid" value="" />
	<div class="pophead">
		<div class="in-pophead">
			<h4><b>编辑分类</b></h4>
			<span class="popCLose"><a href="javascript:void(0);$.Jxq.HideDialog('#editblogcat')" class="closeA bgreen" title="关闭">×</a></span>

		</div>
	</div>
	<div class="popmain">
		<div class="in-popmain clearfix">
			<div class="increatgroups">
				分类名称：<input type="text" id="mgediarytypename" name="diarytype" class="normal w150" maxlength="20"/>
			</div>

		</div>

	</div>
	<div class="popfoot">
		<div class="in-popfoot">
			<div class="addfriendBtn clearfix">
				<button onclick="editdiarytype($('#mgediarytypeid').attr('value'),$('#mgediarytypename').attr('value'))" class="delBtn igreen" title="确定">确定</button>
				<button onclick="$.Jxq.HideDialog('#editblogcat')" class="cancerBtn igray" type="button" title="取消">取消</button>
			</div>
		</div>

	</div>
</div>

<!-- 新增类型对话框层 -->
<div style="display:none" id="addblogcat" class="popbox">
	<div class="pophead">
		<div class="in-pophead">
			<h4><b>新增分类</b></h4>
			<span class="popCLose"><a href="javascript:void(0);$.Jxq.HideDialog('#addblogcat');$('.blockUI').hide();" class="closeA bgreen" title="关闭">×</a></span>

		</div>
	</div>
	<div class="popmain">
		<div class="in-popmain clearfix">
			<div class="increatgroups">
				分类名称：<input type="text" id="diarytypename" name="diarytype" class="normal w150" maxlength="20"/>
			</div>

		</div>

	</div>
	<div class="popfoot">
		<div class="in-popfoot">
			<div class="addfriendBtn clearfix">
				<button onclick="addgroup($('#diarytypename').attr('value'))" class="delBtn igreen" title="确定">确定</button>
				<button onclick="$.Jxq.HideDialog('#addblogcat');$('.blockUI').hide();" class="cancerBtn igray" type="button" title="取消">取消</button>
			</div>
		</div>

	</div>
</div>
 </@home.home>
 </#escape>