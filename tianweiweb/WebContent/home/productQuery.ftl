<#escape x as (x)!"">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>欢迎来到天威广告业务管理站点</title>
<link href="/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/base/jquery.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
</head>
<body>
<div id="main">
  <div id="top">
    <div id="ad"><div id="logo"><img src="/images/logo.gif" /><br />
    深圳市天威广告有限公司网站</div>
    <div id="topbannal"><img src="/images/AD.gif" width="537" height="67" /></div></div>
    <span class="title">&nbsp;&nbsp;</span> <span class="topsp">
<a href="javascript:setHomepage(document.location.host)">设为首页</a>│<a href="javascript:bookmarksite(document.location.href,document.title)">加入收藏</a>|</span> </div>
  <div id="bannal"> 
    <span class="ad" >
    <marquee scrollamount="5" onMouseOver="stop()" onMouseOut="start()" id="noticediv"></marquee>
    </span>
      <div id="menu">&nbsp;
	      <a href="/home/home.action">首页</a>&nbsp; &nbsp;
	      <a href="#">视窗直播</a> &nbsp;&nbsp; 
	      <a href="/home/productlist.action">精品服务</a> &nbsp;&nbsp;
	      <a href="/home/entlist.action">企业展示</a> &nbsp;&nbsp; 
	      <a href="/home/myshowlist.action">我型我秀</a> &nbsp;&nbsp; 
	      <a href="../progress/paylist.action">网上支付</a> &nbsp; &nbsp;<a href="#">在线帮助</a> 
      </div>
  </div>
  
  <div id="nr1">
  <form action="/home/infoQuery.action" methno="POST">
	  <div id="search"><span class="search">搜索&nbsp;&nbsp;
	  	
		<select name="typeid">
		  <option value="1">搜资讯</option><option value="2">搜产品</option><option value="3">搜企业</option><option value="4">搜个人</option>
		</select>
		<input name="keyname" type="text" class="textfield3"/>&nbsp;&nbsp;
		<input type="submit" class="btn" value="GO!" />&nbsp;&nbsp;
		
		<a href="#">高级搜索</a> <a href="#">帮助</a></span>
	  </div>
	  </form>
  	<#list page.items as news>
  	<div><font size="12"><a href="/home/productlist.action" target="_blank">${news.name}</a></font></div>
  	</#list>
  	<div>${pageString}</div>
  </div>
  <div class="clear"></div>
  <div id="bottom">
	  <span class="link">友情链接： <a href="#">天威视讯</a>|<a href="#">天威宽频</a>|<a href="#">天威网络电视</a>|<a href="#">深圳迪威特</a>|<a href="#">深圳新闻网</a>|<a href="#">深圳电视台</a>|<a href="#">新浪网</a>|<a href="#">搜狐网</a>|<a href="#">中国网联网</a>|<a href="#">中国文化网</a>|<a href="#">深圳人才网</a>|<a href="#">名车网</a></span>
	  <span class="copyright"><a href="#">关于我们</a>  |  <a href="#">广告服务</a>  |  <a href="#">法律声明</a>  |  <a href="#">服务条款</a>  |  <a href="#">联系我们</a>  |  <a href="#">网站地图</a>  |  <a href="#">合作伙伴</a>  |  <a href="#">人才招聘</a>
	  <br />天威广告有限公司版权所有 Copyright @ 2009 www.TOPWAY.com All Right Reserved
	  <br />ICP证号：粤ICP备09851114号 深圳市天威视讯股份有限公司旗下网站</span>  
  </div>
</div>
</body>
</html>
</#escape>