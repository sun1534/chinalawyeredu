package com.changpeng.sns.diary.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.util.FileDirUtil;

public class UploadPicAction extends AbstractAction {

	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	public UploadPicAction() {
		this.rightCode = "PUBLIC";
	}

	@Override
	protected String go() throws Exception {
//		System.out.println("upload:"+upload);
//		System.out.println("uploadContentType:"+uploadContentType);
//		System.out.println("uploadFileName:"+uploadFileName);
		try{
			DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String regix = uploadFileName.substring(uploadFileName.lastIndexOf("."));
			// 根据服务器的文件保存地址和 "原文件名+时间戳"创建目录文件全路径
			String newFileName = currentUserid + "_" + df.format(new Date()) + regix;
			newFileName = newFileName.replaceAll("\\s", "_");
			
			this.result="{\"err\":\"\","+"\"msg\":\""+getRelativePath("blogpic", 123) + newFileName+"\"}";
			System.out.println("result:"+result);
			String realpath = getRealPath("blogpic", 123);
			String savepath = realpath + newFileName;
			File savefile = new File(savepath);
			copy(upload, savefile);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	private static final int BUFFER_SIZE = 16 * 1024;
	private static void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
protected String getRealPath(String type, int id) {
		
		DecimalFormat nf = new DecimalFormat("000000000");
		String str = nf.format(id);
		String p = ServletActionContext.getServletContext().getRealPath("") + "/" + type + "/" + FileDirUtil.getDirBySeq(id);
		File fp = new File(p);
		if (!fp.exists()) {
			fp.mkdirs();
			try{
				//Runtime.getRuntime().exec("chmod 777 "+p);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return p;
	}

	protected String getRelativePath(String type, int id) {
		DecimalFormat nf = new DecimalFormat("000000000");
		String str = nf.format(id);
		String p = "/" + type + "/" + FileDirUtil.getDirBySeq(id);
		return p;
	}
}
