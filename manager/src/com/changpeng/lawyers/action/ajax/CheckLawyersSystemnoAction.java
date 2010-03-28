package com.changpeng.lawyers.action.ajax;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.Lawyers;
//import com.changpeng.models.system.*;
public class CheckLawyersSystemnoAction extends AbstractAction{

	private String systemno;
	private boolean isrepeat;
	public boolean getIsrepeat() {
		return isrepeat;
	}
	
	/**
	 * @return the systemno
	 */
	public String getSystemno() {
		return systemno;
	}

	/**
	 * @param systemno the systemno to set
	 */
	public void setSystemno(String systemno) {
		this.systemno = systemno;
	}

	@Override
	protected String go() throws Exception {
		
		
		LawyersService service = (LawyersService) this.getBean("lawyersService");
		if(lawyerid==0){
			
			Lawyers	 lawyers=service.getLawyerBySystemno(systemno);
			if(lawyers!=null)
				isrepeat=true;	
		}
		else
		{
			BasicService bservice=(BasicService)this.getBean("basicService");
			Lawyers	 lawyers=(Lawyers)bservice.get(Lawyers.class, lawyerid);
			if(lawyers.getSystemno()!=null&&!lawyers.getSystemno().equals(systemno)){
				Lawyers	 _lawyers=service.getLawyerBySystemno(systemno);
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
