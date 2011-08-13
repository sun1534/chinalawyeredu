<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 查看User</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-09-21</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF><div align="left"></div>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center">
		<img src="../images/arr.gif" width="13" height="13">
		</div>
	      </td>
              <td width="97%"><span class="sort-title">用户管理&gt;&gt;查看用户</span></td>
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
                  <TBODY >
	 			 	<TR>
						<TD width="15%" class="listheadline">用户姓名:</TD>
						<TD width="35%" class="listline">${user.username}</TD>
					</TR>
	 			 
	 			 	<TR>
						<TD width="15%" class="listheadline">登录名:</TD>
						<TD width="35%" class="listline">${user.loginname}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">部门:</TD>
						<TD width="35%" class="listline">${user.tsysDepartment.departmentname}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">职务:</TD>
						<TD width="35%" class="listline">${user.positionname}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">邮件:</TD>
						<TD width="35%" class="listline">${user.email}</TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">分机号:</TD>
						<TD width="35%" class="listline">${user.userno}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">工号:</TD>
						<TD width="35%" class="listline">${user.bqqno}</TD>
					</TR>
					
	 			 	<TR>
						<TD width="15%" class="listheadline">电话:</TD>
						<TD width="35%" class="listline">${user.phone}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">手机:</TD>
						<TD width="35%" class="listline">${user.mobile}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">管理员:</TD>
						<TD width="35%" class="listline">${user.isadmin?"是":"否"}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">领导:</TD>
						<TD width="35%" class="listline">${user.isleader?"是":"否"}</TD>
					</TR>
                    <TR>
                      <TD width="15%" class="listheadline">状态:</TD>
                      <TD width="35%" class="listline">${user.statusid==1?"正常":"离职"}</TD>
                    </TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">描述:</TD>
						<TD width="35%" class="listline">${user.description}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">注册时间:</TD>
						<TD width="35%" class="listline">${user.createtime}</TD>
					</TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                      <input name="edit" type=submit class="mask" onClick="document.editForm.submit()" value="编辑">
                      <input name="delete" type=button class="mask"
                      onClick="if(confirm('您确定要删除?')) document.deleteForm.submit()" value="删除">
                      <input name="back" type=button class="mask" onClick="document.backForm.submit()" value="返回">                      </TD>
                    </TR>
                  </TBODY>
                </TABLE>
              <p>&nbsp;</p>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
   <s:form name="editForm" action="userEdit!input.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="deleteForm" action="userDelete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="backForm" action="userList.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
</BODY>
</HTML>
