<%@ page contentType="text/html; charset=GBK" %>
<%@ page language="java"%>
<%@ page import="cn.com.infosec.icbc.ReturnValue,java.io.*"%>
<%  
String APIName="B2B";
String APIVersion="001.001.001.001";
String TranTime=new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
String Shop_code="3002EC13358181";
String MerchantURL="http://localhost:8080/ICBC/B2B/notify.jsp";
String ContractNo=TranTime;
String ContractAmt=request.getParameter("ContractAmt");
String Account_cur="001";
String JoinFlag="2";
String SendType="0";
String Shop_acc_num=request.getParameter("Shop_acc_num");
String PayeeAcct=request.getParameter("PayeeAcct");
String transdate="APIName="+APIName+"&APIVersion="+APIVersion+"&Shop_code="+Shop_code+"&MerchantURL="+MerchantURL+"&ContractNo="+ContractNo+"&ContractAmt="+ContractAmt+"&Account_cur="+Account_cur+"&JoinFlag="+JoinFlag+"&SendType="+SendType+"&TranTime="+TranTime+"&Shop_acc_num="+Shop_acc_num+"&PayeeAcct="+PayeeAcct;

String password = "12345678"; //商户私钥保护口令

byte[] byteSrc = transdate.getBytes();

char[] keyPass = password.toCharArray();

FileInputStream in = new FileInputStream("/usr/software/apache-tomcat-6.0.18/webapps/icbc/WEB-INF/b2buser.key");
byte[] bkey = new byte[in.available()];
in.read(bkey);
in.close();
byte[] sign=ReturnValue.sign(byteSrc,byteSrc.length,bkey,keyPass); //交易数据签名
String Mer_Icbc20_signstr=new String(ReturnValue.base64enc(sign)); //订单签名数据base64编码


in = new FileInputStream("/usr/software/apache-tomcat-6.0.18/webapps/icbc/WEB-INF/b2buser.crt");
byte[] bcert = new byte[in.available()];
in.read(bcert);
in.close();
String Cert=new String(ReturnValue.base64enc(bcert)); //公钥base64编码
%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>工商银行B2B支付</title>
<style type="text/css">
td{
	font-size:10pt;	
}
</style>
</head>
<body>
<form id="PaymentFormICBC" method="post" action="https://corporbank.dccnet.com.cn/servlet/ICBCINBSEBusinessServlet"><br/>
<!-- 接口名称 -->
<input type="hidden" name="APIName" value="<%=APIName%>" />
<!-- 接口版本号 -->
<input type="hidden" name="APIVersion" value="<%=APIVersion%>" />
<!-- 商户代码 -->
<input type="hidden" name="Shop_code" value="<%=Shop_code%>" />
<!-- 支付结果信息通知程序地址 -->
<input type="hidden" name="MerchantURL" value="<%=MerchantURL%>" />
<!-- 订单号 -->
<input type="hidden" name="ContractNo" value="<%=ContractNo%>" />
<!-- 订单金额 -->
<input type="hidden" name="ContractAmt" value="<%=ContractAmt%>" />
<!-- 支付币种 -->
<input type="hidden" name="Account_cur" value="<%=Account_cur%>" />
<!-- 检验联名标志 -->
<input type="hidden" name="JoinFlag" value="<%=JoinFlag%>" />
<!-- 订单签名数据 -->
<input type="hidden" name="Mer_Icbc20_signstr" value="<%=Mer_Icbc20_signstr%>" />
<!-- 商城证书数据 -->
<input type="hidden" name="Cert" value="<%=Cert%>" />
<!-- 结果发送类型 -->
<input type="hidden" name="SendType" value="<%=SendType%>" />
<!-- 交易日期时间 -->
<input type="hidden" name="TranTime" value="<%=TranTime%>" />
<!-- 商城账号 -->
<input type="hidden" name="Shop_acc_num" value="<%=Shop_acc_num%>" />
<!-- 收款单位账号 -->
<input type="hidden" name="PayeeAcct" value="<%=PayeeAcct%>" />
<table>
<tr><td>订单号</td><td><%=ContractNo%> </td></tr> 
<tr><td>订单金额</td><td><%=ContractAmt%></td></tr> 
<tr><td>商城账号</td><td><%=Shop_acc_num%></td></tr> 
<tr><td>收款单位账号</td><td><%=PayeeAcct%> </td></tr> 
<tr><td colspan="2" align="center"><input type="button" onclick="history.go(-1)" value="上一步"/>&nbsp;&nbsp;<input type="submit" value="确认"/></td></tr>
</table>
</form>
</body>
</html>
