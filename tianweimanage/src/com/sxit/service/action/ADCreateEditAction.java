/**
 * 
 */
package com.sxit.service.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.service.CoreAdvPos;
import com.sxit.models.service.CoreAdvtisment;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class ADCreateEditAction extends AbstractAction {

	protected String go() throws Exception {

	
		
		if (upload != null && upload.length() != 0) {
			try {
				if (upload.length() > 5000 * 1024) {
					this.message = "上传的图片大小超出了规定的最大大小5M，请重新选择";
					this.nextPage = "adCreateEdit.action?pos=" + adv.getPos();
					return "message";
				}
				int index = uploadFileName.lastIndexOf(".");
				String name = System.currentTimeMillis() + uploadFileName.substring(index);
				// sysUser.setPhoto(name);
				String indexDir = ServletActionContext.getServletContext().getRealPath("/upload/ad/");
				File dir = new File(indexDir);
				if (!dir.exists())
					dir.mkdirs();
				File file = new File(indexDir + System.getProperty("file.separator") + name);
				upload.renameTo(file);
				adv.setPic("/upload/ad/" + name);
			} catch (Exception e) {
				LOG.error("广告内容上传失败::" + e);

				this.nextPage = "广告内容上传失败::" + e;
				return "message";
			}
		} else {
			adv.setPic(oldpic);
		}

		if (adv.getPic() == null || adv.getPic().equals("")) {
			this.message = "广告内容不能为空,请返回";
			return "message";
		}

		CoreAdvPos advpos = (CoreAdvPos) basicService.get(CoreAdvPos.class, adv.getPos());
		adv.setDescription(advpos.getDescription());
		if (exist == 1) {
			
			basicService.update(adv);
			this.message = "广告信息改成功";
		} else {
			basicService.save(adv);
			this.message = "广告信息新增成功";
		}
		this.nextPage = "adList.action";

		return SUCCESS;

	}

	@Override
	public String input() {
		allPos = new ArrayList();
		List _allPos = basicService.findAll(CoreAdvPos.class);
		if (_allPos == null || _allPos.size() == 0) {
			this.message = "广告位为空,请先<a href=\"advPosCreateEdit!input.action\">新增广告位</a>";
			return "message";
		}

		
		for(int i=0;i<_allPos.size();i++){
			allPos.add(_allPos.get(i));
		}
		
		// 这里要去掉已经使用了的广告位置
		List list = basicService.findAll(CoreAdvtisment.class);

		for (int i = 0; list != null && i < list.size(); i++) {

			CoreAdvtisment adv = (CoreAdvtisment) list.get(i);
			int pos = adv.getPos();
			for (int ii = 0; ii < _allPos.size(); ii++) {
				CoreAdvPos advpos = (CoreAdvPos) _allPos.get(ii);
				if (advpos.getPos() == pos) {
					allPos.remove(advpos);
					break;
				}
//				allPos.add(advpos);
			}

		}

		this.adv = (CoreAdvtisment) basicService.get(CoreAdvtisment.class, pos);
		if (adv == null) {
			adv = new CoreAdvtisment();
			exist = 0;
			
			if(allPos.size()==0){
				this.message = "没有可用的广告位,请点<a href=\"advPosCreateEdit!input.action\">新增</a>";
				return "message";
			}
			
			
		} else {
			oldpic = adv.getPic();
			exist = 1;
		}
		set("adv", adv);

		return INPUT;

	}

	private String oldpic;

	private File upload;
	private String uploadFileName;

	private int pos;
	private CoreAdvtisment adv;
	private int exist;

	public int getExist() {
		return exist;
	}

	public void setExist(int exist) {
		this.exist = exist;
	}

	public CoreAdvtisment getAdv() {
		if (adv == null)
			adv = (CoreAdvtisment) get("adv");
		return adv;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	private List allPos;

	public List getAllPos() {
		return allPos;
	}

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
}
