package com.uu800.admin.base.action;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.OrgService;
import com.uu800.admin.base.service.UserService;

/**
 *
 * <p>功能： 列表用户管理</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-05-22</p>
 * @版本： V1.0
 * @修改：
 */

public class UserManageAction extends AbstractAdminAction  {
        private List userlist;
        private TsysUser user;        
        private LinkedHashMap orgmap;
    	private UserService service;
    	private List orgList;
        /**
		 * @return the orgList
		 */
		public List getOrgList() {
			return orgList;
		}
		public UserManageAction() {
           service = (UserService)this.getBean("userService");
        }
        @Override
    	public String execute()  {
        	user=(TsysUser)service.get(TsysUser.class,getUserinfo().getUserid());
        	OrgService orgservice = (OrgService) this.getBean("orgService");
        	this.orgList = orgservice.getOrgList();
            return SUCCESS;
        }
        public List getUserlist() {
          return userlist;
        }
		public LinkedHashMap getOrgmap() {
			return orgmap;
		}
		public TsysUser getUser() {
			return user;
		}

}
