package com.sxit.content.action;

import java.text.DateFormat;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.content.SnsPhoto;
import com.sxit.models.content.SnsPhotoAlbum;

/**
 * 
 * 查看相册的相片 相片管理
 * 
 */

public class AlbumViewAction extends AbstractListAction {

	public AlbumViewAction() {

	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public String go() throws Exception {
		this.pageSize = 1;
		com.sxit.content.util.CommonDatas.getAllContentTypes();
		album = (SnsPhotoAlbum) basicService.get(SnsPhotoAlbum.class, albumid);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsPhoto.class);
		detachedCriteria.add(Restrictions.eq("albumid", albumid));
		detachedCriteria.addOrder(Order.desc("photoid"));
		this.page = basicService.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}

	private com.sxit.models.content.SnsPhotoAlbum album;

	public SnsPhotoAlbum getAlbum() {
		return this.album;
	}

	private int albumid;

	public int getAlbumid() {
		return albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

}
