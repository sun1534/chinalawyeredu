<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="jscalendar" uri="/jscalendar" %>

<%
/**
 * <p>功能： 编辑creditlog</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-14</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">

<jscalendar:head/>

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
              <td width="97%"><span class="sort-title">业务管理&gt;&gt;编辑催收日志</span></td>
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
                <TABLE width="500"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="form1" action="creditlogEdit" validate="true" method="post">
	 			 	<!--
	 			 	<TR>
						<TD width="15%" class="listheadline"> :</TD>
					<TD width="35%" class="listline"><s:textfield name="creditlog.logid"/></TD>
					</TR>
	 			 	-->
	 			 	<TR>
						<TD width="15%" class="listheadline">客户姓名:</TD>
					<TD width="35%" class="listline"><s:select name="creditlog.toprCredittask.credittaskid" list="tasklist" listKey="credittaskid" listValue="toprCreditcard.username"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">催收日期:</TD>
					<TD width="35%" class="listline">
					<jscalendar:jscalendar	name="creditlog.logtime" format="%Y-%m-%d %H:%M" showstime="true"/>
					
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">催收情况:</TD>
					<TD width="35%" class="listline"><s:textarea name="creditlog.comments" cols="50" rows="5"/></TD>
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
               <br>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
