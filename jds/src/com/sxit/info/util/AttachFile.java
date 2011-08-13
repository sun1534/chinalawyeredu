/**
 * 
 */
package com.sxit.info.util;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import com.sxit.info.model.TinfAttach;

/**
 * @author sg
 * @TODO 上传文件
 * @createtime 2008-9-4
 * @version v1.0
 */
public class AttachFile {

	public static String upload(File upload, String fileName) {
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"\\uploads\\info\\");

		java.util.Date nowDate = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyyMMddHHmmSSS");
		sdf.format(nowDate);
		String filePath2 = filePath + java.io.File.separator
				+ sdf.format(nowDate);
		java.io.File file = new java.io.File(filePath2);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdir();
		}

		System.out.println("uploadFileName====================" + fileName);
		upload.renameTo(new File(filePath2 + "\\" + fileName));
		return ("/uploads/info/" + sdf.format(nowDate) + "/" + fileName);
	}

	public static void delete(String filepath) {
		System.out.println("path======"
				+ ServletActionContext.getServletContext().getRealPath("")
				+ filepath.replace("/", "\\"));
		String filepath2 = ServletActionContext.getServletContext()
				.getRealPath("")
				+ filepath.replace("/", "\\");
		String dirpath = filepath2.substring(0, filepath2.lastIndexOf("\\"));
		File file = new File(filepath2);
		if (file.exists()) {
			file.delete();
		}
		File dir = new File(dirpath);
		if (dir.exists()) {
			dir.delete();
		}
	}

}
