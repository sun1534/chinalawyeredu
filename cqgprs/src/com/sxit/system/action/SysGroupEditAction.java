/**
 * TSysUserAddAction.java
 */
package com.sxit.system.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.BasicService;
import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysGroup;
import com.sxit.system.service.SysGroupService;

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
		this.nextPage="sysGroupList.action";
		return SUCCESS;
	}
	
	@Override
	public String input() throws Exception {
		SysGroup sysGroup=service.get(groupid);
		BasicService bs = (BasicService) this.getBean("basicService");
		DetachedCriteria dc=DetachedCriteria.forClass(SysGroup.class).add(Restrictions.ne("groupid", groupid));
		parentList = bs.findAllByCriteria(dc);
		

		this.set("sysGroup", sysGroup);
		return INPUT;
	}
	private List parentList;

	public List getParentList() {
		return parentList;
	}
	
}
