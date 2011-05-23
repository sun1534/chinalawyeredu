package com.changpeng.diaocha.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.*;
import java.util.*;
public class ReplyViewAction extends AbstractAction{
	private int diaochaid;
	private String replyuser;
	
	private Diaocha diaocha;
	private List<Diaochawenti> wentilist;
	private List<String> anwsers;
	private List<String> replysothers;
	public ReplyViewAction(){
//		this.rightCode="diaocha";
	}
	public List<String> getAnwsers() {
		return anwsers;
	}
	public List<String> getReplysothers() {
		return replysothers;
	}
	public Diaocha getDiaocha() {
		return diaocha;
	}
	public List<Diaochawenti> getWentilist() {
		return wentilist;
	}
	public void setDiaochaid(int diaochaid) {
		this.diaochaid = diaochaid;
	}
	public void setReplyuser(String replyuser) {
		this.replyuser = replyuser;
	}
	@SuppressWarnings("unchecked")
	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService) this.getBean("basicService");
		
		diaocha=(Diaocha)service.get(Diaocha.class, diaochaid);
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Diaochawenti.class);
		detachedCriteria.add(Restrictions.eq("diaocha.diaochaid", diaochaid));
		
		detachedCriteria.addOrder(Order.asc("wentiid"));

		wentilist=service.findAllByCriteria(detachedCriteria);
		anwsers=new ArrayList();
		replysothers=new ArrayList();
		for(Diaochawenti wenti:wentilist){
			Set<Diaochareply> replys=wenti.getDiaochareplys();
		
			
			String anwser="";
			String other="";
			for(Diaochareply reply:replys){
	
				if(reply.getReplyuser().equals(replyuser)){
					anwser=reply.getReplycontent();
					other=reply.getOthers();
				}
					
			}
			anwsers.add(anwser);
			replysothers.add(other);
		}
		
		return SUCCESS;
	}

}
