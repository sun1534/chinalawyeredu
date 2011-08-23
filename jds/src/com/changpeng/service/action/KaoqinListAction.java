package com.changpeng.service.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;


/**
 *
 * <p>功能： 列表考勤管理</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-07</p>
 * @版本： V1.0
 * @修改：
 */

public class KaoqinListAction extends AbstractListAction  {
        private List kaoqinlist;
        private String username;
        private int kqflag;
        private int kqresult;
        private String kqdate;
        public KaoqinListAction() {
          rights="ser4,1";
        }
        public String go() throws HibernateException {
                kaoqinlist = getQuery()
                          .setMaxResults(maxperpage)
                        .setFirstResult(maxperpage * pagenumber)
                        .setCacheable(true)
                        .list();
                return SUCCESS;
        }
        private Query getQuery() throws HibernateException {
                String queryName ;
                queryName="from TserKaoqin as kaoqin where 1=1";
                if(kqflag!=0)
                	queryName+=" and kqflag="+kqflag;
                if(kqresult!=0)
                	queryName+=" and kqresult="+kqresult;
                if(username!=null&&!"".equals(username))
                	queryName+=" and username like '%"+username+"%'";
                if(kqdate!=null&&!"".equals(kqdate))
                	queryName+=" and kqdate='"+kqdate+"'";
                
                basicDao.setSession(getSession());
                recordsize=basicDao.getCountOfQuery(queryName);
                queryName+=" order by kaoqin.kaoqinid desc";
                Query query = getSession().createQuery(queryName);
//                recordsize = query.list().size();
                pagesize = (recordsize - 1) / maxperpage + 1;
                pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
                return query;
        }
        public List getKaoqinlist() {
          return kaoqinlist;
        }
		public String getUsername() {
			return username;
		}
		public int getKqflag() {
			return kqflag;
		}
		public int getKqresult() {
			return kqresult;
		}
		public String getKqdate() {
			return kqdate;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public void setKqflag(int kqflag) {
			this.kqflag = kqflag;
		}
		public void setKqresult(int kqresult) {
			this.kqresult = kqresult;
		}
		public void setKqdate(String kqdate) {
			this.kqdate = kqdate;
		}
}
