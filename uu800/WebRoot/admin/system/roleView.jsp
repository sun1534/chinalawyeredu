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
				<div class="crumbsTit"><em>您所在的位置：</em>系统管理 &gt; 角色管理 &gt; <strong>角色查看</strong></div>
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
								<th class="fTit w100">角色编码：</th>
								<td>${role.roleid}</td>
							</tr>
							<tr>
								<th class="fTit w100">角色名称：</th>
								<td>${role.rolename}</td>
							</tr>
							<tr>
								<th class="fTit w100">角色描述：</th>
								<td>${role.comments}</td>
							</tr>
							<tr>
								<th class="fTit w100">创建时间：</th>
								<td><s:date name="role.createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
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