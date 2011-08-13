<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="jscalendar" uri="/jscalendar" %>

<%
/**
 * <p>功能： 编辑kaoqin</p>
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
<script src="../js/SetTime.js"></script>
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
              <td width="97%"><span class="sort-title">考勤管理&gt;&gt;编辑考勤管理</span></td>
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
               <s:form name="form1" action="kaoqinEdit" validate="true" method="post">
	 			 	<TR>
						<TD width="15%" class="listheadline">员工:</TD>
					<TD width="35%" class="listline"><s:textfield name="kaoqin.username"/></TD>
					</TR>
	 			 		<TR>
						<TD width="15%" class="listheadline">上\下班:</TD>
					<TD width="35%" class="listline"><s:select name="kaoqin.kqflag" list="#{1:'上班',2:'下班'}"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">考勤日期:</TD>
					<TD width="35%" class="listline"><jscalendar:jscalendar name="kaoqin.kqdate" format="%Y-%m-%d"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">出\入时间:</TD>
					<TD width="35%" class="listline"><s:textfield name="kaoqin.kqtime" size="8" onclick="_SetTime(this)"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">考勤结果:</TD>
					<TD width="35%" class="listline"><s:select name="kaoqin.kqresult" list="#{3:'正常',1:'迟到',2:'早退',4:'病假',5:'事假'}"/></TD>
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
