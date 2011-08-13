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
<jscalendar:head/>
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
              
              <span class="sort-title">诉讼案件&gt;&gt;
              <s:if test="!isedit">
              新增诉讼案件
              </s:if>
              <s:else>
              诉讼案件修改
              </s:else>
              </span>
              
                  <s:fielderror/>
                <s:actionerror/>
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
             <table width="580"  border=0 align=center cellpadding=3 cellspacing=1 >
              <tbody>
                <s:form name="form1" action="caselawCreateEdit" validate="true" method="post">
				<s:hidden name="pagenumber" value="${pagenumber}"/>
			    <s:hidden name="backurl"/>
				<s:hidden name="isedit"/>
					<TR class="listline">
	                    <TD colSpan=12 class="sort-title">基本信息</TD>
	                </TR>
                 	<tr>
                      <td class="listheadline">所在银行：</td>
                      <TD class="listline"><s:select name="lawcase.bankid" list="@com.changpeng.lawcase.util.CommanDatas@CASE_BANKS"/></TD>
                      <td class="listheadline">所在支行：</td>
                      <TD class="listline" ><s:textfield name="lawcase.bankbranch" size="15"/></TD>
                      
				    </tr>
				    <tr>
                      <td class="listheadline">银行分管人：</td>
                      <TD class="listline"><s:textfield name="lawcase.bankadmin" size="15"/></TD>
                      <td class="listheadline">合同编号：</td>
                      <TD class="listline"><s:textfield name="lawcase.contractno" size="15"/></TD>
                    </tr>
                    <tr>
					  <td class="listheadline">委托日期：</td>
                      <td class="listline">
                   		<jscalendar:jscalendar name="lawcase.thedate" format="%Y-%m-%d" showstime="false"/>
                      </td>
                      <td class="listheadline">重复查询日期：</td>
                      <TD class="listline" >
                   		<jscalendar:jscalendar name="lawcase.requerytime" format="%Y-%m-%d" showstime="false"/>
					  </TD>
                    </tr>
                    
                  	 <TR class="listline">
                    	<TD colSpan=12 class="sort-title">借款人信息</TD>
                  	</TR>
                    <tr>
                      <td class="listheadline">借款人姓名：</td>
                      <td class="listline"><s:textfield name="jiekuanren.jiekuanren"/></td>
                      <td class="listheadline">担保人（抵押人/保证人）：</td>
                      <td  class="listline" ><s:textfield name="jiekuanren.danbaoren"/></td>
                    </tr>
                    <tr>
                       <td class="listheadline">身份证号码：</td>
                      <td  class="listline" ><s:textfield name="jiekuanren.theidcard"/></td>
                      <td class="listheadline">贷款帐号：</td>
                      <td class="listline" ><s:textfield name="jiekuanren.bankno"/></td>
                    </tr>
                    <tr>
                      <td class="listheadline">电话号码1：</td>
                      <td class="listline"><s:textfield name="jiekuanren.thephone1"/></td>
                      <td class="listheadline">电话号码2：</td>
                      <td class="listline"><s:textfield name="jiekuanren.thephone2"/></td>
                    </tr>
                    <tr>
                      <td class="listheadline">贷款类型：</td>
                      <td class="listline"><s:textfield name="jiekuanren.jiekuantype"/></td>
                      <td class="listheadline">担保方式：</td>
                      <td  class="listline"><s:textfield name="jiekuanren.danbaotype"/></td>
                    </tr>
                    <tr>
                      <td class="listheadline">贷款金额：</td>
                      <td class="listline"><s:textfield name="jiekuanren.howmuch"/></td>
                      <td class="listheadline">贷款余额：</td>
                      <td  class="listline"><s:textfield name="jiekuanren.remain"/></td>
                    </tr>
                    <tr>
                      <td class="listheadline">贷款起始日期：</td>
                      <td class="listline">
                      <jscalendar:jscalendar name="jiekuanren.startdate" format="%Y-%m-%d" showstime="false"/>
                      </td>
                      <td class="listheadline">贷款终止日期：</td>
                      <td  class="listline">
                  <jscalendar:jscalendar name="jiekuanren.enddate" format="%Y-%m-%d" showstime="false"/>    
                     </td>
                    </tr>
                    
                
                <tr bgcolor="#CCCCCC">
                  <td colspan="4" align="center">
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
