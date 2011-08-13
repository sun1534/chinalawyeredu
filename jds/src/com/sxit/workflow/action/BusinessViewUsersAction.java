package com.sxit.workflow.action;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;

import java.util.*;

/**
 *
 * <p>功能： 查看用户角色</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2004-10-10</p>
 * @版本： V1.0
 * @修改：
 */

public class BusinessViewUsersAction  extends AbstractAction {

  private TwflBusiness business;

  private int businessid;
  private Set businessusers;

  public BusinessViewUsersAction() {
   rights="wfl2,1";
  }

  protected String go() throws org.hibernate.HibernateException {
    business=(TwflBusiness)getSession().get(TwflBusiness.class,Integer.valueOf(businessid));
    if(business==null)
    {
      nextpage = "listBusiness.action";
      message="此用户不存在！";
      return "message";
    }
    else{
      businessusers = business.getUsers();
      return SUCCESS;
     }
   }
   public TwflBusiness getBusiness() {
           return business;
   }
  public int getBusinessid() {
    return businessid;
  }
  public void setBusinessid(int businessid) {
    this.businessid = businessid;
  }
  public Set getBusinessusers() {
    return businessusers;
  }
}
