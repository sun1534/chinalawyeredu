package com.sxit.workflow.action;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflBusiness;
import com.sxit.models.workflow.TwflProcess;




/**
 * 
 * @author 华锋
 * Jul 9, 2009 3:48:04 PM
 *
 */

public class BusinessDeleteAction extends AbstractAction {

	
	public BusinessDeleteAction() {
         
	}
	public String go() throws Exception {
             
		DetachedCriteria dc=DetachedCriteria.forClass(TwflProcess.class);
		dc.add(Restrictions.eq("twflBusiness.businessid",businessid));
		List list=basicService.findAllByCriteria(dc);
		if(list!=null&&list.size()>0){
			
			this.message="有属于该业务的流程,删除失败";
			return "message";
		}
		basicService.delete(TwflBusiness.class, businessid);
		this.message="流程业务删除成功";
		this.nextPage="businessList.action";
		return SUCCESS;
	}


	private int businessid;
	public void setBusinessid(int id){
		this.businessid=id;
	}
	
}
