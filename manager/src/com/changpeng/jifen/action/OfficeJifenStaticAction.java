/**
 * 
 */
package com.changpeng.jifen.action;

import java.util.ArrayList;
import java.util.List;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.jifen.service.LawyerlessonxfService;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.jifen.util.Jifenstatics;
import com.changpeng.jifen.util.NumberUtil;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUnionparams;

/**
 * 
 * 事务所积分统计 整个的积分统计，实际只做了事务所的市律协的
 * 
 * 
 * @author 华锋 事务所的积分统计
 */
public class OfficeJifenStaticAction extends AbstractListAction {

	public OfficeJifenStaticAction() {
		this.jifenstatics = new Jifenstatics();
		// 直接获取到officeid就可以了

		// this.selectoffice=getLoginUser().getOfficeid();
		// //this.getLoginUser().getSysGroup().getGroupid();
	}

	@Override
	protected String go() throws Exception {
		this.selectoffice = getLoginUser().getOfficeid();
		LawyerlessonxfService xfservice = (LawyerlessonxfService) getBean("lawyerlessonxfService");

		// int groupid=this.getLoginUser().getOfficeid();
		group = (SysGroup) basicService.get(SysGroup.class, selectoffice);
		SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class, group.getParentid());
		if (params == null) {
			this.message = "您所在的律协没有设置达标分等参数,请联系管理员";
			return "message";
		}
		dabiaofen = params.getDabiaofen();
		localfen=params.getLocalfen();

		// 根据用户选择的年份以及年审时间得到查询的起始终止时间段
		jifentime = com.changpeng.jifen.util.CommonDatas.getJifenTime(year, params.getNianshen());
	    year=jifentime.getNianshenyear();
		// 统计这个事务所的律师，达标数、未达标数、未培训数
		// getOfficeDabiaoshu(jifentime.getStart(),jifentime.getEnd(),dabiaofen,selectoffice);

		// 统计这个律协的律师，达标数、未达标数、未培训数
//		this.jifenstatics = xfservice.getFiledDabiaoshu(jifentime.getStart(), jifentime.getEnd(), dabiaofen,
//				"officeid", selectoffice);
	    
		this.jifenstatics = xfservice.getFiledDabiaoshu(jifentime.getNianshenyear(), dabiaofen,params.getLocalfen(),
				"officeid", selectoffice);

		// 显示律师的明细情况

		// 得到统计数据列表
		debug("===from:::" + jifentime.getStartstr() + ",===end:::" + jifentime.getEndstr());

		


		if(!resultType.equals("")&&resultType.equals("excel")){
			pageNo=1;
			pageSize=Integer.MAX_VALUE;
			this.page = xfservice.getJifentongji(jifentime.getNianshenyear(), null, lawyername, lawyerno,
					pageNo, pageSize, this.isdabiao, jifenstatics, "officeid", selectoffice);
			return "excel";
		}else{
		
		
			this.page = xfservice.getJifentongji(jifentime.getNianshenyear(), null, lawyername, lawyerno,
					pageNo, pageSize, this.isdabiao, jifenstatics, "officeid", selectoffice);
			return SUCCESS;
		}
		
		
		
		
	}

private String resultType="list";
	
	
	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/**
	 * @param resultType the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	
	private SysGroup group;
	private int selectoffice;
	private Jifenstatics jifenstatics;
	private JifenTime jifentime;
	private float dabiaofen;
	private float localfen;
	private String lawyerno;
	private String lawyername;
	private int isdabiao;
	private int year;

	/**
	 * @return the isdabiao
	 */
	public int getIsdabiao() {
		return isdabiao;
	}

	/**
	 * @param isdabiao
	 *            the isdabiao to set
	 */
	public void setIsdabiao(int isdabiao) {
		this.isdabiao = isdabiao;
	}

	/**
	 * @return the lawyerno
	 */
	public String getLawyerno() {
		return lawyerno;
	}

	/**
	 * @param lawyerno
	 *            the lawyerno to set
	 */
	public void setLawyerno(String lawyerno) {
		this.lawyerno = lawyerno;
	}

	/**
	 * @return the lawyername
	 */
	public String getLawyername() {
		return lawyername;
	}

	/**
	 * @param lawyername
	 *            the lawyername to set
	 */
	public void setLawyername(String lawyername) {
		this.lawyername = lawyername;
	}

	/**
	 * @return the dabiaofen
	 */
	public float getDabiaofen() {
		return dabiaofen;
	}

	public Jifenstatics getJifenstatics() {
		return this.jifenstatics;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the jifentime
	 */
	public JifenTime getJifentime() {
		return jifentime;
	}

	/**
	 * @return the selectoffice
	 */
	public int getSelectoffice() {
		return selectoffice;
	}

	/**
	 * @param selectoffice
	 *            the selectoffice to set
	 */
	public void setSelectoffice(int selectoffice) {
		this.selectoffice = selectoffice;
	}

	/**
	 * @return the group
	 */
	public SysGroup getGroup() {
		return group;
	}

	/**
	 * @return the localfen
	 */
	public float getLocalfen() {
		return localfen;
	}
}
