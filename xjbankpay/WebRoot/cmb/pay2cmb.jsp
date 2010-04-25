<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>
<%@page import="com.cmb.CMBDatas"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>招商银行支付</title>
</head>
<body>

<form name="form1" id="PaymentFormCMB" method="post" action="<%=com.cmb.CMBDatas.PAYURL %>"><br/>
<!-- 商城所在分行号 -->
<input type="hidden" name="BranchID" value="<%=com.cmb.CMBDatas.BRANCHID %>" />
<!-- 商城编码 -->
<input type="hidden" name="CoNo" value="<%=CMBDatas.CONO %>" />
<!-- 商城产生的订单号 -->
<input type="hidden" name="BillNo" value="<%=request.getAttribute("BillNo") %>" />
<!-- 金额 -->
<input type="hidden" name="Amount" value="<%=request.getAttribute("Amount") %>" />
<!-- 交易的日期 -->
<input type="hidden" name="Date" value="<%=request.getAttribute("Date") %>" /> 
<input type="hidden" name="MerchantUrl" value="<%=CMBDatas.NOTIFYURL %>" /> 
<input type="hidden" name="MerchantPara" value="<%=request.getAttribute("MerchantPara") %>" /> 
<input type="hidden" name="MerchantCode" value="<%=request.getAttribute("MerchantCode") %>" /> 
<!-- 
<input type="submit" value="支付"/>
-->
</form>
<script type="text/javascript">
	document.form1.submit();
</script>
</body>
</html>
