<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
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
				<div class="crumbsTit">&nbsp;</div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
	  <s:form name="form1" action="orgCreate" validate="true" method="post">
  		<s:hidden name="orgid" value="%{parent.orgid}"/>
  		<s:hidden name="org.orgtype" value="SYS"/>
		<div class="main">
			<div id="listArea">
				<div class="addModBtn">
					<table class="addTable">
						<tbody>
							<tr>
								<td class="w100"></td>
								<td><span class="saveBtn"><span class="inbtns"><button type="submit" class="btn">保 存</button></span></span>
									<span class="backBtn"><span class="inbtns"><a href="javascript:history.back(-1);" class="btn">返 回</a></span></span></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="DataAddArea">
					<div class="addMod">
						<table class="addTable">
							<tbody>
								<tr>
									<th class="fTit w100">上级部门：</th>
									<td colspan="3">${parent.orgname}</td>
								</tr>
								<tr>
									<th class="fTit w100">名称：</th>
									<td colspan="3"><s:textfield name="org.orgname" cssClass="txt"/></td>
								</tr>
								<tr>
									<th class="fTit w100">简称：</th>
									<td><s:textfield name="org.orgshortname" cssClass="txt"/></td>
									<th class="fTit w100">英文名称：</th>
									<td><s:textfield name="org.orgenname" cssClass="txt"/></td>
								</tr>
								<tr>
									<th class="fTit w100">联系人：</th>
									<td><s:textfield name="org.contactperson" cssClass="txt"/></td>
									<th class="fTit w100">联系电话：</th>
									<td><s:textfield name="org.contactphone" cssClass="txt"/></td>
								</tr>
								<tr>
									<th class="fTit w100">手机号：</th>
									<td><s:textfield name="org.mobile" cssClass="txt"/></td>
									<th class="fTit w100">传真：</th>
									<td><s:textfield name="org.faxnum" cssClass="txt"/></td>
								</tr>
								<tr>
									<th class="fTit w100">说明：</th>
									<td colspan="3"><s:textfield name="org.comments" cssClass="txt"/></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		</s:form>
	</div>
	<script type="text/javascript" src="../style/js/jquery.js"></script>
	<script type="text/javascript" src="../style/js/tablegrid.js"></script>
</body>
</html>

