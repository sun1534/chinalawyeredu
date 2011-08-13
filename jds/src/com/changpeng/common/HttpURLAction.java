package com.changpeng.common;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import java.net.*;

import com.sxit.common.action.AbstractAction;
import java.io.*;
public class HttpURLAction extends AbstractAction{
	private String call_url;
	private String cmdType;
	private String Extid;
	private String Agentid;
	private String data;	
	private boolean result;
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getCmdType() {
		return cmdType;
	}
	public String getExtid() {
		return Extid;
	}
	public String getAgentid() {
		return Agentid;
	}
	public String getData() {
		return data;
	}

	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}
	public void setExtid(String extid) {
		Extid = extid;
	}
	public void setAgentid(String agentid) {
		Agentid = agentid;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCall_url() {
		return call_url;
	}
	public void setCall_url(String call_url) {
		this.call_url = call_url;
	}
	public void query() throws IOException{
		URL url = null;
	    HttpURLConnection httpurlconnection = null;
	    try{

	      String ext="?cmdType="+cmdType+"&Extid="+Extid+"&Agentid="+Agentid+"&type=3";
	      if(data!=null&&!"".equals(data))
	    	  ext+="&data="+data;
	      
	      LOG.info("request:"+call_url+ext);
	      url = new URL(call_url+ext);

	      httpurlconnection = (HttpURLConnection) url.openConnection();
	      httpurlconnection.setReadTimeout(2000); //设置2秒超时
	      httpurlconnection.setDoOutput(true);
	      httpurlconnection.setRequestMethod("GET");
	     
	      OutputStream out=httpurlconnection.getOutputStream();
	      out.write(ext.getBytes());
	      out.flush();
	      out.close();
	     
	     InputStream response = httpurlconnection.getInputStream();
	      byte[] buffer = new byte[256];
	      int numberRead;
	      StringBuffer bf=new StringBuffer();
	      while ((numberRead = response.read(buffer)) >= 0) {
	        	bf.append(new String(buffer,0,numberRead));
	      }
	      response.close();
	      //LOG.info("返回消息：\r\n"+bf.toString());
	    }finally
	    {
	      if(httpurlconnection!=null)
	        httpurlconnection.disconnect();
	    }
	}


	@Override
	protected String go() throws JDBCException, HibernateException {
		try {
			query();
			result=true;
		} catch (IOException e) {
			LOG.error(e.getMessage());
			result=false;
		}
		return SUCCESS;
	}
	
}
