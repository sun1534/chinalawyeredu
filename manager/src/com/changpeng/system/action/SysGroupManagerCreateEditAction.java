/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysUser;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 对具体部门的管理员的新增和修改列表
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysGroupManagerCreateEditAction extends AbstractListAction {

	private SysUser sysUser;
	private String oldloginname;
	public SysUser getSysUser() {
		if (sysUser == null)
			this.sysUser = (SysUser) get("sysUser");
		return sysUser;
	}

	public SysGroupManagerCreateEditAction() {

	}

	protected String go() throws Exception {
		BasicService bs = (BasicService) this.getBean("basicService");
		SysUserService service = (SysUserService) this.getBean("sysUserService");

		if (!isedit) {

			if (service.getSysUserByLoginname(sysUser.getLoginname()) != null) {
				this.message = "对不起，您输入的帐号【" + sysUser.getLoginname() + "】已经被他人使用。";
				return "message";
			}
			if (!sysUser.getPassword().equals(passagain)) {
				this.message = "两次密码输入不同,请重新输入!";
				return "message";
			}

			SysGroup group = (SysGroup) bs.get(SysGroup.class, groupid);

			sysUser.setCreateuser(super.getLoginUser().getUsername());
			sysUser.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			sysUser.setCreateuserid(this.getLoginUser().getUserid());

			SysRole role = new SysRole();
			if (group.getGrouptype() == 1) { // 事务所
				sysUser.setCityid(group.getParentid());
				sysUser.setProvinceid(group.getDirectgroup());
				sysUser.setOfficeid(group.getGroupid());
				role.setRoleid(1);
			} else if (group.getGrouptype() == 2) {
				sysUser.setCityid(groupid);
				sysUser.setProvinceid(group.getParentid());
				role.setRoleid(2);
			} else if (group.getGrouptype() == 3) {
				sysUser.setProvinceid(groupid);
				role.setRoleid(3);
			} else {
				role.setRoleid(5);
			}
			sysUser.setSysRole(role);
			sysUser.setSysGroup(group);
			service.addUser(sysUser);

			this.opResult = "管理员" + super.getLoginUser().getUsername() + "新增了用户" + sysUser.getUsername();
			this.message = "管理员信息新增成功";
		} else {
			
			SysUser olduser=service.getSysUserByLoginname(sysUser.getLoginname()) ;
			if(olduser!=null&&sysUser.getLoginname().equals(oldloginname)){
				this.message = "对不起，您输入的帐号【" + sysUser.getLoginname() + "】已经被他人使用。";
				return "message";
			}
			
			this.message = "管理员信息修改成功";
			bs.update(sysUser);
			this.opResult = "管理员" + super.getLoginUser().getUsername() + "修改了用户" + sysUser.getUsername();
		}
		this.nextPage = "sysGroupManager.pl?groupid=" + groupid;
		return SUCCESS;

	}

	@Override
	public String input() throws Exception {

		BasicService bs = (BasicService) this.getBean("basicService");
		
		SysGroup group = (SysGroup) bs.get(SysGroup.class, groupid);
		if (group == null) {
			this.message = "这个部门已经不存在,请重新选择";
			return "message";
		}
		SysUser sysuser = (SysUser) bs.get(SysUser.class, userid);
		
		if (sysuser == null) { // 要新增的
			sysuser = new SysUser();
			isedit = false;
			this.oldloginname="";
		} else {
			isedit = true;
			this.oldloginname=sysuser.getLoginname();
		}

		set("sysUser", sysuser);
		this.groupname = group.getGroupname();
		return INPUT;
	}

	private String passagain;

	public void setPassagain(String passagain1) {
		this.passagain = passagain1;
	}

	private boolean isedit;

	private int groupid;

	/**
	 * @return the groupid
	 */
	public int getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid
	 *            the groupid to set
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	private String groupname;

	public String getGroupname() {
		return this.groupname;
	}

	private int userid;

	public int getUserid() {
		return this.userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the isedit
	 */
	public boolean isIsedit() {
		return isedit;
	}

	/**
	 * @param isedit
	 *            the isedit to set
	 */
	public void setIsedit(boolean isedit) {
		this.isedit = isedit;
	}

	/**
	 * @return the oldloginname
	 */
	public String getOldloginname() {
		return oldloginname;
	}

	/**
	 * @param oldloginname the oldloginname to set
	 */
	public void setOldloginname(String oldloginname) {
		this.oldloginname = oldloginname;
	}

}