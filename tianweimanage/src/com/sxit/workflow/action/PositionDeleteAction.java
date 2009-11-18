package com.sxit.workflow.action;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflPosition;
import com.sxit.models.workflow.TwflProcess;




/**
 * 
 * @author 华锋
 * Jul 9, 2009 3:48:04 PM
 *
 */

public class PositionDeleteAction extends AbstractAction {

	
	public PositionDeleteAction() {
         
	}
	public String go() throws Exception {
             
//		DetachedCriteria dc=DetachedCriteria.forClass(TwflProcess.class);
//		dc.add(Restrictions.eq("twflBusiness.businessid",businessid));
//		List list=basicService.findAllByCriteria(dc);
//		if(list!=null&&list.size()>0){
//			
//			this.message="有属于该职务的用户,删除失败";
//			return "message";
//		}
		basicService.delete(TwflPosition.class, positionid);
		this.message="职务删除成功";
		this.nextPage="positionList.action";
		return SUCCESS;
	}


	private int positionid;
	public void setPositionid(int id){
		this.positionid=id;
	}
	
}
