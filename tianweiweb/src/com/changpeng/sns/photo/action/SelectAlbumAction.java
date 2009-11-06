package com.changpeng.sns.photo.action;

import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.photo.service.PhotoService;

public class SelectAlbumAction extends AbstractAction {
	
	private List albumlist;
	private int albumid;

	@Override
	protected String go() throws Exception {
		PhotoService service = (PhotoService) this.getBean("photoService");
		
		albumlist = service.getMyAlbumList(this.currentUserid);

		return SUCCESS;
	}

	public List getAlbumlist() {
		return albumlist;
	}

	public void setAlbumlist(List albumlist) {
		this.albumlist = albumlist;
	}

	public int getAlbumid() {
		return albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

}
