package com.uu800.admin.base.action;

import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.UserService;
import com.uu800.admin.base.entity.TsysLogs;


/**
 *
 * <p>功能： 查看用户管理</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-05-22</p>
 * @版本： V1.0
 * @修改：
 */

public class UserViewAction extends AbstractAdminAction  {
	private TsysUser user;
    private long userid;
    private long orgid;
	private UserService service;
	
	public UserViewAction() {
       service = (UserService)this.getBean("userService");
	   user = new TsysUser();
	}

	@Override
	public String execute() {
           nextPage="userList.action?pageNo="+pageNo;
           user=(TsysUser)service.get(TsysUser.class,userid);
           if(user==null){
             message="未找到此用户管理";
//             log=new TsysLogs("查看系统用户:用户ID为 "+userid,1,false);
             return "sysmsg";
           }
//           log=new TsysLogs("查看系统用户:用户ID为 "+userid+" 用户名为 "+user.getUsername(),1,true);

           set("user", user);
           return SUCCESS;
	}
	public TsysUser getUser() {
		return user;
	}
        public void setUserid(long userid) {

          this.userid = userid;
        }
        public long getUserid() {
          return this.userid;
        }

		public long getOrgid() {
			return orgid;
		}

		public void setOrgid(long orgid) {
			this.orgid = orgid;
		}

}
