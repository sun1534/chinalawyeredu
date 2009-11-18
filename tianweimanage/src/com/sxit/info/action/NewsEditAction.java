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
public class NewsEditAction extends AbstractAction{

	public NewsEditAction(){
		
	}
	protected String go()throws Exception{
	
		info.setModifytime(new java.sql.Timestamp(System.currentTimeMillis()));
		
		info.setModifyuser(this.getLoginUser().getUserid());
		
		if (upload != null && upload.length() != 0) {
			try {
				if (upload.length() > 5000 * 1024) {
					this.message = "上传的图片大小超出了规定的最大大小5M，请重新选择";
					this.nextPage = "newsEdit!input.action?infoid=" +info.getInfoid();
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
				LOG.error("广告内容上传失败::" + e);

				this.nextPage = "广告内容上传失败::" + e;
				return "message";
			}
		} else {
			info.setFilenumber(oldpic);
		}
		basicService.update(info);
		
		this.message="信息修改成功";
		this.nextPage="newsList.action?thetype="+info.getTypeid();
		
		return SUCCESS;
	}
	
	public String input(){
		
		this.info=(TinfInfo)basicService.get(TinfInfo.class, infoid);
		this.oldpic=info.getFilenumber();
		set("info",info);
		
		return INPUT;
	}
	
	private TinfInfo info;
	public TinfInfo getInfo(){
		if(info==null)
			info=(TinfInfo)get("info");
		return this.info;
	}
	
	private int infoid;
	public int getInfoid() {
		return infoid;
	}
	public void setInfoid(int infoid) {
		this.infoid = infoid;
	}
	private String oldpic;
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
	public String getOldpic() {
		return oldpic;
	}
	public void setOldpic(String oldpic) {
		this.oldpic = oldpic;
	}
}
