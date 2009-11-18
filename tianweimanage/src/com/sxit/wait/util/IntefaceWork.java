package com.sxit.wait.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.BasicService;
import com.sxit.common.Globals;
/**
 * 处理短信BQQ EMAIL发送的接口
 * @author 华锋
 * Jul 6, 2009 1:19:17 AM
 *
 */
public class IntefaceWork {
//  private static final Log logger = LogFactory.getLog(IntefaceWork.class);
//  private static BasicService basicService = (BasicService) Globals.getBean("basicService");
//  public IntefaceWork() {
//  }
//  /**
//   * 创建代办事宜接口
//   * @param session Session
//   * @param user TsysUser
//   * @param subject String
//   * @param url String
//   * @param docstatus int  docstatus==1 急件 docstatus==2 办件 docstatus==3 阅件
//   * @param fromto String
//   * @param flowstep String
//   * @param target String
//   * @return int
//   */
//  public static int Sendinteface(TsysUser user,String subject,int waitid) {
//    TwatInteface inteface=new TwatInteface();
//    try{
//      inteface.setIntefaceid(waitid);
//      inteface.setUsername(user.getUsername());
//      inteface.setBqqno(user.getBqqno());
//      inteface.setMobile(user.getMobile());
//      inteface.setContent(subject);
//      inteface.setEmail(user.getEmail());
//      inteface.setStatusid(0);
//      inteface.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
//      session.save(inteface);
//      logger.info("成功创建短信发送接口数据 to 用户:"+user.getUsername());
//     }catch(org.hibernate.HibernateException e)
//     {
//      e.printStackTrace();
//      logger.info("创建短信发送接口数据失败 to 用户:"+user.getUsername());
//      return 0;
//     }
//    return inteface.getIntefaceid();
//  }
//
//  /*
//   * 功能用于处理完一个短信发送接口
//   × 调用方法
//   * com.sxit.inteface.util.InterfaceWork.EndInterface(getSession(),1,curuser);
//   */
//  public static boolean EndInterface(int intefaceid,TsysUser user) {
//    TwatInteface inteface;
//    try{
//     inteface=(TwatInteface)session.get(TwatInteface.class,new Integer(intefaceid));
//     if(inteface!=null){
////       inteface.setStatusid(8);//将这个待办事宜设置成已办
////       session.update(inteface);
//       session.delete(inteface);
//       logger.info("*删除一个短信发送接口 用户:"+user.getUsername());
//     }else{
//       logger.info("未找到该短信发送接口 用户:"+user.getUsername());
//     }
//    }catch(org.hibernate.HibernateException e)
//     {
//      e.printStackTrace();
//      logger.info("删除短信发送接口失败 用户:"+user.getUsername());
//      return false;
//     }
//    return true;
//  }

}
