package com.changpeng.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class ParamIntialListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		//loadip();
	}
//	private void loadprop(){
//		InputStream in=null;
//		try{
//			Properties prop=new Properties();
//			in=ParamIntial.class.getResourceAsStream("/resource.properties");
//			prop.load(in);			
//			jxq.common.sysdata.CommonData.Resourcepath=prop.getProperty("resourcepath");
//			jxq.common.sysdata.CommonData.Staticpath=prop.getProperty("staticpath");
//			jxq.common.sysdata.CommonData.CURPID=Integer.parseInt(prop.getProperty("provinceid"));
//			jxq.common.sysdata.CommonData.CURPNAME=new String(prop.getProperty("provincename").getBytes("iso-8859-1"),"utf8");
//
//		}catch(Exception e){
//			try{
//				if(in!=null)
//			in.close();
//			}catch(Exception ee){
//			}
//		}
//	}
//	private void loadoperateprop(){
//		InputStream in;
//		try{
//			Properties prop=new Properties();
//			in=ParamIntial.class.getResourceAsStream("/operate.properties");
//			prop.load(in);
//			jxq.common.sysdata.CommonData.properties=prop;
//		}catch(Exception e){
//			
//		}
//	}
	
}
