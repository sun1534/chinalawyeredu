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
              <span class="sort-title">诉讼案件&gt;&gt;案件执行立案信息录入
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
                <s:form name="form1" action="zhixingInfoCreateEdit" validate="true" method="post">
			      
			        <TR class="listline">
	                    <TD colspan="4"> 
	                      <s:hidden name="caseid"/>
	                      <s:hidden name="actionid"/>
	                      <s:hidden name="nodeid"/>
	                      <s:hidden name="tonext"/>
	                      <s:hidden name="isedit"/>
	                      
	                    </TD>
	                </TR>
					<TR class="listheadline">
	                    <TD width="30%">执行立案时间：</TD>
	                     <TD class="listline" colspan="3">
	                     <jscalendar:jscalendar name="zhixinginfo.zhixingliandate" format="%Y-%m-%d" showstime="false"/>
                         </TD>
	                                 
	                </TR>
	                <TR class="listheadline">
	                  
	                      <TD>执行月份：</TD>
	                       <TD class="listline" colspan="3">
	                      <s:select name="zhixinginfo.zhixingmonth" list="months"/>
	                      </TD>	                       
	                </TR>
	                <TR class="listheadline">
	                    <TD>执行案号：</TD>
	                    <TD class="listline" colspan="3">
	                     <s:textfield name="zhixinginfo.zhixingno" size="15"/>
	                     </TD>
	                </TR>
	                <TR class="listheadline">
	                    <TD>抵押物地址：</TD>
	                     <TD class="listline" colspan="3">
	                      <s:textfield name="zhixinginfo.diyawuaddress" size="35"/>
	                      </TD>
	                </TR>
	                
	                <TR class="listheadline">
	                    <TD>备注信息：</TD>
	                     <TD class="listline" colspan="3">
	                      <s:textarea name="zhixinginfo.remarks" cols="45" rows="5"/>
	                      (<font color="red">不能超过500个汉字</font>)
	                      </TD>
	                </TR>
	           <TR class="listheadline">
	                    <TD>是否记录执行承办人的制作诉状和立案绩效：</TD>
	                     <TD class="listline" colspan="3">
	                      <s:radio name="isjixiao" list="#{'0':'暂不记录','1':'记录'}"/>
	                    
	                      </TD>
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
