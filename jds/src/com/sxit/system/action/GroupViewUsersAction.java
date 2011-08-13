package com.sxit.system.action;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;

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

public class GroupViewUsersAction  extends AbstractAction {

  private TsysGroup group;

  private int groupid;
  private Set groupusers;

  public GroupViewUsersAction() {
   rights="sys3,1";
  }

  protected String go() throws org.hibernate.HibernateException {
    group=(TsysGroup)getSession().get(TsysGroup.class,Integer.valueOf(groupid));
    if(group==null)
    {
      nextpage = "listGroup.action";
      message="此用户不存在！";
      return "message";
    }
    else{
      groupusers = group.getTsysGroupUsers();
      return SUCCESS;
     }
   }
   public TsysGroup getGroup() {
           return group;
   }
  public int getGroupid() {
    return groupid;
  }
  public void setGroupid(int groupid) {
    this.groupid = groupid;
  }
  public Set getGroupusers() {
    return groupusers;
  }
}
