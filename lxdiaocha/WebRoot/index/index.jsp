<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<frameset rows="52,*,20" cols="*" frameborder="NO" border="0" framespacing="0">
  <frame src="../index/top.pl" name="top" scrolling="NO" noresize >
  <frameset rows="*" cols="180,*" framespacing="0" frameborder="NO" border="0">
    <!--<frame src="../index/left.pl" name="left" scrolling="no" noresize>-->
     <frame src="../index/left.pl" name="left" >
    <frame src="../index/workspace.pl" name="main">
  </frameset>
  <frame src="bottom.jsp" scrolling="no" noresize>
</frameset>
<noframes><body>
</body></noframes>
</html>