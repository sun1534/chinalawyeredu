package com.sxit.content.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.content.CorePublish;
import com.sxit.models.content.CorePublishContent;
import com.sxit.models.content.SnsDiary;
import com.sxit.models.content.SnsFile;
import com.sxit.models.content.SnsPhoto;

/**
 * 1,家庭 2企业',
 * 
 * 查看订购的业务
 * 
 * @author 华锋 Jul 9, 2009 11:16:21 PM
 * 
 */

public class TVContentDoViewAction extends AbstractListAction {

	public TVContentDoViewAction() {

	}

	public String go() throws Exception {

		this.pageSize = 1;

		this.publish = (CorePublish) basicService.get(CorePublish.class, id);
		if (publish == null) {
			this.message = "该内容已不存在,请返回";
			return "message";
		}

		DetachedCriteria dc = DetachedCriteria.forClass(CorePublishContent.class);
		dc.add(Restrictions.eq("publishid", id));
		dc.addOrder(Order.desc("id"));
		this.page = basicService.findPageByCriteria(dc, pageSize, pageNo);
		List list = this.page.getItems();
		// // 2 照片 3 文字内容 4 音频视频
		if (list != null && list.size() > 0) {
			content = (CorePublishContent) list.get(0);
			
			System.out.println("=========>"+content.getStatusid());
			
			int serviceid = content.getServiceid();
		
			int contentId=content.getContentid();
			
			if (serviceid == 2) {
				this.photo = (SnsPhoto) basicService.get(SnsPhoto.class, contentId);
				System.out.println(this.photo);
			} else if (serviceid == 3) {
				this.diary = (SnsDiary) basicService.get(SnsDiary.class, contentId);
			} else if (serviceid == 4) {
				this.video = (SnsFile) basicService.get(SnsFile.class, contentId);
			}
		}


		
		String hql = "from com.sxit.models.workflow.TwflDohistory a where a.businessid=5 and a.serviceid=" + id;
		dolist = basicService.find(hql);

		return SUCCESS;
	}

	private CorePublishContent content;
	public CorePublishContent getContent(){
		return this.content;
	}
	
	
	private List dolist;

	private int id;
	private CorePublish publish;

	private String actionId;
	private SnsPhoto photo;
	private SnsFile video;
	private SnsDiary diary;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public CorePublish getPublish() {
		return publish;
	}

	public SnsPhoto getPhoto() {
		return photo;
	}

	public SnsFile getVideo() {
		return video;
	}

	public SnsDiary getDiary() {
		return diary;
	}

	public List getDolist() {
		return this.dolist;
	}

}
