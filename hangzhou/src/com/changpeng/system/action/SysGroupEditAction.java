/**
 * TSysUserAddAction.java
 */
package com.changpeng.system.action;

import com.changpeng.common.Constants;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysGroup;
import com.changpeng.system.service.SysGroupService;

/**
 * 
 * 部门信息修改
 * 选上级的时候，不能选自己的下级做上级，也就是要将自己的所有下级（包括自己都从列表中取消掉）
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class SysGroupEditAction extends AbstractAction {

	private SysGroupService service;
	
	private SysGroup sysGroup;
	private int groupid;
	
	public void setGroupid(int groupid){
		this.groupid=groupid;
	}
	
	public SysGroup getSysGroup(){
		if(sysGroup==null)
			sysGroup= (SysGroup)get("sysGroup");
		return sysGroup;
	}
	
	public SysGroupEditAction(){
		service=(SysGroupService)this.getBean("sysGroupService");
		this.rightCode="sysGroupEdit";
	}
	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		service.updateGroup(sysGroup);
		
		this.message="部门信息修改成功";
		this.nextPage="sysGroupList.pl";
		return SUCCESS;
	}
	
	@Override
	public String input() throws Exception {
		SysGroup sysGroup=service.get(groupid);
	    SysGroup parent=service.get(sysGroup.getParentid());	
	    if(parent!=null)
	    	this.parentname=parent.getGroupname();
	    else
	    	this.parentname=Constants.COMPANY_NAME;
		this.set("sysGroup", sysGroup);
		return INPUT;
	}
	
	private String parentname;
	public String getParentname(){
		return this.parentname;
	}
}
