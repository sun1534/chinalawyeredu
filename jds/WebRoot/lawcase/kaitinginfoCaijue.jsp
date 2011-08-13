<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%
/**
 * <p>功能： 创建employee</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-03-04</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<jscalendar:head/>
<link rel="stylesheet" href="../struts/jscalendar/calendar-${curuser.style==1?"bule":""}${curuser.style==2?"brown":""}${curuser.style==3?"green":""}.css" type="text/css"/>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style>
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%">
              <span class="sort-title">诉讼案件&gt;&gt;案件审理之裁决判决信息
              </span>
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
			  <br>			  
             <table width="70%"  border=0 align=center cellpadding=3 cellspacing=1 >
              <tbody>
                <s:form name="form1" action="kaitinginfoCreateEdit" validate="true" method="post">
			      
			        <TR class="listheadline">
	                    <TD colspan="4">案件审理之裁决判决信息 
	                      <s:hidden name="caseid"/>
	                      <s:hidden name="actionid"/>
	                      <s:hidden name="nodeid"/>
	                      <s:hidden name="tonext"/>
	                      <s:hidden name="type"/>
	                      <s:hidden name="isedit"/>
	                      <s:hidden name="lianinfo.faguan"/>
	                      
	                    </TD>
	                </TR>
					<TR class="listheadline">
	                    <TD>判决日期：</TD>
	                     <TD class="listline">
	                     <jscalendar:jscalendar name="kaiting.panjuedate" format="%Y-%m-%d" showstime="false"/>
                         </TD>
	                      <TD>判决生效日期：</TD>
	                       <TD class="listline">
	                      <jscalendar:jscalendar name="kaiting.panjuevaliddate" format="%Y-%m-%d" showstime="false"/>
	                      </TD>	                       
	                </TR>
	                <TR class="listheadline">
	                    <TD>案件转接日期：</TD>
	                    <TD class="listline" colspan="3"><jscalendar:jscalendar name="kaiting.zhuanjiedate" format="%Y-%m-%d" showstime="false"/></TD>
	                </TR>
	                <TR class="listheadline">
	                    <TD>开庭结果：</TD>
	                     <TD class="listline" colspan="3">
	                       <s:textarea name="kaiting.kaitingresult" rows="5" cols="50"/></TD>	                    
	                </TR>
	         
                 	
                <tr bgcolor="#CCCCCC">
                  <td align="center" colspan="4">
                    <input name="insert" type="submit" class="botton" value="保存">
            &nbsp;
                    <input name="back" type="button" class="botton" onClick="javascript:history.back(-1)" value="返回">
                  </td>
                </tr>
                </s:form>
              </tbody>
            </table>
		    <br></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
