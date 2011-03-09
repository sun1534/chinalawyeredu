<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
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
				<div class="crumbsTit"><em>您所在的位置：</em> &gt; <strong>修改密码</strong></div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
		<div class="main">
		<s:form id="modifyPassword" name="form1" action="modifyPassword.action" method="post" >
			<div id="DataAddArea">
					<div class="addModBtn">
					<table class="addTable">
						<tbody>
							<tr>
								<td class="w100"></td>
								<td>
								<span class="saveBtn"><span class="inbtns"><button class="btn" type="submit">保 存</button></span></span>
								<span class="backBtn"><span class="inbtns"><a href="javascript:history.back(-1);" class="btn">返 回</a></span></span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			
				<div class="addMod">
					<table class="addTable">
						<tbody>
							<tr>
								<th class="fTit w100">原始密码：</th>
								<td><input type="password" name="inputoldpassword"  id="inputoldpassword" value="" class="txt"/></td>
							</tr>
							<tr>
								<th class="fTit w100">新密码：</th>
								<td><input type="password" name="password1" id="password1" value="" class="txt"/></td>
							</tr>
							<tr>
								<th class="fTit w100">确认密码：</th>
								<td><input type="password" name="password2" id="password2" value="" class="txt"/></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</s:form>
		</div>
	</div>
	<script type="text/javascript" src="style/js/jquery.js"></script>
	<script type="text/javascript" src="style/js/tablegrid.js"></script>
</body>
 
</html>
 

