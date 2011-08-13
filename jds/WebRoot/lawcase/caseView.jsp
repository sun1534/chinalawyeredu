<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%

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
<TABLE width="90%" border=0  align=center cellPadding=0 cellSpacing=5 >
  
  
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF>
    <div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
        	
        
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
			  <br>			  
             <table width="100%"  border=0 align=center cellpadding=3 cellspacing=1 >
              <tbody>
				<s:hidden name="pagenumber" value="${pagenumber}"/>
					<TR class="listline">
	                    <TD colspan="12" class="sort-title">基本信息</TD>
	                </TR>
                 	<tr>
                      <td class="listheadline" width="15%">所在银行：</td>
                      <TD class="listline"  width="35%">${lawcase.bank }</TD>
                      <td class="listheadline"  width="15%">所在支行：</td>
                      <TD class="listline"  width="35%">${lawcase.bankbranch }</TD>
                      
				    </tr>
				    <tr>
                      <td class="listheadline">银行分管人：</td>
                      <TD class="listline">${lawcase.bankadmin}</TD>
                      <td class="listheadline">合同编号：</td>
                      <TD class="listline">${lawcase.contractno}</TD>
                    </tr>
                     <tr>
                      <td class="listheadline">案件诉讼承办人：</td>
                      <TD class="listline" colspan="3">
                      <s:if test="lawcase.susongworkid==0">
                     <font color='red'>还未分配</font>
                      </s:if>
                      <s:else> ${lawcase.susongworkname}
                      </s:else>
                      </TD>
                   
                    </tr>
                      <tr>
                      <td class="listheadline">案件执行承办人：</td>
                      <TD class="listline" colspan="3">
                      <s:if test="lawcase.zhixingworkid==0">
                     <font color='red'>还未分配</font>
                      </s:if>
                      <s:else> ${lawcase.zhixingworkname}
                      </s:else>
                      </TD>
                   
                    </tr>
                    <tr>
					  <td class="listheadline">委托日期：</td>
                      <td class="listline">
                   		${lawcase.thedate}
                      </td>
                      <td class="listheadline">重复查询日期：</td>
                      <TD class="listline" >
                   		${lawcase.requerytime}
					  </TD>
                    </tr>
                    
                  	 <TR class="listline">
                    	<TD colSpan=12 class="sort-title">借款人信息</TD>
                  	</TR>
                    <tr>
                      <td class="listheadline">借款人姓名：</td>
                      <td class="listline">${lawcase.jiekuanren.jiekuanren}</td>
                      <td class="listheadline">担保人（抵押人/保证人）：</td>
                      <td  class="listline" >${lawcase.jiekuanren.danbaoren}</td>
                    </tr>
                    <tr>
                       <td class="listheadline">身份证号码：</td>
                      <td  class="listline" >${lawcase.jiekuanren.theidcard}</td>
                      <td class="listheadline">贷款帐号：</td>
                      <td class="listline" >${lawcase.jiekuanren.bankno}</td>
                    </tr>
                    <tr>
                      <td class="listheadline">电话号码1：</td>
                      <td class="listline">${lawcase.jiekuanren.thephone1}</td>
                      <td class="listheadline">电话号码2：</td>
                      <td class="listline">${lawcase.jiekuanren.thephone2}</td>
                    </tr>
                    <tr>
                      <td class="listheadline">贷款类型：</td>
                      <td class="listline">${lawcase.jiekuanren.jiekuantype}</td>
                      <td class="listheadline">担保方式：</td>
                      <td  class="listline">${lawcase.jiekuanren.danbaotype}</td>
                    </tr>
                    <tr>
                      <td class="listheadline">贷款金额：</td>
                      <td class="listline">${lawcase.jiekuanren.howmuch}</td>
                      <td class="listheadline">贷款余额：</td>
                      <td  class="listline">${lawcase.jiekuanren.remain}</td>
                    </tr>
                    <tr>
                      <td class="listheadline">贷款起始日期：</td>
                      <td class="listline">
                      ${lawcase.jiekuanren.startdate}
                      </td>
                      <td class="listheadline">贷款终止日期：</td>
                      <td  class="listline">
                  ${lawcase.jiekuanren.enddate}  
                     </td>
                    </tr>
                    
                <!-- 
                <tr bgcolor="#CCCCCC">
                  <td colspan="4" align="center">
                    <input name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
                  </td>
                </tr> -->
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
