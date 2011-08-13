package com.changpeng.help.action;


import java.util.*;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.changpeng.help.model.*;

/**
 * 创建站内消息
 * @author sinhoo
 * Sep 7, 2009
 */

public class MsgCreateAction extends AbstractAction {

	private String userids;
	private String title;
	private String contents;
	private List userlist;
	
	public List getUserlist() {
		return userlist;
	}

	public void setUserlist(List userlist) {
		this.userlist = userlist;
	}

	public void setUserids(String userids) {
		this.userids = userids;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public MsgCreateAction() {
           rights="hlp4,2";
		
	}

	public String go() throws HibernateException {
		String[] str=userids.split("\\s");
		ThlpMsg msg=null;
		for(String s:str){
			try{
				long touser=Long.parseLong(s);
				msg=new ThlpMsg();
				msg.setContents(contents);
				msg.setFromuid(curuser.getUserid());
				msg.setFromuname(curuser.getUsername());
				msg.setTitle(title);
				msg.setTouser(touser);
				msg.setCreatetime(new java.util.Date());
				getSession().save(msg);
			}catch(Exception e){
				LOG.error(e);
			}
		}
		this.message="消息发送成功。";
		this.nextpage="msgCreate!input.action";
        return SUCCESS;
	}


    public String input() throws Exception {
    	this.userlist=getSession().createQuery(" from TsysUser where userid<>'"+curuser.getUserid()+"' and statusid=1 order by userid").list();
        return INPUT;
    }
}
