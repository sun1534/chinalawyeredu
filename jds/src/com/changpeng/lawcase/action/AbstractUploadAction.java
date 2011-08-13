package com.changpeng.lawcase.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.sxit.common.action.AbstractAction;

@SuppressWarnings("unused")
public abstract class AbstractUploadAction extends AbstractAction {

	private static Logger log = Logger.getLogger(AbstractUploadAction.class);
	private static final DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
	private static final long serialVersionUID = 572146812454l;
	private static final int BUFFER_SIZE = 16 * 1024;

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
	protected String uploadpath = "origin";

	@Override
	public String execute() throws Exception {
		String userip = ServletActionContext.getRequest().getRemoteAddr();
		try {
			// 处理每个要上传的文件
			String result = "message";
			if (upload != null) {

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

}