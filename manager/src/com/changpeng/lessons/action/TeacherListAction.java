/**
 * TSysUserAddAction.java
 */

package com.changpeng.lessons.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.SysRole;
import com.changpeng.models.Teacher;

/**
 * 授课律师列表,即roleid=100的sys_user数据
 * 
 * @author 华锋 Jul 11, 20108:46:10 PM
 * 
 */
public class TeacherListAction extends AbstractListAction {

	private String loginname;
	private String username;

	public void setLoginname(String name) {
		this.loginname = name;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getLoginname() {
		return this.loginname;
	}

	public String getUsername() {
		return this.username;
	}

	public TeacherListAction() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

	
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Teacher.class);
				

		if (loginname != null && !"".equals(loginname))
			detachedCriteria.add(Restrictions.like("loginname", loginname, MatchMode.ANYWHERE));
		if (username != null && !"".equals(username))
			detachedCriteria.add(Restrictions.like("username", username, MatchMode.ANYWHERE));

		

		// 按userid逆序排序
		detachedCriteria.addOrder(Order.desc("userid"));
		BasicService service = (BasicService) getBean("basicService");

		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		// TODO Auto-generated method stub
		return SUCCESS;
	}
}