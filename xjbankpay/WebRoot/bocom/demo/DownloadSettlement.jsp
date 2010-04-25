<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
<head>
<title>对帐单下载测试</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
	String settleDate = request.getParameter("settleDate");
	String code,err,msg;
	BOCOMB2CClient client = new BOCOMB2CClient();
	int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");
	if (ret != 0){  //初始化失败
		out.print("初始化失败,错误信息："+client.getLastErr());
	}
	else{
		BOCOMB2COPReply rep = client.downLoadSettlement(settleDate) ;//对帐单下载
		if (rep == null){  
			err = client.getLastErr();
			out.print("交易错误信息："+err + "<br>");
		}else{
			code = rep.getRetCode(); //得到交易返回码
			err = rep.getLastErr();
			msg = rep.getErrorMessage();
			out.print("交易返回码："+code + "<br>");
			out.print("交易错误信息："+msg + "<br>");
			if("0".equals(code)){//表示交易成功
				out.print("<br>------------------------<br>");
				OpResult opr = rep.getOpResult();
				String totalSum = opr.getValueByName("totalSum"); //总金额
				String totalNumber = opr.getValueByName("totalNumber"); //总笔数
				String totalFee = opr.getValueByName("totalFee"); //总手续费
	
				out.print("总金额："+totalSum);
				out.print("<br>");
				out.print("总笔数："+totalNumber);
				out.print("<br>");
				out.print("总手续费："+totalFee);
				out.print("<br>");
				out.print("<p></p>");
			}	
		}
	}
%>
<p><a href="Index.htm">返回首页</a></p>
<p>&nbsp;</p>
</body>
</html>