/**
 * 
 */
package com.changpeng.extra.sms.util;


import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.changpeng.common.BasicService;
import com.changpeng.extra.sms.model.ExtraInterfacemtmsg;



/**
 * @author 华锋
 * 
 */
public class SmsMessage {

	private static int spnumber = 999999;

	public static void insertSms(String mobile, String content) {

		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		BasicService bs = (BasicService) wac.getBean("basicService");
		ExtraInterfacemtmsg mtmsg = new ExtraInterfacemtmsg();
		mtmsg.setSpnumber(spnumber);
		mtmsg.setSendtime((int) (System.currentTimeMillis() / 1000));
		mtmsg.setSrcnumber(mobile);// 哪个人发的
		mtmsg.setDestnumber(mobile);
		mtmsg.setMsgcontent(content);
		// 涉及到最后的那个
		mtmsg.setServicenumber("1");
		mtmsg.setSendflag((short) 0);
		bs.save(mtmsg);

	}
}
