<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查看studyexp</p>
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
						<TD width="15%" class="listheadline">教育经历id:</TD>
						<TD width="35%" class="listline">${studyexp.studyexpid}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">学历:</TD>
						<TD width="35%" class="listline">${studyexp.education}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">教育机构:</TD>
						<TD width="35%" class="listline">${studyexp.school}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">开始时间:</TD>
						<TD width="35%" class="listline">${studyexp.begindate}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">结束时间:</TD>
						<TD width="35%" class="listline">${studyexp.enddate}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">专业:</TD>
						<TD width="35%" class="listline">${studyexp.major}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">资历证书:</TD>
						<TD width="35%" class="listline">${studyexp.certification}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">技能专长:</TD>
						<TD width="35%" class="listline">${studyexp.skills}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">id:</TD>
						<TD width="35%" class="listline">${studyexp.resumeid}</TD>
					</TR>
                    <TR>
                      <TD width="15%" class="listheadline">状态:</TD>
                      <TD width="35%" class="listline">${studyexp.statusid==1?"启用":"冻结"}</TD>
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
   <s:form name="editForm" action="studyexpEdit!input.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="deleteForm" action="studyexpDelete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="backForm" action="studyexpList.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
</BODY>
</HTML>
