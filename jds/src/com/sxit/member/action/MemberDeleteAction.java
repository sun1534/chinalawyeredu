package com.sxit.member.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;




/**
 *
 * <p>功能： 删除会员登录</p>
 * <p>作者： 罗裴</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-04-24</p>
 * @版本： V1.0
 * @修改：
 */

public class MemberDeleteAction extends AbstractAction {

	private TmemMember member;

	public MemberDeleteAction() {
           rights="mem1,8";
           nextpage="memberList.action?pagenumber="+pagenumber;
	}
	public String go() throws HibernateException {
                TmemMember member = (TmemMember) get("member");
                getSession().delete(member);
                message="删除成功！";
		return SUCCESS;
	}

	public TmemMember getMember() {
         if (member==null)
            member = (TmemMember) get("member");
          return member;
	}
}
