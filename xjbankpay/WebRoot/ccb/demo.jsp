<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>建行支付</title>
</head>
<body>
<form id="PaymentFormICBC" method="post" action="ccbpay"><br/>
<table>
<tr><td>订单号</td><td><input type="text" name="orderid"/></td></tr> 
<tr><td>订单金额</td><td><input type="text" name="amount" size="8"/>分 </td></tr> 
<tr><td>通知地址</td><td><input type="text" name="notifyurl"/></td></tr> 
<tr><td colspan="2" align="center"><input type="submit" value="支付"/></td></tr>
</table>
</form>
</body>
</html>
