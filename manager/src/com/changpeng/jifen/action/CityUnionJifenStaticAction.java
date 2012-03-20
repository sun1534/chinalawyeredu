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
import com.changpeng.models.SysRole;
import com.changpeng.models.SysUnionparams;

/**
 * @author 华锋 某个市律协的积分统计
 */
public class CityUnionJifenStaticAction extends AbstractListAction {
	public CityUnionJifenStaticAction() {
		this.jifenstatics = new Jifenstatics();
	}

	private String title;
	private String officename;
	private String resultType = "list";

	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}
	private boolean gongzheng;
	public boolean getgongzheng(){
		return gongzheng;
	}
	/**
	 * @param resultType
	 *            the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	@Override
	protected String go() throws Exception {

		SysRole role = this.getLoginUser().getSysRole();
		if(role!=null&&(role.getRoleid()==11||role.getRoleid()==12)){
			gongzheng=true;
		}
		
		if (this.selectcityid == 0) {
			this.selectcityid = super.getLoginUser().getSysGroup().getGroupid();
		}
		// int groupid = this.getLoginUser().getOfficeid();
		group = (SysGroup) basicService.get(SysGroup.class, selectcityid);

		LawyerlessonxfService xfservice = (LawyerlessonxfService) this.getBean("lawyerlessonxfService");

		SysGroup group = (SysGroup) basicService.get(SysGroup.class, selectcityid);
		SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class, group.getGroupid());
		if (params == null) {
			this.message = "您所在的律协没有设置达标分等参数,请联系管理员";
			return "message";
		}
		dabiaofen = params.getDabiaofen();
		localfen = params.getLocalfen();

		// 根据用户选择的年份以及年审时间得到查询的起始终止时间段
		jifentime = com.changpeng.jifen.util.CommonDatas.getJifenTime(year, params.getNianshen());
		year = jifentime.getNianshenyear();

		this.jifenstatics = xfservice.getFiledDabiaoshu(jifentime.getNianshenyear(), dabiaofen, localfen, "cityid",
				selectcityid);
		

		

		// 显示律师的明细情况

		// 得到统计数据列表
		debug("===from:::" + jifentime.getStartstr() + ",===end:::" + jifentime.getEndstr());

		if (!resultType.equals("") && resultType.equals("excel")) {
			pageNo = 1;
			pageSize = Integer.MAX_VALUE;
			this.page = xfservice.getJifentongji(jifentime.getNianshenyear(), officename, lawyername, lawyerno, title,
					pageNo, pageSize, this.isdabiao, jifenstatics, "cityid", selectcityid);
			return "excel";
		} else {

			this.page = xfservice.getJifentongji(jifentime.getNianshenyear(), officename, lawyername, lawyerno, title,
					pageNo, pageSize, this.isdabiao, jifenstatics, "cityid", selectcityid);
			return SUCCESS;
		}

	}

	private SysGroup group;
	private int selectcityid;
	private Jifenstatics jifenstatics;
	private JifenTime jifentime;
	private float dabiaofen;
	private float localfen;
	private String lawyerno;
	private String lawyername;
	private int isdabiao;

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

	private int year;

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
	 * @return the selectcityid
	 */
	public int getSelectcityid() {
		return selectcityid;
	}

	/**
	 * @param selectcityid
	 *            the selectcityid to set
	 */
	public void setSelectcityid(int selectcityid) {
		this.selectcityid = selectcityid;
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

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the officename
	 */
	public String getOfficename() {
		return officename;
	}

	/**
	 * @param officename
	 *            the officename to set
	 */
	public void setOfficename(String officename) {
		this.officename = officename;
	}
}
