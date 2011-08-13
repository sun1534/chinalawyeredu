package com.sxit.info.action;


import java.io.File;
import java.util.Calendar;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;
import com.sxit.info.util.AttachFile;


/**
 *
 * <p>功能： 创建附件</p>
 * <p>作者： 吴桂荣</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class AttachCreateAction extends AbstractAction {

	private TinfAttach attach;
	private File upload;
	private String uploadFileName;
	
	private TinfInfo info;
	private String uploadContentType;


	public AttachCreateAction() {
           rights="inf,0";
		attach = new TinfAttach();
	}

	public String go() throws HibernateException {

		attach.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		info = (TinfInfo)get("info");
		if(upload != null&& uploadFileName!=null&&!uploadFileName.equals("")){

			String filepath2 = AttachFile.upload(upload, uploadFileName);
			TinfAttach attach = new TinfAttach();
			attach.setTinfInfo(info);
			attach.setFilename(uploadFileName);
			attach.setFilepath(filepath2);
			System.out.println(filepath2);
			getSession().save(attach);
		}
		set("attach", attach);
        message="保存成功！";
		return SUCCESS;
	}

	public String input() throws Exception {
		info = (TinfInfo)get("info");
		set("info",info);
		return "input";
	}
	
	public TinfInfo getInfo(){
		if(info==null){
			info = (TinfInfo)get("info");
		}
		return this.info;
	}
	
	public TinfAttach getAttach() {
		return attach;
	}

	public File getUpload() {
		return this.upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return this.uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public String getUploadContentType(){
		return this.uploadContentType;
	}
	
	public void setUploadContentType(String uploadContentType){
		this.uploadContentType = uploadContentType;
	}
	
	public String getNextpage(){
		return this.nextpage;
	}
	
	public void setNextpage(String nextpage){
		this.nextpage = nextpage;
	}
}
