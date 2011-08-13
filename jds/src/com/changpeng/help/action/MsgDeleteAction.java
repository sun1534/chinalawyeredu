package com.changpeng.help.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.help.model.*;


/**
 * 删除站内消息
 * @author sinhoo
 * Sep 7, 2009
 */

public class MsgDeleteAction extends AbstractAction {

	private long msgid;
	
	
	public long getMsgid() {
		return msgid;
	}
	public void setMsgid(long msgid) {
		this.msgid = msgid;
	}
	public MsgDeleteAction() {
           rights="hlp4,8";
           nextpage="msgList.action?pagenumber="+pagenumber;
	}
	public String go() throws HibernateException {
		
		getSession().createSQLQuery("delete from thlp_msg where msgid="+msgid).executeUpdate();
        message="删除成功！";
		
		return SUCCESS;
	}

}
