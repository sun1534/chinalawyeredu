/**
 * 
 */
package com.sxit.content.action;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.content.CorePublish;
import com.sxit.models.users.CoreUserPersonal;
import com.sxit.models.wait.CoreInnerMsg;
import com.sxit.models.wait.CoreInnerMsgDest;
import com.sxit.models.workflow.TwflDohistory;

/**
 * <pre>
 * 停止发布
 * 
 * </pre>
 * 
 * @author 华锋 Jul 12, 2009 4:56:58 PM
 * 
 */
public class TVContentStopAction extends AbstractAction {

	public TVContentStopAction() {

	}

	// PUBLISHSTATUS.put((short)0, "审批通过");
	// PUBLISHSTATUS.put((short)1, "初订购");
	// PUBLISHSTATUS.put((short)2, "待付费");
	// PUBLISHSTATUS.put((short)3, "付费完毕待审核");
	// PUBLISHSTATUS.put((short)4, "审核未通过");
	// PUBLISHSTATUS.put((short)5, "审核通过");
	// PUBLISHSTATUS.put((short)99, "发布中");
	// PUBLISHSTATUS.put((short)100, "业务到期");
	//	
	protected String go() throws Exception {
		int businessId = 5;

		CorePublish publish = (CorePublish) basicService.get(CorePublish.class, serviceId);
		publish.setStatusid((short) 100);
		publish.setEndtime(new java.util.Date());
		
		basicService.update(publish);

		this.nextPage = "tvConfirmList.action?userRole="+publish.getUserRole();

		TwflDohistory history = new TwflDohistory();
		history.setBusinessid(businessId);
		history.setServiceid(serviceId);
		history.setDotime(new java.sql.Timestamp(System.currentTimeMillis()));
		history.setDouserid(this.getLoginUser().getUserid());
		history.setDousername(this.getLoginUser().getUsername());
		history.setNodeid(0);
		history.setDomessage("业务停止电视展示");
		history.setSimpleapprove(100); // 雷同状态字段

		basicService.save(history);

		
		
		com.sxit.models.wait.CoreInnerMsg msg=new CoreInnerMsg();
		com.sxit.models.wait.CoreInnerMsgDest dest=new CoreInnerMsgDest();
		msg.setFlag((short)0);
		msg.setParentId(0);
		msg.setSendTime(new java.sql.Timestamp(System.currentTimeMillis()));
		msg.setSendUserid(1);
		msg.setTitle(null);
		msg.setContent("<a href=\"../progress/publishview.action?publishid="+serviceId+"\">您订购的产品，在电视上已经停止发布</a>");
		dest.setDestUserid(publish.getUserid());
		basicService.save(msg);
		
		dest.setDelflag((short)0);
		dest.setMsgId(msg.getId());
		basicService.save(dest);
		
		
		CoreUserPersonal personal=(CoreUserPersonal)basicService.get(CoreUserPersonal.class,dest.getDestUserid());
		short msgunread=personal.getCountMsgUnread()!=null?personal.getCountMsgUnread().shortValue():0;
		personal.setCountMsgUnread((short)(msgunread+1));
		basicService.update(personal);
		
		com.sxit.wait.util.WaitWork.EndWait(publish.getWaitid(), this.getLoginUser().getUserid());

		this.message = "业务展示停止成功";

		return SUCCESS;
	}

	private int serviceId;

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
}
