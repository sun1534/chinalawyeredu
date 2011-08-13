package com.sxit.workflow.util;

import com.sxit.workflow.model.*;
import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.sxit.system.model.TsysUser;
import com.sxit.system.model.TsysFunction;
import com.sxit.system.model.TsysGroupUser;
import com.sxit.workflow.model.*;
/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author 张如兵
 * @version 1.0
 */
public class ProcessFunction {
  public ProcessFunction() {
  }
  /**
   * @todo 获取某业务上可以使用的流程列表
   * @param session Session
   * @param user TsysUser
   * @param functionid String
   * @return List
   */
  public static List getProcess(Session session,int businessid){
    String queryName ;
    queryName="from TwflProcess where businessid="+businessid+"  and statusid=1 order by processid desc";
    Query query = session.createQuery(queryName);
    List processlist=query.list();
    //queryName="from TwflProcess where functionid=:functionid and (usetype=0 or groupid in(:groups)) and statusid=1 order by processid desc";
    //Query query = session.createQuery(queryName);
    //List processlist=query.setString("functionid",functionid).setString("groups",groups).list();
    return processlist;
  }
  /**
   * @todo 获取流程的开始节点
   * @param session Session
   * @param process TwflProcess
   * @return int
   */
  public static TwflNode getProcessStartNode(Session session,TwflProcess process){
    Set<TwflNode> nodes=process.getTwflNodes();
    for(TwflNode node:nodes){
     if (node.getNodetype()==1)
       return node;
    }
    return null;
  }
  /**
   * @todo 判断用户是否有权限使用业务
   * @param session Session
   * @param process TwflProcess
   * @return int
   */
  public static boolean isCanUse(Session session,TsysUser user,int businessid){
   TwflBusiness business=(TwflBusiness) session.get(TwflBusiness.class,Integer.valueOf(businessid));
   if (business==null)
     return false;
   //业务是所有人使用时
   if (business.getUsetype()==1)
     return true;
   //业务是成员使用时
   if(!business.getUsers().contains(user))
      return false;
    return true;
 }

  public static void main(String[] args) {
    ProcessFunction processfunction = new ProcessFunction();
  }
}
