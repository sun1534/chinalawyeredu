/**
 * TSysUserAddAction.java
 */
package com.sxit.system.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysRight;
import com.sxit.models.system.SysRole;
import com.sxit.system.service.SysRoleService;
import com.sxit.system.util.RightTree;

/**
 * 
 * 用户信息列表
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class SysRoleRightAction extends AbstractAction {

	
	private static Log LOG = LogFactory.getLog(SysRoleRightAction.class);
	private short roleid;
	private SysRoleService service;

	public short getRoleid() {
		return roleid;
	}

	public void setRoleid(short roleid) {
		this.roleid = roleid;
	}

	public SysRoleRightAction(){
		this.service = (SysRoleService) getBean("sysRoleService");
	}
	
	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("选择的roles::::" + this.assignedRightcode+",this.roleid==="+this.roleid);
	//	if (this.roleRights != null && this.roleRights.size() != 0) {
			service.assignRrights(this.roleid, this.assignedRightcode);
	//	}
			this.opResult = "管理员" + super.getLoginUser().getLoginname() + "给角色"+roleid+"分配了"+assignedRightcode+"权限";
		this.message="角色权限分配成功";
		this.nextPage = "sysRoleList.action";
		return SUCCESS;
	}
	@Override
	public String input() throws Exception {
		
		SysRole sysRole=(SysRole)service.getRole(roleid);
		assignedRights=sysRole.getSysRights();
		Iterator<SysRight> _rights=assignedRights.iterator();
		while(_rights.hasNext()){
			
		
			this.assignedRightcode.add(_rights.next().getRightcode());
		}
        
		//获得所有的rights,想在这里实现排序
		allRights=RightTree.getRightList();
//		for(int i=0;i<rights.size();i++){
//			SysRight right=(SysRight)rights.get(i);
////			LOG.debug("==code::"+right.getRightcode()+"=="+right.getRightname()+"==="+right.getGrade());
////			if(!right.getIscommon()){
//				//allRights.put(right.getRightcode(), right.getRightname());
//				allRights.add(right);
////			}
//		}
		return INPUT;
	}
	
	/**
	 * 选中的权限列表
	 */
	private Set<SysRight> assignedRights ;



	public Set<SysRight> getAssignedRights() {
		return this.assignedRights;
	}

	/**
	 * 所有的权限列表
	 */
	private List allRights = new ArrayList();

	public List getAllRights() {
		return this.allRights;
	}
	

	private List<String> assignedRightcode=new ArrayList<String>();
	public List<String> getAssignedRightcode() {
		return this.assignedRightcode;
	}
	public void setAssignedRightcode(List<String> codes){
		this.assignedRightcode=codes;
	}
	
	
}
