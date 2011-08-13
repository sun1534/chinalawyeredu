<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查看customer列表</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script language=javascript>
<!--
function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
}
function query(){
	document.form1.action="userList.action";
    document.form1.submit();
}
-->
</script>
</HEAD>
<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%"><span class="sort-title">嘉得信通讯&gt;&gt;员工通讯录</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="userList.action" method="POST">
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class="listheadline">
                        <TD colspan="15">
                       员工姓名：<s:textfield name="username" size="10"/>
             
                       <input type="button" value="查询" onclick="query()"/>
                        	</TD>
                        </TR>
                      <TR class="listheadline">
                      <TD >部门</TD>
                      <TD >姓名</TD>
                      <TD >职务</TD>
                      <TD >办公电话</TD>
                      <TD >分机号</TD>
                      <TD >手机</TD>
                      <TD >邮箱</TD>
                      <TD >备注</TD>
      
                      </TR>
<s:iterator value="userlist" status="status">
                      <TR class=listline>
                        
                      <TD >${tsysDepartment.departmentname}</TD>
                      <TD >${username}</TD>
                      <TD >${positionname}</TD>
                       <TD >${phone}</TD>
                      <TD >${userno}</TD>
                      <TD >${mobile}</TD>
                       <TD >${email}</TD>
                      <TD >${description}</TD>
                      
                  </TR>
</s:iterator>

${pagestring}

                  </TBODY>
              </TABLE>
</s:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
<s:form name="pageForm" action="userList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>

