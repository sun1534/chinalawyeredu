<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 编辑User</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-09-24</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=15 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center">
		<img src="../images/arr.gif" width="13" height="13">
		</div>
	      </td>
              <td width="97%"><span class="sort-title">用户管理&gt;&gt;编辑用户</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="middle" valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="editForm" action="userEdit" validate="true" method="post">
<s:hidden name="pagenumber" value="${pagenumber}"/>
	 			 	<TR>
					  <TD width="15%" class="listheadline">用户姓名:</TD>
					  <TD width="35%" class="listline"><s:textfield name="user.username"/></TD>
					</TR>
	 			 	
	 			 	<TR>
						<TD width="15%" class="listheadline">登录名:</TD>
						<TD width="35%" class="listline"><s:textfield name="user.loginname"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">部门:</TD>
						<TD width="35%" class="listline">
<s:select 
	name="user.tsysDepartment.departmentid" 
	listKey="departmentid" 
	listValue="displayname" 
	list="departmentlist" 
	value="user.tsysDepartment.departmentid" 	
/>						</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">职务:</TD>
						<TD width="35%" class="listline"><s:textfield name="user.positionname"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">密码:</TD>
						<TD width="35%" class="listline"><s:password name="password"/><span class="style1">*输入至少6位将重置密码</span></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">邮件:</TD>
						<TD width="35%" class="listline"><s:textfield name="user.email"/></TD>
					</TR>
					<TR>
					  <TD width="15%" class="listheadline">分机号:</TD>
					  <TD width="35%" class="listline"><s:textfield name="user.userno"/></TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">工号:</TD>
						<TD width="35%" class="listline"><s:textfield name="user.bqqno"/>该值由呼叫中心分配</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">电话:</TD>
						<TD width="35%" class="listline"><s:textfield name="user.phone"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">手机:</TD>
						<TD width="35%" class="listline"><s:textfield name="user.mobile"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">管理员:</TD>
						<TD class="listline"><s:checkbox name="user.isadmin" disabled="${curuser.isadmin?'false':'true'}"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">领导:</TD>
						<TD class="listline"><s:checkbox name="user.isleader" disabled="${curuser.isadmin?'false':'true'}"/></TD>
					</TR>
                    <TR>
                      <TD width="15%" class="listheadline">状态:</TD>
                      <TD width="35%" class="listline">
                      <SELECT name="user.statusid" class="input1">
<s:if test="user.statusid==1">
                         <OPTION VALUE="1" selected>正常</OPTION>
                         <OPTION VALUE="0">离职</OPTION>
</s:if>
<s:if test="user.statusid==0">
                         <OPTION VALUE="1">正常</OPTION>
                         <OPTION VALUE="0" selected>离职</OPTION>
</s:if>
                      </SELECT>                      </TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">描 述:</TD>
                      <TD width="35%" class="listline">
						<s:textarea  name="user.description" cols="30" rows="5"/>                      </TD>
                    </TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
                        <INPUT name="back2"  type=button   class="botton" onClick="javascript:history.back(-1)" value="返回">		      </TD>
                    </TR>
                   </s:form>
                  </TBODY>
                </TABLE>
            <br></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
