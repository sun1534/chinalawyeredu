package com.sxit.common.action;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;
import com.sxit.common.util.md5;

/**
 *
 * <p>功能： 修改密码 </p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2004.9.29</p>
 * @版本： V1.0
 * @修改：
 */


public class UserChangePasswordAction extends AbstractAction {
  private TsysUser user;
  private String oldpassword;
  private String password1;
  private String password2;
  public UserChangePasswordAction() {
  }
  protected String go() throws HibernateException {
     if("save".equals(flag))
     {
       nextpage = "userChangePassword.action";
       try{
         user=(TsysUser)get("curuser");
         if(!user.getPassword().equals(md5.MD5(oldpassword)))
         {
           message = "旧密码输入错误！";
           return "message";
         }
         if(!password1.equals(password2))
         {
           message = "两次密码输入不同！";
           return "message";
         }
         user.setPassword(md5.MD5(password1));
         getSession().update(user);
         commit();
         message = "密码修改成功！";
       }catch(HibernateException e)
       {
         e.printStackTrace();
         message = "密码修改失败！";
       }
       return "message";
     }else{
       user=(TsysUser)get("curuser");

       if(user==null){
         nextpage = "userChangePassword.action";
         message = "用户未登录！";
         return "message";
       }else{
         return "input";
       }
     }
   }
   public TsysUser getUser() {
        return user;
   }
   public String getOldpassword() {
     return oldpassword;
   }
   public void setOldpassword(String oldpassword) {
     this.oldpassword = oldpassword;
   }
   public String getPassword1() {
     return password1;
   }
   public void setPassword1(String password1) {
     this.password1 = password1;
   }
   public String getPassword2() {
     return password2;
   }
   public void setPassword2(String password2) {
     this.password2 = password2;
   }


}
