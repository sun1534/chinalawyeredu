<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-系统管理-角色列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript">

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
	window.location.href="sysRoleCreate!input.pl";
}

</script>
</head>
<body topmargin="0" leftmargin="0">
	<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="../imagesa/b_02.gif" width="4" height="7">
					${navigator}
				</td>
			</tr>
		</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
align="center" class="border-table">
<s:form name="form1" action="sysRoleList" method="post">
<TR >
  <TD valign="top">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0"
							align="center" class="query-table">
	<tr>
        <td align="left">

              <s:hidden name="pageNo" value="1"/>
        	  <s:textfield name="rolename" size="12" cssClass="text1" label="角色名"/>
              <s:submit value=" 查 询 " cssClass="button"/>

        </td>
      </tr>
    </table>
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
							<tr>
								<td height="24" align="right"
									background="../imagesa/login_bg1.gif">
									${page.pageView}
								</td>
							</tr>
						</table>
    <table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#EDEDED">
							<tr>
        <TD align="center" background="../imagesa/top-bg1.gif" height="23">
        <s:checkbox name="checkAll" onclick="getCheckAll(this)"/>
        </TD>
        <TD align="center" background="../imagesa/top-bg1.gif">角色名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">所属部门</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">创建人员</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">权限分配</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">修改</TD>
       <TD align="center" background="../imagesa/top-bg1.gif">删除</TD>
      </TR>
      <s:iterator value="page.items" status="stat">
      <TR>
        <TD align="center" class="tab_content"><input type="checkbox" name="check" value="${roleid}"/></TD>
        <TD align="center" class="tab_content">${rolename}</TD>
         <TD align="center" class="tab_content">&nbsp;</TD>
        <TD align="center" class="tab_content">${createuser}</TD>
        <TD align="center" class="tab_content"><a href="sysRoleRightPre.pl?roleid=${roleid}">权限分配</a></TD>
        <TD align="center" class="tab_content"><a href="sysRoleEdit!input.pl?roleid=${roleid}">修改</a></TD>
        <TD align="center" class="tab_content">
        <s:if test="createuser.equals(\"0\")||createuser.equals(\"-1\")">
       &nbsp;
        </s:if>
        <s:else>
        <a href="sysRoleDelete.pl?roleid=${roleid}">【删除】</a>
        </s:else>
        </TD>
      </TR>
      </s:iterator>
    </table>
    <TABLE cellSpacing="1" cellPadding="1" width="100%" align="center" border="0" class="content-table">  
      <TR>
         <TD align="center">
             <INPUT type="button" onClick="return getAdd()"  value=" 新增角色 " name="addbutton" class="button">
             &nbsp;
         </TD>
      </TR>
    </TABLE>
  </TD>
</TR>
</s:form>
</TABLE>   
</body>
</html>