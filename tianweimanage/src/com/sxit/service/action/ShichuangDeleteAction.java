/**
 * 
 */
package com.sxit.service.action;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.service.CoreShichuang;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class ShichuangDeleteAction extends AbstractListAction {

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

		CoreShichuang shichuang = (CoreShichuang) basicService.get(CoreShichuang.class, id);
		basicService.delete(shichuang);
		String ss="视窗";
		if(shichuang.getType().equals("sc")){
			ss="视窗";
		}else{
			ss="导航";
		}
		this.message = ss+"链接信息删除成功,请返回";
	
		
		if(shichuang.getType().equals("sc"))
			this.nextPage = "shichuangList.action";
			else
				this.nextPage="daohangList.action";
		return SUCCESS;
	}
}
