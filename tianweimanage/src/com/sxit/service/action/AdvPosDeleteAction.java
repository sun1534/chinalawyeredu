/**
 * 
 */
package com.sxit.service.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.service.CoreAdvPos;
import com.sxit.models.service.CoreAdvtisment;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class AdvPosDeleteAction extends AbstractListAction {

	private int pos;



	public int getPos() {
		return pos;
	}



	public void setPos(int pos) {
		this.pos = pos;
	}



	protected String go() throws Exception {

		DetachedCriteria dc = DetachedCriteria.forClass(CoreAdvtisment.class).add(Restrictions.eq("pos", pos));
		List list = basicService.findAllByCriteria(dc);
		if (list != null && list.size() != 0) {
			this.message = "该广告位有内容，请先删除内容";
			return "message";

		}

		CoreAdvPos type = (CoreAdvPos) basicService.get(CoreAdvPos.class, pos);
	
		basicService.delete(type);
		

		this.message = "广告位删除成功,请返回";
		this.nextPage = "advPosList.action";
		return SUCCESS;
	}
}
