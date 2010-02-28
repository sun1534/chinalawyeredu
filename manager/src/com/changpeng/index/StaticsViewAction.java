/**
 * 
 */
package com.changpeng.index;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.LawyerlessonxfService;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.jifen.util.Jifenstatics;
import com.changpeng.jifen.util.LearnmodeStatics;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.lessons.util.Lessonstatics;
import com.changpeng.models.OfficeProperties;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUnionparams;
import com.changpeng.models.SysUser;

/**
 * @author 华锋
 * 
 * 首页显示的统计信息
 * 
 */
public class StaticsViewAction extends AbstractAction {

	private SysGroup sysgroup;

	public SysGroup getSysgroup() {
		return this.sysgroup;
	}

	public StaticsViewAction() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		sysgroup = (SysGroup) basicService.get(SysGroup.class, this.getLoginUser().getSysGroup() == null ? -1 : this
				.getLoginUser().getSysGroup().getGroupid());
		LawyerlessonxfService xfservice = (LawyerlessonxfService) this.getBean("lawyerlessonxfService");
		LessonsService lessonsservice = (LessonsService) this.getBean("lessonsService");
		LawyersService lawyersservice = (LawyersService) this.getBean("lawyersService");
		
		if (sysgroup == null || sysgroup.getGrouptype() > 3) {// 系统管理员层级
			
			jifentime = com.changpeng.jifen.util.CommonDatas.getJifenTime(0, "12-31");
			this.lawyerscnt=lawyersservice.getFieldLawyerCnt(null, 0);
			this.learnmodestatics = xfservice.getFiledLearnmode(jifentime.getStart(), jifentime.getEnd(),null,0);
//			 this.lessonstatics=lessonsservice.getFiledLessons(jifentime.getStart(), jifentime.getEnd(), null,0);
		    this.alllessonstatics=lessonsservice.getFiledLessons(jifentime.getStart(), jifentime.getEnd(), null,0);

			
		} else if (sysgroup.getGrouptype() == 1) {// 事务所
			SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class, sysgroup.getParentid());
			jifentime = com.changpeng.jifen.util.CommonDatas.getJifenTime(0, params.getNianshen());
			this.jifenstatics = xfservice.getFiledDabiaoshu(jifentime.getNianshenyear(), params
					.getDabiaofen(),params.getLocalfen(), "officeid", sysgroup.getGroupid());
			OfficeProperties properties= (OfficeProperties)basicService.get(OfficeProperties.class, sysgroup.getGroupid());
			if(properties!=null)
			this.officelogo=properties.getPhoto();
		    this.alllessonstatics=lessonsservice.getFiledLessons(jifentime.getStart(), jifentime.getEnd(), null,0);


		} else if (sysgroup.getGrouptype() == 2) {// 市级律协
			SysUnionparams params = sysgroup.getSysUnionparams();
			jifentime = com.changpeng.jifen.util.CommonDatas
					.getJifenTime(0, sysgroup.getSysUnionparams().getNianshen());
			this.jifenstatics = xfservice.getFiledDabiaoshu(jifentime.getNianshenyear(), params
					.getDabiaofen(),params.getLocalfen(), "cityid", sysgroup.getGroupid());
			this.learnmodestatics = xfservice.getFiledLearnmode(jifentime.getStart(), jifentime.getEnd(), "cityid",
					sysgroup.getGroupid());
		    this.alllessonstatics=lessonsservice.getFiledLessons(jifentime.getStart(), jifentime.getEnd(), null,0);

			this.lawyerscnt=this.jifenstatics.getAllusers();

		    this.lessonstatics=lessonsservice.getFiledLessons(jifentime.getStart(), jifentime.getEnd(), "cityid",sysgroup.getGroupid());

		} else if (sysgroup.getGrouptype() == 3) {// 省级律协
			jifentime = com.changpeng.jifen.util.CommonDatas.getJifenTime(0, "12-31");
			this.learnmodestatics = xfservice.getFiledLearnmode(jifentime.getNianshenyear(), "provinceid",
					sysgroup.getGroupid());
		    this.lessonstatics=lessonsservice.getFiledLessons(jifentime.getStart(), jifentime.getEnd(), "provinceid",sysgroup.getGroupid());
		    this.alllessonstatics=lessonsservice.getFiledLessons(jifentime.getStart(), jifentime.getEnd(), null,0);

			this.lawyerscnt=lawyersservice.getFieldLawyerCnt("provinceunion", sysgroup.getGroupid());
		}

		
//		System.out.println(lawyerscnt);
		
		return SUCCESS;
	}
	
	private String officelogo;
	private int lawyerscnt;

	private JifenTime jifentime;
	private Jifenstatics jifenstatics;
	private LearnmodeStatics learnmodestatics;
	private Lessonstatics lessonstatics;
	private Lessonstatics alllessonstatics;


	public Lessonstatics getAlllessonstatics() {
		return alllessonstatics;
	}
	
	/**
	 * @return the lessonstatics
	 */
	public Lessonstatics getLessonstatics() {
		return lessonstatics;
	}

	public SysUser getSysuser() {
		return this.getLoginUser();
	}

	/**
	 * @return the jifentime
	 */
	public JifenTime getJifentime() {
		return jifentime;
	}

	/**
	 * @return the jifenstatics
	 */
	public com.changpeng.jifen.util.Jifenstatics getJifenstatics() {
		return jifenstatics;
	}

	/**
	 * @return the learnmodestatics
	 */
	public LearnmodeStatics getLearnmodestatics() {
		return learnmodestatics;
	}

	/**
	 * @return the lawyerscnt
	 */
	public int getLawyerscnt() {
		return lawyerscnt;
	}

	/**
	 * @return the officelogo
	 */
	public String getOfficelogo() {
		return officelogo;
	}

}
