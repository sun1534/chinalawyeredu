<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
<head>
<title>持卡人身份验证(VIP商户</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
	String card = request.getParameter("card");
	String custName = request.getParameter("custName");
	String certType = request.getParameter("certType");
	String certNo = request.getParameter("certNo");
	String code;
	String err;
	String msg;
	BOCOMB2CClient client = new BOCOMB2CClient();
	int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");
	if (ret != 0){  //初始化失败
		out.print("初始化失败,错误信息："+client.getLastErr());
	}
	else{
		BOCOMB2COPReply rep = client.verifyCustID( card, custName,certType,certNo) ;
		if (rep == null){  
			err = client.getLastErr();
			out.print("交易错误信息："+err + "<br>");
		}else{
			code = rep.getRetCode(); //得到交易返回码
			err = rep.getLastErr();
			msg = rep.getErrorMessage();
			out.print("交易返回码："+code + "<br>");
			out.print("交易错误信息："+msg + "<br>");
		}
	}
%>
<p><a href="Index.htm">返回首页</a></p>
<p>&nbsp;</p>
</body>
</html>
