/**
 * 
 */
package com.sxit.service.action;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.service.CoreShichuang;
import com.sxit.models.service.GlobalKeyword;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class ShichuangCreateEditAction extends AbstractListAction {

	protected String go() throws Exception {
		shichuang.setType(type);
		String ss="视窗";
		if(type.equals("sc")){
			ss="视窗";
		}else{
			ss="导航";
		}
		if (exist == 1) {
			basicService.update(shichuang);
			this.message = ss+"链接信息修改成功";
		} else {
			shichuang.setCreatetime(new java.util.Date());
			shichuang.setCreateuserid(this.getLoginUser().getUserid());
			shichuang.setCreateusername(this.getLoginUser().getUsername());
			basicService.save(shichuang);
			this.message = ss+"链接信息新增成功";
		}
		if(type.equals("sc"))
	    	this.nextPage = "shichuangList.action";
		else
			this.nextPage="daohangList.action";
		return SUCCESS;

	}

	@Override
	public String input() {

		this.shichuang = (CoreShichuang) basicService.get(CoreShichuang.class, id);
		if (shichuang == null) {
			shichuang = new CoreShichuang();
			exist = 0;
		} else {
			// System.out.println("================="+type.getIsapprove());
			exist = 1;
			type=shichuang.getType();
		}
		set("shichuang", shichuang);

		return INPUT;

	}
private String type;
	private int id;
	private CoreShichuang shichuang;
	private int exist;

	public int getId() {
		return id;
	}

	public void setId(int typeid) {
		this.id = typeid;
	}

	public int getExist() {
		return exist;
	}

	public void setExist(int exist) {
		this.exist = exist;
	}

	public CoreShichuang getShichuang() {
		if (shichuang == null)
			shichuang = (CoreShichuang) get("shichuang");
		return shichuang;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
