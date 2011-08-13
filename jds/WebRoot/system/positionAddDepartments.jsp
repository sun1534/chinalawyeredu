<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
/**
 * <p>功能： 查看Function列表</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2004-10-10</p>
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
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60"><div align="center">
                <img src="../images/arr.gif" width="13" height="13">              </div></td>
              <td width="97%"><span class="sort-title">职务管理&gt;&gt;增加职务</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF>
      <br>
      <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#7F9DB9">
          <TBODY>
            <s:form name="form1" action="positionAddDepartments.action" validate="true" method="post"> 
<s:hidden name="flag" value="save"/>
<s:hidden name="positionid"/>
            <TR>
              <TD width="15%" class="listheadline">职务名称:</TD>
              <TD width="35%" class="listline">${position.positionname}</TD>
            </TR>
            <TR>
              <TD width="15%" class="listheadline">部门:</TD>
              <TD width="35%" class="listline">
<s:select 
	name="departmentid" 
	listKey="departmentid" 
	listValue="displayname" 
	list="departmentlist" 
/>
              </TD>
            </TR>
            <TR>
              <TD width="15%" class="listheadline">人员:</TD>
              <TD width="35%" class="listline">
<s:select 
	name="userid" 
	listKey="userid" 
	listValue="username" 
	list="userlist" 
	id="userselsect"
/>
              </TD>
            </TR>
            <TR bgcolor="#CCCCCC">
              <TD colspan="4" align="center">
                <INPUT name="insert" type="submit" class="botton" value="保存">
        &nbsp;
                <INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
              </TD>
            </TR>
            </s:form>
          </TBODY>
    </TABLE>	</TD>
  </TR>
</TABLE>
</BODY>
</HTML>
