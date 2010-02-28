/**
 * OfficeChangeApplyAction.java
 */
package com.changpeng.lawyers.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.LawyersOfficeChangeApply;

/**
 * 律师发起转所申请
 * 
 * 插入转所申请表 
 *  
 * @author 华锋
 * Feb 27, 20104:43:49 PM
 *
 */
public class OfficeChangeApplyListAction extends AbstractListAction {

	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(LawyersOfficeChangeApply.class);
		detachedCriteria.add(Restrictions.eq("lawyerid",this.getLoginUser().getLawyerid()));
		
		detachedCriteria.addOrder(Order.desc("applyTime"));
		
		this.page=basicService.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		
		
		
		return SUCCESS;
	}
	
	

}
