<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 编辑member</p>
 * <p>作者： 罗裴</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-04-28</p>
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
              <td width="97%"><span class="sort-title">人事管理&gt;&gt;编辑人员资料</span></td>
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
                  <TBODY>
               <s:form name="form1" action="memberEdit" validate="true" method="post">
	 			
	 			 	<TR>
                      <TD width="15%" class="listheadline">状态:</TD>
                      <TD width="35%" class="listline">
                      <SELECT name="member.statusid" class="input1">
<s:if test="member.statusid==1">
                         <OPTION VALUE="1" selected>正常</OPTION>
                         <OPTION VALUE="0">离职</OPTION>
</s:if>
<s:if test="member.statusid==0">
                         <OPTION VALUE="1">正常</OPTION>
                         <OPTION VALUE="0" selected>离职</OPTION>
</s:if>
                      </SELECT>
                      </TD>
                    </TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">注册日期:</TD>
						<TD width="35%" class="listline"><s:property value="member.createdate"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">姓名:</TD>
						<TD width="35%" class="listline"><s:textfield name="member.username"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">电话:</TD>
						<TD width="35%" class="listline"><s:textfield name="member.phone"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">手机号码:</TD>
						<TD width="35%" class="listline"><s:textfield name="member.mobile"/></TD>
					</TR>
                    <TR>
                      <TD width="15%" class="listheadline">邮件:</TD>
                      <TD width="35%" class="listline"><s:textfield name="member.email"/></TD>
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
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
