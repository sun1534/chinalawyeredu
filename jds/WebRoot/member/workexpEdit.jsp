<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%
/**
 * <p>功能： 编辑workexp</p>
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
<jscalendar:head/>	
	<link rel="stylesheet" href="../struts/jscalendar/calendar-${curuser.style==1?"bule":""}${curuser.style==2?"brown":""}${curuser.style==3?"green":""}.css" type="text/css"/>
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
              <td width="97%"><span class="sort-title">简历录入&gt;&gt;编辑简历管理</span></td>
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
               <s:form name="form1" action="workexpEdit" validate="true" method="post">
			   <input type="hidden" name="resumeid" value="${resumeid}"/>
			   <s:hidden name="workexp.workexpid"/>
			   <s:hidden name="workexp.tmemResume.resumeid"/>				
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
	 			 	<TR>
						<TD width="15%" class="listheadline">公司名称:</TD>
						<TD width="35%" class="listline"><s:textfield name="workexp.companyname"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">企业性质:</TD>
						<TD width="35%" class="listline"><s:textfield name="workexp.character"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">职务:</TD>
						<TD width="35%" class="listline"><s:textfield name="workexp.duty"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">开始时间:</TD>
						<TD width="35%" class="listline"><jscalendar:jscalendar name="workexp.begindate" format="%Y-%m-%d" showstime="false"  value=									"${workexp.begindate}"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">结束时间:</TD>
						<TD width="35%" class="listline"><jscalendar:jscalendar name="workexp.enddate" format="%Y-%m-%d" showstime="false"  value=									"${workexp.enddate}"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">薪水:</TD>
						<TD width="35%" class="listline"><s:textfield name="workexp.salary"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">离职原因:</TD>
						<TD width="35%" class="listline"><s:textfield name="workexp.leavereason"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">证明人:</TD>
						<TD width="35%" class="listline"><s:textfield name="workexp.attesterman"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">证明人职务:</TD>
						<TD width="35%" class="listline"><s:textfield name="workexp.attestermanduty"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">证明人电话:</TD>
						<TD width="35%" class="listline"><s:textfield name="workexp.attestermanphone"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">工作职责和突出业绩:</TD>
						<TD width="35%" class="listline"><s:textarea  name="workexp.achievement" cols="30" rows="5"/></TD>
					</TR>                    
                    
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
                        <INPUT name="back2"  type=button   class="botton" onClick="javascript:history.back(-1)" value="返回">		      </TD>
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
