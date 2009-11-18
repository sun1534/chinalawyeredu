package com.sxit.users.action;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.users.CoreUser;
import com.sxit.models.users.CoreUserPersonal;
import com.sxit.models.wait.CoreInnerMsg;
import com.sxit.models.wait.CoreInnerMsgDest;
import com.sxit.models.workflow.TwflDohistory;

/**
 * 
 * 处理需要待认证的用户，看是认证通过还是认证失败
 * 
 * @author 华锋 Jul 9, 2009 11:23:13 PM
 * 
 */
public class UsersApproveSubmitAction extends AbstractAction {

	public UsersApproveSubmitAction() {
		history = new TwflDohistory();
	}

	public String go() throws Exception {

		CoreUser user = (CoreUser) basicService.get(CoreUser.class, userId);

		System.out.println("user======"+user);
		
		com.sxit.models.wait.CoreInnerMsg msg=new CoreInnerMsg();
		com.sxit.models.wait.CoreInnerMsgDest dest=new CoreInnerMsgDest();
		msg.setFlag((short)0);
		msg.setParentId(0);
		msg.setSendTime(new java.sql.Timestamp(System.currentTimeMillis()));
		msg.setSendUserid(1);
		msg.setTitle(null);
		dest.setDestUserid(userId);
		
		if (user.getStatus() == 0) {
			this.message = "该用户已经认证通过,无需再次认证";
			return "message";

		} else if (user.getStatus() == 1) {
			this.message = "该用户还未发起认证请求,请返回";
			return "message";
		}

		history.setBusinessid(businessid);
		history.setServiceid(userId);
		history.setDotime(new java.sql.Timestamp(System.currentTimeMillis()));
		history.setDouserid(this.getLoginUser().getUserid());
		history.setDousername(this.getLoginUser().getUsername());
		history.setNodeid(0);
		basicService.save(history);
		
		CoreUserPersonal personal=(CoreUserPersonal)basicService.get(CoreUserPersonal.class,userId);
		short msgunread=personal.getCountMsgUnread()!=null?personal.getCountMsgUnread().shortValue():0;
		personal.setCountMsgUnread((short)(msgunread+1));
		basicService.update(personal);
		
		
		int waitid = user.getWaitid();
		if (history.getSimpleapprove() == 1) {

			user.setStatus((short) 0);
			user.setApproveType(approveType);
			basicService.update(user);
			msg.setContent("您的身份认证信息通过!");
			com.sxit.wait.util.WaitWork.EndWait(waitid, this.getLoginUser().getUserid());

		} else if (history.getSimpleapprove() == 2) {

			user.setStatus((short) 3);
			basicService.update(user);
			//审批不通过的话，继续留在那里，等其他人来修改吧
//			com.sxit.wait.util.WaitWork.EndWait(waitid, this.getLoginUser().getUserid());
			msg.setContent("您的身份认证信息不通过!原因:"+history.getDomessage());
			com.sxit.wait.util.WaitWork.EndWait(waitid, this.getLoginUser().getUserid());

		}

		
		basicService.save(msg);
		
		dest.setDelflag((short)0);
		dest.setMsgId(msg.getId());
		basicService.save(dest);
		
		this.message = "该用户信息认证信息处理成功,请返回";
		this.nextPage = "waitUsersList.action";

		return SUCCESS;
	}
	private int approveType;
	
	private int businessid;
	private int userId;

	public void setBusinessid(int businessid) {
		this.businessid = businessid;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String input() {

		return INPUT;
	}

	private TwflDohistory history;

	public TwflDohistory getHistory() {
		return this.history;
	}

	public void setApproveType(int approveType) {
		this.approveType = approveType;
	}

	

}
