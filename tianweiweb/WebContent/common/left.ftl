<#macro left >
<#assign currentRole=currentRole />
<#if currentRole==0 >
<#elseif currentRole==1 >
<!-- 家庭侧边 -->
<div class="left-menu">
	<div class="inmenu">
	<h3>家庭专区</h3>
		<ul>
			<li><a href="../user/userbaseview.action" class="title">我的资料</a></li>
			<li><a href="../message/shortmessagelist.action?type=1" class="title">站内消息</a></li>
			<li><a href="../photo/albumlist.action" class="title">文件管理</a></li>
			<li><a href="../progress/productlist.action" class="title">产品订购</a></li>
			<li><a href="../progress/paylist.action" class="title">网上支付</a></li>
			<li><a href="../progress/progresslist.action" class="title">订购管理</a></li>
			<li><a href="../common/loginOut.action" class="title">退出登录</a></li>
		</ul>
	</div>
	<div class="clear"></div>
</div>
<#elseif currentRole==2 >
<!-- 商家侧边 -->
<div class="left-menu">
	<div class="inmenu">
		<h3>商家专区</h3>
		<ul>
			<li><a href="../user/userbaseview.action" class="title">商家资料</a></li>
			<!--<li><a href="../diary/diarylist.action" class="title">新闻发布</a></li>-->
			<li><a href="../message/shortmessagelist.action?type=1" class="title">站内消息</a></li>
			<li><a href="../filemanage/dirlist.action?dirtype=3" class="title">文件管理</a></li>
			<li><a href="../progress/productlist.action" class="title">产品订购</a></li>
			<li><a href="../progress/paylist.action" class="title">网上支付</a></li>
			<li><a href="../progress/progresslist.action" class="title">订购管理</a></li>
			<li><a href="../progress/publishlist.action" class="title">文件更新</a></li>
			<li><a href="../common/loginOut.action" class="title">退出登录</a></li>
		</ul>
	</div>
</div>
</#if>
</#macro>