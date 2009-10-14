<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-系统提示</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
  <link rel="stylesheet" type="text/css" href="../css/main.css" />
</head>

<body >
	<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>_pa您所在是位置:</span><em><a>信息提示页面</a></em>
				</div>
			</div>
		</div>
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!--  -->
						<div class="sysinfo">
							<h2>系统提示</h2>
							<div class="sysinfocon">
								<p>${message }
									<a href="#" onclick="document.backForm.submit()">返回上一页</a>
								</p>
						  </div>
						</div>
				</div>
			</div>
		</div>
		<form name="backForm" action="${nextPage}" method="post" target="_parent">
</form>
</body>

</html>

