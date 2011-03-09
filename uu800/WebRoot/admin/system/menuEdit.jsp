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
				<div class="crumbsTit"><em>您所在的位置：</em>系统管理 &gt; 菜单管理 &gt; <strong>菜单编辑</strong></div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
	 <s:form name="form1" action="menuEdit" method="POST">
      <s:hidden name="menuid" value="%{menu.menuid}"/>
      <s:hidden name="backurl"/>
		<div class="main">
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
								<th class="fTit w100">菜单编码：</th>
								<td><s:textfield name="menu.menuid" cssClass="txt" readonly="true"/></td>
							</tr>
							<tr>
								<th class="fTit w100">菜单名称：</th>
								<td><s:textfield name="menu.menuname" cssClass="txt w250"/></td>
							</tr>
							<tr>
								<th class="fTit w100">排序序号：</th>
								<td><s:textfield name="menu.orderid" cssClass="txt"/></td>
							</tr>
							<tr>
								<th class="fTit w100">菜单链接：</th>
								<td><s:textfield name="menu.linkurl" cssClass="txt w250"/></td>
							</tr>
							<tr>
								<th class="fTit w100">目标框架：</th>
								<td><s:textfield name="menu.opentarget" cssClass="txt"/></td>
							</tr>
							<tr>
								<th class="fTit w100">类别：</th>
								<td>${menu.menutype}</td>
							</tr>
							<tr>
								<th class="fTit w100" valign="top">备注：</th>
								<td>
								<s:textarea name="menu.comments" cols="28" rows="4" cssClass="txtArea  w250"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	  </s:form>
	</div>
	<script type="text/javascript" src="../style/js/jquery.js"></script>
	<script type="text/javascript" src="../style/js/tablegrid.js"></script>
</body>
 
</html>
 

