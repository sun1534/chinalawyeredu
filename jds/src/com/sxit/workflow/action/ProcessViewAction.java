package com.sxit.workflow.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看流程</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-15</p>
 * @版本： V1.0
 * @修改：
 */

public class ProcessViewAction extends AbstractAction {
	private TwflProcess process;
        private int processid;
	public ProcessViewAction() {
          rights="wfl1,1";
	   process = new TwflProcess();
	}

	public String go() throws HibernateException {
           nextpage="processList.action?pagenumber="+pagenumber;
           process=(TwflProcess)getSession().get(TwflProcess.class,Integer.valueOf(processid));
           if(process==null){
             message="未找到此流程";
             return "message";
           }
           set("process", process);
           return SUCCESS;
	}
	public TwflProcess getProcess() {
		return process;
	}
        public void setProcessid(int processid) {
          this.processid = processid;
        }
        public int getProcessid() {
          return this.processid;
        }

}
