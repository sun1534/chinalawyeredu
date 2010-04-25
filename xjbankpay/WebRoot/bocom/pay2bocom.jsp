<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>
<%@page import="com.ccb.CCBDatas"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>交通银行支付</title>
</head>
<body>

<form name="form1" id="PaymentFormBOCOM" method="post" action="<%=request.getAttribute("payurl")%>">
<!-- 版本号-->
 			<input type = "hidden" name = "interfaceVersion" value = "<%=request.getAttribute("interfaceVersion")%>">
<!-- 商户客户号（商户编号）  -->
            <input type = "hidden" name = "merID" value = "<%=request.getAttribute("merID")%>">
<!-- 订单号 -->
            <input type = "hidden" name = "orderid" value = "<%=request.getAttribute("orderid")%>">
<!--商户订单日期 -->
            <input type = "hidden" name = "orderDate" value = "<%=request.getAttribute("orderDate")%>">
<!-- 商户订单时间 -->
            <input type = "hidden" name = "orderTime" value = "<%=request.getAttribute("orderTime")%>">
<!-- 交易类别  -->
            <input type = "hidden" name = "tranType" value = "<%=request.getAttribute("tranType")%>">
<!-- 订单金额  -->
            <input type = "hidden" name = "amount" value = "<%=request.getAttribute("amount")%>">
<!-- 订单币种  -->
            <input type = "hidden" name = "curType" value = "<%=request.getAttribute("curType")%>">
<!-- 订单内容  -->
            <input type = "hidden" name = "orderContent" value = "<%=request.getAttribute("orderContent")%>">
<!-- 商家备注 -->
            <input type = "hidden" name = "orderMono" value = "<%=request.getAttribute("orderMono")%>">
<!-- 物流配送标志  -->
            <input type = "hidden" name = "phdFlag" value = "<%=request.getAttribute("phdFlag")%>">
<!-- 通知方式  -->
            <input type = "hidden" name = "notifyType" value = "<%=request.getAttribute("notifyType")%>">
<!--主动通知URL -->
            <input type = "hidden" name = "merURL" value = "<%=request.getAttribute("merURL")%>">
<!-- 取货URL  -->
            <input type = "hidden" name = "goodsURL" value = "<%=request.getAttribute("goodsURL")%>">
<!-- 自动跳转时间 -->
            <input type = "hidden" name = "jumpSeconds" value = "<%=request.getAttribute("jumpSeconds")%>">
<!-- 商户批次号 -->
            <input type = "hidden" name = "payBatchNo" value = "<%=request.getAttribute("payBatchNo")%>">
<!-- 代理商家名称  -->
            <input type = "hidden" name = "proxyMerName" value = "<%=request.getAttribute("proxyMerName")%>">
<!-- 代理商家证件类型 -->
            <input type = "hidden" name = "proxyMerType" value = "<%=request.getAttribute("proxyMerType")%>">
<!-- 代理商家证件号码 -->
            <input type = "hidden" name = "proxyMerCredentials" value = "<%=request.getAttribute("proxyMerCredentials")%>">
<!-- 渠道编号  -->
            <input type = "hidden" name = "netType" value = "<%=request.getAttribute("netType")%>">
<!-- 商家签名 -->
            <input type = "hidden" name = "merSignMsg" value = "<%=request.getAttribute("signMsg")%>">
    
<input type="submit" value="支付"/>

</form>
<script type="text/javascript">
	
	//document.form1.submit();
</script>
</body>
</html>
