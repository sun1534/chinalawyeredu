package com.uu800.admin.base.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.UserService;


/**
 *
 * <p>功能： 列表流程用户管理</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-05-22</p>
 * @版本： V1.0
 * @修改：
 */

public class UserOfWflListAction extends AbstractAdminAction  {
	    private long orgid=-1;
	    private long jobid=-1;
	    private boolean findall=false;
	    private TsysUser user;
	    private String username;
	    private String name;
        private List userlist;
    	private UserService service;
        public UserOfWflListAction() {
           service = (UserService)this.getBean("userService");
        }
        @Override
    	public String execute() {
        	
        	 user=(TsysUser)service.get(TsysUser.class,getUserinfo().getUserid());
        	
        	 
        	 DetachedCriteria dc=DetachedCriteria.forClass(TsysUser.class);
        	 //隐藏超级管理员
        	 dc.add(Restrictions.ne("usertype", 0)); 
        	 
        	 if(!findall)
        	 {
        		 dc.add(Restrictions.eq("tsysOrg.orgid", orgid)); 
        	 }        	 
             if(username!=null&&!"".equals(username))
             {
            	 dc.add(Restrictions.like("username", username,MatchMode.ANYWHERE)); 
             }
             if(name!=null&&!"".equals(name))
             {
            	 dc.add(Restrictions.like("name", name,MatchMode.ANYWHERE)); 
             }
             
             
             dc.addOrder(Order.desc("userid")); 
             
             page= service.findPageOnPageNo(dc,page);
    	     
             userlist=page.getItems();
             
             return SUCCESS;
        }

        public List getUserlist() {
          return userlist;
        }
		public long getOrgid() {
			return orgid;
		}
		public void setOrgid(long orgid) {
			this.orgid = orgid;
		}
		public TsysUser getUser() {
			return user;
		}
		public void setUser(TsysUser user) {
			this.user = user;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public long getJobid() {
			return jobid;
		}
		public void setJobid(long jobid) {
			this.jobid = jobid;
		}
		public boolean isFindall() {
			return findall;
		}
		public void setFindall(boolean findall) {
			this.findall = findall;
		}
}
