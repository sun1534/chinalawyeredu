package com.changpeng.operation.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

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

public class CreditlogListAction extends AbstractListAction  {
        private List creditloglist;
        
        private long credittaskid;
        private long creditcardid;
       
		public long getCreditcardid() {
			return creditcardid;
		}
		public void setCreditcardid(long creditcardid) {
			this.creditcardid = creditcardid;
		}
		public long getCredittaskid() {
			return credittaskid;
		}
		public void setCredittaskid(long credittaskid) {
			this.credittaskid = credittaskid;
		}
		public CreditlogListAction() {
          rights="opr4,1";
        }
        public String go() throws HibernateException {
               /* creditloglist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();*/
                Criteria criteria = getSession().createCriteria(ToprCreditlog.class);
                //criteria.add(Expression.eq("userid", curuser.getUserid()));
               // criteria.createCriteria("toprCredittask");
        		if (credittaskid != 0)
        			criteria.add(Expression.eq("toprCredittask.credittaskid", credittaskid));   
        		if (creditcardid != 0)
        			criteria.add(Expression.eq("toprCreditcard.creditcardid", creditcardid));   
       
        		creditloglist = page(criteria);
                return SUCCESS;
        }
       /* private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from ToprCreditlog as creditlog order by creditlog.creditlogid desc";
                if(creditcardid!=0){
                	queryName="from ToprCreditlog as creditlog where creditlog.toprCredittask.creditcardid="+creditcardid+" order by creditlog.creditlogid desc";
                }
                Query query = getSession().createQuery(queryName);
                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }*/
        public List getCreditloglist() {
          return creditloglist;
        }
}
