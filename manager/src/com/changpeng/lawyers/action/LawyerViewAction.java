package com.changpeng.lawyers.action;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;

import com.changpeng.models.Lawyers;
import com.changpeng.models.SysGroup;

/**
 * 律师信息查看
 * @author sinhoo
 * Jun 6, 2009
 */
public class LawyerViewAction extends AbstractAction {

//	private static Log _LOG = LogFactory.getLog(LawyerViewAction.class);
//	private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

	private BasicService service;

	private Lawyers sysUser;
	private int userid;

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Lawyers getSysUser() {
		
		return this.sysUser;

	}

	public LawyerViewAction() {
		service = (BasicService) this.getBean("basicService");
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

		this.sysUser = (Lawyers)service.get(Lawyers.class, userid);
		SysGroup sysGroup=(SysGroup)service.get(SysGroup.class,sysUser.getTheoffice());
		if(sysGroup!=null)
			this.groupname =sysGroup.getGroupenname(); // sysUser.get.getGroupname();


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
