package com.changpeng.system.action.ajax;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.Lawyers;
//import com.changpeng.models.system.*;
public class CheckLawyersLoginnameAction extends AbstractAction{

	private String loginname;
	private boolean isrepeat;
	public boolean getIsrepeat() {
		return isrepeat;
	}
	public void setLoginname(String loginname){
		this.loginname=loginname;
	}
	public String getLoginname() {
		return loginname;
	}
	@Override
	protected String go() throws Exception {
		
		
		LawyersService service = (LawyersService) this.getBean("lawyersService");
		if(lawyerid==0){
			
			Lawyers	 lawyers=service.getLawyerByLoginname(loginname);
			if(lawyers!=null)
				isrepeat=true;	
		}
		else
		{
			BasicService bservice=(BasicService)this.getBean("basicService");
			Lawyers	 lawyers=(Lawyers)bservice.get(Lawyers.class, lawyerid);
			if(!lawyers.getLoginname().equals(loginname)){
				Lawyers	 _lawyers=service.getLawyerByLoginname(loginname);
				if(_lawyers!=null)
					isrepeat=true;	
			}
		}
		
	
		
		System.out.println("isrepeat:::"+isrepeat);
		
		return SUCCESS;
	}

	private String now;
	/**
	 * @param now the now to set
	 */
	public void setNow(String now) {
		this.now = now;
	}
	
	private int lawyerid;
	/**
	 * @return the lawyerid
	 */
	public int getLawyerid() {
		return lawyerid;
	}
	/**
	 * @param lawyerid the lawyerid to set
	 */
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}
	
	
}
