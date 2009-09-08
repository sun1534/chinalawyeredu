<%@page contentType="text/html; charset=gb2312"%>

<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-系统管理-系统部门树型列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/dtree.js"></script>
<script lanaguage="javascript">
<!--
function SetCwinHeight1()
{
   var mainFrameID1=document.getElementById("mainFrameIDSub");
   if (mainFrameID1 && !window.opera)
   {
       if (mainFrameID1.contentDocument && mainFrameID1.contentDocument.body.offsetHeight)
         mainFrameID1.height = mainFrameID1.contentDocument.body.offsetHeight; 
       else if(mainFrameID1.Document && mainFrameID1.Document.body.scrollHeight)
         mainFrameID1.height = mainFrameID1.Document.body.scrollHeight;
   }
}
//-->
</script>
</head>
<body topmargin="0" leftmargin="0">
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
  <td valign="top" bgcolor="#FFFFFF" width="200">
    <TABLE width="200" align="center" cellpadding="0" cellspacing="1">
      <TR>
        <TD width="200" align=left>
<div class="dtree">
	<!--<a href="javascript: d.openAll();">打开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	-->
	<script type="text/javascript">
		d = new dTree('d');
		d.add('0','-1','${companyName}','sysGroupList.pl?parentid=${upgroupid}','','mainFrameSub');
		<s:iterator value="sysGroups" status="stat">
		    d.add('${groupid}','${parentid}','${groupname}','sysGroupList.pl?parentid=${groupid}','','mainFrameSub');
		</s:iterator>
    	document.write(d);
	    d.openAll();
 	</script>
</div>
        </TD>
      </TR>
    </TABLE>
  </td>
  <td width="100%" valign="top" height="100%">
   	<iframe style="VISIBILITY: inherit; WIDTH: 100%;HEIGHT: 300;" marginWidth=0 frameSpacing=0 marginHeight=0 frameborder=0 scrolling="no" id="mainFrameIDSub" name="mainFrameSub" src="sysGroupList.pl?parentid=${upgroupid}" onload="Javascript:SetCwinHeight1()">
    </iframe>          
  </td>
</tr>
</table>
</body>
</html>