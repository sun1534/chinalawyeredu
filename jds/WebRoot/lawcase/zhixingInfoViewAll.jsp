<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>

<%
/**
 * 查看执行阶段录入的所有信息
 **/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">

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
              <span class="sort-title">诉讼案件&gt;&gt;案件执行阶段信息汇总
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
                <s:form name="form1" action="lianinfoView" validate="true" method="post">
			      
			        <TR class="listheadline">
	                    <TD colspan="4">执行信息
	                    </TD>
	                </TR>
					<TR class="listheadline">
	                    <TD width="30%">执行立案时间：</TD>
	                     <TD class="listline" colspan="3">
	                    ${zhixinginfo.zhixingliandate}
                         </TD>
	                                 
	                </TR>
	                <TR class="listheadline">
	                  
	                      <TD>执行月份：</TD>
	                       <TD class="listline" colspan="3">
	                      ${zhixinginfo.zhixingmonth}
	                      </TD>	                       
	                </TR>
	                <TR class="listheadline">
	                    <TD>执行案号：</TD>
	                    <TD class="listline" colspan="3">
	                     ${zhixinginfo.zhixingno}
	                     </TD>
	                </TR>
	                <TR class="listheadline">
	                    <TD>抵押物地址：</TD>
	                     <TD class="listline" colspan="3">
	                      ${zhixinginfo.diyawuaddress}
	                      </TD>
	                </TR>
	                 <TR class="listheadline">
	                    <TD colspan="4">评估信息
	                    </TD>
	                </TR>
	                <TR class="listheadline">
	                    <TD>评估时间：</TD>
	                     <TD class="listline">
	                   ${pingguinfo.pinggudate}
                         </TD>
	                      <TD>评估值：</TD>
	                       <TD class="listline">
	                      ${pingguinfo.pingguzhi}
	                      </TD>	                       
	                </TR>
	                <TR class="listheadline">
	                    <TD>评估费：</TD>
	                    <TD class="listline" >
	                     ${pingguinfo.pinggufee}
	                     </TD>
	                          <TD>开锁费：</TD>
	                    <TD class="listline" >
	                     ${pingguinfo.kaisuofee}
	                     </TD>
	                </TR>
	                
	                 <TR class="listheadline">
	                    <TD colspan="4">拍卖信息
	                    </TD>
	                </TR>
                 	<TR class="listheadline">
	                    <TD width="25%">拍卖时间：</TD>
	                     <TD class="listline" colspan="3">
	                     ${paimaiinfo.paimaidate}
                         </TD>
	                                    
	                </TR>
	                <TR class="listheadline">
	                    <TD>成交价：</TD>
	                    <TD class="listline" colspan="3">
	                     ${paimaiinfo.chengjiaojia}
	                     </TD>
	                  </TR>
	                  <TR class="listheadline">
	                    <TD>拍卖底价：</TD>
	                       <TD class="listline" colspan="3">
	                    ${paimaiinfo.paimaidijia}
	                      </TD>
	                </TR>
             <!--    <tr bgcolor="#CCCCCC">
                  <td align="center" colspan="4">
                  
                    <input name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
                  </td>
                </tr>
                -->
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
