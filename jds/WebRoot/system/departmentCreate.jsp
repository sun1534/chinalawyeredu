<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 创建department</p>
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
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style></HEAD>
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
              <td width="97%"><span class="sort-title">部门管理&gt;&gt;新增部门</span></td>
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
               <s:form name="form1" action="departmentCreate" validate="true" method="post">
               <s:hidden name="pagenumber" value="${pagenumber}"/>	 			 	<TR>
					  <TD width="15%" class="listheadline">部门名称:</TD>
					  <TD width="35%" class="listline"><s:textfield name="department.departmentname"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">类型:</TD>
						<TD width="35%" class="listline">
						<s:select 
							name="department.type" 
							listKey="typeid" 
							listValue="typename" 
							list="typelist" 
							value="0" 
						/>
                    </TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">上级部门:</TD>
						<TD width="35%" class="listline">
<s:select 
	name="department.parent.departmentid" 
	listKey="departmentid" 
	listValue="displayname" 
	list="departmentlist" 
	value="0" 
/>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">电话:</TD>
						<TD width="35%" class="listline"><s:textfield name="department.phone"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人:</TD>
						<TD width="35%" class="listline"><s:textfield name="department.linkman"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">地址:</TD>
						<TD width="35%" class="listline"><s:textfield name="department.address"/></TD>
					</TR>
                    <TR>
                      <TD width="15%" class="listheadline">状态:</TD>
                      <TD width="35%" class="listline">
                      <SELECT name="department.statusid" class="input1">
                            <OPTION VALUE="1" selected>启用</OPTION>
                            <OPTION VALUE="0">冻结</OPTION>
                      </SELECT>	
                      </TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">描 述:</TD>
                      <TD width="35%" class="listline"><s:textarea  name="department.description" cols="30" rows="5"/>
                      </TD>
                    </TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
			            <INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
		              </TD>
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
