import cn.com.infosec.icbc.ReturnValue;
import java.io.*;

public class Test {
	public static void main(String args[]) throws Exception{
//		String notifydate="PD94bWwgIHZlcnNpb249IjEuMCIgZW5jb2Rpbmc9IkdCSyIgc3RhbmRhbG9uZT0ibm8iID8+PEIyQ1Jlcz48aW50ZXJmYWNlTmFtZT5JQ0JDX1BFUkJBTktfQjJDPC9pbnRlcmZhY2VOYW1lPjxpbnRlcmZhY2VWZXJzaW9uPjEuMC4wLjc8L2ludGVyZmFjZVZlcnNpb24+PG9yZGVySW5mbz48b3JkZXJEYXRlPjIwMTAwMzEzMTIyNDE1PC9vcmRlckRhdGU+PGN1clR5cGU+MDAxPC9jdXJUeXBlPjxtZXJJRD4zMDAyRUMyMzQ0MDY5MzwvbWVySUQ+PHN1Yk9yZGVySW5mb0xpc3Q+PHN1Yk9yZGVySW5mbz48b3JkZXJpZD48L29yZGVyaWQ+PGFtb3VudD48L2Ftb3VudD48aW5zdGFsbG1lbnRUaW1lcz48L2luc3RhbGxtZW50VGltZXM+PG1lckFjY3Q+PC9tZXJBY2N0Pjx0cmFuU2VyaWFsTm8+PC90cmFuU2VyaWFsTm8+PC9zdWJPcmRlckluZm8+PC9zdWJPcmRlckluZm9MaXN0Pjwvb3JkZXJJbmZvPjxjdXN0b20+PHZlcmlmeUpvaW5GbGFnPjA8L3ZlcmlmeUpvaW5GbGFnPjxKb2luRmxhZz48L0pvaW5GbGFnPjxVc2VyTnVtPjwvVXNlck51bT48L2N1c3RvbT48YmFuaz48VHJhbkJhdGNoTm8+PC9UcmFuQmF0Y2hObz48bm90aWZ5RGF0ZT4yMDEwMDMxMzEyMjMwOTwvbm90aWZ5RGF0ZT48dHJhblN0YXQ+MjwvdHJhblN0YXQ+PGNvbW1lbnQ+v827p7fFxvq9u9LXo6E8L2NvbW1lbnQ+PC9iYW5rPjwvQjJDUmVzPg==";
//		String signMsg="PFQUGHoNroXk2eJVv/Qo96MBJISgIuedhW856mF6gWwZ9k23e7jEkEi5oPu0590LjaocIZ2v9bk6VemLOEkSt0H130Ga8iHw/f43pA7D32pr+1+ZhEM0HogxkGiuQykwdJO1ntKBycZqUSwatMHWIyBc2KMdi9swTPoGimLcXaA=";
//		
//		String password = "12345678"; //商户私钥保护口令
//
//		
//		char[] keyPass = password.toCharArray();
//
//		FileInputStream in = new FileInputStream("d:\\b2cuser.key");
//		//FileInputStream in = new FileInputStream("/usr/software/apache-tomcat-6.0.18/webapps/icbc/WEB-INF/b2cuser.key");
//		byte[] bkey = new byte[in.available()];
//		in.read(bkey);
//		in.close();
//		
//		notifydate=new String(ReturnValue.base64dec(notifydate.getBytes()));
//		System.out.println(notifydate);
//		//in = new FileInputStream("/usr/software/apache-tomcat-6.0.18/webapps/icbc/WEB-INF/b2cuser.crt");
//
//		in = new FileInputStream("d:\\b2cuser.crt");
//		byte[] bcert = new byte[in.available()];
//		in.read(bcert);
//		in.close();
//		String merCert=new String(ReturnValue.base64enc(bcert)); //公钥base64编码
//		
//		System.out.println(ReturnValue.verifySign(notifydate.getBytes(), notifydate.getBytes().length, bcert, ReturnValue.base64dec(signMsg.getBytes())));
//		

		
		for(int i=0;i<10;i++){
			B b=new B();
		}
	
	}
}
