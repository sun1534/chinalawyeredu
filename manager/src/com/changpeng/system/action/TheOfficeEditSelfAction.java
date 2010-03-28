/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.io.File;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.OfficeProperties;
import com.changpeng.models.SysGroup;
import com.changpeng.system.util.CommonDatas;

/**
 * 
 * 事务所管理员修改事务所信息
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class TheOfficeEditSelfAction extends AbstractAction {
	private BasicService bservice = null;
	private SysGroup sysGroup;

	public SysGroup getSysGroup() {
		if (sysGroup == null)
			sysGroup = (SysGroup) this.get("sysGroup");
		return this.sysGroup;
	}

	public TheOfficeEditSelfAction() {
		this.datavisible = new DataVisible();

		bservice = (BasicService) this.getBean("basicService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		
		
		sysGroup.setDelflag(false);

		if (upload != null && upload.length() != 0) {
			try {

				if (upload.length() > 1000 * 1024) {
					debug("删除上传图片成功否:" + upload.delete());
					this.message = "上传的图片大小超出了规定的最大大小1000K，请重新选择";

					return "message";
				}
				int index = fileName.lastIndexOf(".");
				String name = System.currentTimeMillis() + fileName.substring(index);

				String indexDir = com.changpeng.common.Constants.PHOTO_SAVE_PATH + "office/";
				File filedir = new File(indexDir);

				if (!filedir.exists()) {
					boolean s = filedir.mkdirs();
					debug("文件路径:::" + indexDir + "创建成功..." + s);
				}

				File file = new File(indexDir + System.getProperty("file.separator") + name);
				upload.renameTo(file);

				debug("=================" + indexDir);
				properties.setPhoto("office/" + name);
				properties.setFilename(fileName);
			} catch (Exception e) {
				debug("照片上传失败..." + e);
				// e.printStackTrace();
			}
		}

	
			bservice.update(sysGroup);
			this.message = "事务所信息修改成功";
	

		if (!propertiesedit) {
			System.out.println(properties);
			properties.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			properties.setCreateusername(this.getLoginUser().getUsername());
			properties.setGroupid(sysGroup.getGroupid());
			bservice.save(properties);
		} else {
			bservice.update(properties);
		}

		this.nextPage = "theOfficeEditSelf!input.pl";

		CommonDatas.getGroups();
		System.out.println("刷新下group的信息");
		// return "toparent";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		System.out.println("this.getLoginUser().getSysGroup():::"+this.getLoginUser().getSysGroup());
		
		if(this.getLoginUser().getSysGroup()==null||this.getLoginUser().getSysGroup().getGrouptype()!=1){
			this.message = "你不是事务所管理员,请返回";
			this.nextPage = "theOfficeEditSelf!input.pl";
			return "message";
		}
		
		sysGroup = (SysGroup) bservice.get(SysGroup.class, this.getLoginUser().getOfficeid());

		properties = (OfficeProperties) bservice.get(OfficeProperties.class, this.getLoginUser().getOfficeid());

		if (sysGroup.getGrouptype() != 1) {
			this.message = "你修改的不是事务所信息,请返回";
			this.nextPage = "theOfficeList.pl";
			return "message";
		}

		isedit = true;

		if (properties == null) {
			properties = new OfficeProperties();
			propertiesedit = false;
		} else {
			propertiesedit = true;
		}

		set("sysGroup", sysGroup);
		set("properties", properties);

		return INPUT;
	}

	private boolean isedit = false;
	private boolean propertiesedit = false;
	private OfficeProperties properties;


	/**
	 * @return the properties
	 */
	public OfficeProperties getProperties() {
		if (properties == null)
			properties = (OfficeProperties) this.get("properties");
		return properties;
	}

	
	/**
	 * @return the isedit
	 */
	public boolean isIsedit() {
		return isedit;
	}

	/**
	 * @param isedit
	 *            the isedit to set
	 */
	public void setIsedit(boolean isedit) {
		this.isedit = isedit;
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

	/**
	 * @return the propertiesedit
	 */
	public boolean getPropertiesedit() {
		return propertiesedit;
	}

	/**
	 * @param propertiesedit
	 *            the propertiesedit to set
	 */
	public void setPropertiesedit(boolean propertiesedit) {
		this.propertiesedit = propertiesedit;
	}
}