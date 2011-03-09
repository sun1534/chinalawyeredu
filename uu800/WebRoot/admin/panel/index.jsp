<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="style/js/jquery.js"></script>
<title>${sysName}-管理台</title>
</head>
<frameset rows="115,*,30" cols="*" frameborder="no" border="0" framespacing="0" id="AllFrame" name="AllFrame">
<frame scrolling="no" noresize="noresize" frameborder="no" id="topFrame" name="topFrame"  src="top.action" />
<frameset id="wapFrame" framespacing="0" border="0" frameborder="no" cols="205,9,*" >
	<frame scrolling="auto" noresize="noresize" frameborder="no" border="0"  id="leftFrame" name="leftFrame" src="left.action"></frame>
	<frame scrolling="no"  noresize="noresize"  frameborder="0"  border="0" id="switchFrame" name="switchFrame" src="swichbar.html"></frame>
	<frame scrolling="yes" noresize="noresize"  frameborder="0"  border="0"  id="mainFrame" name="mainFrame" src="workspace.action" style="overflow:visible"></frame>
</frameset>
<frame scrolling="no" noresize="noresize" frameborder="no" id="footFrame" name="footFrame"  src="foot.html" />
</frameset>
<noframes>
<body>
  Your browser does not seem to support frames or frame support has been disabled.<br />
  We do not currently support non-frame option yet.
 </body>
 </noframes>
</html>