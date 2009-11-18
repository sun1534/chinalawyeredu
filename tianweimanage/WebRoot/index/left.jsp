<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}-菜单</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<link href="../css/dtree.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/dtree.js"></script>
</head>
<body class="bgf3fe">
<div id="sidebar">
<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.config.useIcons =true;
		d.config.useStatusText =false;
		d.config.rightMenu=true;
		d.add('0','-1','功能菜单');
		<s:iterator value="userMenus" status="stat">
		    d.add("${rightcode}","${parentcode}","${rightname}","${linkurl}","","${opentarget}");
		</s:iterator>
		document.write(d);
	//	d.openAll();
 	   //-->
	</script>
</div>
</body>
</html>