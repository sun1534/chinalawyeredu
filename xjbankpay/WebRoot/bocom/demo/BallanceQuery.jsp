<%@ page import="com.bocom.netpay.b2cAPI.*" %>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<html>
<head>
<title>结算帐户查询测试</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<%
	String code,err,msg;
	BOCOMB2CClient client = new BOCOMB2CClient();
	int ret = client.initialize("C:\\bocommjava\\ini\\B2CMerchant.xml");//该代码只需调用一次
	if (ret != 0){  //初始化失败
		out.print("初始化失败,错误信息："+client.getLastErr());
	}
	else{
		BOCOMB2COPReply rep = client.queryAccountBalance(); //结算帐户查询
		if (rep == null){  
			err = client.getLastErr();
			out.print("交易错误信息："+err + "<br>");
		}
		else{
			code = rep.getRetCode(); //得到交易返回码
			msg = rep.getErrorMessage();
			out.print("交易返回码："+code + "<br>");
			out.print("交易错误信息："+msg + "<br>");
			if("0".equals(code)){//表示交易成功
				out.print("<br>------------------------<br>");	
				OpResult opr = rep.getOpResult();
				String accountNo = opr.getValueByName("settAccount") ;		//得到账号
				String accountName = opr.getValueByName("accountName"); 	//得到账号名称
				String currType = opr.getValueByName("currType") ;			//得到币种
				String currBalance = opr.getValueByName("currBalance") ;	//得到当前余额
				String validBalance = opr.getValueByName("validBalance") ;	//得到可用余额
				out.print("账号:"+accountNo);
				out.print("<br>");
				out.print("账号名称:"+accountName);
				out.print("<br>");
				out.print("币种:"+currType);
				out.print("<br>");
				out.print("当前余额:"+currBalance);
				out.print("<br>");
				out.print("可用余额:"+validBalance);
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
