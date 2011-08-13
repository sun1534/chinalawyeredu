/**
 * 
 */
package com.changpeng.lawcase.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawFiles;
import com.changpeng.lawcase.util.LawcaseCreateBatch;
import com.changpeng.lawcase.workflow.WorkFlowAction;

/**
 * 
 * 批量新增案件,这里的话,要判断是否案件已经分配的情况
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class CaselawCreateBatchAction extends WorkFlowAction {

	public String input() throws Exception {
		return INPUT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		System.out.println(fileName);
		System.out.println(file);

		if (fileName != null && !"".equals(fileName)) {
			String extendPath = "/uploads/";
			String name = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
			String toPath = ServletActionContext.getServletContext().getRealPath("") + extendPath;
			String ext = getExtention(fileName);
			String filename = name + ext;
			try {
				FileUtils.forceMkdir(new File(toPath)); // 创建目录
				File dest = new File(toPath + filename);
				FileUtils.copyFile(file, dest); // 移动文件
				if (!ext.equalsIgnoreCase(".xls")) {
					message = "上次的文件必须为.xls格式文件";
					FileUtils.forceDelete(dest);
					return ERROR;
				} else {
					// 将上传的文件保存
					TlawFiles file = new TlawFiles();
					file.setFilename(filename);
					file.setPath(toPath + filename);
					file.setRemarks("批量上传的诉讼案件");
					file.setUploadtime(new java.sql.Timestamp(System.currentTimeMillis()));
					file.setUploaduserid(this.curuser.getUserid());
					file.setUploadusername(this.curuser.getUsername());
					file.setType(1);
					file.setNodeid(21001);
					file.setActionid(0);
					getSession().save(file);

					long fileid = file.getFileid();

					// List<TnlwNonlaw>
					// list=com.changpeng.lawcase.util.save(dest, consigndate,
					// bankid, getSession());

					try {
						List list = LawcaseCreateBatch.save(dest, this.curuser, fileid, getSession());

						if (list != null && list.size() > 0) {
							message = "下列案件导入错误，如需继续，请手动录入：<br>";
							for (Object obj : list) {
								message = message + obj.toString() + "<br>";
							}

						} else {
							message = "批量案件导入成功,请返回";
						}
					} catch (Exception e) {
						e.printStackTrace();
						this.message = "文件解析错误,请重试:" + e.getMessage();
						FileUtils.forceDelete(dest);
						return "message";
					}
				}
			} catch (IOException e) {
				message = "上传委托文件错误：" + e.getMessage();
				return ERROR;
			}
		} else {
			message = "上传文件不能为空";
			return ERROR;
		}

		this.message = "案件上传保存处理成功";
		this.nextpage = "newcaseList.action";

		return SUCCESS;
	}

	private String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	private File file;
	private String fileName;

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileName;
	}

	public void setFileFileName(String fileName) {
		this.fileName = fileName;
	}

}
