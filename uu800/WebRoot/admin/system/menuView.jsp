<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" id="MainHtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <title>${sysName}</title>
 <link rel="stylesheet" type="text/css" href="../style/css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../style/css/main.css" />
 <link rel="stylesheet" type="text/css" href="../style/css/page.css" />
</head>
 
<body >
	<div class="guildNav" id="guildNav">
		<div class="crumbs">
			<div class="inCrumbs">
				<div class="crumbsTit"><em>您所在的位置：</em>系统管理 &gt; 菜单管理 &gt; <strong>菜单查看</strong></div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
		<div class="main">
			<div id="DataAddArea">
				<div class="addModBtn">
					<table class="addTable">
						<tbody>
							<tr>
								<td class="w100"></td>
								<td>
									<span class="backBtn"><span class="inbtns"><a href="javascript:history.back(-1);" class="btn">返 回</a></span></span></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="addMod">
					<table class="addTable">
						<tbody>
							<tr>
								<th class="fTit w100">菜单编码：</th>
								<td>${menu.menuid}</td>
							</tr>
							<tr>
								<th class="fTit w100">菜单名称：</th>
								<td>${menu.menuname}</td>
							</tr>
							<tr>
								<th class="fTit w100">菜单级别：</th>
								<td>${menu.menulevel}</td>
							</tr>
							<tr>
								<th class="fTit w100">父菜单</th>
								<td>${menu.parentMenu.menuname}</td>
							</tr>
							<tr>
								<th class="fTit w100">排序序号：</th>
								<td>${menu.orderid}</td>
							</tr>
							<tr>
								<th class="fTit w100">菜单链接：</th>
								<td>${menu.linkurl}</td>
							</tr>
							<tr>
								<th class="fTit w100">目标框架：</th>
								<td>${menu.opentarget}</td>
							</tr>
							<tr>
								<th class="fTit w100">类别：</th>
								<td>${menu.menutype}</td>
							</tr>
							<tr>
								<th class="fTit w100">创建时间：</th>
								<td><s:date name="menu.createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
							<tr>
								<th class="fTit w100">备注：</th>
								<td>${menu.comments}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="../style/js/jquery.js"></script>
	<script type="text/javascript" src="../style/js/tablegrid.js"></script>
</body>
 
</html>
 

