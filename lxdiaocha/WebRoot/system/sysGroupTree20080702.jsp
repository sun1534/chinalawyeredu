<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-系统管理-系统部门树型列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script type="text/javascript" src="../js/dtree.js"></script>
<link href="../css/dtree.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript">
function setParentId(groupid,groupname){
//弹出窗口的形式打开，使所选中的项目能填充到对应的目标框中
window.opener.setParentId(groupid,groupname);
//将当前窗口关长
//window.close();
}
function nodeSelect(groupid,groupname){
//判断是调用js还是打开链接

window.frames["mainFrameSub"].location.href='sysGroupList.pl?parentid='+groupid;

}
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
	<!--<a href="javascript: d.openAll();">打开所暿/a> | <a href="javascript: d.closeAll();">关闭所暿/a>
	--> 
	<script type="text/javascript">
		d = new dTree('d');
            d.add("${groupid}","-1",         "${groupname}","sysGroupList.pl?parentid=${groupid}","","mainFrameSub");
		<s:iterator value="groupList" status="stat">
		    d.add("${groupid}","${parentid}","${groupname}","sysGroupList.pl?parentid=${groupid}","","mainFrameSub");
		</s:iterator>
		document.write("<p></p>");
    	document.write(d);
	    d.openAll();
 	</script>
</div>
        </TD>
      </TR>
    </TABLE>
  </td>
  <td width="100%" valign="top" height="100%">
   	<iframe style="VISIBILITY:inherit; WIDTH:100%;HEIGHT:500;" marginWidth=0 frameSpacing=0 marginHeight=0 frameborder=0 scrolling="no" id="mainFrameSub" name="mainFrameSub" src="sysGroupList.pl?parentid=${upgroupid}">
    </iframe>          
  </td>
</tr>
</tr>
</table>
</body>
</html>