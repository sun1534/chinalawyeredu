/**
 * MailSender.java
 */

package com.uu800.webbase.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author 华锋 2008-6-2 下午10:43:59
 * 
 */
public class MailUtil {
	private static final Log LOG = LogFactory.getLog(MailUtil.class);

//	private static String SMTP_HOST = com.uu800.admin.base.service.SysDictionaryService.SYS_PARAMS_MAP.get("smtphost");
//	private static String SMTP_USER = com.uu800.admin.base.service.SysDictionaryService.SYS_PARAMS_MAP.get("smtpuser");
//	private static String SMTP_PWD = com.uu800.admin.base.service.SysDictionaryService.SYS_PARAMS_MAP.get("smtppwd");
//	private static String SMTP_SEND_FROM = com.uu800.admin.base.service.SysDictionaryService.SYS_PARAMS_MAP
//			.get("smtp_send_from");
//
//	/**
//	 * 
//	 * @param subject
//	 * @param content
//	 * @param tos
//	 *            以逗号隔开的
//	 * @param ccs
//	 *            以逗号隔开的
//	 * @throws Exception
//	 */
//	public static boolean sendMail(String subject, String content, String tos, String ccs) {
//		try {
//
//			LOG.debug(SMTP_HOST + "," + SMTP_USER + "," + SMTP_PWD + "," + SMTP_SEND_FROM);
//			LOG.debug("tos==" + tos);
//			LOG.debug("ccs==" + ccs);
//			JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
//			javaMail.setHost(SMTP_HOST);
//			javaMail.setUsername(SMTP_USER);
//			javaMail.setPassword(SMTP_PWD);
//			Properties prop = new Properties();
//			prop.setProperty("mail.smtp.auth", "true");
//			javaMail.setJavaMailProperties(prop);
//			MimeMessage message = javaMail.createMimeMessage();
//			MimeMessageHelper messageHelp = new MimeMessageHelper(message, true, "utf-8");
//			messageHelp.setFrom(SMTP_SEND_FROM);
//			messageHelp.setValidateAddresses(true);
//
//			messageHelp.setTo(tos.replace(" ", "").split(","));
//
//			if (ccs != null && !ccs.equals("")) {
//				// String[] cc = new String[] { "huangping@sxit.com.cn",
//				// "yuxiaodan@sxit.com.cn" };
//				messageHelp.setCc(ccs);
//			}
//			// String[] bcc=new String[]{"liuhuafeng@sxit.com.cn"};
//			// messageHelp.setBcc(bcc);
//
//			messageHelp.setSubject(subject);
//			messageHelp.setText(content, true);
//			javaMail.send(message);
//			return true;
//		} catch (Exception e) {
//			LOG.error("邮件发送失败", e);
//			return false;
//		}
//	}
//
//	// public static void mailsender(String subject, String taskid, String[]
//	// tos, boolean iscc) throws Exception {
//	// try {
//	//
//	// URL url = new URL(CommonDatas.BILL_VIEW_URL +
//	// "/workbill/workbillViewOnly.action?taskid=" + taskid);
//	// URLConnection connection = url.openConnection();
//	// String content = readContents(connection);
//	// JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
//	// javaMail.setHost("10.10.3.1");
//	// javaMail.setPassword("123456");
//	// javaMail.setUsername("sxoa");
//	// Properties prop = new Properties();
//	// prop.setProperty("mail.smtp.auth", "true");
//	// javaMail.setJavaMailProperties(prop);
//	// MimeMessage message = javaMail.createMimeMessage();
//	// MimeMessageHelper messageHelp = new MimeMessageHelper(message, true,
//	// "utf-8");
//	// messageHelp.setFrom("sxoa@sxit.com.cn");
//	//
//	// // messageHelp.setTo("liuhuafeng@sxit.com.cn");
//	// messageHelp.setTo(tos);
//	//
//	// if (iscc) {
//	// String[] cc = new String[] { "huangping@sxit.com.cn",
//	// "yuxiaodan@sxit.com.cn" };
//	// messageHelp.setCc(cc);
//	// }
//	// // String[] bcc=new String[]{"liuhuafeng@sxit.com.cn"};
//	// // messageHelp.setBcc(bcc);
//	//
//	// messageHelp.setSubject(subject);
//	// messageHelp.setText(content, true);
//	// javaMail.send(message);
//	//
//	// }
//	// catch (Exception e) {
//	// System.out.println("邮件发送失败:::" + e.toString());
//	// e.printStackTrace();
//	// throw e;
//	// }
//	// }
//
//	private static String readContents(URLConnection connection) throws Exception {
//		BufferedReader in = null;
//		StringBuffer sb = new StringBuffer();
//		try {
//			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
//
//			String inputLine;
//			while ((inputLine = in.readLine()) != null) {
//				sb.append(inputLine);
//			}
//			in.close();
//		} catch (IOException e) {
//			LOG.error("readContents异常", e);
//			throw e;
//		}
//		return sb.toString();
//	}
}
