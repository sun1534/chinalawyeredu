<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-系统管理-系统部门树型列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/dtree.js"></script>
<link href="../css/dtree.css" rel="stylesheet" type="text/css">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript">
var _groupid="";
var _groupname="";
function selectit(groupid,groupname){
	_groupid=groupid;
	_groupname=groupname;
}
function confirmGroup(){
	if(_groupname!=""){
		//弹出窗口的形式打开，使所选中的项目能填充到对应的目标框中
		window.opener.document.form1.groupid.value=_groupid;
		window.opener.document.form1.groupname.value=_groupname;
	}
	//将当前窗口关闭
	window.close();
}
</script>
</head>
<body topmargin="0" leftmargin="0" style="font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 12px;">
<s:form action="sysGroupTree">
<div style="text-align:left;padding-top:3px;" > 
   部门名称:<s:textfield name="queryname" size="20"/><s:submit value="查询"  cssClass="button1"/>
</div>
</s:form>
<div class="dtree" style="border:1px solid #666;padding-left:5px;vaglin:top;height:230px;overflow:auto;overflow-x:hidden;">	
	<script type="text/javascript">
		d = new dTree('d');
        d.add("${groupid}","-1", "${groupname}","","","");
		<s:iterator value="groupList" status="stat">
		   
		    d.add("${groupid}","${parentid}","${groupname}","javascript:selectit('${groupid}','${groupname}')","","");
		</s:iterator>
		
		document.write("<p></p>");
    	document.write(d);
	    d.openAll();
 	</script>
</div>
 <div style="text-align:center;padding-top:3px;" > 
   <input type="button" value=" 确 认 " class="button1"  onclick="confirmGroup()"/>
</div>
   
</body>
</html>