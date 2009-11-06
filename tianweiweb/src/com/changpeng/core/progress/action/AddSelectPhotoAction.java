package com.changpeng.core.progress.action;

import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.CoreProduct;
import com.changpeng.core.model.CorePublish;
import com.changpeng.core.model.CorePublishContent;
import com.changpeng.core.service.ProgressService;

public class AddSelectPhotoAction extends AbstractAction {

	private int publishid;
	
	private int photoid;
	
	private String remark;
	
	private int contentid;
	@Override
	protected String go() throws Exception {
		
		ProgressService progressService=(ProgressService)this.getBean("progressService");
		
		CorePublish publish=(CorePublish)service.get(CorePublish.class, publishid);
		
		CoreProduct product=(CoreProduct)service.get(CoreProduct.class, publish.getProductid());
		
		List photos = progressService.getPhotoList(publishid);
		if(photoid==0){
			this.message="请选择照片！";
		}else{
			if(contentid==0){

				CorePublishContent publishcontent=new CorePublishContent();
				publishcontent.setContentid(photoid);
				publishcontent.setServiceid(2);
				publishcontent.setPublishid(publishid);
				publishcontent.setRemark(remark);
				service.save(publishcontent);

				if(progressService.checkContent(publishid)){
					publish.setStatusid((short)2);
					if(publish.getFee()==0.0){
						publish.setStatusid((short)3);
						
					}
					service.update(publish);
				}
			}else{
				CorePublishContent publishcontent=(CorePublishContent)progressService.get(CorePublishContent.class, contentid);
				publishcontent.setContentid(photoid);
				publishcontent.setRemark(remark);
				service.update(publishcontent);
			}
			this.message="ok";
		}
		
		return "plainmsg";
	}
	
	public int getPublishid() {
		return publishid;
	}
	public void setPublishid(int publishid) {
		this.publishid = publishid;
	}

	public int getPhotoid() {
		return photoid;
	}

	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setContentid(int contentid) {
		this.contentid = contentid;
	}

}
