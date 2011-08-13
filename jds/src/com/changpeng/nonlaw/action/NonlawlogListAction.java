package com.changpeng.nonlaw.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import com.changpeng.nonlaw.model.TnlwNonlawlog;
import com.changpeng.operation.model.*;
import com.sxit.common.action.AbstractListAction;


/**
 *
 * <p>功能： 列表催收日志</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-14</p>
 * @版本： V1.0
 * @修改：
 */

public class NonlawlogListAction extends AbstractListAction  {
        private List nonlawloglist;
        
        private long nonlawtaskid;
        private long nonlawid;
     
		public long getNonlawid() {
			return nonlawid;
		}
		public void setNonlawid(long nonlawid) {
			this.nonlawid = nonlawid;
		}
		public long getNonlawtaskid() {
			return nonlawtaskid;
		}
		public void setNonlawtaskid(long nonlawtaskid) {
			this.nonlawtaskid = nonlawtaskid;
		}
		public NonlawlogListAction() {
          rights="nlw3,1";
        }
        public String go() throws HibernateException {
               /* creditloglist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();*/
                Criteria criteria = getSession().createCriteria(TnlwNonlawlog.class);
                criteria.add(Expression.eq("userid", curuser.getUserid()));
               // criteria.createCriteria("toprCredittask");
        		if (nonlawtaskid != 0)
        			criteria.add(Expression.eq("tnlwNonlawtask.nonlawtaskid", nonlawtaskid));        		
        		if (nonlawid != 0)
        			criteria.add(Expression.eq("tnlwNonlaw.nonlawid", nonlawid));   

        		nonlawloglist =page(criteria);
                
                return SUCCESS;
        }
		public List getNonlawloglist() {
			return nonlawloglist;
		}
		
		public void setNonlawloglist(List nonlawloglist) {
			this.nonlawloglist = nonlawloglist;
		}
		
   
}
