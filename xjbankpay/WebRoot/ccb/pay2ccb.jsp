<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>
<%@page import="com.ccb.CCBDatas"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>建设银行支付</title>
</head>
<body>

<form name="form1" id="PaymentFormCCB" method="post" action="<%=com.ccb.CCBDatas.PAYURL %>">
<!-- 商户柜台代码 -->
<input type="hidden" name="PUB32" value="<%=com.ccb.CCBDatas.KEY %>" />
<!-- 商户柜台代码 -->
<input type="hidden" name="POSID" value="<%=com.ccb.CCBDatas.POSID %>" />
<!-- 商城所在分行号 -->
<input type="hidden" name="BRANCHID" value="<%=com.ccb.CCBDatas.BRANCHID %>" />
<!-- 商城编码 -->
<input type="hidden" name="MERCHANTID" value="<%=CCBDatas.MERCHANTID %>" />
<!-- 商城产生的订单号 -->
<input type="hidden" name="ORDERID" value="<%=request.getAttribute("BillNo") %>" />
<!-- 金额 -->
<input type="hidden" name="PAYMENT" value="<%=request.getAttribute("Amount") %>" />
<!-- 币种01为人民币 -->
<input type="hidden" name="CURCODE" value="<%=CCBDatas.CURCODE %>" />
<!-- 网银不处理，直接传到城综网 -->
<input type="hidden" name="REMARK1" value="<%=request.getAttribute("REMARK1") %>"/> 
<input type="hidden" name="REMARK2" value="<%=request.getAttribute("REMARK2") %>" /> 
<input type="hidden" name="TXCODE" value="<%=CCBDatas.TXCODE %>" /> 
<input type="hidden" name="MAC" value="<%=request.getAttribute("md5") %>" /> 
<!--  
<input type="submit" value="支付"/>
<input type="button" value="md5" onclick="make()"/>
 -->
</form>
<script language="JavaScript" src="md5.js"></script>
<script language="JavaScript"  src="pay.js"></script>
<script type="text/javascript">
  document.form1.submit();
</script>
</body>
</html>
