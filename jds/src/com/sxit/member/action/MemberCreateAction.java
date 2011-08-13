package com.sxit.member.action;


import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Expression;

import com.sxit.common.action.AbstractAction;
import com.sxit.common.util.md5;
import com.sxit.member.model.*;


/**
 *
 * <p>功能： 创建会员登录</p>
 * <p>作者： 罗裴</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-04-28</p>
 * @版本： V1.0
 * @修改：
 */

public class MemberCreateAction extends AbstractAction {

	private TmemMember member;


	public MemberCreateAction() {
           rights="mem1,2";
		member = new TmemMember();
	}

	public String go() throws HibernateException {
		Criteria criteria = getSession().createCriteria(TmemMember.class);
        criteria.add(Expression.eq("loginname", member.getLoginname()));
        List list = criteria.list();
        if(list.size()>0){
          message="该用户名已经被使用,请重新输入一个用户名！";
          nextpage="../member/memberCreate.jsp";
          return "message";
        }
		else
		{
			member.setCreatedate(new java.sql.Timestamp(System.currentTimeMillis()));
			member.setPassword(md5.MD5(member.getPassword()));
//			System.out.println("=="+getSession().save(member)+"::::"+member.getMemberid());
			getSession().save(member);
//			commit();
//			set("member", member);
//	        nextpage="memberLogin.action";
//			System.out.println("member----------"+member.getLoginname());
			nextpage="../member/memberList.action";
	        message="保存成功！";
			return SUCCESS;
		}
	}

	public TmemMember getMember() {
		return member;
	}
        public String input() throws Exception {
            return "input";
    }
}
