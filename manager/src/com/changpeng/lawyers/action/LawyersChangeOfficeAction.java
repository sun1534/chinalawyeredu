/**
 * 
 */
package com.changpeng.lawyers.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysUnionparams;
import com.changpeng.system.util.CommonDatas;

/**
 * 
 * 律师转所，就是改变他的所属单位而已
 * 
 * @author 华锋
 * 
 */
public class LawyersChangeOfficeAction extends AbstractAction {

	public LawyersChangeOfficeAction() {

		this.datavisible = new DataVisible();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		LawyersService bs = (LawyersService) this.getBean("lawyersService");
	
		int oldoffice=lawyers.getTheoffice();
		lawyers.setDirectunion(this.datavisible.getCityid());
		lawyers.setProvinceunion(this.datavisible.getProvinceid());
		lawyers.setTheoffice(this.datavisible.getOfficeid());

		if (lawyers.getTheoffice() == 0) {
			this.message = "必须选择所在的事务所,请返回";
			return "message";
		}

		lawyers.setLoginname(lawyers.getLawyerno());
		lawyers.setPasswd(lawyers.getCertno());
		bs.updateLawyers(lawyers);
		this.message = "律师转所成功";

		this.opResult=lawyers.getLawyername()+"由"+oldoffice+"转移到"+lawyers.getTheoffice();
		
		this.nextPage = "lawyersList.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		if(this.getLoginUser().getSysGroup()!=null&&this.getLoginUser().getSysGroup().getGrouptype()==1){
			this.message="您属于律师事务所,没有权限对律师进行转所操作";
			
			return "message";
			
		}
		
		CommonDatas.getGroups();
		
		
		
		BasicService bservice = (BasicService) this.getBean("basicService");
		lawyers = (Lawyers) bservice.get(Lawyers.class, lawyerid);
		
		

		this.datavisible.setCityid(lawyers.getDirectunion());
		this.datavisible.setOfficeid(lawyers.getTheoffice());
		this.datavisible.setProvinceid(lawyers.getProvinceunion());

		this.datavisible.getVisibleDatas(this.getLoginUser(), false);

		set("lawyers", lawyers);

		return INPUT;
	}

	private Lawyers lawyers;

	public Lawyers getLawyers() {
		if (lawyers == null)
			lawyers = (Lawyers) this.get("lawyers");

		return lawyers;
	}

	private int lawyerid;

	/**
	 * @return the lawyerid
	 */
	public int getLawyerid() {
		return lawyerid;
	}

	/**
	 * @param lawyerid
	 *            the lawyerid to set
	 */
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

}
