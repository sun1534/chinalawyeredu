package com.sxit.workflow.action;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;
import com.sxit.system.model.*;
/**
 *
 * <p>功能： 创建用户角色</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2004-10-11</p>
 * @版本： V1.0
 * @修改：
 */

public class BusinessAddUsersAction
    extends AbstractAction {

  private List userlist;
  private String[] check;
  private int businessid;

  public BusinessAddUsersAction() {
    rights="wfl2,4";
  }

  protected String go() throws HibernateException {
    nextpage = "businessViewUsers.action?businessid="+businessid;
    if ("save".equals(flag)) {
      getAdd(check,businessid);
      message = "新增用户角色成功！";
      return "message";
    }
    else {
      TsysUser businessuser =null;
      List useridlist=new ArrayList();
      useridlist.add(Long.valueOf(-1));
      TwflBusiness business=(TwflBusiness)getSession().get(TwflBusiness.class,Integer.valueOf(businessid));
      Set businessusers=business.getUsers();
      for(Object obj:businessusers.toArray())
      {
        businessuser=(TsysUser)obj;
        useridlist.add(businessuser.getUserid());
      }
      userlist = getQuery()
          .setParameterList("businessuserids",useridlist)
          .setCacheable(false)
          .list();

      return "input";
    }
  }

  public int getBusinessid() {
    return businessid;
  }

  public void setBusinessid(int businessid) {
    this.businessid = businessid;
  }

  private Query getQuery() throws org.hibernate.HibernateException {
    Query query = getSession().createQuery("from TsysUser user where userid not in (:businessuserids) order by userid desc");
    return query;
  }

  private boolean getAdd(String[] check,int businessid) throws HibernateException {
    String checkid;
    checkid = "";
    TwflBusinessUser businessuser =null;
    TwflBusinessUserPK businessuserpk = new TwflBusinessUserPK();
    TsysUser user = null;
    getSession().flush();
    for (int i = 0; i < check.length; i++) {
      businessuser = new TwflBusinessUser();
      businessuserpk.setUserid(new Long(check[i]));
      businessuserpk.setBusinessid(businessid);
      businessuser.setComp_id(businessuserpk);
      getSession().save(businessuser);
      getSession().flush();
    }
    return true;
  }

  public List getUserlist() {
    return userlist;
  }
  public void setUserlist(List userlist) {
    this.userlist = userlist;
  }

  public String[] getCheck() {
    return check;
  }
  public void setCheck(String[] check) {
    this.check = check;
  }

}

