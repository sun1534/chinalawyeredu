/**
 * 
 */
package com.changpeng.lawyers.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.LawyersShixi;

/**
 * @author 华锋
 * 
 */
public class LawyersShixiDeleteAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

//		BasicService bs = (BasicService) this.getBean("basicService");
//		
//		
//		Lawyers lawyers=(Lawyers)bs.get(Lawyers.class, lawyerid);
//
//		bs.delete(lawyers);
//		
//		
		
		LawyersService service=(LawyersService)this.getBean("lawyersService");
		LawyersShixi lawyer=service.deleteLawyersShixi(lawyerid, this.getLoginUser());

		this.message = "该律师信息以及对应的积分信息删除成功,请返回"; //这里删除律师，都记录下日志吧
		
		this.opResult=this.getLoginUser().getLoginname()+"删除了实习律师:"+lawyer.getLawyername();

		this.nextPage = "lawyersList.pl";
		return SUCCESS;
	}

	private int lawyerid;

	/**
	 * @param lawyerid
	 *            the lawyerid to set
	 */
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}
}
