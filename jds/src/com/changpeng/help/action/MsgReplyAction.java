package com.changpeng.help.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.help.model.*;
import org.hibernate.Query;
import java.util.List;

/**
 * 站内消息回复
 * @author sinhoo
 * Sep 7, 2009
 */

public class MsgReplyAction extends AbstractAction {
	private ThlpMsg msg;
    private long msgid;
    private List userlist;
	
	public List getUserlist() {
		return userlist;
	}
    public ThlpMsg getMsg() {
		return msg;
	}

	public long getMsgid() {
		return msgid;
	}

	public void setMsg(ThlpMsg msg) {
		this.msg = msg;
	}

	public void setMsgid(long msgid) {
		this.msgid = msgid;
	}

	public MsgReplyAction() {
          rights="hlp4,1";
	}

	public String go() throws HibernateException {
		msg=(ThlpMsg)getSession().get(ThlpMsg.class,msgid);
		this.userlist=getSession().createQuery(" from TsysUser where userid<>'"+curuser.getUserid()+"' and statusid=1 order by userid").list();       
       return SUCCESS;
	}
}
