/**
 * 
 */
package com.sxit.info.action;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.info.TinfInfo;

/**
 * @author 华锋
 * Jul 8, 2009 11:27:42 PM
 *
 */
public class NewsCreateAction extends AbstractAction{

	
	public NewsCreateAction(){
		this.info=new TinfInfo();
	}
	protected String go()throws Exception{
		System.out.println("====="+info.getContent());
		
		if (upload != null && upload.length() != 0) {
			try {
				if (upload.length() > 5000 * 1024) {
					this.message = "上传的图片大小超出了规定的最大大小5M，请重新选择";
					this.nextPage = "newsCreate!input.action?thetype=" +thetype;
					return "message";
				}
				int index = uploadFileName.lastIndexOf(".");
				String name = System.currentTimeMillis() + uploadFileName.substring(index);
				// sysUser.setPhoto(name);
				String indexDir = ServletActionContext.getServletContext().getRealPath("/upload/info/");
				File dir = new File(indexDir);
				if (!dir.exists())
					dir.mkdirs();
				File file = new File(indexDir + System.getProperty("file.separator") + name);
				upload.renameTo(file);
				info.setFilenumber("/upload/info/" + name);
			} catch (Exception e) {
				LOG.error("内容上传失败::" + e);

				this.nextPage = "新闻广告图片上传失败::" + e;
				return "message";
			}
		} 
		
		
		info.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		info.setCreateuser(this.getLoginUser().getUserid());
		info.setGroupid(this.getLoginUser().getSysGroup()==null?0:this.getLoginUser().getSysGroup().getGroupid());
		info.setStatusid(0);
		info.setWaitid("");
		basicService.save(info);
		
		
		
		this.message="信息新增成功";
		this.nextPage="newsList.action?thetype="+info.getTypeid();
		
		return SUCCESS;
	}
	public String input(){
		
		info.setTypeid(thetype);
		return INPUT;
	}
	
	private int thetype;
	
	private TinfInfo info;
	public TinfInfo getInfo(){
		return this.info;
	}
	public void setThetype(int thetype) {
		this.thetype = thetype;
	}
	
	
	

	private File upload;
	private String uploadFileName;


	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
}
