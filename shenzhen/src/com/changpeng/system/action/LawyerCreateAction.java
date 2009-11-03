/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.io.File;
import java.util.Iterator;

import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysRole;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 新增律师
 * 
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class LawyerCreateAction extends AbstractAction {

	private SysUser sysUser;
	private int groupid;
	
	// public void setSysUser(SysUser sysUser){
	// this.sysUser=sysUser;
	// }
//	private SysUser loginUser;
	private boolean isadminer; //是否为系统管理员
	
	public boolean getIsadminer(){
		return isadminer;
	}
	
	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

//	public SysUser getLoginUser() {
//		return loginUser;
//	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public LawyerCreateAction() {
		this.rightCode = "lawyerCreate";
		this.sysUser = new SysUser();
	}
	
//	@Override
//	public void validate()
//	{
////		  addFieldError("sysUser.cardno", "The user name has been used!");
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		SysUserService service = (SysUserService) this.getBean("sysUserService");
		if(service.getSysUserByLoginname(sysUser.getLawerno())!=null){
			this.message="对不起，您输入的律师执业证号【"+sysUser.getLawerno()+"】已经被他人使用。";
			return "message";
		}
		
		BasicService basicService = (BasicService) this.getBean("basicService");

		//SysGroup group = (SysGroup) basicService.get(SysGroup.class, this.getLoginUser().getSysGroup().getGroupid());

		
		//Integer roleid=super.getLoginUser().getRoleid();
		//if(roleid!=null&&roleid==3){
		if(groupid==0){
			this.message="对不起，您没有律师事务所。";
			return "message";
		}
			SysGroup group = (SysGroup) basicService.get(SysGroup.class, groupid);
		//}
		if(group.getParentid()==0){
			this.message="对不起，您没有选择具体律师事务所。";
			return "message";
		}
		sysUser.setSysGroup(group);
		sysUser.setRoleid(1);
		// 同时分配律师

		if (upload != null && upload.length() != 0) {
			try {
				if(upload.length()>100*1024){	
					debug("删除上传图片成功否:"+upload.delete());
					this.message="上传的图片大小超出了规定的最大大小100K，请重新选择";
					this.nextPage="lawyerEditSelfPre.pl";
					return "message";
				}
				int index = fileName.lastIndexOf(".");
				String name = System.currentTimeMillis() + fileName.substring(index);
				sysUser.setPhoto(name);

				String indexDir = ServletActionContext.getServletContext().getRealPath("/lawyerphotos");
				File file = new File(indexDir + System.getProperty("file.separator") + name);
				upload.renameTo(file);

				debug("=================" + indexDir);

			}
			catch (Exception e) {
				debug("照片上传失败..." + e);
			}
		}

		sysUser.setLawerno(sysUser.getLawerno().trim());
		sysUser.setLoginname(sysUser.getLawerno().trim());
		sysUser.setPassword(sysUser.getCertno().trim());

		sysUser.setCreateuser(super.getLoginUser().getLoginname());
		// sysUser.setPassword(password1);
		service.addLawyer(sysUser);
		this.nextPage = "lawyerList.pl";
		this.message = "律师新增成功";
		return SUCCESS;
	}

	/**
	 * 这里要将部门信息放出来,树形结构显示
	 */
	@Override
	public String input() throws Exception {
		//
		SysUser loginUser=super.getLoginUser();
		if (loginUser.getLoginname().equals("admin"))
			this.isadminer = true;
		else {
			Iterator<SysRole> roles = loginUser.getSysRoles().iterator();
			short maxroleid = 0;
			while (roles.hasNext()) {
				SysRole role = roles.next();
				if (maxroleid <= role.getRoleid())
					maxroleid = role.getRoleid();
			}
			if (maxroleid == 3) {// 3的角色id是律协管理员
				isadminer = true;

			}
		}
		//SysUser loginuser = this.getLoginUser();
	/*	if (loginUser.getSysGroup() == null) {
			this.message = "您没有所属的部门,不能添加律师";
			this.nextPage = "lawyerList.pl";
			return "message";
		}*/
		if(loginUser.getSysGroup()!=null){
			this.groupid=loginUser.getSysGroup().getGroupid();
		    this.groupname = loginUser.getSysGroup().getGroupname();
		}
		debug("loginUser.groupid:"+groupid);
		
		return INPUT;
	}

	private String groupname;
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	private String passagain;

	public String getPassagain() {
		return this.passagain;
	}

	public void setPassagain(String passagain1) {
		this.passagain = passagain1;
	}

	// private String password1;
	// public String getPassword1(){
	// return this.password1;
	// }
	// public void setPassword1(String password1){
	// this.password1=password1;
	// }

	private File upload;
	private String fileName;

	public String getUploadFileName() {
		return fileName;
	}

	public void setUploadFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

}
