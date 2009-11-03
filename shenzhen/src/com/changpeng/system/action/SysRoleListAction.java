/**
 * TSysUserAddAction.java
 */
package com.changpeng.system.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.system.SysRole;
import com.changpeng.system.service.SysRoleService;

/**
 * 
 * 角色列表
 * 不分group,全部列出来吧
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class SysRoleListAction extends AbstractListAction {

	
	private String rolename;
	public void setRolename(String rolename){
		this.rolename=rolename;
	}
	public String getRolename(){
		return this.rolename;
	}
	
	public SysRoleListAction(){
		this.rightCode="sysRoleList";
	}
	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysRole.class);
		if (rolename != null && !"".equals(rolename))
			detachedCriteria.add(Restrictions.like("rolename", rolename, MatchMode.ANYWHERE));
		
		detachedCriteria.addOrder(Order.desc("roleid"));

		SysRoleService service = (SysRoleService) getBean("sysRoleService");
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		
		return SUCCESS;
	}
//	@Override
//	public String input() throws Exception {
//		return INPUT;
//	}
}
