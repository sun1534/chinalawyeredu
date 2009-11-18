/**
 * 
 */
package com.sxit.service.action;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.service.CorePartner;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class PartnerCreateEditAction extends AbstractListAction {

	protected String go() throws Exception {

		if (upload != null && upload.length() != 0) {
			try {
				if (upload.length() > 5000 * 1024) {
					this.message = "上传的图片大小超出了规定的最大大小5M，请重新选择";
					this.nextPage = "partnerCreateEdit.action?id=" + partner.getId();
					return "message";
				}
				int index = uploadFileName.lastIndexOf(".");
				String name = System.currentTimeMillis() + uploadFileName.substring(index);
				// sysUser.setPhoto(name);
				String indexDir = ServletActionContext.getServletContext().getRealPath("/upload/partner/");
				File dir = new File(indexDir);
				if (!dir.exists())
					dir.mkdirs();
				File file = new File(indexDir + System.getProperty("file.separator") + name);
				upload.renameTo(file);
				partner.setLogo("/upload/partner/" + name);
			} catch (Exception e) {
				LOG.error("合作伙伴LOGO上传失败::" + e);

				this.nextPage = "合作伙伴LOGO上传失败::" + e;
				return "message";
			}
		} else {
			partner.setLogo(oldlogo);
		}

		if (partner.getLogo() == null || partner.getLogo().equals("")) {
			this.message = "合作伙伴LOGO不能为空,请返回";
			return "message";
		}

		if (exist == 1) {

			basicService.update(partner);
			this.message = "合作伙伴信息修改成功";
		} else {
			partner.setCreatetime(new java.util.Date());
			basicService.save(partner);
			this.message = "合作伙伴信息新增成功";
		}
		this.nextPage = "partnerList.action";
		com.sxit.info.util.CommonDatas.getAllTypes();
		return SUCCESS;

	}

	@Override
	public String input() {

		this.partner = (CorePartner) basicService.get(CorePartner.class, id);
		if (partner == null) {
			partner = new CorePartner();
			exist = 0;
		} else {
			exist = 1;
			oldlogo = partner.getLogo();
		}
		set("partner", partner);

		return INPUT;

	}

	private int id;

	private CorePartner partner;
	private int exist;

	private String oldlogo;

	public String getOldlogo() {
		return this.oldlogo;
	}

	public int getExist() {
		return exist;
	}

	public void setExist(int exist) {
		this.exist = exist;
	}

	public CorePartner getPartner() {
		if (partner == null)
			partner = (CorePartner) get("partner");
		return partner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private File upload;
	private String uploadFileName;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setOldlogo(String oldlogo) {
		this.oldlogo = oldlogo;
	}

}
