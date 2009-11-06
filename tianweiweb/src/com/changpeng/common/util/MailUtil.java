package com.changpeng.common.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtil {
	public static void main(String[] args) {
		try {
//			System.out.println(com.sun.activation.registries.LogSupport.isLoggable());
//			String username = "hellomfq@163.com";
//			String password = "860210";
			String username = "13602680150";
			String password = "860210";
			String smtp_server = "smtp.139.com";
			String from_mail_address = username;
			String to_mail_address = "mengfanqiang@139.com";

			Authenticator auth = new PopupAuthenticator(username, password);

			Properties mailProps = new Properties();
			mailProps.put("mail.smtp.auth", "true");

			mailProps.put("username", username);

			mailProps.put("password", password);

			mailProps.put("mail.smtp.host", smtp_server);
			Session mailSession = Session.getDefaultInstance(mailProps, auth);
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from_mail_address));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(
					to_mail_address));
			message.setSubject("Mail Test");

			MimeMultipart multi = new MimeMultipart();
			BodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("电子邮件测试内容!");
			multi.addBodyPart(textBodyPart);
			message.setContent(multi);
			message.saveChanges();
			Transport.send(message);
		} catch (Exception ex) {
			System.err.println("邮件发送失败的原因是：" + ex.getMessage());
			System.err.println("具体错误原因：");
			ex.printStackTrace(System.err);
		}
	}

}

class PopupAuthenticator extends Authenticator {
	private String username;
	private String password;

	public PopupAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.username, this.password);
	}
}
