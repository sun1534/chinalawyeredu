<#escape x as (x)!"">
<!-- 管理博客分类 开始 -->
<div class="blogcategory">
	<div class="categoryedit" id="c01">
		<span class="categoryname">日记心情</span>
		<span class="categoryop">默认分类不能编辑</span>
	</div>
	<div class="categoryedit" id="c02">
		<span class="categoryname">学习交流</span>
		<span class="categoryop">默认分类不能编辑</span>
	</div>
	<div class="categoryedit" id="c03">
		<span class="categoryname">校园生活</span>
		<span class="categoryop">默认分类不能编辑</span>
	</div>
	<div class="categoryedit" id="c04">
		<span class="categoryname">休闲娱乐</span>
		<span class="categoryop">默认分类不能编辑</span>
	</div>
	<#assign i=0 />
	<#list DiaryTypeList as dt>
	<#assign i=i+1 />
	<div class="categoryedit" id="c${i}">
		<span class="categoryname">${dt.typename}</span>
		<span class="categoryop"><a title="编辑" href="javascript:void(0)" onclick="showeditdiarytype(${dt.id},'${dt.typename}')">编辑</a>　　|　　<a onclick="showdeltip(${i},${dt.id})" href="javascript:void(0)">删除</a></span>
	</div>
	</#list>
</div>

<!-- 管理博客分类 结束 -->
<!-- 删除分类 -->
<div style="display:none" class="webmenu" id="categoryedits">
	<div class="tooltips-del_">
		<div class="intooltips-del_">

			<span>确认删除分类吗？</span>
			<input type="button" class="delBtn igreen" onclick="deldiarytype()" title="确定" value="确定"/>
			<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#categoryedits')" title="取消" value="取消"/>
		</div>
	</div>
</div>
<a title="增加新分类" href="javascript:void(0)" onclick="$.Jxq.ShowDialog('#addblogcat',400,-120,-100)" class="ico a-add">增加新分类</a>
 </#escape>