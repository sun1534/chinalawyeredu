package com.sxit.system.action;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;

/**
 *
 * <p>功能： 创建用户权限</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2004-10-11</p>
 * @版本： V1.0
 * @修改：
 */

public class UserAddFunctionsAction
    extends AbstractAction {

  private List functionlist;
  private String[] check;
  private int userid;
  private int[] power;

  public UserAddFunctionsAction() {
    rights="sys2,4";
  }

  protected String go() throws HibernateException {
    nextpage = "userViewFunctions.action?userid="+userid;
    if ("save".equals(flag)) {
      int temppower=0;
      if(power!=null)
      {
        for (int temp : power) {
          temppower += temp;
        }
      }
      getAdd(check,userid,temppower);
      message = "新增用户角色成功！";
      return "message";
    }
    else {
      TsysFunctionUser userfunction =null;
      List functionidlist=new ArrayList();
      functionidlist.add("-1");
      TsysUser user=(TsysUser)getSession().get(TsysUser.class,Long.valueOf(userid));
      Set userfunctions=user.getTsysFunctionUsers();
      for(Object obj:userfunctions.toArray())
      {
        userfunction=(TsysFunctionUser)obj;
        functionidlist.add(userfunction.getComp_id().getTsysFunction().getFunctionid());
      }
      functionlist = getQuery()
          .setParameterList("userfunctionids",functionidlist)
          .setCacheable(false)
          .list();

      return "input";
    }
  }

  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  private Query getQuery() throws org.hibernate.HibernateException {
    Query query = getSession().createQuery("from TsysFunction function where  iscommon<>1 and functionid not in (:userfunctionids) order by functionid desc");
    return query;
  }

  private boolean getAdd(String[] check,int userid,int power) throws HibernateException {
//    String checkid;
//    checkid = "";
    TsysFunctionUser userfunction =null;
    TsysFunctionUserPK userfunctionpk = new TsysFunctionUserPK();
    TsysUser user = new TsysUser();
    user.setUserid(new Integer(userid));
    TsysFunction function = null;
    getSession().flush();
    for (int i = 0; i < check.length; i++) {
      userfunction = new TsysFunctionUser();
      function = new TsysFunction();
      function.setFunctionid(check[i]);
      userfunctionpk.setTsysFunction(function);
      userfunctionpk.setTsysUser(user);
      userfunction.setComp_id(userfunctionpk);
      userfunction.setPower(power);
      getSession().save(userfunction);
      getSession().flush();
    }
    return true;
  }

  public List getFunctionlist() {
    return functionlist;
  }
  public void setFunctionlist(List functionlist) {
    this.functionlist = functionlist;
  }

  public String[] getCheck() {
    return check;
  }
  public void setCheck(String[] check) {
    this.check = check;
  }
  public void setPower(int[] power) {
    this.power = power;
  }
}

