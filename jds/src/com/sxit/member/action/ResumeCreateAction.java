package com.sxit.member.action;

import java.util.Calendar;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 * <p>
 * 功能： 创建简历录入
 * </p>
 * <p>
 * 作者： 雷华
 * </p>
 * <p>
 * 公司： 深圳信科
 * </p>
 * <p>
 * 日期： 2008-05-13
 * </p>
 * 
 * @版本： V1.0
 * @修改： 张如兵 2008-8-21
 */

public class ResumeCreateAction extends AbstractAction {

	private TmemResume resume;
	private TmemMember member;
	private int memberid;
	private List userlist;
	public List getUserlist(){
		return this.userlist;
	}
	public ResumeCreateAction() {
		rights = "mem2,2";
		resume = new TmemResume();
	}

	public String go() throws HibernateException {

		//TmemMember member=(TmemMember)getSession().get(TmemMember.class,resume.getResumeid());
		//resume.setUsername(member.getUsername());
		resume.setCreatedate(new java.sql.Timestamp(System.currentTimeMillis()));
		//resume.setTmemMember(member);
		getSession().save(resume);
		set("resume", resume);

		nextpage = "resumeView.action?resumeid="+memberid+"&pagenumber=0";
		message = "保存成功！";
		return SUCCESS;
	}

	public TmemResume getResume() {
		return resume;
	}

	public String input() throws Exception {
		member=(TmemMember)getSession().get(TmemMember.class,Integer.valueOf(memberid));
		return "input";
	}
	
	public TmemMember getMember() {
		return member;
	}
    public void setMemberid(int memberid) {

          this.memberid = memberid;
    }
    public int getMemberid() {
          return this.memberid;
    }
    }
