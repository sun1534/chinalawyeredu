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
              <td width="97%"><span class="sort-title">用户管理&gt;&gt;修改角色权限</span></td>
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
               <s:form name="editForm" action="functionChangeRolePower.action" validate="true" method="post">
<s:hidden name="functionrole.comp_id.tsysRole.roleid"/>
<s:hidden name="functionrole.comp_id.tsysFunction.functionid"/>
<s:hidden name="flag" value="save"/>
<s:hidden name="functionrole.power" value="${functionrole.power}"/>
	 			 	<TR>
					  <TD width="15%" class="listheadline">角色:</TD>
					  <TD width="35%" class="listline">${functionrole.comp_id.tsysRole.rolename}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">功能:</TD>
						<TD width="35%" class="listline">${functionrole.comp_id.tsysFunction.functionname}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" rowspan="2" class="listheadline">权限:</TD>
						<TD width="35%" class="listline">
							<INPUT type="checkbox" value='8' name="power" ${power8?"checked":""}>
							删除
							<INPUT type="checkbox" value='4' name="power" ${power4?"checked":""}>
							修改
							<INPUT type="checkbox" value='2' name="power" ${power2?"checked":""}>
							新增
							<INPUT type="checkbox" value='1' name="power" ${power1?"checked":""}>
							查看
					    </TD>
					</TR>
	 			 	<TR>
						<TD width="35%" class="listline">
                       <s:if test="functionrole.comp_id.tsysFunction.tsysModule.powertype==0">
                          <INPUT type="checkbox" value='64' name="power" ${power64?"checked":""}>
                          删除自己
                          <INPUT type="checkbox" value='32' name="power" ${power32?"checked":""}>
                          修改自己
                          <INPUT type="checkbox" value='16' name="power" ${power16?"checked":""}>
                          查看自己                        
                      </s:if>
					  <s:if test="functionrole.comp_id.tsysFunction.tsysModule.powertype==1">
<s:iterator value="functionrole.comp_id.tsysFunction.tsysModule.tsysPowers" status="status">
                          <INPUT type="checkbox" value='${powernum}' name="power" ${powernum==16&&power16?"checked":""}${powernum==32&&power32?"checked":""}${powernum==64&&power64?"checked":""}${powernum==128&&power128?"checked":""}${powernum==256&&power256?"checked":""}>
                          ${powername}
</s:iterator>
                      </s:if>
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
