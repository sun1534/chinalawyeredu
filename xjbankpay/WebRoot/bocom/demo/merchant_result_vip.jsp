<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
    <head>
        <title>交通银行商户测试结果页面</title>

        <meta http-equiv = "Content-Type" content = "text/html; charset=gb2312">
    </head>

    <%@ page language = "java" contentType = "text/html; charset=GBK"%>

    <body bgcolor = "#FFFFFF" text = "#000000">
        <%
               
        BOCOMB2CClient client = new BOCOMB2CClient();
        
				int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");//该代码只需调用一次
				
				if (ret != 0){  //初始化失败
				
					out.print("初始化失败,错误信息："+client.getLastErr());
					
				}
				else{
        
        out.print("商户结果页面");

        out.print("<br>");

        out.print("--------------------------");

        out.print("<br>");

        String                    notifyMsg = request.getParameter("notifyMsg");                    //获取银行通知结果
																	notifyMsg =  java.net.URLDecoder.decode(notifyMsg,"GBK");					//中文识别，指定编码格式。
        int                       lastIndex = notifyMsg.lastIndexOf("|");

        String                    signMsg = notifyMsg.substring(lastIndex + 1, notifyMsg.length()); //获取签名信息

        String                    srcMsg = notifyMsg.substring(0, lastIndex + 1);

        int                       veriyCode = -1;

        com.bocom.netpay.b2cAPI.NetSignServer nss = new com.bocom.netpay.b2cAPI.NetSignServer();

        nss.NSDetachedVerify(signMsg.getBytes("GBK"), srcMsg.getBytes("GBK"));

        veriyCode = nss.getLastErrnum();

        if (veriyCode < 0) { //验签出错
            out.print("商户端验证签名失败：return code:" + veriyCode);

            return;
        }

        java.util.StringTokenizer stName = new java.util.StringTokenizer(srcMsg, "|"); //拆解通知结果到Vector

        java.util.Vector          vc = new java.util.Vector();

        int                       i = 0;

        while (stName.hasMoreTokens()) {
            String value = (String)stName.nextElement();

            if (value.equals(""))
                value = "&nbsp;";

            vc.add(i++, value);
        }
        %>

        <table width = "75%" border = "0" cellspacing = "0" cellpadding = "0">
            <tr>
                <td width = "14%">
                    商户客户号
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(0));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    订单编号
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(1));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    交易金额
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(2));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    交易币种
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(3));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    平台批次号
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(4));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    商户批次号
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(5));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    交易日期
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(6));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    交易时间
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(7));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    交易流水号
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(8));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    交易结果
                </td>

                <td width = "86%">
                    <%
                    out.print(vc.get(9));
                    %>

                    &nbsp;[1:成功]
                </td>
            </tr>

            <tr>
                <td width = "14%">
                    手续费总额
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(10));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    银行卡类型
                </td>

                <td width = "86%">
                    <%
                    out.print(vc.get(11));
                    %>

                    &nbsp;[0:借记卡 1：准贷记卡 2:贷记卡]
                </td>
            </tr>

            <tr>
                <td width = "14%">
                    银行备注
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(12));
                    %>

                </td>
            </tr>

            <tr>
                <td width = "14%">
                    错误信息描述
                </td>

                <td width = "86%">
                    <%
                    out.print(vc.get(13));
                    %>

                </td>
            </tr>
            <tr>
                <td width = "14%">
                    IP
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(14));
                    %>

                </td>
            </tr>
            <tr>
                <td width = "14%">
                    Referer
                </td>

                <td width = "86%">

                    <%
                    out.print(vc.get(15));
                    %>

                </td>
            </tr>
        </table>
        
        <%
        }
        %>

        <p>
            &nbsp;
        </p>
    </body>
</html>
