/**
 * TSysUserAddAction.java
 */

package com.sxit.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.BasicService;
import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysRole;
import com.sxit.models.system.SysUser;
import com.sxit.system.service.SysRoleService;
import com.sxit.system.service.SysUserService;
import com.sxit.system.util.CommonDatas;

/**
 * 
 * 新增用户所属的角色，先显示所有的角色，checkbox选中了的为已有的 原则： 1、每个人只1个角色
 * 2、可以分配的角色为roles中大于所属角色的grandeid
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysUserRoleAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(SysUserRoleAction.class);
	private int userid;
	private SysUserService service;

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getUserid() {
		return this.userid;
	}

	public SysUserRoleAction() {
		// this.rightCode = "sysUserRole";
		service = (SysUserService) this.getBean("sysUserService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		// LOG.debug("选择的roles::::" + this.sysRoles + ",this.Userid===" +
		// this.userid);
//		List sysRoles = new ArrayList();
//		if (roleid!=null&&roleid.size()!=0) {
//			sysRoles.add(roleid.get(0));
//		}

//		if (sysRoles != null && sysRoles.size() != 0) {
		SysRoleService sysRoleService = (SysRoleService) this.getBean("sysRoleService");
		short _roleid=0;
		if (roleid != null && roleid.size() != 0) {
			// service.assignRoles(this.userid, this.sysRoles, locationid);
			service.assignRoles(this.userid, roleid, 0);
			_roleid=Short.parseShort(roleid.get(0).toString());
			
		
			this.opResult = "管理员" + super.getLoginUser().getLoginname() + "给用户" + CommonDatas.getLoginNameByUserId(String.valueOf(userid)) + "分配了"
			+ sysRoleService.getRole(_roleid).getRolename() + "角色";
			
			this.message = "角色分配成功";
		} else {
			
			this.message = "您必须为该用户分配一个角色,不能为空!";
			return "message";
			
//			service.assignRoles(this.userid, new ArrayList(), 0);
			
//			this.opResult = "管理员" + super.getLoginUser().getLoginname() + "给用户" + CommonDatas.getLoginNameByUserId(String.valueOf(userid)) + "取消了角色";
//			this.message = "您的角色清除成功,请确认";
		}
		
		
		this.nextPage = "sysUserList.action";

		return SUCCESS;
	}

	// // 存储这个人已分配的角色id
	// private List sysRoles = new ArrayList();
	//
	// public void setSysRoles(List sysRoles) {
	// this.sysRoles = sysRoles;
	// }
	//
	// public List getSysRoles() {
	// return this.sysRoles;
	// }

	private Map roles = new HashMap();

	public Map getRoles() {
		return this.roles;
	}

	private List roleid=new ArrayList();

	public void setRoleid(List roleid) {
		this.roleid = roleid;
	}

	public List getRoleid() {
		return this.roleid;
	}

	@Override
	public String input() throws Exception {

		// if (this.getLoginUser().getLocationid() == 0)
		// provinceList = LocationTree.getDirectionChildren(0);
		// else {
		// provinceList = new HashMap();
		// provinceList.put(this.getLoginUser().getLocationid(),
		// LocationTree.locationNameMap.get(this.getLoginUser().getLocationid()));
		// }

		// 得到这个人所属的角色
		SysUser user = service.getUser(userid);
		// this.locationid = user.getLocationid();
		Set<SysRole> _sysRoles = user.getSysRoles();

		for (SysRole role : _sysRoles) {
			// sysRoles.add((int) role.getRoleid());
			roleid .add((int) role.getRoleid());
			break;
		}

		// 这个人是地市管理员的话
		// if (sysRoles.contains(2)) {
		// provinceid = (Integer)
		// LocationTree.locationIdMap.get(user.getLocationid());
		// cityList = LocationTree.getDirectionChildren(provinceid);
		// cityid = user.getLocationid();
		// // System.out.println(cityList);
		// } else if (sysRoles.contains(1)) {
		// provinceid = user.getLocationid();
		// }else{
		// provinceid=user.getLocationid();
		// // cityid=user.getLocationid();
		// }

		SysRoleService roleService = (SysRoleService) this.getBean("sysRoleService");
		List allroles = null;
		// List loginroleids = new ArrayList();
		if (!this.getLoginUser().getLoginname().equals("admin")) {
			SysRole sysrole = null;
			Set<SysRole> loginroles = this.getLoginUser().getSysRoles();			
			if(loginroles==null||loginroles.size()==0){
				this.message="您没有所属的角色,不能为用户分配角色,请返回";
				this.nextPage="sysUserList.action";
				return "message";
			}			
			for (SysRole role : loginroles) {
				sysrole = role;
				break;
			}
			BasicService bs = (BasicService) this.getBean("basicService");
			DetachedCriteria dc = DetachedCriteria.forClass(SysRole.class);			
			Criterion c1=Restrictions.gt("gradeid", sysrole.getGradeid());
			Criterion c2=Restrictions.eq("roleid", sysrole.getRoleid());			
			dc.add(Restrictions.or(c1, c2));
			allroles = bs.findAllByCriteria(dc);
		} else { // admin的话，显示所有的角色
			allroles = roleService.getRoles();
		}
		for (int i = 0; i < allroles.size(); i++) {
			SysRole role = (SysRole) allroles.get(i);
			roles.put((int) role.getRoleid(), role.getRolename());
		}

		return INPUT;

	}
	// private int locationid;
	//
	// public int getLocationid() {
	// return locationid;
	// }
	//
	// public void setLocationid(int locationid) {
	// this.locationid = locationid;
	// }

	// private Map provinceList;
	//
	// public Map getProvinceList() {
	//
	// return provinceList;
	// }
	//
	// private Map cityList;
	//
	// public Map getCityList() {
	// return this.cityList;
	// }
	//
	// private int provinceid;
	// private int cityid;
	//
	// public int getProvinceid() {
	// return this.provinceid;
	// }
	//
	// public int getCityid() {
	// return cityid;
	// }
}