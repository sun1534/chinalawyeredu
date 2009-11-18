/**
 * 
 */
package com.sxit.info.action;

import java.io.File;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.info.TinfType;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class InfoTypeCreateEditAction extends AbstractListAction {

	protected String go() throws Exception {

		if (exist == 1) {
			basicService.update(type);
			this.message = "信息类型修改成功";
		} else {
			type.setIsapprove(false);
			basicService.save(type);
			this.message = "信息类型新增成功";
		}
		this.nextPage = "infoTypeList.action";
		com.sxit.info.util.CommonDatas.getAllTypes();
		return SUCCESS;

	}

	@Override
	public String input() {

		this.type = (TinfType) basicService.get(TinfType.class, typeid);
		if (type == null) {
			type = new TinfType();
			exist = 0;
		} else {
//			System.out.println("================="+type.getIsapprove());
			exist = 1;
		
		}
		set("type", type);

		return INPUT;

	}

	private int typeid;
	private TinfType type;
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

	public TinfType getType() {
		if (type == null)
			type = (TinfType) get("type");
		return type;
	}

}
