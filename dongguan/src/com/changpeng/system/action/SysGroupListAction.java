/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.system.SysGroup;

/**
 * 
 * 用户信息列表
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysGroupListAction extends AbstractListAction {

	// private int parentid;
	// public void setParentid(int parentid){
	// this.parentid=parentid;
	// }
	// public int getParentid(){
	// return this.parentid;
	// }
	private String groupname;
	private String groupenname;

	/**
	 * @return the groupname
	 */
	public String getGroupname() {
		return groupname;
	}

	/**
	 * @param groupname
	 *            the groupname to set
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	/**
	 * @return the groupenname
	 */
	public String getGroupenname() {
		return groupenname;
	}

	/**
	 * @param groupenname
	 *            the groupenname to set
	 */
	public void setGroupenname(String groupenname) {
		this.groupenname = groupenname;
	}

	public SysGroupListAction() {
		this.rightCode = "sysGroupList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 * 
	 * 根据parentid得到下面一层的所有group;
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		BasicService service = (BasicService) this.getBean("basicService");

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysGroup.class);
		if (groupname != null && !"".equals(groupname)) {
			detachedCriteria.add(Restrictions.like("groupname", groupname.trim(),MatchMode.ANYWHERE));
		}
		if (groupenname != null && !"".equals(groupenname)) {
			detachedCriteria.add(Restrictions.like("groupenname", groupenname.trim(),MatchMode.START));
		}
		detachedCriteria.addOrder(Order.desc("groupid"));
		
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;

	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}

}