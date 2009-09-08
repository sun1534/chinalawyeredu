package com.changpeng.diaocha.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.*;

import java.util.*;
public class ReplyViewAction20080720 extends AbstractAction{
	private boolean hasreply;
	private int diaochaid;
	private Set<Diaochawenti> wentilist=new HashSet<Diaochawenti>(0);
	private Diaocha diaocha;
	
	private boolean hastype;
	private Set<Diaochatype> diaochatypes=new HashSet<Diaochatype>(0);
	
	private List<Diaochareply> replys;
	private String replyuser;
	
	public boolean getHasreply(){
		return hasreply;
	}
	public Set<Diaochawenti> getWentilist() {
		return wentilist;
	}
	public Diaocha getDiaocha() {
		return diaocha;
	}
	
	public Set<Diaochatype> getDiaochatypes(){
		return diaochatypes;
	}
	public boolean getHastype(){
		return hastype;
	}
	
	private BasicService service;
	public ReplyViewAction20080720(){
	    service=(BasicService) this.getBean("basicService");
	    this.rightCode="diaocha";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected String go() throws Exception {
		
		diaocha=(Diaocha)service.get(Diaocha.class, diaochaid);
		diaochatypes=diaocha.getDiaochatypes();
		
		if(diaochatypes!=null&&diaochatypes.size()>0)
			hastype=true;
		wentilist=diaocha.getDiaochawentis();

		replys=service.find("from Diaochareply where replyuser='"+replyuser+"' and diaochawenti.diaocha.diaochaid="+diaochaid);

		return SUCCESS;
	}
	public void setDiaochaid(int diaochaid) {
		this.diaochaid = diaochaid;
	}
	public void setReplyuser(String replyuser){
		this.replyuser=replyuser;
	}
	public List<Diaochareply> getReplys() {
		return replys;
	}

}
