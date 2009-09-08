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
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUser;

/**
 * 
 * 部门的管理员列表
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysGroupManagerAction extends AbstractListAction {

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

	public SysGroupManagerAction() {
		// this.rightCode = "sysUserList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		BasicService basicService = (BasicService) this.getBean("basicService");
		SysGroup group = (SysGroup) basicService.get(SysGroup.class, groupid);
		if (group == null) {
			this.message = "部门数据不存在,请返回";
			return "message";
		}
		this.groupname = group.getGroupname();
		if (group.getGrouptype() == 1) {
			backurl = "theOfficeList.pl";
		} else if (group.getGrouptype() == 2) {
			backurl = "theUnionList.pl";
		} else if (group.getGrouptype() == 3) {
			backurl = "theUnionList.pl";
		} else {
			backurl = "sysGroupList.pl";
		}

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysUser.class);
		// 只显示律师事务所的
		detachedCriteria.add(Restrictions.eq("sysGroup.groupid", groupid));
		// 这里要判断出登录帐号所属的group
		// 如果是admin，看所有的，如果不是admin,则根据登录人员的所属角色,判断角色对用户和对部门的可见性
		if (loginname != null && !"".equals(loginname))
			detachedCriteria.add(Restrictions.like("loginname", loginname, MatchMode.START));
		if (username != null && !"".equals(username))
			detachedCriteria.add(Restrictions.like("username", username, MatchMode.START));

		// 必须是没有删除的啊
		detachedCriteria.add(Restrictions.eq("delflag", false));
		// 取消掉自己的显示
		detachedCriteria.add(Restrictions.ne("userid", getLoginUser().getUserid()));
		detachedCriteria.addOrder(Order.desc("userid"));
		BasicService service = (BasicService) getBean("basicService");
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	private String backurl;

	public String getBackurl() {
		return this.backurl;
	}

	private int groupid;

	/**
	 * @return the groupid
	 */
	public int getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid
	 *            the groupid to set
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	private String groupname;

	public String getGroupname() {
		return this.groupname;
	}

}
