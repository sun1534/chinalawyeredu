<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
<head>
<title>退款录入测试</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
String operator = request.getParameter("operator");
String order = request.getParameter("order");
String orderdate = request.getParameter("date");
String amount = request.getParameter("amount");
String comment = request.getParameter("comment");
String code ;
String err ;
String msg ;
BOCOMB2CClient client = new BOCOMB2CClient();
int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");
if (ret != 0){  //初始化失败
		out.print("初始化失败,错误信息："+client.getLastErr());
}else {
	BOCOMB2COPReply rep = client.Refund( operator,order,orderdate,amount,comment); //退款录入
	if (rep == null){  
		err = client.getLastErr();
		out.print("交易错误信息："+err + "<br>");
	}else{
		code = rep.getRetCode(); 	//得到交易返回码
		err = rep.getLastErr();
		msg = rep.getErrorMessage();
		out.print("交易返回码："+code + "<br>");
		out.print("交易错误信息："+msg + "<br>");
		if("0".equals(code)){//表示交易成功
			out.print("<br>------------------------<br>");
			OpResult opr = rep.getOpResult();
			String serial = opr.getValueByName ("serial"); 		//退款流水号
			String account = opr.getValueByName ("account"); 	//退款账号
	
			out.print("退款流水号"+serial);
			out.print("<br>");
			out.print("退款账号"+account);
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
