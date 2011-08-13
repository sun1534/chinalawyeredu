package com.sxit.member.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;




/**
 *
 * <p>功能： 编辑会员登录</p>
 * <p>作者： 罗裴</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-04-28</p>
 * @版本： V1.0
 * @修改：
 */

public class MemberEditAction extends AbstractAction {

	private TmemMember member;

	public MemberEditAction() {
          rights="mem1,4";
	}

	public String go() throws HibernateException {
                getSession().update(member);
		set("member", member);
                nextpage="memberList.action";
                message="保存成功！";
		return SUCCESS;
	}

	public TmemMember getMember() {
         if (member==null)
            member = (TmemMember) get("member");
          return member;
	}

        public String input() throws Exception {
          return "input";
        }


}
