<#macro header>
<div id="top">
	<div id="topl">
		  <div id="logo"><img src="../images/logo.jpg" /></div>
		  <div id="login">
		  	<#if currentUserid=0>
			  <form id="loginform" action="../common/login.action" method="post">
				<dl class="nologin">
					<dt>用户名：</dt>
					<dd><input name="loginname" type="text" class="textfield"/></dd>
					<dt>密　码：</dt>
					<dd><input name="password" type="password" class="textfield"/></dd>
					<dt>验证码：</dt>
					<dd><input name="verifycode" type="text" class="textfield3" onkeydown="if(event.keyCode==13)stlogin()" size="4" />
			    <img src="../verify/VerifyCode.jsp" id="imgObj" align="top" onclick="changeImg()" /></dd>
					<dt><input type="reset" value="注 册" onclick="document.location='../regist/regist!input.action'" class="btn3"/></dt>
					<dd><input type="button" onclick="stlogin()" value="登 陆" class="btn"/><a href="../regist/findpwd.html" class="findpws">忘记密码</a></dd>
				</dl>
		      </form>
		    </#if>
		    <#if currentUserid!=0>
				<dl class="loginin">
					<dt><b>${currentUsername}</b>您好！</dt>
					<dd>欢迎来到天威广告管理系统！<br />
					<a href="../user/userbaseview.action">我的天威</a>
		    	<a href="../common/loginOut.action">退出登录</a></dd>
				</dl>
		    </#if>
		  </div>
			<span class="menu"><a href="../home/home.action"  id="Home"><span>首页</span></a></span>
	</div>
	<div id="topr">
		<div class="toptext">

		<!--<iframe src="http://www.7stk.com/1/6/sina.htm" frameborder="0" width="150" height="17" marginheight="0" marginwidth="0" scrolling="no"></iframe>-->
		<div id="" class="weather">
			<iframe src="http://www.tianqi123.com/small_page/chengshi_371.html?c0=red&c1=D96C00&bg=FFD476&w=280&h=13&text=yes" width=280 height=13 marginwidth=0 marginheight=0 hspace=0 vspace=0 frameborder=0 scrolling=no align=center id=url style="height:13px;line-height:13px;float:left;"></iframe>
			<!--<a href="#">深圳
			<input name="Input" type="image" disabled="disabled" src="../images/rainy.gif" align="bottom" />
			阵雨 气温27-32度 2009年7月28日</a>
			-->
			<a href="#" onClick="this.style.behavior='url(#default#homepage)';this.setHomePage(document.location.href);event.returnValue=false;"><img src="../images/home.gif" align="absmiddle" />设为首页</a>
			<a href="#" onclick="javascript:window.external.addFavorite(document.location.href,'天威广告')"><img src="../images/add.gif"  align="absmiddle" />加入收藏</a>
		</div>

		</div>
		<div class="clear"></div>
		<div id="flash">
		<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="680" height="170">
          <param name="movie" value="../images/top.swf" />
          <param name="quality" value="high" />
          <param name="wmode" value="transparent" />
          <embed src="../images/top.swf" quality="high" wmode="transparent" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="680" height="170"></embed>
		  </object>
		</div>
		<div class="clear"></div>
		<div class="topmenu">
			<a href="../home/about.action?type=adagent" id="topmenu_1"><span>广告代理</span></a>
			<a href="../home/about.action?type=videoshop" id="topmenu_2"><span>影视工作室</span></a>
			<a href="../home/productlist.action" id="topmenu_3"><span>产品服务</span></a>
			<a href="../home/about.action?type=cooper" id="topmenu_4"><span>合作加盟</span></a>
			<a href="../home/about.action?type=onlinehelp" id="topmenu_5"><span>在线帮助</span></a>
		</div>
	</div>
</div>
<div id="bannal">
	<div id="scroll">
		<marquee scrollamount="5" onMouseOver="stop()" onMouseOut="start()" id="noticediv">
		</marquee>
	</div>

	<form action="../home/infoQuery.action" method="post">
	<div class="search"><span class="fleft">搜索</span>
	<select name="typeid" class="fleft">
	  <option value="2">搜产品</option>
	  <option value="5">搜活动</option>
	</select>
	<input type="text" id="keyword" class="input_txt fleft" name="keyword" value="请输入关键字" onfocus="clicksearch();" size="120"/><input type="submit" class="btn2 fleft" value="GO!" /><!--<a href="#" class="fleft searcha">高级搜索</a>-->
	</div>
	</form>
</div>
<script>
function clicksearch(){
	if($("#keyword").val()=="请输入关键字"){
		$("#keyword").attr("value","");
	}
}
</script>
</#macro>