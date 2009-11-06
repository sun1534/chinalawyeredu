package com.changpeng.core.progress.action;

import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.CoreProduct;
import com.changpeng.core.model.CorePublish;
import com.changpeng.core.model.CorePublishContent;
import com.changpeng.core.service.ProgressService;
import com.changpeng.sns.diary.service.ProDiaryService;

public class AddSelectAudioAction extends AbstractAction {

	private int publishid;

	private int[] fileid;
	@Override
	protected String go() throws Exception {
		
		
		ProgressService progressService=(ProgressService)this.getBean("progressService");
		
		CorePublish publish=(CorePublish)service.get(CorePublish.class, publishid);
		
		CoreProduct product=(CoreProduct)service.get(CoreProduct.class, publish.getProductid());
		
		List diarys = progressService.getDiaryList(publishid);
		if(fileid==null||fileid.length==0){
			this.message="请选择文件！";
		}else if(fileid.length+diarys.size()>product.getMaxpic()){
			int diarycount=product.getMaxpic()-diarys.size();
			this.message="您最多只能选择"+diarycount+"个文件！";
		}else{
			for(int i=0;i<fileid.length;i++){
				CorePublishContent publishcontent=new CorePublishContent();
				publishcontent.setContentid(fileid[i]);
				publishcontent.setServiceid(4);
				publishcontent.setPublishid(publishid);
				service.save(publishcontent);
			}
			if(progressService.checkContent(publishid)){
				publish.setStatusid((short)2);
				service.update(publish);
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

	public int[] getFileid() {
		return fileid;
	}

	public void setFileid(int[] fileid) {
		this.fileid = fileid;
	}



}
