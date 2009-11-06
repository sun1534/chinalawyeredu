/**
 * wuguirong
 * Jun 6, 2009
 */
package com.changpeng.sns.photo.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.util.HtmlUtil;
import com.changpeng.sns.photo.model.SnsPhotoAlbum;
import com.changpeng.sns.photo.service.PhotoService;

/**
 * @author sg
 *
 */
public class EditAlbumAction extends AbstractAction {
	
	private int albumid;
	private SnsPhotoAlbum album;
	private String albumName;
	private String privateFlag;
	private String remark;

	public EditAlbumAction(){
		this.moduleid = 16;
	}
	
	@Override
	protected String go() throws Exception {
		
		/* 判断privateFlag是否为指定值 0 1 2 */
		if(Integer.getInteger(privateFlag)!=null){
			int flag = Integer.getInteger(privateFlag);
			if(flag!=0&&flag!=1&&flag!=2){
				this.redirectURL = "../photo/albumlist.action";
				message = "出错了！";
				return "message";
			}
		}
		
		
		
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		
		photoService.modifyAlbumName(albumid, this.currentUserid, albumName, privateFlag, remark);
		
		return SUCCESS;
	}
	
	protected String getin(){
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		
		album = (SnsPhotoAlbum) photoService.get(SnsPhotoAlbum.class, albumid);
		return INPUT;
	}

	public int getAlbumid() {
		return albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

	public SnsPhotoAlbum getAlbum() {
		return album;
	}

	public void setAlbum(SnsPhotoAlbum album) {
		this.album = album;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = HtmlUtil.Html2Text(albumName);
	}

	public String getPrivateFlag() {
		return privateFlag;
	}

	public void setPrivateFlag(String privateFlag) {
		this.privateFlag = privateFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
