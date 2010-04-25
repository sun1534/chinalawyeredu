<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>工商银行B2B支付</title>
<style type="text/css">
td{
	font-size:10pt;	
}
</style>
</head>
<body>
<form id="PaymentFormICBC" method="post" action="b2bnext.jsp"><br/>
<table>

<tr><td>订单金额</td><td><input type="text" name="ContractAmt" size="8"/>分 </td></tr> 
<tr><td>商城账号</td><td><input type="text" name="Shop_acc_num"/> </td></tr> 
<tr><td>收款单位账号</td><td><input type="text" name="PayeeAcct"/> </td></tr> 
<tr><td colspan="2" align="center"><input type="submit" value="下一步"/></td></tr>
</table>
</form>
</body>
</html>
