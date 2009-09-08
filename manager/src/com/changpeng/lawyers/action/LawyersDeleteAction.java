/**
 * 
 */
package com.changpeng.lawyers.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lawyers;
import com.changpeng.models.LawyersDelete;

/**
 * @author 华锋
 * 
 */
public class LawyersDeleteAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		BasicService bs = (BasicService) this.getBean("basicService");
		
		
		Lawyers lawyers=(Lawyers)bs.get(Lawyers.class, lawyerid);

		bs.delete(lawyers);
		
		LawyersDelete delete=new LawyersDelete();
		delete.setLawyername(lawyers.getLawyername());
		delete.setLawyerenname(lawyers.getLawyerenname());
		delete.setLawyerno(lawyers.getLawyerno());
		delete.setLoginname(lawyers.getLoginname());
		delete.setPasswd(lawyers.getPasswd());
		delete.setBirthday(lawyers.getBirthday());
		delete.setCarddate(lawyers.getCarddate());
		delete.setCardno(lawyers.getCardno());
		delete.setCertno(lawyers.getCardno());
		delete.setCityid(lawyers.getCityid());
		delete.setCountryid(lawyers.getCountryid());
		delete.setCreatetime(lawyers.getCreatetime());
		delete.setCreateuser(lawyers.getCreateuser());
		delete.setCreateusername(lawyers.getCardno());
		delete.setDabiaofen(lawyers.getDabiaofen());
		delete.setDegree(lawyers.getDegree());
		delete.setDirectunion(lawyers.getDirectunion());
		delete.setDistrictid(lawyers.getDistrictid());
		delete.setEmail(lawyers.getEmail());
		delete.setFax(lawyers.getFax());
		delete.setForeignlan(lawyers.getForeignlan());
		delete.setForeignlevel(lawyers.getForeignlevel());
		delete.setGender(lawyers.getGender());
		delete.setHomephone(lawyers.getHomephone());
		delete.setIsmarrige(lawyers.getIsmarrige());
		delete.setLawyerenname(lawyers.getLawyerenname());
		delete.setLawyerid(lawyers.getDirectunion());
		delete.setLawyerenname(lawyers.getLawyerenname());
		delete.setMobile1(lawyers.getMobile1());
		delete.setMobile2(lawyers.getMobile2());
		delete.setMsn(lawyers.getMsn());
		delete.setOfficephone(lawyers.getOfficephone());
		delete.setPasswd(lawyers.getPasswd());
		delete.setPhoto(lawyers.getPhoto());
		delete.setPhotoname(lawyers.getPhotoname());
		delete.setPolicy(lawyers.getPolicy());
		delete.setPostcode(lawyers.getPostcode());
		delete.setProvinceid(lawyers.getProvinceid());
		delete.setProvinceunion(lawyers.getProvinceunion());
		delete.setQq(lawyers.getQq());
		delete.setRegsrc(lawyers.getRegsrc());
		delete.setRemarks(lawyers.getRemarks());
		delete.setSchool(lawyers.getSchool());
		delete.setSpecility(lawyers.getSpecility());
		delete.setStatus(lawyers.getStatus());
		delete.setSystemno(lawyers.getSystemno());
		delete.setTheoffice(lawyers.getTheoffice());
		delete.setWorktime(lawyers.getWorktime());
		delete.setZhiyedate(lawyers.getZhiyedate());
		delete.setDeletetime(new java.sql.Timestamp(System.currentTimeMillis()));
		delete.setDeleteuser(this.getLoginUser().getLoginname());
		
		bs.save(delete);

		this.message = "该律师信息删除成功,请返回"; //这里删除律师，都记录下日志吧
		
		this.opResult=this.getLoginUser().getLoginname()+"删除了律师:"+lawyers.getLawyername();

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
