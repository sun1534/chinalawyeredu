/**
 * JifenQueryAction.java
 */

package com.changpeng.jifen.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Lawyerlessonxf;

/**
 * 积分查询，都查自己的律师积分
 * 
 * @author 华锋 2008-5-5 上午12:08:59
 * 
 */
public class JifenDeleteAction extends AbstractListAction {
	private static Log _LOG = LogFactory.getLog(JifenDeleteAction.class);

	private int xfid;
	private int lawyerid;

	/**
	 * @return the xfid
	 */
	public int getXfid() {
		return xfid;
	}

	/**
	 * @param xfid the xfid to set
	 */
	public void setXfid(int xfid) {
		this.xfid = xfid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractListAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// SysUser lawer = ;
		
		BasicService bs=(BasicService)getBean("basicService");
		
		Lawyerlessonxf xf=(Lawyerlessonxf)bs.get(Lawyerlessonxf.class, xfid);
		if(xf!=null){
			this.opResult=this.getLoginUser().getUserid()+"("+this.getLoginUser().getUsername()+")删除了"+lawyerid+"的学分信息:学分:"+xf.getPxxf()+",时间:"+xf.getPxdate();		
			bs.delete(xf);
		}
		_LOG.info("删除:"+lawyerid+"的学分信息:::"+xfid);
		
		this.nextPage="jifenQuery.pl?fromwhere=fromwhere&lawyerid="+lawyerid+"&year="+year;
		this.message="学分信息删除成功";
		
		return SUCCESS;
	}

	private int year;

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @param lawyerid
	 *            the lawyerid to set
	 */
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

	public int getLawyerid() {
		return lawyerid;
	}


}