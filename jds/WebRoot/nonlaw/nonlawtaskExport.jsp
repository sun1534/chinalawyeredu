<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String filename="tasklist.xls";
response.reset();
response.setContentType("bin;charset=utf-8"); 
response.addHeader("Content-Disposition","attachment; filename="+filename+"");
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
%>
<html xmlns:x="urn:schemas-microsoft-com:office:excel">
<head>
<style type="text/css">
<!--
body,td {
	font-size: 12px;
	line-height:16px;
	color: #000000;
}
-->
</style>
</head>
<body>
<table border="1" cellspacing="0" bordercolor="#000000" width = "80%" style="border-collapse:collapse;">
		<tr align="center">
		 <TD >借据号</TD>
		  <TD >承办人</TD>
       	  <TD >委托银行</TD>
          <TD >支行名称</td>
          <TD >客户姓名</TD>                   
          <TD >证件号码</TD>
           <TD >用户住址</TD>
           <TD >购房地址</TD>
           <TD >贷款金额</TD>
           <TD >贷款余额</TD>
            <TD>逾期利息</TD>
           <TD>逾期期数</TD>
           <TD >委托时间</TD>
      </tr>
      
<s:iterator value="nonlawlist" status="stat">
      <TR align="center">
      	  <TD>${duebill}</TD>
      	  <TD>
           	<s:if test="state==1">
           		<s:property value="@com.changpeng.nonlaw.util.NonlawUtil@taskuser(nonlawid)"/>
           	</s:if>
          </TD>
           <TD ><s:property value="@com.changpeng.operation.util.OperationUtil@bankMap()[bankid+\"\"]"/></TD>
           <TD>${bankname}</TD>  
           <TD >${username}</TD>
            <TD x:str>${idcard}</TD>
           <TD >${homeaddr}</TD>
           <TD >${buyaddr}</TD>
           <TD x:str>${lendfee}</TD>
           <TD x:str>${curbalancefee}</TD>
           <TD x:str>${accrualfee}</TD>
           <TD >${overnum}</TD>
           <TD >${consigndate}</TD>
                   
      </TR>
      </s:iterator>   
     </table>  
</body>
</html>