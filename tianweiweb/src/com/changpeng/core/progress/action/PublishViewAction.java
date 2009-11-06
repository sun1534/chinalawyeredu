package com.changpeng.core.progress.action;

import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.CoreProduct;
import com.changpeng.core.model.CorePublish;
import com.changpeng.core.service.ProgressService;

public class PublishViewAction extends AbstractAction {

	private int publishid;

	private CoreProduct product;
	
	private CorePublish publish;
	
	private List photos;
	
	private List videos;
	private List audios;
	private List diarys;
	private int remainphotos;
	
	public PublishViewAction() {
	}

	@Override
	protected String go() throws Exception {
		
		ProgressService progressService=(ProgressService)this.getBean("progressService");
		
		publish=(CorePublish)service.get(CorePublish.class, publishid);
		
		product=(CoreProduct)service.get(CoreProduct.class, publish.getProductid());
		
		photos = progressService.getPhotoList(publishid);
		diarys = progressService.getDiaryList(publishid);
		audios = progressService.getAudioList(publishid);
		
		int uphotos=photos.size();
		int maxphotos=product.getMaxpic();
		remainphotos=maxphotos-uphotos;
		return SUCCESS;
	}

	public int getPublishid() {
		return publishid;
	}

	public void setPublishid(int publishid) {
		this.publishid = publishid;
	}

	public CorePublish getPublish() {
		return publish;
	}

	public List getPhotos() {
		return photos;
	}

	public List getVideos() {
		return videos;
	}

	public List getAudios() {
		return audios;
	}

	public List getDiarys() {
		return diarys;
	}

	public CoreProduct getProduct() {
		return product;
	}

	public int getRemainphotos() {
		return remainphotos;
	}
	
}
