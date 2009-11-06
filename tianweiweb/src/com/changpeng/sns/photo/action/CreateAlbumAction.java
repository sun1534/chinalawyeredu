package com.changpeng.sns.photo.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.sysdata.CommonData;
import com.changpeng.common.util.JugeTime;
import com.changpeng.sns.photo.service.PhotoService;


/**
 * 
 * 新增一个相册,默认相册的首页
 * @author 华锋
 * May 20, 2009 7:44:54 PM
 *
 */
public class CreateAlbumAction extends AbstractAction {
	
	private String albumName;
	private String privateFlag;
	private String remark;
	public CreateAlbumAction(){
		this.moduleid=16;
	}
	
	
	
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
//		int userid = 0;
//		int userid = this.currentUserid;
		
		/* 判断privateFlag是否为指定值 0 1 2 */
		if(Integer.getInteger(privateFlag)!=null){
			int flag = Integer.getInteger(privateFlag);
			if(flag!=0&&flag!=1&&flag!=2){
				this.redirectURL = "../photo/albumlist.action";
				message = "出错了！";
				return "message";
			}
		}
		
		jugeTime = new JugeTime();

		PhotoService photoService = (PhotoService)this.getBean("photoService");
		//log.debug("xxxxxxxxxxxxx----xxxxxxxxxxx");
		photoService.addAlbum(this.currentUserid,  albumName,CommonData.DEFAULT_ALBUM_PIC,  this.userip ,privateFlag,remark);

		
		return SUCCESS;

	}
	
	protected String getin() {
		
		return INPUT;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumname) {
		this.albumName = albumname;
	}

	private JugeTime jugeTime;
	public JugeTime getJugeTime() {
		return jugeTime;
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
