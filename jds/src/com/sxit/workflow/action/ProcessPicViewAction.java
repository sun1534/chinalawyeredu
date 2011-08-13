package com.sxit.workflow.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;
import com.sxit.workflow.model.TwflProcess;


/**
 *
 * <p>功能： 列表节点</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-16</p>
 * @版本： V1.0
 * @修改：
 */

public class ProcessPicViewAction extends AbstractListAction  {
        private List nodelist;
        private TwflProcess process;
        private int processid;
        public ProcessPicViewAction() {
          rights="wfl1,2";
        }
        public String go() throws HibernateException {
          process=(TwflProcess)getSession().get(TwflProcess.class,Integer.valueOf(processid));
          if(process==null){
            message = "未找到此流程";
            return "message";
          }
          set("process", process);
//          nodelist = getQuery()
//                        .setInteger("processid",processid)
//                        .setMaxResults(maxperpage)
//                        .setFirstResult(maxperpage * pagenumber)
//                        .setCacheable(true)
//                        .list();
                return SUCCESS;
        }
//        private Query getQuery() throws HibernateException {
//                String queryName ;
//                queryName="from TwflNode where processid=:processid order by nodeid desc";
//                Query query = getSession().createQuery(queryName);
//                return query;
//        }
        public List getNodelist() {
          return nodelist;
        }
        public void setProcessid(int processid) {
          this.processid = processid;
        }
        public TwflProcess getProcess() {
                return process;
	}
}
