<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" id="Tophtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
<title>${sysName}</title>
<link rel="stylesheet" type="text/css" href="../style/css/reset.css" />
<link rel="stylesheet" type="text/css" href="../style/css/top.css" />
</head>

<body>
	<div class="doc">
		<div id="hd">

				<h1 id="logo"><a title="中山·${sysName}" href="javascript:;"><span>中山·${sysName}</span></a></h1>
				<h2><span>广东省中山市${sysName}</span></h2>
				<div class="cl"></div>
				<div id="topNav">
					<div class="topNavMain">
						<div id="topNavChange"><h3 id="topSwitchbar" title="关闭左侧菜单">系统管理菜单</h3></div>
						<div class="loginInfo">
							<span><em>${curuser.name}</em>，欢迎您！</span>
						</div>
                        <div id="topMenu">
							<ul>
								<li><a href="home.html" title="" target="mainFrame"><span>首页</span></a></li>
								<li><a href="modifyPassword!input.action" title="" target="mainFrame"><span>修改密码</span></a></li>
                                <em class="line"></em>
                                </li>
								<li><a href="#" title=""><span>系统帮助</span></a>
                                <em class="line"></em>
                                </li>
                                <li>
                                <em class="line"></em>
                                </li>
							</ul>
						</div>
                        <a href="../common/userLogout.action" target="_top" class="loginOut">安全退出</a>
						
					</div>
				</div>
		</div>
	</div>
	<script type="text/javascript" src="../style/js/jquery.js"></script>
	<script type="text/javascript" src="../style/js/swichbar.js"></script>
</body>
</html>