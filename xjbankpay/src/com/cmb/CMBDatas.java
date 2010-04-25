/**
 * B2CUtil.java
 */
package com.cmb;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;





/**
 * 招行支付的相关参数
 * 
 * @author 华锋
 * Mar 28, 201011:00:21 PM
 *
 */
public class CMBDatas {

	private static Log LOG = LogFactory.getLog(CMBDatas.class);
	
	public static String BRANCHID;//商户开户行id
	public static String CONO;//商户号
	
	public static String PAYURL;//支付的url
	
	public static String KEYVALUE;
	public static String NOTIFYURL;
	static{
		try{
			Properties p=new Properties();
			p.load(CMBDatas.class.getResourceAsStream("cmb.properties"));			
			BRANCHID=p.getProperty("branchid");			
			CONO=p.getProperty("cono");	
			PAYURL=p.getProperty("payyurl");	
			KEYVALUE=p.getProperty("key");	
			NOTIFYURL=p.getProperty("notifyurl");	
		}catch(Exception e){
			LOG.error("招行参数初始化失败!",e);
		}
	}
}
