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
public class TVContentApproveSubmitAction extends AbstractAction {

	public TVContentApproveSubmitAction() {
		history=new TwflDohistory();
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
		int userRole=publish.getUserRole();
		
		
		com.sxit.models.wait.CoreInnerMsg msg=new CoreInnerMsg();
		com.sxit.models.wait.CoreInnerMsgDest dest=new CoreInnerMsgDest();
		msg.setFlag((short)0);
		msg.setParentId(0);
		msg.setSendTime(new java.sql.Timestamp(System.currentTimeMillis()));
		msg.setSendUserid(1);
		msg.setTitle(null);
		dest.setDestUserid(publish.getUserid());
		
		
		System.out.println("dest.getDestUserid()::"+dest.getDestUserid());
		
		if(history.getSimpleapprove()==1){ //审核通过
			publish.setStatusid((short)5);
			history.setSimpleapprove(5);		
			publish.setApprovcetime(new java.sql.Timestamp(System.currentTimeMillis()));
			
//			0 待审核  1 审核通过  2 审核部通过
			
			if(userRole==1){
				basicService.executeSql("update core_publish_content set statusid=1 where publishid="+serviceId);
			}
			msg.setContent("<a href=\"../progress/publishview.action?publishid="+serviceId+"\">您订购的产品，上传内容审核通过！</a>");
			
			this.message = "审核通过处理成功";
		}else if(history.getSimpleapprove()==2){ //通过并发布
			publish.setStatusid((short)99);
			history.setSimpleapprove(99);
			publish.setApprovcetime(new java.sql.Timestamp(System.currentTimeMillis()));
			publish.setStarttime(new java.util.Date());
			this.message = "审核通过并发布处理成功";
			publish.setStarttime(new java.util.Date());
			
			msg.setContent("<a href=\"../progress/publishview.action?publishid="+serviceId+"\">您订购的产品，上传内容审核通过并已经发布到电视！</a>");
		}else if(history.getSimpleapprove()==3){//不通过
			publish.setStatusid((short)4);
			history.setSimpleapprove(4);
			this.message = "审核不通过处理成功";
			
			msg.setContent("<a href=\"../progress/publishview.action?publishid="+serviceId+"\">您订购的产品，上传内容审核不通过！</a>");
			if(userRole==1){
				basicService.executeSql("update core_publish_content set statusid=2 where publishid="+serviceId);
			}
		}
		
		basicService.update(publish);
basicService.save(msg);
		
		dest.setDelflag((short)0);
		dest.setMsgId(msg.getId());
		basicService.save(dest);
		
		
		CoreUserPersonal personal=(CoreUserPersonal)basicService.get(CoreUserPersonal.class,dest.getDestUserid());
	System.out.println(personal);
		short msgunread=personal.getCountMsgUnread()!=null?personal.getCountMsgUnread().shortValue():0;
		personal.setCountMsgUnread((short)(msgunread+1));
		basicService.update(personal);
		
		
		
		
		
		this.nextPage = "tvConfirmList.action?userRole="+userRole;

		history.setBusinessid(businessId);
		history.setServiceid(serviceId);
		history.setDotime(new java.sql.Timestamp(System.currentTimeMillis()));
		history.setDouserid(this.getLoginUser().getUserid());
		history.setDousername(this.getLoginUser().getUsername());
		history.setNodeid(0);
//		history.setDomessage("业务发布");
//		history.setSimpleapprove(99); // 雷同状态字段

		System.out.println("history.getDomessage():::"+history.getDomessage());
		
		
		basicService.save(history);

	

		return SUCCESS;
	}
	
	private static DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
	
	private String start;
	private String end;
	
private TwflDohistory history ;


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

	public TwflDohistory getHistory() {
		return history;
	}
}
