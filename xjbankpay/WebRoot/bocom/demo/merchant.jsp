<%@ page import = "com.bocom.netpay.b2cAPI.*"%>

<%@ page language = "java" contentType = "text/html; charset=GBK"%>

<html>
    <head>
        <title>商户订单测试</title>

        <meta http-equiv = "Content-Type" content = "text/html; charset=gb2312">
    </head>
 
    <%
        BOCOMB2CClient client = new BOCOMB2CClient();

        int            ret = client.initialize("/home/koojj/tomcat6/webapps/pay/WEB-INF/classes/com/bocom/B2CMerchant.xml"); //该代码只需调用一次

        if (ret != 0) {                                                                 //初始化失败
            out.print("初始化失败,错误信息：" + client.getLastErr());

            return;
        }

        String interfaceVersion;

        String merID;

        String orderid;

        String orderDate;

        String orderTime;

        String tranType;

        String amount;

        String curType;

        String orderContent;

        String orderMono;

        String phdFlag;

        String notifyType;

        String merURL;

        String goodsURL;

        String jumpSeconds;

        String payBatchNo;

        String proxyMerName;

        String proxyMerType;

        String proxyMerCredentials;

        String netType;

        String sourceMsg;
        
        String issBankNo;

        interfaceVersion = request.getParameter("interfaceVersion");

        merID = BOCOMSetting.MerchantID;

        orderid = request.getParameter("orderid");

        orderDate = request.getParameter("orderDate");

        orderTime = request.getParameter("orderTime");

        tranType = request.getParameter("tranType");

        amount = request.getParameter("amount");

        curType = request.getParameter("curType");

        orderContent = request.getParameter("orderContent");

        orderMono = request.getParameter("orderMono");

        phdFlag = request.getParameter("phdFlag");

        notifyType = request.getParameter("notifyType");

        merURL = request.getParameter("merURL");

        goodsURL = request.getParameter("goodsURL");

        jumpSeconds = request.getParameter("jumpSeconds");

        payBatchNo = request.getParameter("payBatchNo");

        proxyMerName = request.getParameter("proxyMerName");

        proxyMerType = request.getParameter("proxyMerType");

        proxyMerCredentials = request.getParameter("proxyMerCredentials");

        netType = request.getParameter("netType");
        
        issBankNo = request.getParameter("issBankNo");

        sourceMsg = interfaceVersion + "|" + merID + "|" + orderid + "|" + orderDate + "|" + orderTime + "|" + tranType
                        + "|" + amount + "|" + curType + "|" + orderContent + "|" + orderMono + "|" + phdFlag + "|"
                        + notifyType + "|" + merURL + "|" + goodsURL + "|" + jumpSeconds + "|" + payBatchNo + "|"
                        + proxyMerName + "|" + proxyMerType + "|" + proxyMerCredentials + "|" + netType;

        com.bocom.netpay.b2cAPI.NetSignServer nss = new com.bocom.netpay.b2cAPI.NetSignServer();

        String                    merchantDN = BOCOMSetting.MerchantCertDN;

        nss.NSSetPlainText(sourceMsg.getBytes("GBK"));

        byte bSignMsg [] = nss.NSDetachedSign(merchantDN);

        if (nss.getLastErrnum() < 0) {
            out.print("ERROR:商户端签名失败");

            return;
        }

        String signMsg = new String(bSignMsg, "GBK");
    %>

    <body bgcolor = "#FFFFFF" text = "#000000" onload = " javascript: form1.submit()">
        <form name = "form1" method = "post" action = "<%= BOCOMSetting.OrderURL %>">
            <input type = "hidden" name = "interfaceVersion" value = "<%out.print(interfaceVersion);%>">
            <input type = "hidden" name = "merID" value = "<%out.print(merID);%>">
            <input type = "hidden" name = "orderid" value = "<%out.print(orderid);%>">
            <input type = "hidden" name = "orderDate" value = "<%out.print(orderDate);%>">
            <input type = "hidden" name = "orderTime" value = "<%out.print(orderTime);%>">
            <input type = "hidden" name = "tranType" value = "<%out.print(tranType);%>">
            <input type = "hidden" name = "amount" value = "<%out.print(amount);%>">
            <input type = "hidden" name = "curType" value = "<%out.print(curType);%>">
            <input type = "hidden" name = "orderContent" value = "<%out.print(orderContent);%>">
            <input type = "hidden" name = "orderMono" value = "<%out.print(orderMono);%>">
            <input type = "hidden" name = "phdFlag" value = "<%out.print(phdFlag);%>">
            <input type = "hidden" name = "notifyType" value = "<%out.print(notifyType);%>">
            <input type = "hidden" name = "merURL" value = "<%out.print(merURL);%>">
            <input type = "hidden" name = "goodsURL" value = "<%out.print(goodsURL);%>">
            <input type = "hidden" name = "jumpSeconds" value = "<%out.print(jumpSeconds);%>">
            <input type = "hidden" name = "payBatchNo" value = "<%out.print(payBatchNo);%>">
            <input type = "hidden" name = "proxyMerName" value = "<%out.print(proxyMerName);%>">
            <input type = "hidden" name = "proxyMerType" value = "<%out.print(proxyMerType);%>">
            <input type = "hidden" name = "proxyMerCredentials" value = "<%out.print(proxyMerCredentials);%>">
            <input type = "hidden" name = "netType" value = "<%out.print(netType);%>">
            <input type = "hidden" name = "merSignMsg" value = "<%out.print(signMsg);%>">
            <input type = "hidden" name = "issBankNo" value="<%out.print(issBankNo);%>">
        </form>
    </body>
</html>
