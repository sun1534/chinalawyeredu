package com.sxit.inteface.util;

import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sxit.common.component.HibernateSession;
import com.sxit.inteface.model.TintInterface;
import com.sxit.inteface.util.InfoSend;
import com.sxit.system.model.TsysUser;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *
 * <p>功能： 处理短信,BQQ,EMAIL发送的接口</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008.5.8</p>
 * @版本： V1.0
 * @修改：
 */
public class InfoSend {
  private static final Log logger = LogFactory.getLog(InfoSend.class);
  public InfoSend() {
  }
  
 /**
  * 发送信息  (针对系统用户) 
  * @param session
  * @param user
  * @param subject
  * @param statusid  |对应的二进制 001  BQQ 010 SMS 100 EMAIL
  * @param flag  1 立即发送 0 延迟统一发送
  * @return
  */

  public static long SendInfoToUser(Session session,TsysUser user,String subject,int statusid,int flag) {
	  TintInterface inteface=new TintInterface();
	    try{
	      inteface.setUsername(user.getUsername());
	      inteface.setBqqno(user.getBqqno());
	      inteface.setMobile(user.getMobile());
	      inteface.setContent(subject);
	      inteface.setEmail(user.getEmail());
	      inteface.setStatusid(statusid);
	      inteface.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
	      session.save(inteface);
	      logger.info("成功创建短信发送接口数据 to 用户:"+user.getUsername());
	     }catch(org.hibernate.HibernateException e)
	     {
	      e.printStackTrace();
	      logger.info("创建短信发送接口数据失败 to 用户:"+user.getUsername());
	      return 0;
	     }
	    return inteface.getIntefaceid();
	  }
  
 
   /**
    * 发送邮件(立即)
    * @param session
    * @param username
    * @param email
    * @param subject
    * @return
    */

   public static long SendMail(Session session,String username,String email,String subject) {
	 	  TintInterface inteface=new TintInterface();
	 	    try{
	 	      inteface.setUsername(username);
	 	      inteface.setBqqno(""); 
	 	      inteface.setMobile(""); 
	 	      inteface.setEmail(email);
	 	      inteface.setContent(subject);	 	      
	 	      inteface.setStatusid(4);
	 	      inteface.setFlag(1);
	 	      inteface.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
	 	      session.save(inteface);
	 	      logger.info("成功创建邮件发送接口数据 to 邮件:"+email);
	 	     }catch(org.hibernate.HibernateException e)
	 	     {
	 	      e.printStackTrace();
	 	      logger.info("创建短信邮件接口数据失败 to 邮件:"+email);
	 	      return 0;
	 	     }
	 	    return inteface.getIntefaceid();
	 	  }
   /**
    * 发送BQQ(立即)
    * @param session
    * @param username
    * @param bqqno
    * @param subject
    * @return
    */

   public static long SendBQQ(Session session,String username,String bqqno,String subject) {
	 	  TintInterface inteface=new TintInterface();
	 	    try{
	 	      inteface.setUsername(username);
	 	      inteface.setBqqno(bqqno); 
	 	      inteface.setMobile(""); 
	 	      inteface.setEmail("");
	 	      inteface.setContent(subject);	 	      
	 	      inteface.setStatusid(1);
	 	      inteface.setFlag(1);
	 	      inteface.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
	 	      session.save(inteface);
	 	      logger.info("成功创建BQQ发送接口数据 to BQQ:"+bqqno);
	 	     }catch(org.hibernate.HibernateException e)
	 	     {
	 	      e.printStackTrace();
	 	      logger.info("创建短信BQQ接口数据失败 to BQQ:"+bqqno);
	 	      return 0;
	 	     }
	 	    return inteface.getIntefaceid();
	 	  }
   /**
    * 发送SMS(立即)
    * @param session
    * @param username
    * @param mobile
    * @param subject
    * @return
    */
   public static long SendSMS(Session session,String username,String mobile,String subject) {
	 	  TintInterface inteface=new TintInterface();
	 	    try{
	 	      inteface.setUsername(username);
	 	      inteface.setBqqno(""); 
	 	      inteface.setMobile(mobile); 
	 	      inteface.setEmail("");
	 	      inteface.setContent(subject);	 	      
	 	      inteface.setStatusid(2);
	 	      inteface.setFlag(1);
	 	      inteface.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
	 	      session.save(inteface);
	 	      logger.info("成功创建短信发送接口数据 to mobile:"+mobile);
	 	     }catch(org.hibernate.HibernateException e)
	 	     {
	 	      e.printStackTrace();
	 	      logger.info("创建短信发送接口数据失败 to mobile:"+mobile);
	 	      return 0;
	 	     }
	 	    return inteface.getIntefaceid();
	 	  }
  /**
   * 功能用于取消一个信息发送接口
   * @param session
   * @param intefaceid
   * @return
   * 调用方法: com.sxit.inteface.util.InfoSend.CancelSendInfo(getSession(),intefaceid);
   */

  public static boolean CancelSendInfo(Session session,long intefaceid) {
	TintInterface inteface;
    try{
    	inteface=(TintInterface)session.get(TintInterface.class,intefaceid);
     String username=inteface.getUsername();
     if(inteface!=null){
       session.delete(inteface);
       logger.info("*删除一个短信发送接口 用户:"+username);
     }else{
       logger.info("未找到该短信发送接口 用户:"+username);
     }
    }catch(org.hibernate.HibernateException e)
     {
      e.printStackTrace();
      logger.info("删除短信发送接口失败");
      return false;
     }
    return true;
  }
	public static void main(String[] args) {
		/*// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"/applicationContext.xml");
		HibernateSession hibernateSession = (HibernateSession) ctx
				.getBean("hibernateSession");
		Session session = hibernateSession.getSession();
		// ctx.gethibernateTemplate();

		TsysUser user = new TsysUser();
		TmemMember member = new TmemMember();
		user.setBqqno("2047");
		user.setEmail("zhangrubing@sxit.com.cn");
		user.setMobile("13823594056");
		user.setUsername("张如兵");

		member.setEmail("zhangrubing@sxit.com.cn");
		member.setMobile("13316927225");
		member.setUsername("张如兵");

		String subject = "内部用户测试";
		int statusid = 7;
		int flag = 1;
	
		// System.out.println(tempid);
		try {
			Transaction tx = session.beginTransaction();
			// session.save(member);
			// 测试接口 发送信息 (针对系统用户)
			subject="测试接口 发送信息 (针对系统用户)";
			long tempid = InfoSend.SendInfoToUser(session, user, subject,
					statusid, flag);
			System.out.println(tempid);
			// 测试接口 发送信息 (针对会员)
			subject="测试接口 发送信息 (针对会员)";
			tempid = InfoSend.SendInfoToMember(session, member, subject,
					statusid, flag);
			System.out.println(tempid);
			// 测试接口 发送邮件
			subject="测试接口 发送邮件";
			String username="张如兵";
			String email="zhangrubing@sxit.com.cn";
			tempid = InfoSend.SendMail(session, username, email, subject);

			System.out.println(tempid);
			// 测试接口 发送BQQ
			subject="测试接口 发送BQQ";
			String bqqno="2047";
			tempid = InfoSend.SendBQQ(session, username, bqqno, subject);

			System.out.println(tempid);
			// 测试接口 发送SMS
			subject="测试接口 发送SMS";
			String mobile="13316927225";
			tempid = InfoSend.SendSMS(session, username, mobile, subject);

			System.out.println(tempid);

			tx.commit();
			session.close();
		} catch (RuntimeException e) {
			throw e;
		}*/
	}
}
