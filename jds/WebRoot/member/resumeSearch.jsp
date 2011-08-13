<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查询resume</p>
 * <p>作者： 雷华</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-05-13</p>
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
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%">
		 <span class="title-blank-b">简历录入&gt;&gt;查询简历管理</span>
	      </td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="searchForm" action="resumeSearch.action" method="post">
                <TABLE cellSpacing=1 cellPadding=3 width="60%" align=center  border=0>
                  <TBODY>
                    
                    <TR>
                      <TD width="15%" class="listheadline">姓名:</TD>
                      <TD width="35%" class="listline"><INPUT name="resumeExample.username"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">手机号码:</TD>
                      <TD width="35%" class="listline"><INPUT name="resumeExample.mobile"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">毕业学校:</TD>
                      <TD width="35%" class="listline"><INPUT name="resumeExample.school"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">专业:</TD>
                      <TD width="35%" class="listline"><INPUT name="resumeExample.major"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">工作年限:</TD>
                      <TD width="35%" class="listline"><INPUT name="resumeExample.workyears"/></TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">毕业时间:</TD>
                      <TD width="35%" class="listline"><INPUT name="resumeExample.graduateddate"/></TD>
                    </TR>
                    
                    <TR>
                      <TD width="15%" class="listheadline">学历:</TD>
                      <TD width="35%" class="listline"><INPUT name="resumeExample.educationallevel"/></TD>
                    </TR>
                    <TR bgcolor="#CCCCCC">
                       <TD colspan="4" align="center">
                       <input  class="botton" type="submit" value="查询">&nbsp;
		       <input name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">                      </TD>
                    </TR>
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
