package com.sxit.member.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看会员登录</p>
 * <p>作者： 罗裴</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-04-24</p>
 * @版本： V1.0
 * @修改：
 */

public class MemberViewAction extends AbstractAction {
	private TmemMember member;
        private int memberid;
	public MemberViewAction() {
          rights="mem1,1";
	   member = new TmemMember();
	}

	public String go() throws HibernateException {
           nextpage="memberList.action?pagenumber="+pagenumber;
           member=(TmemMember)getSession().get(TmemMember.class,Integer.valueOf(memberid));
           if(member==null){
             message="未找到此用户";
             return "message";
           }
           set("member", member);
           return SUCCESS;
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
