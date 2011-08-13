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
              <span class="sort-title">诉讼案件&gt;&gt;案件开庭信息录入
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
	                    <TD colspan="4">案件开庭信息
	                      <s:hidden name="caseid"/>
	                      <s:hidden name="actionid"/>
	                      <s:hidden name="nodeid"/>
	                      <s:hidden name="tonext"/>
	                      <s:hidden name="type"/>
	                       <s:hidden name="isedit"/>
	                      
	                    </TD>
	                </TR>
					<TR class="listheadline">
	                    <TD>开庭日期：</TD>
	                     <TD class="listline">
	                     <jscalendar:jscalendar name="kaiting.kaitingdate" format="%Y-%m-%d" showstime="false"/>
                         </TD>
	                      <TD>开庭时间：</TD>
	                       <TD class="listline"><s:textfield name="kaiting.kaitingtime" size="8"/>(9:30的形式)</TD>
	                </TR>
	                <TR class="listheadline">
	                    <TD>开庭律师：</TD>
	                     <TD class="listline" colspan="3"><s:textfield name="kaiting.kaitinglawyer"/></TD>
	                    
	                </TR>
	                <TR class="listheadline">
	                    <TD>受理机构：</TD>
	                     <TD class="listline"><s:textfield name="lianinfo.jigou"/></TD>
	                      <TD>地址：</TD>
	                       <TD class="listline"><s:textfield name="kaiting.address" size="25"/></TD>
	                </TR>
	            <TR class="listheadline">
	                    <TD>法官：</TD>
	                     <TD class="listline"><s:textfield name="lianinfo.faguan"/></TD>
	                      <TD>联系方式：</TD>
	                       <TD class="listline"><s:textfield name="lianinfo.faguanlinkphone"/></TD>
	                </TR>
	                     <TR class="listheadline">
	                    <TD>法官助理：</TD>
	                     <TD class="listline"><s:textfield name="lianinfo.zhuli"/></TD>
	                      <TD>助理联系方式：</TD>
	                       <TD class="listline"><s:textfield name="lianinfo.zhulilinkphone"/></TD>
	                </TR>
	                <TR class="listheadline">
	                    <TD>诉讼公告费：</TD>
	                     <TD class="listline"><s:textfield name="kaiting.susonggonggaofee" size="8"/>(只能输入数字)</TD>
	                      <TD>公告时间：</TD>
	                       <TD class="listline">
	                       <jscalendar:jscalendar name="kaiting.gonggaodate" format="%Y-%m-%d" showstime="false"/>
	                        </TD>
	                </TR>
	                <TR class="listheadline">
	                    <TD colspan="2" align="right">是否生成领开庭传票绩效</TD>
	                     <TD class="listline" colspan="2">
	                     <s:radio name="chuanpiaojixiao" list="#{'1':'记录','0':'不记录'}"/>
	                     </TD>
	                    
	                </TR>
                 	
                <tr bgcolor="#CCCCCC">
                  <td align="center" colspan="4">
                    <input name="insert" type="submit" class="botton" value="保存">
            &nbsp;
                    <input name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
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
