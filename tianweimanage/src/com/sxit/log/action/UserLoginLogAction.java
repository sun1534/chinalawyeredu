/**
 * SysLoginLogList.java
 */

package com.sxit.log.action;

import java.text.DateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.log.GlobalLogLogin;

/**
 * 
 * 显示所有登录的日志,提供根据用户名和登录时间进行查询的功能
 * 
 * 查询的话，根据登录的用户来查询疾
 * 
 * @author 华锋 2008-3-3 上午11:11:29
 * 
 */
public class UserLoginLogAction extends AbstractListAction {
	private static Log _LOG = LogFactory.getLog(UserLoginLogAction.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String totype="list";

	public String getTotype() {
		return totype;
	}

	public void setTotype(String totype) {
		this.totype = totype;
	}
	public UserLoginLogAction() {
		// this.rightCode = "sysLoginLogList";
	}
	
	private String loginTime;

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	private short roleid;

	public short getRoleid() {
		return roleid;
	}

	public void setRoleid(short roleid) {
		this.roleid = roleid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(GlobalLogLogin.class);
	
		if(roleid!=0){
			detachedCriteria.add(Restrictions.eq("userRole",roleid));
		}
		if(loginTime!=null&&!loginTime.equals(""))
		{
			detachedCriteria.add(Restrictions.ge("loginTime", loginTime));
		}
		if(username!=null&&!username.equals(""))
		{
			detachedCriteria.add(Restrictions.ge("userName", username));
		}
		detachedCriteria.addOrder(Order.desc("id"));
		
		if(totype!=null&&totype.equals("excel"))
			this.page=basicService.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, 1);
		else
			
			this.page=basicService.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		
		
		return totype;
	}
	
}
