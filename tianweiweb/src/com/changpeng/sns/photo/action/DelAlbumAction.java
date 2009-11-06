package com.changpeng.sns.photo.action;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.sns.photo.service.PhotoService;

/**
 * 
 * 删除相册
 * 
 * @author 华锋
 * May 20, 2009 9:30:35 PM
 *
 */
public class DelAlbumAction extends AbstractListAction {
	
//	private PaginationSupport albumPage;
	private int albumid;
	//private JugeTime jugeTime;

	public DelAlbumAction(){
		this.moduleid=16;
	}
	
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		int userid = 0;
		userid = this.currentUserid;

		
		PhotoService photoService = (PhotoService)this.getBean("photoService");
//		albumPage = photoService.getPageSpecial(userid, pageSize, pageNo);
		int result = photoService.deleteAlbum(albumid, userid);
		this.redirectURL="../photo/albumlist.action";
//		 0成功 -1相册拥有人和删除的人不是同一个人 -2相册下还有照片,不能删呢
		if(result==0){
			this.message="相册删除成功";
		}
		else if(result==-1){
			this.message="您没有删除则个相册的权利";
		}
		
		return "message";
	}


	public int getAlbumid() {
		return albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}
}
