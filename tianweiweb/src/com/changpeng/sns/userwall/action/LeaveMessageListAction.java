/**
 * 留言板 分页显示
 */
package com.changpeng.sns.userwall.action;

import java.util.List;


import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.JugeTime;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.UserPersonalService;
import com.changpeng.core.service.UserService;
import com.changpeng.sns.userwall.model.SnsUserWall;
import com.changpeng.sns.userwall.service.UserWallService;

public class LeaveMessageListAction extends AbstractListAction {
	Logger log = Logger.getLogger(LeaveMessageListAction.class);

	@Override
	protected String go() {
		jt = new JugeTime();
		UserWallService userwallservice = (UserWallService) this.getBean("userWallService");
		UserService userService = (UserService) this.getBean("userService");
		
		//留言板的名称
		if (viewUserid == currentUserid) {
			UserPersonalService ups=(UserPersonalService)this.getBean("userPersonalService");
			ups.clearComMsgCount(this.currentUserid);
			name = "我";
		}else{
			name = userService.getUserinfoById(viewUserid).getUserName();
		}
		if (pageNo < 1) pageNo = 1;
		
		//messageid=0 留言,  不为0 获取回复
		if (messageid.equals("0")) {
			log.info(name + "的留言,我的userid为：" + viewUserid);

			page = userwallservice.getUserWallList(viewUserid, pageSize, pageNo);
			
			Pagefoot pagefoot = new Pagefoot();
			pageString = pagefoot.packString(page, pageNo,"getPage");
			

			return SUCCESS;
		} else {
			//获取 一条留言的所有回复
			usermessagelist = userwallservice.getUserWallReply(Integer.parseInt(messageid));
			return "SUCCESS1";
		}

	}
	
	public LeaveMessageListAction() {
		moduleid=12;
	}

	public List<SnsUserWall> usermessagelist;
	private String pageString;

	public String getPageString() {
		return pageString;
	}

	private String name = "";
	private JugeTime jt;

	public JugeTime getJt() {
		return jt;
	}

	public String getName() {
		return name;
	}

	private String messageid = "0";

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}
}
