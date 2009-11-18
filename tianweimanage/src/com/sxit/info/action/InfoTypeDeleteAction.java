/**
 * 
 */
package com.sxit.info.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.info.TinfInfo;
import com.sxit.models.info.TinfType;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class InfoTypeDeleteAction extends AbstractListAction {

	private int typeid;

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	protected String go() throws Exception {

		DetachedCriteria dc = DetachedCriteria.forClass(TinfInfo.class).add(Restrictions.eq("typeid", typeid));
		List list = basicService.findAllByCriteria(dc);
		if (list != null && list.size() != 0) {
			this.message = "有属于该类型的信息,请返回";
			return "message";

		}

		TinfType type = (TinfType) basicService.get(TinfType.class, typeid);
		if (type.getIsapprove()) {
			this.message = "系统类型,不能删除";
			return "message";
		}

		basicService.delete(type);
		com.sxit.info.util.CommonDatas.getAllTypes();

		this.message = "信息类型删除成功,请返回";
		this.nextPage = "infoTypeList.action";
		return SUCCESS;
	}
}
