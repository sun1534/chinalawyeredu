package com.changpeng.core.progress.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.CorePublish;
import com.changpeng.core.model.CorePublishContent;
import com.changpeng.core.service.ProgressService;
import com.changpeng.sns.diary.model.SnsDiary;

public class EditContentAction extends AbstractAction {

	private int publishid;
	private String content;
	
	public EditContentAction() {
	}

	@Override
	protected String go() throws Exception {
		
		ProgressService progressService=(ProgressService)this.getBean("progressService");
		CorePublish publish=(CorePublish)service.get(CorePublish.class, publishid);
		publish.setRemarks(content);
		System.out.println("content:"+content);
		publish.setStatusid((short)2);
		if(publish.getFee()==0.0){
			publish.setStatusid((short)3);
			
		}
		//这里要判断是否有content
		
		DetachedCriteria dc=DetachedCriteria.forClass(CorePublishContent.class).add(Restrictions.eq("publishid",publishid));
		BasicService service=(BasicService)getBean("basicService");
		List list=service.findByCriteria(dc, 1);
		if(list==null||list.size()==0){
		
		
		SnsDiary diary=new SnsDiary();
		diary.setContent(content);
		diary.setCreateIp(this.userip);
		diary.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		diary.setDiarytypeId(0);
		diary.setPrivateFlag((short)0);
		diary.setReplyCount(0);
		diary.setClickCount(0);
		diary.setStatusid((short)0);
		diary.setTile("");
		diary.setTop((short)0);
		diary.setUserid(this.currentUserid);
		diary.setUpdateTime(diary.getCreateTime());
	
		service.save(diary);
		CorePublishContent publishcontent=new CorePublishContent();
		publishcontent.setContentid(diary.getDiaryid());
		publishcontent.setServiceid(3);
		publishcontent.setPublishid(publishid);
		publishcontent.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));

		service.save(publishcontent);
		System.out.println("=========================="+diary.getDiaryid());
		}else{
			CorePublishContent _content=(CorePublishContent)list.get(0);
			SnsDiary diary=(SnsDiary)service.get(SnsDiary.class, _content.getContentid());
			diary.setContent(content);
			diary.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			service.update(diary);
			
		}
		
		
		
	
		
		
		progressService.update(publish);
		this.result="ok";
		return SUCCESS;
	}

	public int getPublishid() {
		return publishid;
	}

	public void setPublishid(int publishid) {
		this.publishid = publishid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
