package com.sxit.system.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;

/**
 *
 * <p>功能： 修改角色权限</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-11</p>
 * @版本： V1.0
 * @修改：
 */

public class FunctionChangeRolePowerAction
    extends AbstractAction {

  private String functionid;
  private int roleid;
  private TsysFunctionRole functionrole;
  private int[] power;

  public FunctionChangeRolePowerAction() {
    rights="sys5,4";
  }

  protected String go() throws HibernateException {
    if ("save".equals(flag)) {
      int temppower=0;
      if(power!=null)
      {
        for (int temp : power) {
          temppower += temp;
        }
      }
      functionrole.setPower(temppower);
      getSession().update(functionrole);
      nextpage = "functionViewPower.action?functionid=" + functionrole.getComp_id().getTsysFunction().getFunctionid();
      message="保存用户权限成功";
      return "message";
    }else{
      TsysFunctionRolePK compid = new TsysFunctionRolePK();
      TsysRole role = (TsysRole) getSession().get(TsysRole.class,
          Integer.valueOf(roleid));
      if (role == null) {
        message = "未找到此用户";
        return "message";
      }
      TsysFunction function = (TsysFunction) getSession().get(TsysFunction.class,
          functionid);
      if (function == null) {
        message = "未找到此功能";
        return "message";
      }
      compid.setTsysRole(role);
      compid.setTsysFunction(function);
      functionrole = (TsysFunctionRole) getSession().get(TsysFunctionRole.class,
          compid);
      if (functionrole == null) {
        message = "未找到此用户和此功能的权限";
        return "message";
      }
      return SUCCESS;
    }
  }
  public void setFunctionid(String functionid) {
    this.functionid = functionid;
  }
  public void setRoleid(int roleid) {
    this.roleid = roleid;
  }
  public TsysFunctionRole getFunctionrole() {
    return functionrole;
  }

  public void setFunctionrole(TsysFunctionRole functionrole) {
    this.functionrole = functionrole;
  }
  public void setPower(int[] power) {
    this.power = power;
  }
  //权限位与运算
  public boolean getPower1() {
    int power = this.functionrole.getPower();
    return 1==(power & 1);
  }
  public boolean getPower2() {
  int power = this.functionrole.getPower();
  return 2==(power & 2);
  }
  public boolean getPower4() {
    int power = this.functionrole.getPower();
    return 4==(power & 4);
  }
  public boolean getPower8() {
  int power = this.functionrole.getPower();
  return 8==(power & 8);
  }
  public boolean getPower16() {
    int power = this.functionrole.getPower();
    return 16==(power & 16);
  }
  public boolean getPower32() {
  int power = this.functionrole.getPower();
  return 32==(power & 32);
  }
  public boolean getPower64() {
  int power = this.functionrole.getPower();
  return 64==(power & 64);
  }
  public boolean getPower128() {
  int power = this.functionrole.getPower();
  return 128==(power & 128);
  }
  public boolean getPower256() {
  int power = this.functionrole.getPower();
  return 256==(power & 256);
  }
}
