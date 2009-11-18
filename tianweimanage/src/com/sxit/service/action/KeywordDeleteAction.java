/**
 * 
 */
package com.sxit.service.action;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.info.TinfType;
import com.sxit.models.service.GlobalKeyword;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class KeywordDeleteAction extends AbstractListAction {

	private int id;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	protected String go() throws Exception {

//		DetachedCriteria dc = DetachedCriteria.forClass(TinfInfo.class).add(Restrictions.eq("typeid", typeid));
//		List list = basicService.findAllByCriteria(dc);
//		if (list != null && list.size() != 0) {
//			this.message = "有属于该类型的信息,请返回";
//			return "message";
//
//		}

		GlobalKeyword type = (GlobalKeyword) basicService.get(GlobalKeyword.class, id);
		basicService.delete(type);

		this.message = "关键字删除成功,请返回";
		this.nextPage = "keywordList.action";
		return SUCCESS;
	}
}
