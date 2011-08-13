<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script language=javascript>
<!--

function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
}
function query(){
	document.form1.action="customer3List.action";
    document.form1.submit();
}
-->
</script>
</HEAD>
<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%"><span class="sort-title">客户管理&gt;&gt;当事人客户业务数据列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="customer3ServiceList.action" method="POST">
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                 
                      <TR class="listheadline">
                      <TD>业务来源</TD>  
                      <TD>委托日期</TD>
                      <TD>银行</TD>
                      <TD>业务ID</TD>
                    
            		  <TD>详细</TD>
            
                      </TR>
<s:iterator value="serviceList" status="status">
                      <TR class=listline>
                        <TD >
                      <s:if test="servicetype==1">信用卡业务
                       </s:if>
                       <s:elseif test="servicetype==2">非诉业务
                       </s:elseif>
                       <s:elseif test="servicetype==3">诉讼业务
                       </s:elseif>
                       <s:else>未知来源
                       </s:else>
                      </TD>
                         <TD >
                      <s:if test="servicetype==1">${card.consigndate}
                       </s:if>
                       <s:elseif test="servicetype==2">${nonlaw.consigndate}
                       </s:elseif>
                       <s:elseif test="servicetype==3">未知
                       </s:elseif>
                       <s:else>未知
                       </s:else>
                      </TD>
                       <TD >
                       <s:if test="servicetype==1"><s:property value="@com.changpeng.operation.util.OperationUtil@bankMap()[card.bankid+\"\"]"/>
                       </s:if>
                       <s:elseif test="servicetype==2"><s:property value="@com.changpeng.operation.util.OperationUtil@bankMap()[nonlaw.bankid+\"\"]"/>
                       </s:elseif>
                       <s:elseif test="servicetype==3">未知
                       </s:elseif>
                       <s:else>未知
                       </s:else>
                       </TD>
                   
                         <TD >
                      <s:if test="servicetype==1">${card.creditcardid}
                       </s:if>
                       <s:elseif test="servicetype==2">${nonlaw.nonlawid}
                       </s:elseif>
                       <s:elseif test="servicetype==3">未知
                       </s:elseif>
                       <s:else>未知
                       </s:else>
                      </TD>
                      <TD >
                      <s:if test="servicetype==1"><a href="../operation/creditcardView.action?creditcardid=${card.creditcardid}&pagenumber=0">详细</a>
                       </s:if>
                       <s:elseif test="servicetype==2"><a href="../nonlaw/nonlawView.action?nonlawid=${nonlaw.nonlawid}&pagenumber=0">详细</a>
                       </s:elseif>
                       <s:elseif test="servicetype==3">未知
                       </s:elseif>
                       <s:else>未知
                       </s:else>
                      </TD>
         
                    
                  </TR>
</s:iterator>
${pagestring}
                   
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
<s:form name="pageForm" action="customer3List.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>

