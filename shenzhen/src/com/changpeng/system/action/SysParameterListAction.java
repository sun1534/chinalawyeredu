/**
 * TSysParameterAddAction.java
 */

package com.changpeng.system.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.system.SysParameter;

/**
 * 
 * 用户信息列表根据参数名查询
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysParameterListAction extends AbstractListAction {

	private String paramname;
	
	public SysParameterListAction() {
		this.rightCode = "sysParameterList";
	}

	/**
	 * @return the paramname
	 */
	public String getParamname() {
		return paramname;
	}

	/**
	 * @param paramname the paramname to set
	 */
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysParameter.class);
		// 这里要判断出登录帐号所属的group
		// 如果是admin，看所有的，如果不是admin,则根据登录人员的所属角色,判断角色对用户和对部门的可见性

		if (paramname != null && !"".equals(paramname))
			detachedCriteria.add(Restrictions.like("paramname", paramname, MatchMode.ANYWHERE));
	
		// 按createtime逆序排序
		detachedCriteria.addOrder(Order.desc("createtime"));
		BasicService service = (BasicService) getBean("basicService");
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
}
