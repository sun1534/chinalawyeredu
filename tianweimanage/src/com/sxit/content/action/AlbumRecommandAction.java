/**
 * 
 */
package com.sxit.content.action;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.content.SnsPhotoAlbum;

/**
 * 
 * 推荐/取消推荐 针对相册
 * 
 * @author 华锋
 * Jul 15, 2009 10:28:00 AM
 *
 */
public class AlbumRecommandAction extends AbstractAction {

	
	private int albumid;
	private int pageNo;
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getAlbumid() {
		return albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		SnsPhotoAlbum album=(SnsPhotoAlbum)basicService.get(SnsPhotoAlbum.class, albumid);
		boolean a=!album.getIsrecommand();
		
		album.setIsrecommand(a);
		basicService.update(album);
		if(!a){
			this.message="该相册取消推荐成功";
		}else{
			this.message="该相册设置成推荐成功";
		}
		
		this.nextPage="albumView.action?albumid="+albumid+"&pageNo="+pageNo;
		return SUCCESS;
	}

}
