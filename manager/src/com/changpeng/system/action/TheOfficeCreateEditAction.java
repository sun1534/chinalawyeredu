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
import com.changpeng.system.service.SysGroupService;
import com.changpeng.system.util.CommonDatas;

/**
 * 
 * 事务所信息的新增修改
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class TheOfficeCreateEditAction extends AbstractAction {
	private BasicService bservice = null;
	private SysGroup sysGroup;
	private SysGroupService groupservice = null;

	public SysGroup getSysGroup() {
		if (sysGroup == null)
			sysGroup = (SysGroup) this.get("sysGroup");
		return this.sysGroup;
	}

	public TheOfficeCreateEditAction() {
		this.datavisible = new DataVisible();
		groupservice = (SysGroupService) this.getBean("sysGroupService");

		bservice = (BasicService) this.getBean("basicService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		if (datavisible.getCityid() == 0) {
			this.message = "请选择对应的市律协";
			this.nextPage = "theOfficeList.pl";
			return "message";
		}

		if (datavisible.getProvinceid() == 0) {
			this.message = "请选择对应的省律协";
			this.nextPage = "theOfficeList.pl";
			return "message";
		}
		if (sysGroup.getGroupenname() == null || sysGroup.getGroupenname().equals("")) {
			this.message = "律师事务所执业证号不能为空,请输入";
			this.nextPage = "theOfficeList.pl";
			return "message";
		}

		SysGroup parent = (SysGroup) bservice.get(SysGroup.class, sysGroup.getParentid());
		int grouplevel = 1;
		if (parent != null) {
			grouplevel = parent.getGrouplevel() + 1;
		}

		sysGroup.setParentid(datavisible.getCityid());
		sysGroup.setDirectgroup(datavisible.getProvinceid());

		sysGroup.setGrouplevel(grouplevel);
		sysGroup.setDelflag(false);

		
		sysGroup.setGroupenname(sysGroup.getGroupenname()==null?"":sysGroup.getGroupenname().trim());
		
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

		if (!isedit) {
			sysGroup.setCreatetype(1);
			sysGroup.setCreateuser(getLoginUser().getUsername());
			sysGroup.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			sysGroup.setSystemno(System.currentTimeMillis() / 1000 + "");
			// bservice.save(sysGroup);
			int s = groupservice.addTheOffice(sysGroup);
			if (s == 0) {
				this.opResult = "新增事务所信息同时新增一个事务所帐号";
			} else if (s == 1) {
				this.opResult = "新增事务所信息,但该执业证号已经被其他所使用,修改为该所";
			} else if (s == 2) {
				this.opResult = "新增事务所信息，帐号不变化";
			}
			this.message = "事务所信息新增成功";
		} else {

			int s = groupservice.updateTheOffice(oldloginname, sysGroup);
			if(s==0){
				this.opResult="律所自己修改事务所信息修改登录帐号";
			}
			if(s==3){
				this.opResult="律所自己修改事务所信息同时新增一个事务所帐号";
			}
			else if(s==1){
				this.opResult="律所自己修改事务所信息,但该执业证号已经被其他所使用,修改为该所";
			}
			else if(s==2){
				this.opResult="律所自己修改事务所信息，帐号不变化";
			}
			// bservice.update(sysGroup);
			this.message = "事务所信息修改成功";
		}

		if (!propertiesedit) {
			System.out.println(properties);
			properties.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			properties.setCreateusername(this.getLoginUser().getUsername());
			properties.setGroupid(sysGroup.getGroupid());
			bservice.save(properties);
		} else {
			bservice.update(properties);
		}

		this.nextPage = "theOfficeList.pl";

		CommonDatas.getGroups();
		System.out.println("刷新下group的信息");
		// return "toparent";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		sysGroup = (SysGroup) bservice.get(SysGroup.class, groupid);

		properties = (OfficeProperties) bservice.get(OfficeProperties.class, groupid);

		if (this.sysGroup == null) {
			sysGroup = new SysGroup();
			sysGroup.setGrouptype(1); // 事务所的新增修改
			isedit = false;
		} else {

			if (sysGroup.getGrouptype() != 1) {
				this.message = "你修改的不是事务所信息,请返回";
				this.nextPage = "theOfficeList.pl";
				return "message";
			}
			datavisible.setCityid(sysGroup.getParentid());
			datavisible.setProvinceid(sysGroup.getDirectgroup());
			datavisible.setOfficeid(groupid);
			oldloginname = sysGroup.getGroupenname();
			isedit = true;
		}
		if (properties == null) {
			properties = new OfficeProperties();
			propertiesedit = false;
		} else {
			propertiesedit = true;
		}
		datavisible.getVisibleDatas(this.getLoginUser(), false);

		set("sysGroup", sysGroup);
		set("properties", properties);

		return INPUT;
	}

	private String oldloginname;
	private boolean isedit = false;
	private boolean propertiesedit = false;
	private OfficeProperties properties;

	private int groupid;

	/**
	 * @return the properties
	 */
	public OfficeProperties getProperties() {
		if (properties == null)
			properties = (OfficeProperties) this.get("properties");
		return properties;
	}

	/**
	 * @return the groupid
	 */
	public int getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid
	 *            the groupid to set
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
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

	/**
	 * @return the oldloginname
	 */
	public String getOldloginname() {
		return oldloginname;
	}

	/**
	 * @param oldloginname
	 *            the oldloginname to set
	 */
	public void setOldloginname(String oldloginname) {
		this.oldloginname = oldloginname;
	}
}