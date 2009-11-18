/**
 * 
 */
package com.sxit.content.action;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.content.CorePublish;
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
 * 
 * </pre>
 * @author 华锋
 * Jul 12, 2009 4:56:58 PM
 *
 */
public class ContentApproveSubmitAction extends AbstractAction {

	
	public ContentApproveSubmitAction(){
		history = new TwflDohistory();
	}
	
	protected String go()throws Exception{
		
		com.sxit.models.wait.CoreInnerMsg msg=new CoreInnerMsg();
		com.sxit.models.wait.CoreInnerMsgDest dest=new CoreInnerMsgDest();
		msg.setFlag((short)0);
		msg.setParentId(0);
		msg.setSendTime(new java.sql.Timestamp(System.currentTimeMillis()));
		msg.setSendUserid(1);
		msg.setTitle(null);
	
		
		
		if(businessId==2){
			SnsPhoto photo=(SnsPhoto)basicService.get(SnsPhoto.class, serviceId);			
			if (history.getSimpleapprove() == 1) {
				photo.setStatusid((short) 0);
				basicService.update(photo);
				msg.setContent("<a href=\""+com.sxit.common.Constants.RESOURCE_PATH+"/"+photo.getPic()+"\" target=\"_blank\">您上传的相片审核通过!</a>");
			} else if (history.getSimpleapprove() == 2) {
				photo.setStatusid((short) 2);
				basicService.update(photo);
				msg.setContent("<a href=\""+com.sxit.common.Constants.RESOURCE_PATH+"/"+photo.getPic()+"\" target=\"_blank\">您上传的相片审核不通过!,原因:"+history.getDomessage()+"</a>");
			}
		
			dest.setDestUserid(photo.getUserid());
			this.nextPage = "photoList.action";
		}else if(businessId==3){
			SnsDiary diary=(SnsDiary)basicService.get(SnsDiary.class, serviceId);			
			if (history.getSimpleapprove() == 1) {
				diary.setStatusid((short) 0);
				basicService.update(diary);
				msg.setContent("<a href=\""+com.sxit.common.Constants.RESOURCE_PATH+"/diary/viewDiary.action?diaryid="+diary.getDiaryid()+"\" target=\"_blank\"您上传的文章审核通过!</a>");
			} else if (history.getSimpleapprove() == 2) {
				diary.setStatusid((short) 2);
				basicService.update(diary);
				msg.setContent("<a href=\""+com.sxit.common.Constants.RESOURCE_PATH+"/diary/viewDiary.action?diaryid="+diary.getDiaryid()+"\" target=\"_blank\"您上传的文章审核不通过!,原因:"+history.getDomessage()+"</a>");
			}
			dest.setDestUserid(diary.getUserid());
			
			this.nextPage = "contentList.action";
		}else if(businessId==4){
			SnsFile file=(SnsFile)basicService.get(SnsFile.class, serviceId);			
			if (history.getSimpleapprove() == 1) {
				file.setStatusid((short) 0);
				basicService.update(file);
				msg.setContent("<a href=\""+com.sxit.common.Constants.RESOURCE_PATH+"/"+file.getUrl()+"\" target=\"_blank\"您上传的文件审核通过!</a>");
			} else if (history.getSimpleapprove() == 2) {
				file.setStatusid((short) 2);
				basicService.update(file);
				msg.setContent("<a href=\""+com.sxit.common.Constants.RESOURCE_PATH+"/"+file.getUrl()+"\" target=\"_blank\"您上传的文件审核不通过!,原因:"+history.getDomessage()+"</a>");
			}
			dest.setDestUserid(file.getUserid());
			
			this.nextPage = "videoList.action";
		}else if(businessId==5){
			CorePublish publish=(CorePublish)basicService.get(CorePublish.class, serviceId);			
			if (history.getSimpleapprove() == 1) {
				publish.setStatusid((short) 0);
				basicService.update(publish);
				msg.setContent("<a href=\"../progress/publishview.action?publishid="+serviceId+"\">您订购的产品内容审核通过!</a>");
			} else if (history.getSimpleapprove() == 2) {
				publish.setStatusid((short) 2);
				basicService.update(publish);
				msg.setContent("<a href=\"../progress/publishview.action?publishid="+serviceId+"\">您订购的产品内容审核不通过!,原因:"+history.getDomessage()+"</a>");
			}
			dest.setDestUserid(publish.getUserid());
			
			this.nextPage = "tvConfirmList.action?userRole="+publish.getUserRole();
		}

		history.setBusinessid(businessId);
		history.setServiceid(serviceId);
		history.setDotime(new java.sql.Timestamp(System.currentTimeMillis()));
		history.setDouserid(this.getLoginUser().getUserid());
		history.setDousername(this.getLoginUser().getUsername());
		history.setNodeid(0);
		basicService.save(history);
	
		
		basicService.save(msg);
		
		dest.setDelflag((short)0);
		dest.setMsgId(msg.getId());
		basicService.save(dest);
		
		
		CoreUserPersonal personal=(CoreUserPersonal)basicService.get(CoreUserPersonal.class,dest.getDestUserid());
		short msgunread=personal.getCountMsgUnread()!=null?personal.getCountMsgUnread().shortValue():0;
		personal.setCountMsgUnread((short)(msgunread+1));
		basicService.update(personal);
		
		
		

		this.message = "内容审核处理成功,请返回";
		

		return SUCCESS;
	}
	
	
	
	private int businessId;
	private int serviceId;
	private TwflDohistory history;

	public TwflDohistory getHistory() {
		return this.history;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
}
