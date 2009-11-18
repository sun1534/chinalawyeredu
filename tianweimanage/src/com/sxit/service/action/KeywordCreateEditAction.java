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
public class KeywordCreateEditAction extends AbstractListAction {

	protected String go() throws Exception {

		if (exist == 1) {
			basicService.update(type);
			this.message = "关键字修改成功";
		} else {
		type.setCreatetime(new java.util.Date());
		type.setCreateuserid(this.getLoginUser().getUserid());
			basicService.save(type);
			this.message = "关键字新增成功";
		}
		this.nextPage = "keywordList.action";
		return SUCCESS;

	}

	@Override
	public String input() {

		this.type = (GlobalKeyword) basicService.get(GlobalKeyword.class, typeid);
		if (type == null) {
			type = new GlobalKeyword();
			exist = 0;
		} else {
//			System.out.println("================="+type.getIsapprove());
			exist = 1;
		}
		set("type", type);

		return INPUT;

	}

	private int typeid;
	private GlobalKeyword type;
	private int exist;

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public int getExist() {
		return exist;
	}

	public void setExist(int exist) {
		this.exist = exist;
	}

	public GlobalKeyword getType() {
		if (type == null)
			type = (GlobalKeyword) get("type");
		return type;
	}
}
