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
public class LawyerViewAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(LawyerViewAction.class);
	private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

	private SysUserService service;

	private SysUser sysUser;
	private int userid;

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public SysUser getSysUser() {
		
		return this.sysUser;

	}

	public LawyerViewAction() {
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


//		SysUser loginuser = this.getLoginUser();

		this.sysUser = service.getUser(userid);
		_LOG.debug("主键值:::" + sysUser.getUserid());
		this.groupname = sysUser.getSysGroup().getGroupname();
		set("sysUser", sysUser);

		return SUCCESS;
	}


	private String groupname;

	public String getGroupname() {
		return this.groupname;
	}

//	// private String passagain;
//	// public String getPassagain(){
//	// return this.passagain;
//	// }
//	// public void setPassagain(String passagain1){
//	// this.passagain=passagain1;
//	// }
//	private File upload;
//	private String fileName;
//
//	public String getUploadFileName() {
//		return fileName;
//	}
//
//	public void setUploadFileName(String fileName) {
//		this.fileName = fileName;
//	}
//
//	public File getUpload() {
//		return upload;
//	}
//
//	public void setUpload(File upload) {
//		this.upload = upload;
//	}
}
