/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysRole;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 律师信息修改
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class LawyerEditAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(LawyerEditAction.class);
	private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

	private SysUserService service;

	private SysUser sysUser;
	private int userid;

	private int groupid;

	// private SysUser loginUser;
	private boolean isadminer; // 是否为系统管理员

	public boolean getIsadminer() {
		return isadminer;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	// public SysUser getLoginUser() {
	// return loginUser;
	// }

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public SysUser getSysUser() {
		if (sysUser == null)
			sysUser = (SysUser) get("sysUser");
		return sysUser;

	}

	public LawyerEditAction() {
		service = (SysUserService) this.getBean("sysUserService");
		this.rightCode = "lawyerEdit";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		BasicService basicService = (BasicService) this.getBean("basicService");

		if (groupid == 0) {
			this.message = "对不起，您没有选择具体律师事务所。";
			return "message";
		}

		if (service.getSysUser(sysUser.getLawerno(), sysUser.getUserid()) != null) {
			this.message = "对不起，您输入的律师执业证号【" + sysUser.getLawerno() + "】已经被他人使用。";
			return "message";
		}

		SysGroup group = (SysGroup) basicService.get(SysGroup.class, groupid);
		if (group.getParentid() == 0) {
			this.message = "对不起，您没有选择具体律师事务所。";
			return "message";
		}
		sysUser.setSysGroup(group);

		_LOG.debug("主键值:::" + sysUser.getUserid());

		if (upload != null && upload.length() != 0) {
			try {
				if (upload.length() > 1000 * 1024) {
					_LOG.info("删除上传图片成功否:" + upload.delete());
					this.message = "上传的图片大小超出了规定的最大大小1000K，请重新选择";
					// this.nextPage="lawyerEditSelfPre.pl";
					return "message";
				}

				int index = fileName.lastIndexOf(".");
				String name = System.currentTimeMillis() + fileName.substring(index);
				sysUser.setPhoto(name);

				String indexDir = ServletActionContext.getServletContext().getRealPath("/lawyerphotos");
				// try {
				if (sysUser.getPhoto() != null && !"".equals(sysUser.getPhoto())) {
					// 删除掉之前的照片
					File oldfile = new File(indexDir + System.getProperty("file.separator") + sysUser.getPhoto());
					_LOG.info("老照片是否删除成功:" + oldfile.getAbsoluteFile() + ":::" + oldfile.delete());
				}
				// }
				// catch (Exception e) {
				// debug("之前的照片删除失败..." + e);
				// throw e;
				// }
				File file = new File(indexDir + System.getProperty("file.separator") + name);
				upload.renameTo(file);

				debug("=================" + indexDir);
			}
			catch (Exception e) {
				debug("照片上传失败..." + e);
				throw e;
			}
		}

		// 登录名与执行证号保持一致
		sysUser.setLoginname(sysUser.getLawerno().trim());
		sysUser.setLawerno(sysUser.getLawerno().trim());

		service.updateUser(sysUser);

		this.message = "律师信息修改成功,请确认";
		this.nextPage = "lawyerList.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		SysUser sysUser = service.getUser(userid);
		SysUser loginUser = super.getLoginUser();

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

		/*
		 * if (loginUser.getSysGroup() == null) { this.message = "您没有所属的部门,不能进行修改"; this.nextPage = "lawyerList.pl"; return "message"; }
		 */

		if (userid == 0) {// 修改自己的
			userid = loginUser.getUserid();
		}

		if (sysUser.getSysGroup() != null) {
			this.groupid = sysUser.getSysGroup().getGroupid();
			this.groupname = sysUser.getSysGroup().getGroupname();
		}
		else {
			this.groupname = "该律师没有所属的事务所";
		}

		set("sysUser", sysUser);

		return INPUT;
	}

	private String groupname;

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	// private String passagain;
	// public String getPassagain(){
	// return this.passagain;
	// }
	// public void setPassagain(String passagain1){
	// this.passagain=passagain1;
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
