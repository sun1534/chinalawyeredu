<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script language=javascript>
  function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
}
</script>
<jscalendar:head/>
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->

</style></HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">&nbsp; 
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%"><span class="sort-title">绩效管理&gt;&gt;律师费报费</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
     <br>
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
                <br>
               <s:form name="pageForm" action="lawfeeList" validate="true" method="post">
               <s:hidden name="pagenumber"/>
               <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class="listheadline">
                        <TD colspan="20">
                     姓名:<s:textfield name="username" size="8"/>
                    账号:<s:textfield name="creditcard" size="15"/>
                       <input type="submit" value="查询"/>
                        	</TD>
                        </TR>
                      <TR class="listheadline">
                        <TD rowspan=2>姓名</TD>
                      	<TD rowspan=2>账号</TD>
                      	<TD rowspan=2>身份证号</TD>
                      	<TD rowspan=2>委托日期</TD>
                      	<TD colspan=2 align="center">委托金额</TD>
                       <TD rowspan=2>还款情况</TD>
                       <TD colspan=2 align="center">还款金额</TD>
                       <TD colspan=2 align="center">本次报费金额</TD>
                       <TD rowspan=2>汇率</TD>
                       <TD rowspan=2>小计</TD>
                       <TD colspan=2 align="center">尚欠金额</TD>
                       <TD rowspan=2>备注</TD>
                      </TR>
                       <TR class="listheadline">
                        <TD>人民币</TD>
                      	<TD>美元</TD>
                      	 <TD>人民币</TD>
                      	<TD>美元</TD>
                      	 <TD>人民币</TD>
                      	<TD>美元</TD>
                      	 <TD>人民币</TD>
                      	<TD>美元</TD>
                      </TR>
                    </TR>
                    
                   <s:iterator value="feelist" status="status">
                    <TR class=listline>
                      <TD >${username}</TD>
                      <TD >${creditcard}</TD>
                      <TD >${idcard}</TD>
                      <TD >${consigndate}</TD>
                      <TD >${cnfee}</TD>
                      <TD >${usafee}</TD>
                      <TD > 
                      	<s:if test="repaystatus==3">备注清零</s:if>
                       	<s:elseif test="repaystatus==2">全清</s:elseif>
                     	<s:else>部分</s:else>
                      </TD>
                      <TD >${pCnfee}</TD>
                      <TD >${pUsafee}</TD>
                      <TD >${pCnfee}</TD>
                      <TD >${pUsafee}</TD>
                      <td>${rate}</td>
                      <td>${sumfee}</td>
                       <TD >${bCnfee}</TD>
                      <TD >${bUsafee}</TD>
                       <TD >${comments}</TD>
                  </TR>
				</s:iterator>
                  ${pagestring} 
                  </TBODY>
              </TABLE>
                 </s:form>
               <br>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
