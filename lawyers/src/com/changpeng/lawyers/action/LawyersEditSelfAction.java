/**
 * TSysUserAddAction.java
 */

package com.changpeng.lawyers.action;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.Constants;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lawyers;

/**
 * 
 * 律师信息修改
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class LawyersEditSelfAction extends AbstractAction {
	private static Log _LOG = LogFactory.getLog(LawyersEditSelfAction.class);

	public LawyersEditSelfAction() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		com.changpeng.common.BasicService bs = (com.changpeng.common.BasicService) this.getBean("basicService");
		lawyers.setLawyerenname(com.changpeng.common.util.Chinese2Pinyin.to2pinyin(lawyers.getLawyername()));

		// if (lawyers.getTheoffice() == 0) {
		// this.message = "必须选择所在的事务所,请返回";
		// return "message";
		// }

		if (upload != null && upload.length() != 0) {
			try {

				if (upload.length() > 1000 * 1024) {
					debug("删除上传图片成功否:" + upload.delete());
					this.message = "上传的图片大小超出了规定的最大大小1000K，请重新选择";

					return "message";
				}
				int index = fileName.lastIndexOf(".");
				String name = System.currentTimeMillis() + fileName.substring(index);

				String indexDir = ServletActionContext.getServletContext().getRealPath(
						"/lawyerphotos/" + lawyers.getDirectunion() + "/");
				File filedir = new File(indexDir);

				if (!filedir.exists()) {
					boolean s = filedir.mkdirs();
					debug("文件路径:::" + indexDir + "创建成功...");
				}

				File file = new File(indexDir + System.getProperty("file.separator") + name);
				upload.renameTo(file);

				debug("=================" + indexDir);
				lawyers.setPhoto(name);
				lawyers.setPhotoname(fileName);
				
//				this.getLoginUser()
				
//				Lawyers sysUser = (Lawyers) get();
				this.set(Constants.LOGIN_USER, lawyers);
				

			} catch (Exception e) {
				debug("照片上传失败..." + e);
			}
		}
		//

		bs.update(lawyers);
		this.message = "律师信息修改成功";

		// this.nextPage = "lawyersList.pl";
		this.nextPage = "lawyersEditSelf!input.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		BasicService bservice = (BasicService) this.getBean("basicService");
		lawyers = (Lawyers) bservice.get(Lawyers.class, this.getLoginUser().getLawyerid());

		com.changpeng.common.CommonDatas.getGroups();
		set("lawyers", lawyers);

		return INPUT;
	}

	private Lawyers lawyers;

	public Lawyers getLawyers() {
		if (lawyers == null)
			lawyers = (Lawyers) this.get("lawyers");

		return this.lawyers;
	}

	private File upload;
	private String fileName;

	public String getUploadFileName() {
		return fileName;
	}

	public void setUploadFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
}
