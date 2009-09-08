/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import org.hibernate.criterion.Criterion;
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
 * 不显示grouptype为1，2，3的。普通的组管理
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysGroupListAction extends AbstractListAction {


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





	public SysGroupListAction() {
		
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
		Criterion c4=Restrictions.eq("grouptype", 4);
		Criterion c5=Restrictions.eq("grouptype", 5);
		
		detachedCriteria.add(Restrictions.or(c4,c5));
		
		
		
//		detachedCriteria.add(Restrictions.eq("grouptype", this.getLoginUser().getSysGroup().getGrouptype()));
		detachedCriteria.addOrder(Order.desc("groupid"));
		
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		
		SysUser user=this.getLoginUser();
		if(user.getLoginname().equals("admin")||user.getRightList().contains("sysGroupExcludeRight")){
			hasright=true;
		}

		return SUCCESS;
	}
	
	private boolean hasright;
	public boolean getHasright(){
		return this.hasright;
	}
}