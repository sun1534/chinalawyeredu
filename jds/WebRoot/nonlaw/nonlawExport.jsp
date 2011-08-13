<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String filename="cuishoujindu.xls";
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
.txt 
{
padding-top:1px; 
padding-right:1px; 
padding-left:1px; 
mso-ignore:padding; 
color:black; 
font-size:11.0pt; 
font-weight:400; 
font-style:normal; 
text-decoration:none; 
font-family:宋体; 
mso-generic-font-family:auto; 
mso-font-charset:134; 
mso-number-format:"\@";
text-align:general; 
vertical-align:middle; 
mso-background-source:auto; 
mso-pattern:auto; 
white-space:nowrap;
} 

-->
</style>
</head>
<body>

<div style="font:bold;text-align:center" >非诉催收进度表</div>
<table border="1" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;">
<tr>
    <td rowspan="2">序号</td>
     <td rowspan="2">借据号</td>
    <td rowspan="2">委托时间</td>
    <td rowspan="2">分类</td>
    <td rowspan="2">姓名</td>
    <td rowspan="2">支行</td>
    <td rowspan="2">借据号</td>
    <td rowspan="2">贷款日期</td>
    <td rowspan="2">证件号码</td>
    <td rowspan="2">贷款金额</td>
    <td rowspan="2">贷款余额</td>
    <td rowspan="2">动态逾期期数</td>
    <td rowspan="2">动态逾期金额</td>
    <td colspan="3" align="center">还款情况</td>
    <td colspan="5" align="center">催收情况</td>
    <td rowspan="2">催收建议</td>
</tr>
<tr>
	<td>还款情况</td>
	<td>还款金额</td>
	<td>还款日期</td>
	<td>催收时间</td>
	<td>电话催收</td>
	<td>律师函催收</td>
	<td>上门时间</td>
	<td>上门催收</td>
</tr>
<s:iterator value="nonlawlist" status="status">


  <tr>
    <td>${status.index+1}</td>
     <td>${duebill}</td>
    <td>${consigndate}</td>
	<td><s:property value="@com.changpeng.nonlaw.util.NonlawUtil@projectnameMap.get(projectname)"/><br></td>
    <td>${username}</td>
    <td>${bankname}</td>
    <td class="txt">${duebill}</td>
    <td>${lenddate}</td>
    <td class="txt">${idcard}</td>
    <td>${lendfee}</td>
    <td>${curbalancefee}</td>
    <td>${curoverstat}</td>
    <td>${curoverfee}</td>
    <td colspan="3">
    	<table border="1" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;">
    		<s:iterator value="tnlwRepaylogs" status="stat1">
    			<tr><td align="left">部分</td><td align="left">${fee}</td><td align="left">${repaytime}</td></tr>
    		</s:iterator>
    	</table>
    </td>
     <td colspan="5">
    	<table border="1" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;">
    		<s:iterator value="tnlwNonlawlogs" status="stat2">
    		
    			<s:if test="#stat2.index==0">
    				<s:if test="logtype==1">
    					<tr><td align="left">${logtime}</td><td align="left">${comments}</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
    				</s:if>
    				<s:else>
    					<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td align="left">${logtime}</td><td align="left">${comments}</td></tr>
    				</s:else>
    			</s:if>
    		</s:iterator>
    	</table>
    </td>
    <td>跟进</td>
</s:iterator>
</table>
</BODY>
</HTML>
