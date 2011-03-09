package com.uu800.webbase;

import java.io.File;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 上传的基础类，主要处理将文件copy到对应的某个目录下
 * 
 * @author 华锋 Aug 20, 2010 1:25:32 PM
 */
public abstract class AbstractUploadAction extends ActionSupport {

	private static Log LOG = LogFactory.getLog(AbstractUploadAction.class);

	// 用File数组来封装多个上传文件域对象
	private File[] upload;
	// 用String数组来封装多个上传文件名
	private String[] uploadFileName;
	// 用String数组来封装多个上传文件类型
	private String[] uploadContentType;

	public AbstractUploadAction(){
		
	}
	
	@Override
	public String execute() throws Exception {

		if (upload != null && upload.length > 0) {
			try {
				// 处理每个要上传的文件
				String result = "sysmsg";

				for (int i = 0; i < upload.length; i++) {
					LOG.debug("fileName:"+uploadFileName[i]+",type:"+uploadContentType[i]);
					String regix = uploadFileName[i].substring(uploadFileName[i].lastIndexOf("."));
					// 根据服务器的文件保存地址和 "原文件名+时间戳"创建目录文件全路径
				}
				return fileUploaded();
			} catch (Exception e) {
				return fileUploadedError(e);
			}
		
		} else {
			return fileUploadedNoFile();
		}
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public abstract String fileUploaded()throws Exception;

	public abstract String fileUploadedNoFile() throws Exception;
	
	public abstract String fileUploadedError(Exception e) throws Exception;

}