package com.cqmm.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class HttpRequest extends NetRequest {
	private String LOG_TAG="CQMM";
	/**
	 * multipart/form-data 上传附件
	 * 通过拼接的方式构造请求内容，实现参数传输以及文件传输
	 * 
	 * @param actionUrl
	 * @param params
	 * @param files
	 * @return
	 */
	public String post(String actionUrl, Map<String, String> params, Map<String, File> files) {
		Log.d(LOG_TAG, actionUrl);
		String BOUNDARY = java.util.UUID.randomUUID().toString();
		String PREFIX = "--", LINEND = "\r\n";
		String MULTIPART_FROM_DATA = "multipart/form-data";
		String CHARSET = "UTF-8";
		String rtstr="";
		HttpURLConnection conn=null;
		try{
			
			URL uri = new URL(actionUrl);
			conn = (HttpURLConnection) uri.openConnection();
			conn.setReadTimeout(5 * 1000); // 缓存的最长时间
			conn.setDoInput(true);// 允许输入
			conn.setDoOutput(true);// 允许输出
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST");
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
	
			// 首先组拼文本类型的参数
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(PREFIX);
				sb.append(BOUNDARY);
				sb.append(LINEND);
				sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
				sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
				sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
				sb.append(LINEND);
				sb.append(entry.getValue());
				sb.append(LINEND);
			}
	
			DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
			outStream.write(sb.toString().getBytes());
			// 发送文件数据
			if (files != null)
				for (Map.Entry<String, File> file : files.entrySet()) {
					StringBuilder sb1 = new StringBuilder();
					sb1.append(PREFIX);
					sb1.append(BOUNDARY);
					sb1.append(LINEND);
					sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\""
							+ file.getKey() + "\"" + LINEND);
					sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
					sb1.append(LINEND);
					outStream.write(sb1.toString().getBytes());
	
					InputStream is = new FileInputStream(file.getValue());
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = is.read(buffer)) != -1) {
						outStream.write(buffer, 0, len);
					}
	
					is.close();
					outStream.write(LINEND.getBytes());
				}
	
			// 请求结束标志
			byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
			outStream.write(end_data);
			outStream.flush();
			// 得到响应码
			int res = conn.getResponseCode();
			InputStream in = null;
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if (res == 200) {
				in = conn.getInputStream();
				byte[] tmpb=new byte[1024];
				int len;
				while ((len = in.read(tmpb)) != -1) {
					baos.write(tmpb,0,len);
				}
			}else{
				return "-"+res;
			}
			outStream.close();
			rtstr=baos.toString();
		} catch(Exception e){
			
		} finally{
			conn.disconnect();
		}
		return rtstr;
	}

	/**
	 * 普通的http POST请求 params为form参数
	 * @param url
	 * @param params
	 * @return
	 */
	public String post(String url,Map<String, String> params) {
		Log.v(LOG_TAG, url);
		String rstr="";
		
		HttpParams httpParameters=new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, SOCKET_CONNECT_TIMEOUT);
		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		// URL
		HttpPost httppost = new HttpPost(url);
		
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			// 数据
			for (Map.Entry<String, String> entry : params.entrySet()) {
				nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response;
			response = httpclient.execute(httppost);
			int statuscode=response.getStatusLine().getStatusCode();
			if(statuscode==200){
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				InputStream in=response.getEntity().getContent();
				byte[] tmpb=new byte[1024];
				int len;
				while ((len = in.read(tmpb)) != -1) {
					baos.write(tmpb,0,len);
				}
//				Log.d(LOG_TAG, baos.toString());
				rstr=baos.toString();
			}else{
				rstr="-"+statuscode;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			rstr="-1";
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			rstr="-1";
			e.printStackTrace();
		}
		Log.d(LOG_TAG, rstr);
		return rstr;
	}

	public String post(String url, String requeststr)  {
		 
	    PrintWriter printWriter = null;   
	    BufferedReader bufferedReader = null;   
	    StringBuffer responseResult = new StringBuffer();   
	    HttpURLConnection httpURLConnection=null;
	    String rtstr="";
	    try {   
	        URL realUrl = new URL(url);   
	        // 打开和URL之间的连接   
	        httpURLConnection = (HttpURLConnection) realUrl.openConnection();   
	        httpURLConnection.setReadTimeout(SOCKET_CONNECT_TIMEOUT);
	        // 设置通用的请求属性   
	        httpURLConnection.setRequestProperty("accept", "*/*");   
	        httpURLConnection.setRequestProperty("connection", "Keep-Alive");   
//	        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(requeststr.length()));   
	        httpURLConnection.setRequestProperty("Content-Type", "text/xml");
	        // 发送POST请求必须设置如下两行   
	        httpURLConnection.setDoOutput(true);   
	        httpURLConnection.setDoInput(true);   
	        httpURLConnection.setRequestMethod("POST");
	        
//	        // 获取URLConnection对象对应的输出流   
//	        printWriter = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(),"GBK"));   
//	        // 发送请求参数   
//	        printWriter.write(requeststr);
//	        // flush输出流的缓冲   
//	        printWriter.flush();
	        
	        OutputStream out=httpURLConnection.getOutputStream();
	        out.write(requeststr.getBytes("GBK"));
	        out.flush();
	        out.close();
	        
	        // 根据ResponseCode判断连接是否成功   
	        int responseCode = httpURLConnection.getResponseCode();   
	        if (responseCode != 200) {   
	             
	        } else {   
	               
	        }
	        // 定义BufferedReader输入流来读取URL的ResponseData   
	        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"GBK"));   
	        String line;   
	        while ((line = bufferedReader.readLine()) != null) {   
	            responseResult.append(line);   
	        }
	        rtstr=responseResult.toString();
	    } catch (Exception e) {   
	           e.printStackTrace();
	    } finally {   
	        httpURLConnection.disconnect();   
	        try {   
	            if (printWriter != null) {   
	                printWriter.close();   
	            }   
	            if (bufferedReader != null) {   
	                bufferedReader.close();   
	            }   
	        } catch (IOException ex) {   
	            ex.printStackTrace();   
	        }
	    }
//	    try{
//	    	rtstr=new String(rtstr.getBytes("UTF-8"),"GBK");
//	    }catch(Exception e){}
	    return rtstr;
	}
	public byte[] gbk2utf8(String chenese){
		  char c[] = chenese.toCharArray();
		        byte [] fullByte =new byte[3*c.length];
		        for(int i=0; i<c.length; i++){
		         int m = (int)c[i];
		         String word = Integer.toBinaryString(m);
//		         System.out.println(word);
		        
		         StringBuffer sb = new StringBuffer();
		         int len = 16 - word.length();
		         //补零
		         for(int j=0; j<len; j++){
		          sb.append("0");
		         }
		         sb.append(word);
		         sb.insert(0, "1110");
		         sb.insert(8, "10");
		         sb.insert(16, "10");
		         
//		         System.out.println(sb.toString());
		         
		         String s1 = sb.substring(0, 8);          
		         String s2 = sb.substring(8, 16);          
		         String s3 = sb.substring(16);
		         
		         byte b0 = Integer.valueOf(s1, 2).byteValue();
		         byte b1 = Integer.valueOf(s2, 2).byteValue();
		         byte b2 = Integer.valueOf(s3, 2).byteValue();
		         byte[] bf = new byte[3];
		         bf[0] = b0;
		         fullByte[i*3] = bf[0];
		         bf[1] = b1;
		         fullByte[i*3+1] = bf[1];
		         bf[2] = b2;
		         fullByte[i*3+2] = bf[2];
		         
		        }
		        return fullByte;
		 }
		
	
	public String get(String actionUrl) {
		Log.v(LOG_TAG, actionUrl);
//		String BOUNDARY = java.util.UUID.randomUUID().toString();
//		String PREFIX = "--", LINEND = "\r\n";
//		String MULTIPART_FROM_DATA = "multipart/form-data";
//		String CHARSET = "UTF-8";
		String rtstr="";
		HttpURLConnection conn=null;
		try{
			URL uri = new URL(actionUrl);
			conn = (HttpURLConnection) uri.openConnection();
			conn.setReadTimeout(1 * 1000); // 缓存的最长时间
			conn.setDoInput(true);// 允许输入
			conn.setDoOutput(true);// 允许输出
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("GET");
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Charsert", "UTF-8");
//			conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
			
			// 得到响应码
			int res = conn.getResponseCode();
			
			Log.v(LOG_TAG, "RESULT CODE:"+res);
			InputStream in = null;
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if (res == 200) {
				in = conn.getInputStream();
				byte[] tmpb=new byte[1024];
				int len;
				while ((len = in.read(tmpb)) != -1) {
					baos.write(tmpb,0,len);
				}
			}else{
				return "-"+res;
			}
			
			rtstr=baos.toString();
		} catch(Exception e){
			
		} finally{
			conn.disconnect();
		}
		Log.v(LOG_TAG, "RESULT STR:"+rtstr);
		return rtstr;
	}

}
