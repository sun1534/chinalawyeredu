<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>
<%@ page import="com.icbc.common.*,org.dom4j.*,cn.com.infosec.icbc.ReturnValue"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<style type="text/css">
td{
	font-size:10pt;	
}
</style>
</head>
<body>
<%
String merVAR=request.getParameter("merVAR");
String[] s=merVAR.split("##");
String orderid=s[0]; //订单ID
String notifyurl=s[1]; //通知地址
String notifyData=request.getParameter("notifyData");
String signMsg=request.getParameter("signMsg");
XMLUtil xml=new XMLUtil();
Element root=xml.getRootElement(new String(ReturnValue.base64dec(notifyData.getBytes())));
xml.getElementList(root);
String tranStat=xml.getValue("/B2CRes/bank/tranStat");
try{
	response.sendRedirect(notifyurl+"?orderid="+orderid+"&tranStat="+tranStat);
	//HttpUtil.sendPost(notifyurl,"orderid="+orderid+"&tranStat="+tranStat);
}catch(Exception e){
	out.println("转发通知结果异常");
}
%>
</body>
</html>
