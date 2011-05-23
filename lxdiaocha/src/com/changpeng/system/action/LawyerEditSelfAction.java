/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 律师信息修改
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class LawyerEditSelfAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(LawyerEditSelfAction.class);
	private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

	private SysUserService service;

	private SysUser sysUser;
	private int userid;

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public SysUser getSysUser() {
		if (sysUser == null)
			sysUser = (SysUser) get("sysUser");
		return sysUser;

	}

	public LawyerEditSelfAction() {
		service = (SysUserService) this.getBean("sysUserService");
		// this.rightCode = "lawyerEdit";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		_LOG.debug("主键值:::" + sysUser.getUserid());

		if (upload != null && upload.length() != 0) {
			try {

				if (upload.length() > 100 * 1024) {
					debug("删除上传图片成功否:" + upload.delete());
					this.message = "上传的图片大小超出了规定的最大大小100K，请重新选择";
					this.nextPage = "lawyerEditSelfPre.pl";
					return "message";
				}

				int index = fileName.lastIndexOf(".");
				String name = System.currentTimeMillis() + fileName.substring(index);
				sysUser.setPhoto(name);

				String indexDir = ServletActionContext.getServletContext().getRealPath("/lawyerphotos");

				if (sysUser.getPhoto() != null && !"".equals(sysUser.getPhoto())) {
					// 删除掉之前的照片
					File oldfile = new File(indexDir + System.getProperty("file.separator") + sysUser.getPhoto());
					debug("老照片是否删除成功:" + oldfile.getAbsoluteFile() + oldfile.delete());
				}

				File file = new File(indexDir + System.getProperty("file.separator") + name);
				upload.renameTo(file);

				debug("=================" + indexDir);
			}
			catch (Exception e) {
				debug("照片上传失败..." + e);
				throw e;
			}
		}

		service.updateUser(sysUser);
		
//		set(com.changpeng.common.Constants.LOGIN_USER,sysUser);

		this.message = "您的个人资料信息修改成功,请确认";
		if (comefrom != null && !"".equals(comefrom))
			this.nextPage = "../index/workspace.pl";
		else
			this.nextPage = "../system/lawyerEditSelfPre.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		SysUser loginuser = this.getLoginUser();
		// if (loginuser.getSysGroup() == null) {
		// this.message = "您没有所属的部门,不能进行修改";
		// this.nextPage = "lawyerList.pl";
		// return "message";
		// }

		if (userid == 0) {// 修改自己的
			userid = loginuser.getUserid();
		}

		SysUser sysUser = service.getUser(userid);
		if (sysUser.getSysGroup() != null) {
			this.groupname = sysUser.getSysGroup().getGroupname();
		}
		else {
			this.groupname = "该用户没有所属的事务所机构";
		}
		set("sysUser", sysUser);

		if (sysUser.getRoleid() != null && sysUser.getRoleid().intValue() == 1) {
			return "lawyer";
		}
		else {
			return "adminer";
		}

		// return INPUT;
	}

	private String comefrom;

	public void setComefrom(String from) {
		this.comefrom = from;
	}

	private String groupname;

	public String getGroupname() {
		return this.groupname;
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
