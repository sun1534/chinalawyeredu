<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 编辑Role</p>
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
<link rel="stylesheet" type="text/css" href="../ext2.0/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="../ext2.0/resources/css/xtheme-gray.css" />
<!-- GC -->
<!-- LIBS -->
<script type="text/javascript" src="../ext2.0/adapter/ext/ext-base.js"></script>
<!-- ENDLIBS -->
<script type="text/javascript" src="../ext2.0/ext-all.js"></script>
<script type="text/javascript" src="userselect.js"></script>
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
              <td width="97%"><span class="sort-title">职务管理&gt;&gt;修改职务人员</span></td>
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
               <s:form name="editForm" action="positionChangeDepartment.action" validate="true" method="post">
<s:hidden name="departmentposition.comp_id.tsysPosition.positionid"/>
<s:hidden name="departmentposition.comp_id.tsysDepartment.departmentid"/>
<s:hidden name="flag" value="save"/>
	 			 	<TR>
					  <TD width="15%" class="listheadline">职务:</TD>
					  <TD width="35%" class="listline">${departmentposition.comp_id.tsysPosition.positionname}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">部门:</TD>
						<TD width="35%" class="listline">${departmentposition.comp_id.tsysDepartment.departmentname}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">任职人员:</TD>
						<TD width="35%" class="listline">
<s:select 
	name="departmentposition.tsysUser.userid" 
	listKey="userid" 
	listValue="username" 
	list="userlist" 
	value="departmentposition.tsysUser.userid" 	
	id="userselsect"
/>
					</TD>
					</TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
                        <INPUT name="back2"  type=button   class="botton" onClick="javascript:history.back(-1)" value="返回">
		      </TD>
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
