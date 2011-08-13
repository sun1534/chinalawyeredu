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
		 <TD >任务ID</TD>
		  <TD >承办人</TD>
		 <TD >卡号</TD>
       	 <TD >委托银行</TD>
         <TD >客户姓名</TD>
         <TD >家庭住址</TD>
         <TD >委托日期</TD>
         <TD >透支人民币</TD>
         <TD >透支美元</TD>
         <TD >透支港元</TD>
         <TD >透支欧元</TD>
       	<TD >逾期状态</TD>
        <TD >委托类型</TD>
        <TD >委托类别</TD>
      </tr>
      
<s:iterator value="creditcardlist" status="stat">
      <TR align="center">
      	  <TD>${creditcardid}</TD>
      	  <TD>
           	<s:if test="state==1">
           		<s:property value="@com.changpeng.operation.util.OperationUtil@taskuser(creditcardid)"/>
           	</s:if>
          </TD>
          <TD x:str>${creditcard}</TD>
          <TD><s:property value="@com.changpeng.operation.util.OperationUtil@bankMap()[bankid+\"\"]"/></TD>
          <TD>${username}</TD>
          <TD>${homeaddr}</TD>
          <TD>${consigndate}</TD>
          <TD>${curcnfee}</TD>
          <TD>${curusafee}</TD>
          <TD>${curhkfee}</TD>
          <TD>${cureurfee}</TD>
          <TD>${overstat}</TD>
       	  <TD ><s:property value="@com.changpeng.operation.util.OperationUtil@consigntypeMap[consigntype]"/></TD>
          <TD ><s:property value="@com.changpeng.operation.util.OperationUtil@consignflagMap[consignflag]"/></TD>
                      	
      </TR>
      </s:iterator>   
     </table>  
</body>
</html>