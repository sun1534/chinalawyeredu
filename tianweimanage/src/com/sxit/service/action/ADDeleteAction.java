/**
 * 
 */
package com.sxit.service.action;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.service.CoreAdvtisment;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class ADDeleteAction extends AbstractListAction {

	private int pos;






	public int getPos() {
		return pos;
	}






	public void setPos(int pos) {
		this.pos = pos;
	}






	protected String go() throws Exception {

		CoreAdvtisment info = (CoreAdvtisment) basicService.get(CoreAdvtisment.class, pos);
	
		basicService.delete(info);
		this.nextPage = "adList.action";
		this.message = "此广告信息删除成功,请返回";

		return SUCCESS;
	}
}
