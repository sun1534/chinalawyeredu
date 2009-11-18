<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}</title>
<script language="javascript">  
document.onkeydown=pressF5;  
function pressF5()  
{  
	
	//alert(event.keyCode);
  if(event.keyCode==116){  
  	
    window.event.keyCode=0;  
    window.frames["topFrame"].pressedF5();
    
  }  
}  
</script>
</head>
<frameset rows="71,*,20" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="../index/top.action" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" onload="document.getElementById('left').src='../index/left.action'"/>
  <frameset rows="*" cols="233,*" framespacing="0" frameborder="no" border="0" name="allMain">
    <frameset rows="*" cols="215,18" name="leftMenu">
      <frame name="left" id="left" onload="document.getElementById('leftFrame').src='mainswitch.html'"/>
      <frame name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame"  onload="document.getElementById('mainFrame').src='../index/workspace.action'" />
    </frameset>
    <frame name="mainFrame" id="mainFrame" />
  </frameset>
  <frame src="bottom.jsp" scrolling="no" noresize>  
</frameset>
<noframes></noframes>
<body></body></noframes>
</html>
