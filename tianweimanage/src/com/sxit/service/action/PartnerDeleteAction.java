/**
 * 
 */
package com.sxit.service.action;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.service.CorePartner;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class PartnerDeleteAction extends AbstractListAction {

	private int id;

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	protected String go() throws Exception {


		CorePartner type = (CorePartner) basicService.get(CorePartner.class, id);
		

		basicService.delete(type);
	

		this.message = "合作伙伴删除成功,请返回";
		this.nextPage = "partnerList.action";
		return SUCCESS;
	}
}
