/**
 * ApiQueryServlet.java
 */
package com.icbc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.text.DateFormat;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 华锋 May 26, 20107:35:26 PM
 * 
 */
public class ApiQueryServlet extends HttpServlet {
	public void init()

	{

	}

	private static final String B2CMERID="3002EC23478693";
	private static final String MERACCT="3002016929200112801";
	private DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");

	/**
	 * 
	 * 
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String orderId = req.getParameter("orderId");// 订单号
		String orderDate = req.getParameter("orderDate");// 金额,以元为单位
		StringBuffer xmlbuffer = new StringBuffer();
		xmlbuffer.append("<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>");
		xmlbuffer.append("<ICBCAPI>");
		xmlbuffer.append("<in>");
		xmlbuffer.append("<orderNum>"+orderId+"</orderNum>");
		xmlbuffer.append("<tranDate>"+orderDate+"</tranDate>");
		xmlbuffer.append("<ShopCode>"+B2CMERID+"</ShopCode>");
		xmlbuffer.append("<ShopAccount>"+MERACCT+"</ShopAccount>");
		xmlbuffer.append("</in>");
		xmlbuffer.append("</ICBCAPI>");
		System.out.println(xmlbuffer.toString());
		String x=	java.net.URLEncoder.encode(xmlbuffer.toString(),"gbk");
		req.setAttribute("MerReqData", x);

		System.out.println(x);
		
		javax.servlet.RequestDispatcher rd = getServletContext().getRequestDispatcher("/icbc/apiquery.jsp");
		rd.forward(req, resp);

	}
	
//	 private String sendPost( String url, String param ) 
//	 	throws IOException 
//	{
//		String keyf = "d:/jxkj2b2c.pfx" ;
//	    String pass = "12345678" ;
//	    SSLSocketFactory ssf = null ;
//	
//	    PrintWriter out = null ;
//	    BufferedReader in = null ;
//	    String result111 = "" ;
//	    try
//	    {
//	        //init context
//	        SSLContext ctx=SSLContext.getInstance( "TLS" ) ;            
//	        KeyManagerFactory kmf = KeyManagerFactory.getInstance( "SunX509" ) ;
////	        KeyStore ks=KeyStore.getInstance( "PKCS12" ) ;
//	        KeyStore ks=KeyStore.getInstance( "PKCS12" ) ;
//	
//	        ks.load( new FileInputStream( keyf ),pass.toCharArray() ) ;
//	        
//	        kmf.init( ks,pass.toCharArray() ) ;
//	        
//	        ctx.init( kmf.getKeyManagers(),trustAllCerts,null ) ;
//	        
//	        System.out.println( "load keystore success." ) ;
//	        ssf=ctx.getSocketFactory() ;
//	        
//	        HttpsURLConnection.setDefaultSSLSocketFactory( ssf ) ;
//	
//	        HttpsURLConnection.setDefaultHostnameVerifier( hnv ) ;
//	        URL realUrl = new URL( url ) ;
//	        
//	        //打开和URL之间的连接
//	        HttpsURLConnection conn = ( HttpsURLConnection ) realUrl.openConnection() ;
//	        
//	        //设置通用的请求属性
//	        conn.setRequestProperty( "accept", "*/*" ) ; 
//	        conn.setRequestProperty( "connection", "Keep-Alive" ) ; 
//	        conn.setRequestProperty( "user-agent","Mozilla/4.0 ( compatible; MSIE 6.0; Windows NT 5.1; SV1)" ) ; 
//	        
//	        //发送POST请求必须设置如下两行
//	        conn.setDoOutput( true ) ;
//	        conn.setDoInput( true ) ;
//	        //获取URLConnection对象对应的输出流
//	        out = new PrintWriter( conn.getOutputStream() ) ;
//	       
//	        //发送请求参数
//         out.print( param ) ;
//	        
//	        //flush输出流的缓冲
//	        out.flush() ;
//	       
//	        //定义BufferedReader输入流来读取URL的响应
//	        in = new BufferedReader( new InputStreamReader( conn.getInputStream()  ) ) ;
//	      
//	        String line;
//	        while ( ( line = in.readLine() ) != null )
//	        {
//	        	System.out.println("==>>>"+line+"<<<<====");
//	        	result111 += "\n" + line ;
////	            System.out.println( "result===========" + result111 ) ;
//	        }
//	    }
//	    catch( Exception e )
//	    {
//	        System.out.println( "发送POST请求出现异常！" + e ) ;
//	        e.printStackTrace() ;
//	    }
//	    //使用finally块来关闭输出流、输入流
//	    finally
//	    {
//	        try
//	        {
//	            if (out != null)
//	            {
//	                out.close() ;
//	            }
//	            if ( in != null )
//	            {
//	                in.close() ;
//	            }
//	        }
//	        catch ( IOException ex )
//	        {
//	            ex.printStackTrace() ;
//	        }
//	    }
//		return result111;
//	}
}
