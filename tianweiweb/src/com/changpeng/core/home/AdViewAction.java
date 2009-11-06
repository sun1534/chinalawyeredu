package com.changpeng.core.home;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.CoreAdvtisment;


public class AdViewAction extends AbstractAction {

	private int id;
	
	private String picurl;
	
	private String linkurl;
	
	public AdViewAction(){
		this.rightCode="PUBLIC";
	}
	
	@Override
	protected String go() throws Exception {
		DetachedCriteria dc= DetachedCriteria.forClass(CoreAdvtisment.class);
		dc.add(Restrictions.eq("pos", id));
		dc.add(Restrictions.eq("status", (short)0));
		List l=service.findByCriteria(dc);
		
		if(l!=null&&l.size()>0){
			CoreAdvtisment ad=(CoreAdvtisment)l.get(0);
			picurl=ad.getPic();
			linkurl=ad.getUrl();
		}
		return SUCCESS;
	}

	public String getPicurl() {
		return picurl;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setId(int id) {
		this.id = id;
	}


}
