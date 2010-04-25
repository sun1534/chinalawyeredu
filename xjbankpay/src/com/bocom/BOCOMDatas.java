/**
 * B2CUtil.java
 */
package com.bocom;

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
public class BOCOMDatas {

	private static Log LOG = LogFactory.getLog(BOCOMDatas.class);

//	public static String MERCHANTID;// 商户代码
/**
 * 接口版本
 */
	public static String INTERFACE_VERSION="1.0.0.0";
	/**
	 * 订单币种 
	 */
	public static String CURTYPE="CNY";
/**
 * 交易类别  Y  tranType  1  0  B2C 
 */
	public static String TRANTYPE="0";

//	物流配送标志  N phdFlag  1  0 非物流 1 物流配送 
	
	public static String PHD_FLAG="0";
//	12.  通知方式  Y notifyType  1  0 不通知 1 通知 2 转页面
	public static String NOTIFY_TYPE="1";
//	渠道编号  Y netType  1  固定填0:（html 渠道） 
	public static String NETTYPE = "0";
	
public static String NOTIFYURL="";

public static String JUMP_SECONDS="0";

	static {
		try {
			Properties p = new Properties();
			p.load(BOCOMDatas.class.getResourceAsStream("bocom.properties"));
//			MERCHANTID = p.getProperty("merchantid");
			NOTIFYURL = p.getProperty("notifyurl");
			JUMP_SECONDS = p.getProperty("jumpSeconds");
		} catch (Exception e) {
			LOG.error("交通银行参数初始化失败!", e);
		}
	}
}
