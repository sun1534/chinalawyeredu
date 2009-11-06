package com.changpeng.common.action;

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

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

@SuppressWarnings("unused")
public abstract class AbstractUploadAction extends AbstractAction {

	private static Logger log = Logger.getLogger(AbstractUploadAction.class);
	private static final DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
	private static final long serialVersionUID = 572146812454l;
	private static final int BUFFER_SIZE = 16 * 1024;

	// 文件标题
	private String title;
	// 用File数组来封装多个上传文件域对象
	protected File[] upload;
	// 用String数组来封装多个上传文件名
	protected String[] uploadFileName;
	// 用String数组来封装多个上传文件类型
	private String[] uploadContentType;
	// 保存文件的目录路径(通过依赖注入)
	private String savePath;

	// 上传后的临时文件路径 ,每个类中操作这个路径
	protected String[] filenames;

	protected String[] dstPath;

	@Override
	public String execute() throws Exception {
		userip = ServletActionContext.getRequest().getRemoteAddr();
		// if (get("currentUserid") != null && get("currentRole") != null) {
		// currentUserid = (Integer) get("currentUserid");
		// currentRole = (Integer) get("currentRole");
		// } else if (!rightCode.equals("LOGIN")) {
		// return LOGIN;
		// }
		try {
			// 处理每个要上传的文件
			String result = "message";
			if (upload != null) {
				filenames = new String[upload.length];
				dstPath = new String[upload.length];
				for (int i = 0; i < upload.length; i++) {
					if (uploadFileName[i].lastIndexOf(".") + 1 == uploadFileName[i].length()) {
						this.message = "您上传的文件格式错误";
						return ERROR;
					}
					java.util.Random random = new java.util.Random();
					String regix = uploadFileName[i].substring(uploadFileName[i].lastIndexOf("."));
					// 根据服务器的文件保存地址和 "原文件名+时间戳"创建目录文件全路径
					String newFileName = currentUserid + "_" + df.format(new Date())+ "_"+i + regix;
					newFileName = newFileName.replaceAll("\\s", "_");

					String realpath = getRealPath("origin", currentUserid);
					dstPath[i] = realpath + newFileName;
					filenames[i] = getRelativePath("origin", currentUserid) + newFileName;
					File dstFile = new File(dstPath[i]);
					copy(upload[i], dstFile);
				}
			}
			result = go();

			return result;

		} catch (Exception e) {
			log.debug(e.getMessage());
			e.printStackTrace();
			return ERROR;
		} finally {
		}

	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

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
		String p = ServletActionContext.getServletContext().getRealPath(savePath) + "/" + type + "/" 
				+ str.substring(3, 5) + "/" + str.substring(5, 7) + "/";
		File fp = new File(p);
		if (!fp.exists()) {
			fp.mkdirs();
		}
		return p;
	}

	protected String getRelativePath(String type, int id) {
		DecimalFormat nf = new DecimalFormat("000000000");
		String str = nf.format(id);
		String p = savePath + "/" + type + "/" + str.substring(3, 5) + "/"
				+ str.substring(5, 7) + "/";
		return p;
	}
}