<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/jquery.js"></script>
<title>${sysName}</title>
</head>
<frameset  rows="96,9,100%,20" cols="100%" frameborder="no" border="0" framespacing="0" id="AllFrame">
<frame scrolling="no" noresize="noresize" frameborder="no" id="topFrame" name="topFrame"  src="top.action" />
<frame scrolling="no" noresize="noresize" frameborder="no" id="topbarFrame" name="topbarFrame" src="topbar.html"/>
<frameset id="wapFrame" framespacing="0" border="0" frameborder="no" cols="0,9,*" >
	<frame id="leftFrame" name="leftFrame" scrolling="no" noresize="noresize" frameborder="no" src="left.action"></frame>
	<frame id="switchFrame" name="switchFrame" scrolling="no"  noresize="noresize"  frameborder="0"  border="0" src="swichbar.html"></frame>
	<frame id="mainFrame" name="mainFrame" scrolling="auto"  noresize="noresize"  frameborder="0"  border="0" src="info.html"></frame>
</frameset>
<frame scrolling="no" noresize="noresize" frameborder="no" id="footFrame" name="footFrame"  src="bottom.jsp" />
</frameset>
<noframes>
<body>
  Your browser does not seem to support frames or frame support has been disabled.<br />
  We do not currently support non-frame option yet.
 </body>
 </noframes>
</html>