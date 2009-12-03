/**
 * 
 */
package com.sxit.content.action;

import java.util.List;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.content.CorePublish;
import com.sxit.models.content.CorePublishContent;
import com.sxit.models.content.SnsDiary;
import com.sxit.models.content.SnsFile;
import com.sxit.models.content.SnsPhoto;
import com.sxit.models.users.CoreUserPersonal;
import com.sxit.models.wait.CoreInnerMsg;
import com.sxit.models.wait.CoreInnerMsgDest;
import com.sxit.models.workflow.TwflDohistory;

/**
 * <pre>
 * 对内容的审批
 * 1、身份认证
 * 2、相片审核
 * 3、文字内容审核
 * 4、音频视频内容审核
 * 5、发布到电视内容审核
 * 6、单个的内容的审核
 * 
 * 企业的订购那,对单个的内容的审核
 * 
 * </pre>
 * 
 * @author 华锋 Jul 12, 2009 4:56:58 PM
 * 
 */
public class OrderContentApproveSubmitAction extends AbstractAction {

	public OrderContentApproveSubmitAction() {
		history = new TwflDohistory();
	}

	protected String go() throws Exception {

		int businessId = 6;

		com.sxit.models.wait.CoreInnerMsg msg = new CoreInnerMsg();
		com.sxit.models.wait.CoreInnerMsgDest dest = new CoreInnerMsgDest();
		msg.setFlag((short) 0);
		msg.setParentId(0);
		msg.setSendTime(new java.sql.Timestamp(System.currentTimeMillis()));
		msg.setSendUserid(1);
		msg.setTitle(null);

		publishcontent = (CorePublishContent) basicService.get(CorePublishContent.class, contentId);
		CorePublish publish = (CorePublish) basicService.get(CorePublish.class, publishcontent.getPublishid());


		if (history.getSimpleapprove() == 1) {
			publishcontent.setStatusid((short) 1);
			publishcontent.setApprovetime(new java.sql.Timestamp(System.currentTimeMillis()));
			basicService.update(publishcontent);

			msg.setContent("<a href=\"../progress/publishview.action?publishid="+publishcontent.getPublishid()+"\">您订购的企业产品，内容审核通过！</a>");
	
		//	msg.setContent("<a href=\"javascript:getUploadFile("+publishcontent.getPublishid()+")\" class=\"a_pay\">您订购的企业产品，内容审核通过，请点此上传相应文件内容！</a>");

		} else if (history.getSimpleapprove() == 2) {
			publishcontent.setStatusid((short) 2);
			basicService.update(publishcontent);
			msg.setContent("<a href=\"../progress/publishview.action?publishid="+publishcontent.getPublishid()+"\">您订购的企业产品，内容审核不通过！原因:" + history.getDomessage()+"</a>");
		}
		dest.setDestUserid(publish.getUserid());

		this.nextPage = "tvContentDoView.action?pageNo=" + pageNo + "&actionId=" + actionId + "&id=" + publishcontent.getPublishid();

		history.setBusinessid(businessId);
		history.setServiceid(contentId);
		history.setDotime(new java.sql.Timestamp(System.currentTimeMillis()));
		history.setDouserid(this.getLoginUser().getUserid());
		history.setDousername(this.getLoginUser().getUsername());
		history.setNodeid(0);
		basicService.save(history);

		basicService.save(msg);

		dest.setDelflag((short) 0);
		dest.setMsgId(msg.getId());
		basicService.save(dest);

		CoreUserPersonal personal = (CoreUserPersonal) basicService.get(CoreUserPersonal.class, dest.getDestUserid());
		short msgunread = personal.getCountMsgUnread() != null ? personal.getCountMsgUnread().shortValue() : 0;
		personal.setCountMsgUnread((short) (msgunread + 1));
		basicService.update(personal);
		com.sxit.wait.util.WaitWork.EndWait(publish.getWaitid(), this.getLoginUser().getUserid());

		this.message = "内容审核处理成功,请返回";

		return SUCCESS;
	}

	public String input() throws Exception {

		publishcontent = (CorePublishContent) basicService.get(CorePublishContent.class, contentId);

		publish = (CorePublish) basicService.get(CorePublish.class, publishcontent.getPublishid());

		int serviceid = publishcontent.getServiceid();

		int _contentId = publishcontent.getContentid();

		if (serviceid == 2) {
			this.photo = (SnsPhoto) basicService.get(SnsPhoto.class, _contentId);
			System.out.println(this.photo);
		} else if (serviceid == 3) {
			this.diary = (SnsDiary) basicService.get(SnsDiary.class, _contentId);
		} else if (serviceid == 4) {
			this.video = (SnsFile) basicService.get(SnsFile.class, _contentId);
		}

		String hql = "from com.sxit.models.workflow.TwflDohistory a where a.businessid=6 and a.serviceid=" + contentId;
		dolist = basicService.find(hql);

		return INPUT;
	}

	private CorePublishContent publishcontent;

	public CorePublishContent getPublishcontent() {
		return this.publishcontent;
	}

	private CorePublish publish;

	public CorePublish getPublish() {
		return this.publish;
	}

	private String actionId;
	private String pageNo;

	private int contentId;
	private TwflDohistory history;

	public TwflDohistory getHistory() {
		return this.history;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int serviceId) {
		this.contentId = serviceId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	private SnsPhoto photo;
	private SnsFile video;
	private SnsDiary diary;

	public SnsPhoto getPhoto() {
		return photo;
	}

	public SnsFile getVideo() {
		return video;
	}

	public SnsDiary getDiary() {
		return diary;
	}

	private List dolist;

	public List getDolist() {
		return dolist;
	}
}
