package com.changpeng.lawyers.action.ajax;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.Lawyers;
//import com.changpeng.models.system.*;
public class CheckLawyersCardnoAction extends AbstractAction{

	private String cardno;
	private boolean isrepeat;
	public boolean getIsrepeat() {
		return isrepeat;
	}
	
	/**
	 * @return the cardno
	 */
	public String getCardno() {
		return cardno;
	}

	/**
	 * @param cardno the cardno to set
	 */
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	@Override
	protected String go() throws Exception {
		
		
		LawyersService service = (LawyersService) this.getBean("lawyersService");
		if(lawyerid==0){
			
			Lawyers	 lawyers=service.getLawyerByCardno(cardno);
			if(lawyers!=null)
				isrepeat=true;	
		}
		else
		{
			BasicService bservice=(BasicService)this.getBean("basicService");
			Lawyers	 lawyers=(Lawyers)bservice.get(Lawyers.class, lawyerid);
			if(lawyers.getCardno()!=null&&!lawyers.getCardno().equals(cardno)){
				Lawyers	 _lawyers=service.getLawyerByCardno(cardno);
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
