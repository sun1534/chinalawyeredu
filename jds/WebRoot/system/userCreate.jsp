<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 创建user</p>
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
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%"><span class="sort-title">用户管理&gt;&gt;新增用户</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="middle" valign="top" bgColor=#F9F9F7>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="createForm" action="userCreate" validate="true" method="post">
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
/>						</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">职务:</TD>
						<TD width="35%" class="listline"><s:textfield name="user.positionname"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">密码:</TD>
						<TD width="35%" class="listline"><s:textfield name="user.password"/></TD>
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
						<TD width="15%" class="listheadline">坐席号:</TD>
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
						<TD class="listline"><s:checkbox value="false" name="user.isadmin"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">领导:</TD>
						<TD class="listline"><s:checkbox value="false" name="user.isleader"/></TD>
					</TR>
                    <TR>
                      <TD width="15%" class="listheadline">状态:</TD>
                      <TD width="35%" class="listline">
                      <SELECT name="user.statusid" class="input1">
                            <OPTION VALUE="1" selected>启用</OPTION>
                            <OPTION VALUE="0">冻结</OPTION>
                      </SELECT>                      </TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">描 述:</TD>
                      <TD width="35%" class="listline">
					<s:textarea  name="user.description" cols="30" rows="5"/>                    </TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
			            <INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">		              </TD>
                    </TR>
                   </s:form>
                  </TBODY>
                </TABLE>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
