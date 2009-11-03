/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.util.List;

import org.hibernate.FetchMode;
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
 * <pre>
 * 师列表 
 * 、列表的部分，只能查看自己组以及下面组的人员，对于有权限的人来说 
 * 、对于group部分,也是同样如此 
 * 、根据用户姓名、登录名进行查询 
 * 、
 * 
 * 
 * </pre>
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class LawyerListAction extends AbstractListAction {

	private String loginname;
	private String username;
	private String groupname;

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

	public LawyerListAction() {
		this.rightCode = "lawyerList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysUser.class).add(Restrictions.eq("roleid", 1));

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
		SysGroup group = sysUser.getSysGroup();
		// 如果没有所属的group,则能查看所有的数据信息，否则只能看本部门和下级部门的数据
		if (group != null && group.getGroupid() != 1) { // 不是深圳律协的人上来的话
			int groupid = group.getGroupid();
			GroupTree tree = new GroupTree();
			List groupidlist = tree.getChildGroupIds(groupid);
			detachedCriteria.createAlias("sysGroup", "sysGroup").add(Restrictions.in("sysGroup.groupid", groupidlist));
			if (groupname != null &&! "".equals(groupname)) {
				detachedCriteria.add(Restrictions.like("sysGroup.groupname", groupname, MatchMode.ANYWHERE));
			}

			// detachedCriteria.add(Restrictions.in("groupid", groupidlist));
		}
		else if (groupname != null && !"".equals(groupname)) {
			detachedCriteria.createAlias("sysGroup", "sysGroup").add(Restrictions.like("sysGroup.groupname", groupname, MatchMode.ANYWHERE));
		}
		detachedCriteria.setFetchMode("sysGroup", FetchMode.JOIN);

		// 按userid逆序排序
		detachedCriteria.addOrder(Order.desc("userid"));
		BasicService service = (BasicService) getBean("basicService");
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
}
