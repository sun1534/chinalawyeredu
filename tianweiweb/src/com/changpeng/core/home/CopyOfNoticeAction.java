package com.changpeng.core.home;


import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.SysParameter;

public class CopyOfNoticeAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(CopyOfNoticeAction.class);
	
	List noticelist;
	
	public CopyOfNoticeAction() {
		this.rightCode="PUBLIC";
		this.moduleid = 11;
	}

	@Override
	protected String go() throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysParameter.class).add(Restrictions.eq("typeid",2));
		// 这里要判断出登录帐号所属的group
		// 如果是admin，看所有的，如果不是admin,则根据登录人员的所属角色,判断角色对用户和对部门的可见性

	
	
		// 按createtime逆序排序

		BasicService service = (BasicService) getBean("basicService");
		noticelist = service.findByCriteria(detachedCriteria);
		// TODO Auto-generated method stub
//		noticelist=service.findNumList("from TinfInfo where typeid=100 and statusid=0", 10);
		return SUCCESS;

	}

	public List getNoticelist() {
		return noticelist;
	}
}
