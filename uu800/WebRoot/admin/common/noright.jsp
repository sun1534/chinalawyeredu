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
 <link rel="stylesheet" type="text/css" href="../style/css/error.css" />
</head>
 
<body >
	<div class="guildNav" id="guildNav">
		<div class="crumbs">
			<div class="inCrumbs">
				<div class="crumbsTit"><em>您所在的位置：</em><strong>操作提示</strong></div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
		<div class="main">
			<div class="error">
				<div class="errorTit">操作提示</div>
				<div class="errorM">
					<p class="errorInfo">对不起,您没有操作此业务的权限,请返回</p>
					<p class="tCenter">
						<span class="backBtn">
						<span class="inbtns">
						<INPUT  type="button"  class="btn" onClick="document.backForm.submit()" value="返 回"/>
						</span>
						</span>
					</p>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="../style/js/jquery.js"></script>
	<script type="text/javascript" src="../style/js/tablegrid.js"></script>
</body>
<form name="backForm" action="${nextPage}" method="post">
  <s:hidden name="pageNo" value="%{pageNo}"/>
</form>
</html>
