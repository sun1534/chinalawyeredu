package com.changpeng.core.message.util;

import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.util.UserUtil;
import com.changpeng.core.message.model.CoreInnerMsg;
import com.changpeng.core.message.model.CoreInnerMsgDest;
import com.changpeng.core.user.model.Userinfo;

public class MsgUtil {
	Logger log=Logger.getLogger(MsgUtil.class);
	private BasicService service;
	UserUtil util=UserUtil.getInstance();
	
	public MsgUtil(){
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		service=(BasicService)wac.getBean("basicService");
	}	
	
	public Userinfo getDest(int msgid){
		log.debug("getDest()  msgid:"+msgid);
		CoreInnerMsg comm=(CoreInnerMsg)service.get(CoreInnerMsg.class, msgid);
		
		DetachedCriteria criteria=DetachedCriteria.forClass(CoreInnerMsgDest.class);
		criteria.add(Restrictions.eq("msgId", comm.getId()));
		List dests=service.findByCriteria(criteria);
		log.debug("getDest() destsize:"+dests.size());
		if(dests.size()>0){
			for(int i=0;i<dests.size();i++){
				int a=comm.getSendUserid();
				int b=((CoreInnerMsgDest)dests.get(i)).getDestUserid();
				if(a!=b&&b!=0){
					return util.getUserinfo(b);
				}
			}
		}
		
		return util.getUserinfo(comm.getSendUserid());
	}
	
	public String getCount(int parentid){
		log.debug("msgid:"+parentid);
		if(parentid==0)
			return "";
		
		DetachedCriteria criteria=DetachedCriteria.forClass(CoreInnerMsgDest.class);
		criteria.add(Restrictions.eq("msgId", parentid));
		List dests=service.findByCriteria(criteria);
		log.debug("dests.size:"+dests.size());
		if(dests.size()>2){
			int n=dests.size()-1;
			return "等"+n+"人";
		}else{
			return "";
		}
	}
	
	public String getDestAll(int parentid,int currentUserid){
		if(parentid==0)
			return "";
		DetachedCriteria criteria=DetachedCriteria.forClass(CoreInnerMsgDest.class);
		criteria.add(Restrictions.eq("msgId", parentid));
		List dests=service.findByCriteria(criteria);
		
		StringBuffer rt=new StringBuffer();
		if(dests.size()>0){
			for(int i=0;i<dests.size();i++){
				CoreInnerMsgDest du=(CoreInnerMsgDest)dests.get(i);
				if(du.getDestUserid()!=currentUserid){
					Userinfo u=util.getUserinfo(du.getDestUserid());
					if(u.getUserName()!=null){
						short dr=u.getUserRole();
						rt.append(u.getUserName()+(dr==1?"学生":(dr==2?"家长":"老师"))).append(";");
					}
				}
			}
		}
		return rt.toString();
	}
}
