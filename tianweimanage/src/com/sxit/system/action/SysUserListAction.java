/**
 * TSysUserAddAction.java
 */

package com.sxit.system.action;

import java.util.Set;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.BasicService;
import com.sxit.common.action.AbstractListAction;
import com.sxit.models.system.SysRole;
import com.sxit.models.system.SysUser;

/**
 * 
 * 用户信息列表 1、列表的部分
 * 除了admin,所有用户只能查询到自己角色对应的用户列表
 * @author 华锋 2008-2-25 上午11:12:05
 * Tompan 2009-3-11修改
 */
public class SysUserListAction extends AbstractListAction {

	private String loginname="";
	private String username;
	private String groupname;
	private short roleid;
	private SysUser sysUser;

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

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

	public SysUserListAction() {
		this.rightCode = "sysUserList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		
		SysUser user=this.getLoginUser();
		if(user.getRightList().contains("sysUserRightPre")||user.getLoginname().equals("admin"))
			hassetrights=true;
		if(user.getRightList().contains("sysUserRolePre")||user.getLoginname().equals("admin"))
			hassetrole=true;
		
		
//		System.out.println("Get LOGINNAME:");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysUser.class);
		detachedCriteria.setFetchMode("sysGroup", FetchMode.JOIN);

		// detachedCriteria.createAlias("sysGroup","sysGroup",CriteriaSpecification.LEFT_JOIN);
		// detachedCriteria.setFetchMode("sysGroup", FetchMode.JOIN);
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
		// 取消掉登录名为admin的数据显示,这个是最核心的,不显示出来
		detachedCriteria.add(Restrictions.ne("loginname", "admin"));
		sysUser = getLoginUser();
//		System.out.println("Get LOGINNAME:"+sysUser.getLoginname());
		// 只显示角色和自己相等的记录
		if(!sysUser.getLoginname().equals("admin"))
		{
			Set<SysRole> _sysRoles = sysUser.getSysRoles();

			for (SysRole role : _sysRoles) {
				// sysRoles.add((int) role.getRoleid());
				roleid = role.getRoleid();
				break;
			}
//			detachedCriteria.createAlias("sysRoles", "sysrole");
//			detachedCriteria.add(Restrictions.eq("sysrole.roleid", roleid));
			
			detachedCriteria.add(Restrictions.eq("createuser", sysUser.getLoginname()));
			// 取消掉创建用户为admin的数据显示,不显示出来
			detachedCriteria.add(Restrictions.ne("createuser", "admin"));
		}
		// 如果地域不为0的话，显示所有的下属地域信息以及由他自己新增的
//		if (sysUser.getLocationid() != 0) 
//		{	
//			Criterion in=Restrictions.in("locationid", sysUser.getChildrenLocations());
//			Criterion create=Restrictions.eq("createuser", this.getLoginUser().getLoginname());
//			detachedCriteria.add(Restrictions.or(in, create));	
//		}

		// 按userid逆序排序
		detachedCriteria.addOrder(Order.desc("userid"));
		BasicService service = (BasicService) getBean("basicService");
		// service.setCriteriaSpecification(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
//		System.out.println("Get LOGINNAME:"+sysUser.getLoginname());
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public short getRoleid() {
		return roleid;
	}

	public void setRoleid(short roleid) {
		this.roleid = roleid;
	}
	
	/**
	 * 是否可以分配角色
	 * @return
	 */
	public boolean getHassetrole(){
	
		return hassetrole;
	}
	
	/**
	 * 是否可以分配权限
	 * @return
	 */
	public boolean getHassetrights(){
	
		return hassetrights;
	}

	private boolean hassetrole;
	private boolean hassetrights;
}
