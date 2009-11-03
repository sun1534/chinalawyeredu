package com.changpeng.lessons.action;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.changpeng.common.action.AbstractAction;

public class DownloadAction extends AbstractAction {
	private String inputPath;
	private String contentType;
	private String filename;
	
/*	private String file;

	public void setFile(String file) {
		this.file=file;
	}*/
	
	public InputStream getInputStream() throws Exception {
		return ServletActionContext.getServletContext().getResourceAsStream(
				inputPath);

	}

	public String go() throws Exception {
		
		inputPath = "/uploads/"+filename;//要下载的文件名称
		//filename = lesson.getAttach(); //保存文件时的名称
		contentType = "application/bin"; 
		ServletActionContext.getResponse().setHeader("Content-Disposition",
				"attachment; filename=" + filename);
		return SUCCESS;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
