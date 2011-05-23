/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;
import com.changpeng.system.util.GroupTree;

/**
 * 
 * 事务所管理员列表
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

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysUser.class);
		// 只显示律师事务所的
		detachedCriteria.createAlias("sysRoles", "roles").add(Restrictions.eq("roles.roleid", (short) 2));
		// 这里要判断出登录帐号所属的group
		// 如果是admin，看所有的，如果不是admin,则根据登录人员的所属角色,判断角色对用户和对部门的可见性

		if (loginname != null && !"".equals(loginname))
			detachedCriteria.add(Restrictions.like("loginname", loginname, MatchMode.ANYWHERE));
		if (username != null && !"".equals(username))
			detachedCriteria.add(Restrictions.like("username", username, MatchMode.ANYWHERE));

		// 必须是没有删除的啊
		detachedCriteria.add(Restrictions.eq("delflag", false));
		// 取消掉自己的显示
		detachedCriteria.add(Restrictions.ne("userid", getLoginUser().getUserid()));
		SysUser sysUser = getLoginUser();
//		SysGroup group = sysUser.getSysGroup();
		BasicService basicService = (BasicService) this.getBean("basicService");
		SysGroup group=(SysGroup)basicService.get(SysGroup.class, groupid);
	this.groupname=group.getGroupname();
		
		// 如果没有所属的group,则能查看所有的数据信息，否则只能看本部门和下级部门的数据
		// if (group != null) {

		// GroupTree tree = new GroupTree();
		// List groupidlist = tree.getChildGroupIds(groupid);
		detachedCriteria.createCriteria("sysGroup").add(Restrictions.eq("groupid", groupid));
		// }
		// 按userid逆序排序
		detachedCriteria.addOrder(Order.desc("userid"));
		SysUserService service = (SysUserService) getBean("sysUserService");
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	// @Override
	// public String input() throws Exception {
	// return INPUT;
	// }

	// public List getSysUserList(){
	// return this.sysUserList;
	// }

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
	public String getGroupname(){
		return this.groupname;
	}

}
