/**
 * OfficeChangeApplyAction.java
 */
package com.changpeng.jifen.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.JifenbudengApply;
import com.changpeng.models.Lawyers;

/**
 * 律师发起的转速申请列表
 *  
 * @author 华锋
 * Feb 27, 20104:43:49 PM
 *
 */
public class JifenbudengApplyListAction extends AbstractListAction {
	private Lawyers lawyer;
	public Lawyers getLawyer() {
		return lawyer;
	}
	public String getTopbarpic(){
		return super.webpara.getTopbarpic();
	}
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		this.lawyer = this.getLoginUser();
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(JifenbudengApply.class);
		detachedCriteria.add(Restrictions.eq("lawyerid",this.getLoginUser().getLawyerid()));
		
		detachedCriteria.addOrder(Order.desc("budengid"));
		
		this.page=basicService.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		return SUCCESS;
	}
}