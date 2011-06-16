/**
 * SmsSend.java
 */
package sms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import service.SendService;

import common.Globals;

import entity.LogMtsend;
import entity.TmpMtsend;

/**
 * @author 刘哈哈 Mar 30, 20115:07:10 PM
 * 
 * 这个只是插到数据库里，另外一个定时器，每5秒钟扫描那个临时表来处理
 * 
 */
public class Sms {

	private static Log LOG = LogFactory.getLog(Sms.class);


	public static String sendSms(String mobile, String content, String type) {
		if (mobile == null || mobile.equals("") || mobile.length() != 11)
			return "-3";
		if (content == null || content.equals(""))
			return "-4";
		String result = "0";

		SendService sendService = (SendService) Globals.getWebBean("sendService");
		
		sendService.sendSms(mobile, content, result,type);
		
//		LogMtsend mtsend = new LogMtsend();
//		mtsend.setContent(content);
//		mtsend.setMobile(mobile);
//		mtsend.setResult(result);
//		mtsend.setType(type);
//		mtsend.setSendTime(new java.sql.Timestamp(System.currentTimeMillis()));
//		sendService.save(mtsend);
//
//		TmpMtsend tmp = new TmpMtsend();
//		tmp.setContent(content);
//		tmp.setMobile(mobile);
//		tmp.setResult(result);
//		tmp.setType(type);
//		tmp.setSendTime(new java.sql.Timestamp(System.currentTimeMillis()));
//		sendService.save(tmp);

		return result;
	}

}