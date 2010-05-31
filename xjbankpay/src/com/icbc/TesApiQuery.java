package com.icbc;

import java.io.* ;
import java.net.* ;
import java.security.* ;
import java.security.cert.* ;

import javax.net.ssl.* ;

public class TesApiQuery 
{
	private static HostnameVerifier hnv = new HostnameVerifier() 
	{

		public boolean verify( String hostname, SSLSession session ) 
		{
            return true ;
		}
	} ;
	private static TrustManager[] trustAllCerts = new TrustManager[] 
	{
        new X509TrustManager() 
        {
        	 public X509Certificate[] getAcceptedIssuers() 
        	 {
                 return null ;
             }
             public void checkServerTrusted( X509Certificate[] certs, String authType ) 
             {
                 return ;
             }
             public void checkClientTrusted( X509Certificate[] certs, String authType ) 
             {
                 return ;
             }
        }
	} ;
	 public static String sendPost( String url, String param ) 
	 	throws IOException 
	{
		String keyf = "d:/jxkj2b2c.pfx" ;
//		String keyf = "d:/my.jks" ;
//		String keyf = "d:/jxkjb2c.cer" ;
	    String pass = "12345678" ;
	    SSLSocketFactory ssf = null ;
	
	    PrintWriter out = null ;
	    BufferedReader in = null ;
	    String result111 = "" ;
	    try
	    {
	        //init context
	        SSLContext ctx=SSLContext.getInstance( "TLS" ) ;            
	        KeyManagerFactory kmf = KeyManagerFactory.getInstance( "SunX509" ) ;
//	        KeyStore ks=KeyStore.getInstance( "PKCS12" ) ;
	        KeyStore ks=KeyStore.getInstance( "PKCS12" ) ;
	
	        ks.load( new FileInputStream( keyf ),pass.toCharArray() ) ;
	        
	        kmf.init( ks,pass.toCharArray() ) ;
	        
	        ctx.init( kmf.getKeyManagers(),trustAllCerts,null ) ;
	        
	        System.out.println( "load keystore success." ) ;
	        ssf=ctx.getSocketFactory() ;
	        
	        HttpsURLConnection.setDefaultSSLSocketFactory( ssf ) ;
	
	        HttpsURLConnection.setDefaultHostnameVerifier( hnv ) ;
	        URL realUrl = new URL( url ) ;
	        
	        //打开和URL之间的连接
	        HttpsURLConnection conn = ( HttpsURLConnection ) realUrl.openConnection() ;
	        
	        //设置通用的请求属性
	        conn.setRequestProperty( "accept", "*/*" ) ; 
	        conn.setRequestProperty( "connection", "Keep-Alive" ) ; 
	        conn.setRequestProperty( "user-agent","Mozilla/4.0 ( compatible; MSIE 6.0; Windows NT 5.1; SV1)" ) ; 
	        
	        //发送POST请求必须设置如下两行
	        conn.setDoOutput( true ) ;
	        conn.setDoInput( true ) ;
	        //获取URLConnection对象对应的输出流
	        out = new PrintWriter( conn.getOutputStream() ) ;
	       
	        //发送请求参数
            out.print( param ) ;
	        
	        //flush输出流的缓冲
	        out.flush() ;
	       
	        //定义BufferedReader输入流来读取URL的响应
	        in = new BufferedReader( new InputStreamReader( conn.getInputStream()  ) ) ;
	      
	        String line;
	        while ( ( line = in.readLine() ) != null )
	        {
	        	System.out.println("==>>>"+line+"<<<<====");
	        	result111 += "\n" + line ;
//	            System.out.println( "result===========" + result111 ) ;
	        }
	    }
	    catch( Exception e )
	    {
	        System.out.println( "发送POST请求出现异常！" + e ) ;
	        e.printStackTrace() ;
	    }
	    //使用finally块来关闭输出流、输入流
	    finally
	    {
	        try
	        {
	            if (out != null)
	            {
	                out.close() ;
	            }
	            if ( in != null )
	            {
	                in.close() ;
	            }
	        }
	        catch ( IOException ex )
	        {
	            ex.printStackTrace() ;
	        }
	    }
		return result111;
	}
	public static void main ( String [] args )
	{
		try 
		{
			String url="https://corporbank.icbc.com.cn/servlet/ICBCINBSEBusinessServlet";
			String ordernum="01005202114469436525";
			String orderdate="20100520";
//			String url="https://b2c.icbc.com.cn/servlet/ICBCINBSEBusinessServlet";
			String s1 = TesApiQuery.sendPost( url, "&APIName=" + "EAPI" + "&APIVersion=" + "001.001.002.001" + "&MerReqData=" + "<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?><ICBCAPI><in><orderNum>"+ordernum+"</orderNum><tranDate>"+orderdate+"</tranDate><ShopCode>3002EC23478693</ShopCode><ShopAccount>3002016929200112801</ShopAccount></in></ICBCAPI>");
			
			System.out.println(java.net.URLDecoder.decode(s1,"gbk"));
			System.out.println( "=====>>>"+s1 ) ;
		} 
		catch ( IOException e ) 
		{
			e.printStackTrace() ;
		}
	}
}