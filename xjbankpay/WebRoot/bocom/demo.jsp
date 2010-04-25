<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>交通银行支付</title>
</head>
<body>
<form id="PaymentFormBOCOM" method="post" action="bocompay"><br/>
<table>
<tr><td>订单号:</td><td><input type="text" name="orderid"/></td></tr> 
<tr><td>订单金额:</td><td><input type="text" name="amount" size="8"/>分 </td></tr> 
<tr><td>通知地址:</td><td><input type="text" name="notifyurl" size="25"/></td></tr> 
<tr><td>订单内容说明:</td><td><input type="text" name="orderContent" size="25"/>（不超过50个汉字）</td></tr> 
<tr><td>商家备注信息:</td><td><input type="text" name="orderMono" size="25"/>（不超过50个汉字）</td></tr> 

<tr><td colspan="2" align="center"><input type="submit" value="支付"/></td></tr>
</table>
</form>
</body>
</html>
