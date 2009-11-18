/**
 * 
 */
package com.sxit.content.action;

import java.text.DateFormat;

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
public class TVContentPublishAction extends AbstractAction {

	public TVContentPublishAction() {

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

		if(start==null||start.equals("")){
			this.message="请输入发布的起始时间";
			return "message";
		}
		
		if(end==null||end.equals("")){
			this.message="请输入发布的终止时间";
			return "message";
		}
		
		CorePublish publish = (CorePublish) basicService.get(CorePublish.class, serviceId);
		publish.setStatusid((short) 99);
		publish.setStarttime(df.parse(start));
		publish.setEndtime(df.parse(end));
		
		basicService.update(publish);

		this.nextPage = "tvConfirmList.action?userRole="+publish.getUserRole();

		TwflDohistory history = new TwflDohistory();
		history.setBusinessid(businessId);
		history.setServiceid(serviceId);
		history.setDotime(new java.sql.Timestamp(System.currentTimeMillis()));
		history.setDouserid(this.getLoginUser().getUserid());
		history.setDousername(this.getLoginUser().getUsername());
		history.setNodeid(0);
		history.setDomessage("业务发布");
		history.setSimpleapprove(99); // 雷同状态字段

		basicService.save(history);
		
		
		com.sxit.models.wait.CoreInnerMsg msg=new CoreInnerMsg();
		com.sxit.models.wait.CoreInnerMsgDest dest=new CoreInnerMsgDest();
		msg.setFlag((short)0);
		msg.setParentId(0);
		msg.setSendTime(new java.sql.Timestamp(System.currentTimeMillis()));
		msg.setSendUserid(1);
		msg.setTitle(null);
		msg.setContent("<a href=\"../progress/publishview.action?publishid="+serviceId+"\">您订购的产品，在电视上已经发布，起始时间："+start+"，终止时间："+end+"</a>");
		dest.setDestUserid(publish.getUserid());
		basicService.save(msg);
		
		dest.setDelflag((short)0);
		dest.setMsgId(msg.getId());
		basicService.save(dest);
		
		
		CoreUserPersonal personal=(CoreUserPersonal)basicService.get(CoreUserPersonal.class,dest.getDestUserid());
		short msgunread=personal.getCountMsgUnread()!=null?personal.getCountMsgUnread().shortValue():0;
		personal.setCountMsgUnread((short)(msgunread+1));
		basicService.update(personal);
		
		
		
		
		
		
		
		

		this.message = "业务发布成功";

		return SUCCESS;
	}
	
	private static DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
	
	private String start;
	private String end;
	

	private int serviceId;

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public void setEnd(String end) {
		this.end = end;
	}
}
