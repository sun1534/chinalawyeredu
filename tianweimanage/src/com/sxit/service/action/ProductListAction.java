/**
 * 
 */
package com.sxit.service.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.service.CoreProduct;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class ProductListAction extends AbstractListAction {

	

	protected String go() throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(CoreProduct.class);
	
		if(producttype!=-1){
			dc.add(Restrictions.eq("producttype",producttype));
		}
		
		dc.addOrder(Order.desc("id"));
		
		this.page=basicService.findPageByCriteria(dc, pageSize, pageNo);
		

		return SUCCESS;
	}
	
	private int producttype=-1;

	public int getProducttype() {
		return producttype;
	}

	public void setProducttype(int producttype) {
		this.producttype = producttype;
	}
	
}
