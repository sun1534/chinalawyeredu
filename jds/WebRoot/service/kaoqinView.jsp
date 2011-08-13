<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查看kaoqin</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-07</p>
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
              <td width="97%"><span class="sort-title">考勤管理&gt;&gt;查看考勤管理</span></td>
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
						<TD width="15%" class="listheadline">员工:</TD>
						<TD width="35%" class="listline">${kaoqin.username}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">上\下班:</TD>
						<TD width="35%" class="listline"><s:if test="kaoqin.kqflag==1">上班</s:if><s:else>下班</s:else></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">考勤日期:</TD>
						<TD width="35%" class="listline">${kaoqin.kqdate}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">出\入时间:</TD>
						<TD width="35%" class="listline">${kaoqin.kqtime}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">考勤结果</TD>
						<TD width="35%" class="listline">
						<s:if test="kaoqin.kqresult==3">正常</s:if><s:elseif test="kaoqin.kqresult==1">迟到</s:elseif>
						<s:elseif test="kqresult==4">病假</s:elseif><s:elseif test="kqresult==5">事假</s:elseif>
						<s:else>早退</s:else>
						</TD>
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
   <s:form name="editForm" action="kaoqinEdit!input.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="deleteForm" action="kaoqinDelete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="backForm" action="kaoqinList.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
</BODY>
</HTML>
