<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}-错误页面</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="generator" content="EditPlus" />
<meta name="author" content="zrj" />
<link rel="stylesheet" type="text/css" href="css/style.css" media="all"/>
</head>

<body>

<!-- 出错提示 -->
	<div class="box-red">    
		<h4>出错提示</h4>    
		<div class="box-content">        
			<p>${message}！...
			<a href="#" onclick="document.backForm.submit()">返回上一页</a>
			</p>    
		</div>
	</div>
    <form name="backForm" action="${nextPage}" method="post">
</form>
</body>
</html>