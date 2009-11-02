<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-${navigator}</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
	window.location.href="sysGroupCreate!input.pl?parentid=${parentid}";
}

</script>
</head>
<body topmargin="0" leftmargin="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="title-table">
  <tr>
    <td width="60"  align="center">
      <img src="../imagesa/arr.gif" width="13" height="13">
    </td>
    <td width="97%">${navigator}
    </td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0" align="center" class="border-table">
<s:form name="form1" action="sysGroupList" method="post">
<TR class="border-tr">
  <TD valign="top">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="query-table">
      <tr>
        <td align="left">
         &nbsp;
        </td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="page-table">
      <tr> 
        <td align="right">
      <!--这里不设置分页 
      -->
        <s:hidden name="parentid"/>
        </td>
      </tr>
    </table>
    <table cellSpacing="1" cellPadding="1" width="100%" align="center" border="0" class="content-table">
      <TR class="list-title">
        <TD align="center">部门名称</TD>
        <TD align="center">联系人</TD>
        <TD align="center">创建时间</TD>
        <TD align="center">创建人员</TD>
        <!--<TD align="center">备注信息</TD>
        -->
        <TD align="center">修改</TD>
        <TD align="center">删除</TD>
      </TR>
    <s:iterator value="grouplist" status="stat">
       <TR class="list-content">
         <TD align="center">${groupname}</TD>
         <TD align="center">${contacter}</TD>
         <TD align="center">${createtime}</TD>
         <TD align="center">${createuser}</TD>>
        <!--<TD align="center">${comments}</TD>
          -->
         <TD align="center"><a href="sysGroupEdit!input.pl?groupid=${groupid}">修改</a></TD>
         <TD align="center"><a href="sysGroupDelete.pl?groupid=${groupid}&parentid=${parentid}">【删除】</a></TD>
       </TR>
    </s:iterator>
    </table>
    <table cellSpacing="1" cellPadding="1" width="100%" align="center" border="0" class="content-table">  
      <TR>
         <TD align="center">
             <INPUT type="button" onclick="return getAdd()"  value=" 新增事务所 " name="addbutton" class="button1">
           
         </TD>
      </TR>
    </table>
  </TD>
</TR>
</s:form>
</TABLE>   
</body>
</html>