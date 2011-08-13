package com.changpeng.help.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.help.model.*;
import org.hibernate.Query;
import java.util.List;

/**
 * 查看站内消息
 * @author sinhoo
 * Sep 7, 2009
 */

public class MsgViewAction extends AbstractAction {
	private ThlpMsg msg;
    private long msgid;
	
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

	public MsgViewAction() {
          rights="hlp4,1";
	}

	public String go() throws HibernateException {
		msg=(ThlpMsg)getSession().get(ThlpMsg.class,msgid);
  
       return SUCCESS;
	}
}
