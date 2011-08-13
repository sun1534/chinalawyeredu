package com.sxit.member.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.member.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看简历录入</p>
 * <p>作者： 雷华</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-05-16</p>
 * @版本： V1.0
 * @修改：
 */

public class WorkexpViewAction extends AbstractAction {
	private TmemWorkexp workexp;
        private int workexpid;
	public WorkexpViewAction() {
          rights="mem5,1";
	   workexp = new TmemWorkexp();
	}

	public String go() throws HibernateException {
           nextpage="workexpList.action?pagenumber="+pagenumber;
           workexp=(TmemWorkexp)getSession().get(TmemWorkexp.class,Integer.valueOf(workexpid));
           if(workexp==null){
             message="未找到此用户";
             return "message";
           }
           set("workexp", workexp);
           return SUCCESS;
	}
	public TmemWorkexp getWorkexp() {
		return workexp;
	}
        public void setWorkexpid(int workexpid) {

          this.workexpid = workexpid;
        }
        public int getWorkexpid() {
          return this.workexpid;
        }

}
