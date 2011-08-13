package com.sxit.common.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.activation.*;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MailSend {
  private static String POP3_HOST_NAME = "mail.sxit.com.cn";
  private static String SMTP_HOST_NAME = "mail.sxit.com.cn";
  private static String SMTP_AUTH_USER = "system";
  private static String SMTP_AUTH_PWD = "sxit2007";

  private static Authenticator auth = new Authenticator() {
    public PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
    }
  };
  public static int sendMail(String toAddr, String subject,
                             String body) {
    try {
      Properties props = new Properties();
      String fromAddr = "system@sxit.com.cn";
  //    String contentType = "text/html;charset=GBK";

      //指定SMTP服务器，邮件通过它来投递
      props.put("mail.smtp.host", (String) SMTP_HOST_NAME);
      props.put("mail.smtp.auth", (String) "true");
      //Session session =Session.getDefaultInstance(props, auth);
      Session session = Session.getInstance(props, auth); //
      Message msg = new MimeMessage(session);

      //指定发信人
      msg.setFrom(new InternetAddress(fromAddr));

      //指定收件人
      //InternetAddress[] tos = {new InternetAddress(toAddr)};
      //msg.setRecipients(Message.RecipientType.TO,tos);

      //指定收件人，多人时用逗号分隔
      InternetAddress[] tos = InternetAddress.parse(toAddr);
      msg.setRecipients(Message.RecipientType.TO, tos);

      //标题//转码BASE64Encoder
//      sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//      msg.setSubject("=?GBK?B?"+enc.encode(subject.getBytes())+"?=");
//      msg.setSubject(new String(subject.getBytes("GBK"),"ISO8859-1"));
//      msg.setSubject("=?GB2312?B?"+enc.encode(subject.getBytes())+"?=");
      msg.setSubject(subject);
      // ( (MimeMessage) msg).setSubject(subject, "GBK");
      //得到中文标题for linux，windows下不用;

      //内容
     // body = new String(body.getBytes("ISO-8859-1"),"GBK");
     // body=new String(body.getBytes("UTF-8"),"GBK");
     // System.out.println(body);
     // ( (MimeMessage) msg).setText(enc.encode(subject.getBytes()),"GBK");


      Multipart mp = mp = new MimeMultipart(); //Multipart对象
      BodyPart bp = new MimeBodyPart();
      bp.setContent(
        "<meta http-equiv=Content-Type content='text/html; charset=GBK'>" +
        body, "text/html;charset=UTF-8");
       mp.addBodyPart(bp);
       msg.setContent(mp);

      //发送时间
      msg.setSentDate(new Date());

      //内容类型Content-type
      //普通文本为text/plain,html格式为text/html;charset=GBK
    //  msg.setContent(body, contentType);

      //发送
      Transport.send(msg);
      return 0;

    }
    catch (Exception e) {
      System.out.println(e);
      return 1;
    }
  }

  public MailSend() {
  }

  public static void main(String[] args) {

    String title = "您有待办件需要处理(测试一下邮件乱码)";
    String body = "";
    body = body + "测试员" + ":";
    body = body + "<p> 您在办公自动化系统中有一待办件需要处理,请打开以下连接处理.";
    body = body + "<p>打开 <a href=\"http://10.10.3.198//\" target=\"_blank\">http://10.10.3.198</a> 或直接 <a href=\"http://10.10.3.198\" target=\"_blank\">点击打开</a>";
    body = body + "<hr size=1>";
    body = body + "<p>OA系统";
    body = body + "<p>" + new java.sql.Date(System.currentTimeMillis());

    int a = MailSend.sendMail("system@sxit.com.cn", title, body);
     a = MailSend.sendMail("jiangkeyang@sxit.com.cn", title, body);
     a = MailSend.sendMail("zhangrubing@sxit.com.cn", title, body);
     a = MailSend.sendMail("gaobing@sxit.com.cn", title, body);
     a = MailSend.sendMail("chengjun@sxit.com.cn", title, body);
    System.out.println(a);
  }

}
