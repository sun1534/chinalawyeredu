/**
 * SysLoginLogList.java
 */

package com.changpeng.system.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.SysLoginlog;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysRoleVisible;

/**
 * 
 * 显示所有登录的日志,提供根据用户名和登录时间进行查询的功能
 * 
 * 查询的话，根据登录的用户来查询疾
 * 
 * @author 华锋 2008-3-3 上午11:11:29
 * 
 */
public class SysLoginLogListAction extends AbstractListAction {
//	private static Log _LOG = LogFactory.getLog(SysLoginLogListAction.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	private String loginTime;

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginTime() {
		return this.loginTime;
	}
	
	
	public SysLoginLogListAction(){
		this.datavisible = new DataVisible();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
	
		// TODO Auto-generated method stub
		this.datavisible.getVisibleDatas(this.getLoginUser(),true);
		
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysLoginlog.class);
		
		if (loginTime != null && !loginTime.equals("")) {
			java.sql.Timestamp begin = new Timestamp(df.parse(loginTime + " 00:00:00").getTime());
			java.sql.Timestamp end = new Timestamp(df.parse(loginTime + " 23:59:59").getTime());
			detachedCriteria.add(Restrictions.between("loginTime", begin, end));
		}
		SysRole role = this.getLoginUser().getSysRole();
		if (role != null) {
			Set<SysRoleVisible> rolevisibles = role.getSysRoleVisibles();
			SysRoleVisible rolevisible = null;
			for (SysRoleVisible v : rolevisibles) {
				if (v.getThetable().equalsIgnoreCase("sys_loginlog")) {
					rolevisible = v;
					break;
				}
			}
			// 权限判断了
			if (rolevisible != null) {
				detachedCriteria.add(Restrictions.eq(rolevisible.getThefield(), PropertyUtils.getProperty(this
						.getLoginUser(), rolevisible.getThefield())));
			}
		}


		if (datavisible.getOfficeid() != 0) {
			detachedCriteria.add(Restrictions.eq("officeid", datavisible.getOfficeid()));
		} else if (datavisible.getCityid() != 0) {
			detachedCriteria.add(Restrictions.eq("cityid", datavisible.getCityid()));
		} else if (datavisible.getProvinceid() != 0) {
			detachedCriteria.add(Restrictions.eq("provinceid", datavisible.getProvinceid()));
		}
		
		
		
		detachedCriteria.addOrder(Order.desc("loginid"));
		BasicService service = (BasicService) getBean("basicService");
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}

}
