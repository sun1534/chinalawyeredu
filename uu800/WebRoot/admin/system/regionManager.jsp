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
 <link rel="stylesheet" type="text/css" href="../style/css/dtree.css" />
 <script type="text/javascript" src="../style/js/dtree.js"></script>
</head>
 
<body >
	<div class="guildNav" id="guildNav">
		<div class="crumbs">
			<div class="inCrumbs">
				<div class="crumbsTit"><em>您所在的位置：</em>系统管理 &gt; <strong>区域管理</strong></div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
		<div class="main">
			<div class="Warp">
				<table width="100%">
					<tbody>
						<tr>
							<td width="180" valign="top">
								${regiontree}
							</td>
							<td height="100%">
								<iframe scrolling="auto" height="522" frameborder="0" width="100%" marginheight="5" marginwidth="5" src="regionView.action?areacode=${areacode}" name="areamain" noresize=""></iframe>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
 
</html>