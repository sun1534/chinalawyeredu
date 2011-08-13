package com.sxit.system.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;

/**
 *
 * <p>功能： 修改用户权限</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-11</p>
 * @版本： V1.0
 * @修改：
 */

public class UserChangeFunctionAction
    extends AbstractAction {

  private String functionid;
  private int userid;
  private TsysFunctionUser functionuser;
  private int[] power;

  public UserChangeFunctionAction() {
    rights="sys2,4";

  }

  protected String go() throws HibernateException {
    if ("save".equals(flag)) {
      int temppower=0;
      for(int temp:power){
        temppower+=temp;
      }
      functionuser.setPower(temppower);
      getSession().update(functionuser);
      nextpage = "userViewFunctions.action?userid=" + functionuser.getComp_id().getTsysUser().getUserid();
      message="保存用户权限成功";
      return "message";
    }else{
      TsysFunctionUserPK compid = new TsysFunctionUserPK();
      TsysUser user = (TsysUser) getSession().get(TsysUser.class,
          Long.valueOf(userid));
      if (user == null) {
        message = "未找到此用户";
        return "message";
      }
      TsysFunction function = (TsysFunction) getSession().get(TsysFunction.class,
          functionid);
      if (function == null) {
        message = "未找到此功能";
        return "message";
      }
      compid.setTsysUser(user);
      compid.setTsysFunction(function);
      functionuser = (TsysFunctionUser) getSession().get(TsysFunctionUser.class,
          compid);
      if (functionuser == null) {
        message = "未找到此用户和此功能的权限";
        return "message";
      }
      return SUCCESS;
    }
  }
  public void setFunctionid(String functionid) {
    this.functionid = functionid;
  }
  public void setUserid(int userid) {
    this.userid = userid;
  }
  public TsysFunctionUser getFunctionuser() {
    return functionuser;
  }

  public void setFunctionuser(TsysFunctionUser functionuser) {
    this.functionuser = functionuser;
  }
  public void setPower(int[] power) {
    this.power = power;
  }

  //权限位与运算
  public boolean getPower1() {
    int power = this.functionuser.getPower();
    return 1==(power & 1);
  }
  public boolean getPower2() {
  int power = this.functionuser.getPower();
  return 2==(power & 2);
  }
  public boolean getPower4() {
    int power = this.functionuser.getPower();
    return 4==(power & 4);
  }
  public boolean getPower8() {
  int power = this.functionuser.getPower();
  return 8==(power & 8);
  }
  public boolean getPower16() {
    int power = this.functionuser.getPower();
    return 16==(power & 16);
  }
  public boolean getPower32() {
  int power = this.functionuser.getPower();
  return 32==(power & 32);
  }
  public boolean getPower64() {
  int power = this.functionuser.getPower();
  return 64==(power & 64);
  }
  public boolean getPower128() {
  int power = this.functionuser.getPower();
  return 128==(power & 128);
  }
  public boolean getPower256() {
  int power = this.functionuser.getPower();
  return 256==(power & 256);
  }
}
