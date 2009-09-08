/**
 * JifenbudengListAction.java
 */

package com.changpeng.jifen.action;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Jifenbudeng;
import com.changpeng.models.system.SysUser;

/**
 * @author 华锋 2008-5-4 下午11:28:45
 * 
 */
public class JifenbudengListAction extends AbstractListAction {
	public JifenbudengListAction(){
		this.rightCode="jifenbudeng";
	}
	/**
	 * 判断这个人的角色,是不是管理员，如果使系统管理员，显示所有补登的情况 <br/> 事务所管理员没这个功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String go() throws Exception {

//		SysUser user = (SysUser) this.getLoginUser();

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Jifenbudeng.class);
		boolean sysuser = false;
		if (username != null && !"".equals(username) && (lawerno == null || lawerno.equals(""))) {
			detachedCriteria.createAlias("lawer", "sysuser").add(Restrictions.like("sysuser.username", username, MatchMode.ANYWHERE));
			sysuser = true;
		}
		if (lawerno != null && !"".equals(lawerno) && (username == null || username.equals(""))) {
			detachedCriteria.createAlias("lawer", "sysuser").add(Restrictions.like("sysuser.lawerno", lawerno, MatchMode.ANYWHERE));
			sysuser = true;
		}
		if (lawerno != null && !"".equals(lawerno) && (username != null && !username.equals(""))) {
			detachedCriteria.createAlias("lawer", "sysuser");
			Criterion lhs = Restrictions.like("sysuser.username", username, MatchMode.ANYWHERE);
			Criterion rhs = Restrictions.like("sysuser.lawerno", lawerno, MatchMode.ANYWHERE);
			detachedCriteria.add(Restrictions.or(lhs, rhs));
			sysuser = true;
		}
		if (officename != null && !"".equals(officename)) {
			if (sysuser) {
				detachedCriteria.createAlias("lawer.sysGroup", "group").add(Restrictions.like("group.groupname", officename, MatchMode.ANYWHERE));
			}
			else {
				detachedCriteria.createAlias("lawer", "sysuser").createAlias("lawer.sysGroup", "group").add(
						Restrictions.like("group.groupname", officename, MatchMode.ANYWHERE));
			}
		}

		detachedCriteria.addOrder(Order.desc("createtime"));
		BasicService basic = (BasicService) getBean("basicService");
		this.page=basic.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		
		return SUCCESS;
	}

	// 根据律师名字、律师执业资格证号、事务所名称来查询补登情况
	private String lawerno;
	private String username;
	private String officename;

	/**
	 * @return the lawername
	 */
	public String getLawerno() {
		return lawerno;
	}

	/**
	 * @param lawername
	 *            the lawername to set
	 */
	public void setLawerno(String lawerno) {
		this.lawerno = lawerno;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the officename
	 */
	public String getOfficename() {
		return officename;
	}

	/**
	 * @param officename
	 *            the officename to set
	 */
	public void setOfficename(String officename) {
		this.officename = officename;
	}

}
