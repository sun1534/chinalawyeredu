<%@ page import = "com.bocom.netpay.b2cAPI.*"%>

<%@ page language = "java" contentType = "text/html; charset=GBK"%>

<html>
    <head>
        <title>预订单生成测试</title>

        <meta http-equiv = "Content-Type" content = "text/html; charset=gb2312">
    </head>

    <body bgcolor = "#FFFFFF" text = "#000000">
        <%
        String         code, err, msg;

        BOCOMB2CClient client = new BOCOMB2CClient();

        int            ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml"); //该代码只需调用一次

        if (ret != 0) {                                                                 //初始化失败
            out.print("初始化失败,错误信息：" + client.getLastErr());
        }

        else {
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
        
        String phone;
        
        String period;

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

        interfaceVersion = request.getParameter("interfaceVersion");

        merID = BOCOMSetting.MerchantID;

        orderid = request.getParameter("orderid");

        orderDate = request.getParameter("orderDate");

        orderTime = request.getParameter("orderTime");

        tranType = request.getParameter("tranType");

        amount = request.getParameter("amount");

        curType = request.getParameter("curType");
        
        orderContent = request.getParameter("orderContent");
        
        orderMono = new String(request.getParameter("orderMono").getBytes(),"GB2312");
        
        phone = request.getParameter("phone");

        period = request.getParameter("period");

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
        PreOrder tran = new PreOrder(orderid,orderDate,orderTime,amount,curType,orderContent, orderMono,phone,period, phdFlag,notifyType,merURL,goodsURL,jumpSeconds,payBatchNo,proxyMerName,proxyMerType,proxyMerCredentials,netType);
        	
            BOCOMB2COPReply rep = client.createPreOrder(tran); //结算帐户查询

            if (rep == null) {
                err = client.getLastErr();

                out.print("交易错误信息：" + err + "<br>");
            }

            else {
                code = rep.getRetCode(); //得到交易返回码

                msg = rep.getErrorMessage();

                out.print("交易返回码：" + code + "<br>");

                out.print("交易错误信息：" + msg + "<br>");

                if ("0".equals(code)) { //表示交易成功
                    out.print("<br>------------------------<br>");

                }
            }
        }
        %>

        <p>
            <a href = "Index.htm">返回首页</a>
        </p>

        <p>
            &nbsp;
        </p>
    </body>
</html>
