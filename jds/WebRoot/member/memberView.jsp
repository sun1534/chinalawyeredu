<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查看member</p>
 * <p>作者： 罗裴</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-04-24</p>
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
              <td width="97%"><span class="sort-title">人事管理&gt;&gt;查看会员信息</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY >
	 			 	
	 			 	<TR>
						<TD width="15%" class="listheadline">状态:</TD>
						<TD width="35%" class="listline">${member.statusid==1?"启用":"冻结"}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">注册日期:</TD>
						<TD width="35%" class="listline">${member.createdate}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">姓名:</TD>
						<TD width="35%" class="listline">${member.username}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">电话:</TD>
						<TD width="35%" class="listline">${member.phone}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">手机号码:</TD>
						<TD width="35%" class="listline">${member.mobile}</TD>
					</TR>
                    <TR>
                      <TD width="15%" class="listheadline">邮件:</TD>
                      <TD width="35%" class="listline">${member.email}</TD>
                    </TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                      <input name="edit" type=submit class="mask" onClick="document.editForm.submit()" value="编辑">
                      <input name="delete" type=button class="mask"
                      onClick="if(confirm('您确定要删除?')) document.deleteForm.submit()" value="删除">
                      <input name="back" type=button class="mask" onClick="document.backForm.submit()" value="返回">
                      </TD>
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
   <s:form name="editForm" action="memberEdit!input.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="deleteForm" action="memberDelete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="backForm" action="memberList.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
</BODY>
</HTML>
