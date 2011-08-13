package com.sxit.system.action;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
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

public class RoleAddFunctionsAction
    extends AbstractAction {

  private List functionlist;
  private String[] check;
  private int roleid;
  private int[] power;

  public RoleAddFunctionsAction() {
     rights="sys4,4";
  }

  protected String go() throws HibernateException {
    nextpage = "roleViewFunctions.action?roleid="+roleid;
    if ("save".equals(flag)) {
      int temppower=0;
      if(power!=null)
      {
        for (int temp : power) {
          temppower += temp;
        }
      }
      getAdd(check,roleid,temppower);
      message = "新增用户角色成功！";
      return "message";
    }
    else {
      TsysFunctionRole rolefunction =null;
      List functionidlist=new ArrayList();
      functionidlist.add("-1");
      TsysRole role=(TsysRole)getSession().get(TsysRole.class,Integer.valueOf(roleid));
      Set rolefunctions=role.getTsysFunctionRoles();
      for(Object obj:rolefunctions.toArray())
      {
        rolefunction=(TsysFunctionRole)obj;
        functionidlist.add(rolefunction.getComp_id().getTsysFunction().getFunctionid());
      }
      functionlist = getQuery()
          .setParameterList("rolefunctionids",functionidlist)
          .setCacheable(false)
          .list();

      return "input";
    }
  }

  public int getRoleid() {
    return roleid;
  }

  public void setRoleid(int roleid) {
    this.roleid = roleid;
  }

  private Query getQuery() throws org.hibernate.HibernateException {
    Query query = getSession().createQuery("from TsysFunction function where iscommon<>1 and functionid not in (:rolefunctionids) order by functionid desc");
    return query;
  }

  private boolean getAdd(String[] check,int roleid,int power) throws HibernateException {
//    String checkid;
//    checkid = "";
    TsysFunctionRole rolefunction =null;
    TsysFunctionRolePK rolefunctionpk = new TsysFunctionRolePK();
    TsysRole role = new TsysRole();
    role.setRoleid(new Integer(roleid));
    TsysFunction function = null;
    getSession().flush();
    for (int i = 0; i < check.length; i++) {
      rolefunction = new TsysFunctionRole();
      function = new TsysFunction();
      function.setFunctionid(check[i]);
      rolefunctionpk.setTsysFunction(function);
      rolefunctionpk.setTsysRole(role);
      rolefunction.setComp_id(rolefunctionpk);
      rolefunction.setPower(power);
      getSession().save(rolefunction);
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

