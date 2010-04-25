<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=GBK"
pageEncoding="GBK"
%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="cn.com.infosec.icbc.ReturnValue" %>

<META http-equiv="Content-Type" content="text/html; charset=GBK">
<TITLE>javaApiDemo.jsp</TITLE>
</HEAD>
<BODY bgcolor=Silver>
<%
String tranData = "this is a test";
out.println("<font face='Arial' size='4' color='Green'>明文：</font>"+tranData+"<br>");
String password = "12345678";
try{
	byte[] byteSrc = tranData.getBytes();
	char[] keyPass = password.toCharArray();
	
	FileInputStream in1 = new FileInputStream("d:\\b2buser.crt");
	byte[] bcert = new byte[in1.available()];
	in1.read(bcert);
	in1.close();
	FileInputStream in2 = new FileInputStream("d:\\b2buser.key");
	byte[] bkey = new byte[in2.available()];
	in2.read(bkey);
	in2.close();
	

    byte[] sign =ReturnValue.sign(byteSrc,byteSrc.length,bkey,keyPass);
    if (sign==null) {
    	out.println("<font face='Arial' size='4' color='Red'>签名失败,签名返回为空。<br>请检查证书私钥和私钥保护口令是否正确。</font><br>");
    }else{
    	out.println("<font face='Arial' size='4' color='Green'>签名成功</font><br>");
   
	    byte[] EncSign = ReturnValue.base64enc(sign);
	    String SignMsgBase64=new String(EncSign).toString();
	    out.println("<font face='Arial' size='4' color='Green'>签名信息BASE64编码：</font>"+SignMsgBase64.substring(0,100)+"...<br>");
	    
		byte[] EncCert=ReturnValue.base64enc(bcert);
		String CertBase64=new String(EncCert).toString();
		out.println("<font face='Arial' size='4' color='Green'>证书公钥BASE64编码：</font>"+CertBase64.substring(0,100)+"...<br>");
	
		byte[] DecSign = ReturnValue.base64dec(EncSign);
	    if (DecSign!=null){
	    	out.println("<font face='Arial' size='4' color='Green'>签名信息BASE64解码成功</font><br>");
	    	byte[] DecCert = ReturnValue.base64dec(EncCert);
	    	if (DecCert!=null){
	    		out.println("<font face='Arial' size='4' color='Green'>证书公钥BASE64解码成功</font><br>");
	    		int a=ReturnValue.verifySign(byteSrc,byteSrc.length,DecCert,DecSign);
	    		if (a==0) out.println("<font face='Arial' size='4' color='Green'>验签成功</font><br>");
	    		else out.println("<font face='Arial' size='4' color='Red'>验签失败<br>验证返回码：</font><br>"+a);	    		
	    	}else out.println("<font face='Arial' size='4' color='Red'>证书BASE64解码失败</font><br>");
	    }else out.println("<font face='Arial' size='4' color='Red'>签名信息BASE64解码失败</font><br>");	
	}
	
}catch (Exception e){
	out.println(e);
}
%>
</BODY>
</HTML>
