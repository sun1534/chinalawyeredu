/**
 * B2CUtil.java
 */
package com.abc;

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
public class ABCDatas {

	private static Log LOG = LogFactory.getLog(ABCDatas.class);

	public static String NOTIFYURL;// 支付的url

	static {
		try {
			Properties p = new Properties();
			p.load(ABCDatas.class.getResourceAsStream("abc.properties"));
			NOTIFYURL = p.getProperty("notifyurl");

			LOG.info("NOTIFYURL:" + NOTIFYURL);
		} catch (Exception e) {
			LOG.error("建行参数初始化失败!", e);
		}
	}
}