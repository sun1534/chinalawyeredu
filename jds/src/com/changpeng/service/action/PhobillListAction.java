package com.changpeng.service.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;


/**
 *
 * <p>功能： 列表电话费管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-07</p>
 * @版本： V1.0
 * @修改：
 */

public class PhobillListAction extends AbstractListAction  {
        private List phobilllist;
        private String feetime;
        private String username;
        private String phone;
		public String getFeetime() {
			return feetime;
		}
		public String getUsername() {
			return username;
		}
		public String getPhone() {
			return phone;
		}
		public void setFeetime(String feetime) {
			this.feetime = feetime;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public PhobillListAction() {
          rights="ser1,1";
        }
        public String go() throws HibernateException {
                phobilllist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();
                return SUCCESS;
        }
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TserPhobill as phobill where 1=1";
                if(feetime!=null&&!"".equals(feetime))
                	queryName+=" and feetime='"+feetime+"'";
                if(phone!=null&&!"".equals(phone))
                	queryName+=" and phone like '%"+phone+"%'";
                if(username!=null&&!"".equals(username))
                	queryName+=" and username like '%"+feetime+"%'";
                basicDao.setSession(getSession());
                recordsize=basicDao.getCountOfQuery(queryName);
                queryName+=" order by phobill.phobillid desc";
                Query query = getSession().createQuery(queryName);
//                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }
        public List getPhobilllist() {
          return phobilllist;
        }
}
