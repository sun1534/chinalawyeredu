package com.sxit.common.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;
import org.hibernate.Query;
import java.util.List;

/**
 *
 * <p>功能： 设置个人页面风格 </p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2004.9.29</p>
 * @版本： V1.0
 * @修改：
 */

public class SetStytleAction extends AbstractAction {
  private int style;
  public SetStytleAction() {
  }
protected String go() throws HibernateException {

    curuser = (TsysUser) get("curuser");
    if(curuser==null)
    {
      nextpage = "../userlogin.jsp";
      message="用户未登录,不能进行此操作";
      return "message";
    }
    curuser.setStyle(style);
    set("curuser",curuser);
    getSession().update(curuser);
    if(style==1)
      return "style1";
    if(style==2)
      return "style2";
    if(style==3)
      return "style3";
    return "style1";
  }
  public void setStyle(int style){
  this.style=style;
}
}
