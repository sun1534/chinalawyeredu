/**
 * B2CUtil.java
 */
package com.ccb;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 招行支付的相关参数
 * 
 * @author 华锋 Mar 28, 201011:00:21 PM
 * 
 */
public class CCBDatas {

	private static Log LOG = LogFactory.getLog(CCBDatas.class);

	public static String BRANCHID;// 商户开户行id
	public static String MERCHANTID;// 商户代码

	public static String PAYURL;// 支付的url

	public static String POSID;

	public static String KEY;

	public static String TXCODE = "520100";
	public static String CURCODE = "01";
	
	public static String PUBLICKEY="";

	static {
		try {
			Properties p = new Properties();
			p.load(CCBDatas.class.getResourceAsStream("ccb.properties"));
			BRANCHID = p.getProperty("branchid");
			POSID = p.getProperty("posid");
			PAYURL = p.getProperty("payyurl");
			MERCHANTID = p.getProperty("merchantid");
			PUBLICKEY = p.getProperty("publickey");
			KEY=PUBLICKEY.substring(0,30);
			LOG.info("key:"+KEY);
		} catch (Exception e) {
			LOG.error("建行参数初始化失败!", e);
		}
	}
}
