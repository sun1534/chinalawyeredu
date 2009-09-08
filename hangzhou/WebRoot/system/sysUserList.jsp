<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>管理列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
	window.location.href="sysUserCreate!input.pl";
}
</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" class="baseFontBold">
    	<img src="images/b_02.gif" width="4" height="7"> 
    	当前位置： ${navigator}
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
	<s:form name="form1" action="sysUserList" method="post">
  <tr>
    <td valign="top">	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list_box">
        <tr>
          <td height="24"  >
             <s:hidden name="pageNo" value="1"/>
        	       <s:textfield name="loginname" size="12" label="登录名"/>
                   <s:textfield name="username" size="12" label="姓名"/> 
              	   <s:textfield name="groupname" size="12" label="事务所"/> 
             <s:submit value=" 查 询 " cssClass="button"/>
          </td>
        </tr>
      </table>
   
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list_box">
        <tr  class="list_tdfunc">
          <td height="24" align="right" >
           ${page.pageView}
          </td>
        </tr>
      </table>
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#EDEDED">
		<tr>
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif">姓名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">登录名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">创建人员</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">所属部门</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">状态</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">角色分配</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">权限分配</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">修改</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">删除</TD> 
      </tr>
      
<s:iterator value="page.items" status="stat">
      <TR class="list_td01">
        <!--<TD class="tab_content" align="center"><input type="checkbox" name="check" value="${userid}"/></TD>
         -->
        <TD align="center">${username}</TD>
        <TD align="center">${loginname}</TD>
        <TD align="center">${createuser}</TD>
        <TD align="center">${sysGroup.groupname}</TD>
        <TD align="center">
        <s:if test="status==0">
        激活
        </s:if>
        <s:else>
        <span class="statusClass">冻结</span>
        </s:else>
        </TD>
       <!-- 
       <TD align="center">
        <s:iterator value="sysRoles" status="stat">
         	 ${rolename}
        </s:iterator>	
        </TD>
        -->
           <TD align="center"><a href="sysUserRolePre.pl?userid=${userid}">角色分配</a></TD>
        <TD align="center"><a href="sysUserRightPre.pl?userid=${userid}">权限分配</a></TD>
        <TD align="center"><a href="sysUserEditPre.pl?userid=${userid}">修改</a></TD>
        <TD align="center"><a href="sysUserDelete.pl?check=${userid}">【删除】</a></TD>
      </TR>
      </s:iterator>
      <tr class="list_td01">
        <td  colspan="9" align="center">
    &nbsp;
  
        	
       </td>
      </tr>
       <tr class="list_tdfunc">
          <td height="24" colspan="9"  align="center"  >
         <INPUT type="button" onClick="return getAdd()"  value=" 新增管理员 " name="addbutton" class="button">
             &nbsp;    	
          </td>
        </tr>
    </table>
        
    </td>
  </tr>
    </s:form>
</table>
</body>
</html>