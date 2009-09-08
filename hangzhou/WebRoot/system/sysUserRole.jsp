<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-管理员新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jscalendar:head/>
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/common.js"></script>
</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="title-table">
  <tr>
    <td width="60"  align="center">
      <img src="../imagesa/arr.gif" width="13" height="13">
    </td>
    <td width="97%"> ${navigator} </td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0" align="center" class="border-table">
  <tr class="border-tr">
    <td>
    <s:form name="form1" action="sysUserRole" method="post">
      <table width="80%" border="0" cellspacing="1" cellpadding="1" align="center" class="content-table">
		<tr>
          <td colspan="2">&nbsp;</td>
        </tr>
		 <tr>
          <td align="right" class="input-title">
             请选择角色:<br/>(已勾选的表示现在选中的角色)
             <s:hidden name="userid"/>
          </td>
          <td class="input-content">
            <s:checkboxlist name="sysRoles" list="roles"/>
          </td>
        </tr>
       
       		
        <tr>
          <td height="2" colspan="2">
          </td>
        </tr>

        <tr>
          <td colspan="2" align="center">
          	<s:submit value=" 保 存 " cssClass="button1"/>&nbsp;
           	&nbsp;
          	<input type="button" value=" 返 回 " onClick="javascript:history.back(-1)" class="button1">
          </td>
        </tr>
      </table>
    </s:form>
    </td>
  </tr>
</table>
</body>
</html>