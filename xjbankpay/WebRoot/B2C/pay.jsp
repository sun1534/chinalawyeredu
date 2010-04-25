<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>支付</title>
<style type="text/css">
td{
	font-size:10pt;	
}
</style>
</head>
<body>
<form id="PaymentFormICBC" method="post" action="http://211.90.75.36/icbc/B2C/b2c.jsp"><br/>
<table>
<tr><td>订单号</td><td><input type="text" name="orderid"/></td></tr> 
<tr><td>订单金额</td><td><input type="text" name="amount" size="8"/>分 </td></tr> 
<tr><td>通知地址</td><td><input type="text" name="notifyurl"/></td></tr> 
<tr><td colspan="2" align="center"><input type="submit" value="下一步"/></td></tr>
</table>
</form>
</body>
</html>
