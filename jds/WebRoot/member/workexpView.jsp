<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查看workexp</p>
 * <p>作者： 雷华</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-05-16</p>
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
              <td width="97%"><span class="sort-title">简历录入&gt;&gt;查看简历管理</span></td>
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
						<TD width="15%" class="listheadline">工作经历id:</TD>
						<TD width="35%" class="listline">${workexp.workexpid}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">公司名称:</TD>
						<TD width="35%" class="listline">${workexp.companyname}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">企业性质:</TD>
						<TD width="35%" class="listline">${workexp.character}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">职务:</TD>
						<TD width="35%" class="listline">${workexp.duty}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">开始时间:</TD>
						<TD width="35%" class="listline">${workexp.begindate}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">结束时间:</TD>
						<TD width="35%" class="listline">${workexp.enddate}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">薪水:</TD>
						<TD width="35%" class="listline">${workexp.salary}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">离职原因:</TD>
						<TD width="35%" class="listline">${workexp.leavereason}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">工作职责和突出业绩:</TD>
						<TD width="35%" class="listline">${workexp.achievement}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">证明人:</TD>
						<TD width="35%" class="listline">${workexp.attesterman}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">证明人职务:</TD>
						<TD width="35%" class="listline">${workexp.attestermanduty}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">证明人电话:</TD>
						<TD width="35%" class="listline">${workexp.attestermanphone}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">id:</TD>
						<TD width="35%" class="listline">${workexp.resumeid}</TD>
					</TR>
                    <TR>
                      <TD width="15%" class="listheadline">状态:</TD>
                      <TD width="35%" class="listline">${workexp.statusid==1?"启用":"冻结"}</TD>
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
   <s:form name="editForm" action="workexpEdit!input.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="deleteForm" action="workexpDelete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="backForm" action="workexpList.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
</BODY>
</HTML>
